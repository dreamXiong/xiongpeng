package com.liguo.hgl.proxydao.dao;

import java.util.List;

import com.liguo.hgl.proxydao.base.BaseMapper;
import com.liguo.hgl.proxydao.dto.TbProductInventoryDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbProductInventory;
import com.liguo.hgl.proxydao.page.PageDto;

public interface TbProductInventoryMapper extends BaseMapper {
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
    int insert(TbProductInventory record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbProductInventory record);

    /**
     * 根据条件查询记录集
     */
    List<TbProductInventory> selectByObject(Criteria example);

    /**
     * 根据主键查询记录
     */
    TbProductInventory selectByPrimaryKey(Integer id);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbProductInventory record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbProductInventory record);
    
    /**
     * 根据主键查询记录
     */
    TbProductInventoryDto selectById(Integer id);
    
    /**
     * 根据条件查询列表
     */
    List<TbProductInventoryDto> selectByCriteria(Criteria criteria,PageDto page);
    
    List<TbProductInventoryDto> selectByCriteria(Criteria criteria);
    
    int markDeleteByPrimaryKey(Criteria criteria);
    
    /**
     * 根据条件查询记录集
     */
    int batchUpdatePrice(Criteria example);
    
    /**
     * 根据多个主键查询
     */
    List<TbProductInventory> selectByPrimaryKeys(List<Integer> ids);
}