package com.liguo.hgl.proxydao.dao;

import com.liguo.hgl.proxydao.base.BaseMapper;
import com.liguo.hgl.proxydao.dto.TbMerchantsAssociatedDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbMerchants;
import com.liguo.hgl.proxydao.page.PageDto;

import java.util.List;

public interface TbMerchantsMapper extends BaseMapper {
    /**
     * 根据条件查询记录总数
     */
    int countByObject(Criteria example);

    /**
     * 根据条件删除记录
     */
    int deleteByObject(Criteria example);

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(TbMerchants record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbMerchants record);

    /**
     * 根据条件查询记录集
     */
    List<TbMerchants> selectByObject(Criteria example);

    /**
     * 根据主键查询记录
     */
    TbMerchants selectByPrimaryKey(Integer id);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbMerchants record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbMerchants record);

    
    /**
     * 根据id查询详情，填充品牌和地区
     * @param id
     * @return
     */
    TbMerchantsAssociatedDto selectById(Integer id);
	/**
	 * 
	 * @param criteria
	 * @param page
	 * @return
	 */
	List<TbMerchantsAssociatedDto> selectByCriteria(Criteria criteria, PageDto page);

	List<TbMerchantsAssociatedDto> selectByCriteria(Criteria example);

	List<TbMerchantsAssociatedDto> selectByShopCriteria(Criteria example);
	/**
	 * 查询品牌的招商区域
	 * @param criteria
	 * @return
	 * @author zss
	 */
	List<TbMerchants> selectByAdress(Criteria criteria);
}