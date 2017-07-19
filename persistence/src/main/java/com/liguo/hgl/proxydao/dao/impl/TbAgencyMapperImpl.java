package com.liguo.hgl.proxydao.dao.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;
import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbAgencyMapper;
import com.liguo.hgl.proxydao.dto.AgencyDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbAgency;
import com.liguo.hgl.proxydao.page.PageDto;

@Repository
public class TbAgencyMapperImpl extends BaseMapperImpl<TbAgency> implements TbAgencyMapper {

    public TbAgencyMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbAgencyMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbAgency record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbAgencyMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbAgency record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbAgencyMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbAgencyMapper.deleteByObject", parameter);
    }

    public int insert(TbAgency record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbAgencyMapper.insert", record);
    }

    public int insertSelective(TbAgency record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbAgencyMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbAgencyMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbAgency> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbAgencyMapper.selectByObject", parameter);
    }

    public TbAgency selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbAgencyMapper.selectByPrimaryKey", id);
        return obj != null ? (TbAgency)obj : null;
    }

	@Override
	public List<AgencyDto> selectByCriteria(Criteria criteria, PageDto page) {
		Page<AgencyDto> selectList =(Page)this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbAgencyMapper.selectByCriteria", criteria,new RowBounds(page.pageIndex, page.pageSize));
		int total=(int) selectList.getTotal();
		page.reset(total);
		return selectList;
	}

	@Override
	public List<AgencyDto> selectByCriteria(Criteria parameter) {
		return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbAgencyMapper.selectCriteria", parameter);
	}

	@Override
	public List<TbAgency> selecEffectMerchants(Criteria parameter) {
		return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbAgencyMapper.selecEffectMerchants", parameter);
	}

	@Override
	public TbAgency selectByOrderNumber(String orderNumber) {
		 Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbAgencyMapper.selectByOrderNumber", orderNumber);
	        return obj != null ? (TbAgency)obj : null;
	}
}