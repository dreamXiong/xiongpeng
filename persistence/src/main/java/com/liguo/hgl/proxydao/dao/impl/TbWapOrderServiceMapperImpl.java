package com.liguo.hgl.proxydao.dao.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;
import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbWapOrderServiceMapper;
import com.liguo.hgl.proxydao.dto.WapOrderServiceDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbWapOrderService;
import com.liguo.hgl.proxydao.page.PageDto;

@Repository
public class TbWapOrderServiceMapperImpl extends BaseMapperImpl<TbWapOrderService> implements TbWapOrderServiceMapper {

    public TbWapOrderServiceMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbWapOrderServiceMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbWapOrderService record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbWapOrderServiceMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbWapOrderService record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbWapOrderServiceMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbWapOrderServiceMapper.deleteByObject", parameter);
    }

    public int insert(TbWapOrderService record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbWapOrderServiceMapper.insert", record);
    }

    public int insertSelective(TbWapOrderService record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbWapOrderServiceMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbWapOrderServiceMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbWapOrderService> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbWapOrderServiceMapper.selectByObject", parameter);
    }

    public TbWapOrderService selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbWapOrderServiceMapper.selectByPrimaryKey", id);
        return obj != null ? (TbWapOrderService)obj : null;
    }

	@Override
	public List<WapOrderServiceDto> selectByCriteria(Criteria parameter) {
		// TODO Auto-generated method stub
	    
		 return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbWapOrderServiceMapper.selectByCriteria", parameter);
	}
	@Override
	public List<WapOrderServiceDto> selectByCriteria(Criteria parameter,PageDto page) {
	    // TODO Auto-generated method stub
	    
	     Page<WapOrderServiceDto> selectList =(Page)this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbWapOrderServiceMapper.selectByCriteria", parameter, new RowBounds(page.getPageIndex(), page.getPageSize()));
	     int total = (int)selectList.getTotal();
	     page.reset(total);
	     return selectList; 
	}

	@Override
	public int selectUnCompleteOrderCount(Criteria criteria) {
		
		return this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbWapOrderServiceMapper.selectUnCompleteOrderCount", criteria);
	}
	
	@Override
	public int findServiceTypeIdByOrderId(Criteria criteria) {
	    
	    return this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbWapOrderServiceMapper.findServiceTypeIdByOrderId", criteria);
	}
}