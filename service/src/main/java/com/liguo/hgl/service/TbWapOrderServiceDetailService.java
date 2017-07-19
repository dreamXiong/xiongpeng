package com.liguo.hgl.service;

import com.liguo.hgl.proxydao.dto.WapOrderServiceDetailDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbWapOrderServiceDetail;
import java.util.List;

public interface TbWapOrderServiceDetailService {
    /**
     * 根据条件获取总行数
     */
    int countByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键查询记录
     */
    TbWapOrderServiceDetail selectByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据指定条件查询记录
     */
    List<TbWapOrderServiceDetail> selectByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbWapOrderServiceDetail record) throws RuntimeException;

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbWapOrderServiceDetail record) throws RuntimeException;

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbWapOrderServiceDetail record) throws RuntimeException;
    
    /**
     * 推送到服务买家的购物车
     * @param inventoryListNum  库存=数量
     * @param orderServiceId 服务类型ID
     * @param userId 用户ID
     * @return
     * @throws RuntimeException
     */
    boolean confirmPushCart(String[] inventoryListNum,Integer orderServiceId,Integer userId) throws RuntimeException;
    
    /**
     * 服务买家确认推送到自己购物车
     * @param orderListNum 库存=数量
     * @param orderServiceDetailId 服务明细ID
     * @param repairmanId 地址ID
     * @param userId 用户ID
     * @return
     * @throws RuntimeException
     */
    boolean confirmAddMyCart(String orderListNum[],Integer orderServiceDetailId,Integer repairmanId,Integer userId) throws RuntimeException;
    
    /*一对一查询*/
    List<WapOrderServiceDetailDto> selectInventByObject(Criteria example) throws RuntimeException;
}