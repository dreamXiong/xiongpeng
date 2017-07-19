package com.liguo.hgl.service;

import java.util.List;
import java.util.Map;

import com.liguo.hgl.proxydao.dto.WithdrawalsDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbWithdrawals;
import com.liguo.hgl.proxydao.page.PageDto;

public interface TbWithdrawalsService {
    /**
     * 根据条件获取总行数
     */
    int countByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键查询记录
     */
    TbWithdrawals selectByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据指定条件查询记录
     */
    List<TbWithdrawals> selectByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbWithdrawals record) throws RuntimeException;

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbWithdrawals record) throws RuntimeException;

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbWithdrawals record) throws RuntimeException;
    /**
     * 审核用户提现
     * @param criteria
     * @param page
     * @return
     * @author zss
     */
	List<WithdrawalsDto> selectWithdrawalsPage(Criteria criteria, PageDto page);
	/**
	 * 提现审核
	 * @param id
	 * @param loginUserId
	 * @return
	 * @author zss
	 */
	Map<String, Object> auditWithrawals(Integer id, Integer loginUserId);
	
	/**
	 * 提现拒绝
	 * @param id
	 * @param loginUserId
	 * @return
	 * @author zss
	 */
	public Map<String, Object> refuseAutomaticShow(Integer id, Integer loginUserId) ;
}