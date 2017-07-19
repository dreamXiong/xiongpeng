package com.liguo.hgl.proxydao.dao.impl;

import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbWarehouseUserMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbWarehouseUser;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class TbWarehouseUserMapperImpl extends BaseMapperImpl<TbWarehouseUser> implements TbWarehouseUserMapper {

    public TbWarehouseUserMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbWarehouseUserMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbWarehouseUser record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbWarehouseUserMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbWarehouseUser record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbWarehouseUserMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbWarehouseUserMapper.deleteByObject", parameter);
    }

    public int insert(TbWarehouseUser record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbWarehouseUserMapper.insert", record);
    }

    public int insertSelective(TbWarehouseUser record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbWarehouseUserMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbWarehouseUserMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbWarehouseUser> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbWarehouseUserMapper.selectByObject", parameter);
    }

    public TbWarehouseUser selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbWarehouseUserMapper.selectByPrimaryKey", id);
        return obj != null ? (TbWarehouseUser)obj : null;
    }
}