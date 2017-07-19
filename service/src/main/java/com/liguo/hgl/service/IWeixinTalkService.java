package com.liguo.hgl.service;

import java.util.List;

import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbTalk;

public interface IWeixinTalkService {

	List<TbTalk> getWeixinTalkList(Criteria example);
	
	void updateWeixinTalkStatut(TbTalk talk);
	
	TbTalk getWeixinTalkById(Integer talkId);
}
