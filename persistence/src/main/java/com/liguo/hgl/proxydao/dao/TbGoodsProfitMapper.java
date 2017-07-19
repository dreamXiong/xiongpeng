package com.liguo.hgl.proxydao.dao;

import java.util.List;
import java.util.Map;

import com.liguo.hgl.proxydao.base.BaseMapper;
import com.liguo.hgl.proxydao.dto.GoodsProfitDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbGoodsProfit;

public interface TbGoodsProfitMapper extends BaseMapper {
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
    int insert(TbGoodsProfit record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbGoodsProfit record);

    /**
     * 根据条件查询记录集
     */
    List<TbGoodsProfit> selectByObject(Criteria example);

    /**
     * 根据主键查询记录
     */
    TbGoodsProfit selectByPrimaryKey(Integer id);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbGoodsProfit record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbGoodsProfit record);
    
    /**
     * 调用存储过程
     * @param example
     * @return
     */
    void callGoodsProfit(Map<String,Integer> rowCountMap);
    
    /**
     * 查询出数据分析列表
     * @param example
     * @return
     * @throws RuntimeException
     */
    List<GoodsProfitDto> selectByDataInfo(Criteria example);
    
    /**
     * 查询出数据分析列表求和数据
     * @param example
     * @return
     * @throws RuntimeException
     */
    TbGoodsProfit selectByDataInfoSum(Criteria example);
    
    /**
     * 查询出数据分析利润变化数据
     * @param example
     * @return
     * @throws RuntimeException
     */
    List<GoodsProfitDto> selectByProfitsChange(Criteria example);
    
    /**
     * 查询出货品利润的总和
     * @param example
     * @return
     */
    TbGoodsProfit selectByDataInfoProfitsRate(Criteria example);
}