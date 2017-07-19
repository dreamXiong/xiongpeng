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

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.dao.TbAgencyMapper;
import com.liguo.hgl.proxydao.dao.TbMerchantsMapper;
import com.liguo.hgl.proxydao.dao.TbProductInventoryMapper;
import com.liguo.hgl.proxydao.dao.TbShopInfoMapper;
import com.liguo.hgl.proxydao.dao.TbShoppingCartMapper;
import com.liguo.hgl.proxydao.dto.AgencyDto;
import com.liguo.hgl.proxydao.dto.ShoppingCartDto;
import com.liguo.hgl.proxydao.dto.ShoppingCartInfoDto;
import com.liguo.hgl.proxydao.dto.WebUserDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbMerchants;
import com.liguo.hgl.proxydao.model.TbProductInventory;
import com.liguo.hgl.proxydao.model.TbShoppingCart;
import com.liguo.hgl.service.TbProductInventoryService;
import com.liguo.hgl.service.TbShoppingCartService;

@Service
@Scope(value="prototype")
public class TbShoppingCartServiceImpl implements TbShoppingCartService {
	
    @Autowired
    private TbShoppingCartMapper tbShoppingCartMapper;
    @Autowired
	private TbProductInventoryMapper tbProductInventoryMapper;
    @Autowired
	protected TbProductInventoryService tbProductInventoryService;
    @Autowired
    protected TbAgencyMapper tbAgencyMapper;
    @Autowired
    protected TbMerchantsMapper tbMerchantsMapper;
    @Autowired
    private TbShopInfoMapper tbShopInfoMapper;
    
    private static final Logger logger = LoggerFactory.getLogger(TbShoppingCartServiceImpl.class);

    /**
     * 获取用户的购物车数量
     */
    public int getUserCartNumber(Integer userId) throws RuntimeException {
        try {
        	Criteria criteria = new Criteria();
			criteria.put("userId", userId);
            int count = this.tbShoppingCartMapper.countByObject(criteria);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据主键查询记录
     */
    public TbShoppingCart selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbShoppingCartMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据指定条件查询记录
     */
    public List<TbShoppingCart> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbShoppingCartMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据主键删除记录
     */
    public int deleteByPrimaryKey(Criteria parameter) throws RuntimeException {
        try {
            return this.tbShoppingCartMapper.deleteByPrimaryKey(parameter);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据主键更新属性不为空的记录
     */
    public int updateByPrimaryKeySelective(TbShoppingCart record) throws RuntimeException {
        try {
            return this.tbShoppingCartMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据主键更新记录
     */
    public int updateByPrimaryKey(TbShoppingCart record) throws RuntimeException {
        try {
            return this.tbShoppingCartMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 保存属性不为空的记录
     */
    public int insertSelective(TbShoppingCart record) throws RuntimeException {
        try {
            return this.tbShoppingCartMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 添加商品到购物车
     */
	@Override
	public boolean addShoppingCart(String[] orderListNum,String deleteIdsList[],boolean shoppingType,WebUserDto webUser,double discount,String merchantsId,String brandId) throws RuntimeException {
		try {
			Map<Integer,String> tempMap = splitMap(orderListNum);
			if(tempMap != null && tempMap.size()>0){
				List<TbShoppingCart> shoppingCartList = new ArrayList<TbShoppingCart>();
				List<Integer> idsList = new ArrayList<Integer>(tempMap.keySet());
				//查询出添加到购物车的库存
				List<TbProductInventory> list = tbProductInventoryMapper.selectByPrimaryKeys(idsList);
				if(list != null && list.size()>0){
					if(shoppingType){  //招商
						TbShoppingCart cart = new TbShoppingCart();
						cart.setShoppingType(shoppingType);   
						cart.setBrandId(Integer.parseInt(brandId));
						cart.setCreateBy(webUser.getId());
						if(StringUtils.isNotBlank(merchantsId)){
							cart.setMerchantsId(Integer.parseInt(merchantsId));
						}
						int updCount = tbShoppingCartMapper.updateByMerchants(cart);
						logger.info("库存-修改为招商条数: " + updCount);
					}else{  // 选货购买
						Criteria criteria = new Criteria();
						criteria.put("userId", webUser.getId());
						criteria.put("shoppingType", false);
						criteria.put("brandId", Integer.parseInt(brandId));
						//根据品牌查询出该用户有没有添加过招商
						TbShoppingCart shoppingCart = this.tbShoppingCartMapper.selectByIsMerchants(criteria);
					    //不等于空说明该用户已经添加该品牌为招商过，设置为招商
						if(shoppingCart != null){
							merchantsId = String.valueOf(shoppingCart.getMerchantsId());
							shoppingType = shoppingCart.getShoppingType();
						}
					}
					//循环计算价格添加到购物车表对象中
					for(int i=0;i<list.size();i++){
						TbProductInventory productInventory = list.get(i);
						if(productInventory != null){
							//获取购买数量
							String orderNumStr = tempMap.get(productInventory.getId());
							if(StringUtils.isNotBlank(orderNumStr)){
								//获取折后单价
								BigDecimal discountPrice = tbProductInventoryService.calculateDiscount(productInventory.getOutstockPrice(),discount);
								TbShoppingCart shoppingCart = new TbShoppingCart();
								shoppingCart.setBuyNum(Integer.parseInt(orderNumStr));
								shoppingCart.setCreateBy(webUser.getId());
								shoppingCart.setInventoryId(productInventory.getId());
								shoppingCart.setPrice(productInventory.getOutstockPrice().doubleValue());
								shoppingCart.setSalePrice(discountPrice.doubleValue());
								shoppingCart.setShoppingType(shoppingType);
								shoppingCart.setBrandId(Integer.parseInt(brandId));
								if(StringUtils.isNotBlank(merchantsId)){
									shoppingCart.setMerchantsId(Integer.parseInt(merchantsId));
								}
								shoppingCartList.add(shoppingCart);
							}
						}
					}
					//先批量删除选中的商品,在插入 ,根据库存id
					if(deleteIdsList != null && deleteIdsList.length>0){
						Criteria criteria = new Criteria();
						criteria.put("idsList", Arrays.asList(deleteIdsList));
						criteria.put("userId", webUser.getId());
						int delCount = tbShoppingCartMapper.deleteByObject(criteria);
						logger.info("库存-删除购物车条数: " + delCount);
					}
					//批量插入
					if(shoppingCartList != null && shoppingCartList.size()>0){
						int insCount = tbShoppingCartMapper.batchInsertSelective(shoppingCartList);
						logger.info("库存-插入购物车条数: " + insCount);
						return true;
					}
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new RuntimeException(e);
		}
		return false;
	}

	/**
	 * 购物车显示
	 */
	@Override
	public Map<String,List<ShoppingCartDto>> selectByCart(Criteria parameter) throws RuntimeException {
		Map<String,List<ShoppingCartDto>> shoppingMap = new HashMap<String,List<ShoppingCartDto>>();
		//查询出选购购买的购物车信息
		parameter.put("shoppingType", false);
		List<ShoppingCartDto> goodsOrderList = this.tbShoppingCartMapper.selectByCart(parameter);
		//查询出招商的购物车信息
		parameter.put("shoppingType", true);
		List<ShoppingCartDto> merchantOrderList = this.tbShoppingCartMapper.selectByCart(parameter);
		shoppingMap.put("merchantOrder", merchantOrderList);
		shoppingMap.put("goodsOrder", goodsOrderList);
		return shoppingMap;
	}
	
	/**
	 * 结算处理
	 * @param orderListNum
	 * @param userId
	 * @return
	 * @throws RuntimeException
	 */
	public List<ShoppingCartDto> doSettlement(String orderListNum[],String cartIdsList[],Integer userId,Double discount) throws RuntimeException {
		List<ShoppingCartDto> cartList = new ArrayList<ShoppingCartDto>();
		if(cartIdsList != null && cartIdsList.length>0){
			List<String> idList = new ArrayList<String>();
			idList.addAll(Arrays.asList(cartIdsList));
			Criteria criteria = new Criteria();
			criteria.put("userId", userId);
			Map<Integer,String> tempMap = splitMap(orderListNum);
			if(tempMap.size()>0){
				List<Integer> idsList = new ArrayList<Integer>(tempMap.keySet());
				criteria.put("idsList", idsList);
				List<TbShoppingCart> shoppingCartList = this.tbShoppingCartMapper.selectByObject(criteria);
				//先批量删除选中的商品,在插入 ,根据购物车id
				int delCount = tbShoppingCartMapper.deleteByPrimaryKey(criteria); 
				logger.info("购物车-删除购物车条数: " + delCount);
				//循环插入
				for(TbShoppingCart cart : shoppingCartList){
					String orderNum = tempMap.get(cart.getId());
					if(StringUtils.isNotBlank(orderNum)){
						cart.setId(null);
						cart.setBuyNum(Integer.parseInt(orderNum));
						int shopCount = tbShoppingCartMapper.insert(cart);
						idList.add(String.valueOf(cart.getId()));
						logger.info("购物车-插入购物车条数: " + shopCount);
					}
				}
			}
			//查询显示更改后的购物车信息
			criteria.put("idsList", idList);
			criteria.put("discount", discount);
			cartList = this.tbShoppingCartMapper.selectByCart(criteria);
		}
		return cartList;
	}

	@Override
	public double getShopCartMoneyByBid(Integer userId, Integer brandId) {
		Criteria parameter = new Criteria();
		parameter.put("shoppingType", true);
		parameter.put("brandId", brandId);
		parameter.put("userId", userId);
		//List<ShoppingCartDto> merchantOrderList = this.tbShoppingCartMapper.selectByCart(parameter);
		double cartMoney = tbShoppingCartMapper.getShopCartMoney(parameter);
		return cartMoney;
	}

	@Override
	public List<ShoppingCartDto> doSettlementMerchant(Integer bid,Integer userId,Double discount) {
		List<ShoppingCartDto> cartList = new ArrayList<ShoppingCartDto>();
		Criteria parameter = new Criteria();
		parameter.put("shoppingType", true);
		parameter.put("brandId", bid);
		parameter.put("userId", userId);
		List<TbShoppingCart> shoppingCarts= tbShoppingCartMapper.getShopCartId(parameter);
		List<String> cartIdList = new ArrayList<String>();
		for (int i = 0; i < shoppingCarts.size(); i++) {
			cartIdList.add(shoppingCarts.get(i).getId().toString());
			
		}
		if(cartIdList.size()>0){
			Criteria criteria = new Criteria();
			logger.debug("----dddd------"+cartIdList)	;
			criteria.put("idsList", cartIdList);
			criteria.put("shoppingType", true);
			criteria.put("userId", userId);
			criteria.put("discount", discount);		
			//List<TbShoppingCart> shoppingCartList = this.tbShoppingCartMapper.selectByObject(criteria);
			
			//	delAfterInsert(cartIdList,userId,shoppingCartList,null,cartIdList); //删除该用户已经加入购物车的库存商品,在批量插入
			
			cartList = this.tbShoppingCartMapper.selectByCart(criteria);
			logger.debug(cartIdList.get(0)+"----"+cartIdList);
		}
		return cartList;
	}

	@Override
	public Map<String,Object> isSettlement(String cartIdsList[], Integer loginUserId) {
		
		List<String> merchantsIds = new ArrayList<String>();//不满足条件的招商
		
		Map<String, Object> map = new HashMap<String,Object>();
		if(cartIdsList != null && cartIdsList.length>0){
			List<String> idList = new ArrayList<String>();
			idList.addAll(Arrays.asList(cartIdsList));
			Criteria criteria = new Criteria();
			criteria.put("userId", loginUserId);
			criteria.put("shoppingType", true);
			criteria.put("idList", idList);
			//判断是否存在招商
			List<ShoppingCartInfoDto> carts = tbShoppingCartMapper.selectByMerchats(criteria);
			if(carts.size()>0){
				List<String> merchantsIdList = new ArrayList<String>();
				for (ShoppingCartInfoDto cartInfoDto : carts) {
					merchantsIdList.add(cartInfoDto.getCartMerchantsId().toString());
				}
				
				Criteria parameter = new Criteria();
				parameter.put("merchantIdList", merchantsIdList);
				parameter.put("userId", loginUserId);
				parameter.put("state1",HglContants.MERCHANTS_AGENCY_STATE1);//暂时生效
				parameter.put("state2",HglContants.MERCHANTS_AGENCY_STATE2);//生效
				parameter.put("couponsState", HglContants.MERCHANTS_AGENCY_COUPONSSTATE1);
				List<AgencyDto> agencies = tbAgencyMapper.selectByCriteria(parameter);
				/*if(agencies.size() == merchantsIdList.size()){//判断是否都购买服务费
					TbMerchants merchants =null;
					for (ShoppingCartInfoDto cartInfoDto : carts) {
						 merchants = tbMerchantsMapper.selectByPrimaryKey(cartInfoDto.getCartMerchantsId());
						 //if(merchants.getNumber() >cartInfoDto.getCartMerchantsMoney()){
						 BigDecimal data1 = new BigDecimal(cartInfoDto.getCartMerchantsMoney()); 
						
						 BigDecimal data2 = new BigDecimal(merchants.getNumber()); 
						 if(data1.compareTo(data2) <0){
							 map.put("errcode", "2");
							 map.put("msg", "存在没有达到招商额度的招商产品");
							 return map;
						 }
					}
					
				}else*/ 
				if(agencies.size() != merchantsIdList.size()){//判断是否都购买服务费
					//------循环判断是否存在招商产品--------
					TbMerchants merchants =null;
					for (int i = 0; i < merchantsIdList.size(); i++) {
						System.out.println(merchantsIdList.get(i)+"-----------merchantsIdList.get(i)");
						if(agencies.size()>0){
							for (AgencyDto agencyDto : agencies) {
								if(merchantsIdList.get(i).equals(agencyDto.getMerchantId())){
									
									/*Criteria pCriteria = new Criteria();
									pCriteria.put("userId", loginUserId);
									pCriteria.put("shoppingType", true);
									pCriteria.put("idList", idList);*/
									criteria.put("merchantsId", agencyDto.getMerchantId());
									//判断是否存在招商
									List<ShoppingCartInfoDto> cartInfoDtos = tbShoppingCartMapper.selectByMerchats(criteria);
									merchants = tbMerchantsMapper.selectByPrimaryKey(cartInfoDtos.get(0).getCartMerchantsId());
									if(cartInfoDtos.get(0).getCartMerchantsMoney().compareTo(merchants.getNumber()) < 0){
										merchantsIds.add(merchantsIdList.get(i));
									}
									
								}else{
									merchantsIds.add(merchantsIdList.get(i));
								}
							}
						}else{
							merchantsIds.add(merchantsIdList.get(i));
						}
						
					}
			
					//map.put("merchantsId", merchantsIds);//把不满足条件的招商添加到map中去
					map.put("errcode", "1");
					map.put("msg", "存在未购买服务费的招商产品");
				}else{
					
					TbMerchants merchants =null;
					boolean isHasNo = true;
					for (ShoppingCartInfoDto cartInfoDto : carts) {
						 merchants = tbMerchantsMapper.selectByPrimaryKey(cartInfoDto.getCartMerchantsId());
						 BigDecimal data1 = new BigDecimal(cartInfoDto.getCartMerchantsMoney()); 
						
						 BigDecimal data2 = new BigDecimal(merchants.getNumber()); 
						 if(data1.compareTo(data2) <0){
							 isHasNo= false;
							 map.put("errcode", "2");
							 map.put("msg", "存在没有达到招商额度的招商产品");
							 merchantsIds.add(cartInfoDto.getCartMerchantsId().toString());
							// return map;
						 }
					}
					if(isHasNo){
						 map.put("errcode", "0");
						 map.put("msg", "招商已购买服务已达标！");
					}
				}
			
				if(merchantsIds.size()>0){
					Criteria criteria1 = new Criteria();
					criteria1.put("userId", loginUserId);
					criteria1.put("shoppingType", true);
					criteria1.put("idList", idList);
					criteria1.put("merchantsIds", merchantsIds);
					List<TbShoppingCart> shoppingCarts = tbShoppingCartMapper.selectNoMerchantsCart(criteria1);
					List<String> cartIds = new ArrayList<String>();//不满足条件的购物车Id
					for (TbShoppingCart cart : shoppingCarts) {
						cartIds.add(cart.getId().toString());
					}
					
					map.put("cartIds", cartIds);
				}
				
				
			}else{
				map.put("errcode", "0");
				map.put("msg", "不存在招商产品");
			}
		}else{map.put("errcode", "0");} 
		
		return map;
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