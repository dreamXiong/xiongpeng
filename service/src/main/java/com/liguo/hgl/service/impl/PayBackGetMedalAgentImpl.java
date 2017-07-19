package com.liguo.hgl.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.exceptions.TransactionException;
import com.liguo.hgl.service.PayClallBackService;

/**
 * 
 * @author Administrator
 *
 */
@Service("payBackGetMedalAgentImpl")
public class PayBackGetMedalAgentImpl implements PayClallBackService {
	
	private static final Logger logger = LoggerFactory.getLogger(PayBackGetMedalAgentImpl.class);
	
	@Autowired
	@Qualifier("payBackGetSettlementImpl")
	private PayClallBackService payClallBackService;
	
	@Override
	public String doSomething(String... args) throws TransactionException {
		logger.debug("---------------" + args.toString());
		return payClallBackService.doSomething(args[0],args[1],HglContants.SHOP_MEDAL_AGENT);
	}
}
