package com.liguo.hgl.service.impl;

import org.springframework.stereotype.Service;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.common.message.RespBaseMessage;
import com.liguo.hgl.common.message.RespUserOpenIdMessage;
import com.liguo.hgl.exceptions.WapServiceException;

@Service("queryUserOpenIdExecuteService")
public class QueryUserOpenIdExecuteService extends WeChatGetExecuteService{
	@Override
	protected String queryRequestUrl() throws WapServiceException {
		return HglContants.WE_USER_OPENID;
	}

	@Override
	protected RespBaseMessage convertRespMessage(String respHtml)
			throws WapServiceException {
		 return super.convertRespJson(respHtml, RespUserOpenIdMessage.class);
	}

}
