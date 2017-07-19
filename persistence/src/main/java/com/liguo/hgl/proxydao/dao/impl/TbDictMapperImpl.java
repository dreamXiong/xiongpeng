package com.liguo.hgl.proxydao.dao.impl;

import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbDictMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbDict;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class TbDictMapperImpl extends BaseMapperImpl<TbDict> implements TbDictMapper {

    public TbDictMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbDictMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbDict record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbDictMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbDict record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbDictMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbDictMapper.deleteByObject", parameter);
    }

    public int insert(TbDict record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbDictMapper.insert", record);
    }

    public int insertSelective(TbDict record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbDictMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbDictMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbDict> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbDictMapper.selectByObject", parameter);
    }

    public TbDict selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbDictMapper.selectByPrimaryKey", id);
        return obj != null ? (TbDict)obj : null;
    }
}