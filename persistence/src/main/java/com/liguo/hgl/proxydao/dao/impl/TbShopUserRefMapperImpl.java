package com.liguo.hgl.proxydao.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbShopUserRefMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbShopUserRef;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class TbShopUserRefMapperImpl extends BaseMapperImpl<TbShopUserRef> implements TbShopUserRefMapper {

    public TbShopUserRefMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(TbShopUserRef key) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbShopUserRefMapper.deleteByPrimaryKey", key);
    }

    public int updateByPrimaryKeySelective(TbShopUserRef record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbShopUserRefMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbShopUserRef record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbShopUserRefMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbShopUserRefMapper.deleteByObject", parameter);
    }

    public int insert(TbShopUserRef record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbShopUserRefMapper.insert", record);
    }

    public int insertSelective(TbShopUserRef record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbShopUserRefMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbShopUserRefMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbShopUserRef> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbShopUserRefMapper.selectByObject", parameter);
    }

    public TbShopUserRef selectByPrimaryKey(TbShopUserRef key) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbShopUserRefMapper.selectByPrimaryKey", key);
        return obj != null ? (TbShopUserRef)obj : null;
    }

	@Override
	public List<Map<String, Object>> findNotUserList(TbShopUserRef record) {
		return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbShopUserRefMapper.findNotUserList", record);
	}
    
    public List<Integer> selectShopListByUser(Integer userId) throws RuntimeException {
    	  return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbShopUserRefMapper.selectShopListByUser", userId);
    }
}