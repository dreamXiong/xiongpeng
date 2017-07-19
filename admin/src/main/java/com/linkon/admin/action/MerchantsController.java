package com.linkon.admin.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.dto.AgencyDto;
import com.liguo.hgl.proxydao.dto.TbMerchantsAssociatedDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbBrand;
import com.liguo.hgl.proxydao.model.TbCityInfo;
import com.liguo.hgl.proxydao.model.TbCountryInfo;
import com.liguo.hgl.proxydao.model.TbMerchNotice;
import com.liguo.hgl.proxydao.model.TbMerchants;
import com.liguo.hgl.proxydao.model.TbStreetInfo;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.proxydao.util.StringUtil;
import com.liguo.hgl.service.TbAgencyService;
import com.liguo.hgl.service.TbBrandService;
import com.liguo.hgl.service.TbCityInfoService;
import com.liguo.hgl.service.TbCountryInfoService;
import com.liguo.hgl.service.TbMerchNoticeService;
import com.liguo.hgl.service.TbMerchantsService;
import com.liguo.hgl.service.TbProvinceInfoService;
import com.liguo.hgl.service.TbStreetInfoService;
import com.liguo.hgl.util.ImageUtil;
import com.linkon.admin.action.IBaseController;

/**
 * 招商类
 * @fiMerchantsController.java
 * @2016-3-29	
 * @author 周双双
 */
@Controller
@RequestMapping("/merchants")
public class MerchantsController extends IBaseController{
	
	   @Autowired
		private TbMerchantsService iMerchantsService;
	   
		@Autowired
		protected TbBrandService tbBrandService;
		
		@Autowired
		protected TbProvinceInfoService tbProvinceInfoService;
		
		@Autowired
		protected TbCityInfoService tbCityInfoService;
		
		@Autowired
		protected TbCountryInfoService tbCountryInfoService;
		
		@Autowired
		protected TbStreetInfoService tbStreetInfoService;
		
		@Autowired
		protected TbAgencyService agencyService;
		
		@Autowired
		private TbMerchNoticeService merchNoticeService;
		
	   /**
	    * 编辑招商
	    * @param tbMerchants
	    * @return
	    */
	    @RequestMapping(value="/addMerchants")
	    public ModelAndView edit(TbMerchants tbMerchants){
	    	ModelAndView modelAndView = new ModelAndView();
	    	tbMerchants.setUserId(getLoginUserId());//获取当前登录用户
	    	int count = iMerchantsService.insertSelective(tbMerchants);
	    	if(count >0){
	    		modelAndView.setViewName("redirect:merchants");
	    	}else{
	    		
	    		modelAndView.setViewName("merchants/addMerchants");
	    	}
	    	
	    	return modelAndView;
	    }
	    /**
	     * 校验招商
	     * @param brandId
	     * @param city
	     * @param country
	     * @param street
	     * @param merchantsId
	     * @return
	     * @author zss
	     */
	    @RequestMapping("/checkMerchants")
		@ResponseBody
		public boolean checkMerchants(int brandId ,int city,int country,int street,int merchantsId){
			int count = iMerchantsService.checkMerchants(brandId,city,country,street,merchantsId);
			if(count>0){
				return false;
			}
			return true;
			
		}
	    
	    
	    /**
	     * 查询所有的招商
	     * @param brandName 根据名称查询招商
	     * @return
	     */
	    @RequestMapping(value="/merchants")
	    public ModelAndView getMerchants(PageDto page){
	    	ModelAndView modelAndView = new ModelAndView();
	    	List<TbMerchantsAssociatedDto> merchants = iMerchantsService.getMerchantsList(new Criteria(),page);
	    	if(merchants.size()>0){
	    		modelAndView.addObject("merchants", merchants);
	    	}
	    	modelAndView.addObject(HglContants.PAGE_DTO_ID, page);
	    	modelAndView.setViewName("merchants/merchants");
	        return modelAndView;
	    }
	    
	    @RequestMapping(value="/serachMerchants")
	    public ModelAndView getSerachMerchants(HttpServletRequest request,PageDto page){
	    	Criteria criteria = new Criteria();
			String name = request.getParameter("brandName");
			if (!StringUtil.isEmpty(name)) {
				criteria.put("brandName", StringUtil.trim(name));
			}
	    	ModelAndView modelAndView = new ModelAndView();
	    	List<TbMerchantsAssociatedDto> merchants = iMerchantsService.getMerchantsList(criteria,page);
	    	if(merchants.size()>0){
	    		modelAndView.addObject("merchants", merchants);
	    	}
	    	modelAndView.addObject(HglContants.PAGE_DTO_ID, page);
	    	modelAndView.setViewName("merchants/merchantsList");
	        return modelAndView;
	    }
	    
	    /**
	     * 修改查询
	     * @param id
	     * @return
	     */
	    @RequestMapping(value="/updateMerchants",method=RequestMethod.GET)
	    public ModelAndView getMerchantsById(@RequestParam Integer id){
	    	ModelAndView modelAndView = new ModelAndView();
	    	if(id!=null && id >0){
	    		TbMerchants merchants =	iMerchantsService.selectById(id);
	    		modelAndView.addObject("brands", tbBrandService.selectByObject(null));
	    		modelAndView.addObject("provinceInfos", tbProvinceInfoService.selectByObject(null));
	    		modelAndView.addObject("merchants", merchants);
	    	}
	    	modelAndView.setViewName("merchants/updateMerchants");
	        return modelAndView;
	    }
	    /**
	     * 修改招商信息
	     * @param merchants
	     * @return
	     * @author zss
	     */
	    @RequestMapping(value="/updateMerchants",method=RequestMethod.POST)
	    public ModelAndView updateMerchants(TbMerchants merchants){
	    	ModelAndView modelAndView = new ModelAndView();
	    	merchants.setUserId(getLoginUserId());//获取当前登录用户
	    	iMerchantsService.updateByPrimaryKeySelective(merchants);
	    	modelAndView.setViewName("redirect:merchants");
	        return modelAndView;
	    }
	    /**
	     * 获取添加招商页面
	     * @param param
	     * @return
	     * @author zss
	     */
	    @RequestMapping(value="/addMerchants",method=RequestMethod.GET)
	    public ModelAndView edit1(@RequestParam Map<String, Object> param){
	    	ModelAndView modelAndView = new ModelAndView();
	    	Criteria example = new Criteria();
	    	example.put("type", HglContants.BRAND_DEFAULT_MANUFACTURERID);
	    	List<TbBrand> brands = tbBrandService.selectByObject(example);
	    	if(brands.size() >0){
	    		modelAndView.addObject("brands", brands);
	    	}
	    	modelAndView.addObject("provinceInfos", tbProvinceInfoService.selectByObject(null));
	    	modelAndView.setViewName("merchants/addMerchants");
	        return modelAndView;
	    }
	    
	    /**
	     * 改变招商的状态
	     * @param id
	     * @return
	     */
	    @RequestMapping(value="/updateMerchantsState")
	    @ResponseBody
	    public Map<String,Object> updateMerchantsState(@RequestParam Integer id){
	    	Map<String,Object> map = new HashMap<String,Object>();
	    	TbMerchants merchants = iMerchantsService.updateState(id);
	    	map.put("state", merchants.getState());
	    	map.put("msg", "成功！");
	    	 return map;
	    }
	    
	    /**
	     * 后台招商详情
	     * @param id
	     * @return
	     */
	    @RequestMapping(value="/merchantsDetails")
	    public ModelAndView getMerchantsDetails(PageDto page,@RequestParam Integer id){
	    	ModelAndView modelAndView = new ModelAndView();
	    	Criteria criteria = new Criteria();
	    	if(id!=null && id >0){
	    		TbMerchants merchants =	iMerchantsService.selectById(id);
	    		criteria.put("merchantId", id);
	    		List<AgencyDto> tbAgencies = agencyService.selectAgencyByMid(criteria,page);
	    		modelAndView.addObject("merchants", merchants);
	    		modelAndView.addObject("agencies", tbAgencies);
	    	}
	    	modelAndView.addObject(HglContants.PAGE_DTO_ID, page);
	    	modelAndView.setViewName("merchants/merchantsDetails");
	        return modelAndView;
	    }
	    
	    /**
	     * 根据条件查询招商列表
	     * @param request
	     * @param page
	     * @return
	     * @author zss
	     */
	    @RequestMapping(value="/serachMerchantsDetails")
	    public ModelAndView getSerachMerchantsDetails(HttpServletRequest request,PageDto page){
	    	Criteria criteria = new Criteria();
			String name = request.getParameter("shopName");
			if (!StringUtil.isEmpty(name)) {
				criteria.put("shopName", StringUtil.trim(name));
			}
			if(!StringUtils.isEmpty(request.getParameter("state"))){
				criteria.put("state",  Integer.parseInt(request.getParameter("state")));
			}
	    	ModelAndView modelAndView = new ModelAndView();
	    	int id=Integer.parseInt(request.getParameter("id").toString()) ;
	    	if(id >0){
	    		//TbMerchants merchants =	iMerchantsService.selectById(id);
	    		criteria.put("merchantId", id);
	    		List<AgencyDto> tbAgencies = agencyService.selectAgencyByMid(criteria,page);
	    		//modelAndView.addObject("merchants", merchants);
	    		modelAndView.addObject("agencies", tbAgencies);
	    	}
	    	modelAndView.addObject(HglContants.PAGE_DTO_ID, page);
	    	modelAndView.setViewName("merchants/agencyList");
	        return modelAndView;
	    }
	    /**
	     * 修改代理状态
	     * @param id
	     * @param state
	     * @return
	     * @author zss
	     */
		@RequestMapping(value="/updateAgencyState")
	    @ResponseBody
	    public Map<String,Object> updateAgencyState(Integer id,Integer state){
			logger.debug("id----------"+id+"-------"+state);
	    	Map<String,Object> map = agencyService.updateAgencyState(id,state);
	    	
	    	 return map;
	    }
		
	    /**
	     * 根据省Id查询所有的城市
	     * @param pid
	     * @return
	     */
	    @ResponseBody
		@RequestMapping(value="/cityInfos")
	    public Map<String,Object> getCityInfos(@RequestParam Integer pid){
	    	List<TbCityInfo> cityInfos = tbCityInfoService.getCityInfos(pid);
	    	Map<String,Object> map = new HashMap<String,Object>();
	    	map.put("cityInfos", cityInfos);
	    	return map;
	    }
	    
	    /**
	     * 根据市Id查询其下所有的区域
	     * @param cid
	     * @return
	     */
	    @ResponseBody
		@RequestMapping(value="/countryInfos")
	    public Map<String,Object> getCountryInfos(@RequestParam Integer cid){
	    	List<TbCountryInfo> countryInfos = tbCountryInfoService.getCountrys(cid);
	    	Map<String,Object> map = new HashMap<String,Object>();
	    	map.put("countryInfos", countryInfos);
	    	return map;
	    }
	    
	    /**
	     * 根据区域Id查询其下所有的街道
	     * @param cid
	     * @return
	     */
	    @ResponseBody
		@RequestMapping(value="/streetInfos")
	    public Map<String,Object> getStreetInfos(@RequestParam Integer cid){
	    	List<TbStreetInfo> streetInfos = tbStreetInfoService.getStreetInfos(cid);
	    	Map<String,Object> map = new HashMap<String,Object>();
	    	map.put("streetInfos", streetInfos);
	    	return map;
	    }
	    
	    /*
	     * 根据招商id查询招商公告*/
	    @RequestMapping(value="/doSearchMerchNotice",produces="text/html;charset=UTF-8")
	    @ResponseBody
	    public String doSearchMerchNotice(Integer merchantId){
	    	String stringJson ="";
	    	Criteria criteria = new Criteria();
	    	criteria.put("merchantid", merchantId);
	    	List<TbMerchNotice> list = merchNoticeService.selectByObject(criteria);
	    	ObjectMapper mapper = new ObjectMapper();
	    	try {
				stringJson = mapper.writeValueAsString(list);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
	    	return stringJson;    	
	    }
	    
	    /**
	     * 发布招商公告
	     * @author zst
	     * @return
	     */
	    @RequestMapping("/publishMerchNotice")
	    @ResponseBody
	    public String publishMerchNotice(Integer merchantId,String name,String detail,Integer typeId){
	    	Integer result=0;
	    	result = iMerchantsService.operateMerchNotice(merchantId,name,detail,this.getLoginUser().getUserName(),typeId);	    	
	    	return String.valueOf(result);
	    }

		@Override
		protected void init(PageDto page, HttpServletRequest request,
				HttpServletResponse response, Model model) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public String doSearchResult() {
			// TODO Auto-generated method stub
			return null;
		}
		/**
		 * 读取招商图片信息
		 * @param model
		 * @param id 招商Id
		 * @param response
		 * @return
		 * @author zss
		 */
		 @RequestMapping("/generateImage")
		    public String generateImage(ModelMap model,Integer id,HttpServletResponse response) {
		    	ImageUtil.showImageMerchants(id,response);
		        return null;
		    }

}
