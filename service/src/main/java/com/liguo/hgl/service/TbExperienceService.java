package com.liguo.hgl.service;

import java.util.List;

import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbExperience;
import com.liguo.hgl.proxydao.page.PageDto;

public interface TbExperienceService {
    /**
     * 根据条件获取总行数
     */
    int countByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键查询记录
     */
    TbExperience selectByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据指定条件查询记录
     */
    List<TbExperience> selectByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbExperience record) throws RuntimeException;

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbExperience record) throws RuntimeException;

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbExperience record) throws RuntimeException;

    /**
     * 根据条件查询经验值
     * @param criteria
     * @param page
     * @return
     * @author zss
     */
	List<TbExperience> selectExperienceList(Criteria criteria,
			PageDto page);

	/**
	 * 得到当前登录用户的经验值
	 * @param loginUserId
	 * @return
	 * @author zss
	 */
	int getMyExperience(Integer loginUserId);
	/**
	 * 增加经验值
	 * @param userId  用户id
	 * @param money 购买金额
	 * @param orderNo  订单号
	 * @param type  购买类型
	 * @return
	 * @author zss
	 */
	int addExperience(int userId,double money,String orderNo,int type);
}