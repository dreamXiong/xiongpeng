package com.liguo.hgl.proxydao.dao.impl;

import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbAdminRoleMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbAdminRole;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class TbAdminRoleMapperImpl extends BaseMapperImpl<TbAdminRole> implements TbAdminRoleMapper {

    public TbAdminRoleMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbAdminRoleMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbAdminRole record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbAdminRoleMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbAdminRole record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbAdminRoleMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbAdminRoleMapper.deleteByObject", parameter);
    }

    public int insert(TbAdminRole record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbAdminRoleMapper.insert", record);
    }

    public int insertSelective(TbAdminRole record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbAdminRoleMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbAdminRoleMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbAdminRole> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbAdminRoleMapper.selectByObject", parameter);
    }

    public TbAdminRole selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbAdminRoleMapper.selectByPrimaryKey", id);
        return obj != null ? (TbAdminRole)obj : null;
    }
}