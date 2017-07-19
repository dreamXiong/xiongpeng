package com.linkon.hgl.web.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.dto.WithdrawalsDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.TbWithdrawalsService;

@RequestMapping("withdrawals")
@Controller
public class WithrawalsController extends IBaseController{
	
	@Autowired
	private TbWithdrawalsService tbWithdrawalsService;
	
	
	@Override
	protected void init(PageDto page, HttpServletRequest request,HttpServletResponse response, Model model) {
		if(StringUtils.isEmpty(getShopId())){
			return;
		}
    /*	Criteria criteria = new Criteria();*/
    	//查询店铺记录
    	selecttbWithdrawalsInfo(page,model,new Criteria());
	}
	
	@RequestMapping(value="/searchResult")
    public String getSearchResult(String userName,PageDto page,Model model){
    	Criteria criteria = new Criteria();
		if (!StringUtils.isEmpty(userName)) {
			criteria.put("userName", userName.trim());
		}
	/*	if(!StringUtils.isEmpty(request.getParameter("name"))){
		criteria.put("name",  Integer.parseInt(request.getParameter("name").trim()));
		}
		if(!StringUtils.isEmpty(request.getParameter("state"))){
			criteria.put("state",  Integer.parseInt(request.getParameter("state")));
			}*/
    	//查询平台结算
		selecttbWithdrawalsInfo(page,model,criteria);
        return "withrawals/withrawalsList";
    }
	/**
	 * 审核
	 * @param id
	 * @return
	 * @author zk
	 */
	@RequestMapping(value="/auditWithrawals")
	public String auditWithrawals(Integer id,String userName,PageDto page,Model model){
		tbWithdrawalsService.auditWithrawals(id,getLoginUserId());
		Criteria criteria = new Criteria();
		if (!StringUtils.isEmpty(userName)) {
			criteria.put("userName", userName.trim());
		}
		selecttbWithdrawalsInfo(page,model,criteria);
		return "withrawals/withrawalsList";
	}
	
	/**
	 * 审核拒绝
	 * @param id
	 * @return
	 * @author zk
	 */
	@RequestMapping(value="/refuseAutomatic")
	public String refuseAutomaticShow(Integer id,String userName,PageDto page,Model model){
		Criteria criteria = new Criteria();
		if (!StringUtils.isEmpty(userName)) {
			criteria.put("userName", userName.trim());
		}
		tbWithdrawalsService.refuseAutomaticShow(id,getLoginUserId());
		selecttbWithdrawalsInfo(page,model,criteria);
		return "withrawals/withrawalsList";
	}
	@Override
	public String doSearchResult() {
		
		return null;
	}
	
	private void selecttbWithdrawalsInfo(PageDto page,Model model,Criteria criteria){
		criteria.put("auditPerson", 2);
    	criteria.put("shopId", getShopId());
    	List<WithdrawalsDto> withdrawalsDtos = tbWithdrawalsService.selectWithdrawalsPage(criteria,page);
    	model.addAttribute("withdrawalsDtos", withdrawalsDtos);
    	model.addAttribute(HglContants.PAGE_DTO_ID, page);
	}

}
