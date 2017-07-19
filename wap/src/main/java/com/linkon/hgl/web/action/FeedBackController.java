package com.linkon.hgl.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.TbFeedBackService;

@Controller
@RequestMapping("feedBack")
public class FeedBackController extends IBaseController {
	
	@Autowired
	private TbFeedBackService feedBackService;

	/**
	 * 跳转到意见反馈页面
	 * @param model
	 * @return
	 */
    @RequestMapping("/doInitFeedBack")
    public String doInitFeedBack(ModelMap model){
    	return "feedBack/feedback";
    }
    
    /**
     * 用户提交反馈意见
     * @param feedBack
     * @param model
     * @return
     */
    @RequestMapping("/doSendFeedBack")
    public String doSendFeedBack(String feedBack,ModelMap model){
    	feedBackService.insertFeedBack(feedBack, this.getLoginUserId());
    	model.addAttribute("webUserDto", this.getLoginUser());
    	return "personalCenter/personalCenter";
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
