package com.linkon.admin.action;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.dto.TbProductInventoryDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbProduct;
import com.liguo.hgl.proxydao.model.TbProductInventory;
import com.liguo.hgl.proxydao.model.TbProductType;
import com.liguo.hgl.proxydao.model.TbWarehouse;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.proxydao.util.StringUtil;
import com.liguo.hgl.service.IProductTypeService;
import com.liguo.hgl.service.TbProductInventoryService;
import com.liguo.hgl.service.TbProductService;
import com.liguo.hgl.service.TbProvinceInfoService;
import com.liguo.hgl.service.TbWarehouseService;

@Controller
@RequestMapping("inventory")
public class InventoryController {

	@Autowired
	@Qualifier("productTypeService")
	protected IProductTypeService productTypeService;

	@Autowired
	protected TbProductInventoryService productInventoryService;

	@Autowired
	protected TbProductService tbProductService;

	@Autowired
	protected TbProvinceInfoService tbProvinceInfoService;

	@Autowired
	protected TbWarehouseService tbWarehouseService;

	@RequestMapping("/index")
	public String index(PageDto page, Model model) {
		// page.pageSize=1;
		/* 加载 大类 */
		TbProductType tbProductType = new TbProductType();
		tbProductType.setParentId(0);
		List<TbProductType> mList = productTypeService
				.selectByTbProductType(tbProductType);
		model.addAttribute("mList", mList);
		/* 查询库存列表 */
		// 未被标记删除的
		Criteria criteria = new Criteria();
		criteria.put("deleted", 0);
		List<TbProductInventoryDto> tbpiList = productInventoryService
				.selectByCriteria(criteria, page);
		model.addAttribute("tbpiList", tbpiList);
		/* 绑定分页参数到前台 */
		model.addAttribute(HglContants.PAGE_DTO_ID, page);
		model.addAttribute("provinceInfos",
				tbProvinceInfoService.selectByObject(null));
		/*加载仓库*/
		Criteria c = new Criteria();
		//查询启用的仓库
		c.put("states", 1);
		List<TbWarehouse> warehouseList = tbWarehouseService.selectByObject(c);
		model.addAttribute("warehouseList", warehouseList);
		return "inventory/inventory2";
	}

	@RequestMapping("/serachInventory")
	public String serachInventory(HttpServletRequest request, PageDto page,
			Model model) {
		// page.pageSize=1;
		Criteria criteria = new Criteria();
		// 未被标记删除的
		criteria.put("deleted", 0);
		String name = request.getParameter("name");
		if (!StringUtil.isEmpty(name)) {
			criteria.put("name", StringUtil.trim(name));
		}
		String mainId = request.getParameter("mainId");
		/* mainId不为空并且不为0并且不为-1 */
		if (!StringUtil.isEmpty(mainId) && !"0".equals(mainId)
				&& !"-1".equals(mainId)) {
			criteria.put("mainId", Integer.parseInt(mainId));
		}
		String brandId = request.getParameter("brandId");
		/* brandId不为空并且不为0并且不为-1 */
		if (!StringUtil.isEmpty(brandId) && !"0".equals(brandId)
				&& !"-1".equals(brandId)) {
			criteria.put("brandId", Integer.parseInt(brandId));
		}
		String productId = request.getParameter("productId");
		/* productId不为空并且不为0并且不为-1 */
		if (!StringUtil.isEmpty(productId) && !"0".equals(productId)
				&& !"-1".equals(productId)) {
			criteria.put("productId", Integer.parseInt(productId));
		}
		String warehouseId = request.getParameter("warehouseId");
		/* warehouseId不为空并且不为0并且不为-1 */
		if (!StringUtil.isEmpty(warehouseId) && !"0".equals(warehouseId)
				&& !"-1".equals(warehouseId)) {
			criteria.put("warehouseId", Integer.parseInt(warehouseId));
		}
		String status = request.getParameter("status");
		/* status不为空并且不为-1 */
		if (!StringUtil.isEmpty(status) && !"-1".equals(status)) {
			criteria.put("status", Integer.parseInt(status));
		}
		List<TbProductInventoryDto> tbpiList = productInventoryService
				.selectByCriteria(criteria, page);
		model.addAttribute(HglContants.PAGE_DTO_ID, page);
		model.addAttribute("tbpiList", tbpiList);
		return "inventory/inventoryList";
	}

	@RequestMapping("/markDelete")
	@ResponseBody
	public Map<String, Object> markDelete(
			@RequestParam(value = "id", required = true) Integer id) {
		Map<String, Object> map = new HashMap<String, Object>();
		// XXX 最后更新时间要从session中取
		String lastupdateby = "admindelete";
		productInventoryService.markDeleteByPrimaryKey(id, lastupdateby);
		return map;
	}

	@RequestMapping("/operationInventory")
	public String operationInventory(
			Model model,
			@RequestParam(required = true, value = "operation") String operation,
			HttpServletRequest request) {
		model.addAttribute("operation", operation);
		String idStr = request.getParameter("id");
		TbProductInventoryDto tbpi = new TbProductInventoryDto();
		if (idStr != null && !"".equals(idStr) && !"0".equals(idStr)) {
			tbpi = productInventoryService.selectById(Integer.parseInt(idStr));
		}
		model.addAttribute("tbpi", tbpi);
		if ("add".equals(operation)) {// 新增
			/* 初始化主分类信息 */
			TbProductType tbProductType = new TbProductType();
			tbProductType.setParentId(0);
			List<TbProductType> mLists = productTypeService
					.selectByTbProductType(tbProductType);
			model.addAttribute("mLists", mLists);
			/* 设置初始状态为下载中 */
			tbpi.setStatus(0);
			tbpi.setUnsaleInventory(0);
			tbpi.setSalesCount(0);
			tbpi.setTotalInventory(0);
		}
		return "inventory/addInventory";
	}

	@RequestMapping("/getInventoryById")
	@ResponseBody
	public Map<String, Object> getInventoryById(Model model,
			@RequestParam(required = true, value = "id") Integer id,
			HttpServletRequest request) {
		TbProductInventoryDto tbpi = productInventoryService.selectById(id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tbpi", tbpi);
		return map;
	}

	@RequestMapping("/saveInventory")
	@ResponseBody
	public Map<String, Object> saveInventory(HttpServletRequest request,
			Model model) {
		TbProductInventory record = new TbProductInventory();
		String productId = request.getParameter("productId");
		String warehouseId = request.getParameter("warehouseId");
		if (warehouseId != null && !"".equals(warehouseId)
				&& !"0".equals(warehouseId)) {
			record.setWarehouseId(Integer.parseInt(warehouseId));
		}
		record.setProductId(Integer.parseInt(productId));
		record.setCode(request.getParameter("code"));
		String name=request.getParameter("name");
		record.setName(name);
		record.setSalesPrice(new BigDecimal(request.getParameter("salesPrice")));
		record.setInstockPrice(new BigDecimal(request
				.getParameter("instockPrice")));
		String outstockPrice = request.getParameter("outstockPrice");
		if (outstockPrice != null && !"".equals(outstockPrice)) {
			record.setOutstockPrice(new BigDecimal(outstockPrice));
		} else {
			record.setOutstockPrice(new BigDecimal(0));
		}
		int saleInventory = Integer.parseInt(request
				.getParameter("saleInventory"));
		record.setSaleInventory(saleInventory);
		record.setOneboxCount(Integer.parseInt(request
				.getParameter("oneboxCount")));
		record.setSpec(request.getParameter("spec"));
		record.setMaterial(request.getParameter("material"));
		String[] attributeValues = request
				.getParameterValues("attributeValues[]");
		String values = "";
		/* 合并属性值到一个字段中 */
		if (attributeValues != null) {
			for (int i = 0; i < attributeValues.length; i++) {
				if (i > 0) {
					values += ";";
				}
				values += attributeValues[i];
			}
		}
		if (!"".equals(values)) {
			record.setAttributesValues(values);
		}
		String id = request.getParameter("id");
		if (id == null || "".equals(id) || "0".equals(id)) {
			/* 新增时设置状态为下架 */
			record.setStatus(0);
			/* 新增时设置版本号为0 */
			record.setVersion(0);
			/* 新增时设置锁定量为0 */
			record.setUnsaleInventory(0);
			/* 新增时 入库数为剩余数 */
			record.setTotalInventory(saleInventory);
			// XXX 通过session获取当前用户
			record.setCreateby("adminadd");
			productInventoryService.insertSelective(record);
		} else {
			TbProductInventory tbpi = productInventoryService
					.selectByPrimaryKey(Integer.parseInt(id));
			record.setId(Integer.parseInt(id));
			record.setVersion(tbpi.getVersion());
			// XXX 通过session获取
			record.setLastupdateby("adminupdate");
			
			record.setStatus(tbpi.getStatus());
			/* 计算入库数并更新 */
			int diff = saleInventory - tbpi.getSaleInventory();
			record.setTotalInventory(tbpi.getTotalInventory() + diff);
			productInventoryService.updateByPrimaryKeySelective(record);
		}
		// XXX 查出该产品的价格和这个库存的价格对比，有变化并且库存价格更低就更新该产品的价格
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("record", record);
		return map;
	}

	@RequestMapping("/updateStatus")
	@ResponseBody
	public Map<String, Object> updateStatus(
			@RequestParam(required = true, value = "id") Integer id,
			@RequestParam(required = true, value = "status") Integer status,
			@RequestParam(required = true, value = "version") Integer version) {
		Map<String, Object> map = new HashMap<String, Object>();
		/*TbProductInventory record = new TbProductInventory();
		record.setId(id);*/
		TbProductInventory tp = productInventoryService.selectByPrimaryKey(id);
		/*tp.setVersion(version);*/
		/* 前台传过来的当前状态 变更成对立状态 */
		String msg = "";
		if (status == 0) {
			status = 1;
			
			TbWarehouse tbWarehouse = tbWarehouseService.selectByPrimaryKey(tp.getWarehouseId());
			if(tbWarehouse.getStates() == 0){
				map.put("success", false);
				map.put("result", "仓库已停用，上架失败！");
				return map;
			}
			BigDecimal outstockPrice = tp.getOutstockPrice();
			/* 上架未填出库价格或者出库价格为0 请要求他填写出库价格 */
			if (outstockPrice == null || outstockPrice.compareTo(new BigDecimal(0)) == 0) {
				map.put("success", false);
				map.put("result", "需上架的库存未填写出库价格,请修改出库价格后再上架,谢谢合作!!!");
				return map;
			}
			msg = "库存上架成功!!!";
		} else {
			status = 0;
			msg = "库存下架成功!!!";
		}
		tp.setStatus(status);
		tp.setVersion(version);
		int result = productInventoryService.updateByPrimaryKeySelective(tp);
		if (result > 0) {
			map.put("success", true);
			map.put("record", tp);
			map.put("result", msg);
		} else {
			map.put("success", false);
			map.put("result", "库存上架/下架失败,可能已被其他同事修改,再刷新数据试试!!!");
		}
		return map;
	}

	/**
	 * 
	 * @param id
	 * @param status
	 * @param version
	 * @return
	 */
	@RequestMapping("/isUniqueInventoryCode")
	@ResponseBody
	public Map<String, Object> isUniqueInventoryCode(
			@RequestParam(required = true, value = "inventoryCode") String inventoryCode) {
		Criteria example = new Criteria();
		example.put("code", inventoryCode);
		List<TbProductInventory> list = productInventoryService
				.selectByObject(example);
		Map<String, Object> map = new HashMap<String, Object>();
		if (list != null && list.size() > 0) {
			map.put("result", false);
		} else {
			map.put("result", true);
		}
		return map;
	}

	@RequestMapping("/getWarehouse")
	@ResponseBody
	public Map<String, Object> getWarehouse(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		Criteria criteria = new Criteria();
		String province = request.getParameter("province");
		if (StringUtil.isNotEmpty(province) && !"0".equals(province)) {
			criteria.put("warehouseProvince", province);
		}
		String city = request.getParameter("city");
		if (StringUtil.isNotEmpty(city) && !"0".equals(city)) {
			criteria.put("warehouseCity", city);
		}
		String country = request.getParameter("country");
		if (StringUtil.isNotEmpty(country) && !"0".equals(country)) {
			criteria.put("warehouseCountry", country);
		}
		//查询启用状态的
		criteria.put("states",1);
		List<TbWarehouse> warehouseList = tbWarehouseService.selectByObject(criteria);
		map.put("warehouseList", warehouseList);
		return map;
	}

	@RequestMapping("/getInventoryByProductId")
	public String getInventoryByProductId(
			Model model,
			@RequestParam(required = true, value = "productId") Integer productId,
			@RequestParam(required = true, value = "warehouseId") Integer warehouseId) {
		Criteria criteria = new Criteria();
		criteria.put("productId", productId);
		criteria.put("warehouseId", warehouseId);
		/* 未被删除的库存 */
		criteria.put("deleted", 0);
		List<TbProductInventoryDto> specList = productInventoryService
				.selectByCriteria(criteria);
		model.addAttribute("specList", specList);
		/* 加载产品的特殊属性 */
		TbProduct tbProduct = tbProductService.selectByPrimaryKey(productId);
		String attributes = tbProduct.getAttributes();
		if (attributes != null && !"".equals(attributes)) {
			String[] attributeList = attributes.split("/");
			model.addAttribute("attributeList", attributeList);
		}
		model.addAttribute("productId", productId);
		model.addAttribute("warehouseId", warehouseId);
		return "inventory/spec";
	}

	@RequestMapping("/batchUpdatePrice")
	@ResponseBody
	public Map<String, Object> batchUpdatePrice(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String inventoryIds = request.getParameter("inventoryIds");
		// XXX
		String user = "admin";
		/* 1为按入库价格 2为按零售标价 */
		String asPrice = request.getParameter("asPrice");
		/* 1为按比例 2为按单价 */
		String priceMethod = request.getParameter("priceMethod");
		String price = request.getParameter("price");
		int count=productInventoryService.batchUpdatePrice(inventoryIds, asPrice,
				priceMethod, price, user);
		map.put("count", count);
		return map;
	}
}
