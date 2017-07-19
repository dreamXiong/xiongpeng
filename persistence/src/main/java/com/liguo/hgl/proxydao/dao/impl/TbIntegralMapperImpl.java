package com.liguo.hgl.proxydao.dao.impl;

import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbIntegralMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbIntegral;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class TbIntegralMapperImpl extends BaseMapperImpl<TbIntegral> implements TbIntegralMapper {

    public TbIntegralMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbIntegralMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbIntegral record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbIntegralMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbIntegral record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbIntegralMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbIntegralMapper.deleteByObject", parameter);
    }

    public int insert(TbIntegral record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbIntegralMapper.insert", record);
    }

    public int insertSelective(TbIntegral record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbIntegralMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbIntegralMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbIntegral> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbIntegralMapper.selectByObject", parameter);
    }

    public TbIntegral selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbIntegralMapper.selectByPrimaryKey", id);
        return obj != null ? (TbIntegral)obj : null;
    }

	@Override
	public TbIntegral selectByUserIdObject(Criteria example) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbIntegralMapper.selectByUserIdObject", example);
        return obj != null ? (TbIntegral)obj : null;
	}
	
	@Override
	public TbIntegral selectByShopId(Integer shopId){
		return this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbIntegralMapper.selectByShopId", shopId);
	}
}