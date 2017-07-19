package com.liguo.hgl.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liguo.hgl.common.MessageEnum;
import com.liguo.hgl.exceptions.TransactionException;
import com.liguo.hgl.proxydao.dao.TbAccountMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbAccount;
import com.liguo.hgl.proxydao.model.TbWapOrderGroup;
import com.liguo.hgl.service.PayClallBackService;
import com.liguo.hgl.service.TbAccountService;
import com.liguo.hgl.service.TbWapOrderGroupService;


/**
 * * 
*Company:  hgl
* Description:微信支付完成后回调业务层
* @author:zk
* @date 2016-7-29 下午3:35:44
 */
@Service("payBackWinxinPayImpl")
public class PayBackWinxinPayImpl implements PayClallBackService {
	
	private static final Logger logger = LoggerFactory.getLogger(TbAccountServiceImpl.class);
	 
	@Autowired
	protected TbWapOrderGroupService tbWapOrderGroupService;
	 
	@Autowired
	private TbAccountMapper tbAccountMapper;
	 
	@Override
	public String doSomething(String... args) throws TransactionException {
		logger.debug(args[0]+" .......支付回调操作......... message");
    	Criteria example = new Criteria();
    	example.put("orderSerialNo", args[0]);
    	List<TbWapOrderGroup> oList = tbWapOrderGroupService.selectByObject(example);
    	//更新入金记录
    	if(oList.size() == 0){
    		logger.debug("支付回调时，订单号不存在");
        	throw new TransactionException(MessageEnum.Z2005);
    	}
    	TbWapOrderGroup t = oList.get(0);
    	TbAccount account = tbAccountMapper.selectAccountByUserId(t.getBuyerId());
    	tbWapOrderGroupService.weixinPay(t,account);
    	return "wapOrderGroup/selectOrderGroup";
    }
}
