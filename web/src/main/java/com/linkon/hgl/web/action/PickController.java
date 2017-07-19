package com.linkon.hgl.web.action;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.dto.TbProductDto;
import com.liguo.hgl.proxydao.dto.TbProductInventoryDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbBrand;
import com.liguo.hgl.proxydao.model.TbMerchants;
import com.liguo.hgl.proxydao.model.TbProduct;
import com.liguo.hgl.proxydao.model.TbProductType;
import com.liguo.hgl.proxydao.model.TbSaveInfo;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.IProductTypeService;
import com.liguo.hgl.service.TbAgencyService;
import com.liguo.hgl.service.TbBrandService;
import com.liguo.hgl.service.TbMerchantsService;
import com.liguo.hgl.service.TbProductInventoryService;
import com.liguo.hgl.service.TbProductService;
import com.liguo.hgl.service.TbSaveInfoService;
import com.liguo.hgl.service.TbShoppingCartService;

@Controller
@RequestMapping("/pick")
public class PickController extends IBaseController{

	private static final String SAVESUCCESS = "1";  //收藏成功
	private static final String DLTSUCCESS = "2";   //取消收藏成功
	private static final String SAVEERROR = "-1";   //收藏失败
	private static final String DLTERROR = "-2";    //取消收藏失败
	
	@Autowired
	@Qualifier("productTypeService")
	protected IProductTypeService productTypeService;

	@Autowired
	protected TbBrandService tbBrandService;

	@Autowired
	protected TbProductService tbProductService;
	
	@Autowired
	protected TbProductInventoryService tbProductInventoryService;
	
	@Autowired
	private TbSaveInfoService saveInfoService;

	 @Autowired
	private TbAgencyService agencyService;
    @Autowired
    private TbShoppingCartService cartService;
    @Autowired
	private TbMerchantsService iMerchantsService;
	
	@Override
	protected void init(PageDto page, HttpServletRequest request,
			HttpServletResponse response, Model model) {

		// 查询大分类
		TbProductType tbProductType = new TbProductType();
		tbProductType.setParentId(0);
		List<TbProductType> pList = productTypeService
				.selectByTbProductType(tbProductType);
		if (pList.size() > 0) {
			/* 初始化二级分类 */
			Integer mainId = pList.get(0).getId();
			TbProductType secondProductType = new TbProductType();
			secondProductType.setParentId(mainId);
			List<TbProductType> secondList = productTypeService
					.selectByTbProductType(secondProductType);
			model.addAttribute("secondList", secondList);
		}
		/* 查询所有品牌 */
		Criteria example = new Criteria();
		/*只取出库存已上架的产品*/
		example.put("status", 1);
		/*必须要有库存数据*/
		example.put("hasInventory", true);

		List<TbBrand> tbBrandList = tbBrandService.selectByObject(example);
		model.addAttribute("tbBrandList", tbBrandList);
		model.addAttribute("pList", pList);
		// 查询所有产品数
		//收藏人
		example.put("createBy", this.getLoginUser().getUserName());
		List<TbProductDto> tbProductList = tbProductService.selectByCriteria(
				example, page);
		model.addAttribute("tbProductList", tbProductList);

		model.addAttribute(HglContants.PAGE_DTO_ID, page);
		
		model.addAttribute("st", HglContants.GOODS_ORDER); //商品类型,现货购买
		/* 产品默认显示列表 */			
		model.addAttribute("cursor", 0);
	}
	/*@RequestMapping("/index")
	public String index(PageDto page, Model model) {}
*/
	/**
	 * 查询数据列表
	 * 
	 * @param page
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/serachProduct")
	public String searchResult(PageDto page, HttpServletRequest request,
			HttpServletResponse response, Model model) throws IOException {
		Criteria criteria = new Criteria();
		/*只取出库存已上架的产品*/
		criteria.put("status", 1);
		/*必须要有库存数据*/
		criteria.put("hasInventory", true);
		
		String firstTypeId = request.getParameter("firstTypeId");
		if (firstTypeId != null && !"".equals(firstTypeId)) {
			criteria.put("firstType", Integer.parseInt(firstTypeId));
		}
		String secondTypeId = request.getParameter("secondTypeId");
		if (secondTypeId != null && !"".equals(secondTypeId)) {
			criteria.put("secondType", Integer.parseInt(secondTypeId));
		}
		String thirdTypeId = request.getParameter("thirdTypeId");
		if (thirdTypeId != null && !"".equals(thirdTypeId)) {
			criteria.put("thirdType", Integer.parseInt(thirdTypeId));
		}
		String brandIds = request.getParameter("brandIds");
		if (brandIds != null && !"".equals(brandIds)) {
			criteria.put("brandIds", brandIds.split(","));
		}
		String minprice = request.getParameter("minprice");
		if (minprice != null && !"".equals(minprice)) {
			criteria.put("minprice", new BigDecimal(minprice));
		}
		String maxprice = request.getParameter("maxprice");
		if (maxprice != null && !"".equals(maxprice)) {
			criteria.put("maxprice", new BigDecimal(maxprice));
		}
		String orderby = request.getParameter("orderby");
		if (orderby != null && !"".equals(orderby)) {
			criteria.setOrderByClause(orderby);
			String ordersort = request.getParameter("ordersort");
			if (ordersort != null && !"".equals(ordersort)) {
				criteria.setOrderByClauseDesc(ordersort);
			}
			model.addAttribute("orderby", orderby);
			model.addAttribute("ordersort", ordersort);
		}
		
		criteria.put("createBy", this.getLoginUser().getUserName()); //查看收藏
		List<TbProductDto> tbProductList = tbProductService.selectByCriteria(
				criteria, page);
		model.addAttribute(HglContants.PAGE_DTO_ID, page);
		model.addAttribute("tbProductList", tbProductList);
		model.addAttribute("cursor",
				Integer.parseInt(request.getParameter("cursor")));
		model.addAttribute("minprice", minprice);
		model.addAttribute("maxprice", maxprice);
		if(request.getParameter("shoppingType") != null){
			model.addAttribute("st", request.getParameter("shoppingType")); //商品类型,现货购买
		}
		// 获取弹幕消息					
		return "/pick/productList";
	}

	@RequestMapping("/linkageAllPage")
	@ResponseBody
	public Map<String, Object> linkageAllPage(@RequestParam Integer id)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		TbProductType tbProductType = new TbProductType();
		tbProductType.setParentId(0);
		List<TbProductType> pList = productTypeService
				.selectByTbProductType(tbProductType);
		if (pList.size() > 0) {
			map = linkageMainPage(pList.get(0).getId());
		}
		/* 查询所有品牌 */
		Criteria example = new Criteria();
		List<TbBrand> tbBrandList = tbBrandService.selectByObject(example);
		map.put("tbBrandList", tbBrandList);
		return map;
	}

	/*
	 * 主页面联动显示
	 */
	@RequestMapping("/linkageMainPage")
	@ResponseBody
	public Map<String, Object> linkageMainPage(@RequestParam Integer id)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		/* 初始化二级分类 */
		TbProductType secondProductType = new TbProductType();
		secondProductType.setParentId(id);
		List<TbProductType> secondList = productTypeService
				.selectByTbProductType(secondProductType);
		map.put("secondList", secondList);

		/* 查询所有品牌 */
		Criteria example = new Criteria();
		example.put("producttypeId", id);
		List<TbBrand> tbBrandList = tbBrandService.selectByObject(example);
		map.put("tbBrandList", tbBrandList);
		return map;
	}

	/*
	 * 主页面二级分类联动显示
	 */
	@RequestMapping("/linkageMainPageSecond")
	@ResponseBody
	public Map<String, Object> linkageMainPageSecond(@RequestParam Integer id)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		TbProductType thirdProductType = new TbProductType();
		thirdProductType.setMainId(productTypeService.selectByPrimaryKey(id)
				.getParentId());
		thirdProductType.setParentId(id);
		List<TbProductType> thirdList = productTypeService
				.selectByTbProductType(thirdProductType);
		map.put("thirdList", thirdList);
		return map;
	}

	@RequestMapping("/pickDetail")
	public String index(@RequestParam(value = "id", required = true) Integer id,@RequestParam String st,@RequestParam(required=false)Integer mid, Model model)throws Exception {
		TbProductDto dto=tbProductService.selectProductDtoByPrimaryKey(id);
		model.addAttribute("dto", dto);
		Criteria criteria=new Criteria();
		criteria.put("productId", id);
		criteria.put("deleted", 0);
		criteria.put("status", 1);
		double discount = this.getUserSale();
		criteria.put("discount", discount);
		Integer userId = this.getLoginUserId();
		criteria.put("userId", userId);
		boolean shoppingType = HglContants.MERCHANT_ORDER.equals(Integer.parseInt(st)) ? true : false; //招商为true,订货单为false
		//criteria.put("shoppingType", shoppingType);
		List<TbProductInventoryDto> inventoryDtoList=tbProductInventoryService.selectByCriteria(criteria);
		model.addAttribute("inventoryDtoList", inventoryDtoList);
		/*加载产品属性*/
		Map<String,Object> map =linkProductForAttribute(id);
		Object attributeList=map.get("attributeList");
		if(attributeList!=null){
			model.addAttribute("attributeList", attributeList);
		}
		if(mid!=null&& mid >0 && getShopId()!=null&&shoppingType){
			TbMerchants merchants =iMerchantsService.selectById(mid);
			model.addAttribute("merchant",  iMerchantsService.selectById(mid));
			model.addAttribute("isBuyService", iMerchantsService.isMerchantService(merchants.getId(),getShopId()));
			model.addAttribute("shopCartMoney", cartService.getShopCartMoneyByBid(getLoginUserId(),merchants.getBrandId()));
			
		}
		model.addAttribute("st", st); //记录是否招商或现货购买
		return "pick/pickDetail";
	}
	
	/**
	 * 招商详情页加入进货单局部刷新
	 * @param mid
	 * @param st  
	 * @return
	 * @author zss
	 */
	@RequestMapping("/pickMerchants")
    public String pickMerchants(@RequestParam Integer mid,@RequestParam String st, Model model){
		boolean shoppingType = HglContants.MERCHANT_ORDER.equals(Integer.parseInt(st)) ? true : false; //招商为true,订货单为false
    	if(shoppingType){
    		TbMerchants merchants =iMerchantsService.selectById(mid);
			model.addAttribute("merchant", merchants);
			model.addAttribute("isBuyService", iMerchantsService.isMerchantService(merchants.getId(),getShopId()));
			model.addAttribute("shopCartMoney", cartService.getShopCartMoneyByBid(getLoginUserId(),merchants.getBrandId()));
    	}
    	return "pick/pickMerchants";
    }
	

	@RequestMapping("/linkProductForAttribute")
    @ResponseBody
    public Map<String,Object> linkProductForAttribute(@RequestParam Integer id) throws Exception{
    	Map<String,Object> map = new HashMap<String,Object>();
    	TbProduct tbProduct=tbProductService.selectByPrimaryKey(id);
    	String attributes=tbProduct.getAttributes();
    	if(attributes!=null && !"".equals(attributes)){
    		String[]attributeList=attributes.split("/");
    		map.put("attributeList", attributeList);
    	}
    	return map;
    }
	
	/*操作收藏信息 */
	@RequestMapping("/doOperateSaveInfo")
	@ResponseBody
	public String doOperateSaveInfo(Integer id,Integer typeId){
		Integer createdBy = this.getLoginUser().getId();
		TbSaveInfo saveInfo = saveInfoService.selectSaveInfo(id, HglContants.GOOD_SAVE, createdBy);
		if(typeId==1){     //typeId:1 表示添加到收藏
			TbProductDto productDto = tbProductService.selectProductDtoByPrimaryKey(id);
			if(saveInfo==null){
				if(saveInfoService.insertProductSelective(productDto,createdBy)==1){
					return SAVESUCCESS;  //收藏成功
				}else{
					return SAVEERROR; //收藏失败
				}
			}	
		}else if(typeId==0){ //typeId:0表示取消收藏 			
			if(saveInfo!=null){		//判断产品是否已经被收藏
				if(saveInfoService.deleteByPrimaryKey(saveInfo.getId())==1){
					return DLTSUCCESS; //取消收藏成功
				}else{
					return DLTERROR; //取消收藏失败
				}
			}	
		}
		
		return String.valueOf(false);
	}
	
	@Override
	public String doSearchResult() {
		// TODO Auto-generated method stub
		return null;
	}
}
