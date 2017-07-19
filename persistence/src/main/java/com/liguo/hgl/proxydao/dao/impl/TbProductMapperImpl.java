package com.liguo.hgl.proxydao.dao.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;
import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbProductMapper;
import com.liguo.hgl.proxydao.dto.TbProductDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.ProductInfoDto;
import com.liguo.hgl.proxydao.model.TbProduct;
import com.liguo.hgl.proxydao.model.TbProductTestDto;
import com.liguo.hgl.proxydao.page.PageDto;

@Repository
public class TbProductMapperImpl extends BaseMapperImpl<TbProduct> implements TbProductMapper {

    public TbProductMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbProductMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbProduct record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbProductMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbProduct record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbProductMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbProductMapper.deleteByObject", parameter);
    }

    public int insert(TbProduct record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbProductMapper.insert", record);
    }
  
    public int insertSelective(TbProduct record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbProductMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbProductMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbProduct> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbProductMapper.selectByObject", parameter);
    }

    public TbProduct selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbProductMapper.selectByPrimaryKey", id);
        return obj != null ? (TbProduct)obj : null;
    }
    @Override
    public List<ProductInfoDto> selectInfoListByName(Criteria parameter, PageDto page){
    	Page<ProductInfoDto> selectList=(Page)this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbProductMapper.selectInfoListByName", parameter,new RowBounds(page.pageIndex, page.pageSize));
		 page.reset((int) selectList.getTotal());
		 return selectList;
    }
    
	@Override
	public List<TbProductDto> selectByCriteria(Criteria criteria, PageDto page) {
		 Page<TbProductDto> selectList=(Page)this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbProductMapper.selectByCriteria", criteria,new RowBounds(page.pageIndex, page.pageSize));
		 page.reset((int) selectList.getTotal());
		 return selectList;
	}
	
	@Override
	public List<TbProductDto> selectByCriteria(Criteria criteria) {
		return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbProductMapper.selectByCriteria", criteria);
	}
	
	public ProductInfoDto selectUpdateInfo(Integer id){
    	 Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbProductMapper.selectUpdateInfo", id);
         return obj != null ? (ProductInfoDto)obj : null;
    }

	@Override
	public TbProductDto selectProductDtoByPrimaryKey(Integer id) {
		Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbProductMapper.selectProductDtoByPrimaryKey", id);
        return obj != null ? (TbProductDto)obj : null;
	}
	public List<TbProductTestDto> selectTest(){
		 return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbProductMapper.selectByTest");
	}
	 public Integer selectShopIdByPId(Integer productId){
		 Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbProductMapper.selectShopIdByPId", productId);
		 return (Integer)obj;
	 }
}