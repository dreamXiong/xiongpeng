package com.linkon.hgl.web.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.dto.WebUserDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbUserInfo;
import com.liguo.hgl.proxydao.model.TbWebUser;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.proxydao.util.StringUtil;
import com.liguo.hgl.service.TbUserInfoService;
import com.liguo.hgl.service.TbWebUserService;
import com.liguo.hgl.util.MD5Utils;
import com.liguo.hgl.util.SmsSend;
import com.linkon.hgl.web.common.StrUtil;


@Controller
@RequestMapping("/webuser")
public class WebUserController extends IBaseController{
	
	private static final String WEBUSEREDIT="webuser/webuseredit"; 
	
	private static final String MOBILEUPDATE="webuser/mobileupdate";
		
	private static final String ERROROLDPSD="-1";
	
	private static final Integer DEFAULTVALUE=1; //默认值
	
	
	private Font mFont = new Font("Times New Roman", Font.PLAIN, 20);
	
	@Autowired
	private TbWebUserService webUserService;
	
	@Autowired
	private TbUserInfoService userInfoService;
	
	@RequestMapping(value="/doInitEditWebUser")
	public String doInitEditWebUser(Model model){
		TbUserInfo userInfo = new TbUserInfo();
		
		TbWebUser webUser = this.getLoginUser();
		if(webUser!=null){			
			userInfo = userInfoService.selectByPrimaryKey(webUser.getUserinfoId());
		}
		System.out.println(webUser.getMobile());
		model.addAttribute("webUser",webUser);
		model.addAttribute("userInfo", userInfo);
							
		return WEBUSEREDIT;
	}
	
	
	/**
	 * 修改用户信息
	 * @param webUser
	 * @return
	 */
	@RequestMapping(value="/doEditWebUser")
	@ResponseBody
	public String doEditWebUser(WebUserDto webUser){
		TbWebUser webUserLogin = this.getLoginUser();
		if(webUserLogin!=null){
			webUserLogin.setId(webUserLogin.getId());
			webUserLogin.setVersion(webUserLogin.getVersion());
			webUserLogin.setName(webUser.getName());
			webUserLogin.setEmail(webUser.getEmail());
			
			TbUserInfo userInfo = userInfoService.selectByPrimaryKey(webUserLogin.getUserinfoId());
			if(userInfo!=null){  //如果对应的userInfo表中有数据，则修改
				userInfo.setId(userInfo.getId());
				userInfo.setVersion(userInfo.getVersion());
				userInfo.setBirthYear(webUser.getBirthYear());
				userInfo.setBirthMonth(webUser.getBirthMonth());
				userInfo.setBirthDay(webUser.getBirthDay());
				userInfo.setGender(webUser.getGender());
				userInfo.setVersion(userInfo.getVersion());
				userInfoService.updateByPrimaryKeySelective(userInfo);
			}else{      //如果对应的userInfo表中没有数据则添加一条数据
				userInfo = new TbUserInfo();
				userInfo.setGender(webUser.getGender());
				userInfo.setBirthYear(webUser.getBirthYear());
				userInfo.setBirthMonth(webUser.getBirthMonth());
				userInfo.setBirthDay(webUser.getBirthDay());
				userInfo.setShopType(HglContants.USER_TYPE_DISTRIBUTOR); //经销商	
				userInfo.setVersion(DEFAULTVALUE);
				userInfo.setCreateDt(System.currentTimeMillis());
				userInfoService.insert(userInfo);
				webUserLogin.setUserinfoId(userInfo.getId());
			}
			webUserService.updateByPrimaryKeySelective(webUserLogin);
			
			session.setAttribute(HglContants.SESSION_KEY, webUserLogin);
			return String.valueOf(true);
		}
		
		return String.valueOf(false);
	}
	
	/*
	 * 验证旧密码是否正确 
	 */
	@RequestMapping("/doCheckPassword")
	@ResponseBody
	public String doCheckPassword(String password){
		TbWebUser webUser = this.getLoginUser();
		
		if(webUser!=null){
			System.out.println(MD5Utils.md5(password,"UTF-8"));
			if(webUser.getPassword().equals(MD5Utils.md5(password, "UTF-8"))){				
				return String.valueOf(true);
			}
		}		
		return String.valueOf(false);	
	}
	
	/**
	 * 重置密码
	 */
	@RequestMapping("/doUpdatePassword")
	@ResponseBody
	public String doUpdatePassword(WebUserDto webUser) throws Exception{	
		TbWebUser webUserLogin = this.getLoginUser();
		if(webUserLogin!=null){
			if(StringUtil.isBlank(webUser.getOldPassword())){
				return ERROROLDPSD;
			}else{
				if(!webUserLogin.getPassword().equals(MD5Utils.md5(webUser.getOldPassword(), "UTF-8"))){
					return ERROROLDPSD;
				}else{
					webUserLogin.setPassword(MD5Utils.md5(webUser.getPassword(),"UTF-8"));
					if (webUserService.updateByPrimaryKeySelective(webUserLogin)==1){
						return String.valueOf(true);
					}else{
						return String.valueOf(false);
					}
				}
			}					
		}				
		return String.valueOf(false);
	}
	
	/*判定手机号是否已经被注册*/
	@RequestMapping("/doCheckMobile")
	@ResponseBody
	public String doCheckMobile(String mobile){
		if(StringUtil.isNotBlank(mobile)){
			Criteria criteria = new Criteria();
			criteria.put("mobile", mobile);
			List<TbWebUser> list = webUserService.selectByObject(criteria);
			if(list.size()>0){
				return String.valueOf(false);
			}else{
				return String.valueOf(true);
			}
		}else{
			return String.valueOf(true);
		}
		
	}
	
	@RequestMapping("/doInitUpdateMobile")
	public String doInitUpdateMobile(Model model){
		TbWebUser webUserLogin = this.getLoginUser();
		model.addAttribute("webUser", webUserLogin);
		
		return MOBILEUPDATE; 
	}
	
	@RequestMapping("/doUpdateMobile")
	public String doUpdateMobile(String mobile){
		TbWebUser webUserLogin = this.getLoginUser();
		if(StringUtil.isNotBlank(webUserLogin.getMobile())&& webUserLogin.getMobile().equals(mobile)){
			return MOBILEUPDATE;
		}
		webUserLogin.setMobile(mobile);	
		if(webUserService.updateByPrimaryKeySelective(webUserLogin)==1){
			return "redirect:doInitEditWebUser";
		}else{
			return MOBILEUPDATE;
		}
	}
	
	Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	@RequestMapping("/authImage")
	public String authImage(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		// 表明生成的响应是图片
		response.setContentType("image/jpeg");

		int width = 100, height = 36;
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		Graphics g = image.getGraphics();
		Random random = new Random();
		g.setColor(getRandColor(200, 250));
		g.fillRect(1, 1, width - 1, height - 1);
		g.setColor(new Color(102, 102, 102));
		g.drawRect(0, 0, width - 1, height - 1);
		g.setFont(mFont);

		g.setColor(getRandColor(160, 200));

		// 画随机线
		for (int i = 0; i < 155; i++) {
			int x = random.nextInt(width - 1);
			int y = random.nextInt(height - 1);
			int xl = random.nextInt(6) + 1;
			int yl = random.nextInt(12) + 1;
			g.drawLine(x, y, x + xl, y + yl);
		}

		// 从另一方向画随机线
		for (int i = 0; i < 70; i++) {
			int x = random.nextInt(width - 1);
			int y = random.nextInt(height - 1);
			int xl = random.nextInt(12) + 1;
			int yl = random.nextInt(6) + 1;
			g.drawLine(x, y, x - xl, y - yl);
		}

		// 生成随机数,并将随机数字转换为字母
		String sRand = "";
		for (int i = 0; i < 4; i++) {
			int itmp = random.nextInt(26) + 65;
			char ctmp = (char) itmp;
			sRand += String.valueOf(ctmp);
			g.setColor(new Color(20 + random.nextInt(110), 20 + random
					.nextInt(110), 20 + random.nextInt(110)));
			g.drawString(String.valueOf(ctmp), 15 * i + 10, 16);
		}

		HttpSession session = request.getSession(true);
		session.setAttribute("verify", sRand);
		g.dispose();
		ImageIO.write(image, "JPEG", response.getOutputStream());
		return null;
	}
	
	//判断验证码是否输入正确
	@RequestMapping("/checkVerify")
	@ResponseBody
	public String checkVerify(String identCode){
		String verify = session.getAttribute("verify").toString();
		if(!verify.equalsIgnoreCase(identCode)){
			return String.valueOf(false);
		}else{
			return String.valueOf(true);
		}
	}
	
	
	/*手机验证码*/
	@ResponseBody
	@RequestMapping("/getUserVodePhone")
	public String getUserVodePhone(HttpServletRequest request,String phoneNum){
		String vode = StrUtil.getRandom4Number();
		String userName = "的经销商";
		String sendRegestSms = SmsSend.sendRegestSms(phoneNum, vode, userName);
		if("".equals(sendRegestSms)){
			HttpSession session = request.getSession();
			session.setAttribute("userVodeCode", vode);
		}		
		return sendRegestSms;
	}
	
	/*新手机验证码*/
	@ResponseBody
	@RequestMapping("/getNewUserVodePhone")
	public String getNewUserVodePhone(HttpServletRequest request,String phoneNum){
		String newVode = StrUtil.getRandom4Number();
		String userName = "经销商";
		String sendRegestSms = SmsSend.sendRegestSms(phoneNum, newVode, userName);
		if("".equals(sendRegestSms)){
			HttpSession session = request.getSession();
			session.setAttribute("newUserVodeCode", newVode);			
		}
		return sendRegestSms;
	}
	
	/*验证手机验证码*/
	@ResponseBody
	@RequestMapping("/checkUserVodeCode")
	public String checkUserVodeCode(HttpServletRequest request,String userVodeCode){
		String userVodeCodeOrg ="";
		HttpSession session = request.getSession();
		if(session.getAttribute("userVodeCode")!=null){
			userVodeCodeOrg = session.getAttribute("userVodeCode").toString();
			if(userVodeCodeOrg.equals(userVodeCode)){
				return String.valueOf(true);
			}
		}
		return String.valueOf(false);
	}
	
	
	/*验证新手机验证码*/
	@ResponseBody
	@RequestMapping("/checkNewUserVodeCode")
	public String checkNewUserVodeCode(HttpServletRequest request,String newUserVodeCode){
		String newUserVodeCodeOrg ="";
		HttpSession session = request.getSession();
		if(session.getAttribute("newUserVodeCode")!=null){
			newUserVodeCodeOrg = session.getAttribute("newUserVodeCode").toString();
			if(newUserVodeCodeOrg.equals(newUserVodeCode)){
				return String.valueOf(true);
			}
		}
		return String.valueOf(false);
	}


	@Override
	protected void init(PageDto page, HttpServletRequest request,
			HttpServletResponse response, Model model) {
				
	}

	@Override
	public String doSearchResult() {
		
		return null;
	}
	
}
