package com.liguo.hgl.service;

import java.util.List;
import java.util.Map;

import com.liguo.hgl.proxydao.dto.GoodsProfitDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbGoodsProfit;

public interface TbGoodsProfitService {
    /**
     * 根据条件获取总行数
     */
    int countByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键查询记录
     */
    TbGoodsProfit selectByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据指定条件查询记录
     */
    List<TbGoodsProfit> selectByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbGoodsProfit record) throws RuntimeException;

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbGoodsProfit record) throws RuntimeException;

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbGoodsProfit record) throws RuntimeException;
    
    /**
     * 调用存储过程
     * @param example
     * @return
     */
    int callGoodsProfit(Map<String,Integer> rowCountMap) throws RuntimeException;
    
    /**
     * 查询出数据分析列表
     * @param example
     * @return
     * @throws RuntimeException
     */
    List<GoodsProfitDto> selectByDataInfo(Criteria example) throws RuntimeException;
    
    /**
     * 查询出数据分析列表求和数据
     * @param example
     * @return
     * @throws RuntimeException
     */
    TbGoodsProfit selectByDataInfoSum(Criteria example) throws RuntimeException;
    
    /**
     * 查询出数据分析利润变化数据
     * @param example
     * @return
     * @throws RuntimeException
     */
    List<GoodsProfitDto> selectByProfitsChange(Criteria example) throws RuntimeException;
    
    /**
     * 查询出货品利润的总和
     * @param example
     * @return
     */
    TbGoodsProfit selectByDataInfoProfitsRate(Criteria example);
}