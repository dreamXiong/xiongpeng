package com.liguo.hgl.proxydao.dao;

import com.liguo.hgl.proxydao.base.BaseMapper;
import com.liguo.hgl.proxydao.dto.WapOrderServiceDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbWapOrderService;
import com.liguo.hgl.proxydao.page.PageDto;

import java.util.List;

public interface TbWapOrderServiceMapper extends BaseMapper {
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
    int insert(TbWapOrderService record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbWapOrderService record);

    /**
     * 根据条件查询记录集
     */
    List<TbWapOrderService> selectByObject(Criteria example);

    /**
     * 根据主键查询记录
     */
    TbWapOrderService selectByPrimaryKey(Integer id);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbWapOrderService record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbWapOrderService record);

    /**
     * 根据条件查询服务前台列表
     * @param parameter
     * @return
     * @author zss
     */
	List<WapOrderServiceDto> selectByCriteria(Criteria parameter);
	/**
	 * 根据条件查询服务前台列表(带分页)
	 * @param parameter
	 * @return
	 * @author zss
	 */
	List<WapOrderServiceDto> selectByCriteria(Criteria parameter,PageDto page);
	
	
	/*查询未完成的订单*/
	int selectUnCompleteOrderCount(Criteria criteria);
	
	int findServiceTypeIdByOrderId(Criteria criteria);
}