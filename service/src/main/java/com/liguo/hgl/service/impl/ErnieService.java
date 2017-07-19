/*
 * Copyright (c)2014 快诺迅汇(长沙)网络技术股份有限公司。
 * 版权所有
 */
package com.liguo.hgl.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.liguo.hgl.ceche.ProgramCeche;
import com.liguo.hgl.common.message.ReqCustomSendMessage;
import com.liguo.hgl.exceptions.WapServiceException;
import com.liguo.hgl.proxydao.dao.TbWeixinPassUserMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbWeixinPassUser;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.IErnieService;
import com.liguo.hgl.service.IWeChatExecuteService;

/**
 * 节目<br>
 * 
 * @filename ProgramService.java<br>
 * @author 张勇<br>
 * @date 2016-1-8<br>
 * @Language java<br>
 */
@Service
public class ErnieService implements IErnieService {

    @Autowired
    private TbWeixinPassUserMapper tbWeixinPassUserMapper;

    @Autowired
    @Qualifier("customSendExecuteService")
    private IWeChatExecuteService customSendExecuteService;

    @Override
    public List<TbWeixinPassUser> queryRandeUserList(Integer dateCount, Map userMap) {

        Criteria criteria = new Criteria();
        criteria.put("limit", dateCount);
        if (userMap != null && !userMap.isEmpty()) {
            criteria.put("userArray", userMap.values());
        } else {
            criteria.put("userArray", null);
        }
        return tbWeixinPassUserMapper.selectRandUser(criteria,null);
    }
    
	@Override
	public List<TbWeixinPassUser> queryRandeUserList(PageDto pgo) {
		Criteria criteria = new Criteria();
		criteria.put("userArray", null);
		return tbWeixinPassUserMapper.selectRandUser(criteria,pgo);
	}

    @Override
    public Integer queryCanSignUserNum(Map userMap) {

        Criteria criteria = new Criteria();
        criteria.put("userArray", userMap.values());
        return tbWeixinPassUserMapper.selectCanErnieCount(criteria);
    }

    @Override
    public List<TbWeixinPassUser> selectSignInUser(Integer dateCount) {

        return tbWeixinPassUserMapper.selectSignInUser(dateCount);
    }

    @Override
    public void saveErnie(TbWeixinPassUser tbWeixinPassUser) {

        //tbProgramPraiseMapper.insert(tbProgramPraise);
        tbWeixinPassUserMapper.updateByPrimaryKey(tbWeixinPassUser);
    }

    /**
     * 批量修改指定用户的中奖状态
     * 进入一次大转盘以后 将取消继续参加摇奖资格
     * @param winGrade
     * @param userArray
     */
    @Override
    public void updateUserByPrimaryKeyArray(Integer winGrade, List<TbWeixinPassUser> userArray) {

        Criteria criteria = new Criteria();
        criteria.put("winGrade", winGrade);
        criteria.put("userArray", userArray);
        //加入获得奖项的一级缓存
        ProgramCeche.addErnie(userArray, winGrade);
        tbWeixinPassUserMapper.updateUserByPrimaryKeyArray(criteria);
    }

    @Override
    public void updateWinUserByPrimary(Integer winGrade, Integer userId) throws WapServiceException {

        //修改中奖状态
        TbWeixinPassUser selectByPrimaryKey = tbWeixinPassUserMapper.selectByPrimaryKey(userId);
        selectByPrimaryKey.setWinGrade(winGrade);
        //发生中奖微信消息
        sendCustom(selectByPrimaryKey.getOpenId(), "恭喜您中奖了！请迅速上台领奖，否则视为弃奖！");
        //在二级缓存中加入中奖信息
        ProgramCeche.addWinErnie(selectByPrimaryKey.getOpenId(), selectByPrimaryKey.getWinGrade());
        tbWeixinPassUserMapper.updateByPrimaryKeySelective(selectByPrimaryKey);
    }

    /**
     * 修改失去中奖资格的用户的中奖状态（用于候选用户过少时的处理）
     * @param winGrade 修改后的中奖状态
     * @return
     */
    @Override
    public void updateWinUserByWinGrade(Integer winGrade) {

        Criteria criteria = new Criteria();
        criteria.put("winGrade", 0);
        tbWeixinPassUserMapper.updateWinUserByWinGrade(criteria);
    }

    @Override
    public void sendCustom(String openid, String msg) throws WapServiceException {

        ReqCustomSendMessage reqCustomSendMessage = new ReqCustomSendMessage();
        reqCustomSendMessage.setTouser(openid);
        reqCustomSendMessage.setMsgtype("text");
        ReqCustomSendMessage.Text text = new ReqCustomSendMessage.Text();
        text.setContent(msg);
        reqCustomSendMessage.setText(text);
        customSendExecuteService.execute(reqCustomSendMessage);
    }


}
