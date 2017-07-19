package com.linkon.hgl.web.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.liguo.hgl.proxydao.dto.ActivityInfoImageDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbActivityInfo;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.TbActivityInfoService;
import com.liguo.hgl.util.ImageUtil;

@Controller
@RequestMapping("activityInfo")
public class ActivityInfoController extends IBaseController {

	@Autowired
	private TbActivityInfoService activityInfoService;
	
	/**
	 * 按条件查询活动详情 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/doSearchActivityInfo")
	public String doSearchActivityInfo(Integer id,ModelMap model){
		
		TbActivityInfo activityInfo = activityInfoService.selectByPrimaryKey(id);
		List<ActivityInfoImageDto> imageList = new ArrayList<ActivityInfoImageDto>();
		List<String> strList = Arrays.asList(activityInfo.getImage().split(","));
		for(String strImage:strList){
			ActivityInfoImageDto imageDto = new ActivityInfoImageDto();
			imageDto.setId(activityInfo.getId());
			imageDto.setImage(strImage);
			imageList.add(imageDto);
		}		
		model.addAttribute("imageList", imageList);
		model.addAttribute("activityInfo", activityInfo);
		
		return "activityInfo/activityInfoDetail";
	}
	
	/**
	 * 显示活动的图片
	 * @param id
	 * @param imgName
	 * @param response
	 * @return
	 */
	@RequestMapping("/showActivityImage")
    public String showActivityImage(Integer id,String imgName,HttpServletResponse response){
    	ImageUtil.showActivityImage(id, imgName, response);
    	return null;
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
