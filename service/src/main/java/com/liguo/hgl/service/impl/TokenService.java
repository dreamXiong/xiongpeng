/**
 * Copyright (c)2014 快诺迅汇(长沙)网络技术股份有限公司。
 * 版权所有
 */
package com.liguo.hgl.service.impl;

import com.liguo.hgl.base.AbstractService;
import com.liguo.hgl.common.MessageEnum;
import com.liguo.hgl.common.TokenKey;
import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.common.message.RespBaseMessage;
import com.liguo.hgl.common.message.RespTokenMessage;
import com.liguo.hgl.exceptions.WapServiceException;
import com.liguo.hgl.proxydao.util.DateUtil;
import com.liguo.hgl.proxydao.util.StringUtil;
import com.liguo.hgl.service.ITokenService;
import com.liguo.hgl.service.IWeChatExecuteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 类的功能描述<br>
 *
 * @author 王丹
 * @FileName TokenService.java<br>
 * @Language Java<br>
 * @date 2016-01-08<br>
 */
@Service
public class TokenService extends AbstractService implements ITokenService {

    private static TokenKey tokenKey = new TokenKey();

    @Autowired
    @Qualifier("queryTokenExecuteService")
    protected IWeChatExecuteService queryTokenExecuteService;

    @Override
    public void init() throws WapServiceException {
        logger.info("初始化Token");
        long curTimeMillis = System.currentTimeMillis();
        Map<String, String> map = new HashMap<String, String>();
        map.put("appid", HglContants.APP_ID_FOR_KWINER);
        map.put("secret", HglContants.APP_SECRET_FOR_KWINER);
        map.put("grant_type", HglContants.GRANT_TYPE_FOR_KWINER);
        RespBaseMessage respMessage = queryTokenExecuteService.execute(map);
        if(MessageEnum.isSuccess(respMessage.getErrcode())){
            RespTokenMessage respTokenMessage = (RespTokenMessage)respMessage;
            tokenKey.setToken(respTokenMessage.getAccessToken());
            tokenKey.setExpiresTime((respTokenMessage.getExpiresIn() * 1000 + curTimeMillis));
            logger.info("Token=" + tokenKey.getToken() + ",ExpiresIn=" + DateUtil.parseLong(tokenKey.getExpiresTime(), "yyyy-MM-dd HH:mm:ss"));
        }else {
            throw new WapServiceException("获取Token失败，code=" + respMessage.getErrcode() + ",msg=" + respMessage.getErrmsg());
        }
        /*tokenKey.setToken("1111114324324fdsa");
        tokenKey.setExpiresTime((5 * 1000 + curTimeMillis));*/
    }

    @Override
    public String getToken() throws WapServiceException {
        long curTimeMillis = System.currentTimeMillis();
        if(StringUtil.isBlank(tokenKey.getToken()) || tokenKey.getExpiresTime() < curTimeMillis){
            logger.info("重置Token");
            /*tokenKey.setToken(StringUtil.getUUID());
            tokenKey.setExpiresTime(30 * 1000 + curTimeMillis);*/
            init();
            logger.info("Token=" + tokenKey.getToken() + ",ExpiresIn=" + DateUtil.parseLong(tokenKey.getExpiresTime(), "yyyy-MM-dd HH:mm:ss"));
        }
        return tokenKey.getToken();
    }
}
