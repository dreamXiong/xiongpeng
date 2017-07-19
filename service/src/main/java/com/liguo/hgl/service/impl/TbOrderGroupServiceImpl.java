package com.liguo.hgl.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.dto.MyOrderForm;
import com.liguo.hgl.proxydao.dao.TbAddressHistoryMapper;
import com.liguo.hgl.proxydao.dao.TbAddressMapper;
import com.liguo.hgl.proxydao.dao.TbAgencyMapper;
import com.liguo.hgl.proxydao.dao.TbCashAccountMapper;
import com.liguo.hgl.proxydao.dao.TbInventoryLockMapper;
import com.liguo.hgl.proxydao.dao.TbOrderDetailMapper;
import com.liguo.hgl.proxydao.dao.TbOrderGroupMapper;
import com.liguo.hgl.proxydao.dao.TbOrderTrackingMapper;
import com.liguo.hgl.proxydao.dao.TbShopInfoMapper;
import com.liguo.hgl.proxydao.dao.TbShoppingCartMapper;
import com.liguo.hgl.proxydao.dto.AgencyDto;
import com.liguo.hgl.proxydao.dto.ShoppingCartInfoDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.MyOrderStatesCount;
import com.liguo.hgl.proxydao.model.OrderGroupDetailDto;
import com.liguo.hgl.proxydao.model.OrderGroupDto;
import com.liguo.hgl.proxydao.model.ProductDto;
import com.liguo.hgl.proxydao.model.TbAddress;
import com.liguo.hgl.proxydao.model.TbAddressHistory;
import com.liguo.hgl.proxydao.model.TbAgency;
import com.liguo.hgl.proxydao.model.TbCashAccount;
import com.liguo.hgl.proxydao.model.TbInventoryLock;
import com.liguo.hgl.proxydao.model.TbOrderDetail;
import com.liguo.hgl.proxydao.model.TbOrderGroup;
import com.liguo.hgl.proxydao.model.TbOrderTracking;
import com.liguo.hgl.proxydao.model.TbShopInfo;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.TbExperienceService;
import com.liguo.hgl.service.TbOrderDetailService;
import com.liguo.hgl.service.TbOrderGroupService;
import com.liguo.hgl.service.TbProductService;
import com.liguo.hgl.util.ImageUtil;
import com.liguo.hgl.util.StringUtil;

@Service
@Scope(value="prototype")
public class TbOrderGroupServiceImpl implements TbOrderGroupService {
	
	private static Object lockObj = "lockerOrder";  
	@Autowired
	private TbAgencyMapper tbAgencyMapper;
	
    @Autowired
    private TbOrderGroupMapper tbOrderGroupMapper;
	
    @Autowired
  	protected TbOrderDetailService tbOrderDetailService;
    
    @Autowired
    private TbShoppingCartMapper tbShoppingCartMapper;
    
    @Autowired
    private TbAddressMapper tbAddressMapper;
    
    @Autowired
    private TbAddressHistoryMapper tbAddressHistoryMapper;
    
    @Autowired
    private TbOrderDetailMapper tbOrderDetailMapper;
    
    @Autowired
    private TbOrderTrackingMapper tbOrderTrackingMapper;
    
    @Autowired
    protected TbShopInfoMapper tbShopInfoMapper;
    
    @Autowired
    protected TbCashAccountMapper tbCashAccountMapper;
    
    @Autowired
    protected TbProductService tbProductService;
    
    @Autowired
    protected TbExperienceService tbExperienceService;
    
    @Autowired
    protected TbInventoryLockMapper tbInventoryLockMapper;
    
    private static final Logger logger = LoggerFactory.getLogger(TbOrderGroupServiceImpl.class);

    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbOrderGroupMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TbOrderGroup selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbOrderGroupMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TbOrderGroup> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbOrderGroupMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int deleteByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbOrderGroupMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKeySelective(TbOrderGroup record) throws RuntimeException {
        try {
            return this.tbOrderGroupMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKey(TbOrderGroup record) throws RuntimeException {
        try {
            return this.tbOrderGroupMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int insertSelective(TbOrderGroup record) throws RuntimeException {
        try {
            return this.tbOrderGroupMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<OrderGroupDto> selectOrderGroupList(MyOrderForm myOrderForm ,PageDto page){
    	Criteria parameter = new Criteria();
	    	parameter.put("shopId", myOrderForm.getShopId());
	    	parameter.put("shopIdList", myOrderForm.getShopIdList());
	    	parameter.put("orderType", myOrderForm.getOrderType());
	    	parameter.put("orderStateList", myOrderForm.getOrderStateList());
	    	parameter.put("warehouseIdList", myOrderForm.getWarehouseIdList());
	    	parameter.put("orderState", myOrderForm.getOrderState());
	    	parameter.put("startTime", myOrderForm.getStartTime());
	    	parameter.put("endTime", myOrderForm.getEndTime());
	    	if(!StringUtils.isBlank(myOrderForm.getSearchText())){
	    		parameter.put("searchText", myOrderForm.getSearchText().trim());
	    	}
	    	List<OrderGroupDto> dtoList =  this.tbOrderGroupMapper.selectOrderGroupList( parameter, page);
	    	
	    	int detailCount = 0;
	    	Integer buyNumCount = 0;
	    	double buyMoneyCount = 0.0;
	    	//用于量总记
	    	Integer buyCount = 0;
	    	
	    	for(int i=0 ; i<dtoList.size() ;i++){
	    		detailCount = 0;
	    		buyCount = 0;
	    		OrderGroupDto orderGroupDto = dtoList.get(i);
	    		for(int v=0;v<orderGroupDto.getProductList().size();v++){
	    			ProductDto productDto = orderGroupDto.getProductList().get(v);
	    			detailCount += productDto.getOrderDetailList().size(); 
	    			buyNumCount = 0;
	    	    	buyMoneyCount = 0.0;
	    	    	for(int z=0; z<productDto.getOrderDetailList().size(); z++){
	    	    		OrderGroupDetailDto orderGroupDetailDto = productDto.getOrderDetailList().get(z);
	    	    		buyNumCount += orderGroupDetailDto.getBuyNum();
	    	    		buyMoneyCount += orderGroupDetailDto.getDetailPayMoney();
	    	    	}
	    	    	BigDecimal b = new BigDecimal(buyMoneyCount);
	    	    	double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	    	    	productDto.setBuyMoneyCount(f1);
	    	    	productDto.setBuyNumCount(buyNumCount);
	    	    	buyCount += buyNumCount;
	    		}
	    		dtoList.get(i).setDetailCount(detailCount);
	    		dtoList.get(i).setBuyCount(buyCount);
	    	}
    	return dtoList;
    }
   
    public MyOrderStatesCount selectMyOrderStatesCount(Integer shopId){
    	
    	return this.tbOrderGroupMapper.selectMyOrderStatesCount(shopId);
    }
    public MyOrderStatesCount selectMyOrderStatesCountAdmin(List<Integer> shopIdList){
    	if(shopIdList.size() == 0){
    		shopIdList.add(0);
    	}
    	return this.tbOrderGroupMapper.selectMyOrderStatesCountAdmin(shopIdList);
    }
    public MyOrderStatesCount selectMyOrderStatesCount(List<Integer> shopId){
    	
    	return this.tbOrderGroupMapper.selectMyOrderStatesCountAdmin(shopId);
    }
    /**
     * 订单取消操作
     * */
    public void cancleMyOrderGroupByBuyer(MyOrderForm myOrderForm,Integer shopId,Integer userId){
    	
    	TbOrderGroup tbOrderGroup= selectByPrimaryKey(myOrderForm.getOrderGroupId());
    	//判断操作者的shopID 是不是订单的shopID 
    	if(tbOrderGroup.getBuyerId().equals(shopId)){
    		tbOrderGroup.setOrderStatus(HglContants.ORDER_STATE_222);
    		tbOrderGroup.setStopReason(myOrderForm.getStopReason());
    		tbOrderGroup.setVersion(myOrderForm.getVersion());
    		tbOrderGroup.setCancelDt(System.currentTimeMillis());
    		int count = updateByPrimaryKeySelective(tbOrderGroup);
    		if(count == 0){
    			return;
    		}
    		insertTbOrderTrackInfo(myOrderForm.getOrderGroupId(),"买家取消订单",userId,tbOrderGroup.getOrderStatus());
    	}
    }
    /**
     * 后台订单取消操作
     * */
    public void cancleMyOrderGroupByAdmin(MyOrderForm myOrderForm,Integer userId){
    	TbOrderGroup tbOrderGroup= selectByPrimaryKey(myOrderForm.getOrderGroupId());
    	//判断操作者的shopID 是不是订单的shopID 
    		tbOrderGroup.setOrderStatus(HglContants.ORDER_STATE_222);
    		tbOrderGroup.setStopReason(myOrderForm.getStopReason());
    		tbOrderGroup.setVersion(myOrderForm.getVersion());
    		tbOrderGroup.setCancelDt(System.currentTimeMillis());
    		int count = updateByPrimaryKeySelective(tbOrderGroup);
    		if(count == 0){
    			return;
    		}
    		insertTbOrderTrackInfoAdmin(myOrderForm.getOrderGroupId(),"卖家取消订单",userId,tbOrderGroup.getOrderStatus());
    }
    
    /**
     * 后台申请终止订单操作
     * */
 
    public void stopOrderGroupByAdmin(MyOrderForm myOrderForm,Integer userId){
    	TbOrderGroup tbOrderGroup= selectByPrimaryKey(myOrderForm.getOrderGroupId());
    		int status = tbOrderGroup.getOrderStatus();
    		//保存订单之前的状态
    		tbOrderGroup.setStopStatus(status);
    		tbOrderGroup.setOrderStatus(HglContants.ORDER_STATE_216);
    		tbOrderGroup.setStopReason(myOrderForm.getStopReason());
    		tbOrderGroup.setVersion(myOrderForm.getVersion());
    		int count = updateByPrimaryKeySelective(tbOrderGroup);
    		if(count == 0){
    			return;
    		}
    		insertTbOrderTrackInfoAdmin(myOrderForm.getOrderGroupId(),"卖申请终止订单",userId,tbOrderGroup.getOrderStatus());
    }
    
    /**
     * 后台确认终止订单操作
     * 1.更新订单状态
     * 2.增加订单跟踪记录
     * 3.增加库存量，减去库存占用量
     * 4.删除订单库存锁定表的记录
     * 5.退还用户支付金额（模拟）
     * */
 
    public void configStopOrderGroupAdmin(MyOrderForm myOrderForm,Integer userId){
    	
    	TbOrderGroup tbOrderGroup= selectByPrimaryKey(myOrderForm.getOrderGroupId());
    		tbOrderGroup.setOrderStatus(HglContants.ORDER_STATE_222);
    		tbOrderGroup.setStopReason(myOrderForm.getStopReason());
    		tbOrderGroup.setVersion(myOrderForm.getVersion());
    		int count = updateByPrimaryKeySelective(tbOrderGroup);
    		if(count == 0){
    			return;
    		}
    		insertTbOrderTrackInfoAdmin(myOrderForm.getOrderGroupId(),"卖家确认终止订单",userId,tbOrderGroup.getOrderStatus());
    		followUpOperation(tbOrderGroup);
    }
    
   public void configReceivePayment(MyOrderForm myOrderForm,Integer userId){
		   TbOrderGroup tbOrderGroup= selectByPrimaryKey(myOrderForm.getOrderGroupId());
	   		/*int orderState = tbOrderGroup.getOrderStatus();*/
			tbOrderGroup.setOrderStatus(HglContants.ORDER_STATE_208);
			tbOrderGroup.setVersion(myOrderForm.getVersion());
			int count = updateByPrimaryKeySelective(tbOrderGroup);
			if(count == 0){
				return;
			}
			insertTbOrderTrackInfoAdmin(myOrderForm.getOrderGroupId(),"卖家确认收到货款",userId,tbOrderGroup.getOrderStatus());
    		List<OrderGroupDetailDto> dList = tbOrderDetailMapper.selectOrderDetailBuyOrderID(myOrderForm.getOrderGroupId());
    		List<TbInventoryLock> iList = new ArrayList<TbInventoryLock>();
    		if(dList.size() > 0){
    			for(int i=0 ;i<dList.size() ;i++){
    				TbInventoryLock tbInventoryLock = new TbInventoryLock();
    				tbInventoryLock.setOrderId(myOrderForm.getOrderGroupId());
    				tbInventoryLock.setInventoryId(dList.get(i).getInventoryId());
    				tbInventoryLock.setOrderStatus(tbOrderGroup.getOrderStatus());
    				tbInventoryLock.setOrderType(tbOrderGroup.getOrderType());
    				//锁定量为订单支付时的购买量
    				tbInventoryLock.setLockQuantity(dList.get(i).getBuyNum());
    				iList.add(tbInventoryLock);
    			}
    		}
    		tbInventoryLockMapper.batchInsertSelective(iList);
    		//增加占用库存量
    		tbOrderDetailMapper.updateSaleInventoryPayment(myOrderForm.getOrderGroupId());
    }
    
    /**
     * 发货
     * */
 
    public void sendOutGoods(MyOrderForm myOrderForm,Integer userId){
    	TbOrderGroup tbOrderGroup= selectByPrimaryKey(myOrderForm.getOrderGroupId());
		tbOrderGroup.setOrderStatus(HglContants.ORDER_STATE_210);
		tbOrderGroup.setStopReason(myOrderForm.getStopReason());
		tbOrderGroup.setVersion(myOrderForm.getVersion());
		tbOrderGroup.setUpdateTime(Calendar.getInstance().getTimeInMillis());
		int count = updateByPrimaryKeySelective(tbOrderGroup);
		if(count == 0){
			return;
		}
		insertTbOrderTrackInfoAdmin(myOrderForm.getOrderGroupId(),"卖家已经发货",userId,tbOrderGroup.getOrderStatus());
		//更新该订单下所有的库存，更新库存占用量，减库存量和库存占用量updateSaleInventorySendGoods
		tbOrderDetailMapper.updateSaleInventorySendGoods(myOrderForm.getOrderGroupId());
    }
    /**
     * 后台不同意买家终止订单操作
     * */
    public void cancleStopOrderGroupAdmin(MyOrderForm myOrderForm,Integer userId){
    	TbOrderGroup tbOrderGroup= selectByPrimaryKey(myOrderForm.getOrderGroupId());
    		tbOrderGroup.setOrderStatus(tbOrderGroup.getStopStatus());
    		tbOrderGroup.setStopReason(myOrderForm.getStopReason());
    		tbOrderGroup.setVersion(myOrderForm.getVersion());
    		int count = updateByPrimaryKeySelective(tbOrderGroup);
    		if(count == 0){
    			return;
    		}
    		insertTbOrderTrackInfoAdmin(myOrderForm.getOrderGroupId(),"卖家不同意终止订单",userId,tbOrderGroup.getOrderStatus());
    }
    /**
     * 买家申请订单终止操作
     * */
    public void stopMyOrderGroupByBuyer(MyOrderForm myOrderForm,Integer shopId,Integer userId){
    	TbOrderGroup tbOrderGroup= selectByPrimaryKey(myOrderForm.getOrderGroupId());
    	//判断操作者的shopID 是不是订单的shopID 
    	int status = tbOrderGroup.getOrderStatus();
    	if(tbOrderGroup.getBuyerId().equals(shopId)){
    		//保存订单之前的状态
    		tbOrderGroup.setStopStatus(status);
    		//订单状态修改
			tbOrderGroup.setOrderStatus(HglContants.ORDER_STATE_218);
			tbOrderGroup.setStopReason(myOrderForm.getStopReason());
			tbOrderGroup.setVersion(myOrderForm.getVersion());
    		int count = updateByPrimaryKeySelective(tbOrderGroup);
    		if(count == 0){
    			return;
    		}
			//订单跟踪记录
    		insertTbOrderTrackInfo(myOrderForm.getOrderGroupId(),"买家申请终止订单",userId,tbOrderGroup.getOrderStatus());
    	}
    }
    /**
     * 买家同意终止订单操作
     * */
    public void configStopOrderGroupByBuyer(MyOrderForm myOrderForm,Integer shopId,Integer userId){
    	TbOrderGroup tbOrderGroup= selectByPrimaryKey(myOrderForm.getOrderGroupId());
    	//判断操作者的shopID 是不是订单的shopID 
    	
    	if(tbOrderGroup.getBuyerId().equals(shopId)){
    		//保存订单之前的状态
    		//订单状态修改
			tbOrderGroup.setOrderStatus(HglContants.ORDER_STATE_222);
			tbOrderGroup.setVersion(myOrderForm.getVersion());
    		int count = updateByPrimaryKeySelective(tbOrderGroup);
    		if(count == 0){
    			return;
    		}
			//订单跟踪记录
    		insertTbOrderTrackInfo(myOrderForm.getOrderGroupId(),"买家同意终止订单",userId,tbOrderGroup.getOrderStatus());
    		//减去支付金额
    		followUpOperation(tbOrderGroup);
    	}
    }
    
    //订单终止时进行减库存占用量和退钱的操作
    public void followUpOperation(TbOrderGroup tbOrderGroup){
    	//判断这个订单有没有发货
    	if(HglContants.ORDER_STATE_210.toString().equals(tbOrderGroup.getStopStatus()+"")){
    		//如果发货了,增加他的库存量
    		tbOrderDetailMapper.stopOrderForSendGoods(tbOrderGroup.getId());
    	}else{
    		tbOrderDetailMapper.updateSaleInventoryStopOrder(tbOrderGroup.getId());
    		
    	}
    	//如果是线下操作不涉及金额
    	if(HglContants.SETTLE_TYPE_240.equals(tbOrderGroup.getSettleType())){
    		TbShopInfo tbShopInfo = tbShopInfoMapper.selectByPrimaryKey(tbOrderGroup.getBuyerId());
	       	 batchDeleteTbInventoryLock(tbOrderGroup.getId());
	   		 BigDecimal b1 = new BigDecimal(tbOrderGroup.getPayMoney());   
	   		 BigDecimal b2 = new BigDecimal(tbShopInfo.getBalance());  
	   		 Double balance = new Double(b1.add(b2).doubleValue());
	   		/* tbShopInfo.setBalance(balance);*/
	   		 tbShopInfoMapper.updateByPrimaryKeySelective(tbShopInfo);
	   		 
	   		TbCashAccount tbCashAccount = new TbCashAccount();
			tbCashAccount.setCashIn(tbOrderGroup.getPayMoney());
			tbCashAccount.setBalance(balance);
			tbCashAccount.setOperationDt(System.currentTimeMillis());
			tbCashAccount.setShopId(tbOrderGroup.getBuyerId());
			tbCashAccount.setOrderSerialNo(tbOrderGroup.getOrderSerialNo());
			tbCashAccountMapper.insertSelective(tbCashAccount);
    	}
    }
    public void batchDeleteTbInventoryLock(Integer orderGroupId){
    	  Criteria example = new Criteria();
    		example.put("orderId", orderGroupId);
    		List<TbInventoryLock>  ttlList = tbInventoryLockMapper.selectByObject(example);
    		List<Integer> ls = new ArrayList<Integer>();  
    		if(ttlList.size() > 0){
    			for(int i=0 ;i<ttlList.size() ;i++){
    				ls.add(ttlList.get(i).getId());
    			}
    			tbInventoryLockMapper.batchDeleteTbInventoryLock(ls);
    		}
    }
  
    /**
     * 买家确认收货
     * */
    public void configGoodsReceipt(MyOrderForm myOrderForm,Integer shopId,Integer userId){
    	TbOrderGroup tbOrderGroup= selectByPrimaryKey(myOrderForm.getOrderGroupId());
		
    	//如果是招商订单
    	if(HglContants.MERCHANT_ORDER.equals(tbOrderGroup.getOrderType())){
    		updateAgency(tbOrderGroup.getOrderSerialNo(),tbOrderGroup.getBrandId(),tbOrderGroup.getBuyerId());
    	}
    	//判断操作者的shopID 是不是订单的shopID 
    	int status = tbOrderGroup.getOrderStatus();
    	if(tbOrderGroup.getBuyerId().equals(shopId)){
    		//保存订单之前的状态
    		tbOrderGroup.setStopStatus(status);
    		//订单状态修改
			tbOrderGroup.setOrderStatus(HglContants.ORDER_STATE_212);
			tbOrderGroup.setVersion(myOrderForm.getVersion());
    		int count = updateByPrimaryKeySelective(tbOrderGroup);
    		if(count == 0){
    			return;
    		}
    		//将用户购买的产品放入库存表中
    		tbOrderDetailService.batchWapInventory(tbOrderGroup.getId(),shopId);
    		//订单跟踪记录
    		insertTbOrderTrackInfo(myOrderForm.getOrderGroupId(),"买家确认收货",userId,tbOrderGroup.getOrderStatus());
    		//增加经验值
    		tbExperienceService.addExperience(userId, tbOrderGroup.getTotalMoney(), tbOrderGroup.getOrderSerialNo(), HglContants.BUY_EXPERIENCE);
    	}
    }
    /**
     * 买家取消终止操作
     * */
    public void cancleStopOrderGroupByBuyer(MyOrderForm myOrderForm,Integer shopId,Integer userId){
    	TbOrderGroup tbOrderGroup= selectByPrimaryKey(myOrderForm.getOrderGroupId());
    	//判断操作者的shopID 是不是订单的shopID 
    	if(tbOrderGroup.getBuyerId().equals(shopId)){
    		//订单状态修改
			tbOrderGroup.setOrderStatus(tbOrderGroup.getStopStatus());
			tbOrderGroup.setVersion(myOrderForm.getVersion());
    		int count = updateByPrimaryKeySelective(tbOrderGroup);
    		if(count == 0){
    			return;
    		}
		
			insertTbOrderTrackInfo(myOrderForm.getOrderGroupId(),"买家取消终止订单",userId,tbOrderGroup.getOrderStatus());
    	}
    }
    /**
     * 模拟支付
     * */
    public void payMyOrderGroup(MyOrderForm myOrderForm,Integer userId){
    	//订单状态修改
    	TbOrderGroup tbOrderGroup= selectByPrimaryKey(myOrderForm.getOrderGroupId());
		tbOrderGroup.setOrderStatus(HglContants.ORDER_STATE_208);
		tbOrderGroup.setVersion(myOrderForm.getVersion());
		int count = updateByPrimaryKeySelective(tbOrderGroup);
		if(count == 0){
			return;
		}
		//订单跟踪记录
		insertTbOrderTrackInfo(myOrderForm.getOrderGroupId(),"订单支付成功",userId,tbOrderGroup.getOrderStatus());
		
		//增加库存锁定记录
		//1:根据订单号去查询订单明细表 
		List<OrderGroupDetailDto> dList = tbOrderDetailMapper.selectOrderDetailBuyOrderID(myOrderForm.getOrderGroupId());
		List<TbInventoryLock> iList = new ArrayList<TbInventoryLock>();
		if(dList.size() > 0){
			for(int i=0 ;i<dList.size() ;i++){
				TbInventoryLock tbInventoryLock = new TbInventoryLock();
				tbInventoryLock.setOrderId(myOrderForm.getOrderGroupId());
				tbInventoryLock.setInventoryId(dList.get(i).getInventoryId());
				tbInventoryLock.setOrderStatus(tbOrderGroup.getOrderStatus());
				tbInventoryLock.setOrderType(tbOrderGroup.getOrderType());
				//锁定量为订单支付时的购买量
				tbInventoryLock.setLockQuantity(dList.get(i).getBuyNum());
				iList.add(tbInventoryLock);
			}
		}
		/*tbInventoryLockService.batchInsertSelective(iList);*/
		tbInventoryLockMapper.batchInsertSelective(iList);
		
		//增加占用库存量
		tbOrderDetailMapper.updateSaleInventoryPayment(myOrderForm.getOrderGroupId());
		//更新店铺剩余金额
		
		//减去支付金额
		TbShopInfo tbShopInfo = tbShopInfoMapper.selectByPrimaryKey(tbOrderGroup.getBuyerId());
		 BigDecimal b1 = new BigDecimal(tbOrderGroup.getPayMoney());  
		 BigDecimal b2 = new BigDecimal(tbShopInfo.getBalance());  
		 Double balance = new Double(b2.subtract(b1).doubleValue());
		/* tbShopInfo.setBalance(balance);*/
		 tbShopInfoMapper.updateByPrimaryKeySelective(tbShopInfo);
			TbCashAccount tbCashAccount = new TbCashAccount();
			tbCashAccount.setCashOut(tbOrderGroup.getPayMoney());
			tbCashAccount.setBalance(balance);
			tbCashAccount.setOperationDt(System.currentTimeMillis());
			tbCashAccount.setShopId(tbOrderGroup.getBuyerId());
			tbCashAccount.setOrderSerialNo(tbOrderGroup.getOrderSerialNo());
			tbCashAccountMapper.insertSelective(tbCashAccount);
		
    }
    /**
     * 买家根据ShopID去查询所有订单信息
     * */
    public List<OrderGroupDto> selectOrderList(MyOrderForm myOrderForm,PageDto page,Integer shopId){
		 myOrderForm.setShopId(shopId);
		return initializationInfo(myOrderForm, page);
	 }
    /**
     * 后台根据该用户所管理的所有店铺ShopIDList去查询所有订单信息
     * */
    public List<OrderGroupDto> selectOrderListByAdmin(MyOrderForm myOrderForm,PageDto page,List<Integer> shopIdList){
    	if(shopIdList.size() == 0){
    		shopIdList.add(0);
    	}
		 myOrderForm.setShopIdList(shopIdList);
		 return initializationInfo(myOrderForm, page);
	 }

    /**
     * 后台根据该用户所管理的仓库查询发货订单
     * */
    public List<OrderGroupDto> selectOrderListByWIds(MyOrderForm myOrderForm,PageDto page,List<Integer> wIdList){
    	if(wIdList.size() == 0){
    		wIdList.add(0);
    	}
		 myOrderForm.setWarehouseIdList(wIdList);
		 return initializationInfo(myOrderForm, page);
	 }
	private Long setDate(String time,String fomart) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat(fomart);
		long millionSeconds = sdf.parse(time).getTime();//毫秒
		return millionSeconds ;
	}
	 /**
	  * 初始化信息
	  * */
	 private List<OrderGroupDto> initializationInfo(MyOrderForm myOrderForm,PageDto page){
		 try{
			 myOrderForm.setStartTime(setDate(myOrderForm.getStartTime(),"yyyy-MM-dd").toString());
			 myOrderForm.setEndTime((setDate(myOrderForm.getEndTime(),"yyyy-MM-dd")+86400000)+"");
		 }catch(ParseException e){
			 e.printStackTrace();
		 }
		 if(myOrderForm.getOrderState()!=null){
			 if(myOrderForm.getOrderState().equals("0")){
				 myOrderForm.setOrderState(null);
			 }else{
				 myOrderForm.setOrderStateList(Arrays.asList(myOrderForm.getOrderState().split(",")));
			 }
		 }
		 List<OrderGroupDto> dtoList =  selectOrderGroupList(myOrderForm,page);
		 return dtoList;
	 }
	 /**
	  * 后台确认订单
	  * */
	public void  configOrderGroup(MyOrderForm myOrderForm,Integer userId){
		//订单状态修改
    	TbOrderGroup tbOrderGroup= selectByPrimaryKey(myOrderForm.getOrderGroupId());
    	/*int orderState = tbOrderGroup.getOrderStatus();*/
		tbOrderGroup.setOrderStatus(HglContants.ORDER_STATE_202);
		tbOrderGroup.setVersion(myOrderForm.getVersion());
		int count = updateByPrimaryKeySelective(tbOrderGroup);
		if(count == 0){
			return;
		}
		//订单跟踪记录
		insertTbOrderTrackInfoAdmin(myOrderForm.getOrderGroupId(),"卖家确认订单",userId,tbOrderGroup.getOrderStatus());
	}
	private void insertTbOrderTrackInfo(Integer orderGroupId,String info,Integer userId,Integer orderState){
		
		TbOrderTracking record = new TbOrderTracking();
		record.setOrderGroupId(orderGroupId);
		record.setOperateName(info);
		record.setOperateBy(userId);
		record.setOrderState(orderState);
		record.setOperateDt(System.currentTimeMillis());
		tbOrderTrackingMapper.insertSelective(record);
	}
	
private void insertTbOrderTrackInfoAdmin(Integer orderGroupId,String info,Integer userId,Integer orderState){
		TbOrderTracking record = new TbOrderTracking();
		record.setOrderGroupId(orderGroupId);
		record.setOperateName(info);
		record.setOperateByAdmin(userId);
		record.setOrderState(orderState);
		record.setOperateDt(System.currentTimeMillis());
		tbOrderTrackingMapper.insertSelective(record);
	}

	/**
	 * 提交订单,业务处理,与品牌和仓库ID分割成多个订单
	 * 1. 查询出所有招商转为普通的招商ID
	 * 2. 插入地址历史表
	 * 3. 插入订单表
	 * 4. 插入订单明细表
	 * 5. 插入订单跟踪表
	 * 6. 删除已经购买的购物车中的信息,插入地址历史表
	 */
	@Override
	public Map<String,Object> submitOrder(String productIds,String[] cartIdList,String addressId,String payType,String couponsMoney,Integer userId,Integer shopId,Double couponRule,Double discount) {
		//定义返回结果的map
		Map<String,Object> resultMap = new HashMap<String, Object>();
		if(cartIdList != null && cartIdList.length>0){
			//转换为list
			List<String> idsList = Arrays.asList(cartIdList);  
			//查询出所有招商转为普通的招商ID
			if(StringUtils.isNotBlank(productIds)){
				List<String> shopCartIds = new ArrayList<String>();
				String[] sourceStrArray = productIds.split(",");
			    for (int i = 0; i < sourceStrArray.length; i++) {
			         shopCartIds.add(sourceStrArray[i]);
			    }
			    Criteria parameter = new Criteria();
			    parameter.put("shopCartIds", shopCartIds);
			    parameter.put("shoppingType", false);
			    //将不满足条件的招商购物单改成普通
			    int updateshopCount = tbShoppingCartMapper.updateShopInfoType(parameter);
			    logger.info("将不满足条件的招商订单改成普通订单记录数: " + updateshopCount);
			}    
		
			//计算优惠卷比例
			BigDecimal useCoupon = null;
			if(couponRule != null){
				BigDecimal couponGd = new BigDecimal(couponRule);
				BigDecimal percent = new BigDecimal(100);
				useCoupon = couponGd.divide(percent,0,BigDecimal.ROUND_HALF_EVEN);
			}
			
			//获取收货地址,插入地址历史表
			TbAddress address = tbAddressMapper.selectByPrimaryKey(Integer.parseInt(addressId));
			TbAddressHistory addressHistory = new TbAddressHistory();
			//copy属性
			BeanUtils.copyProperties(address, addressHistory,"id","version","createDt");
			//执行插入
			int count = tbAddressHistoryMapper.insert(addressHistory);  
			logger.info("插入地址历史记录表: " + count);
			
			//查询出品牌,产品,库存
			Criteria criteria = new Criteria();
			criteria.put("userId", userId);
			criteria.put("idsList", idsList);
			criteria.put("discount", discount);
			List<ShoppingCartInfoDto> shoppingCartInfoList = this.tbShoppingCartMapper.selectByCartInfo(criteria);
			
			//分割订单
			Map<String,List<ShoppingCartInfoDto>> splitMap = new HashMap<String,List<ShoppingCartInfoDto>>();
			List<ShoppingCartInfoDto> cartInfoList = null;
			for(ShoppingCartInfoDto cartInfo : shoppingCartInfoList){
				cartInfoList = new ArrayList<ShoppingCartInfoDto>();
				//使用品牌id和仓库id作为key
				String key = String.valueOf(cartInfo.getBrandId())+"-"+String.valueOf(cartInfo.getWarehouseId()); 
				if(splitMap.get(key) != null){
					splitMap.get(key).add(cartInfo);
				}else{
					cartInfoList.add(cartInfo);
					splitMap.put(key, cartInfoList);
				}
			}
			
			//设置订单表和订单明细表数据,和计算总数量,金额 
			List<TbOrderGroup> orderGroupList = new ArrayList<TbOrderGroup>();
			//订单明细的临时list
			List<TbOrderDetail> orderDetailTempList = null;
			//订单明细的list
			Map<String,List<TbOrderDetail>> orderDetailMap = new HashMap<String,List<TbOrderDetail>>();
			//获取厂家id的list
			Map<String,Integer> brandMap = new HashMap<String,Integer>();  
			double totalPayMoney = 0;
			
			//订单优惠总额
			int couPayMoney = 0;
			
			//循环订单
			for(String key : splitMap.keySet()) {  
				//根据key获取订单
				cartInfoList = splitMap.get(key);
				//计算单个订单的总价格
				double calculateMoney = 0;
				//计算单个订单的优惠后的价格
				double payMoeny = 0;
				//计算单个订单的总数量
				Integer calculateCount = 0;
				//单个订单的品牌ID
				Integer brandId = null;
				//单个订单的仓库ID
				Integer warehouseId = null;
				//单个订单的订单类型
				Integer orderType = null;
				//订单明细的临时list
				orderDetailTempList = new ArrayList<TbOrderDetail>();
				//订单表对象
				TbOrderGroup orderGroup = new TbOrderGroup();
				//循环订单中的明细
				for(ShoppingCartInfoDto shopCart : cartInfoList){  
					//获取品牌ID
					if(brandId==null){
						brandId = shopCart.getBrandId();
					}
					//获取仓库ID
					if(warehouseId==null){
						warehouseId = shopCart.getWarehouseId();
					}
					//获取订单类型
					if(orderType==null){
						orderType = shopCart.getShopType() ? HglContants.MERCHANT_ORDER:HglContants.GOODS_ORDER;
					}
					//装箱数
					Integer oneBoxCount = shopCart.getOneboxCount(); 		
					//订单数乘以装箱数
					Integer orderNum = shopCart.getBuyNum()*oneBoxCount;   
					//折后价乘以订单数
					BigDecimal singlePrice = shopCart.getDiscountPrice().multiply(new BigDecimal(orderNum)); 
					//把总价转换为BigDecimal
					BigDecimal totalPrice = new BigDecimal(calculateMoney);
					//总价加上单价
					calculateMoney = totalPrice.add(singlePrice).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  
					//计算累计的中数量
					calculateCount = calculateCount+orderNum; 
					
					//计算明细
					TbOrderDetail orderDetail = new TbOrderDetail();
					orderDetail.setBuyNum(orderNum);
					orderDetail.setInventoryId(shopCart.getId());
					orderDetail.setBuyPrice(singlePrice.doubleValue());
					orderDetail.setPrice(shopCart.getDiscountPrice().doubleValue());
					orderDetailTempList.add(orderDetail);
				}
				
				//计算优惠卷
				if(useCoupon != null){
					BigDecimal calBd = new BigDecimal(calculateMoney);
					BigDecimal calAfter = useCoupon.multiply(calBd);
					int couMoney = (int)Math.floor(calAfter.doubleValue());
					couPayMoney = (couPayMoney+couMoney);
					orderGroup.setCouponMoney(couMoney);
					payMoeny = (calculateMoney-couMoney);
				}else{
					payMoeny = calculateMoney;
				}
					
				//设置订单所有bean的值
				String orderSerialNo = makeOrderNum(orderType);
				orderGroup.setOrderSerialNo(orderSerialNo);
				orderGroup.setTotalNum(calculateCount);
				orderGroup.setTotalMoney(calculateMoney);
				orderGroup.setPayMoney(payMoeny);
				//厂家ID,//查询出该品牌的厂家ID
				Integer factoryId = null;  
				if(brandMap.get(brandId) != null){
					factoryId = brandMap.get(brandId);
				}else{
					factoryId = tbProductService.selectShopIdByPId(brandId);
				}
				orderGroup.setShopId(factoryId);
				orderGroup.setAddressId(addressHistory.getId());
				orderGroup.setBrandId(brandId);
				orderGroup.setWarehouseId(warehouseId);
				orderGroup.setOrderStatus(HglContants.ORDER_STATE_200);
				orderGroup.setSettleType(Integer.parseInt(payType));
				orderGroup.setOrderType(orderType);
				orderGroup.setBuyerId(shopId);
				orderGroup.setCreateDt(System.currentTimeMillis());
				orderGroupList.add(orderGroup);
				BigDecimal totalPayPrice = new BigDecimal(totalPayMoney);
				//计算中的支付金额
				totalPayMoney = totalPayPrice.add(new BigDecimal(payMoeny)).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
				orderDetailMap.put(orderSerialNo, orderDetailTempList);
			}
			
			List<TbOrderDetail> orderDetailList = new ArrayList<TbOrderDetail>();
			List<TbOrderTracking> orderTrackingList = new ArrayList<TbOrderTracking>();
			//循环插入订单
			for(TbOrderGroup orderGroup : orderGroupList) {
				int orderGroupCount = tbOrderGroupMapper.insert(orderGroup);
				logger.info("订单插入记录: " + orderGroupCount);
				
				orderDetailTempList = orderDetailMap.get(orderGroup.getOrderSerialNo());
				if(orderDetailTempList != null && orderDetailTempList.size()>0){
					for(TbOrderDetail orderDetail : orderDetailTempList){
						orderDetail.setOrderGroupId(orderGroup.getId());
					}
					orderDetailList.addAll(orderDetailTempList);
				}
				TbOrderTracking orderTracking = new TbOrderTracking();
				orderTracking.setOrderGroupId(orderGroup.getId());
				orderTracking.setOperateName("买家已提交订单");
				orderTracking.setOperateBy(userId);
				orderTracking.setOrderState(HglContants.ORDER_STATE_200);
				orderTracking.setOperateDt(System.currentTimeMillis());
				orderTrackingList.add(orderTracking);
			}
			
			//批量插入订单明细表
			int orderDetailCount = tbOrderDetailMapper.batchInsert(orderDetailList);
			logger.info("订单明细插入记录: " + orderDetailCount);
			
			//订单跟踪记录
			int orderTrackCount = tbOrderTrackingMapper.batchInsert(orderTrackingList);
			logger.info("订单跟踪插入记录: " + orderTrackCount);
			
			//生成订单后删除购物车的商品
			int delCount = this.tbShoppingCartMapper.deleteByPrimaryKey(criteria);
			logger.info("生成订单后删除购物车记录: " + delCount);
			
			
			//如果客户选择了使用优惠卷   1：对方勾选了使用优惠卷
			if("1".equals(couponsMoney)){
				TbShopInfo t = tbShopInfoMapper.selectByPrimaryKey(shopId);
				Double couponRemainingamt = t.getCouponRemainingAmt();
				//如果优惠卷大于使用金额
				if(couPayMoney < t.getCouponRemainingAmt()){
					t.setCouponRemainingAmt(couponRemainingamt - couPayMoney);
					//更新优惠卷操作
					tbShopInfoMapper.updateByPrimaryKey(t);
				}else{
					//金额不够 异常
					throw new RuntimeException();
				}
			}
			//设置返回的订单号和订单金额
			resultMap.put("totalPayMoney", totalPayMoney);
			resultMap.put("orderGroupList", orderGroupList);
		}
		return resultMap;
	}
	  
	/**
	 * 生成订单号
	 */
	public String makeOrderNum(Integer orderType) {
		String finOrderNum = "";
		try {
			// 最终生成的订单号
			synchronized (lockObj) {
				long nowLong = Long.parseLong(new SimpleDateFormat(
						"yyMMddHHmmssSSS").format(new Date()));
				for (int i = 0; i < 3; i++) {
					finOrderNum = finOrderNum + (int) (Math.random() * 10) + "";
				}
				if (orderType == HglContants.MERCHANT_ORDER) {
					return "Z" + nowLong + finOrderNum;
				}
				if (orderType == HglContants.GOODS_ORDER) {
					return "L" + nowLong + finOrderNum;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return finOrderNum;
	}
	public static void main() throws Exception {
		File f1 = new File(HglContants.WAP_PRODUCT+"8888");
		File f2 = new File( HglContants.PRODUCT_PATH+"100");
		
		FileUtils.copyDirectory(f1,f2);
	}

	@Override
	public List<OrderGroupDto> selectOrderGroupById(Criteria criteria,PageDto page) {
	    	
    	List<OrderGroupDto> dtoList =  this.tbOrderGroupMapper.selectOrderGroupList(criteria,page);
    	int detailCount = 0;
    	Integer buyNumCount = 0;
    	double buyMoneyCount = 0.0;
    	/*int addressId = 0;*/
    	for(int i=0 ; i<dtoList.size() ;i++){
    		detailCount = 0;
    		OrderGroupDto orderGroupDto = dtoList.get(i);
    		for(int v=0;v<orderGroupDto.getProductList().size();v++){
    			ProductDto productDto = orderGroupDto.getProductList().get(v);
    			detailCount += productDto.getOrderDetailList().size(); 
    			buyNumCount = 0;
    	    	buyMoneyCount = 0.0;
    	    	for(int z=0; z<productDto.getOrderDetailList().size(); z++){
    	    		OrderGroupDetailDto orderGroupDetailDto = productDto.getOrderDetailList().get(z);
    	    		buyNumCount += orderGroupDetailDto.getBuyNum()*orderGroupDetailDto.getOneboxCount();
    	    		buyMoneyCount += orderGroupDetailDto.getDetailPayMoney();
    	    	}
    	    	productDto.setBuyMoneyCount(buyMoneyCount);
    	    	productDto.setBuyNumCount(buyNumCount);
    		}
    		dtoList.get(i).setDetailCount(detailCount);
    	}
    	
	    return dtoList;
	}

	@Override
	public List<OrderGroupDto> selectObjectByPage(Criteria criteria,
			PageDto page) {
		
		return this.tbOrderGroupMapper.selectObjectByPage(criteria, page);
	}

	@Override
	public OrderGroupDto selectObjectByPrimaryKey(Integer id) {
		
		return this.tbOrderGroupMapper.selectObjectByPrimaryKey(id);
	}
	
	
	/**
	 * 修改招商代理的状态
	 * */
	 private void updateAgency(String orderNumber,Integer brandId, Integer buyerId) {
		   Criteria criteria = new Criteria();
			criteria.put("brandId", brandId);
			criteria.put("shopId", buyerId);
			criteria.put("orderState", HglContants.ORDER_STATE_200);//待确认
			List<AgencyDto> agencies = tbAgencyMapper.selectByCriteria(criteria);
			if(agencies!=null && agencies.size()>0){
				TbAgency agency = agencies.get(0);
				agency.setOrderId(orderNumber);
				agency.setOrderState(HglContants.ORDER_STATE_220);
				agency.setState(HglContants.MERCHANTS_AGENCY_STATE2);
				tbAgencyMapper.updateByPrimaryKeySelective(agency);
			}
		 }

	@Override
	public void reissueOrderGroup(MyOrderForm myOrderForm, Integer shopId,
			Integer userId) {
		// TODO Auto-generated method stub
		TbOrderGroup tbOrderGroup= selectByPrimaryKey(myOrderForm.getOrderGroupId());
    	//判断操作者的shopID 是不是订单的shopID 
    	if(tbOrderGroup.getBuyerId().equals(shopId)){
    		//订单状态修改
			tbOrderGroup.setOrderStatus(HglContants.ORDER_STATE_204);
			tbOrderGroup.setReissueDescription(myOrderForm.getStopReason());
			
			
			if(StringUtils.isNotBlank(myOrderForm.getImages())){
				String image="";
				String[] sourceStrArray = myOrderForm.getImages().split(",");
			    for (int i = 0; i < sourceStrArray.length; i++) {
			    	if(StringUtils.isNotBlank(sourceStrArray[i])){
			    		String newName = StringUtil.changeFileName("reissueImage"+i,sourceStrArray[i].toString());
			    		try {
							ImageUtil.changReissueImagePathAndName(sourceStrArray[i].toString(), newName, myOrderForm.getOrderGroupId());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			    		image=image+newName+",";
			    	//	sb.append(newName);
			    	}
			    }
			    tbOrderGroup.setReissueImage(image);
			}    
			 
			tbOrderGroup.setVersion(myOrderForm.getVersion());
    		int count = updateByPrimaryKeySelective(tbOrderGroup);
    		if(count == 0){
    			return;
    		}
			insertTbOrderTrackInfo(myOrderForm.getOrderGroupId(),"买家提交漏发订单",userId,tbOrderGroup.getOrderStatus());
    	}
		
	}

    @Override
    public List<TbOrderGroup> findOrderGroupByOrderStatus(Criteria example) throws RuntimeException {
        
        return this.tbOrderGroupMapper.findOrderGroupByOrderStatus(example);
    }

    @Override
    public int updateOrderStatusByIds(Criteria example) throws RuntimeException {
        
        return tbOrderGroupMapper.updateOrderStatusByIds(example);
    }

}