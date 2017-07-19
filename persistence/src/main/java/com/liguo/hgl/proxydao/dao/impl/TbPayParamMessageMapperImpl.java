package com.liguo.hgl.proxydao.dao.impl;

import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbPayParamMessageMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbPayParamMessage;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class TbPayParamMessageMapperImpl extends BaseMapperImpl<TbPayParamMessage> implements TbPayParamMessageMapper {

    public TbPayParamMessageMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbPayParamMessageMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbPayParamMessage record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbPayParamMessageMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbPayParamMessage record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbPayParamMessageMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbPayParamMessageMapper.deleteByObject", parameter);
    }

    public int insert(TbPayParamMessage record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbPayParamMessageMapper.insert", record);
    }

    public int insertSelective(TbPayParamMessage record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbPayParamMessageMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbPayParamMessageMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbPayParamMessage> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbPayParamMessageMapper.selectByObject", parameter);
    }

    public TbPayParamMessage selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbPayParamMessageMapper.selectByPrimaryKey", id);
        return obj != null ? (TbPayParamMessage)obj : null;
    }
}