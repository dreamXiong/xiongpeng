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
import org.springframework.web.bind.annotation.ResponseBody;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.dto.ManualOrderDetailDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.OrderGroupDto;
import com.liguo.hgl.proxydao.model.TbManualorderDetail;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.TbManualorderDetailService;
import com.liguo.hgl.service.TbOrderGroupService;


@Controller
@RequestMapping("manualOrder")
public class ManualOrderController extends IBaseController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private static final String MANUALORDER="ordermanual/ordermanual";
	
	private static final String MANUALORDERLIST="ordermanual/ordermanuallist";
	
	private static final String MANUALORDERUPD="ordermanual/manualupd";
	
	private static final String MANUALHISTORY="ordermanual/manualhistory";
	
	private static final String MANUALHISTORYLIST="ordermanual/manualhistorylist";
	
	private static final Integer VERSION = 1;  //初始版本号
	
	@Autowired
	private TbOrderGroupService orderGroupService;
	
	@Autowired
	private TbManualorderDetailService manualOrderDetailService;
	
	
	@RequestMapping("/doInitOrderGroup")
	private String doInitOrderGroup(PageDto page,ModelMap model){
		model.addAttribute(HglContants.PAGE_DTO_ID, page);		
		return MANUALORDER;
	}
	
	@RequestMapping("/doSearchResult")
	private String doSearchResult(ManualOrderDetailDto orderDetailDto,PageDto page,ModelMap model){
	
			Criteria criteria = new Criteria();
			criteria.put("orderSerialNo", orderDetailDto.getOrderSerialNo());
			criteria.put("userName", orderDetailDto.getUserName());
				
			List<OrderGroupDto> list = orderGroupService.selectObjectByPage(criteria, page);
			model.addAttribute("orderGroupDto", list);
			model.addAttribute(HglContants.PAGE_DTO_ID, page);
					 
		return MANUALORDERLIST;
	}
	
	@RequestMapping("/doUpdateManualOrder")
	@ResponseBody
	public String doUpdateManualOrder(Integer orderId,Integer status){
		OrderGroupDto orderGroupDto = orderGroupService.selectObjectByPrimaryKey(orderId);
		if(orderGroupDto!=null){
			orderGroupDto.setOrderStatus(HglContants.ORDER_STATE_220);
			if(orderGroupService.updateByPrimaryKeySelective(orderGroupDto)==1){
				TbManualorderDetail manualOrder = new TbManualorderDetail();
				manualOrder.setOrderId(orderGroupDto.getId());
				manualOrder.setBuyId(orderGroupDto.getUserName());		
				manualOrder.setBuyDt(orderGroupDto.getCreateDt());
				manualOrder.setMobile(orderGroupDto.getMobile());
				manualOrder.setPayAmount(orderGroupDto.getPayMoney());
				manualOrder.setStatusBefore(status);
				manualOrder.setStatusAfter(HglContants.ORDER_STATE_220);
				manualOrder.setOperateDt(System.currentTimeMillis());
				manualOrder.setOperateBy(this.getLoginUser().getUserName());
				manualOrder.setVersion(VERSION);
				manualOrderDetailService.insertSelective(manualOrder);
				return String.valueOf(true);
			}			
		}		
		return String.valueOf(false);
	}
	
	/*手动补单记录*/
	@RequestMapping("/doInitManualOrderRecord")
	public String doInitManualOrderRecord(PageDto page,ModelMap model){
		Criteria criteria = new Criteria();
		criteria.setOrderByClause("operate_dt desc");
		List<ManualOrderDetailDto> list =manualOrderDetailService.selectObjectByPage(criteria, page);
		model.addAttribute("manualOrderDtos", list);
		model.addAttribute(HglContants.PAGE_DTO_ID, page);
		return MANUALHISTORY;
	}
	
	@RequestMapping("/doSearchManualOrderResult")
	public String doSearchManualOrderResult(PageDto page,ManualOrderDetailDto detailDto,ModelMap model){
		Criteria criteria = new Criteria();
		criteria.put("orderSerialNo", detailDto.getOrderSerialNo());
		criteria.put("buyId", detailDto.getUserName());
		criteria.put("operateBy", detailDto.getOperateBy());
		criteria.setOrderByClause("operate_dt desc");
		List<ManualOrderDetailDto> list = manualOrderDetailService.selectObjectByPage(criteria, page);
		
		model.addAttribute("manualOrderDtos", list);
		model.addAttribute(HglContants.PAGE_DTO_ID, page);
		
		return MANUALHISTORYLIST;
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
