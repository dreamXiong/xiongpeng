package com.liguo.hgl.proxydao.dao.impl;

import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbProgramPraiseMapper;
import com.liguo.hgl.proxydao.exception.DataAlreadyModifyException;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbProgramPraise;

import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class TbProgramPraiseMapperImpl extends BaseMapperImpl<TbProgramPraise> implements TbProgramPraiseMapper {

    public TbProgramPraiseMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbProgramPraiseMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbProgramPraise record) throws DataAlreadyModifyException {
        int ret = this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbProgramPraiseMapper.updateByPrimaryKeySelective", record);
        if(ret > 0){
            return ret;
        }
        throw new DataAlreadyModifyException("数据实体已经被修改");
    }

    public int updateByPrimaryKey(TbProgramPraise record) throws DataAlreadyModifyException {
        int ret = this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbProgramPraiseMapper.updateByPrimaryKey", record);
        if(ret > 0){
            return ret;
        }
        throw new DataAlreadyModifyException("数据实体已经被修改");
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbProgramPraiseMapper.deleteByObject", parameter);
    }

    public int insert(TbProgramPraise record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbProgramPraiseMapper.insert", record);
    }

    public int insertSelective(TbProgramPraise record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbProgramPraiseMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbProgramPraiseMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbProgramPraise> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbProgramPraiseMapper.selectByObject", parameter);
    }

    public TbProgramPraise selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbProgramPraiseMapper.selectByPrimaryKey", id);
        return obj != null ? (TbProgramPraise)obj : null;
    }
}