package com.liguo.hgl.proxydao.dao.impl;

import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbWebPermissionMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbWebPermission;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class TbWebPermissionMapperImpl extends BaseMapperImpl<TbWebPermission> implements TbWebPermissionMapper {

    public TbWebPermissionMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbWebPermissionMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbWebPermission record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbWebPermissionMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbWebPermission record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbWebPermissionMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbWebPermissionMapper.deleteByObject", parameter);
    }

    public int insert(TbWebPermission record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbWebPermissionMapper.insert", record);
    }

    public int insertSelective(TbWebPermission record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbWebPermissionMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbWebPermissionMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbWebPermission> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbWebPermissionMapper.selectByObject", parameter);
    }

    public TbWebPermission selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbWebPermissionMapper.selectByPrimaryKey", id);
        return obj != null ? (TbWebPermission)obj : null;
    }

    @Override
    public List<Map<String, Object>> selectByObjectToMap(Criteria example) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbWebPermissionMapper.selectByObjectToMap", example);
    }

    @Override
    public List<TbWebPermission> selectByStringList(List<String> strList) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbWebPermissionMapper.selectByStringList", strList);
    }

	@Override
	public TbWebPermission selectByUrl(Criteria criteria) {
		
		return this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbWebPermissionMapper.selectByUrl", criteria);
	}
}