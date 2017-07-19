package com.liguo.hgl.proxydao.dao.impl;

import com.github.pagehelper.Page;
import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbManualorderDetailMapper;
import com.liguo.hgl.proxydao.dto.ManualOrderDetailDto;
import com.liguo.hgl.proxydao.dto.TbAdminUserDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbManualorderDetail;
import com.liguo.hgl.proxydao.page.PageDto;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

@Repository
public class TbManualorderDetailMapperImpl extends BaseMapperImpl<TbManualorderDetail> implements TbManualorderDetailMapper {

    public TbManualorderDetailMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbManualorderDetailMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbManualorderDetail record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbManualorderDetailMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbManualorderDetail record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbManualorderDetailMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbManualorderDetailMapper.deleteByObject", parameter);
    }

    public int insert(TbManualorderDetail record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbManualorderDetailMapper.insert", record);
    }

    public int insertSelective(TbManualorderDetail record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbManualorderDetailMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbManualorderDetailMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbManualorderDetail> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbManualorderDetailMapper.selectByObject", parameter);
    }

    public TbManualorderDetail selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbManualorderDetailMapper.selectByPrimaryKey", id);
        return obj != null ? (TbManualorderDetail)obj : null;
    }

	@Override
	public List<ManualOrderDetailDto> selectObjectByPage(Criteria example,
			PageDto page) {
		
		Page<ManualOrderDetailDto> selectList = (Page)this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbManualorderDetailMapper.selectObjectByPage", example, new RowBounds(page.pageIndex,page.pageSize));
		page.reset((int) selectList.getTotal());
		
		return selectList;
	}
}