package com.liguo.hgl.service;

import com.liguo.hgl.proxydao.dto.CalulateFreightDto;
import com.liguo.hgl.proxydao.dto.WapShoppingCartDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbWapShoppingCart;

import java.util.List;
import java.util.Map;

public interface TbWapShoppingCartService {
    /**
     * 根据条件获取总行数
     */
    int countByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键查询记录
     */
    TbWapShoppingCart selectByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据指定条件查询记录
     */
    List<TbWapShoppingCart> selectByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbWapShoppingCart record) throws RuntimeException;

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbWapShoppingCart record) throws RuntimeException;

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbWapShoppingCart record) throws RuntimeException;
    
    /**
     * 增加购物车
     * @param orderListNum
     * @param deleteIdsList
     * @param userId
     * @return
     * @throws RuntimeException
     */
    boolean addShoppingCart(String[] orderListNum,String deleteIdsList[],String accumulateFlag,Integer repairmanId,Integer userId) throws RuntimeException;
    
    /**
     * 查询购物车列表
     * @param parameter
     * @param lon 经度
     * @param lat 维度
     * @return
     * @throws RuntimeException
     */
    List<WapShoppingCartDto> selectByCart(Criteria parameter,String lon,String lat) throws RuntimeException;
    
    /**
     * 根据条件删除记录
     */
    int deleteByCartObject(Criteria example);
    
    /**
     * 去结算处理
     * @param orderListNum 库存=数量
     * @param cartIdsList 购物车ID
     * @param userId 用户ID
     * @return
     * @throws RuntimeException
     */
	List<WapShoppingCartDto> doSettlement(String orderListNum[],String cartIdsList[],Integer userId) throws RuntimeException;
	
	/**
	 * 立即购买
	 * @param orderListNum 库存=数量
	 * @param deleteIdsList 购物车ID
	 * @param userId 用户ID
	 * @return
	 * @throws RuntimeException
	 */
	List<WapShoppingCartDto> nowBuy(String[] orderListNum,String deleteIdsList[],Integer userId) throws RuntimeException;
	
	/**
	 * 根据购物车的价格和距离计算出邮费
	 * @param cartList
	 * @param lon2
	 * @param lat2
	 * @return
	 * @throws RuntimeException
	 */
	Map<Integer,CalulateFreightDto> calculateFreight(List<WapShoppingCartDto> cartList,double lon2,double lat2) throws RuntimeException;
	
	/**
	 * 获取地址计算出距离
	 * @param map
	 * @param calulateFreightList
	 * @param address
	 * @return
	 * @throws RuntimeException
	 */
	Map<Integer,CalulateFreightDto> findCalculateFreight(Map<Integer,CalulateFreightDto> map,List<CalulateFreightDto> calulateFreightList,String address)throws RuntimeException;
	
	/**
	 * 获取邮费
	 * @param shopId
	 * @param lon
	 * @param lat
	 * @param lon2
	 * @param lat2
	 * @param calculateMoney
	 * @return
	 * @throws RuntimeException
	 */
	CalulateFreightDto getCalculateFreight(Integer shopId,String lon,String lat,double lon2,double lat2,double calculateMoney)throws RuntimeException;
	
	/**
	 * 根据条件删除
	 * @param example
	 * @return
	 */
	int deleteAllByPrimaryKey(Criteria example);
}