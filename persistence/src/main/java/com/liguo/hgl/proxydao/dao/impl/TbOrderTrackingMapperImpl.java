package com.liguo.hgl.proxydao.dao.impl;

import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbOrderTrackingMapper;
import com.liguo.hgl.proxydao.dto.OrderTrackingDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbOrderTracking;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class TbOrderTrackingMapperImpl extends BaseMapperImpl<TbOrderTracking> implements TbOrderTrackingMapper {

    public TbOrderTrackingMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbOrderTrackingMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbOrderTracking record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbOrderTrackingMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbOrderTracking record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbOrderTrackingMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbOrderTrackingMapper.deleteByObject", parameter);
    }

    public int insert(TbOrderTracking record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbOrderTrackingMapper.insert", record);
    }

    public int insertSelective(TbOrderTracking record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbOrderTrackingMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbOrderTrackingMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbOrderTracking> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbOrderTrackingMapper.selectByObject", parameter);
    }

    public TbOrderTracking selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbOrderTrackingMapper.selectByPrimaryKey", id);
        return obj != null ? (TbOrderTracking)obj : null;
    }

	@Override
	public int batchInsert(List<TbOrderTracking> record) {
		return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbOrderTrackingMapper.batchInsert", record);
    }

	@Override
	public List<TbOrderTracking> selectByObjectStatus(Criteria example) {
		
		return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbOrderTrackingMapper.selectByObjectStatus", example);
	}

	@Override
	public List<OrderTrackingDto> selectByObjectDto(Criteria example) {
		
		return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbOrderTrackingMapper.selectByObjectDto", example);
	}
}