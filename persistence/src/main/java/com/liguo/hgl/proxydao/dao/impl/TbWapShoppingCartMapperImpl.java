package com.liguo.hgl.proxydao.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbWapShoppingCartMapper;
import com.liguo.hgl.proxydao.dto.WapShoppingCartDto;
import com.liguo.hgl.proxydao.dto.WapShoppingCartInfoDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbWapShoppingCart;

@Repository
public class TbWapShoppingCartMapperImpl extends BaseMapperImpl<TbWapShoppingCart> implements TbWapShoppingCartMapper {

    public TbWapShoppingCartMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbWapShoppingCartMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbWapShoppingCart record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbWapShoppingCartMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbWapShoppingCart record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbWapShoppingCartMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbWapShoppingCartMapper.deleteByObject", parameter);
    }

    public int insert(TbWapShoppingCart record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbWapShoppingCartMapper.insert", record);
    }

    public int insertSelective(TbWapShoppingCart record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbWapShoppingCartMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbWapShoppingCartMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbWapShoppingCart> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbWapShoppingCartMapper.selectByObject", parameter);
    }

    public TbWapShoppingCart selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbWapShoppingCartMapper.selectByPrimaryKey", id);
        return obj != null ? (TbWapShoppingCart)obj : null;
    }
    
	@Override
	public int batchInsertSelective(List<TbWapShoppingCart> record) {
		 return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbWapShoppingCartMapper.batchInsertSelective", record);
	}

	@Override
	public List<WapShoppingCartDto> selectByCart(Criteria parameter) throws RuntimeException {
		return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbWapShoppingCartMapper.selectByCart", parameter);
    }

	@Override
	public int deleteByCartObject(Criteria example) throws RuntimeException {
		 return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbWapShoppingCartMapper.deleteByCartObject", example);
	}

	@Override
	public int deleteByCartInfoObject(Criteria example) {
		return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbWapShoppingCartMapper.deleteByCartInfoObject", example);
	}

	@Override
	public int batchUpdateByPrimaryKey(List<TbWapShoppingCart> record) {
		  return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbWapShoppingCartMapper.batchUpdateByPrimaryKey", record);
	}

	@Override
	public int deleteAllByPrimaryKey(Criteria example) {
		return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbWapShoppingCartMapper.deleteAllByPrimaryKey", example);
	}
	
	@Override
    public List<WapShoppingCartInfoDto> selectByCartInfo(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbWapShoppingCartMapper.selectByCartInfo", parameter);
    }
}