package com.linkon.hgl.web.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.dto.WapProductType;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.proxydao.util.BaiduMapUtil;
import com.liguo.hgl.proxydao.util.GaoDeMapUtil;
import com.liguo.hgl.service.TbShopInfoService;
import com.liguo.hgl.service.TbWapProductInventoryService;

/**
 * 产品选购
 * 
 * */
@Controller
@RequestMapping("/productType")
public class ProductTypeController extends IBaseController{

	@Autowired
	protected TbWapProductInventoryService tbWapProductInventoryService;
	
	@Autowired
	private TbShopInfoService tbShopInfoService;
	
	@RequestMapping(value = "")
	public String indexPage(PageDto page, HttpServletRequest request,HttpServletResponse response, Model model) {
		/**
		 * 当用户没有登陆时进入首页时，将用户当前的坐标传入session中
		 *	如果出现session失效的情况就重定向到首页 
		 * */
		if(StringUtils.isBlank((String)session.getAttribute(HglContants.LON))){
			return "redirect:/";
		}
		double lon = Double.parseDouble((String)session.getAttribute(HglContants.LON));
		double lat = Double.parseDouble((String)session.getAttribute(HglContants.LAT));
		String address="";
		try{
			address = GaoDeMapUtil.selectAddBycoordinate(lon,lat).getRegeocode().getAddressComponent().getDistrict();	
		}catch(IOException e){
			logger.debug("定位失败！");
		}
		Criteria c = new Criteria();
		c.put("address", address);
		List<WapProductType> productTypeList = tbShopInfoService.selectProductBrand(c);
		model.addAttribute("productTypeList",productTypeList);
		return "productType/productType";
	}
	@Override
	protected void init(PageDto page, HttpServletRequest request,HttpServletResponse response, Model model) {
		
	}
	@Override
	public String doSearchResult() {
		return null;
	}
}
