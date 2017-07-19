package com.linkon.admin.action;

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

import com.liguo.hgl.proxydao.dto.OrderTrackingDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.OrderGroupDto;
import com.liguo.hgl.proxydao.model.TbAddressHistory;
import com.liguo.hgl.proxydao.model.TbOrderGroup;
import com.liguo.hgl.proxydao.model.TbOrderTracking;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.TbAddressHistoryService;
import com.liguo.hgl.service.TbOrderDetailService;
import com.liguo.hgl.service.TbOrderGroupService;
import com.liguo.hgl.service.TbOrderTrackingService;
import com.liguo.hgl.util.ImageUtil;

@Controller
@RequestMapping("orderTracking")
public class OrderTackingController extends IBaseController {

private static Logger logger = LoggerFactory.getLogger(OrderTackingController.class);
	
	@Autowired
	private TbOrderTrackingService orderTrackingService;
	
	@Autowired
	private TbOrderGroupService orderGroupService;
	
	@Autowired
	private TbAddressHistoryService addressHistoryService;
	
	@Autowired
	private TbOrderDetailService orderDetailService;
	
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
}
