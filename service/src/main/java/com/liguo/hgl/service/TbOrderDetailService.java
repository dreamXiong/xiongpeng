package com.liguo.hgl.service;

import java.util.List;

import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.OrderGroupDetailDto;
import com.liguo.hgl.proxydao.model.TbOrderDetail;
import com.liguo.hgl.proxydao.model.WapProductDto;

public interface TbOrderDetailService {
    /**
     * 根据条件获取总行数
     */
    int countByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键查询记录
     */
    TbOrderDetail selectByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据指定条件查询记录
     */
    List<TbOrderDetail> selectByObject(Criteria example) throws RuntimeException;

    /**
     * 根据指定条件查询记录
     */
  /*  DataPackage getDatapackage(Criteria example, int lines, int page) throws RuntimeException;*/

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbOrderDetail record) throws RuntimeException;

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbOrderDetail record) throws RuntimeException;

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbOrderDetail record) throws RuntimeException;
    
    /**
     * 库存更新操作
     * */
    public int updateSaleInventorySendGoods(Integer orderGroupId);
    
    /**
     * 支付时增加占用库存
     * */
    public int updateSaleInventoryPayment(Integer orderGroupId);
    
    /**
     * 终止订单时减去占用库存
     * */
    public int updateSaleInventoryStopOrder(Integer orderGroupId);
    
    /**
     * 买家确认收货时进行减库存占用量和减去剩余库存量
     * */
    public int updateSaleInventoryConfigGoods(Integer orderGroupId);
    
    public List<OrderGroupDetailDto> selectOrderDetailBuyOrderID(Integer orderGroupId);
    
    /**
     * 根据用户订单去查询产品ID
     * */
    public List<WapProductDto> selectProductIdByOId(Integer orderGroupId);
    
    /**
     * @Description:用户在确认确认收货时将买的产品更新和产品图片更新到自己店铺
    * @author:ZK 
    * @date:2016-7-18
    * @parameter:
     */
    public void batchWapInventory(Integer orderGroupId,Integer shopId);
    
    /**
     * @Description:已发货状态终止订单
    * @author:ZK 
    * @date:2016-7-18
    * @parameter:
     */
    public int stopOrderForSendGoods(Integer orderGroupId);
    
    /*查询OrderGroupDetailDto*/
    public OrderGroupDetailDto selectOrderDetailDto(Integer id);
    
    /*查询同一订单的总价*/
    public OrderGroupDetailDto selecOrderDetailTotalAmount(Integer orderGroupId);
    
}