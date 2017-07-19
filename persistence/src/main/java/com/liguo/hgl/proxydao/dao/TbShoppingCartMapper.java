package com.liguo.hgl.proxydao.dao;

import com.liguo.hgl.proxydao.base.BaseMapper;
import com.liguo.hgl.proxydao.dto.ShoppingCartDto;
import com.liguo.hgl.proxydao.dto.ShoppingCartInfoDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbShoppingCart;
import java.util.List;

public interface TbShoppingCartMapper extends BaseMapper {
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
    int deleteByPrimaryKey(Criteria example);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(TbShoppingCart record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbShoppingCart record);

    /**
     * 根据条件查询记录集
     */
    List<TbShoppingCart> selectByObject(Criteria example);

    /**
     * 根据主键查询记录
     */
    TbShoppingCart selectByPrimaryKey(Integer id);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbShoppingCart record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbShoppingCart record);
    
    /**
     * 批量保存属性不为空的记录
     */
    int batchInsertSelective(List<TbShoppingCart> record);
    
    /**
     * 查询当前用户的购物车信息
     * @param parameter
     * @return
     */
    List<ShoppingCartDto> selectByCart(Criteria parameter);
    
    /**
     * 查询购物车信息
     * @param parameter
     * @return
     */
    List<ShoppingCartInfoDto> selectByCartInfo(Criteria parameter);
    /**
     * 得到购物车总额
     * @param parameter
     * @return
     * @author zss
     */
	double getShopCartMoney(Criteria parameter);
	/**
	 * 招商购物车
	 * @param parameter
	 * @return
	 * @author zss
	 */
	List<TbShoppingCart> getShopCartId(Criteria parameter);
	/**
	 * 按照招商分类查询
	 * @param criteria
	 * @return
	 * @author zss
	 */
	List<ShoppingCartInfoDto> selectByMerchats(Criteria criteria);
	
	/**
	 * 查询出该品牌下是否存在招商
	 * @param criteria
	 * @return
	 */
	TbShoppingCart selectByIsMerchants(Criteria criteria);
	
	/**
	 * 更新招商信息
	 * @param record
	 * @return
	 */
    int updateByMerchants(TbShoppingCart record);

    /**
     * 查询不是招商的购物车信息
     * @param criteria1
     * @return
     */
	List<TbShoppingCart> selectNoMerchantsCart(Criteria criteria1);

	/**
	 * 更新店铺类型
	 * @param parameter
	 * @return
	 */
	int updateShopInfoType(Criteria parameter);
}