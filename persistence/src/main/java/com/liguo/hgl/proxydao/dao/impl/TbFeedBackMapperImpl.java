package com.liguo.hgl.proxydao.dao.impl;

import com.github.pagehelper.Page;
import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbFeedBackMapper;
import com.liguo.hgl.proxydao.dto.FeedBackDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbFeedBack;
import com.liguo.hgl.proxydao.model.TbSaveInfo;
import com.liguo.hgl.proxydao.page.PageDto;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

@Repository
public class TbFeedBackMapperImpl extends BaseMapperImpl<TbFeedBack> implements TbFeedBackMapper {

    public TbFeedBackMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbFeedBackMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbFeedBack record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbFeedBackMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbFeedBack record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbFeedBackMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbFeedBackMapper.deleteByObject", parameter);
    }

    public int insert(TbFeedBack record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbFeedBackMapper.insert", record);
    }

    public int insertSelective(TbFeedBack record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbFeedBackMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbFeedBackMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbFeedBack> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbFeedBackMapper.selectByObject", parameter);
    }

    public TbFeedBack selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbFeedBackMapper.selectByPrimaryKey", id);
        return obj != null ? (TbFeedBack)obj : null;
    }

	@Override
	public List<FeedBackDto> selectByObjectPage(Criteria criteria,PageDto pgo) {

		Page<FeedBackDto> selectList = (Page)this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbFeedBackMapper.selectByObjectPage", criteria, new RowBounds(pgo.pageIndex,pgo.pageSize));
		pgo.reset((int)selectList.getTotal());
		return selectList;
	}
}