package com.liguo.hgl.proxydao.dao.impl;

import java.util.List;

import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbWeixinUserMapper;
import com.liguo.hgl.proxydao.exception.DataAlreadyModifyException;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbWeixinUser;

import org.springframework.stereotype.Repository;

@Repository
public class TbWeixinUserMapperImpl extends BaseMapperImpl<TbWeixinUser> implements TbWeixinUserMapper {

    public TbWeixinUserMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbWeixinUserMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbWeixinUser record) throws DataAlreadyModifyException {
        int ret = this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbWeixinUserMapper.updateByPrimaryKeySelective", record);
        if(ret > 0){
            return ret;
        }
        throw new DataAlreadyModifyException("数据实体已经被修改");
    }

    public int updateByPrimaryKey(TbWeixinUser record) throws DataAlreadyModifyException {
        int ret = this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbWeixinUserMapper.updateByPrimaryKey", record);
        if(ret > 0){
            return ret;
        }
        throw new DataAlreadyModifyException("数据实体已经被修改");
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbWeixinUserMapper.deleteByObject", parameter);
    }

    public int insert(TbWeixinUser record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbWeixinUserMapper.insert", record);
    }

    public int insertSelective(TbWeixinUser record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbWeixinUserMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbWeixinUserMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbWeixinUser> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbWeixinUserMapper.selectByObject", parameter);
    }

    public TbWeixinUser selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbWeixinUserMapper.selectByPrimaryKey", id);
        return obj != null ? (TbWeixinUser)obj : null;
    }
}