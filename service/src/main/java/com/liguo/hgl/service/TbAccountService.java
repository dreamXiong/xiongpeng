package com.liguo.hgl.service;

import java.util.List;

import com.liguo.hgl.exceptions.TransactionException;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbAccount;

public interface TbAccountService {
    /**
     * 根据条件获取总行数
     */
    int countByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键查询记录
     */
    TbAccount selectByPrimaryKey(Integer id) throws RuntimeException;
    
    /**
     * 根据用户ID得到账户信息
     * */
    TbAccount selectAccountByUserId(Integer id) ;

    /**
     * 根据指定条件查询记录
     */
    List<TbAccount> selectByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbAccount record) throws RuntimeException;

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbAccount record) throws RuntimeException;

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbAccount record) throws RuntimeException;
    
    /**
     * 保存属性不为空的记录
     */
    int insert(TbAccount record) throws RuntimeException;
    
    /**
     * 
    * @Description:用户提现
    * @author:ZK 
    * @date:2016-7-18
    * @parameter:account 提现用户账户ID，accountBankId 银行账户ID，money 提现金额，userId 用户ID，shopId 店铺ID
    * @return:int
     */
    public int withdrawalsSave(Integer accountId,Integer accountBankId,Double money,Integer userId,Integer shopId) throws TransactionException;
}