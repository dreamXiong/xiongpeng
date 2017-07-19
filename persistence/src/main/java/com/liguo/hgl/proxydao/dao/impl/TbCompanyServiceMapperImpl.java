package com.liguo.hgl.proxydao.dao.impl;

import com.github.pagehelper.Page;
import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbCompanyServiceMapper;
import com.liguo.hgl.proxydao.dto.TbCompanyServiceDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbBrand;
import com.liguo.hgl.proxydao.model.TbCompanyService;
import com.liguo.hgl.proxydao.page.PageDto;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

@Repository
public class TbCompanyServiceMapperImpl extends BaseMapperImpl<TbCompanyService> implements TbCompanyServiceMapper {

    public TbCompanyServiceMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbCompanyServiceMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbCompanyService record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbCompanyServiceMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbCompanyService record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbCompanyServiceMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbCompanyServiceMapper.deleteByObject", parameter);
    }

    public int insert(TbCompanyService record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbCompanyServiceMapper.insert", record);
    }

    public int insertSelective(TbCompanyService record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbCompanyServiceMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbCompanyServiceMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbCompanyService> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbCompanyServiceMapper.selectByObject", parameter);
    }

    public TbCompanyService selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbCompanyServiceMapper.selectByPrimaryKey", id);
        return obj != null ? (TbCompanyService)obj : null;
    }
    public List<TbCompanyServiceDto> selectTbCompanyServicePage(Criteria parameter, PageDto page){
    	Page<TbCompanyServiceDto> selectList = 
    			(Page)this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbCompanyServiceMapper.selectTbCompanyServicePage", 
    														parameter,new RowBounds(page.pageIndex, page.pageSize));
    	page.reset((int) selectList.getTotal());
        return selectList;
    }
    
    public List<TbCompanyServiceDto> selectTbCompanyService(Criteria parameter){
    	 return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbCompanyServiceMapper.selectTbCompanyService", parameter);
    }
}