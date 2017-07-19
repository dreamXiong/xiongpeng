package com.liguo.hgl.proxydao.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;
import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbWapProductMapper;
import com.liguo.hgl.proxydao.dto.TbWapProductDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.ProductInfoDto;
import com.liguo.hgl.proxydao.model.TbWapProduct;
import com.liguo.hgl.proxydao.model.WapProductInfoDto;
import com.liguo.hgl.proxydao.page.PageDto;

@Repository
public class TbWapProductMapperImpl extends BaseMapperImpl<TbWapProduct> implements TbWapProductMapper {

    public TbWapProductMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbWapProductMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbWapProduct record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbWapProductMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbWapProduct record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbWapProductMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbWapProductMapper.deleteByObject", parameter);
    }

    public int insert(TbWapProduct record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbWapProductMapper.insert", record);
    }

    public int insertSelective(TbWapProduct record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbWapProductMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbWapProductMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbWapProduct> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbWapProductMapper.selectByObject", parameter);
    }

    public TbWapProduct selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbWapProductMapper.selectByPrimaryKey", id);
        return obj != null ? (TbWapProduct)obj : null;
    }

	@Override
	public TbWapProductDto selectWapProductDtoByPrimaryKey(Integer id) {
		Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbWapProductMapper.selectWapProductDtoByPrimaryKey", id);
		return obj != null ? (TbWapProductDto)obj : null;
	}
	
	@Override
	public List<TbWapProductDto> selectByCriteria(Criteria criteria,PageDto page) {
		Page<TbWapProductDto>selectList=(Page)this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbWapProductMapper.selectByCriteria", criteria,new RowBounds(page.pageIndex, page.pageSize));
		page.reset((int) selectList.getTotal());
		return selectList;
	}
	
	@Override
	public List<TbWapProductDto> selectByCriteria(Criteria criteria) {
		return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbWapProductMapper.selectByCriteria", criteria);
	}

	@Override
	public List<WapProductInfoDto> selectInfoListByName(Criteria criteria,PageDto page) {
		Page<WapProductInfoDto> selectList=(Page)this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbWapProductMapper.selectInfoListByName", criteria,new RowBounds(page.pageIndex, page.pageSize));
		page.reset((int) selectList.getTotal());
	    return selectList;
	}
	
	public WapProductInfoDto selectUpdateInfo(Criteria criteria){
   	 	Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbWapProductMapper.selectUpdateInfo", criteria);
        return obj != null ? (WapProductInfoDto)obj : null;
   }
	public List<TbWapProductDto> selectTbWapPickList(Criteria parameter){
		 return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbWapProductMapper.selectTbWapPickList", parameter);
	}

    @Override
    public void insertBatchProduct(List<TbWapProduct> objList) {
        this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbWapProductMapper.insertBatchProduct", objList);
    }
	
	
}