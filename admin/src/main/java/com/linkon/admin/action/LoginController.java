/**
 * Copyright (c)2014 快诺迅汇(长沙)网络技术股份有限公司。
 * 版权所有
 */
package com.linkon.admin.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.dto.TbAdminUserDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbAdminUser;
import com.liguo.hgl.proxydao.util.StringUtil;
import com.liguo.hgl.service.TbAdminUserService;
import com.liguo.hgl.service.TbShopUserRefService;
import com.liguo.hgl.util.MD5Utils;
/**
 * 类的功能描述<br>
 *
 * @author 王丹
 * @FileName LoginController.java<br>
 * @Language Java<br>
 * @date 2016-01-12<br>
 */
@Controller
@RequestMapping("login")
public class LoginController{

	@Autowired
	private TbAdminUserService tbAdminUserService;
	
	private static final String FINDPASSWORDPAGE = "adminuser/findPassword";
	
	private static final String UPDATEPASSWORD = "adminuser/updatePsd";
	
	private Font mFont = new Font("Times New Roman", Font.PLAIN, 20);
	
	private HttpSession session;
	
	@Autowired
	private TbShopUserRefService tbShopUserRefService;
    @RequestMapping("/index")
    public String index(Model model){
        return "index/login";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request, HttpSession session,
			ModelMap model) throws Exception{
		String userName = request.getParameter("userName");
		String pwd = request.getParameter("pwd");
		// 确定计算方法
		if(pwd!=null){
			pwd = MD5Utils.md5(pwd, "UTF-8");
		}
		// 加密后的字符串
		TbAdminUserDto tbAdminUser = tbAdminUserService.selectByUsernameAndPwd(userName,pwd);
		if (tbAdminUser != null && HglContants.USER_STATUS_Invalid.equals(tbAdminUser.getStatus())) {
			model.put("error", "该账户已经被禁用");
			return "index/login";
		}else if(tbAdminUser != null && HglContants.USER_STATUS_Valid.equals(tbAdminUser.getStatus())){
			session.setAttribute(HglContants.SESSION_KEY, tbAdminUser);
			//询该ID下的所有ShopID
			List<Integer> shopIdList = tbShopUserRefService.selectShopListByUserID(tbAdminUser.getId());
			session.setAttribute(HglContants.SHOP_ID_LIST, shopIdList);
			return "redirect:/";
		} else {
			model.put("error", "用户名密码不匹配");
			return "index/login";
		}
    }
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) throws UnsupportedEncodingException,
            NoSuchAlgorithmException {
        HttpSession session = request.getSession();
        //清空session
        session.setAttribute(HglContants.SESSION_KEY, null);
        return "index/login";
    }
    
	/*初始化找回密码页面*/
	@RequestMapping("/doInitFindPassword")
	public String doInitFindPassword(){
		
		return FINDPASSWORDPAGE;
	}
	
	/*随机生成二维码*/
	Color getRandColor(int fc,int bc){
		Random random = new Random();
		if(fc>255){
			fc = 255;
		}
		
		if(bc>255){
			bc = 255;
		}
		
		int r = fc + random.nextInt(bc-fc);
		int g = fc + random.nextInt(bc-fc);
		int b = fc +random.nextInt(bc-fc);
		
		return new Color(r,g,b);			
	}
	
	@RequestMapping("/authImage")
	public String authImage(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		//表明生成的响应是图片
		response.setContentType("image/jpeg");
		
		int width = 100;
		int height = 36;
		
		BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		
		Graphics g = image.getGraphics();		
		Random random = new Random();
		g.setColor(this.getRandColor(200, 250));
		g.fillRect(1, 1, width-1, height-1);
		g.setColor(new Color(102,102,102));
		g.drawRect(0, 0, width-1, height-1);
		g.setFont(mFont);
		
		g.setColor(getRandColor(160,200));
		
		//画随机线
		for(int i=0;i<155;i++){
			int x = random.nextInt(width-1);
			int y = random.nextInt(height-1);
			int xl = random.nextInt(6)+1;
			int yl = random.nextInt(12)+1;
			
			g.drawLine(x, y, x+xl, y+yl);
		}
		
		//从另一个方向画随机线
		for(int i=0;i<70;i++){
			int x = random.nextInt(width-1);
			int y = random.nextInt(height -1);
			int xl = random.nextInt(12)+1;
			int yl = random.nextInt(6)+1;
			
			g.drawLine(x, y, x-xl, y-yl);
		}
				
		//生成随机数
		String sRand = "";
		for(int i=0;i<4;i++){
			int itmp = random.nextInt(26) +65;
			char ctmp = (char) itmp;
			sRand += String.valueOf(ctmp);
			g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));
			g.drawString(String.valueOf(ctmp), 18*i+10, 25);
		}
		
		session = request.getSession(true);
		session.setAttribute("checkCode", sRand);
		g.dispose();
		
		ImageIO.write(image, "JPEG", response.getOutputStream());
		
		return null;		
	}
	
	//判断验证码输入是否正确
	@RequestMapping("/checkCode")
	@ResponseBody
	public String checkCode(String checkCode,HttpServletRequest request){
		
		String checkCodeIpt = session.getAttribute("checkCode").toString();
		if(checkCodeIpt.equalsIgnoreCase(checkCode)){
			return String.valueOf(true);
		}else{
			return String.valueOf(false);
		}
	}
	
	//图片验证码输入正确，跳转到修改密码页面
	@RequestMapping("/doInitUpdatePassword")
	public String doInitUpdatePassword(ModelMap model,TbAdminUserDto adminUserDto){
		TbAdminUser adminUser = new TbAdminUser();
		Criteria criteria = new Criteria();
		
		//根据手机号码验证
		if(StringUtil.isNotBlank(adminUserDto.getMobile()) && StringUtil.isBlank(adminUserDto.getEmail())){
			criteria.put("mobile", adminUserDto.getMobile());
			List<TbAdminUser> adminUsers = tbAdminUserService.selectByObject(criteria);
			if(adminUsers.size()>0){
				adminUser = adminUsers.get(0);	
				adminUser.setEmail("");
			}
		}
		
		//根据电话号码验证
		if(StringUtil.isNotBlank(adminUserDto.getEmail()) && StringUtil.isBlank(adminUserDto.getMobile())){
			criteria.put("email", adminUserDto.getMobile());
			List<TbAdminUser> adminUsers = tbAdminUserService.selectByObject(criteria);
			if(adminUsers.size()>0){
				adminUser = adminUsers.get(0);	
				adminUser.setMobile("");
			}
		}
		
		 int random  =(int)((Math.random()*9+1)*100000);
		 model.addAttribute("random", random);
		 model.addAttribute("adminUser", adminUser);
		 session.setAttribute("checkCodeRandom", random);
		return UPDATEPASSWORD;
	}
	
	//验证手机或邮箱验证码是否输入正确
	@RequestMapping("/docheckCodeRandom")
	@ResponseBody
	public String docheckCodeRandom(String checkCodeRandom){
		String checkCodeRandomIpt = session.getAttribute("checkCodeRandom").toString();
		if(checkCodeRandomIpt.equalsIgnoreCase(checkCodeRandom)){
			return String.valueOf(true);
		}else{
			return String.valueOf(false);
		}
	}
	
	/*必须为注册手机号*/
	@RequestMapping("/doCheckMobile")
	@ResponseBody
	public String doCheckMobile(String mobile){
		Criteria criteria = new Criteria();
		criteria.put("mobile", mobile);
		List<TbAdminUser> adminUsers = tbAdminUserService.selectByObject(criteria);
		if(adminUsers.size()>0){
			return String.valueOf(true);
		}else{
			return String.valueOf(false);
		}
	}
	
	/*必须为注册邮箱*/
	@RequestMapping("/doCheckEmail")
	@ResponseBody
	public String doCheckEmail(String email){
		Criteria criteria = new Criteria();
		criteria.put("email", email);
		List<TbAdminUser> adminUsers = tbAdminUserService.selectByObject(criteria);
		if(adminUsers.size()>0){
			return String.valueOf(true);
		}else{
			return String.valueOf(false);
		}
	}
	
	/*通过手机号重置密码*/
	@RequestMapping("/doUpdatePasswordByMobile")
	public String doUpdatePasswordByMobile(TbAdminUserDto adminUserDto){
		Criteria criteria = new Criteria();
		criteria.put("mobile", adminUserDto.getMobile());
		List<TbAdminUser> adminUsers = tbAdminUserService.selectByObject(criteria);
		if(adminUsers.size()>0){
			TbAdminUser adminUser = adminUsers.get(0);
			if(StringUtil.isNotBlank(adminUserDto.getPassword())){
				adminUser.setPassword(MD5Utils.md5(adminUserDto.getPassword(),"UTF-8"));
				if(tbAdminUserService.updateByPrimaryKey(adminUser)==1){
					return "index/login";
				}
			}
			
		}
		
		return "index/login";
	}
	
	/*通过email重置密码*/
	@RequestMapping("/doUpdatePasswordByEmail")
	public String doUpdatePasswordByEmail(TbAdminUserDto adminUserDto){
		Criteria criteria = new Criteria();
		criteria.put("email", adminUserDto.getEmail());
		List<TbAdminUser> adminUsers = tbAdminUserService.selectByObject(criteria);
		if(adminUsers.size()>0){
			TbAdminUser adminUser = adminUsers.get(0);
			if(StringUtil.isNotBlank(adminUserDto.getPassword())){
				adminUser.setPassword(MD5Utils.md5(adminUserDto.getPassword(),"UTF-8"));
				if(tbAdminUserService.updateByPrimaryKey(adminUser)==1){
					return "index/login";
				}
			}
		}
		
		return "index/login";
	}
	
	/*重新获取验证码*/
	@RequestMapping("/doGetCheckCode")
	@ResponseBody
	public String doGetCheckCode(){
		int random  =(int)((Math.random()*9+1)*100000);
		
		session.setAttribute("checkCodeRandom",random);
		 
		 return String.valueOf(random);
	}
}
