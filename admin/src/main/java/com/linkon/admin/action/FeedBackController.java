package com.linkon.admin.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.dto.FeedBackDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.TbFeedBackService;

@Controller
@RequestMapping("feedBack")
public class FeedBackController extends IBaseController {
	
	@Autowired
	private TbFeedBackService feedBackService;
	
	@RequestMapping("/doInitFeedBack")
	public String doInitFeedBack(PageDto page,ModelMap model){
		Criteria criteria = new Criteria();
		criteria.setOrderByClause("id desc");
		List<FeedBackDto> list = feedBackService.selectByObjectPage(criteria, page);
		model.addAttribute("list", list);
		model.addAttribute(HglContants.PAGE_DTO_ID, page);
		
		return "feedBack/feedBack";
	}
	
	@RequestMapping("/doSearchResult")
	public String doSearchResult(FeedBackDto feedBack,PageDto page,ModelMap model){
		Criteria criteria = new Criteria();		
		criteria.put("userName", feedBack.getUserName());
		criteria.put("typeId", feedBack.getTypeId());
		criteria.put("mobile", feedBack.getMobile());
		criteria.setOrderByClause("id desc");
		List<FeedBackDto> list = feedBackService.selectByObjectPage(criteria, page);
		model.addAttribute("list", list);
		model.addAttribute(HglContants.PAGE_DTO_ID, page);
		
		return "feedBack/feedBackList";
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
