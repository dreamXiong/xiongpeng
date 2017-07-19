package com.liguo.hgl.service;

import java.util.List;

import com.liguo.hgl.dto.MyOrderForm;
import com.liguo.hgl.exceptions.TransactionException;
import com.liguo.hgl.proxydao.dto.OrderGroupDetailDto;
import com.liguo.hgl.proxydao.dto.SubmitOrderForm;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.MyOrderStatesCount;
import com.liguo.hgl.proxydao.model.TbAccount;
import com.liguo.hgl.proxydao.model.TbWapOrderGroup;
import com.liguo.hgl.proxydao.model.WapOrderGroupDto;
import com.liguo.hgl.proxydao.page.PageDto;

public interface TbWapOrderGroupService {
    /**
     * 根据条件获取总行数
     */
    int countByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键查询记录
     */
    TbWapOrderGroup selectByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据指定条件查询记录
     */
    List<TbWapOrderGroup> selectByObject(Criteria example) throws RuntimeException;

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
    int updateByPrimaryKeySelective(TbWapOrderGroup record) throws RuntimeException;

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbWapOrderGroup record) throws RuntimeException;

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbWapOrderGroup record) throws RuntimeException;
    
    /**
     * 提交订单
     * @param submitOrderForm  用户选择的参数
     * @param userId  用户ID
     * @param accountId 账号ID
     * @return
     */
    int submitOrder(SubmitOrderForm submitOrderForm,Integer userId,Integer accountId) ;
    
    
    /*********************店家家订单操作*****************************/
    /**
	 * 店家确认订单
	 * 
	 * */
    public void  configOrderGroup(MyOrderForm myOrderForm,Integer userId);
    
    /**
	 * 店家订单取消操作
	 * */
	 public void cancleMyOrderGroupByBuyer(MyOrderForm myOrderForm,Integer userId);
	 
	 /**
	 * 店家订单支付操作
	 * */
	 public void configReceivePayment(MyOrderForm myOrderForm,Integer userId,String trackInfo);
	 
	 /**
	  * 店家发起的终止订单
	  * */
	 public void stopMyOrderGroupByBuyer(MyOrderForm myOrderForm,Integer userId);
		  
	 /**
	  * 店家取消订单终止订单
	  * */
	 public void cancleStopOrderGroupByBuyer(MyOrderForm myOrderForm,Integer userId);
	 
	 /**
	  * 店家同意终止订单
	  * */
	 public void configStopOrderGroupByBuyer(MyOrderForm myOrderForm,Integer userId,String trackInfo);
	 
	 
	 public MyOrderStatesCount selectMyOrderStatesCountWap(Integer shopId);
	 
	 /**
	  * 店家 订单列表查询操作
	  * */
	 public List<WapOrderGroupDto> selectOrderList(MyOrderForm myOrderForm,PageDto page);
	 
	 /**
	  * 订单分页列表
	  * */
	 public List<WapOrderGroupDto> selectOrderGroupList(MyOrderForm myOrderForm ,PageDto page);
	 
	 /**
	  * wap订单列表
	  * */
	 public List<WapOrderGroupDto> selectOrderGroupListWap(MyOrderForm myOrderForm ,PageDto page);
	 
	 /**
	  * 发货
	  * */
	 public void sendOutGoods(MyOrderForm myOrderForm,Integer userId);
	 
	 /**
	  * 买家确认到货
	  * */
	 public void configGoodsReceipt(MyOrderForm myOrderForm,Integer userId);
	 
	 /**
	  * 店铺取消终止订单
	  * */
	 public void cancleStopOrderGroupByShop(MyOrderForm myOrderForm,Integer userId);
	 
	 /**
	  * 店铺提交终止订单
	  * */
	 public void stopMyOrderGroupByShop(MyOrderForm myOrderForm,Integer userId);
	 
	 /**
	  * 店铺取消订单
	  * */
	 public void cancleMyOrderGroupByShop(MyOrderForm myOrderForm,Integer userId);
	 
	 /**
	  * 店铺确认终止订单
	  * */
	 public void configStopOrderGroupByShop(MyOrderForm myOrderForm,Integer userId);
	 
	 /**
	  * 订单详情
	  * */
	 OrderGroupDetailDto selectOrderGroupDetail(Integer id);
	 
	 /**
	  * 查询订单列表
	  * */
	 List<WapOrderGroupDto> selectOrderGroupList(Criteria parameter);
	 
	 /**
	  * 订单评价
	  * */
	 public void saveEvaluate(Integer orderGroupId,String start,String evaluate,Integer userId);
	 
	 /**
	  * 生成订单号
	  * @param prefix
	  * @return
	  */
	 String makeOrderNum(String prefix);
	 
	 /**
	  * 修改历史地址
	  * @param addressId  地址ID
	  * @param phone 电话
	  * @param recipient 联系人
	  * @param userId  用户ID
	  * @return
	  */
	 Integer modifyAddressHistory(String addressId,String phone,String recipient,Integer userId);
	 
	 /**
	  * 订单支付
	  * */
	 public void payMyOrderGroup(Integer orderId,Integer accountId) throws TransactionException;
	 
	/**
	 * 修改订单价格
	 * @param id
	 * @param totalMoney
	 * @return
	 */
	 public int doUpdateOrderGroupPrice(Integer id,Double totalMoney);
	 
	 /**
	  * 根据指定条件查询记录
	  */
	 List<TbWapOrderGroup> findOrderGroupByOrderStatus(Criteria example) throws RuntimeException;
	 
	 /**
	  * 更新订单状态
	  * @param example
	  * @return
	  * @throws RuntimeException
	  */
	 int updateOrderStatusByIds(Criteria example) throws RuntimeException;
	 
	 /**
	  * 更新价格
	  * @param groupId 分组ID
	  * @param detailId 明细ID
	  * @param unitPrice  价格
	  * @return
	  */
	 boolean doUpdateUnitPrice(Integer groupId, Integer detailId, Double unitPrice);
	 
	 /**
	  * 微信支付订单
	  * 
	  * */
	 public void weixinPay(TbWapOrderGroup t,TbAccount aAccount) throws TransactionException;
	 
	 /**
	  * 查询完成订单
	  * @param example
	  * @return
	  * @throws RuntimeException
	  */
	 int selectUnCompleteOrder(Criteria example) throws RuntimeException;
}