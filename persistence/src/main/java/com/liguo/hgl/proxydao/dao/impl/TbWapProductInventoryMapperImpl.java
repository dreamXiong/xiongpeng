package com.liguo.hgl.proxydao.dao.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;
import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbWapProductInventoryMapper;
import com.liguo.hgl.proxydao.dto.TbWapProductInventoryDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.ProductImport;
import com.liguo.hgl.proxydao.model.TbWapProductInventory;
import com.liguo.hgl.proxydao.page.PageDto;

@Repository
public class TbWapProductInventoryMapperImpl extends BaseMapperImpl<TbWapProductInventory> implements TbWapProductInventoryMapper {

    public TbWapProductInventoryMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbWapProductInventoryMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbWapProductInventory record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbWapProductInventoryMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbWapProductInventory record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbWapProductInventoryMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbWapProductInventoryMapper.deleteByObject", parameter);
    }

    public int insert(TbWapProductInventory record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbWapProductInventoryMapper.insert", record);
    }

    public int insertSelective(TbWapProductInventory record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbWapProductInventoryMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbWapProductInventoryMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbWapProductInventory> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbWapProductInventoryMapper.selectByObject", parameter);
    }

    public TbWapProductInventory selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbWapProductInventoryMapper.selectByPrimaryKey", id);
        return obj != null ? (TbWapProductInventory)obj : null;
    }

	@Override
	public List<TbWapProductInventoryDto> selectByCriteria(Criteria criteria) throws RuntimeException {
		return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbWapProductInventoryMapper.selectByCriteria", criteria);
	}

	@Override
	public int batchUpOrdownFrame(Criteria parameter) throws RuntimeException {
		 return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbWapProductInventoryMapper.batchUpOrdownFrame", parameter);
	}

	@Override
	public List<TbWapProductInventoryDto> selectByCriteria(Criteria criteria,PageDto page) throws RuntimeException {
		Page<TbWapProductInventoryDto>selectList=(Page)this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbWapProductInventoryMapper.selectByCriteria", criteria,new RowBounds(page.pageIndex, page.pageSize));
		page.reset((int) selectList.getTotal());
		return selectList;
	}

	@Override
	public int markDeleteByPrimaryKey(Criteria parameter)
			throws RuntimeException {
		return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbWapProductInventoryMapper.markDeleteByPrimaryKey", parameter);
	}
	
	@Override
	public TbWapProductInventoryDto selectById(Integer id) {
		 Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbWapProductInventoryMapper.selectById", id);
	     return obj != null ? (TbWapProductInventoryDto)obj : null;
	}
	
	@Override
	public int batchUpdatePrice(Criteria parameter) {
		return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbWapProductInventoryMapper.batchUpdatePrice", parameter);
	}
	@Override
	public int batchInsertSelective(List<TbWapProductInventory> twis){
		return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbWapProductInventoryMapper.batchInsertSelective", twis);
	}
	@Override
	public int insertBatchInventory(List<TbWapProductInventory> twis){
	    return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbWapProductInventoryMapper.insertBatchInventory", twis);
	}
	@Override
	public int batchUpdateStatus(Criteria parameter) {
		return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbWapProductInventoryMapper.batchUpdateStatus", parameter);
	}

    @Override
    public List<ProductImport> findProNameAndCodeLimit(Criteria criteria) {
        
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbWapProductInventoryMapper.findProNameAndCodeLimit",criteria);
    }

	@Override
	public TbWapProductInventoryDto selectByProductNameCriteria(Criteria criteria) {
		 Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbWapProductInventoryMapper.selectByProductNameCriteria", criteria);
	     return obj != null ? (TbWapProductInventoryDto)obj : null;
	}
}