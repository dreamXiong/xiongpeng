package com.liguo.hgl.proxydao.dao.impl;

import com.github.pagehelper.Page;
import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbCouponInfoMapper;
import com.liguo.hgl.proxydao.dto.CouponInfoDto;
import com.liguo.hgl.proxydao.dto.TbAdminUserDto;
import com.liguo.hgl.proxydao.dto.WebUserDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbCouponInfo;
import com.liguo.hgl.proxydao.page.PageDto;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

@Repository
public class TbCouponInfoMapperImpl extends BaseMapperImpl<TbCouponInfo> implements TbCouponInfoMapper {

    public TbCouponInfoMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbCouponInfoMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbCouponInfo record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbCouponInfoMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbCouponInfo record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbCouponInfoMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbCouponInfoMapper.deleteByObject", parameter);
    }

    public int insert(TbCouponInfo record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbCouponInfoMapper.insert", record);
    }

    public int insertSelective(TbCouponInfo record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbCouponInfoMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbCouponInfoMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbCouponInfo> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbCouponInfoMapper.selectByObject", parameter);
    }

    public TbCouponInfo selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbCouponInfoMapper.selectByPrimaryKey", id);
        return obj != null ? (TbCouponInfo)obj : null;
    }
	
	@Override
	public List<CouponInfoDto> selectByObjectPage(Criteria criteria, PageDto page) {		
		Page<CouponInfoDto> selectList = (Page)this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbCouponInfoMapper.selectByObjectPage", criteria,new RowBounds(page.pageIndex,page.pageSize));
		int total = (int)selectList.getTotal();
		page.reset(total);
		return selectList;
	}

	@Override
	public List<CouponInfoDto> selectAmount(Criteria criteria) {
		
		return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbCouponInfoMapper.selectAmount", criteria);
	}
}