package com.linkon.hgl.web.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.dto.ActivityInfoDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbActivityInfo;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.TbActivityInfoService;
import com.liguo.hgl.util.ImageUtil;
import com.liguo.hgl.util.StringUtil;

@Controller
@RequestMapping("/activity")
public class ActivityController extends IBaseController {

	@Autowired
	private TbActivityInfoService activityInfoService;
	
	
	@Override
	protected void init(PageDto page, HttpServletRequest request,
			HttpServletResponse response, Model model) {				

	}
	
	/**
	 * 查询出所有的活动信息
	 * @param activityInfoDto
	 * @param page
	 * @param model
	 * @return
	 */
	@RequestMapping("/doInitActivity")
	public String doInitActivity(ActivityInfoDto activityInfoDto,PageDto page,ModelMap model){
		Criteria criteria = new Criteria();
		criteria.put("shopId", this.getLoginUser().getShopId()); //对应店铺的活动
		criteria.put("activityName", activityInfoDto.getActivityName());
		criteria.put("status", activityInfoDto.getStatus());
		criteria.setOrderByClause("id desc");
		List<TbActivityInfo> list = activityInfoService.selectObjectByPage(criteria, page);
		model.addAttribute("activityInfos", list);
		model.addAttribute(HglContants.PAGE_DTO_ID, page);
		
		return "activityInfo/activityInfo";
	}

	/**
	 * 按条件查询出活动信息
	 * @param activityInfoDto
	 * @param page
	 * @param model
	 * @return
	 */
	@RequestMapping("/doSearchResult")
	public String doSearchResult(ActivityInfoDto activityInfoDto,PageDto page,ModelMap model){
		Criteria criteria = new Criteria();
		criteria.put("shopId", this.getLoginUser().getShopId()); //经销商的ShopId
		criteria.put("activityName", activityInfoDto.getActivityName());
		criteria.put("status", activityInfoDto.getStatus());
		criteria.setOrderByClause("id desc");
		List<TbActivityInfo> list = activityInfoService.selectObjectByPage(criteria, page);
		model.addAttribute("activityInfos", list);
		model.addAttribute(HglContants.PAGE_DTO_ID, page);
		
		return "activityInfo/activityInfoList";
	}
	
	/**
	 * 添加活动	
	 * @param activityInfoDto
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/doAddActivityInfo",method=RequestMethod.POST)
	public String doAddActivityInfo(ActivityInfoDto activityInfoDto){
		
		if(activityInfoService.insertActivityInfo(activityInfoDto, this.getLoginUserId())==1){
			return String.valueOf(true);
		}
		
		return String.valueOf(false);
	}
	
	/**
	 * 上传活动图片
	 * @param imgFile
	 * @param request
	 * @param imgNo
	 * @param model
	 * @return
	 */
	@RequestMapping("/validateImg")
    @ResponseBody
    public Map<String,Object> validate(MultipartFile imgFile,HttpServletRequest request ,String imgNo,Model model){
	  if(imgNo.endsWith("_Upd")){
		  imgNo = imgNo.substring(0, imgNo.indexOf("_"));
	  }
      String img = imgNo;
  	  Map<String, Object> map = new HashMap<String, Object>();
  	  String imgName = imgFile.getOriginalFilename();
  	  map.put("code", HglContants.SUCCESS);
  	  long imgSize = imgFile.getSize();
  	  if(imgSize > 5*1024*1024){
 		//图片过大 不超过5MB
 		map.put("code", HglContants.FAIL);
 		return map;
 	  }
  	  long name = System.currentTimeMillis();
  	  String newName = StringUtil.changeFileName(img+"_"+name,imgName);
  	  try{
  		activityInfoService.uploadForm(imgFile, newName,HglContants.ACTIVITY_TESTPATH);
  	  }catch(Exception e){
  		  logger.debug("文件上传失败.....");
  		  e.printStackTrace();
  	  }
  	  map.put("imgNo",newName);
  	  model.addAttribute(imgNo,imgNo);
  	  return map;
    }
	
			
	/**
	 * 修改活动信息
	 * @param activityInfoDto
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/doUpdateActivityInfo")
	public String doUpdateActivityInfo(ActivityInfoDto activityInfoDto){
		
		if(activityInfoService.updateActivityInfoDto(activityInfoDto)==1){
			return String.valueOf(true);
		}else{
			return String.valueOf(false);
		}
	}
	
	/**
	 * 查看某一活动信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/doSearchDetail",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String doSearchDetail(Integer id){
		ActivityInfoDto activityDto = activityInfoService.selectActivityInfoDto(id);
		String strJson = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			strJson = mapper.writeValueAsString(activityDto);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return strJson;
	}
	
	/**
	 * 查看活动详情
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/doSearchActivityInfoDto",produces="text/html;charset=UTF-8")
	public String doSearchActivityInfoDto(Integer id){
		String strJson = "";
		ObjectMapper mapper = new ObjectMapper();
		ActivityInfoDto activityDto = activityInfoService.selectActivityInfoDto(id);
		try {
			strJson = mapper.writeValueAsString(activityDto);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return strJson;
	}
	
	/**
	 * 根据id 删除活动
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/doDeleteActivityInfo")
	public String doDeleteActivityInfo(Integer id){
		TbActivityInfo activityInfo = activityInfoService.selectByPrimaryKey(id);
		if(activityInfo!=null){
			if(activityInfoService.deleteByPrimaryKey(id)==1){
				return String.valueOf(true);
			}
		}		
		return String.valueOf(false);
	}
	
	/**
	 * 发布或下线活动
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/doUpdateStatus")
	public String doUpdateStatus(Integer id){
		TbActivityInfo activityInfo = activityInfoService.selectByPrimaryKey(id);
		if(activityInfo!=null){
			if(HglContants.STATUSYES.equals(activityInfo.getStatus())){
				activityInfo.setStatus(HglContants.STATUSNO);
			}else if(HglContants.STATUSNO.equals(activityInfo.getStatus())){
				activityInfo.setStatus(HglContants.STATUSYES);
			}			
			if(activityInfoService.updateByPrimaryKeySelective(activityInfo)==1){
				return String.valueOf(true);
			}
		}
		
		return String.valueOf(false);
	}
	
	/**
	 * 显示活动图片
	 * @param id
	 * @param imageName
	 * @param response
	 * @return
	 */
	@RequestMapping("/displayImage")
	public String displayImage(Integer id,String imageName,HttpServletResponse response){
		if(id!=null && StringUtils.isNotBlank(imageName)){
			ImageUtil.showActivityImage(id, imageName, response);
		}		
		return null;
	}
	
	@Override
	public String doSearchResult() {
		// TODO Auto-generated method stub
		return null;
	}
}
