package com.liguo.hgl.common;

import com.liguo.hgl.common.message.*;
import com.liguo.hgl.service.IWeChatExecuteService;
import com.liguo.hgl.service.impl.MenuClickExecuteService;
import com.liguo.hgl.service.impl.TextMsgPushExecuteService;

/**
 * 接收推送消息对应的报文和业务执行类配置<br>
 *
 * @author 王丹
 * @FileName CodeMappingClass.java<br>
 * @Language Java<br>
 * @date 2016-01-11<br>
 */
public enum CodeMappingClass {

    EVENT_CLICK(ReqEventPushMessage.class, RespEventPushMessage.class, MenuClickExecuteService.class),
    TEXT(ReqMsgPushMessage.class, RespBaseMessage.class, TextMsgPushExecuteService.class),
    UNKOWN(ReqEventPushMessage.class, RespBaseMessage.class, null);

    protected Class<? extends ReqBaseMessage> reqClass;
    protected Class<? extends RespBaseMessage> respClass;
    protected Class<? extends IWeChatExecuteService> executeClass;

    CodeMappingClass(Class<? extends ReqBaseMessage> reqClass, Class<? extends RespBaseMessage> respClass, Class<? extends IWeChatExecuteService> executeClass) {
        this.reqClass = reqClass;
        this.respClass = respClass;
        this.executeClass = executeClass;
    }

    public Class<? extends ReqBaseMessage> getReqClass() {
        return reqClass;
    }

    public Class<? extends RespBaseMessage> getRespClass() {
        return respClass;
    }

    public Class<? extends IWeChatExecuteService> getExecuteClass() {
        return executeClass;
    }

    public static CodeMappingClass queryCodeMappingClass(String code){
        CodeMappingClass ret = null;
        for(CodeMappingClass codeMappingClass : CodeMappingClass.values()){
            if(codeMappingClass.name().equalsIgnoreCase(code)){
                ret = codeMappingClass;
                break;
            }
        }
        return ret;
    }
}
