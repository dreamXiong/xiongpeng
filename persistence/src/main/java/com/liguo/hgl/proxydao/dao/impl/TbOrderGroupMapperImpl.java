package com.liguo.hgl.proxydao.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;
import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbOrderGroupMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.MyOrderStatesCount;
import com.liguo.hgl.proxydao.model.OrderGroupDto;
import com.liguo.hgl.proxydao.model.TbOrderGroup;
import com.liguo.hgl.proxydao.page.PageDto;

@Repository
public class TbOrderGroupMapperImpl extends BaseMapperImpl<TbOrderGroup> implements TbOrderGroupMapper {

    public TbOrderGroupMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbOrderGroupMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbOrderGroup record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbOrderGroupMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbOrderGroup record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbOrderGroupMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbOrderGroupMapper.deleteByObject", parameter);
    }

    public int insert(TbOrderGroup record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbOrderGroupMapper.insert", record);
    }

    public int insertSelective(TbOrderGroup record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbOrderGroupMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbOrderGroupMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbOrderGroup> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbOrderGroupMapper.selectByObject", parameter);
    }

    public TbOrderGroup selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbOrderGroupMapper.selectByPrimaryKey", id);
        return obj != null ? (TbOrderGroup)obj : null;
    }
    
    public List<OrderGroupDto> selectOrderGroupList(Criteria parameter,PageDto pgo){
    	Page<Integer> iDList = (Page)this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbOrderGroupMapper.selectOrderGroupIds", parameter,new RowBounds(pgo.pageIndex, pgo.pageSize));
    	 pgo.reset( (int) iDList.getTotal());
    	 if(iDList.size() == 0){
    		 return new ArrayList<OrderGroupDto>();
    	 }
    	 parameter.put("iDList",iDList );
    	 List<OrderGroupDto> orderGroupDtoList = this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbOrderGroupMapper.selectOrderGroupList", parameter);
        return orderGroupDtoList;
    }
    public MyOrderStatesCount selectMyOrderStatesCount(Integer shopId){
    	 Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbOrderGroupMapper.selectMyOrderStatesCount", shopId);
         return obj != null ? (MyOrderStatesCount)obj : null;
    	
    }
    
    public MyOrderStatesCount selectMyOrderStatesCountAdmin(List<Integer> shopIdList){
    	Criteria parameter = new Criteria();
    	parameter.put("shopIdList", shopIdList);
   	 	Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbOrderGroupMapper.selectMyOrderStatesCountAdmin", parameter);
        return obj != null ? (MyOrderStatesCount)obj : null;
   	
   }

	@Override
	public int batchInsert(List<TbOrderGroup> record) {
		 return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbOrderGroupMapper.batchInsert", record);
	}

	@Override
	public List<OrderGroupDto> selectObjectByPage(Criteria parameter,
			PageDto pgo) {
		Page<OrderGroupDto> selectList = (Page)this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbOrderGroupMapper.selectObjectByPage", parameter, new RowBounds(pgo.pageIndex,pgo.pageSize));
		pgo.reset((int)selectList.getTotal());
		
		return selectList;
	}

	@Override
	public OrderGroupDto selectObjectByPrimaryKey(Integer id) {
		
		return this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbOrderGroupMapper.selectObjectByPrimaryKey", id);
	}
	
	
	@Override
	public TbOrderGroup selectByOrderNumber(String orderNumber) {
		Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbOrderGroupMapper.selectByOrderNumber", orderNumber);
        return obj != null ? (TbOrderGroup)obj : null;
	}

    @Override
    public List<TbOrderGroup> findOrderGroupByOrderStatus(Criteria example) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbOrderGroupMapper.findOrderGroupByOrderStatus", example);
    }

    @Override
    public int updateOrderStatusByIds(Criteria example) {
        
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbOrderGroupMapper.updateOrderStatusByIds", example);
        
    }

}