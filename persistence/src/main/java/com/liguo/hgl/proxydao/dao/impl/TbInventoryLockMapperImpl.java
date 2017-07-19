package com.liguo.hgl.proxydao.dao.impl;

import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbInventoryLockMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbInventoryLock;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class TbInventoryLockMapperImpl extends BaseMapperImpl<TbInventoryLock> implements TbInventoryLockMapper {

    public TbInventoryLockMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbInventoryLockMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbInventoryLock record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbInventoryLockMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbInventoryLock record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbInventoryLockMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbInventoryLockMapper.deleteByObject", parameter);
    }

    public int insert(TbInventoryLock record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbInventoryLockMapper.insert", record);
    }

    public int insertSelective(TbInventoryLock record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbInventoryLockMapper.insertSelective", record);
    }

    public int batchInsertSelective(List<TbInventoryLock> record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbInventoryLockMapper.batchInsertSelective", record);
    }
    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbInventoryLockMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbInventoryLock> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbInventoryLockMapper.selectByObject", parameter);
    }

    public TbInventoryLock selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbInventoryLockMapper.selectByPrimaryKey", id);
        return obj != null ? (TbInventoryLock)obj : null;
    }
    
    
    public int batchDeleteTbInventoryLock(List<Integer> record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbInventoryLockMapper.batchDeleteTbInventoryLock", record);
    }
    
    
}