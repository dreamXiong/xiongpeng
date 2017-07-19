package com.linkon.hgl.web.action;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.common.QRCodeUtil;
import com.liguo.hgl.dto.MyOrderForm;
import com.liguo.hgl.exceptions.TransactionException;
import com.liguo.hgl.proxydao.dto.ShopWebUserDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbIntegralRules;
import com.liguo.hgl.proxydao.model.TbProductType;
import com.liguo.hgl.proxydao.model.TbRecommendedRules;
import com.liguo.hgl.proxydao.model.TbShopInfo;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.IProductTypeService;
import com.liguo.hgl.service.PaymentHandler;
import com.liguo.hgl.service.TbIntegralRulesService;
import com.liguo.hgl.service.TbProvinceInfoService;
import com.liguo.hgl.service.TbRecommendedRulesService;
import com.liguo.hgl.service.TbShopInfoService;
import com.liguo.hgl.service.TbSystemConfigService;
import com.liguo.hgl.service.TbWebUserService;
import com.liguo.hgl.util.ImageUtil;
import com.liguo.hgl.util.StringUtil;
/**
 * shop用户操作类
 * @fiShopInfroController.java
 * @2016-5-17	
 * @author 周双双
 */
@Controller
@RequestMapping("shop")
public class ShopInfoController extends IBaseController{
	
	@Autowired
	protected TbWebUserService tbWebUserService;
	
	@Autowired
	protected IProductTypeService productTypeService;
	
	@Autowired
	protected TbProvinceInfoService tbProvinceInfoService;
	
	@Autowired
	protected TbShopInfoService tbShopInfoService;
	
	@Autowired
	private TbRecommendedRulesService recommendedRulesService;
	
	@Autowired
	protected PaymentHandler paymentHandler;
	
	@Autowired
	protected TbSystemConfigService tbSystemConfigService;
	
	@Autowired
	protected TbIntegralRulesService tbIntegralRulesService;
	/**
	 * 用户是否审核通过
	 * @return
	 * @author zss
	 */
	 @ResponseBody
	 @RequestMapping(value="/checkShopUser")
	 public ModelAndView checkShopUser(){
		 ModelAndView modelAndView = new ModelAndView();
		 if(getShopId() !=null){
			 Map<String,Object> map=tbWebUserService.isApproved(getLoginUserId());
			 modelAndView.addObject("errcode", map.get("errcode"));
			 modelAndView.addObject("msg", map.get("msg"));
		 }
		 return modelAndView;
	 }
	/**
	 * 用户重新提交审核
	 * @param id
	 * @return
	 * @author zss
	 */
	 @RequestMapping(value="/shopEdit",method=RequestMethod.GET) 
    public ModelAndView getSuppliersById(ModelAndView modelAndView){
    	 if(getShopId() !=null){
			 Map<String,Object> map=tbWebUserService.isApproved(getLoginUserId());
			logger.debug("------------errcode:"+map.get("errcode"));
			 modelAndView.addObject("errcode", map.get("errcode"));
			 modelAndView.addObject("msg", map.get("msg"));
			 if(map!=null && map.get("errcode").equals("2")){
				 TbProductType tbProductType = new TbProductType();
				 tbProductType.setParentId(0);
				 modelAndView.addObject("productTypes",  productTypeService.selectByTbProductType(tbProductType));
		    	ShopWebUserDto shopDto  = tbWebUserService.getShopWebUser(getShopId());
		    	if(shopDto!=null){
		    		modelAndView.addObject("shopDto", shopDto);
		    		modelAndView.addObject("provinceInfos", tbProvinceInfoService.selectByObject(null));
		    	}
			 }
		 }
    	
    	modelAndView.setViewName("webuser/shopEdit");
        return modelAndView;
     }
	/**
	 * 经销商重新提交审核资料
	 * @param param
	 * @return
	 * @author zss
	 */
	@RequestMapping(value="/shopEdit",method=RequestMethod.POST) 
    public ModelAndView updateSuppliers(@RequestParam Map<String, Object> param){
		System.out.println("param:::::"+param+"---------"+getShopId());
    	ModelAndView modelAndView = new ModelAndView();
    	tbWebUserService.updateShopByUser(param, getShopId(),getLoginUserId());
    	modelAndView.setViewName("redirect:shopEdit");
        return modelAndView;
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
	
	  @RequestMapping("/generateImage")
	    public String generateImage(ModelMap model,Integer id,String imgName,HttpServletResponse response) {
	    	ImageUtil.showImageShop(id,imgName,response);
	        return null;
	    }

  /**
   * 店铺管理
   * @return
   * @author zss
   */
  @RequestMapping(value="/shopManagement")
  public ModelAndView getSuppliers(){
    	ModelAndView modelAndView = new ModelAndView();
    	if(getShopId() !=null){
    		ShopWebUserDto shopInfo = tbWebUserService.getShopWebUser(getShopId());
	    	Criteria criteria = new Criteria();
	    	criteria.put("shopId", getShopId());
	    	List<TbRecommendedRules> recommendedRules = recommendedRulesService.selectByObject(criteria);
	    	List<TbIntegralRules> tbIntegralRules = tbIntegralRulesService.selectByObject(criteria);
	    	String configValue = tbSystemConfigService.selectByConfigKey("settlement").getConfigValue();
	    	modelAndView.addObject("recommendedRules", recommendedRules);
	    	modelAndView.addObject("tbIntegralRules", tbIntegralRules);
	    	modelAndView.addObject("shopInfo", shopInfo);
	    	modelAndView.addObject("configValue", configValue);
    	}
    	modelAndView.setViewName("webuser/shopManagement");
        return modelAndView;
 }
  
  /**
   * 获取金牌代理商需支付金额
   * @return
   * @throws Exception
   */
  @RequestMapping(value="/getMedalAgentMoney")
  public @ResponseBody String getMedalAgentMoney() throws Exception{
	  if(getShopId() !=null){
		  TbShopInfo tbShopInfo = tbShopInfoService.selectByPrimaryKey(getShopId());  
		  String configValue = tbSystemConfigService.selectByConfigKey(HglContants.SHOP_MEDAL_AGENT).getConfigValue();
		  Double payMoney = tbShopInfoService.subtractSettlementMoney(tbShopInfo,Double.parseDouble(configValue));
		  return String.valueOf(payMoney);
	  }
	  return null;
  }
  
  /**
   * 积分规则开启禁用操作
   * @return
   * @author zk
   */
  @RequestMapping(value="/installTbIntegralRules")
  @ResponseBody
  public Map<String,Object> installTbIntegralRules(Integer useSituation,Integer type){
	  Map<String ,Object> map = new HashMap<String ,Object>();
	  int count = 0;
	  Criteria criteria = new Criteria();
      criteria.put("shopId", getShopId());
	  List<TbIntegralRules> tbIntegralRules = tbIntegralRulesService.selectByObject(criteria);
	  Integer unType = useSituation == 0 ? 1 : 0;
	  for(TbIntegralRules t : tbIntegralRules){
		  if(type.toString().equals(t.getType().toString())){
			  t.setUseSituation(useSituation);
		  }else /*if(!type.toString().equals(t.getType().toString()) && !t.getUseSituation().toString().equals(useSituation.toString()))*/{
			  t.setUseSituation( unType);
		  }
		  count ++;
		  tbIntegralRulesService.updateByPrimaryKey(t);
	  }
	  map.put("count", count);
	  return map;
  }

  /**
   * 
   * @Description:更新积分规则
  * @author:ZK 
  * @date:2016-8-4
  * @parameter:
   */
  @RequestMapping(value="/saveIntegralrules")
  public String saveIntegralrules(Integer integralId,Integer money ,Integer payMoney){
	  if(money >= 0 && payMoney >= 0){
		  Criteria example = new Criteria();
		  example.put("id", integralId);
		  example.put("shopId", getShopId());
		  List<TbIntegralRules> tList = tbIntegralRulesService.selectByObject(example);
		  if(tList.size() == 1){
			  TbIntegralRules tbIntegralRules = tList.get(0);
			  tbIntegralRules.setMoney(money);
			  tbIntegralRules.setPayMoney(payMoney);
			  tbIntegralRulesService.updateByPrimaryKey(tbIntegralRules);
		  }
	  }
	  return "redirect:/shop/shopManagement";
  }
  
  /**
   * 获取修改店铺推荐规则页面
   * @param rulesId
   * @return
   * @author zss
   */
  @RequestMapping(value="/shopRules")
  public ModelAndView getShopRules(Integer rulesId){
    	ModelAndView modelAndView = new ModelAndView();
    	if(rulesId !=null){
	    	TbRecommendedRules tbRecommendedRules = recommendedRulesService.selectByPrimaryKey(rulesId);
	    	modelAndView.addObject("rules", tbRecommendedRules);
    	}
    	modelAndView.setViewName("webuser/myRecommedRules");
        return modelAndView;
 } 
  /**
   * 修改店铺推荐规则
   * @param rules
   * @return
   * @author zss
   */
  @RequestMapping(value="/updateRules",method=RequestMethod.POST)
  public ModelAndView updateRules(TbRecommendedRules rules){
  	ModelAndView modelAndView = new ModelAndView();
  	rules.setUpdateDt(System.currentTimeMillis());
  	recommendedRulesService.updateByPrimaryKeySelective(rules);
  	modelAndView.setViewName("redirect:/shop/shopManagement");
      return modelAndView;
  }
  
  /**
   * 修改返利设置
   * @param model
   * @param id
   * @param rebate
   * @return
   * @author zss
   */
  @RequestMapping(value="/updateEarnings")
  @ResponseBody
	public Map<String,Object> updateEarnings(Model model,Integer id,double rebate){
		Map<String,Object> map =null;
		if(id!=null && id>0){
			map = tbShopInfoService.updateEarnings(id,rebate);
		}
		return map;
	}
 /**
  * 设置自动确认订单
  * @param model
  * @param id
  * @return
  * @author zss
  */
  @RequestMapping(value="/doAutomatic")
  @ResponseBody
	public String doAutomaticOder(Model model,Integer id){
		if(id!=null && id>0){
			Map<String,Object> map = tbShopInfoService.doAutomaticOder(id);
		}
		
		return "shop/shopManagement";
	}
  /**
   * 用户选择线下支付开通结算功能
   * */
  @RequestMapping(value = "/getSettlement")
  @ResponseBody
  public String getSettlement(Integer settlement,Integer shopId ,Integer version,String remarkInfo,String openType){
  	TbShopInfo tbShopInfo = tbShopInfoService.selectByPrimaryKey(shopId);
  	//上架货品平台结算权限类型
  	if(HglContants.SHOP_SETTLEMENT.equals(openType)){
  		tbShopInfo.setBalance(settlement);
  		tbShopInfo.setRemark(remarkInfo);
  	}
  	//金牌代理商权限类型
  	if(HglContants.SHOP_MEDAL_AGENT.equals(openType)){
  		tbShopInfo.setMedalAgentFlag(settlement);
  		tbShopInfo.setRemark(remarkInfo);
  	}
  	tbShopInfoService.updateByPrimaryKey(tbShopInfo);
  	return null;
  }
  
  /**
   * 店铺二维码
   * @param model
   * @param id
   * @param response
   * @return
   * @author zss
   */
  @RequestMapping("/shopCode")
  public String shopCodeImage(ModelMap model,Integer id,HttpServletResponse response) {
  	ImageUtil.showImageshopCode(id,response);
      return null;
  }
  
  /**
   * 账户支付开通平台结算功能
   * */
  @RequestMapping("/accountPay")
  @ResponseBody
  public Map<String, Object>  accountPay(Model model,Integer shopId,String openType) throws TransactionException {
	  Map<String, Object> map = new HashMap<String, Object>();
	  if(tbShopInfoService.getSellement(shopId, getAccountId(),openType) == 1){
		  tbShopInfoService.addShopIntegral(shopId);
		  map.put("code",1);
	  }
	  return map;
  }
  
	/**
	 * 
	 * 微信扫码支付
	 *  com.liguo.hgl.util
	 * @throws TransactionException 
	 * */
	@RequestMapping(value = "/weixinPay")
	public String weixinPay(MyOrderForm myOrderForm,String openType,Model model,PageDto page,HttpServletRequest request,RedirectAttributes attr) throws TransactionException{
		String orderSerialNo = com.liguo.hgl.util.StringUtil.makeOrderNum("V");
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("orderSerialNo",orderSerialNo);
		Criteria criteria = new Criteria();
    	criteria.put("configKey",openType);
		Double payMoney =Double.valueOf(tbSystemConfigService.selectByObject(criteria).get(0).getConfigValue()) ;
		//根据店铺id查询
		TbShopInfo tbShopInfo = tbShopInfoService.selectByPrimaryKey(myOrderForm.getShopId());
		//判断是否已经开通了上架货品平台结算权限(如果开通需要减去上架的货品的支付钱)
		payMoney = tbShopInfoService.subtractSettlementMoney(tbShopInfo, payMoney);
		param.put("payMoney",payMoney);
		param.put("accountId",getAccountId());
		param.put("payment", HglContants.SHOP_MEDAL_AGENT.equals(openType) ? "payBackGetMedalAgentImpl" : "payBackGetSettlementImpl");
		logger.debug("微信 微信扫码支付购买结算权限..................."+param.toString());
		//1：微信扫码支付
		paymentHandler.goToPayment(param, "2");
		model.addAttribute("orderSerialNo",orderSerialNo);
		model.addAttribute("payMoney",payMoney);
		return "webuser/nativePay";
	}
	
	@RequestMapping("/showWeiXinPay")
    public String showWeiXinPay(ModelMap model,String imgName,HttpServletResponse response) {
	 	String imgPath = HglContants.WECHATCODE_PATH;
	 	logger.debug("微信 微信扫码支付二维码路径..................."+imgPath+imgName);
    	ImageUtil.showImageCommon(imgPath,imgName,response);
        return null;
    }

	/**
	 * 修改店铺头像（首张图片）
	 * @param request
	 * @param model
	 * @return
	 * @author zss
	 */
	@RequestMapping(value="/updateShopImage")
    public String updateShopImage(HttpServletRequest request ,Model model){
		   MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		/*   CommonsMultipartFile imgFile = (CommonsMultipartFile) multipartRequest
				     .getFile("input");// 表单中对应的文件名；*/
	MultipartFile imgFile = multipartRequest.getFile("image");
  	  String imgName = imgFile.getOriginalFilename();
  	  long imgSize = imgFile.getSize();
  	  if(imgSize > 5242880){
 		return "redirect:/shop/shopManagement";
 	  }else{
	  	  String newName = StringUtil.changeFileName("shopImage1",imgName);
	  	TbShopInfo shopInfo = tbShopInfoService.selectByPrimaryKey(this.getLoginUser().getShopId());
	  	if(shopInfo!=null){
	  	  try{
				tbShopInfoService.uploadShopInfoImage(imgFile, newName, String.valueOf(shopInfo.getId()));
				shopInfo.setShopImage1(newName);
				tbShopInfoService.updateByPrimaryKey(shopInfo);
				
				//取场景图片地址，生成二维码
				String shopImageUrl = HglContants.SHOP_INFO_PATH+shopInfo.getId()+File.separator+newName;
				QRCodeUtil.createShopQrCode(shopInfo.getId(),shopImageUrl);//店铺二维码
				QRCodeUtil.createLogoCodeImage(this.getLoginUser().getRecommendShopId(),this.getLoginUser().getLogoCode(),shopImageUrl);//推荐二维码
	  	  }catch(Exception e){
	  		  logger.debug("文件上传失败.....");
	  		  e.printStackTrace();
	  	  }
	  
	  	}
 	  }
	  	
  	 return "redirect:/shop/shopManagement";
    }
	
}
