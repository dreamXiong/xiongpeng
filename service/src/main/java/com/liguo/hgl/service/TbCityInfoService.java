package com.liguo.hgl.service;

import java.util.List;
import java.util.Map;

import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbCityInfo;
import com.liguo.hgl.proxydao.model.TbOpenedRegional;

public interface TbCityInfoService {
    /**
     * 根据条件获取总行数
     */
    int countByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键查询记录
     */
    TbCityInfo selectByPrimaryKey(Integer id) throws RuntimeException;
    
    /**
     * 根据省查询市
     */
    List<Map<String,Object>> findCityByProvice(Integer id) throws RuntimeException;

    /**
     * 根据指定条件查询记录
     */
    List<TbCityInfo> selectByObject(Criteria example) throws RuntimeException;
    

 
    /**
     * 根据省Id查询对应的城市
     * @param pid
     * @return
     */
	List<TbCityInfo> getCityInfos(Integer pid);
	
	/*
	 * 根据主键更新不为空的记录*/
	int updateByPrimaryKeySelective(TbCityInfo cityInfo);
	
	
	/***
	 * 
	 * <批量查询市>
	 * @param tbOpenedRegionalList
	 * @return
	 * @throws RuntimeException
	 * @author wzt
	 * @since   2016年7月18日
	 */
	List<TbCityInfo> findBatchCityById(List<String> tbOpenedRegionalList) throws RuntimeException;
	
}