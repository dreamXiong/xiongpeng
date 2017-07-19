package com.liguo.hgl.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.dao.TbAgencyMapper;
import com.liguo.hgl.proxydao.dao.TbCashAccountMapper;
import com.liguo.hgl.proxydao.dao.TbCouponInfoMapper;
import com.liguo.hgl.proxydao.dao.TbExperienceMapper;
import com.liguo.hgl.proxydao.dao.TbInventoryLockMapper;
import com.liguo.hgl.proxydao.dao.TbMerchantsMapper;
import com.liguo.hgl.proxydao.dao.TbOrderDetailMapper;
import com.liguo.hgl.proxydao.dao.TbOrderGroupMapper;
import com.liguo.hgl.proxydao.dao.TbOrderTrackingMapper;
import com.liguo.hgl.proxydao.dao.TbShopInfoMapper;
import com.liguo.hgl.proxydao.dao.TbShopLevelMapper;
import com.liguo.hgl.proxydao.dao.TbWebUserMapper;
import com.liguo.hgl.proxydao.model.OrderGroupDetailDto;
import com.liguo.hgl.proxydao.model.TbAgency;
import com.liguo.hgl.proxydao.model.TbCashAccount;
import com.liguo.hgl.proxydao.model.TbCouponInfo;
import com.liguo.hgl.proxydao.model.TbExperience;
import com.liguo.hgl.proxydao.model.TbInventoryLock;
import com.liguo.hgl.proxydao.model.TbOrderGroup;
import com.liguo.hgl.proxydao.model.TbOrderTracking;
import com.liguo.hgl.proxydao.model.TbShopInfo;
import com.liguo.hgl.proxydao.model.TbShopLevel;
import com.liguo.hgl.proxydao.model.TbWebUser;
import com.liguo.hgl.service.PayService;
@Service
@Scope(value="prototype")
public class PayServiceImpl implements PayService{

	@Autowired
    private TbAgencyMapper tbAgencyMapper;
    
    @Autowired
    private TbWebUserMapper tbWebUserMapper;
    
    @Autowired
    private TbShopInfoMapper tbShopInfoMapper;
    
    @Autowired
    private TbExperienceMapper tbExperienceMapper;
    
    @Autowired
    private TbShopLevelMapper levelMapper;
    
    @Autowired
    private TbMerchantsMapper tbMerchantsMapper;
   
    @Autowired
    private TbOrderGroupMapper tbOrderGroupMapper;
    
    @Autowired
   	protected TbOrderTrackingMapper tbOrderTrackingMapper;
    
    @Autowired
  	protected TbOrderDetailMapper tbOrderDetailMapper;
    
    @Autowired
    private TbInventoryLockMapper tbInventoryLockMapper;
    
    @Autowired
    protected TbCashAccountMapper tbCashAccountMapper;
    
    @Autowired
    protected TbCouponInfoMapper tbCouponInfoMapper;
    
	@Override
	public Map<String, Object> payCallResults(String orderNumber, Double money) {
		int count =0;
		Map<String,Object> map = new HashMap<String,Object>();
		TbAgency tbAgency = tbAgencyMapper.selectByOrderNumber(orderNumber);
		//System.out.println("money.compareTo(tbAgency.getCoupons())==0"+(money.compareTo(tbAgency.getCoupons())==0));
		if(tbAgency!=null && money.compareTo(tbAgency.getCoupons())==0){
			//修改代理表
			tbAgency.setCouponsState(HglContants.MERCHANTS_AGENCY_COUPONSSTATE1);
			tbAgency.setState(HglContants.MERCHANTS_AGENCY_STATE1);
			
		    count = tbAgencyMapper.updateByPrimaryKeySelective(tbAgency);
		    //增加修改经验值等级
		    addExperience(tbAgency.getShopId(), money, orderNumber);
		    //增加优惠卷
		    //addCouponInfo(tbAgency.getShopid(), money, orderNumber);
		    
		    //修改招商表，减少招商位数
		   /* TbMerchants merchants = tbMerchantsMapper.selectByPrimaryKey(tbAgency.getMerchantid());
		    merchants.setPlaces(merchants.getPlaces()-1);
		    count =tbMerchantsMapper.updateByPrimaryKeySelective(merchants);*/
		    
		}else{
			map.put("errmsg", "找不到对应的订单号！");
		}
		if(count >0){
			map.put("errcode", "0");
			map.put("msg", "支付回调成功!");
		}else{
			map.put("errcode", "1");
			map.put("msg", "支付回调失败!");
		}
		return map;
	}
	
	
	private int addCouponInfo(Integer shopid,String name, Double money, String orderNumber) {
		int count =0;
		TbCouponInfo couponInfo = new TbCouponInfo();
		couponInfo.setTypeId(HglContants.COUPON_TYPE_GIVE);
		couponInfo.setStatus(HglContants.COUPON_STATUS_GAIN);
		couponInfo.setAmount(money);
		couponInfo.setShopId(shopid);
		couponInfo.setShopName(name);
		couponInfo.setObtainDt(System.currentTimeMillis());
		couponInfo.setOperateDt(System.currentTimeMillis());
		couponInfo.setOrderSerialNo(orderNumber);
		count = tbCouponInfoMapper.insert(couponInfo);
		return count;
	}


	public int addExperience(int shopId, double money,String orderNo) {
		int count =0;
		TbShopInfo shopInfo = tbShopInfoMapper.selectByPrimaryKey(shopId);
		if(shopInfo !=null){
			//增加经验值
			TbExperience experience = new TbExperience();
			experience.setCreateBy(tbWebUserMapper.selectWebUser(shopId).getId());
			experience.setOrderNumber(orderNo);
			experience.setType(HglContants.SERVICE_EXPERIENCE);
			experience.setCreateDt(System.currentTimeMillis());
			//int myexperience = getMyExperience(userId);
			double exp_proportion = getMyLevel(shopInfo.getExp()).getExpProportion();
			double number = money*exp_proportion;
			int number1= new BigDecimal(number).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
			experience.setNumber(number1);
			int experienceSum = shopInfo.getExp()+number1;
			experience.setExperienceSum(experienceSum);
			experience.setDetail("购买服务"+money+"*"+exp_proportion);
			count = tbExperienceMapper.insert(experience);
			//增加优惠卷
			count = addCouponInfo(shopId, shopInfo.getShopName(), money, orderNo);
			///修改shop_info
			
			shopInfo.setExp(experienceSum);
			shopInfo.setExpLevel(getMyLevel(shopInfo.getExp()).getId());
			shopInfo.setCouponGainAmt(shopInfo.getCouponGainAmt()+money);
			shopInfo.setCouponRemainingAmt(shopInfo.getCouponRemainingAmt()+money);
			count = tbShopInfoMapper.updateByPrimaryKeySelective(shopInfo);
			
			
		}
		return count;
	}
	/**
	 * 根据经验值查等级
	 * @param experience
	 * @return
	 * @author zss
	 */
	public TbShopLevel getMyLevel(int experience){
		TbShopLevel level = levelMapper.selectLevelByExperience(experience);
		return level;
	}

//----------------------订单回调
	@Override
	public Map<String, Object> payCallOderResults(String orderNumber,
			Double money,String type) {
		int count =0;
		Map<String,Object> map = new HashMap<String,Object>();
		TbOrderGroup orderGroup = tbOrderGroupMapper.selectByOrderNumber(orderNumber);
		if(orderGroup !=null){
			TbWebUser tbWebUser = tbWebUserMapper.selectWebUser(orderGroup.getBuyerId());
			//修改有关订单的内容
			count =payMyOrderGroup(orderGroup, tbWebUser.getId());
			
		}else{
			map.put("errmsg", "找不到对应的订单号！");
		}
		if(count >0){
			map.put("errcode", "0");
			map.put("msg", "支付回调成功!");
		}else{
			map.put("errcode", "1");
			map.put("msg", "支付回调失败!");
		}
		
		
		return map;
	}
	
/*	 private void updateAgency(String orderNumber,Integer brandId, Integer buyerId) {
	   Criteria criteria = new Criteria();
		criteria.put("brandId", brandId);
		criteria.put("shopId", buyerId);
		criteria.put("orderState", HglContants.ORDER_STATE_200);//待确认
		List<AgencyDto> agencies = tbAgencyMapper.selectByCriteria(criteria);
		if(agencies!=null && agencies.size()>0){
			TbAgency agency = agencies.get(0);
			agency.setOrderid(orderNumber);
			agency.setOrderState(HglContants.ORDER_STATE_220);
			agency.setState(HglContants.MERCHANTS_AGENCY_STATE2);
		}
	 }*/

	public int payMyOrderGroup(TbOrderGroup myOrderForm,Integer userId){
		    int count =0;
	    	//订单状态修改
	    	TbOrderGroup tbOrderGroup= tbOrderGroupMapper.selectByPrimaryKey(myOrderForm.getId());
			tbOrderGroup.setOrderStatus(HglContants.ORDER_STATE_208);
			tbOrderGroup.setVersion(myOrderForm.getVersion());
			 count = tbOrderGroupMapper.updateByPrimaryKeySelective(tbOrderGroup);
			if(count == 0){
				return count;
			}
			//订单跟踪记录
			 insertTbOrderTrackInfo(myOrderForm.getId(),"订单支付成功",userId,tbOrderGroup.getOrderStatus());
			
			//增加库存锁定记录
			//1:根据订单号去查询订单明细表 selectByObject(batchInsertSelective
		/*	Criteria  example = new Criteria();
			example.put("orderGroupId",myOrderForm.getOrderGroupId());*/
			List<OrderGroupDetailDto> dList = tbOrderDetailMapper.selectOrderDetailBuyOrderID(myOrderForm.getId());
			List<TbInventoryLock> iList = new ArrayList<TbInventoryLock>();
			if(dList.size() > 0){
				for(int i=0 ;i<dList.size() ;i++){
					TbInventoryLock tbInventoryLock = new TbInventoryLock();
					tbInventoryLock.setOrderId(myOrderForm.getId());
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
			tbOrderDetailMapper.updateSaleInventoryPayment(myOrderForm.getId());
			//更新店铺剩余金额
			
			//减去支付金额
			TbShopInfo tbShopInfo = tbShopInfoMapper.selectByPrimaryKey(tbOrderGroup.getBuyerId());
			 BigDecimal b1 = new BigDecimal(tbOrderGroup.getPayMoney());  
			 BigDecimal b2 = new BigDecimal(tbShopInfo.getBalance());  
			 Double balance = new Double(b2.subtract(b1).doubleValue());
			 /*tbShopInfo.setBalance(balance);*/
			 tbShopInfoMapper.updateByPrimaryKeySelective(tbShopInfo);
				TbCashAccount tbCashAccount = new TbCashAccount();
				tbCashAccount.setCashOut(tbOrderGroup.getPayMoney());
				tbCashAccount.setBalance(balance);
				tbCashAccount.setOperationDt(System.currentTimeMillis());
				tbCashAccount.setShopId(tbOrderGroup.getBuyerId());
				tbCashAccount.setOrderSerialNo(tbOrderGroup.getOrderSerialNo());
				tbCashAccountMapper.insertSelective(tbCashAccount);
			return count;
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


	@Override
	public Map<String, Object> payCallSuccessResults(String orderNumber,
			Double money) {
		Map<String,Object> map = new HashMap<String,Object>();
		String type= (String) orderNumber.subSequence(0, 1);
		if(type.equals("F")){//服务支付
			 map= payCallResults(orderNumber, money);
		}else{//购物订单
			map = payCallOderResults(orderNumber,money,type);
		}
		return map;
	}

}
