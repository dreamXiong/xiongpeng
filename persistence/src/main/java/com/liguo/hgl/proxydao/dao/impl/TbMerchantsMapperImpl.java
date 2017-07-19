package com.liguo.hgl.proxydao.dao.impl;

import com.github.pagehelper.Page;
import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbMerchantsMapper;
import com.liguo.hgl.proxydao.dto.TbMerchantsAssociatedDto;
import com.liguo.hgl.proxydao.dto.TbProductInventoryDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbMerchants;
import com.liguo.hgl.proxydao.page.PageDto;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

@Repository
public class TbMerchantsMapperImpl extends BaseMapperImpl<TbMerchants> implements TbMerchantsMapper {

    public TbMerchantsMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbMerchantsMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbMerchants record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbMerchantsMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbMerchants record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbMerchantsMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbMerchantsMapper.deleteByObject", parameter);
    }

    public int insert(TbMerchants record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbMerchantsMapper.insert", record);
    }

    public int insertSelective(TbMerchants record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbMerchantsMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbMerchantsMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbMerchants> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbMerchantsMapper.selectByObject", parameter);
    }

    public TbMerchants selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbMerchantsMapper.selectByPrimaryKey", id);
        return obj != null ? (TbMerchants)obj : null;
    }

	@Override
	public TbMerchantsAssociatedDto selectById(Integer id) {
		 Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbMerchantsMapper.selectById", id);
	        return obj != null ? (TbMerchantsAssociatedDto)obj : null;
	}


	@Override
	public List<TbMerchantsAssociatedDto> selectByCriteria(Criteria criteria, PageDto page) {
		Page<TbMerchantsAssociatedDto> selectList =(Page)this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbMerchantsMapper.selectByCriteria", criteria,new RowBounds(page.pageIndex, page.pageSize));
		int total=(int) selectList.getTotal();
		page.reset(total);
		return selectList;
	}

	@Override
	public List<TbMerchantsAssociatedDto> selectByCriteria(Criteria example) {
		List<TbMerchantsAssociatedDto> selectList =this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbMerchantsMapper.selectByCriteria", example);
		
		return selectList;
	}

	@Override
	public List<TbMerchantsAssociatedDto> selectByShopCriteria(Criteria example) {
		List<TbMerchantsAssociatedDto> selectList =this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbMerchantsMapper.selectByShopCriteria", example);
		
		return selectList;
	}

	@Override
	public List<TbMerchants> selectByAdress(Criteria example) {
     List<TbMerchants> selectList =this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbMerchantsMapper.selectByAdress", example);
		
		return selectList;
	}
}