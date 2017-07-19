/**
 * Copyright (c)2014 快诺迅汇(长沙)网络技术股份有限公司。
 * 版权所有
 */
package com.liguo.hgl.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.liguo.hgl.ceche.ProgramCeche;
import com.liguo.hgl.common.MessageEnum;
import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.common.WeChatMenuKey;
import com.liguo.hgl.common.message.ReqEventPushMessage;
import com.liguo.hgl.common.message.ReqGroupsMoveMessage;
import com.liguo.hgl.common.message.RespBaseMessage;
import com.liguo.hgl.common.message.RespEventPushMessage;
import com.liguo.hgl.common.message.RespUserInfoMessage;
import com.liguo.hgl.exceptions.WapServiceException;
import com.liguo.hgl.proxydao.dao.TbWeixinPassUserMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbWeixinPassUser;
import com.liguo.hgl.proxydao.util.ToolsUtil;
import com.liguo.hgl.service.IManagerService;
import com.liguo.hgl.service.ITokenService;
import com.liguo.hgl.service.IWeChatExecuteService;

/**
 * 类的功能描述<br>
 *
 * @author 王丹
 * @FileName MenuClickExecuteService.java<br>
 * @Language Java<br>
 * @date 2016-01-11<br>
 */
@Service("menuClickExecuteService")
public class MenuClickExecuteService implements IWeChatExecuteService<ReqEventPushMessage> {

    @Autowired
    protected TbWeixinPassUserMapper tbWeixinPassUserMapper;
    @Autowired
    protected ITokenService tokenService;
    @Autowired
    @Qualifier("queryUserInfoExecuteService")
    protected IWeChatExecuteService queryUserInfoExecuteService;
    @Autowired
    protected IManagerService managerService;

    @Autowired
    @Qualifier("customSendExecuteService")
    protected IWeChatExecuteService customSendExecuteService;
    @Autowired
    @Qualifier("groupsMoveExecuteService")
    protected IWeChatExecuteService groupsMoveExecuteService;
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public RespBaseMessage execute(ReqEventPushMessage reqMessage) throws WapServiceException {

        RespEventPushMessage respMessage = new RespEventPushMessage();
        respMessage.setFromUserName(reqMessage.getToUserName());
        respMessage.setToUserName(reqMessage.getFromUserName());
        respMessage.setCreateTime(reqMessage.getCreateTime());
        respMessage.setMsgType("text");
        respMessage.setFuncFlag("0");
        if (WeChatMenuKey.YEAR_SIGN_IN.getCode().equalsIgnoreCase(reqMessage.getEventKey())) {
            //是否开启
            if (managerService.isOpenSign()) {
                Criteria criteria = new Criteria();
                criteria.put("openId", reqMessage.getFromUserName());
                List<TbWeixinPassUser> passUserList = tbWeixinPassUserMapper.selectByObject(criteria);
                if (passUserList.isEmpty()) {
                    TbWeixinPassUser passUser = new TbWeixinPassUser();
                    passUser.setOpenId(reqMessage.getFromUserName());
                    passUser.setCreateTime(ToolsUtil.getDateYMDHMS_14bit());
                    passUser.setVersion(HglContants.DEFUAL_VERSION);
                    String queryUser = queryUser(passUser);
                    if (queryUser != null) {
                        respMessage.setContent(queryUser);
                        return respMessage;
                    }
                    tbWeixinPassUserMapper.insertSelective(passUser);
                    //moveGroup(reqMessage.getFromUserName());
                    ProgramCeche.addSignInUser(passUser);
                    respMessage.setContent("恭喜您已经成功签到，抽奖号码" + passUser.getId() + "，请牢记。");
                } else {
                    //已经签过到
                    //                    respMessage = new RespBaseMessage(MessageEnum.I1001);
                    //moveGroup(reqMessage.getFromUserName());
                    TbWeixinPassUser tbWeixinPassUser = passUserList.get(0);
                    String queryUser = queryUser(tbWeixinPassUser);
                    if (queryUser != null) {
                        respMessage.setContent(queryUser);
                        return respMessage;
                    }
                    tbWeixinPassUserMapper.updateByPrimaryKeySelective(tbWeixinPassUser);
                    ProgramCeche.addSignInUser(tbWeixinPassUser);
                    respMessage.setContent(MessageEnum.I1001.getMessageValue() + ",抽奖号码" + passUserList.get(0).getId());
                }
            } else {
                //签到已经关闭
                //                respMessage = new RespBaseMessage(MessageEnum.I1002);
                //                sendCustom(reqMessage.getFromUserName());
                respMessage.setContent(MessageEnum.I1002.getMessageValue());
            }
        }
        return respMessage;
    }

    private String queryUser(TbWeixinPassUser passUser) throws WapServiceException {

        Map<String, String> map = new HashMap<String, String>();
        map.put("access_token", tokenService.getToken());
        map.put("lang", HglContants.LANG);
        map.put("openid", passUser.getOpenId());
        RespBaseMessage respMessage = queryUserInfoExecuteService.execute(map);
        if (MessageEnum.isSuccess(respMessage.getErrcode())) {
            RespUserInfoMessage userInfoMessage = (RespUserInfoMessage) respMessage;
            passUser.setNickname(userInfoMessage.getNickname());
            passUser.setHeadimgurl(userInfoMessage.getHeadimgurl());
            if ("0".equals(userInfoMessage.getSubscribe())) {
                return "请先关注公众号！";
            }
        } else {
            logger.error("  ");
            logger.error(" 异常发生 " + respMessage.getErrcode());
            logger.error(" 异常发生 " + respMessage.getErrcode());
            logger.error(" 异常发生 " + respMessage.getErrcode());
            logger.error("  ");
            return "业务异常";
        }

        if (passUser.getNickname() == null || "".equals(passUser.getNickname()) || "".equals(passUser.getNickname().trim())) {
            return "您的昵称为空或者包含非法字符，请重新设置微信昵称。";
        }
        if (passUser.getHeadimgurl() == null || "".equals(passUser.getHeadimgurl()) || "".equals(passUser.getHeadimgurl().trim()) || !HttpSendService.getImg(passUser.getHeadimgurl())) {
            return "您的微信头像为空，请设置微信头像。";
        }
        return null;
    }

    private void moveGroup(String openid) throws WapServiceException {

        ReqGroupsMoveMessage reqMessage = new ReqGroupsMoveMessage();
        reqMessage.setOpenid(openid);
        reqMessage.setToGroupid(HglContants.SIGN_IN_GROUP_ID);
        groupsMoveExecuteService.execute(reqMessage);
    }
}
