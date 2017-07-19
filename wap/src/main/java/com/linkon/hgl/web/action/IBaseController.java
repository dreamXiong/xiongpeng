/*
 * Copyright (c)2016 深圳立国网络技术有限公司。
 * 版权所有
 */

package com.linkon.hgl.web.action;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.common.HglPage;
import com.liguo.hgl.proxydao.dto.WebUserDto;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.util.StringUtil;

/**
 * 所有的业务Controller都继承该抽象类。<br>
 * 
 * @FileName IBaseController.java。<br>
 * @Language java。
 * @date 2016-04-20。
 * @author 吴奇
 */
public abstract class IBaseController {
	protected HglPage HGLPAGE = new HglPage();
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	protected HttpSession session;

	public IBaseController() {
		String funName = getClass().getSimpleName().split("Controller")[0];
		String str1 = funName.substring(0, 1);
		funName = funName.replaceFirst(str1, str1.toLowerCase());
		this.HGLPAGE = new HglPage(funName);
	}
	
	/**
	 * 获得当前登录用户纵坐标 
	 * @return
	 */
	protected String getLon() {
		return (String)this.session.getAttribute(HglContants.LON);
	}
	/**
	 * 获得当前登录用户横坐标
	 * @return
	 */
	protected String getLat() {
		return (String)this.session.getAttribute(HglContants.LAT);
	}
	
	/**
	 * 获得当前登录用户的账户ID
	 * @return
	 */
	protected Integer getAccountId() {
		return getLoginUser().getAccountId();
	}
	
	/**
	 * 获得当前登录用户的积分ID
	 * @return
	 */
	protected Integer getIntegralId() {
		return getLoginUser().getIntegralId();
	}
	
	/**
	 * 获得当前登录用户绑定店铺结算方式
	 * @return
	 */
	/*protected Integer getSettlement() {
		return (Integer)this.session.getAttribute(HglContants.SETTLEMENT_KEY);
	}*/
	/**
	 * 获得当前登录用户
	 * 
	 * @return
	 */
	protected WebUserDto getLoginUser() {
		Object user = this.session.getAttribute(HglContants.SESSION_KEY);
		if (user == null) {
			return new WebUserDto();
		}

		return (WebUserDto) user;
	}

	/**
	 * 获得当前登录用户的绑定店铺ID
	 * @return
	 */
	protected Integer getRecommendShopId() {
		return getLoginUser().getRecommendShopId();
	}
	
	/**
	 * 获得当前登录用户id
	 * 
	 * @return
	 */
	protected Integer getLoginUserId() {
		WebUserDto currentUser = getLoginUser();
		return currentUser.getId() == null ? null : Integer.valueOf(currentUser
				.getId());
	}
	
	/**
	 * 获得当前登录用户openID
	 * 
	 * @return
	 */
	protected String getLoginOpenId() {
		WebUserDto currentUser = getLoginUser();
		return currentUser.getOpenId() == null ? null : currentUser.getOpenId();
	}

	/**
	 * 获得当前登录用户ShopId
	 * 
	 * @return
	 */
	protected Integer getShopId() {
		WebUserDto currentUser = getLoginUser();
		return currentUser.getShopId() == null ? null : Integer
				.valueOf(currentUser.getShopId());
	}
	

	/**
	 * 获得当前登录用户的价格优惠力度
	 * 返回0.98 表示9.8折
	 * 
	 * @return
	 */
	protected Double getUserSale() {
		WebUserDto currentUser = getLoginUser();
		return currentUser.getUserSale() == null ? 1 : Double
				.valueOf(currentUser.getUserSale());
		
	}
	
	/**
	 * 获得当前登录用户等级
	 * 
	 * @return
	 */
	protected Integer getUserlevel() {
		WebUserDto currentUser = getLoginUser();
		return currentUser.getExpLevel() == null ? null : Integer
				.valueOf(currentUser.getExpLevel());
	}
	
	/**
	 * 获得当前登录用户的经验值折算比例
	 * 返回1.2 表示 1元=1.2经验值
	 * 
	 * @return
	 */
	protected Double getExpProportion() {
		WebUserDto currentUser = getLoginUser();
		return currentUser.getExpProportion() == null ? null : Double
				.valueOf(currentUser.getExpProportion());
	}


	/**
	 * 返回主页面
	 * 
	 * @return
	 */
	protected String mainPage() {
		return this.HGLPAGE.MAIN_PAGE;
	}

	/**
	 * 返回查询结果页面
	 * 
	 * @return
	 */
	protected String resultPage() {
		return this.HGLPAGE.RESULT_PAGE;
	}

	/**
	 * 返回修改页面
	 * 
	 * @return
	 */
	protected String forwardPage() {
		return this.HGLPAGE.UPDATE_PAGE;
	}

	/**
	 * 返回详情页面
	 * 
	 * @return
	 */
	protected String infoDetailPage() {
		return this.HGLPAGE.DETAIL_PAGE;
	}

	// ///////////////////////////////////////////////////
	/**
	 * 初始化的页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/index")
	public String indexPage(PageDto page, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		init(page, request, response, model);
		return mainPage();
	}

	/**
	 * 初始化方法
	 */
	protected abstract void init(PageDto page, HttpServletRequest request,
			HttpServletResponse response, Model model);

	/**
	 * 查询方法
	 */
	public abstract String doSearchResult();

	protected void doSelect() {
	}

	protected void doSave() {
	}

	protected void doCreate() {
	}

	protected void doChange() {
	}

	protected void doDeleteSelect() {
	}

	protected void doDelete() {
	}
	
	/**
	 * 获得当前登录用户UserinfoId
	 * 
	 * @return
	 */
	protected Integer getUserinfoId() {
		WebUserDto currentUser = getLoginUser();
		return currentUser.getUserinfoId() == null ? null : Integer
				.valueOf(currentUser.getUserinfoId());
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
}
