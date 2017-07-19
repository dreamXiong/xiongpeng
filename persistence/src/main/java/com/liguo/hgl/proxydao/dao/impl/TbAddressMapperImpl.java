package com.liguo.hgl.proxydao.dao.impl;

import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbAddressMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbAddress;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class TbAddressMapperImpl extends BaseMapperImpl<TbAddress> implements TbAddressMapper {

    public TbAddressMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbAddressMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbAddress record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbAddressMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbAddress record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbAddressMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbAddressMapper.deleteByObject", parameter);
    }

    public int insert(TbAddress record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbAddressMapper.insert", record);
    }

    public int insertSelective(TbAddress record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbAddressMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbAddressMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbAddress> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbAddressMapper.selectByObject", parameter);
    }

    public TbAddress selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbAddressMapper.selectByPrimaryKey", id);
        return obj != null ? (TbAddress)obj : null;
    }
}