package com.liguo.hgl.service.impl;

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

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.common.MessageEnum;
import com.liguo.hgl.dto.MyOrderForm;
import com.liguo.hgl.exceptions.TransactionException;
import com.liguo.hgl.proxydao.dao.TbAccountMapper;
import com.liguo.hgl.proxydao.dao.TbCashAccountMapper;
import com.liguo.hgl.proxydao.dao.TbFreezingMapper;
import com.liguo.hgl.proxydao.dao.TbIntegralDetailedMapper;
import com.liguo.hgl.proxydao.dao.TbIntegralMapper;
import com.liguo.hgl.proxydao.dao.TbIntegralRulesMapper;
import com.liguo.hgl.proxydao.dao.TbShopInfoMapper;
import com.liguo.hgl.proxydao.dao.TbWapAddressHistoryMapper;
import com.liguo.hgl.proxydao.dao.TbWapAddressMapper;
import com.liguo.hgl.proxydao.dao.TbWapInventoryLockMapper;
import com.liguo.hgl.proxydao.dao.TbWapOrderDetailMapper;
import com.liguo.hgl.proxydao.dao.TbWapOrderGroupMapper;
import com.liguo.hgl.proxydao.dao.TbWapOrderTrackingMapper;
import com.liguo.hgl.proxydao.dao.TbWapShoppingCartMapper;
import com.liguo.hgl.proxydao.dao.TbWebUserMapper;
import com.liguo.hgl.proxydao.dto.CalulateFreightDto;
import com.liguo.hgl.proxydao.dto.OrderGroupDetailDto;
import com.liguo.hgl.proxydao.dto.SubmitOrderForm;
import com.liguo.hgl.proxydao.dto.WapShoppingCartInfoDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.MyOrderStatesCount;
import com.liguo.hgl.proxydao.model.TbAccount;
import com.liguo.hgl.proxydao.model.TbCashAccount;
import com.liguo.hgl.proxydao.model.TbFreezing;
import com.liguo.hgl.proxydao.model.TbIntegral;
import com.liguo.hgl.proxydao.model.TbIntegralDetailed;
import com.liguo.hgl.proxydao.model.TbIntegralRules;
import com.liguo.hgl.proxydao.model.TbShopInfo;
import com.liguo.hgl.proxydao.model.TbUserGroup;
import com.liguo.hgl.proxydao.model.TbWapAddress;
import com.liguo.hgl.proxydao.model.TbWapAddressHistory;
import com.liguo.hgl.proxydao.model.TbWapInventoryLock;
import com.liguo.hgl.proxydao.model.TbWapOrderDetail;
import com.liguo.hgl.proxydao.model.TbWapOrderGroup;
import com.liguo.hgl.proxydao.model.TbWapOrderTracking;
import com.liguo.hgl.proxydao.model.TbWebUser;
import com.liguo.hgl.proxydao.model.WapOrderDetailDto;
import com.liguo.hgl.proxydao.model.WapOrderGroupDto;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.PaymentHandler;
import com.liguo.hgl.service.TbIntegralService;
import com.liguo.hgl.service.TbOrderDetailService;
import com.liguo.hgl.service.TbSystemConfigService;
import com.liguo.hgl.service.TbUserGroupService;
import com.liguo.hgl.service.TbWapOrderGroupService;
import com.liguo.hgl.service.TbWebUserService;

@Service
@Scope(value = "prototype")
public class TbWapOrderGroupServiceImpl implements TbWapOrderGroupService {

	private static Object lockObj = "lockerOrder";

	private static final Logger logger = LoggerFactory.getLogger(TbWapOrderGroupServiceImpl.class);

	@Autowired
	private TbWapOrderGroupMapper tbWapOrderGroupMapper;
	@Autowired
	private TbWapAddressMapper tbWapAddressMapper;
	@Autowired
	private TbWapAddressHistoryMapper tbWapAddressHistoryMapper;
	@Autowired
	private TbWapShoppingCartMapper tbWapShoppingCartMapper;
	@Autowired
	private TbWapOrderTrackingMapper tbWapOrderTrackingMapper;
	@Autowired
	private TbWapOrderDetailMapper tbWapOrderDetailMapper;
	@Autowired
	private TbOrderDetailService tbOrderDetailService;
	@Autowired
	private TbWapInventoryLockMapper tbWapInventoryLockMapper;
	@Autowired
	private TbAccountMapper tbAccountMapper;
	@Autowired
	private TbFreezingMapper tbFreezingMapper;
	@Autowired
	private TbIntegralDetailedMapper tbIntegralDetailedMapper;
	@Autowired
	private TbIntegralMapper tbIntegralMapper;

	@Autowired
	private PaymentHandler PaymentHttpHandlerImpl;

	@Autowired
	private TbShopInfoMapper tbShopInfoMapper;

	@Autowired
	private TbWebUserMapper tbWebUserMapper;

	@Autowired
	private TbCashAccountMapper tbCashAccountMapper;

	@Autowired
	protected TbUserGroupService tbUserGroupService;

	@Autowired
	private TbWebUserService tbWebUserService;

	@Autowired
	private TbIntegralService tbIntegralService;

	@Autowired
	private TbSystemConfigService tbSystemConfigService;

	@Autowired
	private TbIntegralRulesMapper tbIntegralRulesMapper;

	public int countByObject(Criteria example) throws RuntimeException {
		try {
			int count = this.tbWapOrderGroupMapper.countByObject(example);
			logger.debug("count: {}", count);
			return count;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public TbWapOrderGroup selectByPrimaryKey(Integer id) throws RuntimeException {
		try {
			return this.tbWapOrderGroupMapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public List<TbWapOrderGroup> selectByObject(Criteria example) throws RuntimeException {
		try {
			return this.tbWapOrderGroupMapper.selectByObject(example);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public int deleteByPrimaryKey(Integer id) throws RuntimeException {
		try {
			return this.tbWapOrderGroupMapper.deleteByPrimaryKey(id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public int updateByPrimaryKeySelective(TbWapOrderGroup record) throws RuntimeException {
		try {
			return this.tbWapOrderGroupMapper.updateByPrimaryKeySelective(record);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public int updateByPrimaryKey(TbWapOrderGroup record) throws RuntimeException {
		try {
			return this.tbWapOrderGroupMapper.updateByPrimaryKey(record);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public int insertSelective(TbWapOrderGroup record) throws RuntimeException {
		try {
			return this.tbWapOrderGroupMapper.insertSelective(record);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 提交订单,业务处理,与店铺分割成多个订单 1. 插入地址历史表 2. 插入订单表 3. 插入订单明细表 4. 插入订单跟踪表 5.
	 * 删除已经购买的购物车中的信息,插入地址历史表
	 */
	@Override
	public int submitOrder(SubmitOrderForm submitOrderForm, Integer userId, Integer accountId) throws RuntimeException {
		// 定义订单号ID
		int orderId = 0;
		if (submitOrderForm.getCartIdList() != null && submitOrderForm.getCartIdList().length > 0) {
			// 把购物车ID转换为list
			List<String> idsList = Arrays.asList(submitOrderForm.getCartIdList());

			// 把买家留言转换为map
			Map<Integer, String> buyMessageMap = setMap(submitOrderForm.getBuyMessageList());

			// 把收货方式转换为map
			Map<Integer, String> goodsTypeMap = setMap(submitOrderForm.getGoodsTypeList());

			// 把支付方式转换为map
			Map<Integer, String> payTypeMap = setMap(submitOrderForm.getPayTypeList());

			// 把优惠方式转换为map
			Map<String, String> distributionMap = setList(submitOrderForm.getDistributionList());
			if (distributionMap != null && distributionMap.size() > 0) {
				Criteria example = new Criteria();
				example.put("shopIds", distributionMap.get("key"));
				example.put("groupIds", distributionMap.get("value"));
				List<TbUserGroup> groupList = tbUserGroupService.selectByShopGroup(example);
				distributionMap = listCastMap(groupList);
			}

			// 获取买家运费的map
			Map<Integer, CalulateFreightDto> calulateFreightMap = submitOrderForm.getCalulateFreightMap();

			// 根据选择的收货地址ID查询
			Integer addressHistoryId = modifyAddressHistory(submitOrderForm.getAddressId(), submitOrderForm.getPhone(), submitOrderForm.getRecipient(), userId);

			// 查询出品牌,产品,库存
			Criteria criteria = new Criteria();
			criteria.put("userId", userId);
			criteria.put("idsList", idsList);
			List<WapShoppingCartInfoDto> shoppingCartInfoList = this.tbWapShoppingCartMapper.selectByCartInfo(criteria);

			// 分割订单
			Map<Integer, List<WapShoppingCartInfoDto>> splitMap = new HashMap<Integer, List<WapShoppingCartInfoDto>>();
			List<WapShoppingCartInfoDto> cartInfoList = null;
			for (WapShoppingCartInfoDto cartInfo : shoppingCartInfoList) {
				cartInfoList = new ArrayList<WapShoppingCartInfoDto>();
				// 使用店铺ID作为key
				Integer key = cartInfo.getShopId();
				if (splitMap.get(key) != null) {
					splitMap.get(key).add(cartInfo);
				} else {
					cartInfoList.add(cartInfo);
					splitMap.put(key, cartInfoList);
				}
			}

			// 判断是否只是一家店铺的商品
			if (splitMap.size() > 1) {
				throw new RuntimeException("只能购买一家店铺的商品");
			}

			// 设置订单表和订单明细表数据,和计算总数量,金额
			List<TbWapOrderGroup> wapOrderGroupList = new ArrayList<TbWapOrderGroup>();
			// 订单明细的临时list
			List<TbWapOrderDetail> wapOrderDetailTempList = null;
			// 订单明细的list
			Map<String, List<TbWapOrderDetail>> wapOrderDetailMap = new HashMap<String, List<TbWapOrderDetail>>();
			// 循环订单
			for (Integer key : splitMap.keySet()) {
				// 根据店铺ID获取订单list
				cartInfoList = splitMap.get(key);
				// 单个订单的总金额
				double calculateMoney = 0;
				// 单个订单的总数量
				Integer calculateCount = 0;
				// 订单的品牌ID
				Integer brandId = null;
				// 推送材料师傅ID
				Integer repairmanId = null;
				// 订单明细的临时list
				wapOrderDetailTempList = new ArrayList<TbWapOrderDetail>();
				// 订单表
				TbWapOrderGroup wapOrderGroup = new TbWapOrderGroup();
				// 循环订单中的明细
				for (WapShoppingCartInfoDto shopCart : cartInfoList) {
					// 获取品牌ID
					if (brandId == null) {
						brandId = shopCart.getBrandId();
					}
					// 获取推送师傅的ID
					if (repairmanId == null) {
						repairmanId = shopCart.getMasterId();
					}
					// 订单数量
					Integer orderNum = shopCart.getBuyNum();
					// 出库价格乘以订单数
					BigDecimal singlePrice = shopCart.getOutstockPrice().multiply(new BigDecimal(orderNum));
					// 把总价转换为BigDecimal
					BigDecimal totalPrice = new BigDecimal(calculateMoney);
					// 总价加上单价
					calculateMoney = totalPrice.add(singlePrice).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
					// 计算累计的中数量
					calculateCount = calculateCount + orderNum;

					// 计算明细
					TbWapOrderDetail wapOrderDetail = new TbWapOrderDetail();
					wapOrderDetail.setBuyNum(orderNum);
					wapOrderDetail.setInventoryId(shopCart.getId());
					wapOrderDetail.setBuyPrice(singlePrice.doubleValue());
					wapOrderDetail.setPrice(shopCart.getOutstockPrice().doubleValue());
					wapOrderDetailTempList.add(wapOrderDetail);
				}

				// 设置订单所有bean的值
				String orderSerialNo = makeOrderNum("W");
				wapOrderGroup.setOrderSerialNo(orderSerialNo);
				wapOrderGroup.setTotalNum(calculateCount);
				wapOrderGroup.setTotalMoney(calculateMoney);
				wapOrderGroup.setShopId(key);
				wapOrderGroup.setAddressId(addressHistoryId);
				wapOrderGroup.setBrandId(brandId);
				wapOrderGroup.setOrderStatus(HglContants.WAP_ORDER_STATE_600);
				String payTypeStr = payTypeMap.get(key);
				wapOrderGroup.setSettleType("".equals(payTypeStr) ? HglContants.SETTLE_TYPE_240 : Integer.parseInt(payTypeStr));
				String goodsTypeStr = goodsTypeMap.get(key);
				wapOrderGroup.setPlatFlag("".equals(goodsTypeStr) ? HglContants.RECEIVING_TYPE_416 : Integer.parseInt(goodsTypeStr));
				wapOrderGroup.setBuyerId(userId);
				wapOrderGroup.setOrderType(HglContants.ORDINARY_ORDER);
				wapOrderGroup.setRepairmanId(repairmanId);
				double postage = 0;
				// 如果收货方式是送货,就获取店铺的邮费,是自提不需要获取店铺的邮费
				if (String.valueOf(HglContants.RECEIVING_TYPE_416).equals(goodsTypeStr)) {
					CalulateFreightDto cfd = calulateFreightMap.get(key);
					postage = cfd == null ? 0 : cfd.getFreight();
				}
				wapOrderGroup.setPostage(postage);

				// 优惠折扣
				BigDecimal orderTotalMoney = new BigDecimal(calculateMoney);
				String distr = distributionMap.get(String.valueOf(key));
				if (distr != null) {
					double distrDouble = Double.parseDouble("0." + distr);
					orderTotalMoney = orderTotalMoney.multiply(new BigDecimal(distrDouble)).setScale(2, BigDecimal.ROUND_HALF_UP);
				}

				// 订单总金额相加邮费=支付金额
				double payMoney = orderTotalMoney.add(new BigDecimal(postage)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				wapOrderGroup.setPayMoney(payMoney);
				String message = buyMessageMap.get(key);
				wapOrderGroup.setMessage(message == null ? "" : message);
				wapOrderGroup.setCreateDt(System.currentTimeMillis());
				wapOrderGroup.setDeleteFlag(0);
				wapOrderGroup.setDeleteFlagSeller(0);
				wapOrderGroup.setAccountId(accountId);
				wapOrderGroup.setRebate(0);
				wapOrderGroup.setVersion(0);
				wapOrderGroupList.add(wapOrderGroup);
				wapOrderDetailMap.put(orderSerialNo, wapOrderDetailTempList);
			}

			// wap-订单明细表list
			List<TbWapOrderDetail> wapOrderDetailList = new ArrayList<TbWapOrderDetail>();
			// wap-订单跟踪表list
			List<TbWapOrderTracking> wapOrderTrackingList = new ArrayList<TbWapOrderTracking>();
			// 自动确认订单
			List<TbWapOrderTracking> automaticWapOrderTrackingList = new ArrayList<TbWapOrderTracking>();
			// 循环插入订单
			for (TbWapOrderGroup wapOrderGroup : wapOrderGroupList) {
				int wapOrderGroupCount = tbWapOrderGroupMapper.insert(wapOrderGroup);
				orderId = wapOrderGroup.getId();
				logger.info("wap-订单插入记录: " + wapOrderGroupCount);

				wapOrderDetailTempList = wapOrderDetailMap.get(wapOrderGroup.getOrderSerialNo());
				if (wapOrderDetailTempList != null && wapOrderDetailTempList.size() > 0) {
					for (TbWapOrderDetail wapOrderDetail : wapOrderDetailTempList) {
						wapOrderDetail.setOrderGroupId(wapOrderGroup.getId());
					}
					wapOrderDetailList.addAll(wapOrderDetailTempList);
				}
				TbWapOrderTracking wapOrderTracking = new TbWapOrderTracking();
				wapOrderTracking.setOrderGroupId(wapOrderGroup.getId());
				wapOrderTracking.setOperateName("买家已提交订单");
				wapOrderTracking.setOperateBy(userId);
				wapOrderTracking.setOrderState(HglContants.WAP_ORDER_STATE_600);
				wapOrderTracking.setOperateDt(System.currentTimeMillis());
				wapOrderTrackingList.add(wapOrderTracking);

				// 自动确认订单
				TbShopInfo info = tbShopInfoMapper.selectByPrimaryKey(wapOrderGroup.getShopId());
				if (info.getIsAutomaticOrder().equals(HglContants.SHOP_ISAUTOMATICORDER)) {
					TbWapOrderGroup orderGroup = tbWapOrderGroupMapper.selectByPrimaryKey(wapOrderGroup.getId());
					orderGroup.setOrderStatus(HglContants.WAP_ORDER_STATE_602);
					orderGroup.setVersion(orderGroup.getVersion());
					tbWapOrderGroupMapper.updateByPrimaryKeySelective(orderGroup);

					// 订单跟踪记录
					// insertTbWapOrderTrackInfo(orderGroup.getId(),"店家自动确认订单",orderGroup.getShopId(),orderGroup.getOrderStatus());

					TbWapOrderTracking trackingRecord = new TbWapOrderTracking();
					trackingRecord.setOrderGroupId(orderGroup.getId());
					trackingRecord.setOperateName("店家自动确认订单");
					TbWebUser webUser = tbWebUserMapper.selectWebUser(orderGroup.getShopId());
					trackingRecord.setOperateBy(webUser == null ? 0 : webUser.getId());
					trackingRecord.setOrderState(orderGroup.getOrderStatus());
					trackingRecord.setOperateDt(System.currentTimeMillis());
					automaticWapOrderTrackingList.add(trackingRecord);
				}
			}

			// 批量插入订单明细表
			int wapOrderDetailCount = tbWapOrderDetailMapper.batchInsert(wapOrderDetailList);
			logger.info("订单明细插入记录: " + wapOrderDetailCount);

			// 订单跟踪记录
			int orderTrackCount = tbWapOrderTrackingMapper.batchInsert(wapOrderTrackingList);
			logger.info("订单跟踪插入记录: " + orderTrackCount);

			// 生成订单后删除购物车的商品
			int delCount = this.tbWapShoppingCartMapper.deleteAllByPrimaryKey(criteria);
			logger.info("生成订单后删除购物车记录: " + delCount);

			if (automaticWapOrderTrackingList.size() > 0) {
				int automaticWapOrderTrackingcount = tbWapOrderTrackingMapper.batchInsert(automaticWapOrderTrackingList);
				logger.info("店家自动确认订单记录: " + automaticWapOrderTrackingcount);
			}

			// 设置返回的订单号和订单金额
			// resultMap.put("totalPayMoney", totalPayMoney);
			// resultMap.put("wapOrderGroupList", wapOrderGroupList);
		}
		return orderId;
	}

	private void automaticOder(TbWapOrderGroup wapOrderGroup) {
		TbShopInfo info = tbShopInfoMapper.selectByPrimaryKey(wapOrderGroup.getShopId());
		if (info.getIsAutomaticOrder().equals(HglContants.SHOP_ISAUTOMATICORDER)) {
			TbWapOrderGroup orderGroup = tbWapOrderGroupMapper.selectByPrimaryKey(wapOrderGroup.getId());
			orderGroup.setOrderStatus(HglContants.WAP_ORDER_STATE_602);
			orderGroup.setVersion(orderGroup.getVersion());
			tbWapOrderGroupMapper.updateByPrimaryKeySelective(orderGroup);
			// 订单跟踪记录
			insertTbWapOrderTrackInfo(orderGroup.getId(), "店家自动确认订单", orderGroup.getShopId(), orderGroup.getOrderStatus());
		}
	}

	/**
	 * 生成订单号
	 */
	public String makeOrderNum(String prefix) {
		String finOrderNum = "";
		try {
			// 最终生成的订单号
			synchronized (lockObj) {
				long nowLong = Long.parseLong(new SimpleDateFormat("yyMMddHHmmssSSS").format(new Date()));
				for (int i = 0; i < 3; i++) {
					finOrderNum = finOrderNum + (int) (Math.random() * 10) + "";
				}
				return prefix + nowLong + finOrderNum;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return finOrderNum;
	}

	/**
	 * 把买家留言list转换为map
	 * 
	 * @param buyMessageList
	 * @return
	 * @throws RuntimeException
	 */
	public Map<Integer, String> setMap(String[] buyMessageList) throws RuntimeException {
		Map<Integer, String> tempMap = new HashMap<Integer, String>();
		for (String strs : buyMessageList) {
			String[] goods = strs.split("=");
			String value = "";
			if (goods.length == 2) {
				value = goods[1];
			}
			tempMap.put(Integer.parseInt(goods[0]), value);
		}
		return tempMap;
	}

	/**
	 * 转换map
	 * 
	 * @param buyMessageList
	 * @return
	 * @throws RuntimeException
	 */
	public Map<String, String> setList(String[] buyMessageList) throws RuntimeException {
		Map<String, String> tempMap = new HashMap<String, String>();
		String key = "";
		String value = "";
		for (String strs : buyMessageList) {
			String[] goods = strs.split("=");
			key = key + goods[0] + ",";
			if (goods.length == 2) {
				value = value + goods[1] + ",";
			}
		}
		if (!"".equals(key) && key.indexOf(",") != -1) {
			key = key.substring(0, key.lastIndexOf(","));
		}
		if (!"".equals(value) && value.indexOf(",") != -1) {
			value = value.substring(0, value.lastIndexOf(","));
		}
		tempMap.put("key", key);
		tempMap.put("value", value);
		return tempMap;
	}

	/**
	 * 把买家留言list转换为map
	 * 
	 * @param buyMessageList
	 * @return
	 * @throws RuntimeException
	 */
	public Map<String, String> listCastMap(List<TbUserGroup> list) throws RuntimeException {
		Map<String, String> tempMap = new HashMap<String, String>();
		for (TbUserGroup group : list) {
			tempMap.put(String.valueOf(group.getShopId()), String.valueOf(group.getDiscount()));
		}
		return tempMap;
	}

	/**
	 * 店家确认订单
	 * */
	public void configOrderGroup(MyOrderForm myOrderForm, Integer userId) {
		// 订单状态修改
		TbWapOrderGroup tbOrderGroup = selectByPrimaryKey(myOrderForm.getOrderGroupId());
		tbOrderGroup.setOrderStatus(HglContants.WAP_ORDER_STATE_602);
		tbOrderGroup.setVersion(myOrderForm.getVersion());
		int count = updateByPrimaryKeySelective(tbOrderGroup);
		if (count > 0) {
			// 订单跟踪记录
			insertTbWapOrderTrackInfo(myOrderForm.getOrderGroupId(), "店家确认订单", userId, tbOrderGroup.getOrderStatus());
		}
	}

	/**
	 * 店家确认到款
	 * */
	public void configReceivePayment(MyOrderForm myOrderForm, Integer userId, String trackInfo) {
		// 订单状态修改
		TbWapOrderGroup tbOrderGroup = selectByPrimaryKey(myOrderForm.getOrderGroupId());
		tbOrderGroup.setOrderStatus(HglContants.WAP_ORDER_STATE_608);
		tbOrderGroup.setVersion(myOrderForm.getVersion());
		int count = updateByPrimaryKeySelective(tbOrderGroup);
		if (count > 0) {
			// 订单跟踪记录
			insertTbWapOrderTrackInfo(myOrderForm.getOrderGroupId(), trackInfo, userId, tbOrderGroup.getOrderStatus());
			// 增加库存锁定记录
			// 1:根据订单号去查询订单明细表
			Criteria example = new Criteria();
			example.put("orderGroupId", myOrderForm.getOrderGroupId());
			List<TbWapOrderDetail> dList = tbWapOrderDetailMapper.selectByObject(example);
			List<TbWapInventoryLock> iList = new ArrayList<TbWapInventoryLock>();
			if (dList.size() > 0) {
				for (int i = 0; i < dList.size(); i++) {
					TbWapInventoryLock tbInventoryLock = new TbWapInventoryLock();
					tbInventoryLock.setOrderId(myOrderForm.getOrderGroupId());
					tbInventoryLock.setInventoryId(dList.get(i).getInventoryId());
					tbInventoryLock.setOrderStatus(tbOrderGroup.getOrderStatus());
					tbInventoryLock.setOrderType(tbOrderGroup.getOrderType());
					// 锁定量为订单支付时的购买量
					tbInventoryLock.setLockQuantity(dList.get(i).getBuyNum());
					iList.add(tbInventoryLock);
				}
			}
			for (TbWapInventoryLock t : iList) {
				tbWapInventoryLockMapper.insert(t);
			}
			/* tbWapInventoryLockMapper.batchInsertSelective(iList); */
			// 增加占用库存量
			tbWapOrderDetailMapper.updateSaleInventoryPayment(myOrderForm.getOrderGroupId());
		}
	}

	/**
	 * 店家发货
	 * */
	public void sendOutGoods(MyOrderForm myOrderForm, Integer userId) {
		TbWapOrderGroup tbOrderGroup = selectByPrimaryKey(myOrderForm.getOrderGroupId());
		tbOrderGroup.setOrderStatus(HglContants.WAP_ORDER_STATE_610);
		tbOrderGroup.setStopReason(myOrderForm.getStopReason());
		tbOrderGroup.setUpdateTime(Calendar.getInstance().getTimeInMillis());
		tbOrderGroup.setVersion(myOrderForm.getVersion());
		int count = updateByPrimaryKeySelective(tbOrderGroup);
		if (count > 0) {
			insertTbWapOrderTrackInfo(myOrderForm.getOrderGroupId(), "卖家已经发货", userId, tbOrderGroup.getOrderStatus());
			// 更新该订单下所有的库存，更新库存占用量，减库存量和库存占用量
			tbWapOrderDetailMapper.shopSendGoods(myOrderForm.getOrderGroupId());
		}
	}

	// 订单终止时进行减库存占用量
	public void followUpOperation(TbWapOrderGroup tbOrderGroup) {
		int id = tbOrderGroup.getId();
		// 判断这个订单有没有发货
		if (HglContants.ORDER_STATE_208.toString().equals(tbOrderGroup.getStopStatus() + "")) {
			// 如果还没有发货：库存量不动，减去占用量
			tbWapOrderDetailMapper.updateSaleInventoryStopOrder(id);
		} else {
			// 如果发货了,增加他的库存量
			tbWapOrderDetailMapper.stopOrderForSendGoods(id);
		}
		// 删除在支付时生成的锁表记录
		batchDeleteTbInventoryLock(id);
		// 如果是线下操作不涉及金额
		if (HglContants.SETTLE_TYPE_240.toString().equals(tbOrderGroup.getSettleType().toString())) {
			TbAccount tbAccount = tbAccountMapper.selectByPrimaryKey(tbOrderGroup.getAccountId());
			BigDecimal freeze = new BigDecimal(tbAccount.getFreeze());
			BigDecimal payMoney = new BigDecimal(tbOrderGroup.getPayMoney());
			// 减掉资金冻结金额
			tbAccount.setFreeze(freeze.subtract(payMoney).doubleValue());

			tbAccountMapper.updateByPrimaryKeySelective(tbAccount);

			Criteria example = new Criteria();
			example.put("orderSerialNo", tbOrderGroup.getOrderSerialNo());
			example.put("orderId", tbOrderGroup.getId());
			TbFreezing tbFreezing = tbFreezingMapper.selectByObject(example).get(0);
			// 该笔记录已经结束
			tbFreezing.setFinish(1);
			tbFreezing.setUnfreezeMoney(tbOrderGroup.getPayMoney());
			tbFreezingMapper.updateByPrimaryKey(tbFreezing);
		}
	}

	public void batchDeleteTbInventoryLock(Integer orderGroupId) {
		Criteria example = new Criteria();
		example.put("orderId", orderGroupId);
		List<TbWapInventoryLock> tList = tbWapInventoryLockMapper.selectByObject(example);
		List<Integer> ls = new ArrayList<Integer>();
		if (tList.size() > 0) {
			for (int i = 0; i < tList.size(); i++) {
				ls.add(tList.get(i).getId());
			}
			tbWapInventoryLockMapper.batchDeleteTbInventoryLock(ls);
		}
	}

	private void insertTbWapOrderTrackInfo(Integer orderGroupId, String info, Integer userId, Integer orderState) {
		TbWapOrderTracking record = new TbWapOrderTracking();
		record.setOrderGroupId(orderGroupId);
		record.setOperateName(info);
		record.setOperateBy(userId);
		record.setOrderState(orderState);
		record.setOperateDt(System.currentTimeMillis());
		tbWapOrderTrackingMapper.insertSelective(record);
	}

	@Override
	public MyOrderStatesCount selectMyOrderStatesCountWap(Integer shopId) {
		return this.tbWapOrderGroupMapper.selectMyOrderStatesCount(shopId);
	}

	/**
	 * 店家根据ShopID去查询所有销售订单
	 * */
	@Override
	public List<WapOrderGroupDto> selectOrderList(MyOrderForm myOrderForm, PageDto page) {
		try {
			myOrderForm.setStartTime(setDate(myOrderForm.getStartTime(), "yyyy-MM-dd").toString());
			myOrderForm.setEndTime((setDate(myOrderForm.getEndTime(), "yyyy-MM-dd") + 86400000) + "");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (myOrderForm.getOrderState() != null) {
			if (myOrderForm.getOrderState().equals("0")) {
				myOrderForm.setOrderState(null);
			} else {
				myOrderForm.setOrderStateList(Arrays.asList(myOrderForm.getOrderState().split(",")));
			}
		}
		if (!StringUtils.isBlank(myOrderForm.getSearchText())) {
			myOrderForm.setSearchText(myOrderForm.getSearchText().trim());
		}
		List<WapOrderGroupDto> dtoList = selectOrderGroupList(myOrderForm, page);
		return dtoList;
	}

	private Long setDate(String time, String fomart) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(fomart);
		long millionSeconds = sdf.parse(time).getTime();// 毫秒
		return millionSeconds;
	}

	/*************************************** wap 买家操作 ************************************************************************************/
	//
	@Override
	public List<WapOrderGroupDto> selectOrderGroupList(MyOrderForm myOrderForm, PageDto page) {
		if (!StringUtils.isBlank(myOrderForm.getOrderState())) {
			myOrderForm.setOrderStateList(Arrays.asList(myOrderForm.getOrderState().split(",")));
		}
		Criteria parameter = new Criteria();
		if (myOrderForm.getShopId() == null) {
			parameter.put("deleteFlag", "0");
		}
		if (myOrderForm.getUserId() == null) {
			parameter.put("deleteFlagSeller", "0");
		}
		parameter.put("shopId", myOrderForm.getShopId());
		parameter.put("userId", myOrderForm.getUserId());
		parameter.put("orderType", myOrderForm.getOrderType());
		parameter.put("orderStateList", myOrderForm.getOrderStateList());
		parameter.put("saleOrderFlag", myOrderForm.getSaleOrderFlag());
		/* parameter.put("orderState", myOrderForm.getOrderState()); */
		parameter.put("startTime", myOrderForm.getStartTime());
		parameter.put("endTime", myOrderForm.getEndTime());
		parameter.put("lon", myOrderForm.getLon());
		parameter.put("lat", myOrderForm.getLat());
		if (!StringUtils.isBlank(myOrderForm.getSearchText())) {
			parameter.put("searchText", myOrderForm.getSearchText().trim());
		}

		List<WapOrderGroupDto> dtoList = tbWapOrderGroupMapper.selectOrderGroupList(parameter, page);
		return dtoList;
	}

	@Override
	public List<WapOrderGroupDto> selectOrderGroupListWap(MyOrderForm myOrderForm, PageDto page) {
		List<WapOrderGroupDto> dtoList = selectOrderGroupList(myOrderForm, page);
		splitSpecifications(dtoList);
		return dtoList;
	}

	/**
	 * wap买家确认收货
	 * */
	@Override
	public void configGoodsReceipt(MyOrderForm myOrderForm, Integer userId) {
		TbWapOrderGroup tbOrderGroup = selectByPrimaryKey(myOrderForm.getOrderGroupId());
		if (tbOrderGroup.getBuyerId().equals(userId)) {
			// 订单状态修改
			tbOrderGroup.setOrderStatus(HglContants.WAP_ORDER_STATE_612);
			tbOrderGroup.setVersion(myOrderForm.getVersion());
			int count = updateByPrimaryKeySelective(tbOrderGroup);
			if (count > 0) {
				insertTbWapOrderTrackInfo(myOrderForm.getOrderGroupId(), "买家确认收货", userId, tbOrderGroup.getOrderStatus());
				// 增销量
				tbWapOrderGroupMapper.updateSaleNumByOrderId(myOrderForm.getOrderGroupId());
				// 买家金额操作.......................................................................................
				// 如果该订单是线下交易则不对资金进行操作
				if (HglContants.SETTLE_TYPE_240.toString().equals(tbOrderGroup.getSettleType().toString())) {

					logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>确认收货资金操作：" + tbOrderGroup.getOrderSerialNo());
					TbAccount tbAccount = tbAccountMapper.selectByPrimaryKey(tbOrderGroup.getAccountId());
					// 减去用户的账户金额和冻结金额
					BigDecimal freeze = new BigDecimal(tbAccount.getFreeze());
					BigDecimal payMoney = new BigDecimal(tbOrderGroup.getPayMoney());
					BigDecimal balance = new BigDecimal(tbAccount.getBalance());

					tbAccount.setBalance(balance.subtract(payMoney).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
					tbAccount.setFreeze(freeze.subtract(payMoney).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
					tbAccountMapper.updateByPrimaryKeySelective(tbAccount);
					logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>减去用户的账户金额和冻结金额：" + tbOrderGroup.getOrderSerialNo());
					// 增加买家的资金明细记录
					TbCashAccount buyerAccount = new TbCashAccount();
					buyerAccount.setCashOut(tbOrderGroup.getPayMoney());
					buyerAccount.setBalance(tbAccount.getBalance());
					buyerAccount.setOperationDt(System.currentTimeMillis());
					buyerAccount.setShopId(tbOrderGroup.getShopId());
					buyerAccount.setPlatform(1);
					buyerAccount.setAccountId(tbOrderGroup.getAccountId());
					buyerAccount.setOrderSerialNo(tbOrderGroup.getOrderSerialNo());
					tbCashAccountMapper.insertSelective(buyerAccount);
					logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>增加买家的资金明细记录：" + tbOrderGroup.getOrderSerialNo());

					// 卖家金额操作.......................................................................................
					// 增加卖家的资金明细记录
					logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>增加卖家的资金：" + tbOrderGroup.getOrderSerialNo());
					TbShopInfo tbShopInfo = tbShopInfoMapper.selectByPrimaryKey(tbOrderGroup.getShopId());
					TbAccount sellerA = tbAccountMapper.selectByPrimaryKey(tbShopInfo.getAccountId());

					BigDecimal oPayMoney = new BigDecimal(tbOrderGroup.getPayMoney());
					BigDecimal sBalance = new BigDecimal(sellerA.getBalance());
					sellerA.setBalance(sBalance.add(oPayMoney).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
					tbAccountMapper.updateByPrimaryKeySelective(sellerA);

					logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>增加卖家的资金明细记录：" + tbOrderGroup.getOrderSerialNo());
					TbCashAccount sellerAccount = new TbCashAccount();
					sellerAccount.setCashIn(tbOrderGroup.getPayMoney());
					sellerAccount.setBalance(sellerA.getBalance());
					sellerAccount.setOperationDt(System.currentTimeMillis());
					sellerAccount.setShopId(tbOrderGroup.getShopId());
					sellerAccount.setPlatform(1);
					sellerAccount.setAccountId(tbShopInfo.getAccountId());
					sellerAccount.setOrderSerialNo(tbOrderGroup.getOrderSerialNo());
					tbCashAccountMapper.insertSelective(sellerAccount);

					Criteria example = new Criteria();
					example.put("orderSerialNo", tbOrderGroup.getOrderSerialNo());
					example.put("orderId", tbOrderGroup.getId());
					TbFreezing tbFreezing = tbFreezingMapper.selectByObject(example).get(0);
					// 该笔记录已经结束
					logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>该笔冻结记录已经结束：" + tbOrderGroup.getOrderSerialNo());
					tbFreezing.setFinish(1);
					tbFreezing.setUnfreezeMoney(tbOrderGroup.getPayMoney());
					tbFreezingMapper.updateByPrimaryKey(tbFreezing);
				}
				logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>增加积分：" + tbOrderGroup.getOrderSerialNo());
				IntegralInfo(tbOrderGroup);
			}
		}
	}

	// 买家确认收货时对于积分的操作：增加买家的积分，减去店铺的积。
	private void IntegralInfo(TbWapOrderGroup tbOrderGroup) {
		Double payMoney = tbOrderGroup.getPayMoney();
		// 根据金额得到店铺积分设置规则
		Criteria c = new Criteria();
		c.put("money", payMoney);
		c.put("shopId", tbOrderGroup.getShopId());
		TbIntegralRules integralRules = tbIntegralRulesMapper.selectIntegralByMoney(c);
		if (integralRules != null) {
			Integer integral = 0;
			if(integralRules.getMoney() > 0){
				//根据赠送类型和钱算出要赠送的积分
				if(integralRules.getType() == HglContants.INTEGRALL_TYPE_0){
					//按百分比赠送积分
					integral = (int) Math.round(payMoney*integralRules.getMoney()/100);
				}else{
					//这是按固定值赠送积分
					integral = integralRules.getMoney();
				}
				if(integral > 0){
					//根据店铺的Id查询积分账户，如果剩余积分足够扣除就进行积分扣除操作
					TbIntegral tbIntegral = tbIntegralMapper.selectByShopId(tbOrderGroup.getShopId());
					if(tbIntegral.getIntegral() > integral){
						//扣除店铺的积分。
						tbIntegral.setIntegral(tbIntegral.getIntegral() - integral);
						tbIntegralMapper.updateByPrimaryKey(tbIntegral);
						//成生积分明细
						TbIntegralDetailed tbIntegralDetailed = new TbIntegralDetailed();
						tbIntegralDetailed.setIntegralId(tbIntegral.getId());
						tbIntegralDetailed.setOpearDt(System.currentTimeMillis());
						tbIntegralDetailed.setOrderId(tbOrderGroup.getId());
						tbIntegralDetailed.setOrderSerialNo(tbOrderGroup.getOrderSerialNo());
						tbIntegralDetailed.setIntegral(-integral);
						tbIntegralDetailed.setType(1);
						tbIntegralDetailed.setVersion(0);
						tbIntegralDetailedMapper.insert(tbIntegralDetailed);
						
						//得到买家积分账户，增加积分，生成积分明细
						Criteria c1 = new Criteria();
						c1.put("userId", tbOrderGroup.getBuyerId());
						TbIntegral t = tbIntegralMapper.selectByObject(c1).get(0);
						t.setIntegral(t.getIntegral() + integral);
						tbIntegralMapper.updateByPrimaryKey(t);
						//成生积分明细
						TbIntegralDetailed buyerTbIntegral = new TbIntegralDetailed();
						buyerTbIntegral.setIntegralId( t.getId());
						buyerTbIntegral.setOpearDt(System.currentTimeMillis());
						buyerTbIntegral.setOrderId(tbOrderGroup.getId());
						buyerTbIntegral.setOrderSerialNo(tbOrderGroup.getOrderSerialNo());
						buyerTbIntegral.setIntegral(integral);
						buyerTbIntegral.setType(1);
						buyerTbIntegral.setVersion(0);
						tbIntegralDetailedMapper.insert(buyerTbIntegral);
					}
				}
			}
		}
	}

	/**
	 * wap 买家同意终止订单操作
	 * */
	@Override
	public void configStopOrderGroupByBuyer(MyOrderForm myOrderForm, Integer userId, String trackInfo) {
		TbWapOrderGroup tbOrderGroup = selectByPrimaryKey(myOrderForm.getOrderGroupId());
		if (tbOrderGroup.getBuyerId().equals(userId)) {
			tbOrderGroup.setOrderStatus(HglContants.WAP_ORDER_STATE_606);
			tbOrderGroup.setVersion(myOrderForm.getVersion());
			int count = updateByPrimaryKeySelective(tbOrderGroup);
			if (count > 0) {
				// 订单跟踪记录
				insertTbWapOrderTrackInfo(tbOrderGroup.getId(), trackInfo, userId, tbOrderGroup.getOrderStatus());
				followUpOperation(tbOrderGroup);
			}
		}
	}

	/**
	 * wap 买家订单取消操作
	 * */
	public void cancleMyOrderGroupByBuyer(MyOrderForm myOrderForm, Integer userId) {
		TbWapOrderGroup tbOrderGroup = selectByPrimaryKey(myOrderForm.getOrderGroupId());
		// 判断操作者的Id与订单买家Id是否匹配
		if (tbOrderGroup.getBuyerId().equals(userId)) {
			tbOrderGroup.setOrderStatus(HglContants.WAP_ORDER_STATE_622);
			tbOrderGroup.setStopReason(myOrderForm.getStopReason());
			tbOrderGroup.setVersion(myOrderForm.getVersion());
			tbOrderGroup.setCancelDt(System.currentTimeMillis());
			int count = updateByPrimaryKeySelective(tbOrderGroup);
			if (count > 0) {
				insertTbWapOrderTrackInfo(myOrderForm.getOrderGroupId(), "买家取消订单", userId, tbOrderGroup.getOrderStatus());
			}
		}
	}

	/**
	 * wap 买家订单取消操作
	 * */
	public void saveEvaluate(Integer orderGroupId, String start, String evaluate, Integer userId) {
		TbWapOrderGroup t = selectByPrimaryKey(orderGroupId);
		t.setEvaluate(evaluate);
		t.setEvaluateStart(start);
		t.setOrderStatus(HglContants.WAP_ORDER_STATE_620);
		updateByPrimaryKeySelective(t);
		insertTbWapOrderTrackInfo(orderGroupId, "买家提交评价", userId, t.getOrderStatus());
	}

	/**
	 * wap 买家申请订单终止操作
	 * */
	public void stopMyOrderGroupByBuyer(MyOrderForm myOrderForm, Integer userId) {
		TbWapOrderGroup tbOrderGroup = selectByPrimaryKey(myOrderForm.getOrderGroupId());
		// 判断操作者的shopID 是不是订单的shopID
		int status = tbOrderGroup.getOrderStatus();
		if (tbOrderGroup.getBuyerId().equals(userId)) {
			// 保存订单之前的状态
			tbOrderGroup.setStopStatus(status);
			// 订单状态修改
			tbOrderGroup.setOrderStatus(HglContants.WAP_ORDER_STATE_618);
			tbOrderGroup.setStopReason(myOrderForm.getStopReason());
			tbOrderGroup.setVersion(myOrderForm.getVersion());
			int count = updateByPrimaryKeySelective(tbOrderGroup);
			if (count > 0) {
				// 订单跟踪记录
				insertTbWapOrderTrackInfo(myOrderForm.getOrderGroupId(), "买家申请终止订单", userId, tbOrderGroup.getOrderStatus());
			}
		}
	}

	// wap买家取消订单终止
	@Override
	public void cancleStopOrderGroupByBuyer(MyOrderForm myOrderForm, Integer userId) {
		TbWapOrderGroup tbOrderGroup = selectByPrimaryKey(myOrderForm.getOrderGroupId());
		// 判断操作者的shopID 是不是订单的shopID
		if (tbOrderGroup.getBuyerId().equals(userId)) {
			// 订单状态修改
			tbOrderGroup.setOrderStatus(tbOrderGroup.getStopStatus());
			tbOrderGroup.setVersion(myOrderForm.getVersion());
			int count = updateByPrimaryKeySelective(tbOrderGroup);
			if (count > 0) {
				insertTbWapOrderTrackInfo(myOrderForm.getOrderGroupId(), "买家取消终止订单", userId, tbOrderGroup.getOrderStatus());
			}
		}
	}

	/*************************************** shop操作 ************************************************************************************/
	/**
	 * 店铺订单取消操作
	 * */
	@Override
	public void cancleMyOrderGroupByShop(MyOrderForm myOrderForm, Integer userId) {
		TbWapOrderGroup tbOrderGroup = selectByPrimaryKey(myOrderForm.getOrderGroupId());
		tbOrderGroup.setOrderStatus(HglContants.WAP_ORDER_STATE_622);
		tbOrderGroup.setStopReason(myOrderForm.getStopReason());
		tbOrderGroup.setVersion(myOrderForm.getVersion());
		tbOrderGroup.setCancelDt(System.currentTimeMillis());
		int count = updateByPrimaryKeySelective(tbOrderGroup);
		if (count > 0) {
			insertTbWapOrderTrackInfo(myOrderForm.getOrderGroupId(), "店家取消订单", userId, tbOrderGroup.getOrderStatus());
		}
	}

	/**
	 * 店铺申请订单终止操作
	 * */
	@Override
	public void stopMyOrderGroupByShop(MyOrderForm myOrderForm, Integer userId) {
		TbWapOrderGroup tbOrderGroup = selectByPrimaryKey(myOrderForm.getOrderGroupId());
		// 判断操作者的shopID 是不是订单的shopID
		int status = tbOrderGroup.getOrderStatus();
		// 保存订单之前的状态
		tbOrderGroup.setStopStatus(status);
		// 订单状态修改
		tbOrderGroup.setOrderStatus(HglContants.WAP_ORDER_STATE_616);
		tbOrderGroup.setStopReason(myOrderForm.getStopReason());
		tbOrderGroup.setVersion(myOrderForm.getVersion());
		int count = updateByPrimaryKeySelective(tbOrderGroup);
		if (count > 0) {
			// 订单跟踪记录
			insertTbWapOrderTrackInfo(myOrderForm.getOrderGroupId(), "店家申请终止订单", userId, tbOrderGroup.getOrderStatus());
		}
	}

	// 店铺取消订单终止
	@Override
	public void cancleStopOrderGroupByShop(MyOrderForm myOrderForm, Integer userId) {
		TbWapOrderGroup tbOrderGroup = selectByPrimaryKey(myOrderForm.getOrderGroupId());
		// 判断操作者的shopID 是不是订单的shopID
		// 订单状态修改
		tbOrderGroup.setOrderStatus(tbOrderGroup.getStopStatus());
		tbOrderGroup.setVersion(myOrderForm.getVersion());
		int count = updateByPrimaryKeySelective(tbOrderGroup);
		if (count > 0) {
			insertTbWapOrderTrackInfo(myOrderForm.getOrderGroupId(), "店家取消终止订单", userId, tbOrderGroup.getOrderStatus());
		}
	}

	// 店铺同意订单终止
	@Override
	public void configStopOrderGroupByShop(MyOrderForm myOrderForm, Integer userId) {
		TbWapOrderGroup tbOrderGroup = selectByPrimaryKey(myOrderForm.getOrderGroupId());
		tbOrderGroup.setOrderStatus(HglContants.WAP_ORDER_STATE_606);
		tbOrderGroup.setVersion(myOrderForm.getVersion());
		int count = updateByPrimaryKeySelective(tbOrderGroup);
		if (count > 0) {
			// 订单跟踪记录
			insertTbWapOrderTrackInfo(tbOrderGroup.getId(), "店家同意终止订单", userId, tbOrderGroup.getOrderStatus());
			followUpOperation(tbOrderGroup);
		}
	}

	@Override
	public OrderGroupDetailDto selectOrderGroupDetail(Integer id) {
		return tbWapOrderGroupMapper.selectOrderGroupDetail(id);
	}

	@Override
	public List<WapOrderGroupDto> selectOrderGroupList(Criteria parameter) {
		List<WapOrderGroupDto> list = tbWapOrderGroupMapper.selectOrderGroupList(parameter);
		splitSpecifications(list);
		return list;
	}

	/**
	 * 分割规格参数
	 * 
	 * @param dtoList
	 */
	public void splitSpecifications(List<WapOrderGroupDto> dtoList) {
		List<String> valList = new ArrayList<String>();
		List<String> atList = new ArrayList<String>();
		List<Map<String, String>> mList;
		if (dtoList.size() > 0) {
			for (WapOrderGroupDto d : dtoList) {
				if (d.getWapOrderDetailDtoList().size() > 0) {
					List<WapOrderDetailDto> wList = d.getWapOrderDetailDtoList();
					;

					for (int i = 0; i < wList.size(); i++) {
						if (StringUtils.isBlank(wList.get(i).getAttributesValues()) || StringUtils.isBlank(wList.get(i).getAttributes()))
							break;
						mList = new ArrayList<Map<String, String>>();
						valList = Arrays.asList(wList.get(i).getAttributesValues().split(";"));
						atList = Arrays.asList(wList.get(i).getAttributes().split("/"));
						Map m = new HashMap<String, String>();
						if (atList.size() != valList.size()) {
							return;
						}
						for (int a = 0; a < valList.size(); a++) {
							m.put(atList.get(a), valList.get(a));
						}
						mList.add(m);
						wList.get(i).setAttrList(mList);
					}
				}
			}
		}
	}

	/**
	 * 修改订单的地址
	 * 
	 * @param addressId
	 * @param phone
	 * @param recipient
	 * @param userId
	 */
	public Integer modifyAddressHistory(String addressId, String phone, String recipient, Integer userId) {
		// 判断用户的手机号码是否为空，如果为空测更新手机号码为提交订单填写的手机号码
		TbWebUser webUser = tbWebUserService.selectByPrimaryKey(userId);
		if (webUser != null && StringUtils.isBlank(webUser.getMobile())) {
			Criteria parameter = new Criteria();
			parameter.put("id", userId);
			parameter.put("mobile", phone);
			int mobileCount = tbWebUserService.updateUserMobile(parameter);
			logger.info("wap-更新用户手机号码记录数: " + mobileCount);
		}

		// 根据选择的收货地址ID查询
		Criteria criteria = new Criteria();
		criteria.put("id", Integer.parseInt(addressId));
		criteria.put("userId", userId);
		TbWapAddress address = tbWapAddressMapper.selectByPrimaryKey(criteria);
		address.setPhone(phone);
		address.setRecipient(recipient);
		int updAdCount = tbWapAddressMapper.updateByNamePrimaryKey(address);
		logger.info("wap-更新地址记录表: " + updAdCount);

		// 插入地址的历史表
		TbWapAddressHistory addressHistory = new TbWapAddressHistory();
		BeanUtils.copyProperties(address, addressHistory, "id", "version", "createDt");
		addressHistory.setAddressId(address.getId());
		int count = tbWapAddressHistoryMapper.insert(addressHistory);
		logger.info("wap-插入地址历史记录表: " + count);
		return addressHistory.getId();
	}

	/**
	 * 支付操作
	 * */
	public void payMyOrderGroup(Integer orderId, Integer accountId) throws TransactionException {

		TbAccount tbAccount = tbAccountMapper.selectByPrimaryKey(accountId);

		TbWapOrderGroup t = tbWapOrderGroupMapper.selectByPrimaryKey(orderId);
		// 判断该订单的账户与用户的账户是否一致
		if (!t.getAccountId().toString().equals(accountId.toString())) {
			logger.debug("支付失败,数据异常！");
			throw new TransactionException(MessageEnum.Z2002);
		}
		BigDecimal balance = new BigDecimal(tbAccount.getBalance());
		BigDecimal freeze = new BigDecimal(tbAccount.getFreeze());

		BigDecimal payMoney = new BigDecimal(t.getPayMoney());
		BigDecimal accountFreeze = new BigDecimal(tbAccount.getFreeze());

		logger.debug("money:" + balance.subtract(freeze).doubleValue() + ",payMoney" + t.getPayMoney() + ",accountId" + tbAccount.getId());
		// 支付前先判断可用余额是否大于订单总额
		if ((balance.subtract(freeze).doubleValue()) - t.getPayMoney() >= 0) {
			// 进行支付逻辑

			tbAccount.setFreeze(freeze.add(payMoney).doubleValue());
			// 1:生成冻结记录表
			TbFreezing tbFreezing = new TbFreezing();
			tbFreezing.setOrderId(orderId);
			tbFreezing.setAccountId(accountId);
			tbFreezing.setOrderSerialNo(t.getOrderSerialNo());
			tbFreezing.setFreezeMoney(t.getPayMoney());
			tbFreezing.setBalance(tbAccount.getBalance());
			tbFreezing.setFreeze(tbAccount.getFreeze());
			tbFreezing.setOpearDt(System.currentTimeMillis());
			tbFreezingMapper.insert(tbFreezing);
			// 2：对支付金额进行冻结
			tbAccount.setFreeze(payMoney.add(accountFreeze).doubleValue());
			tbAccountMapper.updateByPrimaryKeySelective(tbAccount);
		} else {
			logger.debug("金额不足，支付失败");
			throw new TransactionException(MessageEnum.Z2001);
		}
	}

	@Override
	public int doUpdateOrderGroupPrice(Integer id, Double totalMoney) {
		TbWapOrderGroup wapOrderGroup = tbWapOrderGroupMapper.selectByPrimaryKey(id);
		if (wapOrderGroup != null) {
			wapOrderGroup.setTotalMoney(totalMoney);
			wapOrderGroup.setPayMoney(totalMoney + wapOrderGroup.getPostage());
			return tbWapOrderGroupMapper.updateByPrimaryKey(wapOrderGroup);
		}
		return 0;
	}

	public static void main(String[] args) {
		long millis = Calendar.getInstance().getTimeInMillis();
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(millis);
		Date d = c.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String s = sdf.format(d);
		System.out.println(millis);
		System.out.println(s);

	}

	@Override
	public List<TbWapOrderGroup> findOrderGroupByOrderStatus(Criteria example) throws RuntimeException {

		return tbWapOrderGroupMapper.findOrderGroupByOrderStatus(example);
	}

	@Override
	public boolean doUpdateUnitPrice(Integer groupId, Integer detailId, Double unitPrice) {
		TbWapOrderDetail orderDetail = tbWapOrderDetailMapper.selectByPrimaryKey(detailId);
		TbWapOrderGroup orderGroup = this.tbWapOrderGroupMapper.selectByPrimaryKey(groupId);
		if (orderGroup != null && orderDetail != null) {
			orderDetail.setBuyPrice(unitPrice);
			orderDetail.setPrice(unitPrice);
			orderDetail.setPayMoney(orderDetail.getBuyNum() * unitPrice);
			if (tbWapOrderDetailMapper.updateByPrimaryKey(orderDetail) == 1) {
				// 订单总金额
				Criteria criteria = new Criteria();
				criteria.put("orderGroupId", groupId);
				Double totalAmount = tbWapOrderDetailMapper.selectTotalAmount(criteria);
				orderGroup.setTotalMoney(totalAmount + orderGroup.getPostage());
				orderGroup.setPayMoney(totalAmount + orderGroup.getPostage());
				if (tbWapOrderGroupMapper.updateByPrimaryKey(orderGroup) == 1) {
					return true;
				}
			} else {
				return false;
			}

		}
		return false;
	}

	public int updateOrderStatusByIds(Criteria example) throws RuntimeException {

		return tbWapOrderGroupMapper.updateOrderStatusByIds(example);
	}

	@Override
	public void weixinPay(TbWapOrderGroup t, TbAccount account) throws TransactionException {
		BigDecimal accountBalance = new BigDecimal(account.getBalance());
		BigDecimal payMoneyBalance = new BigDecimal(t.getPayMoney());

		Double accountMoney = accountBalance.add(payMoneyBalance).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		// 增加入金记录
		TbCashAccount tbCashAccount = new TbCashAccount();
		tbCashAccount.setCashOut(0.0);
		tbCashAccount.setCashIn(t.getPayMoney());
		tbCashAccount.setBalance(accountMoney);
		tbCashAccount.setOperationDt(System.currentTimeMillis());
		tbCashAccount.setShopId(t.getShopId());
		tbCashAccount.setAccountId(account.getId());
		tbCashAccount.setPlatform(0);
		tbCashAccount.setOrderSerialNo(t.getOrderSerialNo());
		tbCashAccountMapper.insertSelective(tbCashAccount);

		account.setBalance(accountMoney);
		tbAccountMapper.updateByPrimaryKey(account);
		logger.debug("weixinPay accountMoney>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + accountMoney);
		payMyOrderGroup(t.getId(), account.getId());
		MyOrderForm myOrderForm = new MyOrderForm();
		myOrderForm.setOrderGroupId(t.getId());
		myOrderForm.setVersion(t.getVersion());
		configReceivePayment(myOrderForm, t.getBuyerId(), "买家已经付款");
	}

	@Override
	public int selectUnCompleteOrder(Criteria example) throws RuntimeException {
		try {
			return tbWapOrderGroupMapper.selectUnCompleteOrder(example);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}