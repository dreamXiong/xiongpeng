package com.liguo.hgl.service;

import java.util.List;


import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbStatistical;

public interface TbStatisticalService {
    /**
     * 根据条件获取总行数
     */
    int countByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键查询记录
     */
    TbStatistical selectByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据指定条件查询记录
     */
    List<TbStatistical> selectByObject(Criteria example) throws RuntimeException;


    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbStatistical record) throws RuntimeException;

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbStatistical record) throws RuntimeException;

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbStatistical record) throws RuntimeException;

    /**
     * 增加统计，修改该招商围观量
     * @param id 招商Id
     * @param browser 浏览器类型
     * @param ip  客户端的ip地址
     */
	void addStatisticalView(Integer id, String browser, String ip);
	
	/**
     * 增加统计，修改该招商参与量
     * @param id 招商Id
     * @param browser 浏览器类型
     * @param ip  客户端的ip地址
     */
	void addStatisticalParticipate(Integer id, String browser, String ip);
	
	
	/**
     * 增加统计，修改该招商购买量
     * @param id 招商Id
     * @param browser 浏览器类型
     * @param ip  客户端的ip地址
     */
	void addStatisticalWinning(Integer id, String browser, String ip);

}