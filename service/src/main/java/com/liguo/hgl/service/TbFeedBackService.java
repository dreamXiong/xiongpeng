package com.liguo.hgl.service;

import com.liguo.hgl.proxydao.dto.FeedBackDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbFeedBack;
import com.liguo.hgl.proxydao.page.PageDto;

import java.util.List;

public interface TbFeedBackService {
    /**
     * 根据条件获取总行数
     */
    int countByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键查询记录
     */
    TbFeedBack selectByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据指定条件查询记录
     */
    List<TbFeedBack> selectByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbFeedBack record) throws RuntimeException;

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbFeedBack record) throws RuntimeException;

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbFeedBack record) throws RuntimeException;
    
    /**
     * @param feedBack 意见
     * @param userId  提意见人的id
     * @return 
     */
    int insertFeedBack(String feedBack,Integer userId);
    
    /**
     * 分页查询
     * @param criteria
     * @param pgo
     * @return
     */
    List<FeedBackDto> selectByObjectPage(Criteria criteria,PageDto pgo);
}