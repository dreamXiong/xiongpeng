package com.liguo.hgl.proxydao.dao.impl;

import com.github.pagehelper.Page;
import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbExperienceMapper;
import com.liguo.hgl.proxydao.dto.TbMerchantsAssociatedDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbExperience;
import com.liguo.hgl.proxydao.page.PageDto;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

@Repository
public class TbExperienceMapperImpl extends BaseMapperImpl<TbExperience> implements TbExperienceMapper {

    public TbExperienceMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbExperienceMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbExperience record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbExperienceMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbExperience record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbExperienceMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbExperienceMapper.deleteByObject", parameter);
    }

    public int insert(TbExperience record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbExperienceMapper.insert", record);
    }

    public int insertSelective(TbExperience record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbExperienceMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbExperienceMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbExperience> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbExperienceMapper.selectByObject", parameter);
    }

    public TbExperience selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbExperienceMapper.selectByPrimaryKey", id);
        return obj != null ? (TbExperience)obj : null;
    }

	@Override
	public List<TbExperience> selectByCriteria(Criteria criteria, PageDto page) {
		Page<TbExperience> selectList =(Page)this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbExperienceMapper.selectByCriteria", criteria,new RowBounds(page.pageIndex, page.pageSize));
		int total=(int) selectList.getTotal();
		page.reset(total);
		return selectList;
	}

	@Override
	public TbExperience getMyExperience(Integer id) {
		 Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbExperienceMapper.selectByUserId", id);
	     return obj != null ? (TbExperience)obj : null;
	}
}