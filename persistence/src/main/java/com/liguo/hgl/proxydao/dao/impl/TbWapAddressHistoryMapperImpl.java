package com.liguo.hgl.proxydao.dao.impl;

import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbWapAddressHistoryMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbWapAddressHistory;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class TbWapAddressHistoryMapperImpl extends BaseMapperImpl<TbWapAddressHistory> implements TbWapAddressHistoryMapper {

    public TbWapAddressHistoryMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbWapAddressHistoryMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbWapAddressHistory record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbWapAddressHistoryMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbWapAddressHistory record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbWapAddressHistoryMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbWapAddressHistoryMapper.deleteByObject", parameter);
    }

    public int insert(TbWapAddressHistory record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbWapAddressHistoryMapper.insert", record);
    }

    public int insertSelective(TbWapAddressHistory record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbWapAddressHistoryMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbWapAddressHistoryMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbWapAddressHistory> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbWapAddressHistoryMapper.selectByObject", parameter);
    }

    public TbWapAddressHistory selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbWapAddressHistoryMapper.selectByPrimaryKey", id);
        return obj != null ? (TbWapAddressHistory)obj : null;
    }
}