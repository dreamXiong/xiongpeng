package com.liguo.hgl.proxydao.dao;

import java.util.List;

import com.liguo.hgl.proxydao.base.BaseMapper;
import com.liguo.hgl.proxydao.dto.TbWapProductDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbWapProduct;
import com.liguo.hgl.proxydao.model.WapProductInfoDto;
import com.liguo.hgl.proxydao.page.PageDto;

public interface TbWapProductMapper extends BaseMapper {
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
    int insert(TbWapProduct record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbWapProduct record);

    /**
     * 根据条件查询记录集
     */
    List<TbWapProduct> selectByObject(Criteria example);

    /**
     * 根据主键查询记录
     */
    TbWapProduct selectByPrimaryKey(Integer id);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbWapProduct record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbWapProduct record);
    
    /**
     * 根据id查询
     * @param id
     * @return
     * @throws RuntimeException
     */
    TbWapProductDto selectWapProductDtoByPrimaryKey(Integer id);
    
    /**
     * 根据条件查询返回集合(分页)
     * @param criteria
     * @return
     * @throws RuntimeException
     */
    List<TbWapProductDto> selectByCriteria(Criteria criteria,PageDto page);
    
    /**
     * 根据条件查询返回集合
     * @param criteria
     * @return
     * @throws RuntimeException
     */
    List<TbWapProductDto> selectByCriteria(Criteria criteria) throws RuntimeException;
    
    /**
     * 根据条件查询
     * @param criteria
     * @param page
     * @return
     */
    List<WapProductInfoDto> selectInfoListByName(Criteria criteria, PageDto page);
    
    /**
     * 查询更新信息
     * @param criteria
     * @return
     */
	WapProductInfoDto selectUpdateInfo(Criteria criteria);
	
    /**
     * 查询产品列表
     * @param c
     * @return
     */
	public List<TbWapProductDto> selectTbWapPickList(Criteria criteria);
	
	/**
	 * 批量添加产品
	 * @param objList
	 */
	public void insertBatchProduct(List<TbWapProduct>objList);
}