package com.liguo.hgl.proxydao.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;
import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbWapOrderGroupMapper;
import com.liguo.hgl.proxydao.dto.OrderGroupDetailDto;
import com.liguo.hgl.proxydao.dto.WapOrderGroup;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.MyOrderStatesCount;
import com.liguo.hgl.proxydao.model.TbWapOrderGroup;
import com.liguo.hgl.proxydao.model.WapOrderGroupDto;
import com.liguo.hgl.proxydao.page.PageDto;

@Repository
public class TbWapOrderGroupMapperImpl extends BaseMapperImpl<TbWapOrderGroup> implements TbWapOrderGroupMapper {

    public TbWapOrderGroupMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbWapOrderGroupMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbWapOrderGroup record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbWapOrderGroupMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbWapOrderGroup record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbWapOrderGroupMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbWapOrderGroupMapper.deleteByObject", parameter);
    }

    public int insert(TbWapOrderGroup record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbWapOrderGroupMapper.insert", record);
    }

    public int insertSelective(TbWapOrderGroup record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbWapOrderGroupMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbWapOrderGroupMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbWapOrderGroup> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbWapOrderGroupMapper.selectByObject", parameter);
    }

    @Override
    public List<WapOrderGroup> selectWapOrderGroupByObj(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbWapOrderGroupMapper.selectWapOrderGroupByObj", parameter);
    }
    
    public TbWapOrderGroup selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbWapOrderGroupMapper.selectByPrimaryKey", id);
        return obj != null ? (TbWapOrderGroup)obj : null;
    }
    
    public MyOrderStatesCount selectMyOrderStatesCount(Integer shopId){
   	 Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbWapOrderGroupMapper.selectMyOrderStatesCount", shopId);
        return obj != null ? (MyOrderStatesCount)obj : null;
   	
   }
    public List<WapOrderGroupDto> selectOrderGroupList(Criteria parameter,PageDto pgo){
    	Page<Integer> iDList = (Page)this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbWapOrderGroupMapper.selectOrderGroupIds", parameter,new RowBounds(pgo.pageIndex, pgo.pageSize));
    	 pgo.reset( (int) iDList.getTotal());
    	 if(iDList.size() == 0){
    		 return new ArrayList<WapOrderGroupDto>();
    	 }
    	 parameter.put("iDList",iDList );
    	 
    	 List<WapOrderGroupDto> orderGroupDtoList = this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbWapOrderGroupMapper.selectOrderGroupList", parameter);
        return orderGroupDtoList;
    }
    
    public OrderGroupDetailDto selectOrderGroupDetail(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbWapOrderGroupMapper.selectOrderGroupDetail", id);
        return obj != null ? (OrderGroupDetailDto)obj : null;
    }
    
    public List<WapOrderGroupDto> selectOrderGroupList(Criteria parameter){
    	List<WapOrderGroupDto> orderGroupDtoList = this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbWapOrderGroupMapper.selectOrderGroupList", parameter);
        return orderGroupDtoList;
    }
    
    public int updateSaleNumByOrderId(Integer orderGroupId){
    	return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbWapProductMapper.updateSaleNumByOrderId", orderGroupId);
    }
    public int updateOrderRebateById(List<Integer> orderIdList){
    	Criteria parameter = new Criteria();
    	parameter.put("orderIdList", orderIdList);
    	return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbWapOrderGroupMapper.updateOrderRebateById", parameter);
    }

    @Override
    public List<TbWapOrderGroup> findOrderGroupByOrderStatus(Criteria example) {
        
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbWapOrderGroupMapper.findOrderGroupByOrderStatus", example);
    }

    @Override
    public int updateOrderStatusByIds(Criteria example) {
        
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbWapOrderGroupMapper.updateOrderStatusByIds", example);
    }

	@Override
	public int selectUnCompleteOrder(Criteria example) {
		
		return this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbWapOrderGroupMapper.selectUnCompleteOrder", example);
	}
}