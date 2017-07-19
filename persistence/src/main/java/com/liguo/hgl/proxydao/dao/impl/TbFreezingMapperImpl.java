package com.liguo.hgl.proxydao.dao.impl;

import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbFreezingMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbFreezing;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class TbFreezingMapperImpl extends BaseMapperImpl<TbFreezing> implements TbFreezingMapper {

    public TbFreezingMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbFreezingMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbFreezing record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbFreezingMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbFreezing record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbFreezingMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbFreezingMapper.deleteByObject", parameter);
    }

    public int insert(TbFreezing record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbFreezingMapper.insert", record);
    }

    public int insertSelective(TbFreezing record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbFreezingMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbFreezingMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbFreezing> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbFreezingMapper.selectByObject", parameter);
    }

    public TbFreezing selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbFreezingMapper.selectByPrimaryKey", id);
        return obj != null ? (TbFreezing)obj : null;
    }
}