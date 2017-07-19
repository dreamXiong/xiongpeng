package com.liguo.hgl.proxydao.dao.impl;

import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbRecommendedTypeMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbRecommendedType;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class TbRecommendedTypeMapperImpl extends BaseMapperImpl<TbRecommendedType> implements TbRecommendedTypeMapper {

    public TbRecommendedTypeMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbRecommendedTypeMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbRecommendedType record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbRecommendedTypeMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbRecommendedType record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbRecommendedTypeMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbRecommendedTypeMapper.deleteByObject", parameter);
    }

    public int insert(TbRecommendedType record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbRecommendedTypeMapper.insert", record);
    }

    public int insertSelective(TbRecommendedType record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbRecommendedTypeMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbRecommendedTypeMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbRecommendedType> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbRecommendedTypeMapper.selectByObject", parameter);
    }

    public TbRecommendedType selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbRecommendedTypeMapper.selectByPrimaryKey", id);
        return obj != null ? (TbRecommendedType)obj : null;
    }
}