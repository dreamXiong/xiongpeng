package com.linkon.hgl.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.dto.TbMerchantsAssociatedDto;
import com.liguo.hgl.proxydao.model.TbBrand;
import com.liguo.hgl.proxydao.model.TbNoticeInfo;
import com.liguo.hgl.proxydao.model.TbProductType;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.IProductTypeService;
import com.liguo.hgl.service.TbBrandService;
import com.liguo.hgl.service.TbMerchantsService;
import com.liguo.hgl.service.TbNoticeInfoService;
import com.liguo.hgl.util.ImageUtil;

@Controller
public class indexController extends IBaseController {

	@Autowired
	private IProductTypeService productTypeService;
	
	@Autowired
	private TbBrandService tbBrandService;
	
	@Autowired
	private TbMerchantsService tbMerchantService;

	@Autowired
	private TbNoticeInfoService noticeInfoService;
    
	
	@RequestMapping("/")
	public ModelAndView queryProductType() {
		
		ModelAndView mv = new ModelAndView("redirect:/register/jxregister");
	/*	logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>..首页");
		ModelAndView mv = new ModelAndView("index/index");
		List<TbProductType> productFstTypes = productTypeService.getProductType();
		mv.addObject(HglContants.PRODUCTTYPES, productFstTypes);	
		session.setAttribute(HglContants.PRODUCTTYPES, productFstTypes);
		
		List<TbMerchantsAssociatedDto> merchantsDtos = tbMerchantService.getNewestMerchants();
		mv.addObject("merchantsDtos", merchantsDtos);*/
		
		return mv;
	}
	
	
	/**
	 * 显示招商品牌
	 * @param model
	 * @param id :品牌id
	 * @param response
	 * @return
	 */
	@RequestMapping("/generateImage")
    public String generateImage(ModelMap model,Integer id,HttpServletResponse response) {
    	TbBrand tbBrand = tbBrandService.selectByPrimaryKey(id);
    	model.addAttribute("tbBrand", tbBrand);
    	if(tbBrand!=null){
    		ImageUtil.showImage("shopLogo",tbBrand.getLogoName(),response);
    	}
        return null;
    }
	
	/**
	 * 显示招商品牌
	 * @param model
	 * @param imgName :图片名称
	 * @param response
	 * @return
	 */
	@RequestMapping("/generateBrandImage")
	public String generateBrandImage(ModelMap model,String imgName,HttpServletResponse response) {
			ImageUtil.showImage("shopLogo",imgName,response);
		return null;
	}
	
	/**
	 * 显示产品图片
	 * @param model
	 * @param id
	 * @param imgName
	 * @param response
	 * @return
	 */
	 @RequestMapping("/generateProductImage")
	    public String generateProductImage(ModelMap model,Integer id,String imgName,HttpServletResponse response) {
	    	ImageUtil.showImageProduct(id,imgName,response);
	        return null;
	    }
	 

		
		@RequestMapping(value ="/queryNewestNoticeInfo", produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String queryNewestNoticeInfo(
				@RequestParam(value = "from", required = false, defaultValue = "0") Integer from,
				@RequestParam(value = "length", required = false, defaultValue = "11") Integer length) {

			String noticeInfoArray = "";
			List<TbNoticeInfo> list = noticeInfoService.showNewestNoticeInfo(from,length);
			ObjectMapper mapper = new ObjectMapper();
			try {
				noticeInfoArray = mapper.writeValueAsString(list);
			} catch (Exception e) {

				e.printStackTrace();
			}
			return noticeInfoArray;
		}
		    
		    
		    @RequestMapping("/license")
		    public String license(){
		    	
		    	return "index/license";
		    }

		    @Override
		    protected void init(PageDto page, HttpServletRequest request, HttpServletResponse response, Model model) {

		        // TODO Auto-generated method stub

		    }

		    @Override
		    public String doSearchResult() {

		        // TODO Auto-generated method stub
		        return null;
		    }
}
