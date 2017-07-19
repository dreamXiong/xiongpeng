package com.liguo.hgl.service;

import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbDeliveryTerms;
import java.util.List;

public interface TbDeliveryTermsService {
    /**
     * 根据条件获取总行数
     */
    int countByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键查询记录
     */
    TbDeliveryTerms selectByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据指定条件查询记录
     */
    List<TbDeliveryTerms> selectByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbDeliveryTerms record) throws RuntimeException;

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbDeliveryTerms record) throws RuntimeException;

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbDeliveryTerms record) throws RuntimeException;
    
    /**
     * 保存所有记录
     * 
     */
    int insert(TbDeliveryTerms record) throws RuntimeException;

    /**
     * 根据现有的送货条款 计算出最终规则
     * @param deliveryTerms
     * @return
     * @throws RuntimeException
     */
	List<TbDeliveryTerms> getDeliveryTerms(List<TbDeliveryTerms> deliveryTerms)
			throws RuntimeException;
}