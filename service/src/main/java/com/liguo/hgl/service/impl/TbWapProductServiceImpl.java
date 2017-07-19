package com.liguo.hgl.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.dao.TbBrandMapper;
import com.liguo.hgl.proxydao.dao.TbProductTypeMapper;
import com.liguo.hgl.proxydao.dao.TbWapProductInventoryMapper;
import com.liguo.hgl.proxydao.dao.TbWapProductMapper;
import com.liguo.hgl.proxydao.dao.TbWebUserMapper;
import com.liguo.hgl.proxydao.dto.TbWapProductDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.ProductImport;
import com.liguo.hgl.proxydao.model.TbWapProduct;
import com.liguo.hgl.proxydao.model.TbWapProductInventory;
import com.liguo.hgl.proxydao.model.TbWebUser;
import com.liguo.hgl.proxydao.model.WapProductInfoDto;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.proxydao.util.ExcelOperate;
import com.liguo.hgl.proxydao.util.FileUtil;
import com.liguo.hgl.proxydao.util.OrderSerialNo;
import com.liguo.hgl.proxydao.util.StringUtil;
import com.liguo.hgl.service.TbWapProductService;

@Service
@Scope(value="prototype")
public class TbWapProductServiceImpl implements TbWapProductService {
    @Autowired
    private TbWapProductMapper tbWapProductMapper;
    
    @Autowired
    private TbWapProductInventoryMapper tbWapProductInventoryMapper;
    
    @Autowired
    private TbWebUserMapper tbWebUserMapper;
    
    @Autowired
    private TbBrandMapper tbBrandMapper;
    
    @Autowired
    private  TbProductTypeMapper tbProductTypeMapper;
    
    
    
    
    
    
    int startCount = 0; // 最小
    
    

    private static final Logger logger = LoggerFactory.getLogger(TbWapProductServiceImpl.class);

    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbWapProductMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TbWapProduct selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbWapProductMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TbWapProduct> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbWapProductMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int deleteByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbWapProductMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKeySelective(TbWapProduct record) throws RuntimeException {
        try {
        	TbWapProduct product = tbWapProductMapper.selectByPrimaryKey(record.getId());
        	int count = this.tbWapProductMapper.updateByPrimaryKeySelective(record);
        	logger.info("wap产品修改: " + count);
        	Integer userId = record.getCreateBy();
        	// 相册空间图片的复制
            copyFileToProduct(userId, record);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKey(TbWapProduct record) throws RuntimeException {
        try {
            return this.tbWapProductMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int insertSelective(TbWapProduct record) throws RuntimeException {
        try {
            return this.tbWapProductMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

	@Override
	public TbWapProductDto selectWapProductDtoByPrimaryKey(Integer id)
			throws RuntimeException {
		 try {
	            return this.tbWapProductMapper.selectWapProductDtoByPrimaryKey(id);
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	}
	
	public List<TbWapProductDto> selectByCriteria(Criteria criteria, PageDto pgo) throws RuntimeException {
		try {
            return this.tbWapProductMapper.selectByCriteria(criteria,pgo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}
	
	@Override
	public List<TbWapProductDto> selectByCriteria(Criteria criteria)
			throws RuntimeException {
		try {
			return this.tbWapProductMapper.selectByCriteria(criteria);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<WapProductInfoDto> selectInfoListByName(Criteria criteria,
			PageDto page) {
		try {
			return this.tbWapProductMapper.selectInfoListByName(criteria,page);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
    public WapProductInfoDto selectUpdateInfo(Criteria criteria){
   	 try {
            return this.tbWapProductMapper.selectUpdateInfo(criteria);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
   }

	@Override
	public int insert(TbWapProduct record) {
        try {
        	int count = this.tbWapProductMapper.insert(record);
        	
        	// 相册空间图片的复制
            copyFileToProduct(record.getCreateBy(), record);
        	logger.info("wap产品新增: " + count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}
	
	   /***
     * 
     * <相册空间图片复制到产品目录>
     * 
     * @param userId
     * @param tempTbWapProduct
     * @author wzt
     * @since 2016年6月8日
     */
    private void copyFileToProduct(Integer userId, TbWapProduct tempTbWapProduct) {
        String pimgone = tempTbWapProduct.getPimgOne();
        String pimgtwo = tempTbWapProduct.getPimgTwo();
        String pimgthree = tempTbWapProduct.getPimgThree();
        String dimgone = tempTbWapProduct.getDimgOne();
        String dimgtwo = tempTbWapProduct.getDimgTwo();
        String dimgthree = tempTbWapProduct.getDimgThree();

        Integer id = tempTbWapProduct.getId();

        getFilePath(userId, id, pimgone);

        getFilePath(userId, id, pimgtwo);

        getFilePath(userId, id, pimgthree);

        getFilePath(userId, id, dimgone);

        getFilePath(userId, id, dimgtwo);

        getFilePath(userId, id, dimgthree);
    }

    private void getFilePath(Integer userId, Integer id, String pimgone) {
        String fromFileName = HglContants.WAP_ALBUM_SPACE + userId + File.separator + pimgone;
        String toFileName = HglContants.WAP_PRODUCT + id + File.separator + pimgone;
        FileUtil.copy(fromFileName, toFileName);
    }
	
	 public int insertTbWapProduct(TbWapProduct tbWapProduct){
		 return  this.tbWapProductMapper.insert(tbWapProduct);
	 }
	 public List<TbWapProductDto> selectTbWapPickList(Criteria criteria){
		 return this.tbWapProductMapper.selectTbWapPickList(criteria);
	 }

    @Override
    public void insertBatchProduct(List<TbWapProduct> objList) {
        tbWapProductMapper.insertBatchProduct(objList);
    }

    @Override
    public void importWapProductBatch(Integer userId,MultipartFile productData,Model model) throws IOException {
        List<ProductImport> productAllList = null;
        // 库存 List<每一行的内容>
        int[] arr = {0, 15, 16, 17, 18, 19, 20, 21, 22, 23};

        // Map key 的数组
        String[] keyArr = {"name", "brandName", "brandId", "price", "producttypeName", "productTypeId", "attributes", "describes", "pimgOne",
                "pimgTwo", "pimgThree", "dimgOne", "dimgTwo", "dimgThree", "meterageUnit", "inventoryName", "code", "totalInventory",
                "attributesValues", "instockPrice", "outstockPrice", "salesPrice", "spec", "material"};
        String[] messageArr = {"产品名称为空", "", "品牌编号为空", "产品价格为空", "", "分类编号为空", "", "", "产品图片1为空", "", "", "", "", "", "计量单位为空", "库存名称为空", "货号为空",
                "库存为空", "", "成本价为空", "销售价为空", "市场价为空", "规格为空", "材质为空"};
        // 产品Map<产品名称,产品的一行内容>
        Map<String, List<Map<String, Object>>> ProductMap = new HashMap<String, List<Map<String, Object>>>();
        Map<String, List<Map<String, Object>>> inventoryMap = new HashMap<String, List<Map<String, Object>>>();
         // 所有数据的List
        productAllList = new ArrayList<ProductImport>();
        //产品列
        int[] arr1 = {0, 2, 3, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
        // 生成批次号
        String batchNo = OrderSerialNo.makeOrderNum(OrderSerialNo.BATCH_NO);
        TbWebUser tbWebUser = tbWebUserMapper.selectByPrimaryKey(userId);
        Integer shopId = tbWebUser.getShopId();
        List<Object> objList = null;
        // 07版本的，既然已经固定模板，就不加03版本的解析了
        objList = new ExcelOperate().readXlsxGetTwoArray(productData, arr, arr1, keyArr, batchNo, shopId, messageArr);
        // 获得解析excel结果 0 是产品，1是库存 ,2是全部
        ProductMap = (Map<String, List<Map<String, Object>>>) objList.get(0);
        inventoryMap = (Map<String, List<Map<String, Object>>>) objList.get(1);
        productAllList = (List<ProductImport>) objList.get(2);
        List<TbWapProductInventory> tbWapProductInventoryList = new ArrayList<TbWapProductInventory>();

        /************** 效验数据开始 ****************************/
        // 效验失败
        if (checkFileImportParam(productAllList)) {
            model.addAttribute("productAllList", productAllList);
            model.addAttribute("success", "3");
            return ;
        }
        
        List<Map<String, Object>> wapProductList = new LinkedList<Map<String, Object>>();
        // 用户ID
        // 取出去重后的产品，insert
        for (Map.Entry<String, List<Map<String, Object>>> tempMap : ProductMap.entrySet()) {
            Map<String, Object> productMap = tempMap.getValue().get(0);
            productMap.put("createBy", userId);
            // 产品的一条记录
            wapProductList.add(productMap);
        }
        List<TbWapProduct> tbWapProductList = productExcelToBean(wapProductList, TbWapProduct.class);

        if (CollectionUtils.isEmpty(tbWapProductList)) {
            model.addAttribute("success", "2");
            // TODO 这里调试方便
            model.addAttribute("message", "没有可添加的产品!");
            return ;  
        }
        tbWapProductMapper.insertBatchProduct(tbWapProductList);

        // 通过批次号查询
        Criteria criteria = new Criteria();
        criteria.put("batchNo", batchNo);
        // 批次号的作用在于，找到当前产品插入的数据
        List<TbWapProduct> tbWapProduct = tbWapProductMapper.selectByObject(criteria);

        // 通过循环，插入产品id
        for (int i = 0; i < tbWapProduct.size(); i++) {
            TbWapProduct tempTbWapProduct = tbWapProduct.get(i);
            String name = tempTbWapProduct.getName();
            List<Map<String, Object>> tempTbWapProductInventoryList = inventoryMap.get(name);
            List<TbWapProductInventory> tempTbWapProductInventoryObj = productExcelToBean(tempTbWapProductInventoryList,
                    TbWapProductInventory.class);
            // 解析得到的数据，应当不为空
            for (TbWapProductInventory pro_inv : tempTbWapProductInventoryObj) {
                pro_inv.setProductId(tempTbWapProduct.getId());
                pro_inv.setCreateBy(String.valueOf(userId));
                // 保存所有库存
                tbWapProductInventoryList.add(pro_inv);
            }

            // 相册空间图片的复制
            copyFileToProduct(userId, tempTbWapProduct);

        }

        // 批量 插入库存
        tbWapProductInventoryMapper.insertBatchInventory(tbWapProductInventoryList);
    }
    /***
     * 
     * <效验数据合法性>
     * @param productAllList        这个集合里面包含所有不合法的数据列表
     * @return                      productAllList
     * @author wzt
     * @since   2016年7月21日
     */
    private boolean checkFileImportParam(List<ProductImport> productAllList) {
        Criteria criteria = new Criteria();
        List<ProductImport> codeAllList = new ArrayList<ProductImport>();
        List<ProductImport> brandIdAllList = new ArrayList<ProductImport>();
        List<ProductImport> productTypeIdAllList = new ArrayList<ProductImport>();
        // 循环所有数据，提取出 产品名称，货号
        for (int j = 0; j < productAllList.size(); j++) {
            ProductImport productObj = productAllList.get(j);
            String name = productObj.getName();// 品名
            String code = productObj.getCode();// 货号
            Integer brandId = productObj.getBrandId();// 品牌id
            Integer productTypeId = productObj.getProductTypeId();// 分类id
            // 货号需效验的数据
            ProductImport codeProductImport = new ProductImport();
            codeProductImport.setName(name);
            codeProductImport.setCode(code);
            codeAllList.add(codeProductImport);
            // 品牌id需效验的数据
            ProductImport brandIdProductImport = new ProductImport();
            brandIdProductImport.setBrandId(brandId);
            brandIdAllList.add(brandIdProductImport);
            // 分类id需效验的数据
            ProductImport productTypeIdProductImport = new ProductImport();
            productTypeIdProductImport.setProductTypeId(productTypeId);
            productTypeIdAllList.add(productTypeIdProductImport);
        }

        // 货号错误数据List
        List<ProductImport> codeErrorList = new ArrayList<ProductImport>();
        List<ProductImport> brandIdErrorList = new ArrayList<ProductImport>();
        List<ProductImport> productTypeIdErrorList = new ArrayList<ProductImport>();

        int productCount = tbWapProductInventoryMapper.countByObject(criteria);
        int brandCount = tbBrandMapper.countByObject(criteria);
        int productTypeCount = tbProductTypeMapper.findNotParentIdCount();
        getCodeErrorList(criteria, codeAllList, codeErrorList, productCount, "code");
        getCodeErrorList(criteria, brandIdAllList, brandIdErrorList, brandCount, "brandId");
        getCodeErrorList(criteria, productTypeIdAllList, productTypeIdErrorList, productTypeCount, "productTypeId");
        String message = "";
        // 遍历数据源，如果数据库中包含上述信息，添加错误信息
        for (int i = 0; i < productAllList.size(); i++) {
            ProductImport productAll = productAllList.get(i);
            String name = productAll.getName();// 品名
            String code = productAll.getCode();// 货号
            String brandId = String.valueOf(productAll.getBrandId());// 品牌id
            String productTypeId = String.valueOf(productAll.getProductTypeId());// 分类id
            // 消息
            message = productAll.getMessage();
            message = StringUtil.isBlank(message) ? "" : message;

            /********************** 货号 ********************************/
            List<ProductImport> codeAllListtemp = new ArrayList<ProductImport>();
            ProductImport codeMaptemp = new ProductImport();
            codeMaptemp.setName(name);
            codeMaptemp.setCode(code);
            codeAllListtemp.add(codeMaptemp);
            if (codeAllListtemp.removeAll(codeErrorList)) {
                message = message + "</br>" + "货号重复";

            }

            /********************** 品牌 ********************************/
            List<ProductImport> brandAllListtemp = new ArrayList<ProductImport>();
            ProductImport brandIdMaptemp = new ProductImport();
            brandIdMaptemp.setBrandId(Integer.valueOf(brandId));
            brandAllListtemp.add(brandIdMaptemp);
            // 可以考虑交换，递减来增加效率
            if (brandAllListtemp.removeAll(brandIdErrorList)) {
                message = message + "</br>" + "无效品牌";
            }

            /********************** 分类 ********************************/
            List<ProductImport> productTypeAllListtemp = new ArrayList<ProductImport>();
            ProductImport productTypeIdMaptemp = new ProductImport();
            productTypeIdMaptemp.setProductTypeId(Integer.valueOf(productTypeId));
            productTypeAllListtemp.add(productTypeIdMaptemp);
            // 可以考虑交换，递减来增加效率
            if (productTypeAllListtemp.removeAll(productTypeIdErrorList)) {
                message = message + "</br>" + "无效分类";
            }
            productAll.setMessage(message);
        }
        return getCheckResult(codeErrorList, brandIdErrorList, productTypeIdErrorList, message);
    }
    
    
    /***
     * 
     * <获得错误的数据集合>
     * 
     * @param criteria
     * @param codeAllList
     *            数据源
     * @param codeErrorList
     *            需要返回的数据集合
     * @param count
     *            总数
     * @param type
     * @author wzt
     * @since 2016年6月5日
     */
    private void getCodeErrorList(Criteria criteria, List<ProductImport> codeAllList, List<ProductImport> codeErrorList, int count, String type) {
        // 货号效验
        int maxLength = 1000; // 查询多少行
        double maxCount = (double) count / (double) maxLength;
        for (int i = 0; i < maxCount; i++) {
            // 每次需要验证的1000条记录
            List<ProductImport> validateList = null;

            validateList = getProductList(criteria, maxLength, maxCount, type);
            if ("code".equals(type)) {
                codeAllList.retainAll(validateList);
                // 删除当次验证重复的数据
                codeAllList.removeAll(codeErrorList);
                if(!CollectionUtils.isEmpty(codeAllList)){
                    codeErrorList.addAll(codeAllList);
                }
            }
            else {

                codeAllList.removeAll(validateList);
                if(!CollectionUtils.isEmpty(codeAllList)){
                    codeErrorList.addAll(codeAllList);
                }
            }
        }
    }
    
    private List<ProductImport> getProductList(Criteria criteria, int maxLength, double maxCount, String type) {
        criteria.setMysqlOffset(startCount);
        criteria.setMysqlLength(maxLength);
        if (maxCount > 1) {
            startCount = startCount + 1000;
        }
        List<ProductImport> resultList = null;

        if ("code".equals(type)) {
            resultList = tbWapProductInventoryMapper.findProNameAndCodeLimit(criteria);
        }
        // name
        else if ("brandId".equals(type)) {
            resultList = tbBrandMapper.findBrandIdAll(criteria);
        }

        else if ("productTypeId".equals(type)) {
            resultList = tbProductTypeMapper.findNotParentId(criteria);
        }
        return resultList;
    }
    
    
    private boolean getCheckResult(List<ProductImport> p1, List<ProductImport> p2, List<ProductImport> p3, String message) {
        boolean result = false;
        if(!CollectionUtils.isEmpty(p1)){
            result = true;
        }
        if(!CollectionUtils.isEmpty(p2)){
            result = true;
        }
        if(!CollectionUtils.isEmpty(p3)){
            result = true;
        }
        if(StringUtil.isNotBlank(message)){
            result = true;
        }
        return result;
    }
    
    
    /***
     * 
     * <这个是特殊处理导入excelTO bean 的一个方法>
     * 
     * @param list
     * @param tbWapProduct
     * @author wzt
     * @since 2016年5月31日
     */
    private <T> List<T> productExcelToBean(List<Map<String, Object>> list, Class<T> clazz) {
        List<T> tList = new LinkedList<T>();
        for (Map<String, Object> jsonMap : list) {
            String productJsonStr = JSONObject.toJSONString(jsonMap);
            ObjectMapper objectMapper = new ObjectMapper();

            try {
                T t = objectMapper.readValue(productJsonStr, clazz);
                tList.add(t);
            }
            catch (Exception e) {
                logger.error(">>>>>>>>>>>>>>>>解析转换错误!!");
            }
        }
        return tList;

    }
}