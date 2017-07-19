package com.linkon.hgl.web.action;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.common.crypt.AesException;
import com.liguo.hgl.common.crypt.WXBizMsgCrypt;
import com.liguo.hgl.proxydao.dto.WebUserDto;
import com.liguo.hgl.proxydao.model.TbWebUser;
import com.liguo.hgl.proxydao.model.TbWeixinUser;
import com.liguo.hgl.proxydao.model.Token;
import com.liguo.hgl.proxydao.model.WeiXinOauth2Token;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.proxydao.util.CommonUtil;
import com.liguo.hgl.proxydao.util.IdCreator;
import com.liguo.hgl.service.TbWebUserService;

@Controller
@RequestMapping("/weixin")
public class WeixinController extends IBaseController {
    
	@Autowired
	private TbWebUserService tbWebUserService;
	
	@Autowired
	private RegisterController registerController;
	
	@Autowired
	private LoginController loginController;
	
	@Autowired
	private IdCreator userNameCode;
	
	/**
	 * 获取code方法
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(value = "/getOpenWeixinUrl",method = RequestMethod.POST)
    public @ResponseBody String getOpenWeixinUrl(String recommendcode) throws Exception{
    	//拼接获取微信code的url连接
    	String openUrl = HglContants.WEIXIN_OPEN_URL.replace("APPID", HglContants.WEIXIN_APP_ID).replace("REDIRECT_URI", CommonUtil.urlEncodeUTF8(HglContants.WEIXIN_CALL_METHOD)).replace("SCOPE", HglContants.WEIXIN_SNSAPI_BASE);
    	//判断是否有推荐码
    	if(recommendcode != null && !"".equals(recommendcode)){
    		openUrl = openUrl.replace("STATE", recommendcode);
    	}
    	return openUrl;
    }

    /**
     * 提供给微信接口,微信授权使用
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/weixinApi", method = RequestMethod.GET)
    public String index(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("RemoteAddr: " + request.getRemoteAddr());
        logger.info("QueryString: " + request.getQueryString());
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        WXBizMsgCrypt wxBizMsgCrypt = null;
        try {
            wxBizMsgCrypt = new WXBizMsgCrypt(HglContants.WEIXIN_TOKEN, HglContants.WEIXIN_ENCODINGAESKEY, HglContants.WEIXIN_APP_ID);
            makeResponse(response, "text/html", wxBizMsgCrypt.verifyUrl(signature, timestamp, nonce, echostr, false));
        } catch (AesException e) {
            e.printStackTrace();
            makeResponse(response, "text/html", "fail");
        }
        return null;
    }
    
    /**
     * 获取openID,微信回调,微信一键登录处理逻辑
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getWeixinOpenIdCall")
    public String getWeixinOpenIdCall(HttpServletRequest request, HttpServletResponse response,Model model) throws Exception{
        try {
		    WeiXinOauth2Token weiXinOauth2Token = getOauth2AccessToken(request,response);
		    if(weiXinOauth2Token != null){
		    	String openId = weiXinOauth2Token.getOpenId();
		    	String state = weiXinOauth2Token.getState();
		    	logger.info("--------------openId: " + openId);
		    	logger.info("--------------state: " + state);
		    	WebUserDto tbWebUser = tbWebUserService.selectByOpenId(openId);
		    	//等于null,注册,否则登录
    	    	if(tbWebUser == null){
    	    		tbWebUser = weixinRegister(state,openId,tbWebUser,request,model);
    	    	}else{
		    		//如果自动登录标示不等于1的,更新为1 自动登录
    	    		if(!"1".equals(String.valueOf(tbWebUser.getAutoLoginFlag()))){
		    			int updCount = tbWebUserService.updateAutoLoginFlag(tbWebUser.getId(),0,1,openId);
		    			logger.info("更新自动登录标示记录数: " + updCount);
		    		}
    	    	}
    	    	//跳转登录操作逻辑
	    		loginController.pageJump(tbWebUser, request, model);
	    		return request.getContextPath() + "/index/autoLogin";
		    }
		} catch (Exception e) {
			logger.error("微信回调error: "+e.getMessage());
			e.printStackTrace();
		}
        return "redirect:"+request.getContextPath() + "/";
    }
    
    /**
     * 获取openID,微信回调,自动登录处理逻辑
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/weixinOpenIdCallAutoLogin")
    public String weixinOpenIdCallAutoLogin(HttpServletRequest request, HttpServletResponse response,Model model) throws Exception{
        try {
		    WeiXinOauth2Token weiXinOauth2Token = getOauth2AccessToken(request,response);
		    if(weiXinOauth2Token != null){
		    	String openId = weiXinOauth2Token.getOpenId();
		    	String state = weiXinOauth2Token.getState();
		    	logger.info("--------------openId: " + openId);
		    	logger.info("--------------state: " + state);
		    	WebUserDto tbWebUser = tbWebUserService.selectByOpenId(openId,1);
		    	//跳转到登录
    	    	if(tbWebUser == null){
    	    		this.session.setAttribute("session_openId", openId);
    	    		return "redirect:" + request.getContextPath() + "/login/";
    	    	}else{
    	    		//跳转登录操作逻辑
		    		loginController.pageJump(tbWebUser, request, model);
					return request.getContextPath() + "/index/autoLogin";
    	    	}
		    }
		} catch (Exception e) {
			logger.error("微信回调error: "+e.getMessage());
			e.printStackTrace();
		}
        return "redirect:"+request.getContextPath() + "/";
    }
    
    /**
     * 获取openID,微信回调,当登录成功后获取openid业务逻辑
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/weixinOpenIdCallLogin")
    public String weixinOpenIdCallLogin(HttpServletRequest request, HttpServletResponse response,Model model) throws Exception{
        try {
		    WeiXinOauth2Token weiXinOauth2Token = getOauth2AccessToken(request,response);
		    if(weiXinOauth2Token != null){
		    	String openId = weiXinOauth2Token.getOpenId();
		    	String state = weiXinOauth2Token.getState();
		    	logger.info("--------------openId: " + openId);
		    	logger.info("--------------state: " + state);
		    	WebUserDto tbWebUser = this.getLoginUser();
    	    	if(tbWebUser == null){
    	    		return "redirect:" + request.getContextPath() + "/login/";
    	    	}else{
    	    		//如果是自动绑定登录
    	    		if("1".equals(state)){
    	    			if(!openId.equals(tbWebUser.getOpenId())){
    	    				//把当前的openid更新为获取的
    	    				int count = tbWebUserService.updateUserOpenId(tbWebUser.getId(),openId);
    	    				logger.info("两个openID不一致,更新openId条数: " + count);
    	    			}
    	    			//根据openid更新自动绑定标示为0,然后把当前用户更新为自动登录标示1
    	    			int updCount = tbWebUserService.updateAutoLoginFlag(tbWebUser.getId(),0,1,openId);
    	    			logger.info("更新自动登录标示条数: " + updCount);
    	    		}
    	    		//重新设置openId,防止登录别人的账户,openId不一致无法支付
    	    		tbWebUser.setOpenId(openId);
    	    		//跳转登录操作逻辑
		    		loginController.pageJump(tbWebUser, request, model);
					return request.getContextPath() + "/index/autoLogin";
    	    	}
		    }
		} catch (Exception e) {
			logger.error("微信回调error: "+e.getMessage());
			e.printStackTrace();
		}
        return "redirect:"+request.getContextPath() + "/";
    }
    
    /**
     * 获取openID
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public WeiXinOauth2Token getOauth2AccessToken(HttpServletRequest request,HttpServletResponse response) throws Exception{
    	//定义返回的对象
    	WeiXinOauth2Token wat = null;
    	//将请求、响应的编码均设置为UTF-8（防止中文乱码）
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		//微信返回的code
		String code = request.getParameter("code");
		//微信返回的state
		String state = request.getParameter("state");
		logger.info("#################code: " + code);
		logger.info("#################state: " + state);
		//判断code是否不是authdeny
		if (!"authdeny".equals(code)) {
	        //拼接获取openId的url
	        String requestUrl = HglContants.WEIXIN_API_URL.replace("APPID", HglContants.WEIXIN_APP_ID).replace("SECRET", HglContants.WEIXIN_APP_SECRET).replace("CODE", code).replace("GRANT_TYPE", HglContants.WEIXIN_GRANT_TYPE);
	        logger.info("#################requestUrl: " + requestUrl);
	        //发送获取openid的get请求
	        JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
	        logger.info("#################jsonObject: " + jsonObject.toJSONString());
	        if (null != jsonObject) {
	            try {
	                wat = new WeiXinOauth2Token();
	                wat.setAccessToken(jsonObject.getString("access_token"));
	                wat.setExpiresIn(jsonObject.getInteger("expires_in"));
	                wat.setRefeshToken(jsonObject.getString("refresh_token"));
	                wat.setOpenId(jsonObject.getString("openid"));
	                wat.setScope(jsonObject.getString("scope"));
	                wat.setState(state);
	            } catch (Exception e) {
	                wat = null;
	                String errorCode = jsonObject.getString("errcode");
	                String errorMsg = jsonObject.getString("errmsg");
	                logger.error("获取网页授权凭证失败 errcode{},errMsg", errorCode, errorMsg);
	            }
	        }
		}
        return wat;
    }
    
    /**
     * 通过网页授权获取用户信息
     * 
     * @param accessToken 网页授权接口调用凭证
     * @param openId 用户标识
     * @return SNSUserInfo
     */
    public TbWeixinUser getWeixinUserInfo(String openId) throws Exception{
    	Token token = (Token)this.session.getAttribute(HglContants.SESSION_TOKEN);
    	if(token != null){
    		//判断token失效时间，获取token
    		long diff = new Date().getTime() - token.getExpiresDate().getTime();
    		if((diff/1000) > 6500){
        		token = CommonUtil.getToken(HglContants.WEIXIN_APP_ID, HglContants.WEIXIN_APP_SECRET);
        		token.setExpiresDate(new Date());
        		this.session.setAttribute(HglContants.SESSION_TOKEN,token);
    		}
    	}else{
    		//获取token
    		token = CommonUtil.getToken(HglContants.WEIXIN_APP_ID, HglContants.WEIXIN_APP_SECRET);
    		token.setExpiresDate(new Date());
    		this.session.setAttribute(HglContants.SESSION_TOKEN,token);
    	}
    	String accessToken = token.getAccessToken();
    	TbWeixinUser weixinUser = null;
        // 拼接请求地址
        String requestUrl = HglContants.WEIXIN_USERINFO_URL.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
        // 通过网页授权获取用户信息
        JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
        if (null != jsonObject) {
            try {
            	weixinUser = new TbWeixinUser();
            	// 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息
            	weixinUser.setSubscribe(jsonObject.getInteger("subscribe"));
                // 用户的标识
            	weixinUser.setOpenId(jsonObject.getString("openid"));
                // 昵称
            	weixinUser.setNickname(jsonObject.getString("nickname"));
                // 性别（1是男性，2是女性，0是未知）
            	weixinUser.setSex(jsonObject.getString("sex"));
                // 用户所在国家
            	weixinUser.setCountry(jsonObject.getString("country"));
                // 用户所在省份
            	weixinUser.setProvince(jsonObject.getString("province"));
                // 用户所在城市
            	weixinUser.setCity(jsonObject.getString("city"));
                // 用户头像
            	weixinUser.setHeadimgurl(jsonObject.getString("headimgurl"));
            	//语言
            	weixinUser.setLanguage(jsonObject.getString("language"));
            	//用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
            	weixinUser.setSubscribeTime(jsonObject.getString("subscribe_time"));
            	//只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。详见：获取用户个人信息（UnionID机制）
            	weixinUser.setUnionid(jsonObject.getString("unionid"));
            	//公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注
            	weixinUser.setRemark(jsonObject.getString("remark"));
            	//用户所在的分组ID
            	weixinUser.setGroupid(jsonObject.getString("groupid"));
            } catch (Exception e) {
            	weixinUser = null;
                int errorCode = jsonObject.getInteger("errcode");
                String errorMsg = jsonObject.getString("errmsg");
                logger.error("获取用户信息失败 errcode:{} errmsg:{}", errorCode, errorMsg);
            }
        }
        return weixinUser;
    }

	/**
	 * 将内容从响应输出流中发送给请求方。
	 * @param resp 响应对象
	 * @param contentType 设置格式
	 * @param string 响应的内容
	 * @throws IOException
	 */
	private void makeResponse(HttpServletResponse resp, String contentType,String string) throws IOException {
		resp.setContentType(contentType);
		PrintWriter out = new PrintWriter(new OutputStreamWriter(resp.getOutputStream(), "utf-8"));
		out.write(string);
		out.flush();
		out.close();
	}

	/**
	 * 获取临时的用户名
	 * 
	 * @return
	 */
	public String getTempUserName() {
		return "temp_user_" + userNameCode.create();
	}
    
    /**
     * 微信登录,先根据openid查询,没有根据openid注册
     * @param state
     * @param openId
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    public WebUserDto weixinRegister(String state,String openId,WebUserDto tbWebUser,HttpServletRequest request,Model model) throws Exception{
    	TbWeixinUser weixinUser = getWeixinUserInfo(openId);
		//封装参数调用注册并登录
		if(weixinUser != null){
			TbWebUser webUser = new TbWebUser();
			webUser.setName(weixinUser.getNickname());
			webUser.setOpenId(openId);
			webUser.setUserName(getTempUserName());
			webUser.setPassword(getTempUserName());
			webUser.setAutoLoginFlag(1);//自动登录
			//如果推荐码为空指点惠给力用户的推荐码
			if(StringUtils.isBlank(state) || "STATE".equals(state)){
				state = HglContants.HGL_RECOMMENDCODE;
			}
			webUser.setRecommendCode(state);
			String province = weixinUser.getProvince() == null ? "" : weixinUser.getProvince();
			String city = weixinUser.getCity() == null ? "" : weixinUser.getCity();
			webUser.setAddress(province+city);
			registerController.doRegisterUser(webUser,weixinUser.getHeadimgurl(),model);
			tbWebUser = tbWebUserService.selectByOpenId(openId);
		}
		return tbWebUser;
    }
    
    /**
     * 签到
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/sign", method = RequestMethod.GET)
    public String sign(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	return "/weixin/sign";
    }
    
    /**
     * 公司简介
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/companyDescription", method = RequestMethod.GET)
    public String companyDescription(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	return "/weixin/companyDescription";
    }
    
    /**
     * 商家入驻
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/merchantsEnter", method = RequestMethod.GET)
    public String merchantsEnter(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	return "/weixin/merchantsEnter";
    }
    
    /**
     * 招商政策
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/merchantsPolicy", method = RequestMethod.GET)
    public String merchantsPolicy(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	return "/weixin/merchantsPolicy";
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
