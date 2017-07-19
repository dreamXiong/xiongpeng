package com.linkon.hgl.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.dto.TbWapProductInventoryDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbWapProductInventory;
import com.liguo.hgl.proxydao.model.WapProductInfoDto;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.IProductTypeService;
import com.liguo.hgl.service.TbWapProductInventoryService;
import com.liguo.hgl.service.TbWapProductService;

@Controller
@RequestMapping("inventory")
public class InventoryController extends IBaseController{

	@Autowired
	@Qualifier("productTypeService")
	protected IProductTypeService productTypeService;

	@Autowired
	protected TbWapProductService tbWapProductService;
	
	@Autowired
	protected TbWapProductInventoryService tbWapProductInventoryService;

	/**
	 * 初始化方法
	 * @param page
	 * @param model
	 * @return
	 */
//	@RequestMapping("/index")
//	public String index(PageDto page, Model model) {
//		/* 查询库存列表 */
//		Criteria criteria = new Criteria();
//		criteria.put("deleted", 0);
//		List<TbWapProductInventoryDto> tbpiList = tbWapProductInventoryService.selectByCriteria(criteria, page);
//		model.addAttribute("tbpiList", tbpiList);
//		model.addAttribute(HglContants.PAGE_DTO_ID, page);
//		return "inventory/inventory2";
//	}

	/**
	 * 分页跳转方法
	 * @param request
	 * @param page
	 * @param model
	 * @return
	 */
	@RequestMapping("/serachInventory")
	public String serachInventory(String status,String productId,HttpServletRequest request, PageDto page,Model model) {
		/* 查询库存列表 */
		Criteria criteria = new Criteria();
		criteria.put("deleted", 0);
		if(StringUtils.isNotBlank(status)){
			criteria.put("status", status);
		}
		if(StringUtils.isNotBlank(productId)){
			criteria.put("productId", productId);
		}
		List<TbWapProductInventoryDto> tbpiList = tbWapProductInventoryService.selectByCriteria(criteria, page);
		if(tbpiList != null && tbpiList.size()>0){
			Integer shopFlag = tbpiList.get(0).getShopFlag();
			model.addAttribute("shopFlag", shopFlag);
		}
		model.addAttribute(HglContants.PAGE_DTO_ID, page);
		model.addAttribute("tbpiList", tbpiList);
		model.addAttribute("productId", productId);
		return "inventory/inventoryList";
	}

	/**
	 * 库存删除
	 * @param id
	 * @return
	 */
	@RequestMapping("/markDelete")
	public String markDelete(Integer id,String status,String productId,HttpServletRequest request, PageDto page,Model model) {
		Criteria criteria = new Criteria();
		criteria.put("id", id);
		criteria.put("lastUpdateBy", getLoginUser().getUserName());
		int count = tbWapProductInventoryService.markDeleteByPrimaryKey(criteria);
		logger.info("wap库存删除记录数: " + count);
		serachInventory(status,productId,request, page,model);
		return "inventory/inventoryList";
	}

	@RequestMapping("/operationInventory")
	public String operationInventory(Model model,Integer productId,HttpServletRequest request) {
		Criteria criteria = new Criteria();
		criteria.put("id", productId);
		criteria.put("userId", this.getLoginUserId());
		WapProductInfoDto pifd = tbWapProductService.selectUpdateInfo(criteria);
    	String[] attr = pifd.getAttributes().split("/");
    	model.addAttribute("pifd",pifd);
    	model.addAttribute("attr",attr);
		return "inventory/operationInventory";
	}

	/**
	 * 库存明细
	 * @param model
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/getInventoryById")
	public String getInventoryById(Model model,@RequestParam(required = true, value = "id") Integer id,HttpServletRequest request) {
		TbWapProductInventoryDto tbpi = tbWapProductInventoryService.selectById(id);
		model.addAttribute("pifd", tbpi);
		return "inventory/inventoryDetail";
	}
	
	/**
	 * 修改初始化 
	 * @param model
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/updateInit")
	public String updateInit(Model model,@RequestParam(required = true, value = "id") Integer id,HttpServletRequest request) {
		TbWapProductInventoryDto tbpi = tbWapProductInventoryService.selectById(id);
		model.addAttribute("pifd", tbpi);
		return "inventory/inventoryUpdate";
	}

	/**
	 * 保存库存
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/saveInventory")
	public @ResponseBody Map<String, Object> saveInventory(TbWapProductInventory productInventory,String[] attributeValues,HttpServletRequest request,Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		String values = "";
		if (attributeValues != null) {
			for (int i = 0; i < attributeValues.length; i++) {
				if (i > 0) {
					values += ";";
				}
				values += attributeValues[i];
			}
		}
		if (!"".equals(values)) {
			productInventory.setAttributesValues(values);
		}
		Integer id = productInventory.getId();
		if (id == null || "".equals(id)) {
			//productInventory.setShopId(this.getShopId());
			productInventory.setStatus(0);
			productInventory.setCreateTime(System.currentTimeMillis());
			productInventory.setSalesCount(0);
			productInventory.setUnsaleInventory(0);
			productInventory.setTotalInventory(productInventory.getSaleInventory());
			productInventory.setCreateBy(getLoginUser().getUserName());
			int count = tbWapProductInventoryService.insert(productInventory);
			logger.info("wap库存插入记录数: " + count);
		}else{
			TbWapProductInventory tbpi = tbWapProductInventoryService.selectByPrimaryKey(id);
			productInventory.setLastUpdateBy(getLoginUser().getUserName());
			int diff = productInventory.getSaleInventory() - tbpi.getSaleInventory();
			productInventory.setTotalInventory(tbpi.getTotalInventory() + diff);
			int cou = tbWapProductInventoryService.updateByPrimaryKeySelective(productInventory);
			logger.info("wap库存更新记录数: " + cou);
		}
		//map.put("record", record);
		return map;
	}

	/**
	 * 上架，下架
	 * @param id
	 * @param status
	 * @param version
	 * @return
	 */
	@RequestMapping("/updateStatus")
	public String updateStatus(Integer id,String status,Integer state,String productId,HttpServletRequest request, PageDto page,Model model) {
		TbWapProductInventory record = new TbWapProductInventory();
		record.setId(id);
		if (state == 0) {
			state = 1;
		} else {
			state = 0;
		}
		record.setStatus(state);
		int result = tbWapProductInventoryService.updateByPrimaryKeySelective(record);
		logger.info("wap库存上下架记录数: " + result);
		serachInventory(status,productId,request, page,model);
		return "inventory/inventoryList";
	}
	
	/**
	 * 批量上架，下架
	 * @param id
	 * @param status
	 * @param version
	 * @return
	 */
	@RequestMapping("/batchUpdateStatus")
	public String batchUpdateStatus(String[] id,String status,Integer state,String productId,HttpServletRequest request, PageDto page,Model model) {
		Criteria example = new Criteria();
		example.put("ids", id);
		example.put("status", state);
		int result = tbWapProductInventoryService.batchUpdateStatus(example);
		logger.info("wap库存批量上下架记录数: " + result);
		serachInventory(status,productId,request, page,model);
		return "inventory/inventoryList";
	}

	/**
	 * 判断库存编码是否重复
	 * @param id
	 * @param status
	 * @param version
	 * @return
	 */
	@RequestMapping("/isUniqueInventoryCode")
	public @ResponseBody Map<String, Object> isUniqueInventoryCode(String productId,String code,HttpServletRequest request) {
		Criteria example = new Criteria();
		example.put("code", code);
		example.put("deleted", 0);
		example.put("productId", productId);
		example.put("userId", this.getLoginUserId());
		List<TbWapProductInventoryDto> list = tbWapProductInventoryService.selectByCriteria(example);
		//List<TbWapProductInventory> list = tbWapProductInventoryService.selectByObject(example);
		Map<String, Object> map = new HashMap<String, Object>();
		if (list != null && list.size() > 0) {
			map.put("result", false);
		} else {
			map.put("result", true);
		}
		return map;
	}

	@RequestMapping("/getInventoryByProductId")
	public String getInventoryByProductId(@RequestParam(required = true, value = "productId") Integer productId,@RequestParam(required = true, value = "warehouseId") Integer warehouseId,Model model) {
//		Criteria criteria = new Criteria();
//		criteria.put("productId", productId);
//		criteria.put("warehouseId", warehouseId);
//		
//		/* 未被删除的库存 */
//		criteria.put("deleted", 0);
//		List<TbProductInventoryDto> specList = productInventoryService.selectByCriteria(criteria);
//		model.addAttribute("specList", specList);
//		/* 加载产品的特殊属性 */
//		TbProduct tbProduct = tbProductService.selectByPrimaryKey(productId);
//		String attributes = tbProduct.getAttributes();
//		if (attributes != null && !"".equals(attributes)) {
//			String[] attributeList = attributes.split("/");
//			model.addAttribute("attributeList", attributeList);
//		}
//		model.addAttribute("productId", productId);
//		model.addAttribute("warehouseId", warehouseId);
		return "inventory/spec";
	}

	/**
	 * 批量更新价格
	 * @param request
	 * @return
	 */
	@RequestMapping("/batchUpdatePrice")
	public String batchUpdatePrice(String[] id,String status,String productId,HttpServletRequest request, PageDto page,Model model) {
		/* 1为按入库价格 2为按零售标价 */ /* 1为按比例 2为按单价 */
		String asPrice = request.getParameter("asPrice");
		String priceMethod = request.getParameter("priceMethod");
		String price = request.getParameter("price");
		int count = tbWapProductInventoryService.batchUpdatePrice(id, asPrice,priceMethod, price, getLoginUser().getUserName());
		logger.info("wap批量更新价格记录数: " + count);
		serachInventory(status,productId,request, page,model);
		return "inventory/inventoryList";
	}

	@Override
	protected void init(PageDto page, HttpServletRequest request,HttpServletResponse response, Model model) {
		/* 查询库存列表 */
		Criteria criteria = new Criteria();
		criteria.put("deleted", 0);   
		String productId = request.getParameter("productId");
		if(productId == null){
			return;
		}
		criteria.put("productId", productId);
		//criteria.put("userId", this.getLoginUserId());
		model.addAttribute("productId", productId);
		TbWapProductInventoryDto wapProductInventoryDto = tbWapProductInventoryService.selectByProductNameCriteria(criteria);
		model.addAttribute("tbpiItem", wapProductInventoryDto);
		List<TbWapProductInventoryDto> tbpiList = tbWapProductInventoryService.selectByCriteria(criteria, page);
		if(tbpiList != null && tbpiList.size()>0){
			Integer shopFlag = tbpiList.get(0).getShopFlag();
			model.addAttribute("shopFlag", shopFlag);
			model.addAttribute("tbpiList", tbpiList);
			model.addAttribute(HglContants.PAGE_DTO_ID, page);
		}
	}

	@Override
	public String doSearchResult() {
		return null;
	}
}
