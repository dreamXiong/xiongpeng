package com.linkon.admin.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liguo.hgl.proxydao.model.OrderGroupDetailDto;
import com.liguo.hgl.proxydao.model.TbOrderDetail;
import com.liguo.hgl.proxydao.model.TbOrderGroup;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.TbOrderDetailService;
import com.liguo.hgl.service.TbOrderGroupService;

@Controller
@RequestMapping("orderDetail")
public class OrderDetailController extends IBaseController {

	@Autowired
	private TbOrderDetailService orderDetailService;
	
	@Autowired
	private TbOrderGroupService orderGroupService;
	
	@RequestMapping(value="/doInitUpdateOrderDetailPrice",produces="text/html;charset=utf-8")
	@ResponseBody
	public String doInitUpdataOrderDetailPrice(Integer id){
		String strJson = "";
		TbOrderDetail orderDetail = orderDetailService.selectOrderDetailDto(id);
		ObjectMapper mapper = new ObjectMapper();
		try {
			strJson = mapper.writeValueAsString(orderDetail);
		} catch (IOException e) {			
			e.printStackTrace();
		}
		
		return strJson;		
	}
	
	@RequestMapping("/doUpdateOrderDetailPrice")
	@ResponseBody
	public String doUpdateOrderDetailPrice(Integer id,Double price){
		TbOrderDetail orderDetail = orderDetailService.selectByPrimaryKey(id);
		if(orderDetail!=null){
			orderDetail.setId(id);
			orderDetail.setBuyPrice(price*orderDetail.getBuyNum());
			orderDetail.setPrice(price);
			if(orderDetailService.updateByPrimaryKey(orderDetail)==1){
				//重新计算总价
				Integer orderGroupId = orderDetail.getOrderGroupId();
				OrderGroupDetailDto orderGroupDetailDto = orderDetailService.selecOrderDetailTotalAmount(orderGroupId);
				TbOrderGroup orderGroup = orderGroupService.selectByPrimaryKey(orderGroupId);
				if(orderGroup!=null){
					orderGroup.setPayMoney(orderGroupDetailDto.getTotalAmount());
					orderGroup.setTotalMoney(orderGroupDetailDto.getTotalAmount());
					orderGroupService.updateByPrimaryKeySelective(orderGroup);
				}
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
