package com.liguo.hgl.proxydao.dao.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;
import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbShopInfoMapper;
import com.liguo.hgl.proxydao.dto.ShopIndexForWapDto;
import com.liguo.hgl.proxydao.dto.ShopWebUserDto;
import com.liguo.hgl.proxydao.dto.TbWapProductDto;
import com.liguo.hgl.proxydao.dto.WapProductType;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbShopInfo;
import com.liguo.hgl.proxydao.page.PageDto;

@Repository
public class TbShopInfoMapperImpl extends BaseMapperImpl<TbShopInfo> implements TbShopInfoMapper {

    public TbShopInfoMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbShopInfoMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbShopInfo record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbShopInfoMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbShopInfo record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbShopInfoMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbShopInfoMapper.deleteByObject", parameter);
    }

    public int insert(TbShopInfo record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbShopInfoMapper.insert", record);
    }

    public int insertSelective(TbShopInfo record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbShopInfoMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbShopInfoMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbShopInfo> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbShopInfoMapper.selectByObject", parameter);
    }

    public TbShopInfo selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbShopInfoMapper.selectByPrimaryKey", id);
        return obj != null ? (TbShopInfo)obj : null;
    }

	@Override
	public ShopWebUserDto getShopWebUserById(Integer id) {
		 Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbShopInfoMapper.selectUserShopById", id);
	     return obj != null ? (ShopWebUserDto)obj : null;
	}

	@Override
	public List<ShopWebUserDto> selectByCriteria(Criteria criteria, PageDto page) {
		Page<ShopWebUserDto> selectList =(Page)this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbShopInfoMapper.selectByCriteria", criteria,new RowBounds(page.pageIndex, page.pageSize));
		int total=(int) selectList.getTotal();
		page.reset(total);
		return selectList;
	}
	public ShopIndexForWapDto selectShopIndexInfo(Criteria parameter){
		 Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbWapProductMapper.selectProducIndex", parameter);
	     return obj != null ? (ShopIndexForWapDto)obj : null;
	}
	@Override
	public List<WapProductType> selectProductBrand(Criteria parameter){
		 return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbProductMapper.selectProductBrand", parameter);
	}
	public List<TbWapProductDto> selectTbWapProductDtoList(Criteria parameter){
		 return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbWapProductMapper.selectTbWapProductDtoList", parameter);
	}

	@Override
	public TbShopInfo selectByUserId(int id) {
		 Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbShopInfoMapper.selectByUserId", id);
	     return obj != null ? (ShopWebUserDto)obj : null;
	}
	public List<TbShopInfo> selectTbShopInfoForWap(Criteria parameter){
		 return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbShopInfoMapper.selectTbShopInfoForWap", parameter);
	}

	@Override
	public TbShopInfo selectRebatereCommended(Criteria criteria) {
		 Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbShopInfoMapper.selectRebatereCommended", criteria);
	     return obj != null ? (TbShopInfo)obj : null;
	}
	
	@Override
	public List<TbShopInfo> selectNearTbShopInfo(Criteria parameter){
		return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbShopInfoMapper.selectNearTbShopInfo", parameter);
	}
	
	public Integer selectNearTbShopPageCount(Criteria parameter){
		 Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbShopInfoMapper.selectNearTbShopPageCount", parameter);
	     return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
	 }
}