package com.liguo.hgl.proxydao.dao.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;
import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbBrandMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.ProductImport;
import com.liguo.hgl.proxydao.model.TbBrand;
import com.liguo.hgl.proxydao.page.PageDto;

@Repository
public class TbBrandMapperImpl extends BaseMapperImpl<TbBrand> implements TbBrandMapper {

    public TbBrandMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbBrandMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbBrand record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbBrandMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbBrand record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbBrandMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbBrandMapper.deleteByObject", parameter);
    }

    public int insert(TbBrand record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbBrandMapper.insert", record);
    }

    public int insertSelective(TbBrand record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbBrandMapper.insertSelective", record);
    }
    
    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbBrandMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbBrand> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbBrandMapper.selectByObject", parameter);
    }
    @Override
    public List<TbBrand> selectByObject(Criteria parameter,PageDto pgo) {
    	Page<TbBrand> selectList = 
    			(Page)this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbBrandMapper.selectByObjectPage", parameter,new RowBounds(pgo.pageIndex, pgo.pageSize));
    	 pgo.reset((int) selectList.getTotal());
        return selectList;
    }
    public TbBrand selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbBrandMapper.selectByPrimaryKey", id);
        return obj != null ? (TbBrand)obj : null;
    }

    @Override
    public List<ProductImport> findBrandIdAll(Criteria example) {
        
       return  this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbBrandMapper.findBrandIdAll",example);
    }
}