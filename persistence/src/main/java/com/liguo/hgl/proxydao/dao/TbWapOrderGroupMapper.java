package com.liguo.hgl.proxydao.dao;

import java.util.List;

import com.liguo.hgl.proxydao.base.BaseMapper;
import com.liguo.hgl.proxydao.dto.OrderGroupDetailDto;
import com.liguo.hgl.proxydao.dto.WapOrderGroup;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.MyOrderStatesCount;
import com.liguo.hgl.proxydao.model.TbWapOrderGroup;
import com.liguo.hgl.proxydao.model.WapOrderGroupDto;
import com.liguo.hgl.proxydao.page.PageDto;

public interface TbWapOrderGroupMapper extends BaseMapper {
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
    int insert(TbWapOrderGroup record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbWapOrderGroup record);

    /**
     * 根据条件查询记录集
     */
    List<TbWapOrderGroup> selectByObject(Criteria example);
    
    
    public List<WapOrderGroup> selectWapOrderGroupByObj(Criteria parameter);

    /**
     * 根据主键查询记录
     */
    TbWapOrderGroup selectByPrimaryKey(Integer id);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbWapOrderGroup record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbWapOrderGroup record);
    
    public List<WapOrderGroupDto> selectOrderGroupList(Criteria parameter,PageDto pgo);
    
    public MyOrderStatesCount selectMyOrderStatesCount(Integer shopId);
    
    OrderGroupDetailDto selectOrderGroupDetail(Integer id);
    
    List<WapOrderGroupDto> selectOrderGroupList(Criteria parameter);
    
    public int updateSaleNumByOrderId(Integer orderGroupId);
    
    public int updateOrderRebateById(List<Integer> orderIdList);
    /**
     * 根据条件查询记录集
     */
    List<TbWapOrderGroup> findOrderGroupByOrderStatus(Criteria example);
    /**
     * 根据条件查询记录集
     */
    int updateOrderStatusByIds(Criteria example);
    
    /*查询未完成的订单*/
    int selectUnCompleteOrder(Criteria example);
}