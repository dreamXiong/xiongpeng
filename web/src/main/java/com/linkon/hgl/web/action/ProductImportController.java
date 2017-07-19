package com.linkon.hgl.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.ProductImport;
import com.liguo.hgl.proxydao.model.TbWapProduct;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.proxydao.util.FileUtil;
import com.liguo.hgl.proxydao.util.StringUtil;
import com.liguo.hgl.service.IProductTypeService;
import com.liguo.hgl.service.TbAlbumSpaceService;
import com.liguo.hgl.service.TbBrandService;
import com.liguo.hgl.service.TbWapProductInventoryService;
import com.liguo.hgl.service.TbWapProductService;
import com.liguo.hgl.service.TbWebUserService;

@Controller
@RequestMapping(value = "/import")
public class ProductImportController extends IBaseController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String IMPORT_PRO_LIST = "import/importProductList";

    private static final String IMPORT_PRO_INIT = "import/import";

    private static final String IMPORT_LIST = "product/importList";

    private static final String PRODUCT_MUNE = "product/productMune";

    int startCount = 0; // 最小

    @Autowired
    private TbWapProductService tbWapProductService;

    @Autowired
    private TbWapProductInventoryService tbWapProductInventoryService;

    @Autowired
    private TbBrandService tbBrandService;

    @Autowired
    private IProductTypeService productTypeService;

    @Autowired
    private TbAlbumSpaceService tbAlbumSpaceService;

    @Autowired
    private TbWebUserService tbWebUserService;

    @Override
    protected void init(PageDto page, HttpServletRequest request, HttpServletResponse response, Model model) {

        // TODO Auto-generated method stub

    }

    @Override
    public String doSearchResult() {

        // TODO Auto-generated method stub
        return null;
    }

    @RequestMapping(value = "/init")
    public String init(Model model) {
        return IMPORT_PRO_INIT;
    }

    @RequestMapping(value = "/productMuneView")
    public String productMuneView(Model model) {
        return PRODUCT_MUNE;
    }

    @RequestMapping(value = "/downloadTemplate", method = RequestMethod.GET)
    public void downloadTemplate(HttpServletResponse response) {
        String srcFile = HglContants.PRODUCT_BATCH_DOWNLOAD_PATH + HglContants.PRODUCT_BATCH_DOWNLOAD_NAME;
        new FileUtil().downloadFile(response, srcFile, HglContants.PRODUCT_BATCH_DOWNLOAD_NAME);
    }

    /***
     * 
     * <产品导入>
     * 
     * @param model
     * @return
     * @throws IOException
     * @author wzt
     * @since 2016年5月25日
     */
    @RequestMapping(value = "/productImport", method = RequestMethod.POST)
    public String productImport(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.debug("productImport is start...");
        // 初始化状态成功
        model.addAttribute("success", "1");
        model.addAttribute("message", "成功");
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile productData = multipartRequest.getFile("productData");
        Integer userId = getLoginUserId();
        try {
            tbWapProductService.importWapProductBatch(userId, productData, model);
        }
        catch (Exception e) {
            logger.error("unknown mistake, error message:" + e.getMessage());
            model.addAttribute("success", "2");
            // TODO 这里调试方便
            model.addAttribute("message", "产品批量导入出错!!");
        }

        return IMPORT_LIST;
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

        int productCount = tbWapProductInventoryService.countByObject(criteria);
        int brandCount = tbBrandService.countByObject(criteria);
        int productTypeCount = productTypeService.findNotParentIdCount();
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
            resultList = tbWapProductInventoryService.findProNameAndCodeLimit(criteria);
        }
        // name
        else if ("brandId".equals(type)) {
            resultList = tbBrandService.findBrandIdAll(criteria);
        }

        else if ("productTypeId".equals(type)) {
            resultList = productTypeService.findNotParentId(criteria);
        }
        return resultList;
    }

    /***
     * 效验文件名称，防止重复提交
     * 
     * @param filename
     * @return
     */
    public boolean checkForm(String filename) {
        if (!StringUtil.isBlank(filename)) {
            Criteria criteria = new Criteria();
            criteria.put("fileName", filename);
            List<TbWapProduct> tbWapProductDtoList = tbWapProductService.selectByObject(criteria);
            if (CollectionUtils.isEmpty(tbWapProductDtoList)) {
                return false;
            }
        }
        return true;
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

    public static void main(String[] args) throws IOException {
        List<Map<String, Object>> productAllList1 = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> productAllList2 = new ArrayList<Map<String, Object>>();
        Map<String, Object> m1 = new HashMap<String, Object>();
        Map<String, Object> m2 = new HashMap<String, Object>();

        m1.put("brandId", "1");
        // m1.put("2", "2");
        productAllList1.add(m1);
        m1 = new HashMap<String, Object>();
        m1.put("brandId", "1");
        // m1.put("4", "4");
        productAllList1.add(m1);
        m1 = new HashMap<String, Object>();
        m1.put("brandId", "1");
        // m1.put("6", "6");
        // m1.put("7", "7");
        productAllList1.add(m1);

        m2.put("brandId", "1");
        // m2.put("2", "2");
        productAllList2.add(m2);
        m2 = new HashMap<String, Object>();
        m2.put("brandId", "3");
        // m2.put("4", "4");
        productAllList2.add(m2);
        m2 = new HashMap<String, Object>();
        m2.put("brandId", "5");
        // m2.put("6", "6");
        // m2.put("7", "7");
        productAllList2.add(m2);

        // productAllList1.retainAll(productAllList2);
        // productAllList1.addAll(productAllList2);

        System.out.println(productAllList1.contains(productAllList2));

        // System.out.println(productAllList2.removeAll(productAllList1));
        // System.out.println(productAllList1.removeAll(productAllList2));
        // System.out.println(productAllList2.isEmpty());
        // productAllList1.addAll(productAllList1);

        // System.out.println(productAllList1);

        // int i =1;
        // int a = 1000;
        // double s = (double)i/(double)a;
        // System.out.println(s);

        // System.out.println(productAllList1.indexOf("1"));
        //
        // String s = "";
        // System.out.println(s);

        // subtract1(productAllList1,productAllList2);
    }

    public static byte[] toByteArray2(String filename) throws IOException {

        File f = new File(filename);
        if (!f.exists()) {
            throw new FileNotFoundException(filename);
        }

        FileChannel channel = null;
        FileInputStream fs = null;
        try {
            fs = new FileInputStream(f);
            channel = fs.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate((int) channel.size());
            while ((channel.read(byteBuffer)) > 0) {
                // do nothing
                // System.out.println("reading");
            }
            return byteBuffer.array();
        }
        catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
        finally {
            try {
                channel.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fs.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
