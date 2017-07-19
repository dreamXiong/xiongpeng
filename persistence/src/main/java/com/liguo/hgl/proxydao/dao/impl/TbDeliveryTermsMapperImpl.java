package com.liguo.hgl.proxydao.dao.impl;

import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbDeliveryTermsMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbDeliveryTerms;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class TbDeliveryTermsMapperImpl extends BaseMapperImpl<TbDeliveryTerms> implements TbDeliveryTermsMapper {

    public TbDeliveryTermsMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbDeliveryTermsMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbDeliveryTerms record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbDeliveryTermsMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbDeliveryTerms record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbDeliveryTermsMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbDeliveryTermsMapper.deleteByObject", parameter);
    }

    public int insert(TbDeliveryTerms record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbDeliveryTermsMapper.insert", record);
    }

    public int insertSelective(TbDeliveryTerms record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbDeliveryTermsMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbDeliveryTermsMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbDeliveryTerms> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbDeliveryTermsMapper.selectByObject", parameter);
    }

    public TbDeliveryTerms selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbDeliveryTermsMapper.selectByPrimaryKey", id);
        return obj != null ? (TbDeliveryTerms)obj : null;
    }

	@Override
	public TbDeliveryTerms selectByDeliveryObject(Criteria example) {
		 Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbDeliveryTermsMapper.selectByDeliveryObject", example);
		 return obj != null ? (TbDeliveryTerms)obj : null;
	}
}