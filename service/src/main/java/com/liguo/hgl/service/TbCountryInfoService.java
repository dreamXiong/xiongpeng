package com.liguo.hgl.service;

import java.util.List;

import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbCityInfo;
import com.liguo.hgl.proxydao.model.TbCountryInfo;

public interface TbCountryInfoService {
    /**
     * 根据条件获取总行数
     */
    int countByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键查询记录
     */
    TbCountryInfo selectByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据指定条件查询记录
     */
    List<TbCountryInfo> selectByObject(Criteria example) throws RuntimeException;

    /**
     * 根据城市Id查询对应所有的区
     * @param cid
     * @return
     */
	List<TbCountryInfo> getCountrys(Integer cid);
	
	
	 /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(TbCountryInfo record);
    
    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbCountryInfo record);
    
    /***
     * 
     * <批量查询已开通区域市对应的区>
     * @param example
     * @return
     * @throws RuntimeException
     * @author wzt
     * @since   2016年7月18日
     */
    List<TbCityInfo> findBatchCountryById(Criteria example) throws RuntimeException;


}