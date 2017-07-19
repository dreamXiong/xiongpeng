package com.liguo.hgl.proxydao.dao.impl;

import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbAccountBankMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbAccountBank;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class TbAccountBankMapperImpl extends BaseMapperImpl<TbAccountBank> implements TbAccountBankMapper {

    public TbAccountBankMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbAccountBankMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbAccountBank record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbAccountBankMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbAccountBank record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbAccountBankMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbAccountBankMapper.deleteByObject", parameter);
    }

    public int insert(TbAccountBank record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbAccountBankMapper.insert", record);
    }

    public int insertSelective(TbAccountBank record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbAccountBankMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbAccountBankMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbAccountBank> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbAccountBankMapper.selectByObject", parameter);
    }

    public TbAccountBank selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbAccountBankMapper.selectByPrimaryKey", id);
        return obj != null ? (TbAccountBank)obj : null;
    }
}