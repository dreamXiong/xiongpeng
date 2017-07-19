/*
 * Copyright (c)2016 深圳立国网络技术有限公司。
 * 版权所有
 */

package com.linkon.admin.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.common.HglPage;
import com.liguo.hgl.proxydao.model.TbAdminUser;
import com.liguo.hgl.proxydao.page.PageDto;

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
	 * 获得当前登录用户
	 * 
	 * @return
	 */
	protected TbAdminUser getLoginUser() {
		Object user = this.session.getAttribute(HglContants.SESSION_KEY);
		if (user == null) {
			return new TbAdminUser();
		}
		return (TbAdminUser) user;
	}
	/**
	 * 获得当前登录用户id
	 * 
	 * @return
	 */
	protected Integer getLoginUserId() {
		TbAdminUser currentUser = getLoginUser();
		return currentUser.getId() == null ? null : Integer.valueOf(currentUser
				.getId());
	}

	/**
	 * 获得当前登录用户ShopId
	 * 
	 * @return
	 */
	protected List<Integer> getShopId() {
		List<Integer> shopIdList = (List<Integer>)session.getAttribute(HglContants.SHOP_ID_LIST);
		return shopIdList;
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

}
