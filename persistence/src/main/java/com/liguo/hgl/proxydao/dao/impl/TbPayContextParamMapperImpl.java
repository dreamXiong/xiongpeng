package com.liguo.hgl.proxydao.dao.impl;

import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbPayContextParamMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbPayContextParam;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class TbPayContextParamMapperImpl extends BaseMapperImpl<TbPayContextParam> implements TbPayContextParamMapper {

    public TbPayContextParamMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(String orderGroubNo) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbPayContextParamMapper.deleteByPrimaryKey", orderGroubNo);
    }

    public int updateByPrimaryKeySelective(TbPayContextParam record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbPayContextParamMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbPayContextParam record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbPayContextParamMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbPayContextParamMapper.deleteByObject", parameter);
    }

    public int insert(TbPayContextParam record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbPayContextParamMapper.insert", record);
    }

    public int insertSelective(TbPayContextParam record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbPayContextParamMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbPayContextParamMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbPayContextParam> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbPayContextParamMapper.selectByObject", parameter);
    }

    public TbPayContextParam selectByPrimaryKey(String orderGroubNo) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbPayContextParamMapper.selectByPrimaryKey", orderGroubNo);
        return obj != null ? (TbPayContextParam)obj : null;
    }
}