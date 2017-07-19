package com.liguo.hgl.proxydao.dao.impl;

import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbIntegralDetailedMapper;
import com.liguo.hgl.proxydao.dto.IntegralDetailedDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbIntegralDetailed;
import com.liguo.hgl.proxydao.page.PageDto;

import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class TbIntegralDetailedMapperImpl extends BaseMapperImpl<TbIntegralDetailed> implements TbIntegralDetailedMapper {

    public TbIntegralDetailedMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbIntegralDetailedMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbIntegralDetailed record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbIntegralDetailedMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbIntegralDetailed record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbIntegralDetailedMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbIntegralDetailedMapper.deleteByObject", parameter);
    }

    public int insert(TbIntegralDetailed record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbIntegralDetailedMapper.insert", record);
    }

    public int insertSelective(TbIntegralDetailed record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbIntegralDetailedMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbIntegralDetailedMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbIntegralDetailed> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbIntegralDetailedMapper.selectByObject", parameter);
    }

    public TbIntegralDetailed selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbIntegralDetailedMapper.selectByPrimaryKey", id);
        return obj != null ? (TbIntegralDetailed)obj : null;
    }
    @Override
    public List<IntegralDetailedDto> selectIntegralDetailedPage(Criteria parameter,PageDto page,Integer pageIndex){
    	return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbIntegralDetailedMapper.selectIntegralDetailedPage", parameter);
    }

	@Override
	public IntegralDetailedDto selectTodaySignByUserId(Criteria example) {
		 Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbIntegralDetailedMapper.selectTodaySignByUserId", example);
	     return obj != null ? (IntegralDetailedDto)obj : null;
	}
}