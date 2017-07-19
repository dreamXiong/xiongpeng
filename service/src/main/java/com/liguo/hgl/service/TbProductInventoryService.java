package com.liguo.hgl.service;

import com.liguo.hgl.proxydao.dto.TbProductInventoryDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbProductInventory;
import com.liguo.hgl.proxydao.page.PageDto;

import java.math.BigDecimal;
import java.util.List;

public interface TbProductInventoryService {
	/**
	 * 根据条件获取总行数
	 */
	int countByObject(Criteria example) throws RuntimeException;

	/**
	 * 根据主键查询记录
	 */
	TbProductInventory selectByPrimaryKey(Integer id) throws RuntimeException;

	/**
	 * 根据指定条件查询记录
	 */
	List<TbProductInventory> selectByObject(Criteria example)
			throws RuntimeException;

	/**
	 * 根据主键删除记录
	 */
	int deleteByPrimaryKey(Integer id) throws RuntimeException;

	/**
	 * 根据主键更新属性不为空的记录
	 */
	int updateByPrimaryKeySelective(TbProductInventory record)
			throws RuntimeException;

	/**
	 * 根据主键更新记录
	 */
	int updateByPrimaryKey(TbProductInventory record) throws RuntimeException;

	/**
	 * 保存属性不为空的记录
	 */
	int insertSelective(TbProductInventory record) throws RuntimeException;

	/**
	 * 根据主键查询记录
	 */
	TbProductInventoryDto selectById(Integer id) throws RuntimeException;

	List<TbProductInventoryDto> selectByCriteria(Criteria criteria, PageDto page)
			throws RuntimeException;

	List<TbProductInventoryDto> selectByCriteria(Criteria criteria)
			throws RuntimeException;

	/**
	 * 根据id逻辑删除数据，必须给参数 lastupdateby 和 id
	 * 
	 * @param criteria
	 * @return
	 * @throws RuntimeException
	 */
	int markDeleteByPrimaryKey(int id, String lastupdateby)
			throws RuntimeException;

	public int batchUpdatePrice(String inventoryIds, String asPrice,
			String priceMethod, String price, String user)
			throws RuntimeException;
	
	/**
	 * 处理结算的业务计算方法
	 */
	String calculateMoney(String orderListNum[],double discount) throws RuntimeException;
	
	BigDecimal calculateDiscount(BigDecimal outstockPrice,double discount);
		
}