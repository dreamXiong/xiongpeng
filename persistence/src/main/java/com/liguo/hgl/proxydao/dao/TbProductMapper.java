package com.liguo.hgl.proxydao.dao;

import java.util.List;

import com.liguo.hgl.proxydao.base.BaseMapper;
import com.liguo.hgl.proxydao.dto.TbProductDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.ProductInfoDto;
import com.liguo.hgl.proxydao.model.TbProduct;
import com.liguo.hgl.proxydao.model.TbProductTestDto;
import com.liguo.hgl.proxydao.page.PageDto;

public interface TbProductMapper extends BaseMapper {
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
    int insert(TbProduct record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbProduct record);

    /**
     * 根据条件查询记录集
     */
    List<TbProduct> selectByObject(Criteria example);
    
    /**
     * 根据条件查询记录集
     */
    List<TbProductDto> selectByCriteria(Criteria criteria,PageDto page);
    /**
     * 根据条件查询记录集
     */
    List<TbProductDto> selectByCriteria(Criteria criteria);

    /**
     * 根据主键查询记录
     */
    TbProduct selectByPrimaryKey(Integer id);
    
    
    TbProductDto selectProductDtoByPrimaryKey(Integer id);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbProduct record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbProduct record);
    
    /**
     * 查询出更新的信息
     * @param id
     * @return
     */
    public ProductInfoDto selectUpdateInfo(Integer id);
    
    public List<ProductInfoDto> selectInfoListByName(Criteria criteria, PageDto page);
    
	public List<TbProductTestDto> selectTest();
	
	 public Integer selectShopIdByPId(Integer productId);
}