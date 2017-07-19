package com.linkon.hgl.web.action;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.model.TbUserInfo;
import com.liguo.hgl.proxydao.model.TbWebUser;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.proxydao.util.StringUtil;
import com.liguo.hgl.service.TbSaveInfoService;
import com.liguo.hgl.service.TbShopInfoService;
import com.liguo.hgl.service.TbStandLetterService;
import com.liguo.hgl.service.TbUserInfoService;
import com.liguo.hgl.service.TbWebUserService;
import com.liguo.hgl.util.ImageUtil;

@Controller
@RequestMapping("letter")
public class LetterController extends IBaseController {

	@Autowired
	protected TbSaveInfoService tbSaveInfoService;
	
	@Autowired
	protected TbStandLetterService tbStandLetterService;
	
	@Autowired
	private TbWebUserService tbWebUserService;
	
	@Autowired
	private TbShopInfoService tbShopInfoService;
	
	@Autowired
	private TbUserInfoService tbUserInfoService;
	/**
	 * 站内信消息列表
	 * @param model
	 * @return
	 * @author zss
	 */
	@RequestMapping("/userLetterList")
	public String getLetterList(Model model){
		model.addAttribute("userLetterList", tbSaveInfoService.getUserLetter(getLoginUserId()));
		model.addAttribute("isdp", getLoginUser().getTypeId().equals(HglContants.USER_TYPE_DISTRIBUTOR));
		return "letter/userLetterList";
	}
	/**
	 * 用户根据用户Id，查询对话信息
	 * @param model
	 * @param uId
	 * @param num
	 * @return
	 * @author zss
	 */
	@RequestMapping("/letter")
	public String getLetter(Model model,Integer uId,Integer num){
		if(uId!=null && uId>0){
			model.addAttribute("letters", tbStandLetterService.getLetterByShopId(uId,num,getLoginUserId()));
			Integer letterCount = (Integer)session.getAttribute(HglContants.INFO_COUNT_SUM);
			if(num > 0 && letterCount > 0){
				session.setAttribute(HglContants.INFO_COUNT_SUM, letterCount - num);
				logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>letterCount:"+(letterCount - num));
			}
			TbWebUser webUser =  tbWebUserService.selectByPrimaryKey(uId);
			if(webUser.getTypeId().equals(HglContants.USER_TYPE_DISTRIBUTOR)){
				model.addAttribute("titleName",tbShopInfoService.selectByPrimaryKey(webUser.getShopId()).getShopName()+"-"+webUser.getName());
			}else{
				model.addAttribute("titleName", webUser.getName());
			}
		}
		
		return "letter/letter";
	}
	
	/**
	 * wap端发送消息
	 * @param param
	 * @return
	 * @author zss
	 */
	 @RequestMapping("/addLetterByUser")
	 @ResponseBody
	public Map<String, Object> addLetterByUser(@RequestParam Map<String, Object> param){
		 Map<String,Object> map =null;
		if(param!=null){
			map = tbStandLetterService.addLetterByUser(param,getLoginUserId());
		}
		return map;
	}
	/**
	 * 每隔一段时间请求后台查看对话消息
	 * @param model
	 * @param uId
	 * @param response
	 * @return
	 * @throws IOException
	 * @author zss
	 */
	@RequestMapping("/letterByTime")
	public String getLetterByTime(Model model,Integer uId,HttpServletResponse response) throws IOException{
		if(uId!=null && uId>0){
			int count =tbStandLetterService.selectNewLetter(uId,getLoginUserId());
			if(count>0){
				model.addAttribute("letters", tbStandLetterService.getLetterByShopId(uId,count,getLoginUserId()));
				return "letter/letterList";
			}
			//model.addAttribute("letters", tbStandLetterService.getLetterByShopId(sId,num,getLoginUserId()));
		}
		response.getWriter().write("");
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
	/**
	 * 店铺图片
	 * @param model
	 * @param id
	 * @param imgName
	 * @param response
	 * @return
	 * @author zss
	 */
	@RequestMapping("/generateImage")
    public String generateImage(ModelMap model,Integer id,String imgName,HttpServletResponse response) {
    	ImageUtil.showImageShop(id,imgName,response);
        return null;
    }
	/**
	 * 查询用户头像
	 * @param request
	 * @param id 用户id
	 * @param response
	 * @return
	 * @author zss
	 */
	 @RequestMapping("/showHeaderImage")
	    public String showHeaderImage(HttpServletRequest request,Integer id,HttpServletResponse response){
		 TbWebUser tbWebUser = tbWebUserService.selectByPrimaryKey(id);
	    	TbUserInfo userInfo = tbUserInfoService.selectByPrimaryKey(tbWebUser.getUserinfoId());
	    	if(userInfo!=null && StringUtil.isNotBlank(userInfo.getImage())){
	    		//判断是否是http开头的,是http开头是微信头像
				if(userInfo.getImage().startsWith("http")){
					return "redirect:"+userInfo.getImage();
				}else{
					ImageUtil.showWapHeaderImage(userInfo.getId(),userInfo.getImage(),request,response);
				}
	    	}else{
	    		return "redirect:/images/use.jpg";
	    	}
	    	return null; 	
	    }

}
