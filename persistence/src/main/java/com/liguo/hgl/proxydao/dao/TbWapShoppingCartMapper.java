package com.liguo.hgl.proxydao.dao;

import com.liguo.hgl.proxydao.base.BaseMapper;
import com.liguo.hgl.proxydao.dto.WapShoppingCartDto;
import com.liguo.hgl.proxydao.dto.WapShoppingCartInfoDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbWapShoppingCart;
import java.util.List;

public interface TbWapShoppingCartMapper extends BaseMapper {
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
    int insert(TbWapShoppingCart record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbWapShoppingCart record);

    /**
     * 根据条件查询记录集
     */
    List<TbWapShoppingCart> selectByObject(Criteria example);

    /**
     * 根据主键查询记录
     */
    TbWapShoppingCart selectByPrimaryKey(Integer id);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbWapShoppingCart record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbWapShoppingCart record);
    
    /**
     * 批量插入
     * @param record
     * @return
     */
    int batchInsertSelective(List<TbWapShoppingCart> record);
    
    /**
     * 根据条件查询
     * @param parameter
     * @return
     * @throws RuntimeException
     */
    List<WapShoppingCartDto> selectByCart(Criteria parameter) throws RuntimeException;
    
    /**
     * 根据主键删除记录
     */
    int deleteByCartObject(Criteria example) throws RuntimeException;
    
    /**
     * 根据条件删除记录
     */
    int deleteByCartInfoObject(Criteria example);
    
    /**
     * 根据主键更新属性不为空的记录
     */
    int batchUpdateByPrimaryKey(List<TbWapShoppingCart> record);
    
	/**
	 * 根据条件删除
	 * @param example
	 * @return
	 */
    int deleteAllByPrimaryKey(Criteria example);
    
    /**
     * 根据条件查询
     * @param parameter
     * @return
     */
    List<WapShoppingCartInfoDto> selectByCartInfo(Criteria parameter) ;
}