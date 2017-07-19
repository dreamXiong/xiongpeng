package com.liguo.hgl.service;

import java.util.List;
import java.util.Map;

import com.liguo.hgl.exceptions.TransactionException;
import com.liguo.hgl.proxydao.dto.IntegralExchangeForm;
import com.liguo.hgl.proxydao.dto.IntegralMallRecordDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbAccount;
import com.liguo.hgl.proxydao.model.TbIntegral;
import com.liguo.hgl.proxydao.model.TbIntegralMall;
import com.liguo.hgl.proxydao.model.TbIntegralMallRecord;
import com.liguo.hgl.proxydao.page.PageDto;

public interface TbIntegralMallRecordService {
    /**
     * 根据条件获取总行数
     */
    int countByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键查询记录
     */
    TbIntegralMallRecord selectByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据指定条件查询记录
     */
    List<IntegralMallRecordDto> selectByObject(Criteria example) throws RuntimeException;
    
    /**
     * 根据指定条件查询记录(带分页)
     */
    List<IntegralMallRecordDto> selectByObject(Criteria example, PageDto pgo) throws RuntimeException;

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbIntegralMallRecord record) throws RuntimeException;

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbIntegralMallRecord record) throws RuntimeException;

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbIntegralMallRecord record) throws RuntimeException;
    
    /**
     * 我要兑换
     * @param integralExchangeForm
     * @return
     * @throws RuntimeException
     */
    Map<String,Object> wantPay(IntegralExchangeForm integralExchangeForm,Integer userId) throws RuntimeException;
    
    /**
     * 插入积分商城记录表数据
     * @param addressHistoryId
     * @param userId
     * @param form
     * @param integralMall
     * @param integral
     * @return
     * @throws RuntimeException
     */
    Map<String,Object> insertIntegralMallRecord(Integer addressHistoryId,Integer userId,IntegralExchangeForm form,TbIntegralMall integralMall,TbIntegral integral) throws RuntimeException;
    
    /**
     * 账户余额支付
     * @param orderId
     * @param accountId
     * @throws TransactionException
     */
    void payMyOrderGroup(Integer orderId, Integer accountId) throws TransactionException;
    
    /**
     * 根据订单的序列号查询
     */
    TbIntegralMallRecord selectBySerialNo(Criteria example) throws RuntimeException;
    
    /**
     * 更改积分记录表中的状态和扣掉积分
     * @param t
     * @param userId
     * @throws RuntimeException
     */
    void updateIntegralMallRecordStatus(TbIntegralMallRecord t,Integer userId) throws RuntimeException;
    
    /**
	 * 支付回调处理,增加资金记录表记录,修改积分状态,增加积分明细表记录
	 * @param t
	 */
	void payCallUpdate(TbIntegralMallRecord t) throws RuntimeException;
	
	/**
	 * 插入资金明细记录
	 * @param t
	 * @param tbAccount
	 */
	void insertCashAccount(TbIntegralMallRecord t,TbAccount tbAccount) throws RuntimeException;
}