package com.liguo.hgl.proxydao.dao.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;
import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbIntegralMallTypeMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbIntegralMallType;
import com.liguo.hgl.proxydao.page.PageDto;

@Repository
public class TbIntegralMallTypeMapperImpl extends BaseMapperImpl<TbIntegralMallType> implements TbIntegralMallTypeMapper {

    public TbIntegralMallTypeMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbIntegralMallTypeMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbIntegralMallType record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbIntegralMallTypeMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbIntegralMallType record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbIntegralMallTypeMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbIntegralMallTypeMapper.deleteByObject", parameter);
    }

    public int insert(TbIntegralMallType record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbIntegralMallTypeMapper.insert", record);
    }

    public int insertSelective(TbIntegralMallType record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbIntegralMallTypeMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbIntegralMallTypeMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbIntegralMallType> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbIntegralMallTypeMapper.selectByObject", parameter);
    }

    public TbIntegralMallType selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbIntegralMallTypeMapper.selectByPrimaryKey", id);
        return obj != null ? (TbIntegralMallType)obj : null;
    }

	@Override
	public List<TbIntegralMallType> selectByObject(Criteria example, PageDto pgo) {
		 Page<TbIntegralMallType> selectList = (Page)this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbIntegralMallTypeMapper.selectByObject", example,new RowBounds(pgo.pageIndex, pgo.pageSize));
	     pgo.reset((int) selectList.getTotal());
	     return selectList;
	}
}