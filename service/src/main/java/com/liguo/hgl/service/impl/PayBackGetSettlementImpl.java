package com.liguo.hgl.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.exceptions.TransactionException;
import com.liguo.hgl.proxydao.dao.TbAccountMapper;
import com.liguo.hgl.proxydao.dao.TbCashAccountMapper;
import com.liguo.hgl.proxydao.dao.TbPayWaterMapper;
import com.liguo.hgl.proxydao.dao.TbServiceTypeMapper;
import com.liguo.hgl.proxydao.dao.TbShopInfoMapper;
import com.liguo.hgl.proxydao.dao.TbSystemConfigMapper;
import com.liguo.hgl.proxydao.dao.TbWebUserMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbAccount;
import com.liguo.hgl.proxydao.model.TbCashAccount;
import com.liguo.hgl.proxydao.model.TbServiceType;
import com.liguo.hgl.proxydao.model.TbShopInfo;
import com.liguo.hgl.proxydao.model.TbWebUser;
import com.liguo.hgl.service.PayClallBackService;
import com.liguo.hgl.service.TbShopInfoService;
import com.liguo.hgl.util.SmsSend;


/**
 * * 
*Company:  hgl
* Description:微信获取店铺结算功能支付完成后回调业务层
* @author:zk
* @date 2016-7-29 下午3:35:44
 */
@Service("payBackGetSettlementImpl")
public class PayBackGetSettlementImpl implements PayClallBackService {
	
	private static final Logger logger = LoggerFactory.getLogger(TbAccountServiceImpl.class);
	 
	@Autowired
	protected TbShopInfoService tbShopInfoService;

  	@Autowired
    private TbShopInfoMapper tbShopInfoMapper;
    
    @Autowired
    TbWebUserMapper tbWebUserMapper;
    
	@Autowired
	private TbAccountMapper tbAccountMapper;
	
	@Autowired
	private TbSystemConfigMapper tbSystemConfigMapper;
	
	@Autowired
	private TbCashAccountMapper tbCashAccountMapper;
    
    @Autowired
    private TbServiceTypeMapper tbServiceTypeMapper;
    
    @Autowired
    private TbPayWaterMapper tbPayWaterMapper;
	 
	@Override
	public String doSomething(String... args) throws TransactionException {
	//插入出入金记录..
		String orderSerialNo = args[0];
		Integer accountId = Integer.parseInt(args[1]);
		logger.debug("执行微信扫码支付购买平台结算权限》》》》》》》》》》》orderNo》》》》"+args[0]+"》》》》》》》》》》》》》》》》》accountId"+args[1]);
		TbAccount tbAccount = tbAccountMapper.selectByPrimaryKey(accountId);
		//根据账户得到shopInfo信息
		Criteria c = new Criteria();
		c.put("accountId", tbAccount.getId());
		TbShopInfo tbShopInfo = tbShopInfoMapper.selectByObject(c).get(0);
		//得到服务购买所需金额 
		Criteria example = new Criteria();
		example.put("orderGroupNo", orderSerialNo);
		Double payMoney = tbPayWaterMapper.selectByObject(example).get(0).getPayAmt().doubleValue();
		
		//插入资金明细记录表
		TbCashAccount inCashAccount = new TbCashAccount();
		inCashAccount.setCashOut(0.0);
		inCashAccount.setCashIn(payMoney);
		inCashAccount.setBalance(tbAccount.getBalance()+payMoney);
		inCashAccount.setOperationDt(System.currentTimeMillis());
		inCashAccount.setShopId(tbShopInfo.getId());
		inCashAccount.setAccountId(accountId);
		inCashAccount.setPlatform(1);
		inCashAccount.setOrderSerialNo(orderSerialNo);
		tbCashAccountMapper.insertSelective(inCashAccount);
		
		//插入出金记录
		TbCashAccount outCashAccount = new TbCashAccount();
		outCashAccount.setCashOut(payMoney);
		outCashAccount.setCashIn(0.0);
		outCashAccount.setBalance(tbAccount.getBalance()-payMoney);
		outCashAccount.setOperationDt(System.currentTimeMillis());
		outCashAccount.setShopId(tbShopInfo.getId());
		outCashAccount.setAccountId(accountId);
		outCashAccount.setPlatform(1);
		outCashAccount.setOrderSerialNo(orderSerialNo);
		tbCashAccountMapper.insertSelective(outCashAccount);
		
		/**给经销商添加所有技能start**/
        Criteria criteria = new Criteria();
        criteria.put("childAll", 0);// 0 任意值
        List<TbServiceType> tbServiceTypeList =tbServiceTypeMapper.selectByObject(criteria);
        StringBuilder sb = new StringBuilder();
        for(int i =0;i<tbServiceTypeList.size();i++){
            Integer id =tbServiceTypeList.get(i).getId();
            sb.append(id);
            if(i!=tbServiceTypeList.size()-1){
                sb.append(",");
            }
        }
        /**给经销商添加所有技能end**/
		
		//更改店铺结算状态
		tbShopInfo.setBalance(1);
		tbShopInfo.setSettlement(1);
		//设置金牌代理商开通权限标示
		if(args.length>2 && HglContants.SHOP_MEDAL_AGENT.equals(args[2])){
			tbShopInfo.setMedalAgentFlag(2);
		}
		//添加所有技能
		tbShopInfo.setServiceType(sb.toString());
		tbShopInfoMapper.updateByPrimaryKeySelective(tbShopInfo);
		
		//更改用记角色状态
		Criteria webUserExample = new Criteria();
		webUserExample.put("shopId",tbShopInfo.getId());
		TbWebUser tbWebUser = tbWebUserMapper.selectByObject(webUserExample).get(0);
		tbWebUser.setRoleId(HglContants.GETED_ROLE_ID);
		tbWebUserMapper.updateByPrimaryKeySelective(tbWebUser);
		
    	//调用给推荐人返利方法
		tbShopInfoService.rebatereCommended(tbShopInfo);
		
		//增加店铺积分
		tbShopInfoService.addShopIntegral(tbShopInfo.getId());
		
		//发送短信
		logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>.发送短信");
		SmsSend.sendPayed(tbShopInfo.getContractPhone(),tbShopInfo.getContract());
		return "myWallet/index";
    }
}
