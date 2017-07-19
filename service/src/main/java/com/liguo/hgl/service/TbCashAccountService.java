package com.liguo.hgl.service;

import java.util.List;

import com.liguo.hgl.dto.MyOrderForm;
import com.liguo.hgl.proxydao.dto.CashAccountDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbCashAccount;
import com.liguo.hgl.proxydao.page.PageDto;

public interface TbCashAccountService {
    /**
     * 根据条件获取总行数
     */
    int countByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键查询记录
     */
    TbCashAccount selectByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据指定条件查询记录
     */
    List<TbCashAccount> selectByObject(Criteria example) throws RuntimeException;

    /**
     * 根据指定条件查询记录
     */
   /* DataPackage getDatapackage(Criteria example, int lines, int page) throws RuntimeException;*/

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbCashAccount record) throws RuntimeException;

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbCashAccount record) throws RuntimeException;

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbCashAccount record) throws RuntimeException;
    
    /**
     * 
    * @Description:分页查询资金明细记录
    * @author:ZK 
    * @date:2016-7-18
    * @parameter:
    * @return:List<TbCashAccount>
     */
    public List<TbCashAccount> selectCashAccountList(MyOrderForm myOrderForm ,PageDto page);
    
    /**
     * 
    * @Description:批量插入资金明细记录
    * @author:ZK 
    * @date:2016-7-18
    * @parameter:
    * @return:int
     */
    public int insertBatchCashAccount(List<TbCashAccount> List);
    
    /**
     * 
    * @Description:wap端页面滑动分页
    * @author:ZK 
    * @date:2016-7-18
    * @parameter:
    * @return:int
     */
    public List<CashAccountDto> selectCashAccount(Criteria example,PageDto page,Integer indexPage) throws RuntimeException ;
}