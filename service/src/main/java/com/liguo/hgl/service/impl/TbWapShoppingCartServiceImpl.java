package com.liguo.hgl.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liguo.hgl.proxydao.dao.TbDeliveryTermsMapper;
import com.liguo.hgl.proxydao.dao.TbWapShoppingCartMapper;
import com.liguo.hgl.proxydao.dto.CalulateFreightDto;
import com.liguo.hgl.proxydao.dto.WapProductInventoryDto;
import com.liguo.hgl.proxydao.dto.WapShoppingCartDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbDeliveryTerms;
import com.liguo.hgl.proxydao.model.TbWapShoppingCart;
import com.liguo.hgl.proxydao.util.BaiduMapUtil;
import com.liguo.hgl.proxydao.util.GaoDeMapUtil;
import com.liguo.hgl.service.TbWapShoppingCartService;

@Service
@Scope(value="prototype")
public class TbWapShoppingCartServiceImpl implements TbWapShoppingCartService {
	
    @Autowired
    private TbWapShoppingCartMapper tbWapShoppingCartMapper;
    @Autowired
    private TbDeliveryTermsMapper tbDeliveryTermsMapper;

    private static final Logger logger = LoggerFactory.getLogger(TbWapShoppingCartServiceImpl.class);

    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbWapShoppingCartMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TbWapShoppingCart selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbWapShoppingCartMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TbWapShoppingCart> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbWapShoppingCartMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int deleteByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbWapShoppingCartMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKeySelective(TbWapShoppingCart record) throws RuntimeException {
        try {
            return this.tbWapShoppingCartMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKey(TbWapShoppingCart record) throws RuntimeException {
        try {
            return this.tbWapShoppingCartMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int insertSelective(TbWapShoppingCart record) throws RuntimeException {
        try {
            return this.tbWapShoppingCartMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * 添加商品到购物车
     */
	@Override
	public boolean addShoppingCart(String[] orderListNum,String deleteIdsList[],String accumulateFlag,Integer repairmanId,Integer userId) throws RuntimeException {
		try {
			if(orderListNum != null && orderListNum.length>0){
				//转换为map
				Map<Integer,Integer> inventoryMap = setMapInteger(orderListNum); 
				
				//先判断是否有反选的库存,有-先获取库存的数量,在删除
				Criteria criteria = new Criteria();
				List<Integer> inventoryList = new ArrayList<Integer>(inventoryMap.keySet());
				criteria.put("inventoryList", inventoryList);
				criteria.put("userId", userId);
				List<TbWapShoppingCart> cartList = this.tbWapShoppingCartMapper.selectByObject(criteria);  
				for(TbWapShoppingCart cart : cartList){
					Integer persistentBuyNum = inventoryMap.get(cart.getInventoryId());
					//立即购买不需要累计
					if(accumulateFlag != null){
						persistentBuyNum = persistentBuyNum+cart.getBuyNum();
					}
					inventoryMap.put(cart.getInventoryId(),persistentBuyNum);
				}
				
				//先批量删除选中的商品,在插入 ,根据库存id
				int delCount = tbWapShoppingCartMapper.deleteByObject(criteria); 
				logger.info("wap库存-删除购物车条数: " + delCount);
				
				//插入购物车表数据
				List<TbWapShoppingCart> shoppingCartList = new ArrayList<TbWapShoppingCart>();
				for(Integer key : inventoryMap.keySet()) {   
					Integer buyNum = inventoryMap.get(key);
					TbWapShoppingCart shoppingCart = new TbWapShoppingCart();  
					shoppingCart.setInventoryId(key);
					shoppingCart.setBuyNum(buyNum);
					shoppingCart.setCreateBy(userId);
					shoppingCart.setMasterId(repairmanId);
					shoppingCart.setPushFlag(repairmanId == null ? 0 : 1);
					shoppingCart.setCreateDt(System.currentTimeMillis());
					shoppingCartList.add(shoppingCart);
				}
				//批量插入
				int insCount = tbWapShoppingCartMapper.batchInsertSelective(shoppingCartList);
				logger.info("wap库存-插入购物车条数: " + insCount);
				if(insCount > 0){
					return true;
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new RuntimeException(e);
		}
		return false;
	}
	
	/**
	 * 显示购物车
	 */
	@Override
	public List<WapShoppingCartDto> selectByCart(Criteria parameter,String lon,String lat) throws RuntimeException {
		try {
			 List<WapShoppingCartDto> shoppingCartDto = this.tbWapShoppingCartMapper.selectByCart(parameter);
			 if(lon != null && lat != null){
				 //计算出店铺的距离
				 for(WapShoppingCartDto cart : shoppingCartDto){  
					 double lon1 = cart.getLon() == null ? 0.00 : Double.parseDouble(cart.getLon());
					 double lat1 = cart.getLat() == null ? 0.00 : Double.parseDouble(cart.getLat());
					 Double shopDistance = BaiduMapUtil.GetShortDistance(Double.parseDouble(lon), Double.parseDouble(lat), lon1, lat1);
					 cart.setShopDistance(shopDistance == null ? "" : String.valueOf(shopDistance));
				 }
			 }
			 return shoppingCartDto;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}

	/**
	 * 删除购物车
	 */
	@Override
	public int deleteByCartObject(Criteria example) {
		try {
			return this.tbWapShoppingCartMapper.deleteByCartObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}

	/**
	 * 处理结算
	 */
	@Override
	public List<WapShoppingCartDto> doSettlement(String[] orderListNum,String[] cartIdsList,Integer userId) {
		List<WapShoppingCartDto> cartList = new ArrayList<WapShoppingCartDto>();
		if(cartIdsList != null && cartIdsList.length>0){
			Criteria criteria = new Criteria();
			criteria.put("userId", userId);
			//调用设置map方法
			Map<String,String> tempMap = setMap(orderListNum);  
			if(tempMap.size()>0){
				//根据购物车ID查询
				List<String> idsList = new ArrayList<String>(tempMap.keySet());
				criteria.put("idsList", idsList);
				List<TbWapShoppingCart> shoppingCartList = this.tbWapShoppingCartMapper.selectByObject(criteria);  
				for(TbWapShoppingCart cart : shoppingCartList){
					String orderNum = tempMap.get(String.valueOf(cart.getId()));  //根据ID获取数量
					if(StringUtils.isNotBlank(orderNum)){
						cart.setBuyNum(Integer.parseInt(orderNum));
					}
					//根据购物车ID更新数量
					int updateCount = this.tbWapShoppingCartMapper.updateByPrimaryKey(cart);
					logger.info("wap结算-更新购物车条数: " + updateCount);
				}
			}
			//查询出选择的购物车信息
			criteria.put("idsList",Arrays.asList(cartIdsList));
			cartList = this.tbWapShoppingCartMapper.selectByCart(criteria);
		}
		return cartList;
	}

	/**
	 * 处理产品详情立即购买业务
	 */
	@Override
	public List<WapShoppingCartDto> nowBuy(String[] orderListNum,String[] deleteIdsList, Integer userId) throws RuntimeException {
		List<WapShoppingCartDto> cartList = new ArrayList<WapShoppingCartDto>();
		//调用加入购物车中方法
		boolean isSuccess = this.addShoppingCart(orderListNum,deleteIdsList,null,null,userId);  
		if(isSuccess){
			//调用设置map方法
			Map<String,String> tempMap = setMap(orderListNum);  	
			//查询出立即购买的产品信息
			List<String> inventoryList = new ArrayList<String>(tempMap.keySet());				  
			if(inventoryList != null && inventoryList.size()>0){   
				Criteria criteria = new Criteria();
				criteria.put("userId", userId);
				criteria.put("inventoryList",inventoryList);
				cartList = this.tbWapShoppingCartMapper.selectByCart(criteria);
			}
		}
		return cartList;
	}
	
	/**
	 * 设置LIST
	 * @param orderListNum
	 * @param strList
	 * @throws RuntimeException
	 */
	public Map<String,String> setMap(String[] orderListNum) throws RuntimeException {
		Map<String,String> tempMap = new HashMap<String,String>();
		if(orderListNum != null && orderListNum.length>0){
			for(String strs : orderListNum){ 
				String[] goods = strs.split("=");
				tempMap.put(goods[0], goods[1]);
			}
		}
		return tempMap;
	}
	
	/**
	 * 设置LIST
	 * @param orderListNum
	 * @param strList
	 * @throws RuntimeException
	 */
	public Map<Integer,Integer> setMapInteger(String[] orderListNum) throws RuntimeException {
		Map<Integer,Integer> tempMap = new HashMap<Integer,Integer>();
		for(String strs : orderListNum){ 
			String[] goods = strs.split("=");
			tempMap.put(Integer.parseInt(goods[0]), Integer.parseInt(goods[1]));
		}
		return tempMap;
	}
	
	/**
	 * 计算运费
	 * @param cartList
	 * @return
	 * @throws RuntimeException
	 */
	public Map<Integer,CalulateFreightDto> calculateFreight(List<WapShoppingCartDto> cartList,double lon2,double lat2) throws RuntimeException {
		Map<Integer,CalulateFreightDto> resultMap = new HashMap<Integer, CalulateFreightDto>();
		if(cartList != null && cartList.size()>0){
			for(WapShoppingCartDto shoppingCart : cartList){
				double calculateMoney = 0;
				for(WapProductInventoryDto inventory : shoppingCart.getProductInventoryList()){
					BigDecimal singlePrice = inventory.getOutstockPrice().multiply(new BigDecimal(inventory.getBuyNum()));
					BigDecimal totalPrice = new BigDecimal(calculateMoney);
					calculateMoney = totalPrice.add(singlePrice).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
				}
				CalulateFreightDto calulateFreightDto = getCalculateFreight(shoppingCart.getShopId(),shoppingCart.getLon(),shoppingCart.getLat(),lon2,lat2,calculateMoney);
				resultMap.put(shoppingCart.getShopId(), calulateFreightDto);
				shoppingCart.setFreight(String.valueOf(calulateFreightDto.getFreight()));
				shoppingCart.setDescription(calulateFreightDto.getDescription());
				shoppingCart.setDeliveryFlag(calulateFreightDto.getDeliveryFlag());
			}
		}
		return resultMap;
	}
	
	/**
	 * 计算运费,更改收货地址调用
	 * @param map
	 * @param calulateFreightList
	 * @param address
	 * @return
	 */
	public Map<Integer,CalulateFreightDto> findCalculateFreight(Map<Integer,CalulateFreightDto> map,List<CalulateFreightDto> calulateFreightList,String address){
		Map<Integer,CalulateFreightDto> resultMap = new HashMap<Integer, CalulateFreightDto>();
		if(map != null && map.size()>0){
			//获取该地址的位置
			Map<String, String> mapLatitude = GaoDeMapUtil.getLatitude(address);
			if(mapLatitude == null){
				return resultMap;
			}
			double lon2 = Double.parseDouble(mapLatitude.get("lon"));
			double lat2 = Double.parseDouble(mapLatitude.get("lat"));
			if(map != null && map.size()>0){
				for(Integer key : map.keySet()) {  
					CalulateFreightDto cf = map.get(key);
					CalulateFreightDto calulateFreight = getCalculateFreight(key,cf.getLon(),cf.getLat(),lon2,lat2,cf.getAmount());
					calulateFreightList.add(calulateFreight);
					resultMap.put(key, calulateFreight);
				}
			}
		}
		return resultMap;
	}
	
	/**
	 * 计算运费,公用方法
	 * @param shopId
	 * @param lon
	 * @param lat
	 * @param lon2
	 * @param lat2
	 * @param calculateMoney
	 * @return
	 */
	public CalulateFreightDto getCalculateFreight(Integer shopId,String lon,String lat,double lon2,double lat2,double calculateMoney){
		double lon1 = lon == null ? 0.00 : Double.parseDouble(lon);
		double lat1 = lat == null ? 0.00 : Double.parseDouble(lat);
		//根据店铺的位置和收货地址位置获取距离
		Double distance = BaiduMapUtil.GetShortDistance(lon1,lat1,lon2,lat2);
		//查询出该店铺的运费方式
		Criteria criteria = new Criteria();
		criteria.put("shopId", shopId);
		criteria.put("distance",distance);
		criteria.put("amount", calculateMoney);
		TbDeliveryTerms deliceryTerms = tbDeliveryTermsMapper.selectByDeliveryObject(criteria);
		String descriptionStr = "该店不提供送货服务";
		double freightStr = 0.0;
		String deliveryFlag = "1"; //不送货标识，0送货,1不送货
		CalulateFreightDto calulateFreightDto = new CalulateFreightDto();
		if(deliceryTerms != null){
			//运费为-1表示超出了送货距离
			freightStr = deliceryTerms.getFreight() == -1 ? 0.0 : deliceryTerms.getFreight();
			deliveryFlag = deliceryTerms.getFreight() == -1 ? "1" : "0";
			descriptionStr = deliceryTerms.getDescription();
		}
		calulateFreightDto.setShopId(shopId);
		calulateFreightDto.setAmount(calculateMoney);
		calulateFreightDto.setLat(lat);
		calulateFreightDto.setLon(lon);
		calulateFreightDto.setFreight(freightStr);
		calulateFreightDto.setDescription(descriptionStr);
		calulateFreightDto.setDeliveryFlag(deliveryFlag);
		return calulateFreightDto;
	}

	/**
	 * 删除所有失效的产品
	 */
	@Override
	public int deleteAllByPrimaryKey(Criteria example) {
		try {
			return this.tbWapShoppingCartMapper.deleteAllByPrimaryKey(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}
	
}