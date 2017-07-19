package com.liguo.hgl.proxydao.dao.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;
import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbWarehouseMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbWarehouse;
import com.liguo.hgl.proxydao.model.WarehouseDto;
import com.liguo.hgl.proxydao.page.PageDto;

@Repository
public class TbWarehouseMapperImpl extends BaseMapperImpl<TbWarehouse> implements TbWarehouseMapper {

    public TbWarehouseMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbWarehouseMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbWarehouse record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbWarehouseMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbWarehouse record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbWarehouseMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbWarehouseMapper.deleteByObject", parameter);
    }

    public int insert(TbWarehouse record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbWarehouseMapper.insert", record);
    }

    public int insertSelective(TbWarehouse record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbWarehouseMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbWarehouseMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<WarehouseDto> selectByObject(Criteria parameter,PageDto pgo) {
    	Page<WarehouseDto> selectList = 
    			(Page)this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbWarehouseMapper.selectByObjectPage", parameter,new RowBounds(pgo.pageIndex, pgo.pageSize));
    	 pgo.reset((int) selectList.getTotal());
        return selectList;
    }
    @Override
    public List<TbWarehouse> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbWarehouseMapper.selectByObject", parameter);
    }
    public TbWarehouse selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbWarehouseMapper.selectByPrimaryKey", id);
        return obj != null ? (TbWarehouse)obj : null;
    }
}