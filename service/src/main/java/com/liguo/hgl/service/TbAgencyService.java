package com.liguo.hgl.service;

import java.util.List;
import java.util.Map;

import com.liguo.hgl.proxydao.dto.AgencyDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbAgency;
import com.liguo.hgl.proxydao.page.PageDto;

public interface TbAgencyService {
    /**
     * 根据条件获取总行数
     */
    int countByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键查询记录
     */
    TbAgency selectByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据指定条件查询记录
     */
    List<TbAgency> selectByObject(Criteria example) throws RuntimeException;

   

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbAgency record) throws RuntimeException;

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbAgency record) throws RuntimeException;

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbAgency record) throws RuntimeException;
    /**
     * 购买服务生成表
     * @param mid
     * @param shopId
     * @return
     * @author zss
     * @param referrName 
     */
    Map<String,Object>addAgency(Integer mid, Integer shopId, String referrName);
    /**
     * 根据招商ID查询项目的代理
     * @param criteria
     * @param page
     * @return
     * @author zss
     */
	List<AgencyDto> selectAgencyByMid(Criteria criteria, PageDto page);
	/**
	 * 修改代理状态
	 * @param id
	 * @param state
	 * @return
	 * @author zss
	 */
	Map<String, Object> updateAgencyState(Integer id, Integer state);
	/**
	 * 判断当前用户是否购买此招商的服务
	 * @param shopId
	 * @return
	 * @author zss
	 * @param integer 
	 */
	boolean isUserByService(Integer shopId, Integer mid);
}