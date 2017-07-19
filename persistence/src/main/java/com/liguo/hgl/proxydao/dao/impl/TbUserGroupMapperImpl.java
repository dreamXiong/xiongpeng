package com.liguo.hgl.proxydao.dao.impl;

import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbUserGroupMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbUserGroup;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class TbUserGroupMapperImpl extends BaseMapperImpl<TbUserGroup> implements TbUserGroupMapper {

    public TbUserGroupMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbUserGroupMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbUserGroup record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbUserGroupMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbUserGroup record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbUserGroupMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbUserGroupMapper.deleteByObject", parameter);
    }

    public int insert(TbUserGroup record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbUserGroupMapper.insert", record);
    }

    public int insertSelective(TbUserGroup record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbUserGroupMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbUserGroupMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbUserGroup> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbUserGroupMapper.selectByObject", parameter);
    }

    public TbUserGroup selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbUserGroupMapper.selectByPrimaryKey", id);
        return obj != null ? (TbUserGroup)obj : null;
    }

	@Override
	public List<TbUserGroup> selectByGroup(Criteria example) {
		return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbUserGroupMapper.selectByGroup", example);
	}

	@Override
	public List<TbUserGroup> selectByShopGroup(Criteria example) {
		return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbUserGroupMapper.selectByShopGroup", example);
	}
}