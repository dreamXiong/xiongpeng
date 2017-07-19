package com.liguo.hgl.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liguo.hgl.proxydao.dao.TbProductInventoryMapper;
import com.liguo.hgl.proxydao.dto.TbProductInventoryDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbProductInventory;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.TbProductInventoryService;

@Service
@Scope(value = "prototype")
public class TbProductInventoryServiceImpl implements TbProductInventoryService {
	@Autowired
	private TbProductInventoryMapper tbProductInventoryMapper;

	private static final Logger logger = LoggerFactory
			.getLogger(TbProductInventoryServiceImpl.class);

	public int countByObject(Criteria example) throws RuntimeException {
		try {
			int count = this.tbProductInventoryMapper.countByObject(example);
			logger.debug("count: {}", count);
			return count;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public TbProductInventory selectByPrimaryKey(Integer id)
			throws RuntimeException {
		try {
			return this.tbProductInventoryMapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public List<TbProductInventory> selectByObject(Criteria example)
			throws RuntimeException {
		try {
			return this.tbProductInventoryMapper.selectByObject(example);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public int deleteByPrimaryKey(Integer id) throws RuntimeException {
		try {
			return this.tbProductInventoryMapper.deleteByPrimaryKey(id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public int updateByPrimaryKeySelective(TbProductInventory record)
			throws RuntimeException {
		try {
			return this.tbProductInventoryMapper
					.updateByPrimaryKeySelective(record);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public int updateByPrimaryKey(TbProductInventory record)
			throws RuntimeException {
		try {
			return this.tbProductInventoryMapper.updateByPrimaryKey(record);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public int insertSelective(TbProductInventory record)
			throws RuntimeException {
		try {
			return this.tbProductInventoryMapper.insertSelective(record);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public TbProductInventoryDto selectById(Integer id) throws RuntimeException {
		try {
			return this.tbProductInventoryMapper.selectById(id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<TbProductInventoryDto> selectByCriteria(Criteria criteria,
			PageDto page) throws RuntimeException {
		try {
			if (criteria.getOrderByClause() == null) {
				criteria.setOrderByClause("create_time");
				criteria.setOrderByClauseDesc("desc");
			}
			return this.tbProductInventoryMapper.selectByCriteria(criteria,
					page);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int markDeleteByPrimaryKey(int id, String lastupdateby)
			throws RuntimeException {
		try {
			Criteria criteria = new Criteria();
			criteria.put("id", id);
			criteria.put("lastUpdateBy", lastupdateby);
			return this.tbProductInventoryMapper
					.markDeleteByPrimaryKey(criteria);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<TbProductInventoryDto> selectByCriteria(Criteria criteria)
			throws RuntimeException {
		try {
			List<TbProductInventoryDto> list = this.tbProductInventoryMapper.selectByCriteria(criteria);
			if(criteria.getCondition().get("discount") != null){
				double discount = (double)criteria.getCondition().get("discount");
				for(TbProductInventoryDto dto : list){
					BigDecimal discountPrice = calculateDiscount(dto.getOutstockPrice(),discount);
					dto.setDiscountPrice(discountPrice.setScale(2,BigDecimal.ROUND_HALF_UP));
				}
			}
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int batchUpdatePrice(String inventoryIds, String asPrice,
			String priceMethod, String price, String user)
			throws RuntimeException {
		Criteria criteria = new Criteria();
		criteria.put("asPrice", asPrice);
		criteria.put("priceMethod", priceMethod);
		criteria.put("price", price);
		criteria.put("user", user);
		criteria.put("inventoryIds", inventoryIds.split(","));
		return this.tbProductInventoryMapper.batchUpdatePrice(criteria);
	}

	/**
	 * 处理结算的业务计算方法
	 */
	@Override
	public String calculateMoney(String[] orderListNum,double discount) throws RuntimeException {
		double calculateMoney = 0;
		try {
			Map<Integer,String> tempMap = splitMap(orderListNum);
			if(tempMap.size()>0){
				List<Integer> idsList = new ArrayList<Integer>(tempMap.keySet());
				List<TbProductInventory> list = tbProductInventoryMapper.selectByPrimaryKeys(idsList);
				if(list != null && list.size()>0){
					for(int i=0;i<list.size();i++){
						TbProductInventory productInventory = list.get(i);
						if(productInventory != null){
							String id = String.valueOf(productInventory.getId()); //把id转换为字符串
							String orderNumStr = tempMap.get(id);
							if(StringUtils.isNotBlank(orderNumStr)){
								BigDecimal discountPrice = calculateDiscount(productInventory.getOutstockPrice(),discount); //获取折后单价
								BigDecimal singlePrice = discountPrice.multiply(new BigDecimal(orderNumStr)); //订单数量乘以折后单价
								BigDecimal totalPrice = new BigDecimal(calculateMoney);
								calculateMoney = totalPrice.add(singlePrice).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  //总价加上单价
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return String.valueOf(calculateMoney);
	}
	
	/**
	 * 计算折扣
	 * @param outstockPrice
	 * @param discount
	 * @return
	 */
	public BigDecimal calculateDiscount(BigDecimal outstockPrice,double discount){
		return outstockPrice.multiply(new BigDecimal(discount));  //计算折后单价
	}
	
	/**
	 * 分割成map
	 * @param orderListNum
	 * @return
	 */
	public Map<Integer,String> splitMap(String orderListNum[]){
		Map<Integer,String> tempMap = new HashMap<Integer,String>();
		for(String strs : orderListNum){
			String[] goods = strs.split("=");
			String value = "";
			if(goods.length == 2){
				value = goods[1];
			}
			tempMap.put(Integer.parseInt(goods[0]), value);
		}
		return tempMap;
	}

}