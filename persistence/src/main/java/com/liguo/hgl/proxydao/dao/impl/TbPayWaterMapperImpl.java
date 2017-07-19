package com.liguo.hgl.proxydao.dao.impl;

import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbPayWaterMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbPayWater;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class TbPayWaterMapperImpl extends BaseMapperImpl<TbPayWater> implements TbPayWaterMapper {

    public TbPayWaterMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbPayWaterMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbPayWater record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbPayWaterMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbPayWater record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbPayWaterMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbPayWaterMapper.deleteByObject", parameter);
    }

    public int insert(TbPayWater record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbPayWaterMapper.insert", record);
    }

    public int insertSelective(TbPayWater record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbPayWaterMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbPayWaterMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbPayWater> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbPayWaterMapper.selectByObject", parameter);
    }

    public TbPayWater selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbPayWaterMapper.selectByPrimaryKey", id);
        return obj != null ? (TbPayWater)obj : null;
    }
}