package com.linkon.hgl.web.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.dto.BaiduMapDto;
import com.liguo.hgl.proxydao.dto.SubmitServiceOrderForm;
import com.liguo.hgl.proxydao.dto.WapOrderServiceDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbCustomerService;
import com.liguo.hgl.proxydao.model.TbOpenedRegional;
import com.liguo.hgl.proxydao.model.TbWapAddress;
import com.liguo.hgl.proxydao.model.TbWapAddressHistory;
import com.liguo.hgl.proxydao.model.TbWapOrderService;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.proxydao.util.BaiduMapUtil;
import com.liguo.hgl.proxydao.util.GaoDeMapUtil;
import com.liguo.hgl.service.TbCustomerServiceService;
import com.liguo.hgl.service.TbOpenedRegionalService;
import com.liguo.hgl.service.TbWapAddressHistoryService;
import com.liguo.hgl.service.TbWapAddressService;
import com.liguo.hgl.service.TbWapOrderServiceDetailService;
import com.liguo.hgl.service.TbWapOrderServiceService;

@Controller
@RequestMapping("wapOrderService")
public class WapOrderServiceController extends IBaseController {
	
	@Autowired
	protected TbWapOrderServiceService tbWapOrderServiceService;
	
	@Autowired
	protected TbWapAddressService tbWapAddressService;
	
	@Autowired
	protected TbCustomerServiceService tbCustomerServiceService;
	
	@Autowired
	protected TbWapOrderServiceDetailService tbWapOrderServiceDetailService;
	
	@Autowired
	protected TbWapAddressHistoryService tbWapAddressHistoryService;
	
	@Autowired
	protected MyAddressController myAddressController;
	
	@Autowired
	protected TbOpenedRegionalService tbOpenedRegionalService;
	
	/**
	 * 提交订单
	 * @param submitServiceOrderForm 提交服务订单的参数
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/submitOrder", method = RequestMethod.POST)
	public String submitOrder(SubmitServiceOrderForm submitServiceOrderForm,Model model) {
		String resultUrl = "redirect:/wapOrderService/failureSubmitOrder";
		boolean isSuccess = false;
		try {
			if(submitServiceOrderForm != null){
				//校验用户选择的日期是否在3天以内,如果跳转返回错误页面
				Integer[] appointments = submitServiceOrderForm.getAppointments();
				Integer appoint = appointments[0];
				Integer appointPeriod = appointments[1];
				if((appoint <=0 && appoint >3) || (appointPeriod <=0 && appointPeriod >3)){
					return resultUrl;
				}
				//把用户选择的天数,转换为日期
				getDayMillis(appoint,appointPeriod,submitServiceOrderForm);
				//执行业务方法,返回成功或失败
				isSuccess = tbWapOrderServiceService.submitOrder(submitServiceOrderForm,this.getLoginUserId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		if(isSuccess){
			resultUrl = "redirect:/wapOrderService/successSubmitOrder";
		}
		return resultUrl;
	}
	
	/**
	 * 成功提交订单页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/successSubmitOrder")
	public String successSubmitOrder(Model model) {
		return "orderService/successOrderService";
	}
	
	/**
	 * 失败提交订单页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/failureSubmitOrder")
	public String failureSubmitOrder(Model model) {
		return "orderService/failureOrderService";
	}

	@Override
	protected void init(PageDto page, HttpServletRequest request,HttpServletResponse response, Model model) {
	}
	
    /**
     * 显示出我的收货地址
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/showMyAddress", method = RequestMethod.POST)
    public String showMyAddress(HttpServletRequest request,HttpServletResponse response,Model model) throws Exception {
    	//服务地址查询列表
    	Criteria criteria = new Criteria();
		criteria.put("userId", this.getLoginUserId());
		List<TbWapAddress> addressList = tbWapAddressService.selectByObject(criteria);
		if(addressList != null && addressList.size()>0){
			model.addAttribute("addressList", addressList);
			return "orderService/addressList";
		}
		return "myAddress/AddLocationMyAddress";
    }
    
    /**
     * 添加收货地址
     * @param address 地址对象
     * @param serviceTypeId 服务类型ID
     * @param updateServiceId 服务ID
     * @return
     * @throws Exception 
     */
    @RequestMapping(value = "/addAddress", method = RequestMethod.POST)
    public String addAddress(TbWapAddress address,Integer serviceTypeId,HttpServletRequest request,HttpServletResponse response,
    		@RequestParam(required=false)Integer updateServiceId,Model model) throws Exception {
    	myAddressController.addAddress(address,model);
    	if(updateServiceId!=null && updateServiceId >0){
    		goUpdateService(updateServiceId,address.getId(),model);
    		return "orderService/updateService";
    	}else{
    		goAppointmentPage(serviceTypeId,address.getId(),model);
    	}
    	return "orderService/orderService";
    }
    
	/**
	 * 跳转到立即预约页面
	 * @param id  客户服务类型ID
	 * @param addressId 收货地址ID
	 * @param model
	 * @return
	 */
	@RequestMapping("goAppointmentPage")
	public String goAppointmentPage(Integer id,Integer addressId,Model model){
		//根据id查询出分类
		TbCustomerService serviceType = tbCustomerServiceService.selectByPrimaryKey(id);
		if(serviceType != null){
		   	//查询出该用户的默认地址
			Criteria criteria = new Criteria();
			if(addressId != null){
				criteria.put("id", addressId);
			}else{
				criteria.put("checkFlag", 0);
			}
			criteria.put("userId", this.getLoginUserId());
			TbWapAddress wapAddress = tbWapAddressService.selectByDefaultObject(criteria);
			if(wapAddress != null){
				model.addAttribute("wapAddress", wapAddress);
			}
			model.addAttribute("serviceType", serviceType);
			model.addAttribute("dateOne", getDay(0));
			model.addAttribute("dateTwo", getDay(1));
			model.addAttribute("dateThree", getDay(2));
			model.addAttribute("hour", getHour());
		}
		return "orderService/orderService";
	}
	
	@Override
	public String doSearchResult() {
		return null;
	}
	
	/**
	 * 根据选择的几个获取日期
	 * @param amount (1,2,3)获取相对于的日期
	 * @return
	 */
	public String getDay(int amount){
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, amount);
		return new SimpleDateFormat("yyyy.MM.dd").format(c.getTime());
	}
	
	/**
	 * 根据选择的时间段获取时间
	 * @param amount (1,2,3)获取相对于的日期
	 * @param num (1,2,3)获取对应的时间段
	 * @param submitServiceOrderForm
	 */
	public void getDayMillis(int amount,int num,SubmitServiceOrderForm submitServiceOrderForm){
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, amount-1);
		String[] period = {"08:00-12:00", "12:00-19:00", "19:00-23:00"};
		submitServiceOrderForm.setAppointmentPeriod(period[num-1]);
		submitServiceOrderForm.setAppointment(c.getTimeInMillis());
	}
	
	/**
	 * 得到当前时,判断超过了那个时段不能勾选
	 * @return
	 */
	public static int getHour(){
		Calendar c = Calendar.getInstance();
		int hour = c.get(Calendar.HOUR_OF_DAY);
		if(hour >= 23){
			return 3;
		}
		if(hour >= 19){
			return 2;
		}
		if(hour >= 12){
			return 1;
		}
		return 0;
	}
	
	/**
	 * 根据日期获取星期几
	 * @param cal
	 * @return
	 */
	public static String getWeekOfDate(Calendar cal) {
		String[] weekDays = {"日", "一", "二", "三", "四", "五", "六"};
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0){
			w = 0;
		}
		return weekDays[w];
	}
	
	/**
	 * 推动材料,跳转到选购页面
	 * @param orderServiceId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/pushShoppingCart")
	public String pushShoppingCart(Integer orderServiceId,Model model){
		this.session.setAttribute(HglContants.ORDER_SERVICE_ID, orderServiceId);
		return "redirect:/pick/pickList";
	}
	
	/**
	 * 产品详情页面跳转,推送材料
	 * @param orderServiceId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/confirmPushCart")
	public String confirmPushCart(String[] inventoryListNum,Model model){
		//获取session中的服务订单ID推送
		Integer orderServiceId = (Integer)this.session.getAttribute(HglContants.ORDER_SERVICE_ID);
		if(orderServiceId != null){
			boolean isSuccess = tbWapOrderServiceDetailService.confirmPushCart(inventoryListNum,orderServiceId,this.getLoginUserId());
			if(isSuccess){
				
			}
		}
		//跳转服务订单详情页面
		return "redirect:/orderTrackingService/doSearchReasult?orderServiceId="+orderServiceId;
	}
	
	/**
	 * 下单前检查是否开放该城市
	 * **/
	@RequestMapping(value="/checkOpenCity")
	public @ResponseBody Map<String,Object> checkOpenCity(double lon,double lat,Model model){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("isOpenCiry", false);
		try {
			BaiduMapDto d =	GaoDeMapUtil.selectAddBycoordinate(lon,lat);
			String city = !"isBlank".equals(d.getRegeocode().getAddressComponent().getCity()) ? d.getRegeocode().getAddressComponent().getCity() : d.getRegeocode().getAddressComponent().getProvince();
			if(StringUtils.isNotBlank(city)){
				Criteria criteria = new Criteria();
				criteria.put("name", city);
				TbOpenedRegional openedRegional = tbOpenedRegionalService.selectByNameOpenCity(criteria);
				if(openedRegional != null){
					map.put("cityCode",openedRegional.getOpenCity());
					map.put("isOpenCiry", true);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 买家确认推送到自己的购物车
	 * @param orderListNum 推送的库存=数量
	 * @param orderServiceId 服务订单ID
	 * @param repairmanId 收货人ID
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/confirmAddMyCart")
	public String confirmAddMyCart(String orderListNum[],Integer orderServiceId,Integer repairmanId,Model model){
		boolean isSuccess = tbWapOrderServiceDetailService.confirmAddMyCart(orderListNum,orderServiceId,repairmanId,this.getLoginUserId());
		if(isSuccess){
			
		}
		//跳转服务订单详情页面
		return "redirect:/orderTrackingService/doSearchReasult?orderServiceId="+orderServiceId;
	}
	
	/**
	 * 删除买家确认推送到自己的购物车
	 * @param orderServiceId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/deleteAddMyCart")
	public String deleteAddMyCart(Integer orderServiceDetailId,Integer orderServiceId,Model model){
		int delCount = tbWapOrderServiceDetailService.deleteByPrimaryKey(orderServiceDetailId);
		logger.info("删除买家确认推送到自己的购物车记录数: "+delCount);
		//跳转服务订单详情页面
		return "redirect:/orderTrackingService/doSearchReasult?orderServiceId="+orderServiceId;
	}
	
	/**
	 * 待服务
	 * @param model
	 * @return
	 * @author zss
	 */
	@RequestMapping("/service")
	public String getService(Model model,HttpServletRequest request){
		if(getUserinfoId()!=null && getLoginUser().getTypeId() == HglContants.USER_TYPE_MASTER){
			List<WapOrderServiceDto> orderServiceDtos= tbWapOrderServiceService.getService(getLoginUserId(),getUserinfoId(),getLon(),getLat());
			 model.addAttribute("orderServiceDtos",orderServiceDtos);
		}
		return "orderService/service";
	}
	
	/**
	 * 查询我的服务
	 * @param model
	 * @return
	 * @author zss
	 */
	@RequestMapping("/myService")
	public String getMyService(Model model,@RequestParam(required=false)Integer sfSercive){
		if(getLoginUserId()!=null){
			List<WapOrderServiceDto> orderServiceDtos= tbWapOrderServiceService.getMyService(getLoginUserId(),getLoginUser().getTypeId(),sfSercive);
			model.addAttribute("isCUS",getLoginUser().getTypeId().equals(HglContants.USER_TYPE_CUS));//是否是普通用户
			
			model.addAttribute("orderServiceDtos",orderServiceDtos);
		}
		return "personalCenter/myService";
	}
	
	/**
	 * 师傅接单
	 * **/
	@RequestMapping(value="/doMasterOrder")
    @ResponseBody
	public Map<String,Object> doMasterOrder(Model model,Integer id){
		Map<String,Object> map =null;
		if(id>0 && getLoginUserId()!=null){
			map = tbWapOrderServiceService.doMasterOrder(id,getLoginUserId());
		}
		return map;
	}
	/**
	 * 取消订单
	 * @param model
	 * @param id
	 * @return
	 * @author zss
	 */
	@RequestMapping(value="/doCancelOrder")
    @ResponseBody
	public Map<String,Object> doCancelOrder(Model model,Integer id){
		Map<String,Object> map =null;
		if(id>0 && getLoginUserId()!=null){
			map = tbWapOrderServiceService.doCancelOrder(id,getLoginUserId());
		}
		return map;
	}
	/**
	 * 得到修改订单页面
	 * @param id
	 * @param model
	 * @return
	 * @author zss
	 */
	@RequestMapping("goUpdateService")
	public String goUpdateService(Integer id,Integer addressId,Model model){
		WapOrderServiceDto orderServiceDto = tbWapOrderServiceService.selectById(id);
		if(orderServiceDto != null){
			Criteria criteria = new Criteria();
			if(addressId != null){
				criteria.put("id", addressId);
			}else{
				criteria.put("id", orderServiceDto.getAddressId());
			}
			criteria.put("userId", this.getLoginUserId());
			TbWapAddress wapAddress = tbWapAddressService.selectByDefaultObject(criteria);
			if(wapAddress != null){
				model.addAttribute("wapAddress", wapAddress);
			}
			model.addAttribute("orderServiceDto", orderServiceDto);
			model.addAttribute("dateOne", getDay(0));
			model.addAttribute("dateTwo", getDay(1));
			model.addAttribute("dateThree", getDay(2));
			model.addAttribute("hour", getHour());
		}
		return "orderService/updateService";
	}
	/**
	 * 提交修改订单信息
	 * @param submitServiceOrderForm
	 * @param model
	 * @return
	 * @author zss
	 */
	@RequestMapping(value = "/updateOrderService", method = RequestMethod.POST)
	public String submitOrderService(SubmitServiceOrderForm submitServiceOrderForm,Model model) {
		String resultUrl = "redirect:/wapOrderService/failureSubmitOrder";
		boolean isSuccess = false;
		try {
			if(submitServiceOrderForm != null){
				if(submitServiceOrderForm.getAppointments().length>0){
					Integer[] appointments = submitServiceOrderForm.getAppointments();
					Integer appoint = appointments[0];
					Integer appointPeriod = appointments[1];
					if((appoint <=0 && appoint >3) || (appointPeriod <=0 && appointPeriod >3)){
						return resultUrl;
					}
					getDayMillis(appoint,appointPeriod,submitServiceOrderForm);
				}
				isSuccess = tbWapOrderServiceService.updateOrderService(submitServiceOrderForm,this.getLoginUserId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(isSuccess){
			resultUrl = "redirect:/wapOrderService/successSubmitOrder";
		}
		return resultUrl;
	}
	
	/**
	 * 师傅开始服务，确认支付
	 * @param model
	 * @param id
	 * @return
	 * @author zss
	 */
	@RequestMapping(value="/doOrderService")
    @ResponseBody
	public Map<String,Object> doOrderService(Model model,Integer id){
		Map<String,Object> map =null;
		if(id>0 && getLoginUserId()!=null){
			map = tbWapOrderServiceService.doOrderService(id,getLoginUserId());
		}
		return map;
	}
	
	/**
	 * 师傅取消接单，服务挂起，恢复服务
	 * @param model
	 * @param id
	 * @return
	 * @author zss
	 */
	@RequestMapping(value="/doAltService")
    @ResponseBody
	public Map<String,Object> doAltService(Model model,Integer id){
		Map<String,Object> map =null;
		if(id>0 && getLoginUserId()!=null){
			map = tbWapOrderServiceService.doAltService(id,getLoginUserId());
		}
		return map;
	}
	
	/**
	 * 用户线下支付
	 * @param model
	 * @param id
	 * @return
	 * @author zss
	 */
	@RequestMapping(value="/doPayService")
    @ResponseBody
	public Map<String,Object> doPayService(Model model,Integer id){
		Map<String,Object> map =null;
		if(id>0 && getLoginUserId()!=null){
			map = tbWapOrderServiceService.doPayService(id,getLoginUserId());
		}
		return map;
	}
	/**
	 * 用户线上支付
	 * @param model
	 * @param id
	 * @return
	 * @author zss
	 */
	@RequestMapping(value="/doOnlinePayService")
    @ResponseBody
	public Map<String,Object> doOnlinePayService(Model model,Integer id){
		Map<String,Object> map =null;
		if(id>0 && getLoginUserId()!=null){
			map = tbWapOrderServiceService.doOnlinePayService(id,getLoginUserId());
		}
		return map;
	}
	
	
	/**
	 * 获取用户评价页面
	 * @param id
	 * @param typeId
	 * @param model
	 * @return
	 * @author zss
	 */
	@RequestMapping("goEvaluationService")
	public String goEvaluationService(Integer id,Integer typeId,Model model){
		if(typeId>0){
			TbCustomerService serviceType = tbCustomerServiceService.selectByPrimaryKey(typeId);
			model.addAttribute("serviceName", serviceType.getName());
			model.addAttribute("serciveId", id);
		}
		return "orderService/evaluationService";
	}
	/**
	 * 用户提交评论
	 * @param orderServiceId
	 * @param start
	 * @param evaluate
	 * @param model
	 * @return
	 * @author zss
	 */
	 @RequestMapping(value = "/saveEvaluate")
    public String saveEvaluate(Integer orderServiceId,String start,String evaluate,Model model){
	 tbWapOrderServiceService.saveEvaluate(orderServiceId,start,evaluate,getLoginUserId());
    	return "redirect:/wapOrderService/myService";
    }
	/**
	 * 获取师傅修改价格页面 
	 * @param id
	 * @param model
	 * @return
	 * @author zss
	 */
	 @RequestMapping("goServicePrice")
	public String goServicePrice(Integer id,Model model){
	  WapOrderServiceDto orderServiceDto = tbWapOrderServiceService.selectById(id);
		if(orderServiceDto != null){
			model.addAttribute("orderServiceDto", orderServiceDto);
		}
		return "orderService/servicePrice";
	}
	 /**
	  * 师傅完成服务，输入价格，改价
	  * @param model
	  * @param id
	  * @return
	  * @author zss
	  */
	 @RequestMapping(value="/doServicePrice")
	 public String doServicePrice(Model model,Integer id,String price){
		if(id>0 && getLoginUserId()!=null){
			tbWapOrderServiceService.doServicePrice(id,getLoginUserId(),price);
		}
		return "redirect:/wapOrderService/myService";
	 }
	 /**
	  * 校验服务最低价格
	  * @param id
	  * @param price
	  * @return
	  * @author zss
	  */
	 @RequestMapping(value="/checkCityLowerPrice")
	 @ResponseBody
	 public Map<String, Object> checkCityLowerPrice(Integer id,String price){
		 Map<String,Object> map= new HashMap<String, Object>();
		 if(id >0){
		  map = tbWapOrderServiceService.checkCityLowerPrice(id,price);
		 }
		 return map;
	 }
	 
	 /**
	  * 支付回调
	  * @param model
	  * @param id
	  * @param price
	  * @return
	  * @author zss
	  */
	 @RequestMapping(value="/onlinPayResult")
	 @ResponseBody
	 public Map<String,Object> onlinPayResult(@RequestParam String orderNumber){
		Map<String,Object> map = tbWapOrderServiceService.onlinPayResult(orderNumber);
		return map;
	 }
	 
	 @RequestMapping("/goServiceOnlinPay")
	 public String goServiceOnlinPay(Integer id,Model model){
		  WapOrderServiceDto orderServiceDto = tbWapOrderServiceService.selectById(id);
			if(orderServiceDto != null){
				model.addAttribute("orderServiceDto", orderServiceDto);
			}
			return "orderService/serviceOnlinPay";
	}
	 
	/**
	 * 客户查看师傅位置
	 * */
	@SuppressWarnings("unchecked")
	@RequestMapping("/masterAddressShow")
	public String masterAddressShow(Model model,HttpServletRequest request,Integer wapOrderId){
		if(wapOrderId == null){
			return null;
		}
		TbWapOrderService w = tbWapOrderServiceService.selectByPrimaryKey(wapOrderId);
		//订单提交时的坐标 
		TbWapAddressHistory address = tbWapAddressHistoryService.selectByPrimaryKey(w.getAddressId());
		//得到师傅的坐标 
		ServletContext s = request.getSession().getServletContext();
		Map<String,Map<String,String>> m = (Map<String,Map<String,String>>)s.getAttribute(HglContants.APPLICATION_KEY);
		Map<String,String> m1= m.get(w.getRepairmanId().toString()+"_"+wapOrderId);
		/*Map<String,String> m = (Map<String,String>)s.getAttribute(w.getRepairmanId().toString());*/
		if(m1 == null){
			return "redirect:/wapOrderService/myService";
		}
		
		String lon = (String)session.getAttribute(HglContants.LON);
		String lat = (String)session.getAttribute(HglContants.LAT);
		model.addAttribute("address",address);
		model.addAttribute("w",w);
		model.addAttribute("lon",lon);
		model.addAttribute("lat",lat);
		model.addAttribute("distance",BaiduMapUtil.GetShortDistance(Double.parseDouble(lon), Double.parseDouble(lat),address.getLon(),address.getLat()));
		return "orderService/masterAddress";
	}
	
	/**
	 * 客户端定时刷新师傅位置
	 * */
	@SuppressWarnings("unchecked")
	@RequestMapping("/masterAddressMapShow")
	@ResponseBody
	public Map<String,String> masterAddressMapShow(Model model,HttpServletRequest request,Integer repairmanId,String lat,String lon ,Integer orderId){
		Map<String,String > map = new HashMap<String,String >();
		double distance = 0.0;
		//得到师傅的坐标 
		ServletContext s = request.getSession().getServletContext();
		Map<String,Map<String,String>> m = (Map<String,Map<String,String>>)s.getAttribute(HglContants.APPLICATION_KEY);
		Map<String,String> m1= m.get(repairmanId.toString()+"_"+orderId.toString());
		if(m1 == null){
			return null;
		}
	    distance = BaiduMapUtil.GetShortDistance(Double.parseDouble(m1.get("lon")), Double.parseDouble(m1.get("lat")),Double.parseDouble(lon),Double.parseDouble(lat));
	    map.put("distance", distance+"");
	    map.put("lon",m1.get("lon"));
	    map.put("lat",m1.get("lat"));
		return map;
	}
	/**
	 * 师傅点击出门时开始导航
	 * */
	@SuppressWarnings("unchecked")
    @RequestMapping("/goCustomeraddress")
    @ResponseBody
    public Map<String,Object> goBeiZhan( HttpServletRequest request,Model model,Integer orderId) throws Exception{
    	//订单提交时的坐标 
		TbWapOrderService tbWapOrder = tbWapOrderServiceService.selectByPrimaryKey(orderId);
    	TbWapAddressHistory t = tbWapAddressHistoryService.selectByPrimaryKey(tbWapOrder.getAddressId());
    	Map<String,Object> map = new HashMap<String,Object>();
    	map.put("t",t);
    	BaiduMapDto g = GaoDeMapUtil.selectAddBycoordinate(Double.parseDouble((String)session.getAttribute(HglContants.LON)),Double.parseDouble((String)session.getAttribute(HglContants.LAT)));
    	map.put("g",g);
    	ServletContext s = request.getSession().getServletContext();
    	//第一次进来时取session的值，因为此时application里的值为空
		Map<String, Map<String,String> > address = new HashMap<String,Map<String,String>>();
		Map<String,String> mapR = new HashMap<String,String>();
		mapR.put("lon",(String)session.getAttribute(HglContants.LON));
		mapR.put("lat",(String)session.getAttribute(HglContants.LAT));
		address.put(tbWapOrder.getRepairmanId()+"_"+orderId,mapR);
		s.setAttribute(HglContants.APPLICATION_KEY, address);
    	return map;
    }
	/**
	 * 师傅出门服务时
	 * 定时刷新师傅位置
	 * */
	@RequestMapping(value="/findMasterAddress")
	public String findMasterAddress(Integer repairmanId,String lon,String lat,HttpServletRequest request,Integer orderId){
		Map<String,String> map = new HashMap<String,String>();
		map.put("lon", lon);
		map.put("lat", lat);
		logger.debug("lon="+lon+";lat="+lat);
		
		Map<String,Map<String,String>> m = new HashMap<String,Map<String,String>>();
		m.put(repairmanId.toString()+"_"+orderId, map);
		
		ServletContext s = request.getSession().getServletContext();
		Map<String,Map<String,String>> application = (Map<String,Map<String,String>>)s.getAttribute(HglContants.APPLICATION_KEY);
		if(application == null){
			s.setAttribute(HglContants.APPLICATION_KEY, m);
		}
		application.put(repairmanId.toString()+"_"+orderId, map);
		return null;
	}

	
	@RequestMapping(value="/doDeleteService")
    @ResponseBody
	public Map<String,Object> doDeleteService(Model model,Integer id){
		Map<String,Object> map =null;
		if(id>0 && getLoginUserId()!=null){
			map = tbWapOrderServiceService.doDeleteService(id,getLoginUserId());
		}
		return map;
	}
}
