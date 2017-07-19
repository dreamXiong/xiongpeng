package com.liguo.hgl.proxydao.dao.impl;

import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbRecommendedRulesMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbRecommendedRules;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class TbRecommendedRulesMapperImpl extends BaseMapperImpl<TbRecommendedRules> implements TbRecommendedRulesMapper {

    public TbRecommendedRulesMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbRecommendedRulesMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbRecommendedRules record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbRecommendedRulesMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbRecommendedRules record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbRecommendedRulesMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbRecommendedRulesMapper.deleteByObject", parameter);
    }

    public int insert(TbRecommendedRules record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbRecommendedRulesMapper.insert", record);
    }

    public int insertSelective(TbRecommendedRules record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbRecommendedRulesMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbRecommendedRulesMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbRecommendedRules> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbRecommendedRulesMapper.selectByObject", parameter);
    }

    public TbRecommendedRules selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbRecommendedRulesMapper.selectByPrimaryKey", id);
        return obj != null ? (TbRecommendedRules)obj : null;
    }

	@Override
	public int batchInsert(List<TbRecommendedRules> record) {
		// TODO Auto-generated method stub
		return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbRecommendedRulesMapper.batchInsert", record);
	}
}