package com.liguo.hgl.service;

import java.util.List;

import com.liguo.hgl.proxydao.dto.CouponInfoDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbCouponInfo;
import com.liguo.hgl.proxydao.page.PageDto;

public interface TbCouponInfoService {
    /**
     * 根据条件获取总行数
     */
    int countByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键查询记录
     */
    TbCouponInfo selectByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据指定条件查询记录
     */
    List<TbCouponInfo> selectByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbCouponInfo record) throws RuntimeException;

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbCouponInfo record) throws RuntimeException;

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbCouponInfo record) throws RuntimeException;
    
    
    /*分页查询*/
    List<CouponInfoDto> selectByObjectPage(Criteria criteria,PageDto page);
    
    /*查询优惠券使用情况*/
    List<CouponInfoDto> selectAmount(Criteria criteria);
}