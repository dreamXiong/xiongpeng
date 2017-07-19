package com.linkon.hgl.web.action;

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

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.dto.BaiduMapDto;
import com.liguo.hgl.proxydao.model.TbNoticeInfo;
import com.liguo.hgl.proxydao.model.TbShopInfo;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.proxydao.util.GaoDeMapUtil;
import com.liguo.hgl.service.IProductTypeService;
import com.liguo.hgl.service.TbBrandService;
import com.liguo.hgl.service.TbMerchantsService;
import com.liguo.hgl.service.TbNoticeInfoService;
import com.liguo.hgl.service.TbShopInfoService;
import com.liguo.hgl.util.ImageUtil;

@Controller
public class IndexController extends IBaseController {

	@Autowired
	private IProductTypeService productTypeService;
	
	@Autowired
	private TbBrandService tbBrandService;
	
	@Autowired
	private TbMerchantsService tbMerchantService;

	@Autowired
	private TbNoticeInfoService noticeInfoService;
	
	@Autowired
	private TbShopInfoService tbShopInfoService;
	
	/**
	 * 页面初始信息展示
	 * */
	@RequestMapping(value = "/")
	public String indexPage(PageDto page, HttpServletRequest request,HttpServletResponse response, Model model) {
		
	/*	if(getLoginUser() == null || getLoginUser().getTypeId() != HglContants.USER_TYPE_MASTER){
			return "redirect:/customerIndex";
		}
		List<TbNoticeInfo> tbNoticeInfolist = noticeInfoService.showNewestNoticeInfo(0,5);
		model.addAttribute("tbNoticeInfolist", tbNoticeInfolist);
		return "index/blank";*/
		return "redirect:shop/userShop"; 
	}
	
	/**
	 * 页面初始完信息后查询店铺列表信息
	 * */
	@RequestMapping(value = "/ajaxIndexShopInfo")
	public String ajaxIndexShopInfo(PageDto page, HttpServletRequest request,HttpServletResponse response, Model model) throws Exception {
		
		double lon = 114.126323;
		double lat = 22.537628;
		
		lon = Double.parseDouble(request.getParameter("lon"));
		lat = Double.parseDouble(request.getParameter("lat"));
		
		session.setAttribute(HglContants.LON, lon+"");
		session.setAttribute(HglContants.LAT, lat+"");
		
		BaiduMapDto d=	GaoDeMapUtil.selectAddBycoordinate(lon,lat);
		String address = "";
		if(StringUtils.isBlank(d.getRegeocode().getAddressComponent().getCity())){
			address = "定位失败";
		}else{
			address = d.getRegeocode().getAddressComponent().getCity();
			logger.debug("定位客户的地址："+address);
			session.setAttribute(HglContants.ADDRESS_CITY, address);
		}
		
		logger.debug("lat="+lat+","+lon);
		List<TbShopInfo> shopInfoList= tbShopInfoService.selectTbShopInfoForWap(lon,lat,address);
		model.addAttribute("shopInfoList", shopInfoList);
		return "index/shopInfoList";
	}
	
	@Override
	protected void init(PageDto page, HttpServletRequest request,HttpServletResponse response, Model model) {
	}
	@RequestMapping(value = "/indexShopInfo")
	public String indexShopInfo(PageDto page, HttpServletRequest request,HttpServletResponse response, Model model) {
		return "redirect:shop/userShop"; 
		/*double lon = Double.parseDouble((String)session.getAttribute(HglContants.LON));
		double lat = Double.parseDouble((String)session.getAttribute(HglContants.LAT)); 
		String city = (String)session.getAttribute(HglContants.ADDRESS_CITY);
		if(StringUtils.isBlank((String)session.getAttribute(HglContants.LON)) || StringUtils.isBlank(city)){
			//登陆角色不是师傅
			if(getLoginUser() == null || getLoginUser().getTypeId() != HglContants.USER_TYPE_MASTER){
				return "redirect:/customerIndex";
			}else{
				return "redirect:/";
			}
		}
		List<TbNoticeInfo> tbNoticeInfolist = noticeInfoService.showNewestNoticeInfo(0,5);
		List<TbShopInfo> shopInfoList = tbShopInfoService.selectTbShopInfoForWap(lon,lat,city);
		
		model.addAttribute("shopInfoList", shopInfoList);
		model.addAttribute("tbNoticeInfolist", tbNoticeInfolist);
		return "index/index";*/
	}
	
	/*private List<TbShopInfo> selectShopInfoList(HttpServletRequest request,HttpServletResponse response){
		double lon = 114.126323;
		double lat = 22.537628;
		HttpSession session = request.getSession();
		if(!StringUtils.isBlank(request.getParameter("lon")) && !StringUtils.isBlank(request.getParameter("lat"))){
			lon = Double.parseDouble(request.getParameter("lon"));
			lat = Double.parseDouble(request.getParameter("lat"));
			session.setAttribute(HglContants.LON, lon+"");
			session.setAttribute(HglContants.LAT, lat+"");
		}else if(!StringUtils.isBlank((String)session.getAttribute(HglContants.LON)) && !StringUtils.isBlank((String)session.getAttribute(HglContants.LON))){
			lon = Double.parseDouble((String)session.getAttribute(HglContants.LON));
			lat = Double.parseDouble((String)session.getAttribute(HglContants.LAT));
		}
		logger.debug("lat="+lat+","+lon);
		List<TbShopInfo> shopInfoList= tbShopInfoService.selectTbShopInfoForWap(lon,lat);
		return shopInfoList;
	}*/
	

	 @RequestMapping("/generateImage")
    public String generateImage(ModelMap model,Integer id,String imgName,HttpServletResponse response) {
    	ImageUtil.showImageShop(id,imgName,response);
        return null;
    }
	@Override
	public String doSearchResult() {
		// TODO Auto-generated method stub
		return null;
	}
	 @RequestMapping("/generateProductImage")
    public String generateProductImage(ModelMap model,Integer id,String imgName,HttpServletResponse response) {
    	ImageUtil.showImageWapProduct(id,imgName,response);
        return null;
    }
	 @RequestMapping(value = "/indexShop")
	public String indexShop(PageDto page, HttpServletRequest request,HttpServletResponse response, Model model) {
		double lon = Double.parseDouble((String)session.getAttribute(HglContants.LON));
		double lat = Double.parseDouble((String)session.getAttribute(HglContants.LAT));
		String city = (String)session.getAttribute(HglContants.ADDRESS_CITY);
		if(StringUtils.isBlank((String)session.getAttribute(HglContants.LON)) || StringUtils.isBlank(city)){
			//登陆角色不是师傅
			if(getLoginUser() == null || getLoginUser().getTypeId() != HglContants.USER_TYPE_MASTER){
				return "redirect:/customerIndex";
			}else{
				return "redirect:/";
			}
		}
		List<TbNoticeInfo> tbNoticeInfolist = noticeInfoService.showNewestNoticeInfo(0,5);
		List<TbShopInfo> shopInfoList= tbShopInfoService.selectTbShopInfoForWap(lon,lat,city);
		model.addAttribute("shopInfoList", shopInfoList);
		model.addAttribute("tbNoticeInfolist", tbNoticeInfolist);
		model.addAttribute("lon", lon);
		return "index/index";
	}
	 
	 @RequestMapping("/getNoticeInfoDetail")
	public String getNoticeInfoDetail(Integer id,Model model) {
		TbNoticeInfo noticeInfo = noticeInfoService.selectByPrimaryKey(id);
		model.addAttribute("noticeInfo", noticeInfo);
		return "index/noticeDetail";
	}
}
