package com.linkon.admin.action;

import java.util.Date;
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
import org.springframework.web.servlet.ModelAndView;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbNoticeInfo;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.TbNoticeInfoService;

@Controller
@RequestMapping("noticeInfo")
public class NoticeInfoController extends IBaseController{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private TbNoticeInfoService noticeInfoService;
	
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
	
	/**
	 * 查询出所有的公告
	 * @param page
	 * @param model
	 * @return
	 */
	@RequestMapping("/doInitNoticeInfo")
	public String doInitNoticeInfo(PageDto page,ModelMap model){
		
		logger.debug("查询出所有的公告信息，分页显示");
		
		Criteria criteria = new Criteria();
		criteria.setOrderByClause("id desc");
		List<TbNoticeInfo> list = noticeInfoService.selectByObjectPage(criteria, page);
		model.addAttribute("list", list);
		model.addAttribute(HglContants.PAGE_DTO_ID, page);
		
		return "noticeInfo/noticeInfo";
	}
	
	
	/**
	 * 按条件查询出公告
	 * @param noticeInfo
	 * @param page
	 * @param model
	 * @return
	 */
	@RequestMapping("/doSearchResult")
	public String doSearchResult(TbNoticeInfo noticeInfo,PageDto page,ModelMap model){
		logger.debug("按条件查询出公告,并分页显示");
		
		Criteria criteria = new Criteria();
		criteria.put("noticeName", noticeInfo.getNoticeName());
		criteria.put("noticeType", noticeInfo.getNoticeType());
		criteria.setOrderByClause("id desc");
		List<TbNoticeInfo> list = noticeInfoService.selectByObjectPage(criteria, page);
		model.addAttribute("list",list);
		model.addAttribute(HglContants.PAGE_DTO_ID, page);
		
		return "noticeInfo/noticeInfoList";
	}

	/**
	 * 显示公告详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/queryNoticeInfoDetail")
	public ModelAndView queryNoticeInfoDetail(Integer id) {
		ModelAndView mv = new ModelAndView("noticeInfo/noticedetail");
		TbNoticeInfo noticeInfo = noticeInfoService.selectByPrimaryKey(id);
		mv.addObject("noticeInfo", noticeInfo);
		return mv;
	}
	
	/**
	 * 跳转到添加公告页面
	 * @return
	 */
	@RequestMapping("/initAddNoticeInfo")
	public String initAddNoticeInfo(){
		return "noticeInfo/addnotice";
	}


	/**
	 * 添加公告
	 * @param noticeInfo
	 * @return
	 */
	@RequestMapping(value = "/addNoticeInfo")
	@ResponseBody
	public String addNoticeInfo(TbNoticeInfo noticeInfo) {
		noticeInfo.setCreateBy(HglContants.ADMIN_USER_NAME);
		noticeInfo.setCreateDt(System.currentTimeMillis());
		String insertCount = String.valueOf(noticeInfoService
				.insertSelective(noticeInfo));

		return insertCount;
	}

	/**
	 * 跳转到修改公告页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/initUpdateNoticeInfo")
	public String initUpdateNoticeInfo(Integer id, ModelMap model) {

		TbNoticeInfo noticeInfo = noticeInfoService.selectByPrimaryKey(id);
		model.addAttribute("noticeInfo", noticeInfo);

		return "noticeInfo/updnotice";
	}

	/**
	 * 修改系统公告
	 * @param noticeInfo
	 * @return
	 */
	@RequestMapping(value = "/updateNoticeInfo")
	public ModelAndView updateNoticeInfo(TbNoticeInfo noticeInfo) {

		ModelAndView mv = new ModelAndView();

		logger.debug("系统公告Id: " + noticeInfo.getId() + " 系统公告名称: "
				+ noticeInfo.getNoticeName() + " 系统公告类型: "
				+ noticeInfo.getNoticeType()+" 修改日期: "+new Date());
		noticeInfo.setCreateBy(HglContants.ADMIN_USER_NAME);
		noticeInfo.setCreateDt(System.currentTimeMillis());

		noticeInfoService.updateByPrimaryKeySelective(noticeInfo);
		mv.setViewName("redirect:" + "doInitNoticeInfo");

		return mv;
	}

	/**
	 * 根据id删除公告
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/deleteNoticeInfo")
	@ResponseBody
	public String deleteNoticeInfo(String id) {
		Integer advertId = 0;
		if (id != null && !"".equals(id)) {
			advertId = Integer.valueOf(id);
		}
		logger.debug("广告Id: " + id);

		String strCount = String.valueOf(noticeInfoService
				.deleteByPrimaryKey(advertId));

		return strCount;
	}
	
}
