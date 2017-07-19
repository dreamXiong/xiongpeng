package com.liguo.hgl.proxydao.dao.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbProductTypeMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.ProductImport;
import com.liguo.hgl.proxydao.model.TbProductType;

@Repository
public class TbProductTypeMapperImpl extends BaseMapperImpl<TbProductType> implements TbProductTypeMapper{

    public TbProductTypeMapperImpl() {
        super();
    }

	@Override
	public int countByExample(Criteria example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteByExample(Criteria example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbProductTypeMapper.deleteByPrimaryKey", id);
	}

	@Override
	public int insert(TbProductType record) {
		return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbProductTypeMapper.insert", record);
	}

	@Override
	public int insertSelective(TbProductType record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<TbProductType> selectByExample(Criteria parameter) {
		return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbProductTypeMapper.selectByCriteria", parameter);
	}

	@Override
	public TbProductType selectByPrimaryKey(Integer id) {
		 Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbProductTypeMapper.selectByPrimaryKey", id);
    return obj != null ? (TbProductType)obj : null;
	}

	@Override
	public int updateByExampleSelective(@Param("record") TbProductType record,
			@Param("example") Criteria example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByExample(@Param("record") TbProductType record,
			@Param("example") Criteria example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKeySelective(TbProductType record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(TbProductType record) {
		int ret = this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbProductTypeMapper.updateByPrimaryKey", record);
		return ret;
	}
	@Override
	public int updateProductTyppeByPrimaryKeyArray(Criteria parameter){
		int ret = this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbProductTypeMapper.updateByPrimaryKeyArray", parameter);
		return ret;
	}

	@Override
	public List<TbProductType> selectProductTypeByMinId(Integer id) {
		List<TbProductType> productTypes = this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbProductTypeMapper.selectProductTypeByMinId", id);
		
		return productTypes;
	}

    @Override
    public List<ProductImport> findNotParentId(Criteria criteria) {
        
       return  this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbProductTypeMapper.findNotParentId",criteria);
    }

    @Override
    public int findNotParentIdCount() {
        
          Object  obj =this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbProductTypeMapper.findNotParentIdCount");
          return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

	@Override
	public List<TbProductType> selectNearShopType() {
		List<TbProductType> productTypes = this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbProductTypeMapper.selectNearShopType");
		return productTypes;
	}

}