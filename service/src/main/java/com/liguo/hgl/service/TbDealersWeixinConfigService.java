package com.liguo.hgl.service;

import java.util.List;

import com.liguo.hgl.proxydao.dto.DealersWeixinConfigDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbDealersWeixinConfig;
import com.liguo.hgl.proxydao.page.PageDto;

public interface TbDealersWeixinConfigService {
    /**
     * 根据条件获取总行数
     */
    int countByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键查询记录
     */
    TbDealersWeixinConfig selectByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据指定条件查询记录
     */
    List<DealersWeixinConfigDto> selectByObject(Criteria example,PageDto pgo) throws RuntimeException;

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbDealersWeixinConfig record) throws RuntimeException;

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbDealersWeixinConfig record) throws RuntimeException;

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbDealersWeixinConfig record) throws RuntimeException;
    
    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(TbDealersWeixinConfig record);
    
    TbDealersWeixinConfig selectByShopId(Criteria example);
}