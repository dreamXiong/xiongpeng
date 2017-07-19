package com.linkon.hgl.web.action;

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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.dto.BaiduMapDto;
import com.liguo.hgl.proxydao.dto.TbCompanyServiceDto;
import com.liguo.hgl.proxydao.dto.WapServiceTypeDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.CustomerServiceDto;
import com.liguo.hgl.proxydao.model.TbCompanyType;
import com.liguo.hgl.proxydao.model.TbServiceType;
import com.liguo.hgl.proxydao.model.TbShopInfo;
import com.liguo.hgl.proxydao.model.TbUserGroup;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.proxydao.util.GaoDeMapUtil;
import com.liguo.hgl.service.TbCompanyServiceService;
import com.liguo.hgl.service.TbCompanyTypeService;
import com.liguo.hgl.service.TbCustomerServiceService;
import com.liguo.hgl.service.TbNoticeInfoService;
import com.liguo.hgl.service.TbServiceTypeService;
import com.liguo.hgl.service.TbShopInfoService;
import com.liguo.hgl.service.TbWapAddressService;
import com.liguo.hgl.util.ImageUtil;
/**
 * 终端客户首页
 * */
@RequestMapping("/master")
@Controller
public class MasterController extends IBaseController {
	
	@Autowired
	private TbServiceTypeService tbServiceTypeService;
	
	@Autowired
	private TbCustomerServiceService tbCustomerServiceService;
	
	@Autowired
	private TbNoticeInfoService noticeInfoService;
	
	@Autowired
	private TbWapAddressService tbWapAddressService;
	
	@Autowired
	private TbShopInfoService tbShopInfoService;
	
	@Autowired
	private TbCompanyTypeService tbCompanyTypeService;
	
	@Autowired
	private TbCompanyServiceService tbCompanyServiceService;
	
	@RequestMapping(value = "/index")
	public String indexPage(PageDto page, HttpServletRequest request,HttpServletResponse response, Model model) {
		init(page,request,response,model);
		return "master/master";
	}
	@RequestMapping(value = "/restIndex")
	public String restIndex(String cityName,PageDto page, HttpServletRequest request,HttpServletResponse response, Model model) {
	    Map<String, String> mapLatitude = GaoDeMapUtil.getLatitude(cityName);
	    if(mapLatitude != null && mapLatitude.size()>0){
	    	double lon2 = Double.parseDouble(mapLatitude.get("lon"));
	    	double lat2 = Double.parseDouble(mapLatitude.get("lat"));
	    	session.setAttribute(HglContants.LON, String.valueOf(lon2));
	        session.setAttribute(HglContants.LAT, String.valueOf(lat2));
	        session.setAttribute(HglContants.ADDRESS_CITY, cityName);
	    }
	    return getServiceTypeInfo(model);
	}
	@Override
	protected void init(PageDto page, HttpServletRequest request,HttpServletResponse response, Model model) {
		List<TbCompanyType> cList = tbCompanyTypeService.selectCompanyTypeInfo();
		model.addAttribute("cList",cList);
	}
	
	/**
	 * 找师傅首页
	 * */
	@RequestMapping("/masterIndex")
	public String getServiceTypeInfo( Model model){
		List<WapServiceTypeDto> s = tbServiceTypeService.selectServiceType();
		Criteria criteria = new Criteria();
    	criteria.put("wapSize", 60);
		List<CustomerServiceDto> cList = tbCustomerServiceService.selectInfoListByName(criteria);
		model.addAttribute("cList",cList);
		model.addAttribute("s", s);
		return "master/masterIndex";
	}
	/**
	 * 服务公司列表
	 * */
	@RequestMapping("/companyServiceList")
	public String companyServiceList(Model model ,Integer typeId,PageDto page){
		
		String lon = (String)session.getAttribute(HglContants.LON);
		String lat = (String)session.getAttribute(HglContants.LAT);
		if(StringUtils.isBlank(lat) || StringUtils.isBlank(lon)){
			return "redirect:/shop/userShop"; 
		}
		Criteria criteria = new Criteria();
		criteria.put("typeId", typeId);
		criteria.put("lon", Double.parseDouble(lon));
		criteria.put("lat", Double.parseDouble(lat));
		criteria.put("address",(String)session.getAttribute(HglContants.ADDRESS_CITY));
		List<TbCompanyServiceDto> cList = tbCompanyServiceService.selectTbCompanyService(criteria);
		model.addAttribute("cList",cList);
		model.addAttribute("typeId",typeId);
		return "master/companyService";
	}
	
	@Override
	public String doSearchResult() {
		// TODO Auto-generated method stub
		return null;
	}
	@RequestMapping("/getServiceTypeInfo")
	public String getServiceTypeInfo(Integer id,Model model){
		Criteria example = new Criteria();
		example.put("parentId",id);
		List<TbServiceType> p = tbServiceTypeService.selectByObject(example);
		model.addAttribute("p", p);
		return "master/serviceTypeList";
	}
	
	@RequestMapping("/selectMasterInfo")
	public String selectMasterInfo(String serchText,String serviceId, Model model){
		Criteria criteria = new Criteria();
    	criteria.put("name", serchText);
    	criteria.put("wapSize", 60);
    	if(!StringUtils.isBlank(serviceId)){
    		criteria.put("serviceId",Arrays.asList(serviceId.split(",")));
		}
		List<CustomerServiceDto> cList = tbCustomerServiceService.selectInfoListByName(criteria);
		model.addAttribute("cList",cList);
		return "master/masterList";
	}
	 /*
     * 定位所有区县位置
     * */
    @RequestMapping("/getAddressInfo")
    @ResponseBody
    public Map<String,Object> getAddressInfo(HttpServletRequest request,Model model) throws Exception {
    	
    	Map<String,Object> map = new HashMap<String,Object>();
    	double lon = 114.126323;
		double lat = 22.537628;
		Integer shopId = getRecommendShopId();
		if(shopId == null){
			shopId = 1;
		}
		if(!StringUtils.isBlank(request.getParameter("lon")) && !StringUtils.isBlank(request.getParameter("lat"))){
			lon = Double.parseDouble(request.getParameter("lon"));
			lat = Double.parseDouble(request.getParameter("lat"));
			session.setAttribute(HglContants.LON, lon+"");
			session.setAttribute(HglContants.LAT, lat+"");
		}else if(!StringUtils.isBlank((String)session.getAttribute(HglContants.LON)) && !StringUtils.isBlank((String)session.getAttribute(HglContants.LAT))){
			lon = Double.parseDouble((String)session.getAttribute(HglContants.LON));
			lat = Double.parseDouble((String)session.getAttribute(HglContants.LAT));
		}
		logger.debug("lat="+lat+","+lon);
    	BaiduMapDto d=	GaoDeMapUtil.selectAddBycoordinate(lon,lat);
		String address = "";
		if(StringUtils.isBlank(d.getRegeocode().getAddressComponent().getCity())){
			address = "定位失败";
		}else{
			address = d.getRegeocode().getAddressComponent().getCity();
			
			session.setAttribute(HglContants.ADDRESS_CITY, address);
			TbShopInfo t = tbShopInfoService.selectByPrimaryKey(shopId);
			double blance = GaoDeMapUtil.GetShortDistance(lon,lat,t.getLon(),t.getLat());
			map.put("blance", blance);
			logger.debug("定位客户的地址>>>>>："+address+"blance>>>>>:"+blance);
			session.setAttribute(HglContants.SHOP_BLANCE, blance);
		}
		map.put("address",address);
		return map;
    }
    
    /**
   	 * 服务公司详情
   	 * */
    @RequestMapping("/showdetailsInfo")
	public String showdetailsInfo(Integer id,Model model){
    	String lon = (String)session.getAttribute(HglContants.LON);
		String lat = (String)session.getAttribute(HglContants.LAT);
		Criteria criteria = new Criteria();
		criteria.put("lon", Double.parseDouble(lon));
		criteria.put("lat", Double.parseDouble(lat));
		criteria.put("id",id);
		TbCompanyServiceDto pifd = tbCompanyServiceService.selectTbCompanyService(criteria).get(0);
		model.addAttribute("pifd",pifd);
		return "master/companyServiceDetail";
	}
    
    /**
	 * 得到服务公司类型图片
	 * */
    @RequestMapping("/generateImage")
    public String generateImage(ModelMap model,Integer id,String imgName,HttpServletResponse response) {
    	ImageUtil.showImageWapProduct(null,imgName,response,HglContants.COMPANY_TYPE);
        return null;
    }
    /**
   	 * 得到服务公司实景图图片
   	 * */
    @RequestMapping("/generateComImage")
    public String generateComImage(ModelMap model,Integer id,String imgName,HttpServletResponse response) {
    	ImageUtil.showImageWapProduct(id,imgName,response,HglContants.COMPANY_SERVICE);
        return null;
    }
    
    
    /**
   	 * 公司导航
   	 * */
    @RequestMapping("/companyMap")
    public String companyMap(ModelMap model,String companyLon,String companyLat) {
    	model.addAttribute("companyLon",companyLon);
    	model.addAttribute("companyLat",companyLat);
        return "master/companyMap";
    }
    
    /**
	 *信息查询
	 */
	@RequestMapping("/selectInfoList")
    public String selectInfoList(ModelMap model,Integer typeId,String name,HttpServletResponse response) {
		Criteria criteria  = new Criteria();
		if(!StringUtils.isBlank(name)){
			criteria.put("companyName", name.trim());
		}
		criteria.put("typeId",typeId);
		criteria.put("address",(String)session.getAttribute(HglContants.ADDRESS_CITY));
		List<TbCompanyServiceDto> cList = tbCompanyServiceService.selectTbCompanyService(criteria);
		model.addAttribute("cList", cList);
        return "master/companyServiceList";
    }
	
	/**
	 * 找服务公司中的地址选择跳转
	 * @param cityName
	 * @param typeId
	 * @param page
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/restCompanyIndex")
	public String restCompanyIndex(String cityName,Integer typeId,PageDto page, HttpServletRequest request,HttpServletResponse response, Model model) {
	    Map<String, String> mapLatitude = GaoDeMapUtil.getLatitude(cityName);
	    if(mapLatitude != null && mapLatitude.size()>0){
	    	double lon2 = Double.parseDouble(mapLatitude.get("lon"));
	    	double lat2 = Double.parseDouble(mapLatitude.get("lat"));
	    	session.setAttribute(HglContants.LON, String.valueOf(lon2));
	        session.setAttribute(HglContants.LAT, String.valueOf(lat2));
	        session.setAttribute(HglContants.ADDRESS_CITY, cityName);
	    }
	    return companyServiceList(model,typeId,page);
	}
}
