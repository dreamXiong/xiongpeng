package com.liguo.hgl.proxydao.dao.impl;

import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbRecommendedMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbRecommended;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class TbRecommendedMapperImpl extends BaseMapperImpl<TbRecommended> implements TbRecommendedMapper {

    public TbRecommendedMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbRecommendedMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbRecommended record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbRecommendedMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbRecommended record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbRecommendedMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbRecommendedMapper.deleteByObject", parameter);
    }

    public int insert(TbRecommended record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbRecommendedMapper.insert", record);
    }

    public int insertSelective(TbRecommended record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbRecommendedMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbRecommendedMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbRecommended> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbRecommendedMapper.selectByObject", parameter);
    }

    public TbRecommended selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbRecommendedMapper.selectByPrimaryKey", id);
        return obj != null ? (TbRecommended)obj : null;
    }
}