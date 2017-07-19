package com.linkon.hgl.web.task;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.liguo.hgl.exceptions.TransactionException;
import com.liguo.hgl.service.TbCustomerInterestsService;
import com.liguo.hgl.service.TbGoodsProfitService;
import com.liguo.hgl.service.TbRecommendedService;
@Component
public class TimeExpressionResetTask {
	private final static Logger logger = Logger.getLogger(TimeExpressionResetTask.class);

	@Autowired
	protected TbRecommendedService tbRecommendedService;
	
	@Autowired
	private TbCustomerInterestsService tbCustomerInterestsService;
	
	@Autowired
	private TbGoodsProfitService tbGoodsProfitService;
	

	//库存预警
	@Scheduled(cron = "0 0 23 * * ?")
	public void goodsWarning(){
		
		logger.info("晚上23开始执行返利任务...............................……");
		//首单返利和师傅返利
		
		//定义map,初始化受影响的行数
		Map<String,Integer> rowCountMap = new HashMap<String,Integer>();
		rowCountMap.put("rowCount",0);
		
		//库存预警定时任务
		int rowCount = tbCustomerInterestsService.callCustomerInterests(rowCountMap);
		logger.info("库存预警定时任务受影响的行数: " + rowCount);
		
		//客户盈利定时任务
		rowCount = tbGoodsProfitService.callGoodsProfit(rowCountMap);
		logger.info("客户盈利定时任务受影响的行数: " + rowCount);
		try{
			tbRecommendedService.rebate();
		}catch(TransactionException e){
			logger.info("返利任务执行失败.............................");
			e.printStackTrace();
		}
	}
}


	

