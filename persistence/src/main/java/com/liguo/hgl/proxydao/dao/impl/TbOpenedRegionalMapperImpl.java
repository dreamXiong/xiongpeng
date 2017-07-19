package com.liguo.hgl.proxydao.dao.impl;

import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbOpenedRegionalMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbCityInfo;
import com.liguo.hgl.proxydao.model.TbOpenedRegional;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class TbOpenedRegionalMapperImpl extends BaseMapperImpl<TbOpenedRegional> implements TbOpenedRegionalMapper {

    public TbOpenedRegionalMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbOpenedRegionalMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbOpenedRegional record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbOpenedRegionalMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbOpenedRegional record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbOpenedRegionalMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbOpenedRegionalMapper.deleteByObject", parameter);
    }

    public int insert(TbOpenedRegional record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbOpenedRegionalMapper.insert", record);
    }

    public int insertSelective(TbOpenedRegional record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbOpenedRegionalMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbOpenedRegionalMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbOpenedRegional> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbOpenedRegionalMapper.selectByObject", parameter);
    }

    public TbOpenedRegional selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbOpenedRegionalMapper.selectByPrimaryKey", id);
        return obj != null ? (TbOpenedRegional)obj : null;
    }

    @Override
    public List<TbOpenedRegional> findOpenedRegByproviceId(Criteria example) throws RuntimeException {
        
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbOpenedRegionalMapper.findOpenedRegByproviceId", example);
    }

	@Override
	public TbOpenedRegional selectByNameOpenCity(Criteria example) {
		 Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbOpenedRegionalMapper.selectByNameOpenCity", example);
	     return obj != null ? (TbOpenedRegional)obj : null;
	}
}