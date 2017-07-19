package com.liguo.hgl.proxydao.dao;

import com.liguo.hgl.proxydao.base.BaseMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbWapOrderDetail;
import java.util.List;

public interface TbWapOrderDetailMapper extends BaseMapper {
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
    int insert(TbWapOrderDetail record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbWapOrderDetail record);

    /**
     * 根据条件查询记录集
     */
    List<TbWapOrderDetail> selectByObject(Criteria example);

    /**
     * 根据主键查询记录
     */
    TbWapOrderDetail selectByPrimaryKey(Integer id);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbWapOrderDetail record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbWapOrderDetail record);
    
    /**
     * 批量保存
     */
    int batchInsert(List<TbWapOrderDetail> record) throws RuntimeException;
    
    /**
	 * 卖家已经发货时确认终止订单：加库存量
	 * 
	 * */
    public int stopOrderForSendGoods(Integer orderGroupId);
    
    /**
	 * 卖家未发货时确认终止订单：加库存量
	 * 
	 * */
    public int updateSaleInventoryStopOrder(Integer orderGroupId);
    
    public int shopSendGoods(Integer orderGroupId);
    
    public int updateSaleInventoryPayment(Integer orderGroupId);
    
    /*查询订单总金额*/
    Double selectTotalAmount(Criteria criteria);
}