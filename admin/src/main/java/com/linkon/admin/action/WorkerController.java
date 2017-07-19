package com.linkon.admin.action;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.dto.ServiceTypeDto;
import com.liguo.hgl.proxydao.dto.WebUserDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbServiceType;
import com.liguo.hgl.proxydao.model.TbUserInfo;
import com.liguo.hgl.proxydao.model.TbWebUser;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.proxydao.util.StringUtil;
import com.liguo.hgl.service.TbServiceTypeService;
import com.liguo.hgl.service.TbUserInfoService;
import com.liguo.hgl.service.TbWebUserService;
import com.liguo.hgl.util.ImageUtil;
import com.liguo.hgl.util.MD5Utils;

@RequestMapping("worker")
@Controller
public class WorkerController extends IBaseController {
	
	@Autowired
	private TbWebUserService webUserService;
	
	@Autowired
	private TbUserInfoService userInfoService;
	
	@Autowired
	private TbServiceTypeService serviceTypeService;

	/*初始化所有的师傅信息*/
	@RequestMapping("/doInitWorker")
	public String doInitWorker(PageDto page,ModelMap model){
		String strTypeId ="114";
		List<String> typeIds = Arrays.asList(strTypeId.split(","));
		Criteria criteria = new Criteria();
		criteria.put("typeId",typeIds);
		criteria.setOrderByClause("id desc");
		List<WebUserDto> list = webUserService.selectByObjectPage(criteria, page);
		model.addAttribute("webUserDtos", list);
		model.addAttribute(HglContants.PAGE_DTO_ID, page);
		
		return "worker/worker";
	}
	
	@RequestMapping("/doSearchWorker")
	public String doSearchWorker(WebUserDto webUser,PageDto page,ModelMap model){
		String strTypeId ="114";
		List<String> typeIds = Arrays.asList(strTypeId.split(","));
		
		Criteria criteria = new Criteria();
		criteria.put("typeId", typeIds);
		criteria.put("userName", webUser.getUserName());
		criteria.put("name", webUser.getName());
		criteria.put("mobile", webUser.getMobile());
		criteria.setOrderByClause("id desc");
		List<WebUserDto> list = webUserService.selectByObjectPage(criteria, page);
		model.addAttribute("webUserDtos",list);
		model.addAttribute(HglContants.PAGE_DTO_ID, page);
		
		return "worker/workerlist";
		
	}
	
	/*初始化师傅审核信息*/
	@RequestMapping("/doInitReviewWorker")
	public String doInitReviewWorker(Integer id,ModelMap model){
		List<ServiceTypeDto> list = null;
		TbWebUser webUser = webUserService.selectByPrimaryKey(id);
		TbUserInfo userInfo = new TbUserInfo();
		
		Criteria criteria = new Criteria();
		criteria.put("parentId",0);
		criteria.setOrderByClause("id");
		
		if(webUser!=null){
			userInfo = userInfoService.selectByPrimaryKey(webUser.getUserinfoId());	
			list = serviceTypeService.getSkills(userInfo.getId());		
		}		
		model.addAttribute("webUser", webUser);
		model.addAttribute("userInfo", userInfo);
		model.addAttribute("serviceTypeDtos", list);
		
		return "worker/workerReview";
	}
	
	/*显示师傅图片信息*/
	@RequestMapping("/showImage")
    public String showImage(ModelMap model,Integer id,String url,HttpServletResponse response) {
		TbUserInfo userInfo = userInfoService.selectByPrimaryKey(id);
		if(userInfo!=null){
			ImageUtil.showImage("user",String.valueOf(id)+File.separator+url, response);
		}
        return null;
    }
	
	/*审核用户*/
	@RequestMapping("/doUpdateWorkerStatus")
	public String doUpdateWorkerStatus(WebUserDto webUserDto){
		TbWebUser webUser = webUserService.selectByPrimaryKey(webUserDto.getId());
		if(webUser!=null){
			webUser.setState(webUserDto.getState());
			if(webUserDto.getState()==1){
				webUser.setRoleId(1); //审核通过的经销商，roleId为1
			}
			webUser.setRemark(webUserDto.getRemark());
			if(webUserService.updateByPrimaryKey(webUser)==1){
				return "redirect:doInitWorker";
			}else{
				return String.valueOf(false);
			}
		}
		
		return "redirect:doInitWorker";
	}
	
	/*重置密码*/
	@RequestMapping("/doUpdateWebUserPassword")
	@ResponseBody
	public String doUpdateWebUserPassword(Integer id){
		TbWebUser webUser = webUserService.selectByPrimaryKey(id);
		if(webUser!=null){
			String mobile = webUser.getMobile();
			if(StringUtils.isNotBlank(mobile)){
				String newPsd = mobile.substring(5, 11);
				webUser.setPassword(MD5Utils.md5(newPsd, "UTF-8"));
				if(webUserService.updateByPrimaryKey(webUser)==1){
					return String.valueOf(true);
				}else{
					return String.valueOf(false);
				}
			}
		}		
		return String.valueOf(false);
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
