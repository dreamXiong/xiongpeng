package com.liguo.hgl.proxydao.dao.impl;

import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbWapInventoryLockMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbInventoryLock;
import com.liguo.hgl.proxydao.model.TbWapInventoryLock;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class TbWapInventoryLockMapperImpl extends BaseMapperImpl<TbWapInventoryLock> implements TbWapInventoryLockMapper {

    public TbWapInventoryLockMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbWapInventoryLockMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbWapInventoryLock record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbWapInventoryLockMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbWapInventoryLock record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbWapInventoryLockMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbWapInventoryLockMapper.deleteByObject", parameter);
    }

    public int insert(TbWapInventoryLock record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbWapInventoryLockMapper.insert", record);
    }

    public int insertSelective(TbWapInventoryLock record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbWapInventoryLockMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbWapInventoryLockMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbWapInventoryLock> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbWapInventoryLockMapper.selectByObject", parameter);
    }

    public TbWapInventoryLock selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbWapInventoryLockMapper.selectByPrimaryKey", id);
        return obj != null ? (TbWapInventoryLock)obj : null;
    }
    
    public int batchInsertSelective(List<TbWapInventoryLock> record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbWapInventoryLockMapper.batchInsertSelective", record);
    }
    public int batchDeleteTbInventoryLock(List<Integer> record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbWapInventoryLockMapper.batchDeleteTbInventoryLock", record);
    }
}