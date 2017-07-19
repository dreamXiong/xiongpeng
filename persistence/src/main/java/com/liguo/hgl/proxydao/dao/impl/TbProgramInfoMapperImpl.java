package com.liguo.hgl.proxydao.dao.impl;

import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbProgramInfoMapper;
import com.liguo.hgl.proxydao.exception.DataAlreadyModifyException;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbProgramInfo;

import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class TbProgramInfoMapperImpl extends BaseMapperImpl<TbProgramInfo> implements TbProgramInfoMapper {

    public TbProgramInfoMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbProgramInfoMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbProgramInfo record) throws DataAlreadyModifyException {
        int ret = this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbProgramInfoMapper.updateByPrimaryKeySelective", record);
        if(ret > 0){
            return ret;
        }
        throw new DataAlreadyModifyException("数据实体已经被修改");
    }

    public int updateByPrimaryKey(TbProgramInfo record) throws DataAlreadyModifyException {
        int ret = this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbProgramInfoMapper.updateByPrimaryKey", record);
        if(ret > 0){
            return ret;
        }
        throw new DataAlreadyModifyException("数据实体已经被修改");
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbProgramInfoMapper.deleteByObject", parameter);
    }

    public int insert(TbProgramInfo record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbProgramInfoMapper.insert", record);
    }

    public int insertSelective(TbProgramInfo record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbProgramInfoMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbProgramInfoMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbProgramInfo> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbProgramInfoMapper.selectByObject", parameter);
    }

    public TbProgramInfo selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbProgramInfoMapper.selectByPrimaryKey", id);
        return obj != null ? (TbProgramInfo)obj : null;
    }
}