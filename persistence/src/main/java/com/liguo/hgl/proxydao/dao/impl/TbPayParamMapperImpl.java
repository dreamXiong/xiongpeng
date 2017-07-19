package com.liguo.hgl.proxydao.dao.impl;

import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbPayParamMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbPayParam;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class TbPayParamMapperImpl extends BaseMapperImpl<TbPayParam> implements TbPayParamMapper {

    public TbPayParamMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(String orderGroubNo) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbPayParamMapper.deleteByPrimaryKey", orderGroubNo);
    }

    public int updateByPrimaryKeySelective(TbPayParam record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbPayParamMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbPayParam record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbPayParamMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbPayParamMapper.deleteByObject", parameter);
    }

    public int insert(TbPayParam record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbPayParamMapper.insert", record);
    }

    public int insertSelective(TbPayParam record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbPayParamMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbPayParamMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbPayParam> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbPayParamMapper.selectByObject", parameter);
    }

    public TbPayParam selectByPrimaryKey(String orderGroubNo) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbPayParamMapper.selectByPrimaryKey", orderGroubNo);
        return obj != null ? (TbPayParam)obj : null;
    }
}