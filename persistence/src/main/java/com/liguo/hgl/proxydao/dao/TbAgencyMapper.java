package com.liguo.hgl.proxydao.dao;

import com.liguo.hgl.proxydao.base.BaseMapper;
import com.liguo.hgl.proxydao.dto.AgencyDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbAgency;
import com.liguo.hgl.proxydao.page.PageDto;

import java.util.List;

public interface TbAgencyMapper extends BaseMapper {
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
    int insert(TbAgency record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbAgency record);

    /**
     * 根据条件查询记录集
     */
    List<TbAgency> selectByObject(Criteria example);

    /**
     * 根据主键查询记录
     */
    TbAgency selectByPrimaryKey(Integer id);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbAgency record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbAgency record);
    /**
     * 根据招商Id查询
     * @param criteria
     * @param page
     * @return
     * @author zss
     */
	List<AgencyDto> selectByCriteria(Criteria criteria, PageDto page);
	/**
	 * 根据条件查询
	 * @param criteria
	 * @return
	 * @author zss
	 */
	List<AgencyDto> selectByCriteria(Criteria criteria);
	/**
	 * 查询生效的招商
	 * @param criteria
	 * @return
	 * @author zss
	 */
	List<TbAgency> selecEffectMerchants(Criteria criteria);
	/**
	 * 根据订单号查询对应的代理
	 * @param orderNumber
	 * @return
	 * @author zss
	 */
	TbAgency selectByOrderNumber(String orderNumber);
}