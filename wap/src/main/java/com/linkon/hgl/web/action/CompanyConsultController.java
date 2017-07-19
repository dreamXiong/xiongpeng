package com.linkon.hgl.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.liguo.hgl.proxydao.model.TbCompanyConsult;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.TbCompanyConsultService;

/**
 * 客户咨询信息
 * */
@RequestMapping("/companyConsult")
@Controller
public class CompanyConsultController extends IBaseController {

	
	@Autowired
	private TbCompanyConsultService tbCompanyConsultService;

	@Override
	protected void init(PageDto page, HttpServletRequest request,HttpServletResponse response, Model model) {
		String companyName = request.getParameter("companyName");
		String id = request.getParameter("id");
		model.addAttribute("companyName",companyName);
		model.addAttribute("id",id);
	}

	@Override
	public String doSearchResult() {
		// TODO Auto-generated method stub
		return null;
	}
	@RequestMapping("/insertCompanyConsult")
	public String insertCompanyConsult(Model model,HttpServletRequest request){
		TbCompanyConsult tbCompanyConsult = new TbCompanyConsult();
		if(this.getLoginUserId() != null){
			tbCompanyConsult.setUserId(getLoginUserId());
		}
		tbCompanyConsult.setContract(request.getParameter("contract"));
		tbCompanyConsult.setContractPhone(request.getParameter("contractPhone"));
		tbCompanyConsult.setCompanyId(Integer.parseInt(request.getParameter("companyId")));
		tbCompanyConsult.setCreateDt(System.currentTimeMillis());
		tbCompanyConsult.setDescribes(request.getParameter("describes"));
		tbCompanyConsultService.insertSelective(tbCompanyConsult);
		return "redirect:/companyConsult/success";
	}
	@RequestMapping("/success")
	public String success(Model model){
		return "companyConsult/success";
	}
}
