package com.linkon.hgl.web.action;

import java.util.HashMap;
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
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.CustomerServiceDto;
import com.liguo.hgl.proxydao.model.TbSaveInfo;
import com.liguo.hgl.proxydao.model.TbWebUser;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.proxydao.util.GaoDeMapUtil;
import com.liguo.hgl.service.TbCustomerServiceService;
import com.liguo.hgl.service.TbNoticeInfoService;
import com.liguo.hgl.service.TbSaveInfoService;
import com.liguo.hgl.service.TbWapAddressService;
import com.liguo.hgl.util.ImageUtil;
/**
 * 终端客户首页
 * */
@RequestMapping("/customerIndex")
@Controller
public class CustomerIndexController extends IBaseController {

	
	@Autowired
	private TbCustomerServiceService tbCustomerServiceService;
	
	@Autowired
	private TbNoticeInfoService noticeInfoService;
	
	@Autowired
	private TbWapAddressService tbWapAddressService;
	
	@Autowired
	private TbSaveInfoService saveInfoService;
	
	@RequestMapping(value = "")
	public String indexPage(PageDto page, HttpServletRequest request,HttpServletResponse response, Model model) {
	/*	List<TbNoticeInfo> tbNoticeInfolist = noticeInfoService.showNewestNoticeInfo(0,5);
		model.addAttribute("tbNoticeInfolist", tbNoticeInfolist);
		Criteria criteria = new Criteria();
    	criteria.put("wapSize", 6);
		List<CustomerServiceDto> shopInfoList = tbCustomerServiceService.selectInfoListByName(criteria);
		model.addAttribute("shopInfoList", shopInfoList);
		return "customerIndex/customerIndex";*/
		return "redirect:shop/userShop"; 
	}
	
	@RequestMapping(value = "/index")
	public String customerIndex(HttpServletRequest request,HttpServletResponse response, Model model) {
		
		return "redirect:shop/userShop"; 
		/*if(StringUtils.isBlank((String)session.getAttribute(HglContants.LON))){
			return "redirect:/customerIndex";
		}
		List<TbNoticeInfo> tbNoticeInfolist = noticeInfoService.showNewestNoticeInfo(0,5);
		model.addAttribute("tbNoticeInfolist", tbNoticeInfolist);
			double lon = Double.parseDouble((String)session.getAttribute(HglContants.LON));
			double lat = Double.parseDouble((String)session.getAttribute(HglContants.LAT));
		logger.debug("lat="+lat+","+lon);
		Criteria criteria = new Criteria();
    	criteria.put("wapSize",6);
		List<CustomerServiceDto> shopInfoList = tbCustomerServiceService.selectInfoListByName(criteria);
		model.addAttribute("shopInfoList", shopInfoList);
		return "customerIndex/customerIndex";*/
	}
	/**
	 * 
	 * <选择开通区域后的跳转>
	 * @param cityName
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @author wzt
	 * @since   2016年6月17日
	 */
	@RequestMapping(value = "/rectCustomerIndex")
    public String indexShopInfo(String cityName,HttpServletRequest request,HttpServletResponse response, Model model) {
        Map<String, String> mapLatitude = GaoDeMapUtil.getLatitude(cityName);
        double lon2 = Double.parseDouble(mapLatitude.get("lon"));
        double lat2 = Double.parseDouble(mapLatitude.get("lat"));
        session.setAttribute(HglContants.LON, String.valueOf(lon2));
        session.setAttribute(HglContants.LAT, String.valueOf(lat2));
        session.setAttribute(HglContants.ADDRESS_CITY, cityName);
        return customerIndex(request, response, model);
    }
    
	
	@Override
	protected void init(PageDto page, HttpServletRequest request,HttpServletResponse response, Model model) {}
	
	@RequestMapping("/generateImage")
    public String generateImage(ModelMap model,Integer id,String imgName,HttpServletResponse response) {
    	ImageUtil.showImageWapProduct(id,imgName,response,HglContants.CUSTOMER_PATH);
        return null;
    }
	
    @RequestMapping("/goServiceDetail")
    public String goServiceDetail(Integer serviceId, Model model){
    	/*如果是师傅，则不显示服务收藏功能*/
    	TbWebUser webUser = this.getLoginUser();
    	model.addAttribute("webUser", webUser);
    	
    	CustomerServiceDto c = tbCustomerServiceService.selectInfoById(serviceId);
    	model.addAttribute("c", c);
    	/*查看该服务是否有被收藏*/
    	TbSaveInfo saveInfo=saveInfoService.selectSaveInfo(serviceId, HglContants.SERVICE_SAVE, this.getLoginUserId());
    	model.addAttribute("saveInfo", saveInfo);
    	
    	return "customerIndex/customerServiceDetail";
    }
  
    
    /*收藏服务*/
    @ResponseBody
    @RequestMapping("/doOperateServiceSaveInfo")
    public String doOperateServiceSaveInfo(Integer id,Integer typeId){
    	return saveInfoService.doWapOperateServiceSaveInfo(id, typeId, this.getLoginUserId());   	
    }
    
	@Override
	public String doSearchResult() {
		return null;
	}
}
