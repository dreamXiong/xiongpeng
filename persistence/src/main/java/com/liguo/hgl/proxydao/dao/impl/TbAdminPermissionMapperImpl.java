package com.liguo.hgl.proxydao.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbAdminPermissionMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbAdminPermission;

@Repository
public class TbAdminPermissionMapperImpl extends BaseMapperImpl<TbAdminPermission> implements TbAdminPermissionMapper {

    public TbAdminPermissionMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbAdminPermissionMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbAdminPermission record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbAdminPermissionMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbAdminPermission record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbAdminPermissionMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbAdminPermissionMapper.deleteByObject", parameter);
    }

    public int insert(TbAdminPermission record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbAdminPermissionMapper.insert", record);
    }

    public int insertSelective(TbAdminPermission record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbAdminPermissionMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbAdminPermissionMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbAdminPermission> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbAdminPermissionMapper.selectByObject", parameter);
    }

    public TbAdminPermission selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbAdminPermissionMapper.selectByPrimaryKey", id);
        return obj != null ? (TbAdminPermission)obj : null;
    }
    
    public List<TbAdminPermission> selectByStringList(List<String> strList) {
         return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbAdminPermissionMapper.selectByStringList", strList);
    }

    @Override
    public List<Map<String, Object>> selectByObjectToMap(Criteria example) {
        
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbAdminPermissionMapper.selectByObjectToMap", example);
    }

	@Override
	public TbAdminPermission selectByURL(Criteria criteria) {
		
		return this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbAdminPermissionMapper.selectByURL", criteria);
	}
}