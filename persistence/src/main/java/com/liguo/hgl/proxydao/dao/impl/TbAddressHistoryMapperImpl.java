package com.liguo.hgl.proxydao.dao.impl;

import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbAddressHistoryMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbAddressHistory;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class TbAddressHistoryMapperImpl extends BaseMapperImpl<TbAddressHistory> implements TbAddressHistoryMapper {

    public TbAddressHistoryMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbAddressHistoryMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbAddressHistory record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbAddressHistoryMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbAddressHistory record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbAddressHistoryMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbAddressHistoryMapper.deleteByObject", parameter);
    }

    public int insert(TbAddressHistory record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbAddressHistoryMapper.insert", record);
    }

    public int insertSelective(TbAddressHistory record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbAddressHistoryMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbAddressHistoryMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbAddressHistory> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbAddressHistoryMapper.selectByObject", parameter);
    }

    public TbAddressHistory selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbAddressHistoryMapper.selectByPrimaryKey", id);
        return obj != null ? (TbAddressHistory)obj : null;
    }
}