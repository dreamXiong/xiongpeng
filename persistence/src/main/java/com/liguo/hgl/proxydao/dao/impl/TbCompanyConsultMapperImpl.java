package com.liguo.hgl.proxydao.dao.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;
import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbCompanyConsultMapper;
import com.liguo.hgl.proxydao.dto.TbCompanyConsultDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbCompanyConsult;
import com.liguo.hgl.proxydao.page.PageDto;

@Repository
public class TbCompanyConsultMapperImpl extends BaseMapperImpl<TbCompanyConsult> implements TbCompanyConsultMapper {

    public TbCompanyConsultMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbCompanyConsultMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbCompanyConsult record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbCompanyConsultMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbCompanyConsult record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbCompanyConsultMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbCompanyConsultMapper.deleteByObject", parameter);
    }

    public int insert(TbCompanyConsult record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbCompanyConsultMapper.insert", record);
    }

    public int insertSelective(TbCompanyConsult record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbCompanyConsultMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbCompanyConsultMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbCompanyConsult> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbCompanyConsultMapper.selectByObject", parameter);
    }
    
    @Override
    public TbCompanyConsult selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbCompanyConsultMapper.selectByPrimaryKey", id);
        return obj != null ? (TbCompanyConsult)obj : null;
    }
    @Override
    public List<TbCompanyConsultDto> selectByObjectPage(Criteria parameter,PageDto pgo){
    	Page<TbCompanyConsultDto> selectList = 
    			(Page)this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbCompanyConsultMapper.selectByObjectPage", parameter,new RowBounds(pgo.pageIndex, pgo.pageSize));
    	 pgo.reset((int) selectList.getTotal());
        return selectList;
    }
}