package com.linkon.hgl.web.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbBrand;
import com.liguo.hgl.proxydao.model.TbMerchNotice;
import com.liguo.hgl.proxydao.model.TbNoticeInfo;
import com.liguo.hgl.proxydao.model.TbProductType;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.IProductTypeService;
import com.liguo.hgl.service.TbBrandService;
import com.liguo.hgl.service.TbMerchNoticeService;
import com.liguo.hgl.service.TbNoticeInfoService;
import com.liguo.hgl.util.ImageUtil;

@Controller
@RequestMapping("noticeInfo")
public class NoticeInfoController {

	private final static String NOTICEINFOLIST = "notice/noticelist";
	private final static String NOTICEDETAIL = "notice/noticedetail";

	@Autowired
	private TbNoticeInfoService noticeInfoService;

	@Autowired
	private IProductTypeService productTypeService;
	
	@Autowired
	private TbBrandService tbBrandService;
	
	@Autowired
	private TbMerchNoticeService merchNoticeService;
		
	@RequestMapping(value="queryNoticeInfoList")
	public String queryNoticeInfoList(PageDto page,ModelMap model){
					
		Criteria criteria = new Criteria();
		criteria.setOrderByClause("createddatetime desc");
		List<TbNoticeInfo> list = noticeInfoService.selectByObjectPage(criteria, page);
		List<TbProductType> productTypes = productTypeService.getProductType();
		model.addAttribute("noticeInfos",list);
		model.addAttribute("productTypes",productTypes);		
		model.addAttribute(HglContants.PAGE_DTO_ID, page);
				
		return NOTICEINFOLIST;
	}
	
	@RequestMapping("/searchImage")
    public String generateImage(ModelMap model,Integer id,HttpServletResponse response) {
    	TbBrand tbBrand = tbBrandService.selectByPrimaryKey(id);
    	model.addAttribute("tbBrand", tbBrand);
    	ImageUtil.showImage("shopLogo",tbBrand.getLogoName(),response);
        return null;
    }

	@RequestMapping(value = "searchNoticeInfo")
	public String searchNoticeInfo(HttpServletRequest request,String name,PageDto page,Model model) throws IOException {
		page.pageSize = 15;	
		String sdf = request.getParameter("name");	
		Criteria criteria=new Criteria();
		criteria.put("name",name);
		criteria.setOrderByClause("createdDateTime desc");
		List<TbNoticeInfo> list = noticeInfoService.selectByObjectPage(criteria, page);
		model.addAttribute(HglContants.PAGE_DTO_ID, page);
    	model.addAttribute("noticeInfos", list);
    	model.addAttribute("name", name);
        return "notice/noticelistPage";
    }
	
	
	@RequestMapping("/getNoticeInfoDetail")
	public String getNoticeInfoDetail(Integer id, Map<String, Object> map) {

		TbNoticeInfo noticeInfo = noticeInfoService.selectByPrimaryKey(id);
		map.put("noticeInfo", noticeInfo);
		
		List<TbProductType> productTypes = productTypeService.getProductType();
		map.put("productTypes", productTypes);

		return NOTICEDETAIL;
	}
}
