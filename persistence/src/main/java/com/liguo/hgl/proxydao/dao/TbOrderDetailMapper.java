package com.liguo.hgl.proxydao.dao;

import java.util.List;

import com.liguo.hgl.proxydao.base.BaseMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.OrderGroupDetailDto;
import com.liguo.hgl.proxydao.model.TbOrderDetail;
import com.liguo.hgl.proxydao.model.WapProductDto;

public interface TbOrderDetailMapper extends BaseMapper {
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
    int insert(TbOrderDetail record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbOrderDetail record);

    /**
     * 根据条件查询记录集
     */
    List<TbOrderDetail> selectByObject(Criteria example);

    /**
     * 根据主键查询记录
     */
    TbOrderDetail selectByPrimaryKey(Integer id);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbOrderDetail record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbOrderDetail record);
    /**
     * 发货时库存更新操作
     * */
    public int updateSaleInventorySendGoods(Integer orderGroupId);
    
    /**
     * 支付完成时加占用量
     * */
    public int updateSaleInventoryPayment(Integer orderGroupId);
    
    /**
     * 订单终止时减占用量
     * */
    public int updateSaleInventoryStopOrder(Integer orderGroupId);
    
    /**
     * 批量保存
     */
    int batchInsert(List<TbOrderDetail> record) throws RuntimeException;
    
    
    public List<OrderGroupDetailDto>selectOrderDetailBuyOrderID(Integer orderGroupId);
    /**
     * 根据用户购买订单去查询产品ID
     * */
    public List<WapProductDto> selectProductIdByOId(Integer orderGroupId);
    
    public int updateSaleInventoryConfigGoods(Integer orderGroupId);
    
    public int stopOrderForSendGoods(Integer orderGroupId);
    
    /*查询orderGroupDto*/
    public OrderGroupDetailDto selectOrderDetailDto(Integer id);
    
    /*查询同一订单的总价*/
    public OrderGroupDetailDto selecOrderDetailTotalAmount(Integer orderGroupId);
}