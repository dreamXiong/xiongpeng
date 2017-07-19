package com.liguo.hgl.service;

import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbNoticeInfo;
import com.liguo.hgl.proxydao.page.PageDto;

import java.util.List;

public interface TbNoticeInfoService {
    /**
     * 根据条件获取总行数
     */
    int countByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键查询记录
     */
    TbNoticeInfo selectByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据指定条件查询记录
     */
    List<TbNoticeInfo> selectByObject(Criteria example) throws RuntimeException;

    /**
     * 根据指定条件查询记录
     */
    /*DataPackage getDatapackage(Criteria example, int lines, int page) throws RuntimeException;*/

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbNoticeInfo record) throws RuntimeException;

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbNoticeInfo record) throws RuntimeException;

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbNoticeInfo record) throws RuntimeException;
    
    /**
     * 保存记录
     */
    int insert(TbNoticeInfo record) throws RuntimeException;
    
    /**
   	 * 查询出最新的公告信息
   	 * @param from ：的起始下标
   	 * @param length ：查询的长度
   	 * @return :返回公告信息集合
   	 */
   	List<TbNoticeInfo> showNewestNoticeInfo(Integer from,Integer length);
   	
   	
   	/**
   	 * 公告分页查询
   	 * @param example
   	 * @param pgo
   	 * @return
   	 * @throws RuntimeException
   	 */
   	public List<TbNoticeInfo> selectByObjectPage(Criteria example, PageDto pgo) throws RuntimeException;
}