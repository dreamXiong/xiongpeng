package com.linkon.hgl.web.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.dto.Attribute;
import com.liguo.hgl.proxydao.dto.WapOrderServiceDetailDto;
import com.liguo.hgl.proxydao.dto.WapOrderServiceDto;
import com.liguo.hgl.proxydao.model.Criteria;

import com.liguo.hgl.proxydao.model.TbCustomerService;
import com.liguo.hgl.proxydao.model.TbUserInfo;
import com.liguo.hgl.proxydao.model.TbWapAddressHistory;
import com.liguo.hgl.proxydao.model.TbWapOrderServiceDetail;
import com.liguo.hgl.proxydao.model.TbWapOrderTrackingService;
import com.liguo.hgl.proxydao.model.TbWebUser;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.proxydao.util.StringUtil;
import com.liguo.hgl.service.TbCustomerServiceService;
import com.liguo.hgl.service.TbUserInfoService;
import com.liguo.hgl.service.TbWapAddressHistoryService;
import com.liguo.hgl.service.TbWapOrderServiceDetailService;
import com.liguo.hgl.service.TbWapOrderServiceService;
import com.liguo.hgl.service.TbWapOrderTrackingServiceService;
import com.liguo.hgl.service.TbWebUserService;
import com.liguo.hgl.util.ImageUtil;

@RequestMapping("orderTrackingService")
@Controller
public class OrderTrackingServiceController extends IBaseController {
	
	@Autowired
	private TbWapOrderTrackingServiceService orderTrackingServiceService;
	
	@Autowired
	private com.liguo.hgl.service.TbWapOrderTrackingService orderTrackingService;
	
	@Autowired
	private TbWapOrderServiceService orderServiceService;
	
	@Autowired
	private TbCustomerServiceService customerServiceService;
	
	@Autowired
	private TbWapAddressHistoryService addressHistoryService;
		
	@Autowired
	private TbWebUserService webUserService;
		
	@Autowired
	private TbUserInfoService userInfoService;
	
	@Autowired
	private TbWapOrderServiceDetailService orderServiceDetailService;
	
	@RequestMapping("/doSearchReasult")
	public String doSearchReasult(@RequestParam(value="orderServiceId",required=true,defaultValue="1")Integer orderServiceId,ModelMap model){
		//remove师傅推送材料session
		this.session.removeAttribute(HglContants.ORDER_SERVICE_ID);
									
		//查询服务订单	
		WapOrderServiceDto orderService = orderServiceService.selectById(orderServiceId);
		model.addAttribute("orderService", orderService);
		
		if(this.getLoginUser().getTypeId().equals(HglContants.USER_TYPE_MASTER)){    //如果当前登录用户是师傅			
			model.addAttribute("userType", HglContants.USER_TYPE_MASTER);
		}else if(this.getLoginUser().getTypeId().equals(HglContants.USER_TYPE_CUS)){  //如果当前登录用户是客户
			model.addAttribute("userType", HglContants.USER_TYPE_CUS);
		}
				
		//查询当前登录用户
		TbUserInfo userInfo = userInfoService.selectByPrimaryKey(this.getUserinfoId());
		model.addAttribute("userInfo", userInfo);
		model.addAttribute("webUser", this.getLoginUser());
		
		//服务项目
		TbCustomerService serviceType = new TbCustomerService();
		if(orderService!=null){
			 serviceType = customerServiceService.selectByPrimaryKey(orderService.getServiceTypeId());
			 
			/*验证订单是否是由师傅发出*/
			Criteria criteria = new Criteria();
			criteria.put("userName", orderService.getUserName());
			TbWebUser webUser = webUserService.selectByUserName(criteria);
			if(webUser!=null && webUser.getTypeId().equals(HglContants.USER_TYPE_MASTER)){
				model.addAttribute("master", webUser);
			}else{
				model.addAttribute("master", null);
			}
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
			
		return "orderTrackingService/orderTrackService";
	}
	
	
	/*显示头像*/
	@RequestMapping("/showImage")
	public String showImage(Integer userInfoId,String url,HttpServletResponse response){
		
		ImageUtil.showImage("worker",String.valueOf(userInfoId)+File.separator+url, response);

		return null;
	}
	
	
	/*显示产品图片*/
	@RequestMapping("/showProductImage")
	public String showProductImage(Integer id,String imgName,HttpServletResponse response){
		ImageUtil.showImageWapProduct(id, imgName, response);
		return null;
	}
	
	/*用户删除材料*/
	@RequestMapping("/doDeleteOrderServiceDetail")
	@ResponseBody
	public String doDeleteOrderServiceDetail(Integer id){
		TbWapOrderServiceDetail orderServiceDetail = orderServiceDetailService.selectByPrimaryKey(id);
		if(orderServiceDetail!=null){
			if(orderServiceDetailService.deleteByPrimaryKey(id)==1){
				return String.valueOf(true);
			}else{
				return String.valueOf(false);
			}
		}
		return String.valueOf(false);
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

}
