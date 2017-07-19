package com.liguo.hgl.proxydao.dao.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;
import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbProductInventoryMapper;
import com.liguo.hgl.proxydao.dto.TbProductInventoryDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbProductInventory;
import com.liguo.hgl.proxydao.page.PageDto;

@Repository
public class TbProductInventoryMapperImpl extends BaseMapperImpl<TbProductInventory> implements TbProductInventoryMapper {

    public TbProductInventoryMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbProductInventoryMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbProductInventory record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbProductInventoryMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbProductInventory record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbProductInventoryMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbProductInventoryMapper.deleteByObject", parameter);
    }

    public int insert(TbProductInventory record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbProductInventoryMapper.insert", record);
    }

    public int insertSelective(TbProductInventory record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbProductInventoryMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbProductInventoryMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbProductInventory> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbProductInventoryMapper.selectByObject", parameter);
    }

    public TbProductInventory selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbProductInventoryMapper.selectByPrimaryKey", id);
        return obj != null ? (TbProductInventory)obj : null;
    }

	@Override
	public TbProductInventoryDto selectById(Integer id) {
		 Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbProductInventoryMapper.selectById", id);
	        return obj != null ? (TbProductInventoryDto)obj : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TbProductInventoryDto> selectByCriteria(Criteria criteria,
			PageDto page) {
		Page<TbProductInventoryDto>selectList=(Page)this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbProductInventoryMapper.selectByCriteria", criteria,new RowBounds(page.pageIndex, page.pageSize));
		page.reset((int) selectList.getTotal());
		return selectList;
	}

	@Override
	public int markDeleteByPrimaryKey(Criteria parameter) {
		return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbProductInventoryMapper.markDeleteByPrimaryKey", parameter);
	}

	@Override
	public List<TbProductInventoryDto> selectByCriteria(Criteria criteria) {
		return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbProductInventoryMapper.selectByCriteria", criteria);
	}

	@Override
	public int batchUpdatePrice(Criteria parameter) {
		return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbProductInventoryMapper.batchUpdatePrice", parameter);
	}

	@Override
	public List<TbProductInventory> selectByPrimaryKeys(List<Integer> ids) {
		List<TbProductInventory> obj = this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbProductInventoryMapper.selectByPrimaryKeys", ids);
	    return obj;
	}
}