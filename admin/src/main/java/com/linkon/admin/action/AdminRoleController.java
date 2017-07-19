package com.linkon.admin.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbAdminRole;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.TbAdminRoleService;

@Controller
@RequestMapping("adminrole")
public class AdminRoleController extends IBaseController {

	@Autowired
	private TbAdminRoleService adminRoleService;
	@Override
	protected void init(PageDto page, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		// TODO Auto-generated method stub

	}

	@Override
	public String doSearchResult() {
		
		return null;
	}

	@RequestMapping("/doInitAdminRole")
	public String doInitAdminRole(ModelMap map){
		List<TbAdminRole> list = adminRoleService.selectByObject(new Criteria());
		map.addAttribute("adminRole", list);
		return "adminrole/role";
	}

}
