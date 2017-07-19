package com.linkon.admin.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.dto.TbCompanyConsultDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.TbCompanyConsultService;
/**
 * 类的功能描述:品牌列表<br>
 *
 * @author 周琨
 * @FileName BrandController.java<br>
 * @Language Java<br>
 * @date 2016-07-28<br>
 */
@Controller
@RequestMapping("companyConsult")
public class CompanyConsultController extends IBaseController{
	
	@Autowired
	protected TbCompanyConsultService tbCompanyConsultService;
	
	@Override
	protected void init(PageDto page, HttpServletRequest request,HttpServletResponse response, Model model) {
    	List<TbCompanyConsultDto> cList = tbCompanyConsultService.selectByObjectPage(new Criteria(),page);
    	model.addAttribute("cList",cList);
    	model.addAttribute(HglContants.PAGE_DTO_ID, page);
	}
	
	@RequestMapping("/seachInfo")
	public String doSearchResult(PageDto page, HttpServletRequest request,Model model) {
		Criteria criteria = new Criteria();
		criteria.put("companyName", request.getParameter("companyName"));
		List<TbCompanyConsultDto> cList = tbCompanyConsultService.selectByObjectPage(criteria,page);
    	model.addAttribute("cList",cList);
    	model.addAttribute(HglContants.PAGE_DTO_ID, page);
		return "companyConsult/companyConsultList";
	}
	@RequestMapping("/showdetailsInfo")
	public String showdetailsInfo(PageDto page,Integer id, Model model){
		Criteria criteria = new Criteria();
		criteria.put("id", id);
		TbCompanyConsultDto pifd = tbCompanyConsultService.selectByObjectPage(criteria,page).get(0);
		model.addAttribute("pifd",pifd);
		return "companyConsult/companyConsultDetail";
	}

	@Override
	public String doSearchResult() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 *删除信息 
	 */
	@RequestMapping("/deleteInfo")
	public String deleteInfo(PageDto page,Integer id,String companyName, HttpServletRequest request,Model model){
		tbCompanyConsultService.deleteByPrimaryKey(id);
		Criteria criteria = new Criteria();
		criteria.put("companyName", request.getParameter("companyName"));
		List<TbCompanyConsultDto> cList = tbCompanyConsultService.selectByObjectPage(criteria,page);
		model.addAttribute("cList",cList);
		model.addAttribute(HglContants.PAGE_DTO_ID, page);
		return "companyConsult/companyConsultList";
	}
}