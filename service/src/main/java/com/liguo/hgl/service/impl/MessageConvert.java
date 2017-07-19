/*
 * Copyright (c)2014 快诺迅汇(长沙)网络技术股份有限公司。
 * 版权所有
 */
package com.liguo.hgl.service.impl;

import com.liguo.hgl.base.AbstractService;
import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.service.IMessageConvert;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.CompactWriter;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.springframework.stereotype.Service;

import java.io.StringWriter;

/**
 * 类的功能描述<br>
 *
 * @author 王丹
 * @FileName IHttpSendService.java<br>
 * @Language Java<br>
 * @date 2016-01-08<br>
 */
@Service
public class MessageConvert extends AbstractService implements IMessageConvert {

    private String errorMessage;

    @Override
    public String getErrorMessage() {
        return this.errorMessage;
    }

    @Override
    public String convertToXml(Object obj) {
        XStream xstream = new XStream(new DomDriver(HglContants.CHARSET_UTF, new NoNameCoder()));
        xstream.processAnnotations(obj.getClass());
        StringWriter sw = new StringWriter();
        xstream.marshal(obj, new CompactWriter(sw, new NoNameCoder()));
        return sw.toString();
    }

    @Override
    public <T> T convertFromXml(Class<T> clazz, String message) {
        XStream xstream = new XStream(new DomDriver(HglContants.CHARSET_UTF, new NoNameCoder()));
        xstream.ignoreUnknownElements();
        xstream.processAnnotations(clazz);
        return (T)xstream.fromXML(message);
    }

}
