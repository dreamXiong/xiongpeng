package com.liguo.hgl.proxydao.dao;

import java.util.List;

import com.liguo.hgl.proxydao.base.BaseMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.MyOrderStatesCount;
import com.liguo.hgl.proxydao.model.OrderGroupDto;
import com.liguo.hgl.proxydao.model.TbOrderGroup;
import com.liguo.hgl.proxydao.page.PageDto;

public interface TbOrderGroupMapper extends BaseMapper {
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
    int insert(TbOrderGroup record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbOrderGroup record);

    /**
     * 根据条件查询记录集
     */
    List<TbOrderGroup> selectByObject(Criteria example);

    /**
     * 根据主键查询记录
     */
    TbOrderGroup selectByPrimaryKey(Integer id);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbOrderGroup record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbOrderGroup record);
    
    /**
     * 我的订单列表
     * */
    public List<OrderGroupDto> selectOrderGroupList(Criteria parameter,PageDto page);
    
    /**
     * 我的订单统计
     * */
    public MyOrderStatesCount selectMyOrderStatesCount(Integer shopId);
    
    public MyOrderStatesCount selectMyOrderStatesCountAdmin(List<Integer> shopIdList);
    
    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int batchInsert(List<TbOrderGroup> record);
    
    /*异常补单分页查询*/
    List<OrderGroupDto> selectObjectByPage(Criteria parameter,PageDto page);
    
    OrderGroupDto selectObjectByPrimaryKey(Integer id);
    /**
     * 根据支付订单号查询查询对应的订单
     * @param orderNumber
     * @return
     * @author zss
     */
	TbOrderGroup selectByOrderNumber(String orderNumber);
	/**
	 * 根据条件查询记录集
	 */
	List<TbOrderGroup> findOrderGroupByOrderStatus(Criteria example);
	/**
	 * 根据条件查询记录集
	 */
	int updateOrderStatusByIds(Criteria example);
}