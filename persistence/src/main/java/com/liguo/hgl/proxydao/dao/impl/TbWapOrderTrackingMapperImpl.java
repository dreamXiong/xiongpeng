package com.liguo.hgl.proxydao.dao.impl;

import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbWapOrderTrackingMapper;
import com.liguo.hgl.proxydao.dto.WapOrderTrackingDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbWapOrderTracking;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class TbWapOrderTrackingMapperImpl extends BaseMapperImpl<TbWapOrderTracking> implements TbWapOrderTrackingMapper {

    public TbWapOrderTrackingMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbWapOrderTrackingMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbWapOrderTracking record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbWapOrderTrackingMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbWapOrderTracking record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbWapOrderTrackingMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbWapOrderTrackingMapper.deleteByObject", parameter);
    }

    public int insert(TbWapOrderTracking record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbWapOrderTrackingMapper.insert", record);
    }

    public int insertSelective(TbWapOrderTracking record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbWapOrderTrackingMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbWapOrderTrackingMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbWapOrderTracking> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbWapOrderTrackingMapper.selectByObject", parameter);
    }

    public TbWapOrderTracking selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbWapOrderTrackingMapper.selectByPrimaryKey", id);
        return obj != null ? (TbWapOrderTracking)obj : null;
    }

	@Override
	public int batchInsert(List<TbWapOrderTracking> record) {
		return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbWapOrderTrackingMapper.batchInsert", record);
	}

	@Override
	public List<WapOrderTrackingDto> selectByObjectDto(Criteria example) {
		 return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbWapOrderTrackingMapper.selectByObjectDto", example);
	}

	@Override
	public List<WapOrderTrackingDto> selectByObjectStatus(Criteria example) {
		 return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbWapOrderTrackingMapper.selectByObjectStatus", example);
	}

    @Override
    public int insertOrderTrackIsBatch(Criteria example) {
        
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbWapOrderTrackingMapper.insertOrderTrackIsBatch", example);
    }
}