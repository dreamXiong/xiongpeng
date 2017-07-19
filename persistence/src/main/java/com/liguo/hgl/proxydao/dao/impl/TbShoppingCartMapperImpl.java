package com.liguo.hgl.proxydao.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbShoppingCartMapper;
import com.liguo.hgl.proxydao.dto.ShoppingCartDto;
import com.liguo.hgl.proxydao.dto.ShoppingCartInfoDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbShoppingCart;

@Repository
public class TbShoppingCartMapperImpl extends BaseMapperImpl<TbShoppingCart> implements TbShoppingCartMapper {

    public TbShoppingCartMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Criteria example) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbShoppingCartMapper.deleteByPrimaryKey", example);
    }

    public int updateByPrimaryKeySelective(TbShoppingCart record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbShoppingCartMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbShoppingCart record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbShoppingCartMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbShoppingCartMapper.deleteByObject", parameter);
    }

    public int insert(TbShoppingCart record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbShoppingCartMapper.insert", record);
    }

    public int insertSelective(TbShoppingCart record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbShoppingCartMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbShoppingCartMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbShoppingCart> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbShoppingCartMapper.selectByObject", parameter);
    }

    public TbShoppingCart selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbShoppingCartMapper.selectByPrimaryKey", id);
        return obj != null ? (TbShoppingCart)obj : null;
    }

	@Override
	public int batchInsertSelective(List<TbShoppingCart> record) {
		 return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbShoppingCartMapper.batchInsertSelective", record);
	}
	
	public List<ShoppingCartDto> selectByCart(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbShoppingCartMapper.selectByCart", parameter);
    }
	
	@Override
    public List<ShoppingCartInfoDto> selectByCartInfo(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbShoppingCartMapper.selectByCartInfo", parameter);
    }

	@Override
	public double getShopCartMoney(Criteria parameter) {
		 Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbShoppingCartMapper.ShopCartMoney", parameter);
	     return obj != null ? Double.valueOf(String.valueOf(obj)) : 0;
	}

	@Override
	public List<TbShoppingCart> getShopCartId(Criteria parameter) {
		return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbShoppingCartMapper.selectShopCartId", parameter);
	}

	@Override
	public List<ShoppingCartInfoDto> selectByMerchats(Criteria parameter) {
		return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbShoppingCartMapper.selectByMerchats", parameter);
		
	}

	@Override
	public TbShoppingCart selectByIsMerchants(Criteria criteria) {
		 Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbShoppingCartMapper.selectByIsMerchants", criteria);
	     return obj != null ? (TbShoppingCart)obj : null;
	}

	@Override
	public int updateByMerchants(TbShoppingCart record) {
		return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbShoppingCartMapper.updateByMerchants", record);
	}

	@Override
	public List<TbShoppingCart> selectNoMerchantsCart(Criteria parameter) {
		return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbShoppingCartMapper.selectNoMerchantsCart", parameter);
	}

	@Override
	public int updateShopInfoType(Criteria parameter) {
		return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbShoppingCartMapper.updateShopInfoType", parameter);
	}
}