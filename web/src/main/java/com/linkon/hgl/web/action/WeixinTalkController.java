/*
 * Copyright (c)2014 快诺迅汇(长沙)网络技术股份有限公司。
 * 版权所有
 */
package com.linkon.hgl.web.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbTalk;
import com.liguo.hgl.proxydao.util.Log;
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
@RequestMapping("/talk")
public class WeixinTalkController {

	private String TALK_PAGE = "/barrage/talk";
	private String TALK_PAGE_LIST = "/barrage/talkList";
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    protected IWeixinTalkService weixinTalkService;
    
    
    /**
     * 弹幕审核页面
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/auth")
    public String authList(Model model) throws IOException {
    	//获取待审核弹幕消息
        
    	Criteria condition = new Criteria();
    	condition.put("isShow", "0");//待审核
    	condition.put("isDelete", "0");//未删除
    	condition.setMysqlOffset(0);
    	condition.setMysqlLength(10);
    	condition.setOrderByClause("create_time asc");
    	
        //获取弹幕消息
    	List<TbTalk> list = weixinTalkService.getWeixinTalkList(condition);
    	
    	model.addAttribute("dataDto", list);
        return TALK_PAGE;
    }
    /**
     * 弹幕页面
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/authOpt", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> authOpt(HttpServletRequest request) throws IOException {
    	String[] talkId = request.getParameterValues("talkId");
    	Map<String,Object> map = new HashMap<String,Object>();
    	for(String id:talkId){
    		TbTalk data = weixinTalkService.getWeixinTalkById(Integer.parseInt(id));
        	if(null != data){
        		data.setIsShow(1);
        		weixinTalkService.updateWeixinTalkStatut(data);
        		
        	}
    	}
    	
    	map.put("code", "0000");
        return map;
    }
    

    @RequestMapping(value = "/refuseOpt")
    @ResponseBody
    public Map<String,Object> refuseOpt(String talkId){
    	Map<String,Object> map = new HashMap<String,Object>();
    	
    	try{
    		TbTalk data = weixinTalkService.getWeixinTalkById(Integer.parseInt(talkId));
        	if(null != data){
        		
        		data.setIsShow(2);//审核不通过
        		data.setIsDelete(1);//同时设置为删除状态
        		
        		weixinTalkService.updateWeixinTalkStatut(data);
        		map.put("code", "0000");
        	}else{
        		map.put("code", "0001");
        	}
    	}catch(Exception e){
    		logger.error("审核出错："+ e.getMessage());
    		map.put("code", "-1");
    		e.printStackTrace();
    	}
    	
        return map;
    }
    
    
    /**
     * 弹幕审核页面
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/flushList")
    public String flushList(Model model) throws IOException {
    	//获取待审核弹幕消息
        
    	Criteria condition = new Criteria();
    	condition.put("isShow", "0");//待审核
    	condition.put("isDelete", "0");//待审核
    	condition.setMysqlOffset(0);
    	condition.setMysqlLength(10);
    	condition.setOrderByClause("create_time asc");
    	
        //获取弹幕消息
    	List<TbTalk> list = weixinTalkService.getWeixinTalkList(condition);
    	
    	model.addAttribute("dataDto", list);
        return TALK_PAGE_LIST;
    }
}
