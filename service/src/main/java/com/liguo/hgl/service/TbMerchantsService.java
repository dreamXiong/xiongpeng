package com.liguo.hgl.service;

import com.liguo.hgl.proxydao.dto.TbMerchantsAssociatedDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbMerchants;
import com.liguo.hgl.proxydao.page.PageDto;

import java.util.List;

public interface TbMerchantsService {
    /**
     * 根据条件获取总行数
     */
    int countByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键查询记录
     */
    TbMerchants selectByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据指定条件查询记录
     */
    List<TbMerchants> selectByObject(Criteria example) throws RuntimeException;

    /**
     * 根据指定条件查询记录
     */
   // DataPackage getDatapackage(Criteria example, int lines, int page) throws RuntimeException;

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbMerchants record) throws RuntimeException;

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbMerchants record) throws RuntimeException;

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbMerchants record) throws RuntimeException;

  /**
   *  查询所有的招商
   * @param brandName根据品牌名称进行查询
   * @return
   */
    List<TbMerchants> getMerchantsList(String brandName);
    
    /**
     * 根据ID修改招商的状态
     * @param id
     * @return
     */
    TbMerchants updateState(Integer id);

    /**
     * 前台查询招商，填充统计
     * @param userId  shopid
     * @return
     */
	List<TbMerchantsAssociatedDto> getMerchantsList(int userId);

	/**
	 * 前台根据招商Id查询详情，需改变统计-围观
	 * @param id
	 * @param ip 客户端ip地址
	 * @param browser 客户端浏览器类型
	 * @return
	 */
	TbMerchants getMerchantsDetails(Integer id, String browser, String ip);

	/**
	 * 通过Id查询招商详情，填充地区和品牌
	 * @param id
	 * @return
	 */
	TbMerchants selectById(Integer id);

	/**
	 * 
	 * @param criteria
	 * @param page
	 * @return
	 */
	List<TbMerchantsAssociatedDto> getMerchantsList(Criteria criteria, PageDto page);
	
	/**
	 * 获取最新的招商信息
	 * @return
	 */
	List<TbMerchantsAssociatedDto> getNewestMerchants();
	
	
	/**
	 * 修改围观商家
	 * @param criteria
	 * @return
	 */
	int updateViews(TbMerchants merchant);
	
	/*操作招商公告 */
	public int operateMerchNotice(Integer merchantId,String name,String detail,String createdBy,Integer typeId);

	/**
	 * 前台招商服务购买信息
	 * @param id
	 * @param loginUserId
	 * @return
	 * @author zss
	 */
	TbMerchants getMerchantService(Integer id, Integer shopId);
	/**
	 * 判断当前用户是否具有购买服务的权限
	 * @param mid
	 * @param shopId
	 * @return
	 * @author zss
	 */
	boolean isMerchantService(Integer mid, Integer shopId);
	/**
	 * 验证品牌招商区域是否存在
	 * @param brandId
	 * @param city
	 * @param country
	 * @param street
	 * @return
	 * @author zss
	 */
	int checkMerchants(int brandId, int city, int country, int street,int merchantsId);

	/**
	 * 验证商家是否能查看此招商
	 * @param id  招商id
	 * @param shopId  厂家id
	 * @return
	 * @author zss
	 */
	boolean isHasMerchants(Integer id, Integer shopId);

}