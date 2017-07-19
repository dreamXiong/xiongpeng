/*
 * Copyright (c)2014 快诺迅汇(长沙)网络技术股份有限公司。
 * 版权所有
 */
package com.linkon.hgl.web.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liguo.hgl.common.*;
import com.liguo.hgl.common.crypt.AesException;
import com.liguo.hgl.common.crypt.WXBizMsgCrypt;
import com.liguo.hgl.common.message.*;
import com.liguo.hgl.exceptions.WapServiceException;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbTalk;
import com.liguo.hgl.proxydao.model.TbWeixinPassUser;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.proxydao.util.StringUtil;
import com.liguo.hgl.service.IDemoService;
import com.liguo.hgl.service.IErnieService;
import com.liguo.hgl.service.IMessageConvert;
import com.liguo.hgl.service.IWeChatExecuteService;
import com.liguo.hgl.service.IWeixinTalkService;

/**
 * 支付结算业务入口Controller<br>
 * 用于各个市场调用支付结算入口，市场定义参照交易平台通道表（marketchanneltable）定义的市场通道
 * 根据交易平台通道表srvprivatekey字段解密报文，将来是否配置签名验签证书路径待定
 * 根据交易码将报文转换成对应的业务消息
 * 根据交易码调用对应的业务逻辑服务
 * 返回相应的业务报文
 *
 * @author 王丹
 * @FileName PlatformController.java<br>
 * @Language Java<br>
 * @date 2015-05-06<br>
 */
@Controller
@RequestMapping("/barrage")
public class BarrageController {

	private String BARRAGE_PAGE = "/barrage/barrage";
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected IDemoService demoService;
    @Autowired
    protected IErnieService ernieService;
    @Autowired
    protected IWeixinTalkService weixinTalkService;
    
    
    

    /**
     * 弹幕页面
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/barrage")
    public String barrage(PageDto page,HttpServletRequest request, HttpServletResponse response,Model model) throws IOException {
    	page.pageSize=5;
    	List<TbWeixinPassUser> queryRandeUserList = ernieService.queryRandeUserList(page); 
    	request.setAttribute(HglContants.PAGE_DTO_ID, page);
    	model.addAttribute("dataDto", queryRandeUserList);
        //获取弹幕消息
        return BARRAGE_PAGE;
    }
    
    /**
     * 弹幕页面
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/searchResult")
    public String searchResult(PageDto page,HttpServletRequest request, HttpServletResponse response,Model model) throws IOException {
    	
    	List<TbWeixinPassUser> queryRandeUserList = ernieService.queryRandeUserList(page); 
    	request.setAttribute("page", page);
    	model.addAttribute("dataDto", queryRandeUserList);
        //获取弹幕消息
        return "/barrage/talkList";
    }
    
    /**
     * 获取弹幕列表
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/barrageList", method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> barrageList(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	Map<String,Object> map = new HashMap<String,Object>();
    	
    	Criteria condition = new Criteria();
    	condition.put("isDelete", "0");//未删除
    	condition.put("isShow", "1");//审核通过
    	/*condition.setMysqlOffset(0);
    	condition.setMysqlLength(1);*/
    	condition.setOrderByClause("create_time asc");
    	
        //获取弹幕消息
    	List<TbTalk> list = weixinTalkService.getWeixinTalkList(condition);
    	if(list.isEmpty() == false){
    		for(TbTalk talk:list){
    			talk.setIsDelete(1);
        		weixinTalkService.updateWeixinTalkStatut(talk);//设置为删除状态
    		}
    		map.put("talkList", list);
        	map.put("listSize", list.size());
    	}else{
    		map.put("talkList", null);
        	map.put("listSize", 0);
    	}
    	
    	map.put("code", "0000");
    	
    	
        return map;
    }
    

}
