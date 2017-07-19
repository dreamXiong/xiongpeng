package com.linkon.admin.action;

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

@RequestMapping("withdrawal")
@Controller
public class WithrawalsController extends IBaseController{
	
	@Autowired
	private TbWithdrawalsService tbWithdrawalsService;
	
	
	@Override
	protected void init(PageDto page, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		/*List<WithdrawalsDto> withdrawalsDtos = tbWithdrawalsService.selectWithdrawalsPage(new Criteria(),page );
		model.addAttribute("withdrawalsDtos", withdrawalsDtos);
		model.addAttribute(HglContants.PAGE_DTO_ID, page);*/
		
	}
	
	/**
	 * 查询用户提现列表
	 * @param page
	 * @return
	 * @author zss
	 */
	 @RequestMapping(value="/withdrawals")
     public ModelAndView getSuppliers(PageDto page){
    	ModelAndView modelAndView = new ModelAndView();
    	Criteria criteria = new Criteria();
    	//查询平台结算
    	criteria.put("auditPerson", 1);
    	List<WithdrawalsDto> withdrawalsDtos = tbWithdrawalsService.selectWithdrawalsPage(criteria,page);
    	modelAndView.addObject("withdrawalsDtos", withdrawalsDtos);
    	modelAndView.addObject(HglContants.PAGE_DTO_ID, page);
    	modelAndView.setViewName("withrawals/withrawals");
        return modelAndView;
     }
	/**
	 * 按条件查询---分页
	 * @param request
	 * @param page
	 * @return
	 * @author zss
	 */
	@RequestMapping(value="/searchResult")
    @ResponseBody
    public ModelAndView getSearchResult(HttpServletRequest request,PageDto page){
    	Criteria criteria = new Criteria();
		String userName = request.getParameter("userName").trim();
		if (!StringUtils.isEmpty(userName)) {
			criteria.put("userName", userName);
		}
		if(!StringUtils.isEmpty(request.getParameter("name"))){
		criteria.put("name",  request.getParameter("name"));
		}
		if(!StringUtils.isEmpty(request.getParameter("state"))){
			criteria.put("state",  Integer.parseInt(request.getParameter("state")));
			}
    	ModelAndView modelAndView = new ModelAndView();
    	//查询平台结算
    	criteria.put("auditPerson", 1);
    	List<WithdrawalsDto> withdrawalsDtos = tbWithdrawalsService.selectWithdrawalsPage(criteria,page);
    	modelAndView.addObject("withdrawalsDtos", withdrawalsDtos);
    	modelAndView.addObject(HglContants.PAGE_DTO_ID, page);
    	modelAndView.setViewName("withrawals/withrawalsList");
        return modelAndView;
    }
	/**
	 * 审核
	 * @param id
	 * @return
	 * @author zss
	 */
	@RequestMapping(value="/auditWithrawals")
    @ResponseBody
	public Map<String,Object> auditWithrawals(Integer id){
		Map<String,Object> map = tbWithdrawalsService.auditWithrawals(id,getLoginUserId());
		return map;
	}
	
	/**
	 * 审核拒绝
	 * @param id
	 * @return
	 * @author zss
	 */
	@RequestMapping(value="/refuseAutomaticShow")
    @ResponseBody
	public Map<String,Object> refuseAutomaticShow(Integer id){
		Map<String,Object> map = tbWithdrawalsService.refuseAutomaticShow(id,getLoginUserId());
		return map;
	}
	@Override
	public String doSearchResult() {
		
		return null;
	}

}
