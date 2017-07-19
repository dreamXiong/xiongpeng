package com.liguo.hgl.proxydao.dao.impl;

import com.github.pagehelper.Page;
import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbActivityInfoMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbActivityInfo;
import com.liguo.hgl.proxydao.page.PageDto;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

@Repository
public class TbActivityInfoMapperImpl extends BaseMapperImpl<TbActivityInfo> implements TbActivityInfoMapper {

    public TbActivityInfoMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbActivityInfoMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbActivityInfo record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbActivityInfoMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbActivityInfo record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbActivityInfoMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbActivityInfoMapper.deleteByObject", parameter);
    }

    public int insert(TbActivityInfo record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbActivityInfoMapper.insert", record);
    }

    public int insertSelective(TbActivityInfo record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbActivityInfoMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbActivityInfoMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbActivityInfo> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbActivityInfoMapper.selectByObject", parameter);
    }

    public TbActivityInfo selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbActivityInfoMapper.selectByPrimaryKey", id);
        return obj != null ? (TbActivityInfo)obj : null;
    }

	@Override
	public List<TbActivityInfo> selectObjectByPage(Criteria example,PageDto page) {
		Page<TbActivityInfo> selectList = (Page)this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbActivityInfoMapper.selectObjectByPage",example,new RowBounds(page.pageIndex,page.pageSize));
		int total = (int)selectList.getTotal();
		page.reset(total);
		return selectList;
	}
}