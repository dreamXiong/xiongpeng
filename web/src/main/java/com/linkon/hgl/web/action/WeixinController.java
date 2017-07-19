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
import java.util.ArrayList;
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

import com.liguo.hgl.common.CodeMappingClass;
import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.common.MessageEnum;
import com.liguo.hgl.common.SpringContextUtil;
import com.liguo.hgl.common.WeChatMenuKey;
import com.liguo.hgl.common.WeChatMsgTypeEnum;
import com.liguo.hgl.common.crypt.AesException;
import com.liguo.hgl.common.crypt.WXBizMsgCrypt;
import com.liguo.hgl.common.message.ReqBaseMessage;
import com.liguo.hgl.common.message.ReqEventPushMessage;
import com.liguo.hgl.common.message.ReqGroupsCreateMessage;
import com.liguo.hgl.common.message.ReqMenuAddconditionalMessage;
import com.liguo.hgl.common.message.ReqMenuDelconditionalMessage;
import com.liguo.hgl.common.message.ReqMenuMessage;
import com.liguo.hgl.common.message.ReqMsgPushMessage;
import com.liguo.hgl.common.message.RespBaseMessage;
import com.liguo.hgl.common.message.RespGroupsCreateMessage;
import com.liguo.hgl.exceptions.WapServiceException;
import com.liguo.hgl.proxydao.dto.TbProgramInfoDto;
import com.liguo.hgl.proxydao.util.StringUtil;
import com.liguo.hgl.service.IMessageConvert;
import com.liguo.hgl.service.IProgramService;
import com.liguo.hgl.service.IWeChatExecuteService;

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
@RequestMapping("/weixin")
public class WeixinController {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private String VOTERESULT_PAGE = "/voteResult/index";
    
    @Autowired
    @Qualifier("menuCreateExecuteService")
    protected IWeChatExecuteService menuCreateExecuteService;
    @Autowired
    @Qualifier("menuAddconditionalExecuteService")
    protected IWeChatExecuteService menuAddconditionalExecuteService;
    @Autowired
    @Qualifier("menuDelconditionalExecuteService")
    protected IWeChatExecuteService menuDelconditionalExecuteService;
    @Autowired
    @Qualifier("groupsCreateExecuteService")
    protected IWeChatExecuteService groupsCreateExecuteService;
    @Autowired
    protected IMessageConvert messageConvert;

    @Autowired
	private IProgramService programService;
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(HttpServletRequest request, HttpServletResponse response) throws IOException {

        logger.info("RemoteAddr: " + request.getRemoteAddr());
        logger.info("QueryString: " + request.getQueryString());
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");

        WXBizMsgCrypt wxBizMsgCrypt = null;
        try {
            wxBizMsgCrypt = new WXBizMsgCrypt(HglContants.TOKEN_FOR_KWINER, HglContants.AES_KEY_FOR_KWINER, HglContants.APP_ID_FOR_KWINER);
            makeResponse(response, "text/html", wxBizMsgCrypt.verifyUrl(signature, timestamp, nonce, echostr, false));
        } catch (AesException e) {
            e.printStackTrace();
            makeResponse(response, "text/html", "fail");
        }
        return null;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public void weChatRecive(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String respStr = "";
        RespBaseMessage respMessage = new RespBaseMessage();
        String requestContent = getRequestContent(request);
        logger.info("接收到微信服务Post请求参数：" + requestContent);
        try {
            String msgType = StringUtil.getTagText(requestContent, "MsgType");
            ReqBaseMessage reqBaseMessage = null;
            IWeChatExecuteService weChatExecuteService = null;
            if(WeChatMsgTypeEnum.EVENT.name().equalsIgnoreCase(msgType)){
                ReqEventPushMessage reqEventPushMessage = messageConvert.convertFromXml(ReqEventPushMessage.class, requestContent);
                reqBaseMessage = reqEventPushMessage;
                CodeMappingClass codeMappingClass = CodeMappingClass.queryCodeMappingClass(reqEventPushMessage.getMsgType() + "_" + reqEventPushMessage.getEvent());
                weChatExecuteService = SpringContextUtil.getBean(StringUtil.firstCharToLower(codeMappingClass.getExecuteClass()));
                respMessage = weChatExecuteService.execute(reqBaseMessage);
                respStr = messageConvert.convertToXml(respMessage);
            }else if(WeChatMsgTypeEnum.TEXT.name().equalsIgnoreCase(msgType)){
                ReqMsgPushMessage reqMsgPushMessage = messageConvert.convertFromXml(ReqMsgPushMessage.class, requestContent);
                reqBaseMessage = reqMsgPushMessage;
                CodeMappingClass codeMappingClass = CodeMappingClass.queryCodeMappingClass(msgType);
                weChatExecuteService = SpringContextUtil.getBean(StringUtil.firstCharToLower(codeMappingClass.getExecuteClass()));
                respMessage = weChatExecuteService.execute(reqBaseMessage);
            }
        } catch (WapServiceException e) {
            e.printStackTrace();
            logger.error("系统接收微信推送异常", e);
            respMessage.setErrmsg(e.getMessage());
            respStr = e.getMessage();
        }
        logger.debug("返回微信服务器信息:" + respMessage);
        makeResponse(response, "text/xml", respStr);
    }

    @RequestMapping(value = "/menu/create")
    @ResponseBody
    public RespBaseMessage menuCreate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        RespBaseMessage respBaseMessage = new RespBaseMessage();
        ReqMenuMessage menuMessage = new ReqMenuMessage();
        List<ReqMenuMessage.Button> buttons = new ArrayList<ReqMenuMessage.Button>();
        ReqMenuMessage.Button botton1 = new ReqMenuMessage.Button();
        botton1.setName(WeChatMenuKey.YEAR_MAIN.getValue());
        List<ReqMenuMessage.Button.SubButton> subButtons = new ArrayList<ReqMenuMessage.Button.SubButton>();
        ReqMenuMessage.Button.SubButton subButton1 = new ReqMenuMessage.Button.SubButton();
        subButton1.setName(WeChatMenuKey.YEAR_SIGN_IN.getValue());
        subButton1.setType(WeChatMenuKey.YEAR_SIGN_IN.getType());
        subButton1.setKey(WeChatMenuKey.YEAR_SIGN_IN.getCode());
        subButtons.add(subButton1);
        ReqMenuMessage.Button.SubButton subButton2 = new ReqMenuMessage.Button.SubButton();
        subButton2.setName(WeChatMenuKey.YEAR_WIN.getValue());
        subButton2.setType(WeChatMenuKey.YEAR_WIN.getType());
        subButton2.setUrl(WeChatMenuKey.YEAR_WIN.getCode());
        subButtons.add(subButton2);
        botton1.setSubButton(subButtons);
        buttons.add(botton1);
        ReqMenuMessage.Button button2 = new ReqMenuMessage.Button();
        button2.setName(WeChatMenuKey.YEAR_VOTE.getValue());
        button2.setType(WeChatMenuKey.YEAR_VOTE.getType());
        button2.setUrl(WeChatMenuKey.YEAR_VOTE.getCode());
        buttons.add(button2);
        ReqMenuMessage.Button button3 = new ReqMenuMessage.Button();
        button3.setName(WeChatMenuKey.YEAR_HELP.getValue());
        button3.setType(WeChatMenuKey.YEAR_HELP.getType());
        button3.setUrl(WeChatMenuKey.YEAR_HELP.getCode());
        buttons.add(button3);

        menuMessage.setButton(buttons);
        try {
            respBaseMessage = menuCreateExecuteService.execute(menuMessage);
        } catch (WapServiceException e) {
            e.printStackTrace();
            respBaseMessage.setErrcode(e.getErrorCode());
            respBaseMessage.setErrmsg(e.getMessage());
        }
        return respBaseMessage;
    }

    @RequestMapping(value = "/menu/createRule")
    @ResponseBody
    public RespBaseMessage menuCreateRule(HttpServletRequest request, HttpServletResponse response) throws IOException {
        RespBaseMessage respBaseMessage = new RespBaseMessage();
        ReqMenuAddconditionalMessage menuMessage = new ReqMenuAddconditionalMessage();
        List<ReqMenuAddconditionalMessage.Button> buttons = new ArrayList<ReqMenuAddconditionalMessage.Button>();
        ReqMenuAddconditionalMessage.Button botton1 = new ReqMenuAddconditionalMessage.Button();
        botton1.setName(WeChatMenuKey.YEAR_WIN.getValue());
        botton1.setType(WeChatMenuKey.YEAR_WIN.getType());
        botton1.setUrl(WeChatMenuKey.YEAR_WIN.getCode());
        buttons.add(botton1);
        ReqMenuAddconditionalMessage.Button button2 = new ReqMenuAddconditionalMessage.Button();
        button2.setName(WeChatMenuKey.YEAR_VOTE.getValue());
        button2.setType(WeChatMenuKey.YEAR_VOTE.getType());
        button2.setUrl(WeChatMenuKey.YEAR_VOTE.getCode());
        buttons.add(button2);
        ReqMenuAddconditionalMessage.Button button3 = new ReqMenuAddconditionalMessage.Button();
        button3.setName(WeChatMenuKey.YEAR_HELP.getValue());
        button3.setType(WeChatMenuKey.YEAR_HELP.getType());
        button3.setUrl(WeChatMenuKey.YEAR_HELP.getCode());
        buttons.add(button3);
        ReqMenuAddconditionalMessage.Matchrule matchrule = new ReqMenuAddconditionalMessage.Matchrule();
        matchrule.setGroupId(HglContants.SIGN_IN_GROUP_ID);
        menuMessage.setMatchrule(matchrule);
        menuMessage.setButton(buttons);
        try {
            respBaseMessage = menuAddconditionalExecuteService.execute(menuMessage);
        } catch (WapServiceException e) {
            e.printStackTrace();
            respBaseMessage.setErrcode(e.getErrorCode());
            respBaseMessage.setErrmsg(e.getMessage());
        }
        return respBaseMessage;
    }

    @RequestMapping(value = "/menu/delRule")
    @ResponseBody
    public RespBaseMessage menuDelRule(HttpServletRequest request, HttpServletResponse response) throws IOException {
        RespBaseMessage respBaseMessage = new RespBaseMessage();
        ReqMenuDelconditionalMessage menuMessage = new ReqMenuDelconditionalMessage();
        menuMessage.setMenuid(HglContants.SIGN_IN_MENU_ADDCONDITIONAL);
        try {
            respBaseMessage = menuDelconditionalExecuteService.execute(menuMessage);
        } catch (WapServiceException e) {
            e.printStackTrace();
            respBaseMessage.setErrcode(e.getErrorCode());
            respBaseMessage.setErrmsg(e.getMessage());
        }
        return respBaseMessage;
    }

    @RequestMapping(value = "/groups/create")
    @ResponseBody
    public RespBaseMessage groupsCreate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        if(StringUtil.isBlank(name)){
            return null;
        }
        RespGroupsCreateMessage respMessage = new RespGroupsCreateMessage();
        ReqGroupsCreateMessage reqMessage = new ReqGroupsCreateMessage();
        ReqGroupsCreateMessage.Group group = new ReqGroupsCreateMessage.Group();
        group.setName(name);
        reqMessage.setGroup(group);
        try {
            RespBaseMessage respBaseMessage = groupsCreateExecuteService.execute(reqMessage);
            if(MessageEnum.isSuccess(respBaseMessage.getErrcode())){
                respMessage = (RespGroupsCreateMessage)respBaseMessage;
            }
        } catch (WapServiceException e) {
            e.printStackTrace();
            respMessage.setErrcode(e.getErrorCode());
            respMessage.setErrmsg(e.getMessage());
        }
        return respMessage;
    }

    /**
     * 获取请求的内容
     * @param request 一个http请求对象
     * @return 获取的请求内容的字符串
     * @throws java.io.UnsupportedEncodingException
     * @throws IOException
     */
    private String getRequestContent(HttpServletRequest request) throws UnsupportedEncodingException, IOException {

        BufferedReader rd = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();

        return sb.toString();
    }

    /**
     * 将内容从响应输出流中发送给请求方。
     * @param resp 响应对象
     * @param contentType 设置格式
     * @param string 响应的内容
     * @throws IOException
     */
    private void makeResponse(HttpServletResponse resp, String contentType, String string) throws IOException {
        resp.setContentType(contentType);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(resp.getOutputStream(), "utf-8"));
        out.write(string);
        out.flush();
        out.close();
    }
    
    
    @RequestMapping(value = "/vote", method = RequestMethod.GET)
    public String vote(Model model) throws IOException {
        //获取弹幕消息
    	
        return VOTERESULT_PAGE;
    }

    @RequestMapping(value = "/voteTop", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> voteTop(HttpServletRequest request) throws IOException {
    	Map<String,Object> map = new HashMap<String,Object>();
    	List<TbProgramInfoDto> list = programService.queryTopProgramList(6);
    	map.put("code", "0000");
    	map.put("data", list);
        return map;
    }
}
