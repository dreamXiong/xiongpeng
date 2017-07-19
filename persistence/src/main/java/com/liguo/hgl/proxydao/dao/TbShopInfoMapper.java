package com.liguo.hgl.proxydao.dao;

import java.util.List;

import com.liguo.hgl.proxydao.base.BaseMapper;
import com.liguo.hgl.proxydao.dto.ShopIndexForWapDto;
import com.liguo.hgl.proxydao.dto.ShopWebUserDto;
import com.liguo.hgl.proxydao.dto.TbWapProductDto;
import com.liguo.hgl.proxydao.dto.WapProductType;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbShopInfo;
import com.liguo.hgl.proxydao.page.PageDto;

public interface TbShopInfoMapper extends BaseMapper {
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
    int insert(TbShopInfo record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbShopInfo record);

    /**
     * 根据条件查询记录集
     */
    List<TbShopInfo> selectByObject(Criteria example);

    /**
     * 根据主键查询记录
     */
    TbShopInfo selectByPrimaryKey(Integer id);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbShopInfo record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbShopInfo record);

	ShopWebUserDto getShopWebUserById(Integer id);

	List<ShopWebUserDto> selectByCriteria(Criteria criteria, PageDto page);
	/**
	 * 店铺首页信息查询
	 * */
	ShopIndexForWapDto selectShopIndexInfo(Criteria criteria);
	
	
	List<WapProductType> selectProductBrand(Criteria criteria);
	
	public List<TbWapProductDto> selectTbWapProductDtoList(Criteria criteria);
	/**
	 * 根据用户id得到shop
	 * @param userId
	 * @return
	 * @author zss
	 */
	TbShopInfo selectByUserId(int userId);
	/**
	 * 
	 * wap端 得到该区的店铺并接距离升序的排序
	 * */
	public List<TbShopInfo> selectTbShopInfoForWap(Criteria criteria);
	
	/**
	 * 查询出返利的账号和店铺信息
	 * @param criteria
	 * @return
	 */
	TbShopInfo selectRebatereCommended(Criteria criteria);
	
	/**
	 * 附近的店
	 * @param criteria
	 * @return
	 */
	public List<TbShopInfo> selectNearTbShopInfo(Criteria parameter);
	
	Integer selectNearTbShopPageCount(Criteria parameter);
}