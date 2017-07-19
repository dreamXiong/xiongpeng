package com.liguo.hgl.proxydao.dao.impl;

import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbGroupDistributionMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbGroupDistribution;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class TbGroupDistributionMapperImpl extends BaseMapperImpl<TbGroupDistribution> implements TbGroupDistributionMapper {

    public TbGroupDistributionMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbGroupDistributionMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbGroupDistribution record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbGroupDistributionMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbGroupDistribution record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbGroupDistributionMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbGroupDistributionMapper.deleteByObject", parameter);
    }

    public int insert(TbGroupDistribution record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbGroupDistributionMapper.insert", record);
    }

    public int insertSelective(TbGroupDistribution record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbGroupDistributionMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbGroupDistributionMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbGroupDistribution> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbGroupDistributionMapper.selectByObject", parameter);
    }

    public TbGroupDistribution selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbGroupDistributionMapper.selectByPrimaryKey", id);
        return obj != null ? (TbGroupDistribution)obj : null;
    }

	@Override
	public List<TbGroupDistribution> selectByShopIds(Criteria example) {
		  return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbGroupDistributionMapper.selectByShopIds", example);
	}
}