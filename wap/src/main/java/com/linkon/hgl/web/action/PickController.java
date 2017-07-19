package com.linkon.hgl.web.action;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.dto.TbWapProductDto;
import com.liguo.hgl.proxydao.dto.TbWapProductInventoryDto;
import com.liguo.hgl.proxydao.dto.WapProductType;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbSaveInfo;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.proxydao.util.BaiduMapUtil;
import com.liguo.hgl.proxydao.util.GaoDeMapUtil;
import com.liguo.hgl.service.TbSaveInfoService;
import com.liguo.hgl.service.TbShopInfoService;
import com.liguo.hgl.service.TbWapProductInventoryService;
import com.liguo.hgl.service.TbWapProductService;
import com.liguo.hgl.service.TbWebUserService;
import com.liguo.hgl.util.ImageUtil;

/**
 * 产品选购
 * 
 * */
@Controller
@RequestMapping("/pick")
public class PickController extends IBaseController{

	@Autowired
	protected TbWapProductService tbWapProductService;
	
	@Autowired
	protected TbWapProductInventoryService tbWapProductInventoryService;
	
	@Autowired
	private TbShopInfoService tbShopInfoService;
	
	@Autowired
	private TbSaveInfoService saveInfoService;
	
	@Autowired
	private TbWebUserService tbWebUserService;
	
	/**
	 * 产品列表显示跳转
	 */
	@RequestMapping(value = "/index")
	public String indexPage(PageDto page, HttpServletRequest request,HttpServletResponse response, Model model) {
		init(page,request,response,model);
		return "pick/pick";
	}
	
	/**
	 * 店铺页面跳转
	 * @param cityName 城市名称
	 * @param page page对象
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/restIndex")
	public String restIndex(String cityName ,PageDto page, HttpServletRequest request,HttpServletResponse response, Model model) throws IOException {
	    Map<String, String> mapLatitude = GaoDeMapUtil.getLatitude(cityName);
	    if(mapLatitude != null && mapLatitude.size()>0){
	    	double lon2 = Double.parseDouble(mapLatitude.get("lon"));
	    	double lat2 = Double.parseDouble(mapLatitude.get("lat"));
	    	session.setAttribute(HglContants.LON, String.valueOf(lon2));
	    	session.setAttribute(HglContants.LAT, String.valueOf(lat2));
	    	session.setAttribute(HglContants.ADDRESS_CITY, cityName);
	    }
	    return indexShop(page, request, response, model);
	}
	
	@Override
	protected void init(PageDto page, HttpServletRequest request,HttpServletResponse response, Model model) {
		
	}
	
	/**
	 * 选购页面初始化信息加载
	 * */
	@RequestMapping(value = "/pickList")
	public String indexShop(PageDto page, HttpServletRequest request,HttpServletResponse response, Model model) throws  IOException {
	/*	String lon = (String)session.getAttribute(HglContants.LON);
		String lat = (String)session.getAttribute(HglContants.LAT);
		String city = (String)session.getAttribute(HglContants.ADDRESS_CITY);*/
		/**
		 * 当用户没有登陆时进入首页时，将用户当前的坐标传入session中,如果出现session失效的情况就重定向到首页 
		 * */
		//.判断当前用户有没有得坐标
	/*	if(StringUtils.isBlank((String)session.getAttribute(HglContants.LON)) || StringUtils.isBlank((String)session.getAttribute(HglContants.ADDRESS_CITY))){
			//登陆角色不是师傅
			if(getLoginUser() == null || getLoginUser().getTypeId() != HglContants.USER_TYPE_MASTER){
				return "redirect:/customerIndex";
			}else{
				return "redirect:/";
			}
		}*/
		Criteria c = new Criteria();
		/*c.put("lon", lon);
		c.put("lat", lat);*/
		c.put("address",(String)session.getAttribute(HglContants.ADDRESS_CITY));
		c.put("saveType", HglContants.SHOP_SAVE);
		c.put("userId", this.getLoginUserId());
		/*c.put("shopId", this.getRecommendShopId());*/
		List<TbWapProductDto> pickList = tbWapProductService.selectTbWapPickList(c);
		/*c.put("id", this.getRecommendShopId());*/
		List<WapProductType> productTypeList = tbShopInfoService.selectProductBrand(c);
		model.addAttribute("pickList",pickList);
		model.addAttribute("productTypeList", productTypeList);
		return "pick/pick";
	}
	
	/**
	 * 选购页面条件查询时调用的方法
	 * */
	@RequestMapping(value = "/selectPickList")
	public String selectPickList(PageDto page, HttpServletRequest request,HttpServletResponse response, Model model) throws  IOException {
		/*String lon = (String)session.getAttribute(HglContants.LON);
		String lat = (String)session.getAttribute(HglContants.LAT);
		String city = (String)session.getAttribute(HglContants.ADDRESS_CITY);*/
		/**
		 * 当用户没有登陆时进入首页时，将用户当前的坐标传入session中,如果出现session失效的情况就重定向到首页 
		 * */
		/*if(StringUtils.isBlank((String)session.getAttribute(HglContants.LON)) || StringUtils.isBlank((String)session.getAttribute(HglContants.ADDRESS_CITY))){
			//登陆角色不是师傅
			if(getLoginUser() == null || getLoginUser().getTypeId() != HglContants.USER_TYPE_MASTER){
				return "redirect:/customerIndex";
			}else{
				return "redirect:/";
			}
		}*/
		String orderProduct =(String)request.getParameter("orderProduct"); 
		String productTypeIds =(String)request.getParameter("productTypeIds"); 
		String brandIds =(String)request.getParameter("brandIds"); 
		String searchText =(String)request.getParameter("searchText"); 
		Criteria c = new Criteria();
		/*c.put("lon", lon);
		c.put("lat", lat);
		c.put("address", city);*/
		c.put("orderProduct", orderProduct);
		if(!StringUtils.isBlank(productTypeIds)){
			c.put("productTypeIds",Arrays.asList(productTypeIds.split(",")));
		}
		if(!StringUtils.isBlank(brandIds)){
			c.put("brandIds",Arrays.asList(brandIds.split(",")));
		}
		if(!StringUtils.isBlank(searchText)){
			c.put("searchText", searchText.trim());
		}
		/*c.put("shopId", this.getRecommendShopId());
		c.put("id", this.getRecommendShopId());*/
		c.put("address",(String)session.getAttribute(HglContants.ADDRESS_CITY));
		c.put("saveType", HglContants.SHOP_SAVE);
		c.put("userId", this.getLoginUserId());
		List<TbWapProductDto> pickList = tbWapProductService.selectTbWapPickList(c);
		List<WapProductType> productTypeList = tbShopInfoService.selectProductBrand(c);
		model.addAttribute("pickList",pickList);
		model.addAttribute("productTypeList", productTypeList);
		return "pick/pickList";
	}
	
	/**
	 * 产品展示
	 * @param id 产品id
	 * @param distance 距离
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/pickDetail")
	public String index(Integer id,String distance,Model model)throws Exception {
		if(id == null){
			return "redirect:/"; 
		}
		//获取产品和店铺信息，在计算距离
		TbWapProductDto dto = tbWapProductService.selectWapProductDtoByPrimaryKey(id);
		double lon1 = dto.getLon() == null ? 0.00 : Double.parseDouble(dto.getLon());
		double lat1 = dto.getLat() == null ? 0.00 : Double.parseDouble(dto.getLat());
		double lon = this.getLon() == null ? 0.00 : Double.parseDouble(this.getLon());
		double lat = this.getLat() == null ? 0.00 : Double.parseDouble(this.getLat());
		
		Double shopDistance = BaiduMapUtil.GetShortDistance(lon, lat, lon1, lat1);
		dto.setShopDistance(shopDistance == null ? "0" : String.valueOf(shopDistance));
		model.addAttribute("dto", dto);
		model.addAttribute("shopUserId", tbWebUserService.selectWebUserByShopId(dto.getShopId()).getId());
		
		//判断用户是否登录
		Criteria criteria = new Criteria();
		criteria.put("productId", id);
		criteria.put("deleted", 0);
		criteria.put("status", 1);
		if(this.getLoginUserId() == null){
			model.addAttribute("isLogin", 0);
		}else{  
			criteria.put("userId", this.getLoginUserId());
		}
		List<TbWapProductInventoryDto> inventoryDtoList = tbWapProductInventoryService.selectByCriteria(criteria);
		model.addAttribute("inventoryDtoList", inventoryDtoList);
		//model.addAttribute("distance", distance);
		
		/*判断产品是否已经被用户收藏*/
		TbSaveInfo saveInfo = saveInfoService.selectSaveInfo(id, HglContants.GOOD_SAVE, this.getLoginUser().getId());
		model.addAttribute("saveInfo",saveInfo);
		return "pick/pickDetail";
	}
	
	/**
	 * 用于没有登录跳转到产品详细
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doPickDetail")
	public String doPickDetail(Model model)throws Exception {
		String productId = (String)session.getAttribute("productId");
		if(productId == null){
			return "redirect:/";
		}
		String distance = (String)session.getAttribute("distance");     
		session.removeAttribute("productId");  //销毁产品Id
		session.removeAttribute("distance");   //销毁产品距离
		index(Integer.parseInt(productId),distance,model);
		return "pick/pickDetail";
	}

	@Override
	public String doSearchResult() {
		return null;
	}
	
    /**
     * 生成显示图片
     * @param model
     * @param id
     * @param imgName
     * @param response
     * @return
     */
    @RequestMapping("/generateImage")
    public String generateImage(ModelMap model,Integer id,String imgName,HttpServletResponse response) {
    	ImageUtil.showImageWapProduct(id,imgName,response);
        return null;
    }
    
    /*添加和取消收藏*/
	@RequestMapping("/doOperateProductSaveInfo")
	@ResponseBody
	public String doOperateProductSaveInfo(Integer id,Integer typeId){
		Integer createdBy = this.getLoginUser().getId();	
		if(createdBy==null){
			return "redirect:/";
		}else{
			TbSaveInfo saveInfo = saveInfoService.selectSaveInfo(id,HglContants.GOOD_SAVE,createdBy);
			if(typeId==1){     //typeId:1 表示添加到收藏
				TbWapProductDto wapProductDto = tbWapProductService.selectWapProductDtoByPrimaryKey(id);
				if(saveInfo==null){
					if(saveInfoService.insertWapProduct(wapProductDto,createdBy)==1){
						return String.valueOf(1);  //收藏成功
					}else{
						return String.valueOf(-1);  //收藏失败
					}
				}else{                    
					return String.valueOf(0); //您已收藏该产品
				}
			}else if(typeId==0){ //typeId:0表示取消收藏 			
				if(saveInfo!=null){		//判断产品是否已经被收藏
					if(saveInfoService.deleteByPrimaryKey(saveInfo.getId())==1){
						return String.valueOf(2); //取消收藏成功
					}else{
						return String.valueOf(-2); //取消收藏失败
					}
				}	
			}
		}	
		return String.valueOf(false); //添加取消收藏失败
	}
}
