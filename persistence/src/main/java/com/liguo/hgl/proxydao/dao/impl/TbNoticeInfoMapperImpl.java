package com.liguo.hgl.proxydao.dao.impl;

import com.github.pagehelper.Page;
import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbNoticeInfoMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbBrand;
import com.liguo.hgl.proxydao.model.TbNoticeInfo;
import com.liguo.hgl.proxydao.page.PageDto;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

@Repository
public class TbNoticeInfoMapperImpl extends BaseMapperImpl<TbNoticeInfo> implements TbNoticeInfoMapper {

    public TbNoticeInfoMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbNoticeInfoMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbNoticeInfo record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbNoticeInfoMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbNoticeInfo record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbNoticeInfoMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbNoticeInfoMapper.deleteByObject", parameter);
    }

    public int insert(TbNoticeInfo record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbNoticeInfoMapper.insert", record);
    }

    public int insertSelective(TbNoticeInfo record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbNoticeInfoMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbNoticeInfoMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbNoticeInfo> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbNoticeInfoMapper.selectByObject", parameter);
    }
    
    public TbNoticeInfo selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbNoticeInfoMapper.selectByPrimaryKey", id);
        return obj != null ? (TbNoticeInfo)obj : null;
    }

	@Override
	public List<TbNoticeInfo> selectByObjectPage(Criteria example, PageDto pgo) {
		
		Page<TbNoticeInfo> selectList = (Page)this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbNoticeInfoMapper.selectByObjectPage",example,new RowBounds(pgo.pageIndex,pgo.pageSize));
		pgo.reset((int)selectList.getTotal());
		return selectList;		
	}
	
}