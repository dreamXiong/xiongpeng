package com.liguo.hgl.service.impl;

/*
 * Copyright (c)2014 快诺迅汇(长沙)网络技术股份有限公司。
 * 版权所有
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liguo.hgl.base.AbstractService;
import com.liguo.hgl.proxydao.dao.TbTalkMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbTalk;
import com.liguo.hgl.service.IWeixinTalkService;


@Service
public class WeixinTalkService extends AbstractService implements IWeixinTalkService {

    @Autowired
    protected TbTalkMapper tbTalkMapper;

	@Override
	public List<TbTalk> getWeixinTalkList(Criteria example) {
		return tbTalkMapper.selectByObject(example);
	}

	@Override
	public void updateWeixinTalkStatut(TbTalk talk) {
		tbTalkMapper.updateByPrimaryKey(talk);
	}

	@Override
	public TbTalk getWeixinTalkById(Integer talkId) {
		return tbTalkMapper.selectByPrimaryKey(talkId);
	}
   

}
