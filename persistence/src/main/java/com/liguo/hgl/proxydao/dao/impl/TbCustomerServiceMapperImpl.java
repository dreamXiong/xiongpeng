package com.liguo.hgl.proxydao.dao.impl;

import com.github.pagehelper.Page;
import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbCustomerServiceMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.CustomerServiceDto;
import com.liguo.hgl.proxydao.model.ProductInfoDto;
import com.liguo.hgl.proxydao.model.TbCustomerService;
import com.liguo.hgl.proxydao.page.PageDto;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

@Repository
public class TbCustomerServiceMapperImpl extends BaseMapperImpl<TbCustomerService> implements TbCustomerServiceMapper {

    public TbCustomerServiceMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbCustomerServiceMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbCustomerService record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbCustomerServiceMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbCustomerService record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbCustomerServiceMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbCustomerServiceMapper.deleteByObject", parameter);
    }

    public int insert(TbCustomerService record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbCustomerServiceMapper.insert", record);
    }

    public int insertSelective(TbCustomerService record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbCustomerServiceMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbCustomerServiceMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbCustomerService> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbCustomerServiceMapper.selectByObject", parameter);
    }

    public TbCustomerService selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbCustomerServiceMapper.selectByPrimaryKey", id);
        return obj != null ? (TbCustomerService)obj : null;
    }
    public List<CustomerServiceDto> selectInfoListByName(Criteria parameter, PageDto page){
    	Page<CustomerServiceDto> selectList=(Page)this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbCustomerServiceMapper.selectInfoListByName", parameter,new RowBounds(page.pageIndex, page.pageSize));
		 page.reset((int) selectList.getTotal());
		 return selectList;
    }
    public List<CustomerServiceDto> selectInfoListByName(Criteria parameter){
    	List<CustomerServiceDto> selectList= this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbCustomerServiceMapper.selectInfoListByName", parameter);
		return selectList;
    }
    public CustomerServiceDto selectInfoById(Integer id){
    	Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbCustomerServiceMapper.selectInfoById", id);
        return obj != null ? (CustomerServiceDto)obj : null;
    }
}