package com.liguo.hgl.service.impl;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.dao.TbWapOrderServiceDetailMapper;
import com.liguo.hgl.proxydao.dto.WapOrderServiceDetailDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbWapOrderServiceDetail;
import com.liguo.hgl.service.TbWapOrderServiceDetailService;
import com.liguo.hgl.service.TbWapShoppingCartService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(value="prototype")
public class TbWapOrderServiceDetailServiceImpl implements TbWapOrderServiceDetailService {
    @Autowired
    private TbWapOrderServiceDetailMapper tbWapOrderServiceDetailMapper;
    @Autowired
    protected TbWapShoppingCartService tbWapShoppingCartService;

    private static final Logger logger = LoggerFactory.getLogger(TbWapOrderServiceDetailServiceImpl.class);

    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbWapOrderServiceDetailMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TbWapOrderServiceDetail selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbWapOrderServiceDetailMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TbWapOrderServiceDetail> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbWapOrderServiceDetailMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int deleteByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbWapOrderServiceDetailMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKeySelective(TbWapOrderServiceDetail record) throws RuntimeException {
        try {
            return this.tbWapOrderServiceDetailMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKey(TbWapOrderServiceDetail record) throws RuntimeException {
        try {
            return this.tbWapOrderServiceDetailMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int insertSelective(TbWapOrderServiceDetail record) throws RuntimeException {
        try {
            return this.tbWapOrderServiceDetailMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 添加订单明细
     */
	@Override
	public boolean confirmPushCart(String[] inventoryListNum,Integer orderServiceId,Integer userId) throws RuntimeException {
		 try {
			 if(inventoryListNum != null && inventoryListNum.length>0){
				 Criteria example = new Criteria();
				 example.put("orderServiceId", orderServiceId);
				 Map<Integer,Integer> tempMap = new HashMap<Integer,Integer>();
				 for(String strs : inventoryListNum){ 
					 String[] goods = strs.split("=");
					 tempMap.put(Integer.parseInt(goods[0]), Integer.parseInt(goods[1]));
				 }
				 List<Integer> inventoryList = new ArrayList<Integer>(tempMap.keySet());
				 example.put("idsList", inventoryList);
				 List<TbWapOrderServiceDetail> orderServiceDetailList = tbWapOrderServiceDetailMapper.selectByObject(example);
				 if(orderServiceDetailList != null && orderServiceDetailList.size()>0){
					 for(TbWapOrderServiceDetail service : orderServiceDetailList){
						Integer persistentBuyNum = tempMap.get(service.getInventoryId());
						tempMap.put(service.getInventoryId(),persistentBuyNum+service.getBuyNum());
					 }
				 }
				int delCount = tbWapOrderServiceDetailMapper.deleteByObject(example); 
				logger.info("wap-删除服务订单明细条数: " + delCount);
				 
				//批量插入
				List<TbWapOrderServiceDetail> orderServiceDetailInsertList = new ArrayList<TbWapOrderServiceDetail>();
				for(Integer key : tempMap.keySet()) {   
					Integer buyNum = tempMap.get(key);
					TbWapOrderServiceDetail serviceDetail = new TbWapOrderServiceDetail();  
					serviceDetail.setOrderServiceId(orderServiceId);
					serviceDetail.setInventoryId(key);
					serviceDetail.setBuyNum(buyNum);
					serviceDetail.setStatus(HglContants.ORDERSERVICEDETAIL_820);
					serviceDetail.setCreateBy(userId);
					serviceDetail.setCreateDt(System.currentTimeMillis());
					orderServiceDetailInsertList.add(serviceDetail);
				}
				//批量插入
				int insCount = tbWapOrderServiceDetailMapper.batchInsertSelective(orderServiceDetailInsertList);
				logger.info("wap-服务订单明细插入条数: " + insCount);
				if(insCount > 0){
					return true;
				}
			 }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
		return false;
	}

	/**
	 * 添加到自己的购物车
	 */
	@Override
	public boolean confirmAddMyCart(String[] orderListNum,Integer orderServiceId,Integer repairmanId,Integer userId) throws RuntimeException {
		//调用添加到自己的购物车
		boolean isSuccess = tbWapShoppingCartService.addShoppingCart(orderListNum, null, null,repairmanId,userId);
		if(isSuccess){
			 //成功后更新服务订单详情的状态
			 Criteria example = new Criteria();
			 example.put("orderServiceId", orderServiceId);
			 String[] goods = orderListNum[0].split("=");
			 example.put("inventoryId", goods[0]);
			 example.put("status", HglContants.ORDERSERVICEDETAIL_822);
			 int updCount = tbWapOrderServiceDetailMapper.updateByServiceKey(example);
			 logger.info("服务订单详情更改状态记录数: " + updCount);
			 return updCount>0 ? true : false;
		}
		return false;
	}

	@Override
	public List<WapOrderServiceDetailDto> selectInventByObject(Criteria example)
			throws RuntimeException {
		try{
			return tbWapOrderServiceDetailMapper.selectInventByObject(example);
		}catch(Exception e){
			
			throw new RuntimeException(e);
		}
	}
}