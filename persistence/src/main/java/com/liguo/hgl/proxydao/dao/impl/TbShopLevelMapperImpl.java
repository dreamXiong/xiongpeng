package com.liguo.hgl.proxydao.dao.impl;

import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbShopLevelMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbShopLevel;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class TbShopLevelMapperImpl extends BaseMapperImpl<TbShopLevel> implements TbShopLevelMapper {

    public TbShopLevelMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbShopLevelMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbShopLevel record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbShopLevelMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbShopLevel record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbShopLevelMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbShopLevelMapper.deleteByObject", parameter);
    }

    public int insert(TbShopLevel record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbShopLevelMapper.insert", record);
    }

    public int insertSelective(TbShopLevel record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbShopLevelMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbShopLevelMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbShopLevel> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbShopLevelMapper.selectByObject", parameter);
    }

    public TbShopLevel selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbShopLevelMapper.selectByPrimaryKey", id);
        return obj != null ? (TbShopLevel)obj : null;
    }

	@Override
	public TbShopLevel selectLevelByExperience(int experience) {
		 Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbShopLevelMapper.selectByExperience", experience);
	        return obj != null ? (TbShopLevel)obj : null;
	}
}