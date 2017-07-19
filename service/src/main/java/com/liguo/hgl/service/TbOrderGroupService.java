package com.liguo.hgl.service;

import java.util.List;
import java.util.Map;

import com.liguo.hgl.dto.MyOrderForm;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.MyOrderStatesCount;
import com.liguo.hgl.proxydao.model.OrderGroupDto;
import com.liguo.hgl.proxydao.model.TbOrderGroup;
import com.liguo.hgl.proxydao.page.PageDto;

public interface TbOrderGroupService {
    /**
     * 根据条件获取总行数
     */
    int countByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键查询记录
     */
    TbOrderGroup selectByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据指定条件查询记录
     */
    List<TbOrderGroup> selectByObject(Criteria example) throws RuntimeException;

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
    int updateByPrimaryKeySelective(TbOrderGroup record) throws RuntimeException;

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbOrderGroup record) throws RuntimeException;

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbOrderGroup record) throws RuntimeException;
    
    
    /**
     * 我的订单
     * */
	List<OrderGroupDto> selectOrderGroupList(MyOrderForm myOrderForm ,PageDto page);
	
	/**
	 *统计用户订单 
	 * */
	public MyOrderStatesCount selectMyOrderStatesCount(Integer shopId);
	
	/**
	 * 订单取消操作
	 * */
	 public void cancleMyOrderGroupByBuyer(MyOrderForm myOrderForm,Integer shopId,Integer userId);
	 
	 /**
	 * 订单支付操作
	 * */
	 public void payMyOrderGroup(MyOrderForm myOrderForm,Integer userId);
	 
	 /**
	  * 订单列表查询操作
	  * */
	 public List<OrderGroupDto> selectOrderList(MyOrderForm myOrderForm,PageDto page,Integer shopId);
	 
	 /**
	  * 买家发起的终止订单
	  * */
	 public void stopMyOrderGroupByBuyer(MyOrderForm myOrderForm,Integer shopId,Integer userId);
		  
	 /**
	  * 买家取消订单终止订单
	  * */
	 public void cancleStopOrderGroupByBuyer(MyOrderForm myOrderForm,Integer shopId,Integer userId);
	 
	 /**
     * 后台根据该用户所管理的所有店铺ShopIDList去查询所有订单信息
     * */
	 public List<OrderGroupDto> selectOrderListByAdmin(MyOrderForm myOrderForm,PageDto page,List<Integer> shopIdList);
	 
	 /**
     * 后台根据该用户所管理的所有仓库去查询所有订单信息
     * */
	 public List<OrderGroupDto> selectOrderListByWIds(MyOrderForm myOrderForm,PageDto page,List<Integer> wIdList);
	 
	 /**
	  * 查询订单统计数
	  * */
	 public MyOrderStatesCount selectMyOrderStatesCountAdmin(List<Integer> shopIdList);
	 
	 /**
	  * 确认订单
	  * */
	 public void  configOrderGroup(MyOrderForm myOrderForm,Integer userId);
	 
	 /**
	  * 取消订单
	  * */
	 public void cancleMyOrderGroupByAdmin(MyOrderForm myOrderForm,Integer userId);
	 
	 
	 /**
	  * 卖家申请终止订单
	  * */
	 public void stopOrderGroupByAdmin(MyOrderForm myOrderForm,Integer userId);
	 
	 /**
	  * 卖家同意（买家）终止订单
	  * */
	 public void configStopOrderGroupAdmin(MyOrderForm myOrderForm,Integer userId);
	 /**
	  * 发货
	  * */
	 public void sendOutGoods(MyOrderForm myOrderForm,Integer userId);
	 
	 /**
	  * 卖家不同意（买家）终止订单
	  * */
	 public void cancleStopOrderGroupAdmin(MyOrderForm myOrderForm,Integer userId);
	 
	 /**
	  * @Description:确认收货
	 * @author:ZK 
	 * @date:2016-7-18
	 * @parameter:
	  */
	 public void configGoodsReceipt(MyOrderForm myOrderForm,Integer shopId,Integer userId);
	 
	 /**
	  * @Description:买家确认终止订单
	 * @author:ZK 
	 * @date:2016-7-18
	 * @parameter:
	  */
	 public void configStopOrderGroupByBuyer(MyOrderForm myOrderForm,Integer shopId,Integer userId);
	 
	 public Map<String,Object> submitOrder(String merchantsIds,String cartIdList[],String addressId,String payType,String couponsMoney,Integer userId,Integer shopId,Double couponRule,Double discount);
	 
	 public String makeOrderNum(Integer orderType);
	 
	 public List<OrderGroupDto> selectOrderGroupById(Criteria criteria,PageDto page);
	 
	 /*异常补单分页*/
	 public List<OrderGroupDto> selectObjectByPage(Criteria criteria,PageDto page);
	 
	 /**
	  * @Description:根据主键查询订单信息
	 * @author:ZK 
	 * @date:2016-7-18
	 * @parameter:
	  */
	 OrderGroupDto selectObjectByPrimaryKey(Integer id);
	 
	 /**
	  * @Description:线下 ：确认收到货款
	 * @author:ZK 
	 * @date:2016-7-18
	 * @parameter:
	  */
	 public void configReceivePayment(MyOrderForm myOrderForm,Integer userId);
	 /**
	  * 漏发，错发
	  * @param myOrderForm
	  * @param shopId
	  * @param userId
	  * @author zss
	  */
	void reissueOrderGroup(MyOrderForm myOrderForm, Integer shopId,
			Integer userId);
	
	

    /**
     * 根据订单状态查询订单，查询结果，订单时间会加上自动确认收货的时间
     */
    List<TbOrderGroup> findOrderGroupByOrderStatus(Criteria example) throws RuntimeException;
    /**
     * 根据指定条件查询记录
     */
    int updateOrderStatusByIds(Criteria example) throws RuntimeException;
	
	
}