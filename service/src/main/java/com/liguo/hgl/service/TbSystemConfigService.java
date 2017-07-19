package com.liguo.hgl.service;

import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbSystemConfig;
import com.liguo.hgl.proxydao.page.PageDto;

import java.util.List;

public interface TbSystemConfigService {
	
    /**
     * 根据条件获取总行数
     */
    int countByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键查询记录
     */
    TbSystemConfig selectByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据指定条件查询记录
     */
    List<TbSystemConfig> selectByObject(Criteria example) throws RuntimeException;

    /**
     * 根据指定条件查询记录,支持分页
     */
    List<TbSystemConfig> selectByObject(Criteria example,PageDto pgo) throws RuntimeException;
    
    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbSystemConfig record) throws RuntimeException;

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbSystemConfig record) throws RuntimeException;

    /**
     * 保存属性记录
     */
    int insert(TbSystemConfig record) throws RuntimeException;
    
    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbSystemConfig record) throws RuntimeException;
    
    /**
     * 校验配置表名称是否存在重复
     */
    boolean validateSystemConfigIsExist(String configKey) throws RuntimeException;

    /**
     * 获得签到应该给予的积分
     * @return
     */
	Integer getSignInregral();
	/**
     * 获得订单的积分规则
     * @return
     */
	Double getOrderInregral();
	
	/**
	 * @Description:根据配置key得到对象
	* @author:ZK 
	* @date:2016-8-4
	* @parameter:
	 */
	TbSystemConfig selectByConfigKey(String configKey);
}