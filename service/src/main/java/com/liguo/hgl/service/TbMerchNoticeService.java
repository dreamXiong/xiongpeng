package com.liguo.hgl.service;

import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbMerchNotice;
import com.liguo.hgl.proxydao.page.PageDto;

import java.util.List;

public interface TbMerchNoticeService {
    /**
     * 根据条件获取总行数
     */
    int countByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键查询记录
     */
    TbMerchNotice selectByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据指定条件查询记录
     */
    List<TbMerchNotice> selectByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbMerchNotice record) throws RuntimeException;

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbMerchNotice record) throws RuntimeException;

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbMerchNotice record) throws RuntimeException;
    
    /*分页*/
    public List<TbMerchNotice> selectByObjectPage(Criteria example,PageDto page);
}