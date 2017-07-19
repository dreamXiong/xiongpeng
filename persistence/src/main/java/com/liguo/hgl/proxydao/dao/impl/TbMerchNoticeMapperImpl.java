package com.liguo.hgl.proxydao.dao.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;
import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbMerchNoticeMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbMerchNotice;
import com.liguo.hgl.proxydao.page.PageDto;

@Repository
public class TbMerchNoticeMapperImpl extends BaseMapperImpl<TbMerchNotice> implements TbMerchNoticeMapper {

    public TbMerchNoticeMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbMerchNoticeMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbMerchNotice record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbMerchNoticeMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbMerchNotice record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbMerchNoticeMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbMerchNoticeMapper.deleteByObject", parameter);
    }

    public int insert(TbMerchNotice record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbMerchNoticeMapper.insert", record);
    }

    public int insertSelective(TbMerchNotice record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbMerchNoticeMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbMerchNoticeMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbMerchNotice> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbMerchNoticeMapper.selectByObject", parameter);
    }

    public TbMerchNotice selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbMerchNoticeMapper.selectByPrimaryKey", id);
        return obj != null ? (TbMerchNotice)obj : null;
    }

	@Override
	public List<TbMerchNotice> selectByObjectPage(Criteria example,PageDto page) {
		
		Page<TbMerchNotice> selectList = (Page)this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbMerchNoticeMapper.selectByObjectPage", example, new RowBounds(page.pageIndex,page.pageSize));
		page.reset((int)selectList.getTotal());
		
		return selectList;
	}
}