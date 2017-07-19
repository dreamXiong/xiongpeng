package com.liguo.hgl.service;

import java.util.List;

import com.liguo.hgl.proxydao.dto.IntegralDetailedDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbIntegralDetailed;
import com.liguo.hgl.proxydao.page.PageDto;

public interface TbIntegralDetailedService {
    /**
     * 根据条件获取总行数
     */
    int countByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键查询记录
     */
    TbIntegralDetailed selectByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据指定条件查询记录
     */
    List<TbIntegralDetailed> selectByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbIntegralDetailed record) throws RuntimeException;

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbIntegralDetailed record) throws RuntimeException;

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbIntegralDetailed record) throws RuntimeException;
    
    /**
     * @Description:wap端手机分页（积分明细）
    * @author:ZK 
    * @date:2016-7-18
    * @parameter:
    * @return:String
     */
    List<IntegralDetailedDto> selectIntegralDetailedPage(Criteria example,PageDto page,Integer pageIndex);
    
    /**
     * 根据userId 获得当天的签到积分记录
     * @param userId
     * @return
     */
    IntegralDetailedDto selectTodaySignByUserId(Integer userId);

    /**
     * 用户签到
     * @param id
     * @return
     */
	boolean signByUserId(Integer id);
}