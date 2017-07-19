package com.liguo.hgl.proxydao.dao.impl;

import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbWapAddressMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbWapAddress;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class TbWapAddressMapperImpl extends BaseMapperImpl<TbWapAddress> implements TbWapAddressMapper {

    public TbWapAddressMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbWapAddressMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbWapAddress record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbWapAddressMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbWapAddress record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbWapAddressMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbWapAddressMapper.deleteByObject", parameter);
    }

    public int insert(TbWapAddress record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbWapAddressMapper.insert", record);
    }

    public int insertSelective(TbWapAddress record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbWapAddressMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbWapAddressMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbWapAddress> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbWapAddressMapper.selectByObject", parameter);
    }
    
    @Override
    public TbWapAddress selectByDefaultObject(Criteria parameter) {
    	Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbWapAddressMapper.selectByDefaultObject", parameter);
        return obj != null ? (TbWapAddress)obj : null;
    }

    public TbWapAddress selectByPrimaryKey(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbWapAddressMapper.selectByPrimaryKey", parameter);
        return obj != null ? (TbWapAddress)obj : null;
    }

	@Override
	public int updateByDefaultPrimaryKey(TbWapAddress record) {
		 return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbWapAddressMapper.updateByDefaultPrimaryKey", record);
	}
	
	@Override
	public int updateByNamePrimaryKey(TbWapAddress record) {
		 return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbWapAddressMapper.updateByNamePrimaryKey", record);
	}
}