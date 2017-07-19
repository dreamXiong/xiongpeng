package com.liguo.hgl.proxydao.dao.impl;

import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbServiceTypeMapper;
import com.liguo.hgl.proxydao.dto.ServiceTypeDto;
import com.liguo.hgl.proxydao.dto.WapServiceTypeDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbServiceType;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class TbServiceTypeMapperImpl extends BaseMapperImpl<TbServiceType> implements TbServiceTypeMapper {

    public TbServiceTypeMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbServiceTypeMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbServiceType record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbServiceTypeMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbServiceType record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbServiceTypeMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbServiceTypeMapper.deleteByObject", parameter);
    }

    public int insert(TbServiceType record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbServiceTypeMapper.insert", record);
    }

    public int insertSelective(TbServiceType record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbServiceTypeMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbServiceTypeMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbServiceType> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbServiceTypeMapper.selectByObject", parameter);
    }
    @Override
    public List<TbServiceType> selectByWap(){
    	return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbServiceTypeMapper.selectByWap");
    }

    public TbServiceType selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbServiceTypeMapper.selectByPrimaryKey", id);
        return obj != null ? (TbServiceType)obj : null;
    }

	@Override
	public List<ServiceTypeDto> selectServiceTypeByIds(Criteria example) {
		return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbServiceTypeMapper.selectServiceTypeByIds", example);
	}

	@Override
	public ServiceTypeDto selectDtoByPrimaryKey(Integer id) {
		Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbServiceTypeMapper.selectDtoByPrimaryKey", id);
		return obj != null ?(ServiceTypeDto)obj:null;
	}

	@Override
	public List<ServiceTypeDto> selectDtoByObject(Criteria example) {
		return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbServiceTypeMapper.selectDtoByObject", example);
	}
	
	public List<WapServiceTypeDto> selectServiceType(){
		return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbServiceTypeMapper.selectServiceType");
	}
}