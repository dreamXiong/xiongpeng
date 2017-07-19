package com.liguo.hgl.proxydao.dao.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;
import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbIntegralMallMapper;
import com.liguo.hgl.proxydao.dto.IntegralMallDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbIntegralMall;
import com.liguo.hgl.proxydao.page.PageDto;

@Repository
public class TbIntegralMallMapperImpl extends BaseMapperImpl<TbIntegralMall> implements TbIntegralMallMapper {

    public TbIntegralMallMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbIntegralMallMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbIntegralMall record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbIntegralMallMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbIntegralMall record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbIntegralMallMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbIntegralMallMapper.deleteByObject", parameter);
    }

    public int insert(TbIntegralMall record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbIntegralMallMapper.insert", record);
    }

    public int insertSelective(TbIntegralMall record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbIntegralMallMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbIntegralMallMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbIntegralMall> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbIntegralMallMapper.selectByObject", parameter);
    }

    public IntegralMallDto selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbIntegralMallMapper.selectByPrimaryKey", id);
        return obj != null ? (IntegralMallDto)obj : null;
    }

	@Override
	public List<IntegralMallDto> selectByObject(Criteria example, PageDto pgo) {
        Page<IntegralMallDto> selectList = (Page)this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbIntegralMallMapper.selectByObject", example,new RowBounds(pgo.pageIndex, pgo.pageSize));
    	pgo.reset((int) selectList.getTotal());
        return selectList;
	}
	
}