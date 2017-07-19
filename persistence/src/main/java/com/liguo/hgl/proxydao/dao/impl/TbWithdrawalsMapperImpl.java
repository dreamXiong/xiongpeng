package com.liguo.hgl.proxydao.dao.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;
import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbWithdrawalsMapper;
import com.liguo.hgl.proxydao.dto.WithdrawalsDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbWithdrawals;
import com.liguo.hgl.proxydao.page.PageDto;

@Repository
public class TbWithdrawalsMapperImpl extends BaseMapperImpl<TbWithdrawals> implements TbWithdrawalsMapper {

    public TbWithdrawalsMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbWithdrawalsMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbWithdrawals record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbWithdrawalsMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbWithdrawals record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbWithdrawalsMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbWithdrawalsMapper.deleteByObject", parameter);
    }

    public int insert(TbWithdrawals record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbWithdrawalsMapper.insert", record);
    }

    public int insertSelective(TbWithdrawals record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbWithdrawalsMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbWithdrawalsMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbWithdrawals> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbWithdrawalsMapper.selectByObject", parameter);
    }

    public TbWithdrawals selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbWithdrawalsMapper.selectByPrimaryKey", id);
        return obj != null ? (TbWithdrawals)obj : null;
    }

	@Override
	public List<WithdrawalsDto> selectWithdrawalsPage(Criteria criteria,
			PageDto page) {
		Page<WithdrawalsDto> selectList = (Page)this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbWithdrawalsMapper.selectWithdrawalsPage", criteria,new RowBounds(page.pageIndex,page.pageSize));
		int total = (int)selectList.getTotal();
		page.reset(total);
		return selectList;
	}
}