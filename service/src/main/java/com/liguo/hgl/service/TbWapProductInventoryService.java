package com.liguo.hgl.service;

import java.util.List;

import com.liguo.hgl.proxydao.dto.TbWapProductInventoryDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.ProductImport;
import com.liguo.hgl.proxydao.model.TbWapProductInventory;
import com.liguo.hgl.proxydao.page.PageDto;

public interface TbWapProductInventoryService {
    /**
     * 根据条件获取总行数
     */
    int countByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键查询记录
     */
    TbWapProductInventory selectByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据指定条件查询记录
     */
    List<TbWapProductInventory> selectByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbWapProductInventory record) throws RuntimeException;

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbWapProductInventory record) throws RuntimeException;

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbWapProductInventory record) throws RuntimeException;
    
    /**
     * 根据条件查询出库存信息
     * @param criteria
     * @return
     * @throws RuntimeException
     */
    List<TbWapProductInventoryDto> selectByCriteria(Criteria criteria)  throws RuntimeException;
    
    /**
     * 批量上下架
     * @param parameter
     * @return
     * @throws RuntimeException
     */
    int batchUpOrdownFrame(Criteria parameter) throws RuntimeException;
    
    /**
     * 根据条件查询返回列表
     * @param criteria
     * @param page
     * @return
     * @throws RuntimeException
     */
	List<TbWapProductInventoryDto> selectByCriteria(Criteria criteria, PageDto page) throws RuntimeException;
	
	/**
	 * 根据条件删除库存信息
	 * @param parameter
	 * @return
	 */
	int markDeleteByPrimaryKey(Criteria parameter);
	
	/**
	 * 根据id查询出库存对象
	 * @param id
	 * @return
	 * @throws RuntimeException
	 */
	TbWapProductInventoryDto selectById(Integer id) throws RuntimeException;
	
	/**
	 * 批量更新价格
	 * @param id
	 * @param asPrice
	 * @param priceMethod
	 * @param price
	 * @param user
	 * @return
	 * @throws RuntimeException
	 */
	int batchUpdatePrice(String[] id, String asPrice,String priceMethod, String price, String user) throws RuntimeException;
	
	/**
	 * 批量更新状态
	 * @param parameter
	 * @return
	 * @throws RuntimeException
	 */
	int batchUpdateStatus(Criteria parameter) throws RuntimeException;
		
    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(TbWapProductInventory record);
    
    /**
     * 批量插入
     * @param twis
     * @return
     */
	int batchInsertSelective(List<TbWapProductInventory> twis);
	
	/**
	 * 单个插入
	 * @param twis
	 * @return
	 */
	int insertBatchInventory(List<TbWapProductInventory> twis);
	
	/**
	 * 获取产品名称
	 * @param criteria
	 * @return
	 */
	List<ProductImport> findProNameAndCodeLimit(Criteria criteria);
	
	/**
	 * 获取产品名称返回单条记录
	 * @param criteria
	 * @return
	 */
	TbWapProductInventoryDto selectByProductNameCriteria(Criteria criteria);
}