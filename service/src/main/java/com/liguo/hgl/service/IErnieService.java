/*
 * Copyright (c)2014 快诺迅汇(长沙)网络技术股份有限公司。
 * 版权所有
 */
package com.liguo.hgl.service;

import java.util.List;
import java.util.Map;

import com.liguo.hgl.exceptions.WapServiceException;
import com.liguo.hgl.proxydao.model.TbWeixinPassUser;
import com.liguo.hgl.proxydao.page.PageDto;

/**
 * 摇奖<br>
 * 
 * @filename IErnieService.java<br>
 * @author 吴奇<br>
 * @date 2016-1-11<br>
 * @Language java<br>
 */
public interface IErnieService {
    /**
     * 获得奖项
     * 
     * @return
     */
    void saveErnie(TbWeixinPassUser tbWeixinPassUser);

    /**
     * 获得指定数量的随机用户
     * @param dateCount
     * @return
     */
    List<TbWeixinPassUser> queryRandeUserList(Integer dateCount, Map userMap);

    /**
     * 批量修改指定用户的中奖状态
     * 进入一次大转盘以后 将取消继续参加摇奖资格
     * @param winGrade
     * @param userArray
     */
    void updateUserByPrimaryKeyArray(Integer winGrade, List<TbWeixinPassUser> userArray);

    /**
     * 修改指定用户的中奖状态
     * @param winGrade
     * @param userArray
     * @throws WapServiceException 
     */
    void updateWinUserByPrimary(Integer winGrade, Integer userId) throws WapServiceException;

    /**
     * 修改指定用户的中奖状态
     * @param winGrade 修改后的中奖状态
     * @return
     */
    void updateWinUserByWinGrade(Integer winGrade);

    public void sendCustom(String openid, String msg) throws WapServiceException;

    List<TbWeixinPassUser> selectSignInUser(Integer dateCount);

    Integer queryCanSignUserNum(Map userMap);

	List<TbWeixinPassUser> queryRandeUserList(PageDto pgo);

}
