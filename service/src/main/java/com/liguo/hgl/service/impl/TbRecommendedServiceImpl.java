package com.liguo.hgl.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.exceptions.TransactionException;
import com.liguo.hgl.proxydao.dao.TbAccountMapper;
import com.liguo.hgl.proxydao.dao.TbCashAccountMapper;
import com.liguo.hgl.proxydao.dao.TbIntegralDetailedMapper;
import com.liguo.hgl.proxydao.dao.TbIntegralMapper;
import com.liguo.hgl.proxydao.dao.TbRecommendedMapper;
import com.liguo.hgl.proxydao.dao.TbRecommendedTypeMapper;
import com.liguo.hgl.proxydao.dao.TbShopInfoMapper;
import com.liguo.hgl.proxydao.dao.TbUserInfoMapper;
import com.liguo.hgl.proxydao.dao.TbWapOrderGroupMapper;
import com.liguo.hgl.proxydao.dao.TbWebUserMapper;
import com.liguo.hgl.proxydao.dto.WapOrderGroup;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbAccount;
import com.liguo.hgl.proxydao.model.TbCashAccount;
import com.liguo.hgl.proxydao.model.TbIntegral;
import com.liguo.hgl.proxydao.model.TbIntegralDetailed;
import com.liguo.hgl.proxydao.model.TbIntegralRules;
import com.liguo.hgl.proxydao.model.TbRecommended;
import com.liguo.hgl.proxydao.model.TbRecommendedRules;
import com.liguo.hgl.proxydao.model.TbShopInfo;
import com.liguo.hgl.proxydao.model.TbUserInfo;
import com.liguo.hgl.proxydao.model.TbWebUser;
import com.liguo.hgl.service.TbIntegralRulesService;
import com.liguo.hgl.service.TbRecommendedRulesService;
import com.liguo.hgl.service.TbRecommendedService;

@Service
@Scope(value="prototype")
public class TbRecommendedServiceImpl implements TbRecommendedService {
    @Autowired
    private TbRecommendedMapper tbRecommendedMapper;
    
    @Autowired
    private TbWapOrderGroupMapper tbWapOrderGroupMapper;
    
    @Autowired
    private TbRecommendedTypeMapper tbRecommendedTypeMapper;
    
    @Autowired
    private TbAccountMapper tbAccountMapper;
    
    @Autowired
    protected TbShopInfoMapper tbShopInfoMapper;
    
    @Autowired
    protected TbUserInfoMapper tbUserInfoMapper;
    
    @Autowired
	private TbWebUserMapper tbWebUserMapper;
    
    @Autowired
    private TbCashAccountMapper tbCashAccountMapper;
    
    @Autowired
    private TbIntegralMapper tbIntegralMapper;
    
    @Autowired
    private TbIntegralDetailedMapper tbIntegralDetailedMapper;
    
    @Autowired
    private TbRecommendedRulesService tbRecommendedRulesService;

    private static final Logger logger = LoggerFactory.getLogger(TbRecommendedServiceImpl.class);

    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbRecommendedMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TbRecommended selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbRecommendedMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TbRecommended> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbRecommendedMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public int deleteByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbRecommendedMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKeySelective(TbRecommended record) throws RuntimeException {
        try {
            return this.tbRecommendedMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKey(TbRecommended record) throws RuntimeException {
        try {
            return this.tbRecommendedMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int insertSelective(TbRecommended record) throws RuntimeException {
        try {
            return this.tbRecommendedMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * 执行返利任务
     * */
    public int rebate() throws TransactionException{
    	//1，查询推荐表
		Criteria example = new Criteria();
		example.put("firstOrderId", 0);
		example.put("money", 0);
		List<TbRecommended> rList= tbRecommendedMapper.selectByObject(example);
		//所有订单ID 用于流程结束后修改标记用
		List<Integer> orderIdList = new ArrayList<Integer>(); 
		//查询已经完成且没有被定时任务执行过的订单信息。
		Criteria oExample = new Criteria();
		oExample.put("rebate", 0);
		oExample.put("orderStatus", 620);
		List<WapOrderGroup> oList = tbWapOrderGroupMapper.selectWapOrderGroupByObj(oExample);
		//资金流水记录，用于批量插入
		List<TbCashAccount> tbCashAccountList = new LinkedList<TbCashAccount>(); 
		//师傅的订单
		List<WapOrderGroup> mOrderList = new ArrayList<WapOrderGroup>();
		//2.推荐首单返利
			for(TbRecommended r : rList){
				Integer buyerId = r.getIsRecommended();
				//所有的订单
				List<WapOrderGroup> rOrderList = new ArrayList<WapOrderGroup>(); 
				//执行客户首单返利
				Iterator<WapOrderGroup> it = oList.iterator();
				
				while(it.hasNext()){
					WapOrderGroup t = it.next();
					if(buyerId.toString().equals(t.getBuyerId().toString())){
						rOrderList.add(t);
					}
				}
				if(rOrderList.size() > 0){
					WapOrderGroup wapOrderGroup = rOrderList.get(0);
					//推荐人账户
					TbAccount rebateAccount = tbAccountMapper.selectAccountByUserId(r.getRecommended());
					//如果是经销商
					if(("1").equals(r.getTypeRecommend().toString())){
						//查询经销商配置表
						TbRecommendedRules tbRecommendedRules = tbRecommendedRulesService.selectByPrimaryKey(r.getRecommendedContactTypeId());
						TbShopInfo shopInfo = tbShopInfoMapper.selectByPrimaryKey(wapOrderGroup.getShopId());
						TbAccount shopAccount = tbAccountMapper.selectByPrimaryKey(shopInfo.getAccountId());
						//经销商如果给的是钱
						if(("1").equals(tbRecommendedRules.getRewardType().toString())){
							//如果是固定值
							if(("1").equals(tbRecommendedRules.getRewardWay().toString())){
								double money = 0.0;
								if( tbRecommendedRules.getReward() > wapOrderGroup.getPayMoney()){
									money = wapOrderGroup.getPayMoney();
								}else{
									money = tbRecommendedRules.getReward();
								}
								BigDecimal rebateMoney = new BigDecimal(money);
							//返利金额
								if(money > 0){
									logger.debug("我是按固定值送钱.....");
									updateAccountBalanec(shopAccount,rebateAccount,rebateMoney);
									//推荐人资金明细表
									tbCashAccountList.add(newTbCashAccount(0.0,rebateMoney.doubleValue(),rebateAccount.getBalance(),wapOrderGroup.getShopId(),rebateAccount.getId(),wapOrderGroup.getOrderSerialNo()));
									//平台账资金明细记录.
									tbCashAccountList.add(newTbCashAccount(rebateMoney.doubleValue(),0.0,shopAccount.getBalance(),wapOrderGroup.getShopId(),shopAccount.getId(),wapOrderGroup.getOrderSerialNo()));
								}
								r.setMoney(money);
								r.setEarnings(rebateMoney.doubleValue());
								r.setFirstOrderId(wapOrderGroup.getId());
							}else{
							//如果是百分比
								double money = 0.0;
								double rMoney = wapOrderGroup.getPayMoney()*tbRecommendedRules.getReward()/100;
								if(rMoney > wapOrderGroup.getPayMoney()){
									money = wapOrderGroup.getPayMoney();
								}else{
									money = rMoney;
								}
								//返利金额
								BigDecimal rebateMoney = new BigDecimal(money);
								if(money > 0){
									logger.debug("我是按百分比送钱.....");
									updateAccountBalanec(shopAccount,rebateAccount,rebateMoney);
									//推荐人资金明细表
									tbCashAccountList.add(newTbCashAccount(0.0,rebateMoney.doubleValue(),rebateAccount.getBalance(),wapOrderGroup.getShopId(),rebateAccount.getId(),wapOrderGroup.getOrderSerialNo()));
									//平台账资金明细记录
									tbCashAccountList.add(newTbCashAccount(rebateMoney.doubleValue(),0.0,shopAccount.getBalance(),wapOrderGroup.getShopId(),shopAccount.getId(),wapOrderGroup.getOrderSerialNo()));
								}
								r.setMoney(money);
								r.setEarnings(rebateMoney.doubleValue());
								r.setFirstOrderId(wapOrderGroup.getId());
							}
						}/*else{
						//如果给的是积分
							//如果是固定值
							if(("1").equals(tbRecommendedRules.getRewardWay().toString())){
								Integer integra = 0;
								Integer rIntegra = (int)Math.round(wapOrderGroup.getPayMoney());
								//如果送的积分大于订单支付金额则取订单值
								if((int)Math.round(tbRecommendedRules.getReward()) > rIntegra){
									integra = rIntegra;
								}else{
									integra = (int)Math.round(tbRecommendedRules.getReward());
								}
								if(integra > 0){
									logger.debug("我是按固定值送积分的.....");
									updateIntegral(r.getRecommended(),integra, wapOrderGroup.getId(),wapOrderGroup.getOrderSerialNo());
								}
								r.setMoney(Double.valueOf(integra.toString()));
								r.setEarnings(Double.valueOf(integra.toString()));
								r.setFirstOrderId(wapOrderGroup.getId());
							}else{
							//如果是百分比
								Integer integra = 0;
								Integer rIntegra = (int)Math.round(wapOrderGroup.getPayMoney());
								//如果送的积分大于订单支付金额则取订单值
								if((int)Math.round(wapOrderGroup.getPayMoney()*tbRecommendedRules.getReward()/100) > rIntegra){
									integra = rIntegra;
								}else{
									integra = (int)Math.round(wapOrderGroup.getPayMoney()*tbRecommendedRules.getReward()/100);
								}
								if(integra > 0){
									logger.debug("我是按百分比送积分的.....");
									updateIntegral(r.getRecommended(),integra, wapOrderGroup.getId(),wapOrderGroup.getOrderSerialNo());
								}
								r.setMoney(Double.valueOf(integra.toString()));
								r.setEarnings(Double.valueOf(integra.toString()));
								r.setFirstOrderId(wapOrderGroup.getId());
							}
						}*/
						tbRecommendedMapper.updateByPrimaryKeySelective(r);
					}else{
					//如果是平台
						
					}
				}
			}
			//对师傅反利
			
			Iterator<WapOrderGroup> it = oList.iterator();
			while(it.hasNext()){
				WapOrderGroup t = it.next();
				orderIdList.add(t.getId());
				//师傅的材料订单和师傅服务订单所推荐的材料订单
				if(HglContants.USER_TYPE_MASTER.toString().equals(t.getTypeId().toString()) || t.getRepairmanId() != null){
					mOrderList.add(t);
				}
			}
			//得到所有师傅订单，得到shopId查询出店铺返利比例
			if(mOrderList.size() > 0){
				for(WapOrderGroup m: mOrderList){
					logger.debug(m.getId()+"....."+m.getRepairmanId());
					logger.debug("师傅订单.....");
					if(m.getRepairmanId() == null){
						//查询店铺账户金额
						TbShopInfo shopInfo = tbShopInfoMapper.selectByPrimaryKey(m.getShopId());
						//扣店铺账户金额，生成资金明细
						TbAccount shopAccount = tbAccountMapper.selectByPrimaryKey(shopInfo.getAccountId());
						//经予:扣除的是店铺账户金额
						double money = m.getPayMoney()*shopInfo.getRebate()/100;
						BigDecimal aBalance = new BigDecimal(money);
						//得到师傅账户
						TbWebUser tbWebUser = tbWebUserMapper.selectByPrimaryKey(m.getBuyerId());
						TbUserInfo tbUserInfo = tbUserInfoMapper.selectByPrimaryKey(tbWebUser.getUserinfoId());
						TbAccount masterAccount = tbAccountMapper.selectByPrimaryKey(tbUserInfo.getAccountId());
						updateAccountBalanec(shopAccount,masterAccount,aBalance);
						//推荐人资金明细表
						tbCashAccountList.add(newTbCashAccount(0.0,aBalance.doubleValue(),masterAccount.getBalance(),m.getShopId(),masterAccount.getId(),m.getOrderSerialNo()));
						//平台账资金明细记录
						tbCashAccountList.add(newTbCashAccount(aBalance.doubleValue(),0.0,shopAccount.getBalance(),m.getShopId(),shopAccount.getId(),m.getOrderSerialNo()));
					}else{
						//师傅服务推荐单返利
						logger.debug("师傅服务推荐单.....");
						//查询店铺账户金额
						TbShopInfo shopInfo = tbShopInfoMapper.selectByPrimaryKey(m.getShopId());
						//扣店铺账户金额，生成资金明细
						TbAccount shopAccount = tbAccountMapper.selectByPrimaryKey(shopInfo.getAccountId());
						//经予:扣除的是店铺账户金额
						double money = m.getPayMoney()*shopInfo.getRebate()/100;
						BigDecimal aBalance = new BigDecimal(money);
						
						//得到师傅账户
						TbWebUser tbWebUser = tbWebUserMapper.selectByPrimaryKey(m.getRepairmanId());
						TbUserInfo tbUserInfo = tbUserInfoMapper.selectByPrimaryKey(tbWebUser.getUserinfoId());
						TbAccount masterAccount = tbAccountMapper.selectByPrimaryKey(tbUserInfo.getAccountId());
						updateAccountBalanec(shopAccount,masterAccount,aBalance);
						//推荐人资金明细表
						tbCashAccountList.add(newTbCashAccount(0.0,aBalance.doubleValue(),masterAccount.getBalance(),m.getShopId(),masterAccount.getId(),m.getOrderSerialNo()));
						//平台账资金明细记录
						tbCashAccountList.add(newTbCashAccount(aBalance.doubleValue(),0.0,shopAccount.getBalance(),m.getShopId(),shopAccount.getId(),m.getOrderSerialNo()));
					}
				}
			}
			//更新订单定时任务执行标记
			if(orderIdList.size() > 0){
				tbWapOrderGroupMapper.updateOrderRebateById(orderIdList);
			}
			//批量插入资金明细记录
			if(tbCashAccountList.size() > 0){
				tbCashAccountMapper.insertBatchCashAccount(tbCashAccountList);
			}
			return 0;
    }
    /**
     * 资金明细插入方法
     * */
    private TbCashAccount newTbCashAccount(double cashOut,double cashIn,double balance,Integer shopId,Integer accountId,String orderSerialNo){
    	TbCashAccount cCashAccount =  new TbCashAccount();
    	cCashAccount.setCashOut(cashOut);
    	cCashAccount.setCashIn(cashIn);
		cCashAccount.setBalance(balance);
		cCashAccount.setOperationDt(System.currentTimeMillis());
		cCashAccount.setShopId(shopId);
		cCashAccount.setAccountId(accountId);
		cCashAccount.setPlatform(2);
		cCashAccount.setOrderSerialNo(orderSerialNo);
    	return cCashAccount;
    }
    
    /**
     * 将A账户的转给B账户，转帐金额money
     * */
    private void updateAccountBalanec(TbAccount aAccount,TbAccount bAccount,BigDecimal money) throws TransactionException {
    	BigDecimal aBalance = new BigDecimal(aAccount.getBalance());
    	BigDecimal bBalance = new BigDecimal(bAccount.getBalance());
    	//扣除A账户的钱
    	double b= aBalance.subtract(money).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    	/*if(b < 0){
    		logger.debug("执行返利时，扣除金额不足");
        	throw new TransactionException(MessageEnum.Z2003);
    	}*/
    	aAccount.setBalance(b);
    	tbAccountMapper.updateByPrimaryKey(aAccount);
    	//增加B账户的钱
    	bAccount.setBalance(bBalance.add(money).doubleValue());
    	tbAccountMapper.updateByPrimaryKey(bAccount);
    }
    
    /**
     * 送积分
     * */
    private void updateIntegral(Integer userId,Integer integra,Integer orderId,String orderNo){
    	Criteria example = new Criteria();
    	example.put("userId", userId);
    	List<TbIntegral> tList = tbIntegralMapper.selectByObject(example);
    	if(tList.size() > 0){
    		TbIntegral tbIntegral = tList.get(0);
    		tbIntegral.setIntegral(tbIntegral.getIntegral()+integra);
    		/*tbIntegralDetailedMapper.selectByPrimaryKey(tbIntegral.getId());*/
    		tbIntegralMapper.updateByPrimaryKey(tbIntegral);
    		
    		TbIntegralDetailed tbIntegralDetailed = new TbIntegralDetailed();
    		tbIntegralDetailed.setIntegralId(tbIntegral.getId());
    		tbIntegralDetailed.setOrderId(orderId);
    		tbIntegralDetailed.setIntegral(integra);
    		tbIntegralDetailed.setOrderSerialNo(orderNo);
    		tbIntegralDetailed.setOpearDt(System.currentTimeMillis());
    		tbIntegralDetailed.setType(2);
    		tbIntegralDetailed.setVersion(0);
    		tbIntegralDetailedMapper.insertSelective(tbIntegralDetailed);
    	}
    }
}