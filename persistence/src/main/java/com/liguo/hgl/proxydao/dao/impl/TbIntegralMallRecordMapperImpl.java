package com.liguo.hgl.proxydao.dao.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;
import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbIntegralMallRecordMapper;
import com.liguo.hgl.proxydao.dto.IntegralMallRecordDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbIntegralMallRecord;
import com.liguo.hgl.proxydao.page.PageDto;

@Repository
public class TbIntegralMallRecordMapperImpl extends BaseMapperImpl<TbIntegralMallRecord> implements TbIntegralMallRecordMapper {

    public TbIntegralMallRecordMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbIntegralMallRecordMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbIntegralMallRecord record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbIntegralMallRecordMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbIntegralMallRecord record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbIntegralMallRecordMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbIntegralMallRecordMapper.deleteByObject", parameter);
    }

    public int insert(TbIntegralMallRecord record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbIntegralMallRecordMapper.insert", record);
    }

    public int insertSelective(TbIntegralMallRecord record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbIntegralMallRecordMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbIntegralMallRecordMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<IntegralMallRecordDto> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbIntegralMallRecordMapper.selectByObject", parameter);
    }

    public TbIntegralMallRecord selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbIntegralMallRecordMapper.selectByPrimaryKey", id);
        return obj != null ? (TbIntegralMallRecord)obj : null;
    }

	@Override
	public List<IntegralMallRecordDto> selectByObject(Criteria example,PageDto pgo) {
		Page<IntegralMallRecordDto> selectList = (Page)this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbIntegralMallRecordMapper.selectByObject", example,new RowBounds(pgo.pageIndex, pgo.pageSize));
    	pgo.reset((int) selectList.getTotal());
        return selectList;
	}

	@Override
	public TbIntegralMallRecord selectBySerialNo(Criteria example) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbIntegralMallRecordMapper.selectBySerialNo", example);
        return obj != null ? (TbIntegralMallRecord)obj : null;
	}
}