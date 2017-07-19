package com.linkon.hgl.web.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.dto.Attribute;
import com.liguo.hgl.proxydao.dto.OrderTrackingDto;
import com.liguo.hgl.proxydao.dto.WapOrderServiceDetailDto;
import com.liguo.hgl.proxydao.dto.WapOrderServiceDto;
import com.liguo.hgl.proxydao.dto.WebUserDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.OrderGroupDto;
import com.liguo.hgl.proxydao.model.TbAddressHistory;
import com.liguo.hgl.proxydao.model.TbCustomerService;
import com.liguo.hgl.proxydao.model.TbOrderGroup;
import com.liguo.hgl.proxydao.model.TbOrderTracking;
import com.liguo.hgl.proxydao.model.TbUserInfo;
import com.liguo.hgl.proxydao.model.TbWapOrderTrackingService;
import com.liguo.hgl.proxydao.model.TbWebUser;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.proxydao.util.StringUtil;
import com.liguo.hgl.service.TbAddressHistoryService;
import com.liguo.hgl.service.TbCustomerServiceService;
import com.liguo.hgl.service.TbOrderDetailService;
import com.liguo.hgl.service.TbOrderGroupService;
import com.liguo.hgl.service.TbOrderTrackingService;
import com.liguo.hgl.service.TbUserInfoService;
import com.liguo.hgl.service.TbWapOrderServiceDetailService;
import com.liguo.hgl.service.TbWapOrderServiceService;
import com.liguo.hgl.service.TbWapOrderTrackingServiceService;
import com.liguo.hgl.service.TbWebUserService;
import com.liguo.hgl.util.ImageUtil;

@Controller
@RequestMapping("orderTracking")
public class OrderTrackingController extends IBaseController{
	
	private static Logger logger = LoggerFactory.getLogger(OrderTrackingController.class);
	
	@Autowired
	private TbOrderTrackingService orderTrackingService;
	
	@Autowired
	private TbOrderGroupService orderGroupService;
	
	@Autowired
	private TbAddressHistoryService addressHistoryService;
	
	@Autowired
	private TbOrderDetailService orderDetailService;
	
	@Autowired
    private TbWapOrderServiceService orderServiceService;
	
	@Autowired
    private TbWebUserService webUserService;
	
	@Autowired
    private TbUserInfoService userInfoService;
	
	@Autowired
    private TbCustomerServiceService customerServiceService;
	
	@Autowired
    private TbWapOrderServiceDetailService orderServiceDetailService;
	
	
	@Autowired
    private TbWapOrderTrackingServiceService orderTrackingServiceService;
	
	private static final String ORDERTRACKINGDETAIL="ordertracking/ordertracking";
	

	@RequestMapping(value="/doSearchOrderTracking")
	public String doSearchOrderTrancking(PageDto page,Integer orderGroupId,ModelMap model){	
		Criteria parameter = new Criteria();
		parameter.put("id", orderGroupId);
    	
		//商品详情
		logger.debug("商品信息,订单号："+orderGroupId);
    	List<OrderGroupDto> dtoList = orderGroupService.selectOrderGroupById(parameter,page);
		model.addAttribute("dtoList", dtoList);
		
		Criteria criteria = new Criteria();
		criteria.put("orderGroupId", orderGroupId);
		List<TbOrderTracking> list = orderTrackingService.selectByObjectStatus(criteria);
		model.addAttribute("orderTrackings", list);
		
		//订单跟踪
		logger.debug("订单跟踪,订单号："+orderGroupId);			
		criteria = new Criteria();
		criteria.put("orderGroupId", orderGroupId);
		criteria.setOrderByClause("operate_dt");
		List<OrderTrackingDto> orderTrackingDtos = orderTrackingService.selectByObjectDto(criteria);		
		model.addAttribute("orderTrackingDtos", orderTrackingDtos);
		
		//收货地址
		TbAddressHistory address = new TbAddressHistory();
		TbOrderGroup orderGroup = orderGroupService.selectByPrimaryKey(orderGroupId);
		if(orderGroup!=null){
			address = addressHistoryService.selectByPrimaryKey(orderGroup.getAddressId());
		}		
		model.addAttribute("address", address);
		
		return ORDERTRACKINGDETAIL;
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
	
	 @RequestMapping("/reissueImage")
	    public String generateImage(ModelMap model,Integer id,String imgName,HttpServletResponse response) {
	    	ImageUtil.showIReissueOderImage(id,imgName,response);
	        return null;
	    }
	 
	 @RequestMapping("/doSearchReasult")
	    public String doSearchReasult(@RequestParam(value="orderServiceId",required=true,defaultValue="1")Integer orderServiceId,ModelMap model){
	        //remove师傅推送材料session
	        this.session.removeAttribute(HglContants.ORDER_SERVICE_ID);
	                
	        //查询服务订单    
	        WapOrderServiceDto orderService = orderServiceService.selectById(orderServiceId);
	        model.addAttribute("orderService", orderService);
	        
	        //查询师傅信息
	        if(orderService!=null){
	            TbWebUser worker = webUserService.selectByPrimaryKey(orderService.getRepairmanId());
	            model.addAttribute("worker", worker);
	        }
	    
	        WebUserDto webUserDto =getLoginUser();
	        Integer userInfoId =webUserDto.getUserinfoId();
	        //查询当前登录用户
	        TbUserInfo userInfo = userInfoService.selectByPrimaryKey(userInfoId);
	        model.addAttribute("userInfo", userInfo);
	        model.addAttribute("webUser", this.getLoginUser());
	        
	        //服务项目
	        TbCustomerService serviceType = new TbCustomerService();
	        if(orderService!=null){
	             serviceType = customerServiceService.selectByPrimaryKey(orderService.getServiceTypeId());          
	        }
	        model.addAttribute("serviceType", serviceType);
	        
	            
	        //查询服务订单跟踪记录
	        Criteria criteria = new Criteria();
	        criteria.put("orderServiceId", orderServiceId);
	        criteria.setOrderByClause("operate_dt");
	        List<TbWapOrderTrackingService> list = orderTrackingServiceService.selectByObject(criteria);
	        model.addAttribute("orderTrackingServices", list);
	        
	        //查询订单材料信息
	        criteria = new Criteria();
	        criteria.put("orderServiceId", orderServiceId);
	        criteria.setOrderByClause("create_dt");
	        List<WapOrderServiceDetailDto> orderServiceDetailDtos = orderServiceDetailService.selectInventByObject(criteria);
	        
	        //查询购买材料属性
	        for(int i=0;i<orderServiceDetailDtos.size();i++){
	            WapOrderServiceDetailDto orderServiceDetailDto = orderServiceDetailDtos.get(i);
	            if(StringUtil.isNotBlank(orderServiceDetailDto.getAttributes())&& StringUtil.isNotBlank(orderServiceDetailDto.getProductInventory().getAttributesValues())){
	                List<String> attr =Arrays.asList(orderServiceDetailDto.getAttributes().split("/"));
	                List<String> attrValue = Arrays.asList(orderServiceDetailDto.getProductInventory().getAttributesValues().split(";"));
	                List<Attribute> attrs = new ArrayList<Attribute>();
	                for(int j=0;j<attr.size();j++){ 
	                    Attribute attribute = new Attribute();
	                    attribute.setAttrName(attr.get(j));
	                    attribute.setAttrValue(attrValue.get(j));
	                    attrs.add(attribute);
	                }
	                orderServiceDetailDto.setAttrs(attrs);
	                orderServiceDetailDtos.remove(i);
	                orderServiceDetailDtos.add(i, orderServiceDetailDto);
	            }                   
	        }
	                        
	        model.addAttribute("orderServiceDetails", orderServiceDetailDtos);
	        
	        return "ordertracking/orderTrackService";
	    }

}
