package com.liguo.hgl.proxydao.dao.impl;

import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbCountryInfoMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbCityInfo;
import com.liguo.hgl.proxydao.model.TbCountryInfo;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class TbCountryInfoMapperImpl extends BaseMapperImpl<TbCountryInfo> implements TbCountryInfoMapper {

    public TbCountryInfoMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbCountryInfoMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbCountryInfo record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbCountryInfoMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbCountryInfo record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbCountryInfoMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbCountryInfoMapper.deleteByObject", parameter);
    }

    public int insert(TbCountryInfo record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbCountryInfoMapper.insert", record);
    }

    public int insertSelective(TbCountryInfo record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbCountryInfoMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbCountryInfoMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbCountryInfo> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbCountryInfoMapper.selectByObject", parameter);
    }

    public TbCountryInfo selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbCountryInfoMapper.selectByPrimaryKey", id);
        return obj != null ? (TbCountryInfo)obj : null;
    }

    @Override
    public List<TbCityInfo> findBatchCountryById(Criteria example) throws RuntimeException {
        
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbCountryInfoMapper.findBatchCountryById", example);
    }
}