package com.liguo.hgl.proxydao.dao.impl;

import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbIntegralRulesMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbIntegral;
import com.liguo.hgl.proxydao.model.TbIntegralRules;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class TbIntegralRulesMapperImpl extends BaseMapperImpl<TbIntegralRules> implements TbIntegralRulesMapper {

    public TbIntegralRulesMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbIntegralRulesMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbIntegralRules record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbIntegralRulesMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbIntegralRules record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbIntegralRulesMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbIntegralRulesMapper.deleteByObject", parameter);
    }

    public int insert(TbIntegralRules record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbIntegralRulesMapper.insert", record);
    }

    public int insertSelective(TbIntegralRules record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbIntegralRulesMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbIntegralRulesMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbIntegralRules> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbIntegralRulesMapper.selectByObject", parameter);
    }

    public TbIntegralRules selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbIntegralRulesMapper.selectByPrimaryKey", id);
        return obj != null ? (TbIntegralRules)obj : null;
    }
    
    @Override
    public int batchInsert(List<TbIntegralRules> record){
    	 return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbIntegralRulesMapper.batchInsert", record);
    }
    
    @Override
    public int batchUpdate(List<TbIntegralRules> record){
    	 return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbIntegralRulesMapper.batchUpdate", record);
    }
    
    @Override
	public TbIntegralRules selectIntegralByMoney(Criteria parameter){
		return this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbIntegralRulesMapper.selectIntegralByMoney", parameter);
	}
	
	@Override
	public  List<TbIntegralRules> selectUsedRule(Criteria parameter){
		return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbIntegralRulesMapper.selectUsedRule", parameter);
	}
}