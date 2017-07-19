package com.liguo.hgl.proxydao.dao.impl;

import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbCityInfoMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbCityInfo;
import com.liguo.hgl.proxydao.model.TbOpenedRegional;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class TbCityInfoMapperImpl extends BaseMapperImpl<TbCityInfo> implements TbCityInfoMapper {

    public TbCityInfoMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbCityInfoMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbCityInfo record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbCityInfoMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbCityInfo record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbCityInfoMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbCityInfoMapper.deleteByObject", parameter);
    }

    public int insert(TbCityInfo record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbCityInfoMapper.insert", record);
    }

    public int insertSelective(TbCityInfo record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbCityInfoMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbCityInfoMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbCityInfo> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbCityInfoMapper.selectByObject", parameter);
    }

    public TbCityInfo selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbCityInfoMapper.selectByPrimaryKey", id);
        return obj != null ? (TbCityInfo)obj : null;
    }

    @Override
    public List<Map<String, Object>> findCityByProvice(Integer id) {
        
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbCityInfoMapper.findCityByProvice", id);
    }

    @Override
    public List<TbCityInfo> findBatchCityById(List<String> tbOpenedRegionalList) throws RuntimeException {
        
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbCityInfoMapper.findBatchCityById", tbOpenedRegionalList);
    }

    @Override
    public String searchCity(Criteria example) {
        return  this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbCityInfoMapper.searchCity", example);
    }
}