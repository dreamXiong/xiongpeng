package com.linkon.hgl.web.action;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbMerchNotice;
import com.liguo.hgl.proxydao.model.TbNoticeInfo;
import com.liguo.hgl.proxydao.model.TbProductType;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.IProductTypeService;
import com.liguo.hgl.service.TbMerchNoticeService;
import com.liguo.hgl.service.TbNoticeInfoService;


@Controller
@RequestMapping("merchnotice")
public class MerchNoticeController extends IBaseController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private static final String MERCHNOTICEDETAIL="merchnotice/merchnoticedetail";
	
	private static final String MERCHNOTICELIST="merchnotice/merchlist";
	
	private static final String MERCHNOTICELISTPAGE="merchnotice/merchlistPage";

	
	@Autowired
	private TbMerchNoticeService merchNoticeService;
	
	@Autowired
	private TbNoticeInfoService noticeInfoService;
	
	@Autowired
	private IProductTypeService productTypeService;
	
	/*
	 * 首页显示招商公告 
	 * */
	@RequestMapping(value="/doSearchResultShow",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String doSearchResultShow(PageDto page,ModelMap model){
		
		page.pageSize =11 ;
		String jsonString = "";		
		Criteria criteria = new Criteria();
		criteria.put("status",280);
		criteria.setOrderByClause("createddatetime desc");
		List<TbMerchNotice> list = merchNoticeService.selectByObjectPage(criteria, page);
		ObjectMapper mapper = new ObjectMapper();
		try {
			jsonString = mapper.writeValueAsString(list);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return jsonString;
	}
	
	/*招商公告列表分页*/
	@RequestMapping("/queryMerchNotices")
	public String queryMerchNotices(PageDto page,ModelMap model){
		Criteria criteria = new Criteria();
		criteria.put("status",280);
		criteria.setOrderByClause("createddatetime desc");
		List<TbMerchNotice> list = merchNoticeService.selectByObjectPage(criteria, page);
		model.addAttribute("merchNotices", list);		
		List<TbProductType> productTypes = productTypeService.getProductType();
		model.addAttribute("productTypes",productTypes);
		
		model.addAttribute(HglContants.PAGE_DTO_ID, page);
			
		return MERCHNOTICELIST;
	}
	
	/*招商公告列表分页*/
	@RequestMapping("/doSearchResult")
	public String doSearchResult(PageDto page,ModelMap model){
		Criteria criteria = new Criteria();
		criteria.put("status",280);
		criteria.setOrderByClause("createddatetime desc");
		List<TbMerchNotice> list = merchNoticeService.selectByObjectPage(criteria, page);
		model.addAttribute("merchNotices", list);
		List<TbProductType> productTypes = productTypeService.getProductType();
		model.addAttribute("productTypes", productTypes);
		
		model.addAttribute(HglContants.PAGE_DTO_ID, page);
				
		return MERCHNOTICELISTPAGE;
	}
	
	
	/*查看招商公告详情*/
	@RequestMapping("/doSearchMerchNoticeDetail")
	public String doSearchMerchNoticeDetail(Integer id,ModelMap model){
		TbMerchNotice merchNotice = merchNoticeService.selectByPrimaryKey(id);
		model.addAttribute("merchNotice", merchNotice);
		
		return MERCHNOTICEDETAIL;
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
