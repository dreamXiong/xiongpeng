package com.linkon.admin.action;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.dto.MyOrderForm;
import com.liguo.hgl.proxydao.model.MyOrderStatesCount;
import com.liguo.hgl.proxydao.model.OrderGroupDto;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.TbOrderGroupService;
import com.liguo.hgl.service.TbProductService;
import com.liguo.hgl.service.TbWarehouseService;
import com.liguo.hgl.service.TbWarehouseUserService;

@Controller
@RequestMapping("sendOutGoods")
public class SendOutGoodsController extends IBaseController {
	
	@Autowired
	protected TbOrderGroupService tbOrderGroupService;
	
	@Autowired
	protected TbWarehouseUserService tbWarehouseUserService;
	@Override
	protected void init(PageDto page, HttpServletRequest request,HttpServletResponse response, Model model) {
		MyOrderForm myOrderForm = new MyOrderForm();
		myOrderForm.setEndTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis())));
		Calendar c = Calendar.getInstance();
	    c.add(Calendar.MONTH, -1);
	    myOrderForm.setStartTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date(c.getTimeInMillis())));
	     
	    List<Integer> wIdList = tbWarehouseUserService.selectOrderByWarehouseIds(getLoginUserId());
		List<OrderGroupDto> dtoList =  tbOrderGroupService.selectOrderListByWIds(myOrderForm,page,wIdList);
		
		model.addAttribute("dtoList", dtoList);
		model.addAttribute(HglContants.PAGE_DTO_ID, page);
	}
	 @RequestMapping(value = "/serachOrderGroup")
	public String searchResult(MyOrderForm myOrderForm ,Model model,PageDto page) {
		 List<Integer> wIdList = tbWarehouseUserService.selectOrderByWarehouseIds(getLoginUserId());
		 List<OrderGroupDto> dtoList = tbOrderGroupService.selectOrderListByWIds(myOrderForm,page,wIdList);
		 model.addAttribute("dtoList", dtoList);
		 model.addAttribute(HglContants.PAGE_DTO_ID, page);
		 return "sendOutGoods/sendOutGoodsList";
	}
	@Override
	public String doSearchResult() {
		
		return null;
	}
	@RequestMapping(value = "/sendOutGoods")
	public String sendOutGoods(MyOrderForm myOrderForm,Model model,PageDto page){
		 Integer userId = this.getLoginUserId();
		 tbOrderGroupService.sendOutGoods(myOrderForm,userId);
		 List<Integer> wIdList = tbWarehouseUserService.selectOrderByWarehouseIds(getLoginUserId());
		 List<OrderGroupDto> dtoList = tbOrderGroupService.selectOrderListByWIds(myOrderForm,page,wIdList);
		 model.addAttribute("dtoList", dtoList);
		 return "sendOutGoods/sendOutGoodsList";
	}

}
