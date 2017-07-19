package com.liguo.hgl.proxydao.dao.impl;

import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbWebRoleMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbWebRole;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class TbWebRoleMapperImpl extends BaseMapperImpl<TbWebRole> implements TbWebRoleMapper {

    public TbWebRoleMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbWebRoleMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbWebRole record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbWebRoleMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbWebRole record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbWebRoleMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbWebRoleMapper.deleteByObject", parameter);
    }

    public int insert(TbWebRole record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbWebRoleMapper.insert", record);
    }

    public int insertSelective(TbWebRole record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbWebRoleMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbWebRoleMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbWebRole> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbWebRoleMapper.selectByObject", parameter);
    }

    public TbWebRole selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbWebRoleMapper.selectByPrimaryKey", id);
        return obj != null ? (TbWebRole)obj : null;
    }
}