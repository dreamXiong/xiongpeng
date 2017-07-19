package com.liguo.hgl.proxydao.dao;

import java.util.List;

import com.liguo.hgl.proxydao.base.BaseMapper;
import com.liguo.hgl.proxydao.dto.CashAccountDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbCashAccount;
import com.liguo.hgl.proxydao.page.PageDto;

public interface TbCashAccountMapper extends BaseMapper {
    /**
     * 根据条件查询记录总数
     */
    int countByObject(Criteria example);

    /**
     * 根据条件删除记录
     */
    int deleteByObject(Criteria example);

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(TbCashAccount record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbCashAccount record);

    /**
     * 根据条件查询记录集
     */
    List<TbCashAccount> selectByObject(Criteria example);

    /**
     * 根据主键查询记录
     */
    TbCashAccount selectByPrimaryKey(Integer id);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbCashAccount record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbCashAccount record);
    
    public List<TbCashAccount> selectCashAccountList(Criteria example,PageDto page);
    
    public int insertBatchCashAccount(List<TbCashAccount> List);
    
    public List<CashAccountDto> selectCashAccount(Criteria example) throws RuntimeException;
}