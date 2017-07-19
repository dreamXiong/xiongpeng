package com.liguo.hgl.proxydao.dao.impl;

import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbTalkMapper;
import com.liguo.hgl.proxydao.exception.DataAlreadyModifyException;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbTalk;

import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class TbTalkMapperImpl extends BaseMapperImpl<TbTalk> implements TbTalkMapper {

    public TbTalkMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbTalkMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbTalk record) throws DataAlreadyModifyException {
        int ret = this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbTalkMapper.updateByPrimaryKeySelective", record);
        if(ret > 0){
            return ret;
        }
        throw new DataAlreadyModifyException("数据实体已经被修改");
    }

    public int updateByPrimaryKey(TbTalk record) throws DataAlreadyModifyException {
        int ret = this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbTalkMapper.updateByPrimaryKey", record);
        if(ret > 0){
            return ret;
        }
        throw new DataAlreadyModifyException("数据实体已经被修改");
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbTalkMapper.deleteByObject", parameter);
    }

    public int insert(TbTalk record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbTalkMapper.insert", record);
    }

    public int insertSelective(TbTalk record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbTalkMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbTalkMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbTalk> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbTalkMapper.selectByObject", parameter);
    }

    public TbTalk selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbTalkMapper.selectByPrimaryKey", id);
        return obj != null ? (TbTalk)obj : null;
    }
}