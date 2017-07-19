/*
 * Copyright (c)2014 快诺迅汇(长沙)网络技术股份有限公司。
 * 版权所有
 */
package com.liguo.hgl.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.liguo.hgl.base.AbstractService;
import com.liguo.hgl.ceche.ProgramCeche;
import com.liguo.hgl.common.MessageEnum;
import com.liguo.hgl.common.ResultDto;
import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.common.message.RespBaseMessage;
import com.liguo.hgl.common.message.RespUserInfoMessage;
import com.liguo.hgl.common.message.RespUserOpenIdMessage;
import com.liguo.hgl.proxydao.dao.TbProgramInfoMapper;
import com.liguo.hgl.proxydao.dao.TbProgramPraiseMapper;
import com.liguo.hgl.proxydao.dao.impl.TbProgramInfoDtoMapperImpl;
import com.liguo.hgl.proxydao.dto.TbProgramInfoDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbProgramInfo;
import com.liguo.hgl.proxydao.model.TbProgramPraise;
import com.liguo.hgl.proxydao.util.DateUtil;
import com.liguo.hgl.service.IManagerService;
import com.liguo.hgl.service.IProgramService;
import com.liguo.hgl.service.ITokenService;
import com.liguo.hgl.service.IWeChatExecuteService;

/**
 * 节目<br>
 * 
 * @filename ProgramService.java<br>
 * @author 张勇<br>
 * @date 2016-1-8<br>
 * @Language java<br>
 */
@Service("programService")
public class ProgramService extends AbstractService implements IProgramService {
    @Autowired
    private TbProgramInfoMapper tbProgramInfoMapper;
    @Autowired
    private TbProgramInfoDtoMapperImpl tbProgramInfoDtoMapperImpl;
    @Autowired
    private TbProgramPraiseMapper tbProgramPraiseMapper;
    @Autowired
    private IManagerService managerService;
    @Autowired
    @Qualifier("queryUserOpenIdExecuteService")
    private IWeChatExecuteService queryUserOpenIdExecuteService;
    @Autowired
    protected ITokenService tokenService;
    @Autowired
    @Qualifier("queryUserInfoExecuteService")
    protected IWeChatExecuteService queryUserInfoExecuteService;
    
    @Override
    public List<TbProgramInfoDto> queryProgramList(String openId, boolean isLimit) {

        Criteria criteria = new Criteria();
        criteria.put("openId", openId);
        if (isLimit) {
            criteria.setMysqlOffset(ProgramCeche.getIndex() - 1);
            criteria.setMysqlLength(ProgramCeche.getSize());
        }
        return tbProgramInfoDtoMapperImpl.queryProgramList(criteria);
    }

    @Override
    public List<TbProgramInfoDto> queryProgramSortList() {

        return tbProgramInfoDtoMapperImpl.queryProgramSortList();
    }

    @Override
    public ResultDto savePraise(TbProgramPraise tbProgramPraise) {
        ResultDto resultDto = new ResultDto(0, "投票成功");
        if (!managerService.isStartPraise()) {
            resultDto = new ResultDto(1, "投票尚未开始");
        } else if (managerService.isEndPraise()) {// 投票已结束
            resultDto = new ResultDto(1, "投票已结束");
        } else {
            try {
                Criteria criteria = new Criteria();
                criteria.put("openId", tbProgramPraise.getOpenId());
                List<TbProgramPraise> list = tbProgramPraiseMapper.selectByObject(criteria);
                if (new Integer(0).equals(ProgramCeche.getPraiseFlag(tbProgramPraise.getProgramId()))) {
                    return new ResultDto(1, "节目尚未开始无法投票！");
                }
                for (TbProgramPraise praise : list) {
                    if (praise.getProgramId().equals(tbProgramPraise.getProgramId())) {
                        return new ResultDto(1, "该节目您已投过票了");
                    }
                }
                // 插入点赞记录
                tbProgramPraise.setVersion(HglContants.DEFUAL_VERSION);
                tbProgramPraise.setWeixinNum("test1");
                tbProgramPraise.setCreateTime(DateUtil.getNowDateTime("yyyy-MM-dd HH:mm:dd"));
                tbProgramPraiseMapper.insert(tbProgramPraise);
                // 修改界面投票总量
//	            TbProgramInfo tbProgramInfo = tbProgramInfoMapper.selectByPrimaryKey(tbProgramPraise.getProgramId());
//	            tbProgramInfo.setPraise(tbProgramInfo.getPraise() + 1);
//	            tbProgramInfoMapper.updateByPrimaryKey(tbProgramInfo);
                ProgramCeche.addProgramPraiseNum(tbProgramPraise.getProgramId(), 1);
            } catch (Exception e) {
            	logger.error("投票保存异常",e);
                resultDto = new ResultDto(1, "系统异常，请稍后再试");
            }
        }
        return resultDto;
    }

    @Override
    public String queryUserOpenId(String code) {

        Map<String, String> map = new HashMap<String, String>();
        map.put("appid", HglContants.APP_ID_FOR_KWINER);
        map.put("secret", HglContants.APP_SECRET_FOR_KWINER);
        map.put("grant_type", HglContants.GRANT_TYPE_QUERY_OPENID);
        map.put("code", code);
        try {
            RespBaseMessage respMessage = queryUserOpenIdExecuteService.execute(map);
            if (MessageEnum.isSuccess(respMessage.getErrcode())) {
                RespUserOpenIdMessage respUserOpenIdMessage = (RespUserOpenIdMessage) respMessage;
                return respUserOpenIdMessage.getOpenid();
            } else {
                logger.error("获取openId失败，code=" + respMessage.getErrcode() + ",msg=" + respMessage.getErrmsg());
            }
        } catch (Exception e) {
            logger.error("获取openId失败:", e);
        }

        return null;
    }

    /**
     * 该用户是否未关注了公众微信 
     * @param openId
     * @return
     */
    @Override
    public boolean isSubscribe(String openId) {

        try {
            Map<String, String> map = new HashMap<String, String>();
            map.put("access_token", tokenService.getToken());
            map.put("lang", HglContants.LANG);
            map.put("openid", openId);
            RespBaseMessage respMessage = queryUserInfoExecuteService.execute(map);
            if (MessageEnum.isSuccess(respMessage.getErrcode())) {
                RespUserInfoMessage userInfoMessage = (RespUserInfoMessage) respMessage;
                return "0".equals(userInfoMessage.getSubscribe());
            }
        } catch (Exception e) {
            logger.error("获取用户信息失败(判断是否关注公众号)：", e);
        }
        return false;
    }

    /**
     * 获取投票排名前topNumber的节目
     */
    @Override
    public List<TbProgramInfoDto> queryTopProgramList(int topNumber) {

        Criteria condition = new Criteria();
        condition.setMysqlLength(topNumber);

        return tbProgramInfoDtoMapperImpl.queryTopProgramList(condition);
    }

    /**
     * 获取投票排名前topNumber的节目
     */
    @Override
    public TbProgramInfo queryProgramById(Integer id) {

        return tbProgramInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateProgramPraiseFlagById(TbProgramInfo info) {

        return tbProgramInfoMapper.updateByPrimaryKey(info);
    }
}
