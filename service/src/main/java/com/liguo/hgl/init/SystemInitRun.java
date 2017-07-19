/**
 * Copyright (c)2014 快诺迅汇(长沙)网络技术股份有限公司。
 * 版权所有
 */
package com.liguo.hgl.init;

import com.liguo.hgl.common.MessageEnum;
import com.liguo.hgl.common.message.ReqMenuMessage;
import com.liguo.hgl.common.message.RespBaseMessage;
import com.liguo.hgl.exceptions.WapServiceException;
import com.liguo.hgl.service.ITokenService;
import com.liguo.hgl.service.IWeChatExecuteService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 类的功能描述<br>
 *
 * @author 王丹
 * @FileName SystemInitRun.java<br>
 * @Language Java<br>
 * @date 2016-01-08<br>
 */
@Component
public class SystemInitRun {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected ITokenService tokenService;
    @Autowired
    @Qualifier("menuCreateExecuteService")
    protected IWeChatExecuteService weChatExecuteService;

    @PostConstruct
    public void init(){
        logger.info("系统开始初始化配置");
//        try {
//            tokenService.init();
//            createMenu();
            /*scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    try {
                        logger.info(System.currentTimeMillis() + "," + tokenService.getToken());
                    } catch (WapServiceException e) {
                        e.printStackTrace();
                    }
                }
            }, 1000, 2000, TimeUnit.MILLISECONDS);*/
//        } catch (WapServiceException e) {
//            e.printStackTrace();
//            logger.error("初始化程序出现异常：", e);
//        }
//        logger.info("系统初始化配置完成");
    }

    private void createMenu(){
        final ReqMenuMessage menuMessage = new ReqMenuMessage();
        List<ReqMenuMessage.Button> buttons = new ArrayList<ReqMenuMessage.Button>();
        ReqMenuMessage.Button button1 = new ReqMenuMessage.Button();
        button1.setName("签到");
        button1.setType("click");
        button1.setKey("V10001_TODAY_MUSIC");
        buttons.add(button1);
        final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    RespBaseMessage respBaseMessage = weChatExecuteService.execute(menuMessage);
                    if(MessageEnum.isSuccess(respBaseMessage.getErrcode())){
                        scheduledExecutorService.shutdown();
                    }
                } catch (WapServiceException e) {
                    e.printStackTrace();
                }
            }
        }, 1000, 60 * 1000, TimeUnit.MILLISECONDS);

    }
}
