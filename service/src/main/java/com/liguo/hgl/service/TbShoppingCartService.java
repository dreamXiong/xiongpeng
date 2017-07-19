package com.liguo.hgl.service;

import java.util.List;
import java.util.Map;

import com.liguo.hgl.proxydao.dto.ShoppingCartDto;
import com.liguo.hgl.proxydao.dto.WebUserDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbShoppingCart;

public interface TbShoppingCartService {
    /**
     * 根据条件获取总行数
     */
    int getUserCartNumber(Integer userId) throws RuntimeException;

    /**
     * 根据主键查询记录
     */
    TbShoppingCart selectByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据指定条件查询记录
     */
    List<TbShoppingCart> selectByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Criteria parameter) throws RuntimeException;

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbShoppingCart record) throws RuntimeException;

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbShoppingCart record) throws RuntimeException;

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbShoppingCart record) throws RuntimeException;
    
    /**
     * 添加商品到购物车
     */
    boolean addShoppingCart(String orderListNum[],String deleteIdsList[],boolean shoppingType,WebUserDto webUser,double discount,String merchantsId,String brandId) throws RuntimeException;
    
    /**
     * 查询当前用户的购物车信息
     * @param parameter
     * @return
     */
    Map<String,List<ShoppingCartDto>> selectByCart(Criteria parameter) throws RuntimeException;
    
    /**
     * 去结算业务处理
     * @param orderListNum 库存ID=数据
     * @param cartIdsList 购物车Id(多个)
     * @param userId 用户ID
     * @param discount 折扣(95折)
     * @return
     * @throws RuntimeException
     */
    List<ShoppingCartDto> doSettlement(String orderListNum[],String cartIdsList[],Integer userId,Double discount) throws RuntimeException;
    
    /**
     * 根据品牌查询购物车内招商产品总额
     * @param userId
     * @return
     * @author zss
     * @param brandId 
     */
	double getShopCartMoneyByBid(Integer userId, Integer brandId);
	
	/**
	 * 根据招商品牌Id查询购物车内
	 * @param bid
	 * @param loginUserId
	 * @return
	 * @author zss
	 */
	List<ShoppingCartDto> doSettlementMerchant(Integer bid, Integer loginUserId,Double discount);
	
	/**
	 * 判断是否可以进行结算
	 * @param cartIdsList
	 * @param loginUserId
	 * @return
	 * @author zss
	 */
	Map<String,Object> isSettlement(String[] cartIdsList, Integer loginUserId);
	
	/**
	 * 转换为map集合
	 * @param orderListNum
	 * @return
	 */
	Map<Integer,String> splitMap(String orderListNum[]);
}