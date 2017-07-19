package com.liguo.hgl.proxydao.dao.impl;

import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbCityLowestPriceMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbCityLowestPrice;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class TbCityLowestPriceMapperImpl extends BaseMapperImpl<TbCityLowestPrice> implements TbCityLowestPriceMapper {

    public TbCityLowestPriceMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbCityLowestPriceMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbCityLowestPrice record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbCityLowestPriceMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbCityLowestPrice record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbCityLowestPriceMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbCityLowestPriceMapper.deleteByObject", parameter);
    }

    public int insert(TbCityLowestPrice record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbCityLowestPriceMapper.insert", record);
    }

    public int insertSelective(TbCityLowestPrice record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbCityLowestPriceMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbCityLowestPriceMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbCityLowestPrice> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbCityLowestPriceMapper.selectByObject", parameter);
    }
    @Override
    public List<String> selectCityIds() {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbCityLowestPriceMapper.selectCityIds");
    }

    public TbCityLowestPrice selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbCityLowestPriceMapper.selectByPrimaryKey", id);
        return obj != null ? (TbCityLowestPrice)obj : null;
    }

    @Override
    public List<Map<String, Object>> findLowestPriceBatchByCityId(List<String> list) throws RuntimeException {
        
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbCityLowestPriceMapper.findLowestPriceBatchByCityId", list);
    }
    @Override
    public void insertLowestPriceBatch(List<String> list) throws RuntimeException {
        
         this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbCityLowestPriceMapper.insertLowestPriceBatch", list);
    }
    
    @Override
    public int deleteLowestPrice_Batch(List<String> list) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbCityLowestPriceMapper.deleteLowestPrice_Batch", list);
    }
}