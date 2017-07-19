package com.linkon.hgl.web.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.dto.ActivityInfoImageDto;
import com.liguo.hgl.proxydao.dto.BaiduMapDto;
import com.liguo.hgl.proxydao.dto.ShopIndexForWapDto;
import com.liguo.hgl.proxydao.dto.TbWapProductDto;
import com.liguo.hgl.proxydao.dto.WapProductType;
import com.liguo.hgl.proxydao.dto.WebUserDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbActivityInfo;
import com.liguo.hgl.proxydao.model.TbShopInfo;
import com.liguo.hgl.proxydao.model.TbWebUser;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.proxydao.util.GaoDeMapUtil;
import com.liguo.hgl.service.TbActivityInfoService;
import com.liguo.hgl.service.TbIntegralRulesService;
import com.liguo.hgl.service.TbSaveInfoService;
import com.liguo.hgl.service.TbShopInfoService;
import com.liguo.hgl.service.TbWebUserService;
import com.liguo.hgl.util.ImageUtil;

@Controller
@RequestMapping("/shop")
public class ShopController extends IBaseController {
	
	@Autowired
	private TbShopInfoService tbShopInfoService;
	
	@Autowired
	private TbSaveInfoService saveInfoService;
	
	@Autowired
	private TbActivityInfoService activityInfoService;
	
	@Autowired
	private TbIntegralRulesService tbIntegralRulesService;
	
	@Override
	protected void init(PageDto page, HttpServletRequest request,HttpServletResponse response, Model model) {
		
		/*Integer shopId = Integer.parseInt(request.getParameter("shopId"));*/ 
		String shopIdStr = request.getParameter("shopId");
		Integer shopId;
		if(shopIdStr == null){
			shopId = this.getRecommendShopId();
		}else{
			shopId = Integer.parseInt(shopIdStr);
		}
		Double distance = Double.parseDouble(request.getParameter("distance")); 
		Criteria c = new Criteria();
		c.put("id",shopId);
		List<WapProductType> productTypeList = tbShopInfoService.selectProductBrand(c);
		//查询店铺信息
		ShopIndexForWapDto t = tbShopInfoService.selectShopIndexInfo(c);
		//查询该店铺下所有产品 List<TbWapProductDto>  wapProductDtoList
		List<TbWapProductDto> wapProductDtoList = tbShopInfoService.selectTbWapProductDtoList(c);
		model.addAttribute("t", t);
		model.addAttribute("blance", distance);
		model.addAttribute("id", shopId);
		model.addAttribute("productTypeList", productTypeList);
		model.addAttribute("wapProductDtoList", wapProductDtoList);
		
		/*查询当前店铺的活动*/
		c = new Criteria();
		c.put("shopId", shopId);
		c.put("status", HglContants.STATUSYES);
		c.setOrderByClause("id desc");
		List<TbActivityInfo> activityInfoList = activityInfoService.selectByObject(c);
		List<ActivityInfoImageDto> imageList = new ArrayList<ActivityInfoImageDto>();
		for(TbActivityInfo activityInfo:activityInfoList){
			List<String> strList = Arrays.asList(activityInfo.getImage().split(","));
			for(String strImage:strList){
				ActivityInfoImageDto imageDto = new ActivityInfoImageDto();
				imageDto.setId(activityInfo.getId());
				imageDto.setImage(strImage);
				imageList.add(imageDto);
			}
		}
		model.addAttribute("imageList", imageList);
		
		/*绑定或收藏店铺*/
		WebUserDto webUser = this.getLoginUser();
		if(webUser!=null && webUser.getId()!=null &&  HglContants.HGL_DEFAULT_SHOP!=shopId){
			Integer doBindShopInfo = saveInfoService.doBindShopInfo(null,shopId, this.getLoginUser());
			if(doBindShopInfo!=null){
				webUser.setRecommendShopId(doBindShopInfo);
				session.setAttribute(HglContants.SESSION_KEY, webUser);
			}
		}
	}
	
	@RequestMapping(value = "/productInfo")
	protected String doSearchResult(HttpServletRequest request,HttpServletResponse response, Model model) {
		Integer shopId = this.getRecommendShopId(); 
		Double distance = Double.parseDouble(request.getParameter("distance")); 
		String orderProduct =(String)request.getParameter("orderProduct"); 
		String productTypeIds =(String)request.getParameter("productTypeIds"); 
		String brandIds =(String)request.getParameter("brandIds"); 
		Criteria c = new Criteria();
		c.put("id",shopId);
		c.put("orderProduct", orderProduct);
		if(!StringUtils.isBlank(productTypeIds)){
			c.put("productTypeIds",Arrays.asList(productTypeIds.split(",")));
		}
		if(!StringUtils.isBlank(brandIds)){
			c.put("brandIds",Arrays.asList(brandIds.split(",")));
		}
		/*c.put("brandIds", brandIds);*/
		//查询该店铺下所有产品 
		List<TbWapProductDto> wapProductDtoList = tbShopInfoService.selectTbWapProductDtoList(c);
		model.addAttribute("wapProductDtoList", wapProductDtoList);
		model.addAttribute("distance", distance);
		return "shop/productList";
	}
	@RequestMapping(value = "/productTypeAndBrand")
	public String productTypeAndBrand(HttpServletRequest request,HttpServletResponse response, Model model){
		Integer shopId = Integer.parseInt(request.getParameter("id")); 
		Criteria c = new Criteria();
		c.put("id",shopId);
		List<WapProductType> productTypeList = tbShopInfoService.selectProductBrand(c);
		model.addAttribute("productTypeList", productTypeList);
		return "shop/productTypeList";
	}
	@Override
	public String doSearchResult() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*添加或删除店铺收藏*/
	@RequestMapping("/doOperateShopSaveInfo")
	@ResponseBody
	public String doOperateShopSaveInfo(Integer id,Integer typeId){
		return saveInfoService.doOperateShopSaveInfo(id, typeId, this.getLoginUserId());
	}
	
    @RequestMapping("/getAddress")
    @ResponseBody
	public Map<String,Object> getAddress() throws Exception{
		double lon = Double.parseDouble((String)session.getAttribute(HglContants.LON));
		double lat = Double.parseDouble((String)session.getAttribute(HglContants.LAT)); 
		String address = GaoDeMapUtil.selectAddBycoordinate(lon,lat).getRegeocode().getFormatted_address();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("address", address);
		return map;
	}
    
    /**
     * 用户绑定店铺后的首页
     * */
    @RequestMapping(value = "/userShop")
	protected String userShop(Model model,@RequestParam(required=false)Integer sId,String recommendCode) {
    	logger.debug(">>>>>>>>>>>sId"+sId);
		//判断推荐码是否为空,不为空保存到session中
		if(StringUtils.isNotBlank(recommendCode)){
			this.session.setAttribute("recommendCode", recommendCode);
		}
    	Integer shopId =null;
    	logger.debug(">>>>>>>>>>>sId"+sId +">>>>>>>>>>>this.getRecommendShopId()"+this.getRecommendShopId());
    	if(sId != null && sId > 0){
    		shopId=sId;
    	}else{
		 shopId = this.getRecommendShopId(); 
    	}
		if(shopId == null){
			shopId=HglContants.HGL_DEFAULT_SHOP;
		}
		Criteria c = new Criteria();
		c.put("id",shopId);
		c.put("orderProduct", "saleNum_desc");
		c.put("userShop", "userShop");
		//查询该店铺下所有产品 
		List<TbWapProductDto> wapProductDtoList = tbShopInfoService.selectTbWapProductDtoList(c);
		model.addAttribute("wapProductDtoList", wapProductDtoList);
		ShopIndexForWapDto t = tbShopInfoService.selectShopIndexInfo(c);
		model.addAttribute("t", t);
		
		/*查询当前店铺的活动*/
		c = new Criteria();
		c.put("shopId", shopId);
		c.put("status", HglContants.STATUSYES);
		c.setOrderByClause("id desc");
		List<TbActivityInfo> activityInfoList = activityInfoService.selectByObject(c);
		List<ActivityInfoImageDto> imageList = new ArrayList<ActivityInfoImageDto>();
		for(TbActivityInfo activityInfo:activityInfoList){
			ActivityInfoImageDto imageDto = new ActivityInfoImageDto();
			imageDto.setId(activityInfo.getId());
			imageDto.setImage(activityInfo.getTitleImage());
			imageList.add(imageDto);
		}
		model.addAttribute("imageList", imageList);
		
		if(getRecommendShopId() != null){
			model.addAttribute("integralRules", tbIntegralRulesService.selectUsedRule(getRecommendShopId()));
		}
		/*绑定或收藏店铺*/
		WebUserDto webUser = this.getLoginUser();
		//存在登录用户，并且当前访问的店铺不为惠给利用户
		logger.debug(">>>>>>>>>>>shopId:"+shopId);
		if(webUser!=null && webUser.getId()!=null &&  HglContants.HGL_DEFAULT_SHOP!=shopId){
			Integer doBindShopInfo = saveInfoService.doBindShopInfo(recommendCode,shopId, this.getLoginUser());
			logger.debug(">>>>>>>>>>>shopId:"+doBindShopInfo);
			if(doBindShopInfo!=null){
				logger.debug(">>>>>>>>>>>>>>>>>插入推荐信息");
				webUser.setRecommendShopId(doBindShopInfo);
				session.setAttribute(HglContants.SESSION_KEY, webUser);
			}
		}		
		return "shop/shop";
	}
    
    /**
     * 用户绑定店铺后的首页
     * */
    @RequestMapping(value = "/getAddressInfo")
    @ResponseBody
	protected Map<String,Object> getAddressInfo(Model model,String shopLat,String shopLon,String userLat,String userLon) throws Exception {
    	double lon = Double.parseDouble(shopLon);
    	double lat = Double.parseDouble(shopLat);
    	session.setAttribute(HglContants.LON, userLon);
		session.setAttribute(HglContants.LAT, userLat);
		Integer shopId = getRecommendShopId();
		if(shopId == null){
			shopId = 1;
			TbShopInfo t = tbShopInfoService.selectByPrimaryKey(shopId);
			lon = t.getLon();
			lat = t.getLat();
		}
    	Map<String,Object> map = new HashMap<String,Object>();
    	BaiduMapDto d=	GaoDeMapUtil.selectAddBycoordinate(Double.parseDouble(userLon),Double.parseDouble(userLat));
		String address = "";
		address = d.getRegeocode().getAddressComponent().getCity();
		logger.debug("定位客户的地址："+address);
		session.setAttribute(HglContants.ADDRESS_CITY, address);
		map.put("address", address);
		double blance = GaoDeMapUtil.GetShortDistance(Double.parseDouble(userLon),Double.parseDouble(userLat),lon,lat);
		map.put("blance", blance);
		logger.debug("》》》userLat:"+userLat+"》》》》》》》》》》定位客户的地址："+address+"距离blance》》》》》："+blance);
		session.setAttribute(HglContants.SHOP_BLANCE, blance);
		return map;
	}
    
    /*显示活动图片*/
    @RequestMapping("/showActivityImage")
    public String showActivityImage(Integer id,String imgName,HttpServletResponse response){
    	ImageUtil.showActivityImage(id, imgName, response);
    	return null;
    }
    
    @RequestMapping("/goShopAddress")
    public String goShopAddress(String shopLon,String shopLat,HttpServletRequest request,Model model){
    	model.addAttribute("shopLon", shopLon);
    	model.addAttribute("shopLat", shopLat);
    	
    	String userLat =(String)request.getParameter("userLat"); 
    	String userLon =(String)request.getParameter("userLon"); 
    	if(userLat != null && userLon != null){
    		model.addAttribute("shop_lat", userLat);
        	model.addAttribute("shop_lon", userLon);
    	}
    	return "shop/goShopAddress";
    }
}
