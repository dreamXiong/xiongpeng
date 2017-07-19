package com.liguo.hgl.proxydao.dao.impl;

import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbWapOrderTrackingServiceMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbWapOrderTrackingService;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class TbWapOrderTrackingServiceMapperImpl extends BaseMapperImpl<TbWapOrderTrackingService> implements TbWapOrderTrackingServiceMapper {

    public TbWapOrderTrackingServiceMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbWapOrderTrackingServiceMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbWapOrderTrackingService record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbWapOrderTrackingServiceMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbWapOrderTrackingService record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbWapOrderTrackingServiceMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbWapOrderTrackingServiceMapper.deleteByObject", parameter);
    }

    public int insert(TbWapOrderTrackingService record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbWapOrderTrackingServiceMapper.insert", record);
    }

    public int insertSelective(TbWapOrderTrackingService record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbWapOrderTrackingServiceMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbWapOrderTrackingServiceMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbWapOrderTrackingService> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbWapOrderTrackingServiceMapper.selectByObject", parameter);
    }

    public TbWapOrderTrackingService selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbWapOrderTrackingServiceMapper.selectByPrimaryKey", id);
        return obj != null ? (TbWapOrderTrackingService)obj : null;
    }
}