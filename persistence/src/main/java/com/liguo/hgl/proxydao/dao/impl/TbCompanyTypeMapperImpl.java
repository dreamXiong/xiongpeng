package com.liguo.hgl.proxydao.dao.impl;

import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbCompanyTypeMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbCompanyType;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class TbCompanyTypeMapperImpl extends BaseMapperImpl<TbCompanyType> implements TbCompanyTypeMapper {

    public TbCompanyTypeMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbCompanyTypeMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbCompanyType record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbCompanyTypeMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbCompanyType record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbCompanyTypeMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbCompanyTypeMapper.deleteByObject", parameter);
    }

    public int insert(TbCompanyType record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbCompanyTypeMapper.insert", record);
    }

    public int insertSelective(TbCompanyType record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbCompanyTypeMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbCompanyTypeMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbCompanyType> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbCompanyTypeMapper.selectByObject", parameter);
    }

    public TbCompanyType selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbCompanyTypeMapper.selectByPrimaryKey", id);
        return obj != null ? (TbCompanyType)obj : null;
    }
    public List<TbCompanyType> selectCompanyTypeInfo(){
    	 return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbCompanyTypeMapper.selectCompanyTypeInfo");
    }
}