package com.liguo.hgl.proxydao.dao.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;
import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbCashAccountMapper;
import com.liguo.hgl.proxydao.dto.CashAccountDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbCashAccount;
import com.liguo.hgl.proxydao.page.PageDto;

@Repository
public class TbCashAccountMapperImpl extends BaseMapperImpl<TbCashAccount> implements TbCashAccountMapper {

    public TbCashAccountMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbCashAccountMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbCashAccount record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbCashAccountMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbCashAccount record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbCashAccountMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbCashAccountMapper.deleteByObject", parameter);
    }

    public int insert(TbCashAccount record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbCashAccountMapper.insert", record);
    }

    public int insertSelective(TbCashAccount record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbCashAccountMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbCashAccountMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbCashAccount> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbCashAccountMapper.selectByObject", parameter);
    }
    
    @Override
    public TbCashAccount selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbCashAccountMapper.selectByPrimaryKey", id);
        return obj != null ? (TbCashAccount)obj : null;
    }
    @Override
    public List<TbCashAccount> selectCashAccountList(Criteria parameter,PageDto page){
    	Page<TbCashAccount> iDList = (Page)this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbCashAccountMapper.selectCashAccountList", parameter,new RowBounds(page.pageIndex, page.pageSize));
		int total=(int) iDList.getTotal();
		page.reset(total);
		return iDList;
    }
    
    @Override
    public int insertBatchCashAccount(List<TbCashAccount> List){
    	 return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbCashAccountMapper.insertBatchCashAccount", List);
    }
    
    @Override
    public List<CashAccountDto> selectCashAccount(Criteria parameter) throws RuntimeException{
    	 return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbCashAccountMapper.selectByCashAccount", parameter);
    }
}