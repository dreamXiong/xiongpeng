package com.liguo.hgl.proxydao.dao.impl;

import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbAccountMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbAccount;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class TbAccountMapperImpl extends BaseMapperImpl<TbAccount> implements TbAccountMapper {

    public TbAccountMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbAccountMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbAccount record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbAccountMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbAccount record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbAccountMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbAccountMapper.deleteByObject", parameter);
    }

    public int insert(TbAccount record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbAccountMapper.insert", record);
    }

    public int insertSelective(TbAccount record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbAccountMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbAccountMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbAccount> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbAccountMapper.selectByObject", parameter);
    }

    public TbAccount selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbAccountMapper.selectByPrimaryKey", id);
        return obj != null ? (TbAccount)obj : null;
    }

	@Override
	public TbAccount selectAccountByUserId(Integer userId) {
		 Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbAccountMapper.selectAccountByUserId", userId);
	     return obj != null ? (TbAccount)obj : null;
	}
}