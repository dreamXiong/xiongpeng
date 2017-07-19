package com.liguo.hgl.proxydao.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbOrderDetailMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.OrderGroupDetailDto;
import com.liguo.hgl.proxydao.model.TbOrderDetail;
import com.liguo.hgl.proxydao.model.WapProductDto;

@Repository
public class TbOrderDetailMapperImpl extends BaseMapperImpl<TbOrderDetail> implements TbOrderDetailMapper {

    public TbOrderDetailMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbOrderDetailMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbOrderDetail record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbOrderDetailMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbOrderDetail record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbOrderDetailMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbOrderDetailMapper.deleteByObject", parameter);
    }

    public int insert(TbOrderDetail record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbOrderDetailMapper.insert", record);
    }

    public int insertSelective(TbOrderDetail record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbOrderDetailMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbOrderDetailMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbOrderDetail> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbOrderDetailMapper.selectByObject", parameter);
    }
    @Override
    public TbOrderDetail selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbOrderDetailMapper.selectByPrimaryKey", id);
        return obj != null ? (TbOrderDetail)obj : null;
    }
    @Override
    public int updateSaleInventorySendGoods(Integer orderGroupId){
    	return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbOrderDetailMapper.updateSaleInventorySendGoods", orderGroupId);
    }

    @Override
    public int updateSaleInventoryPayment(Integer orderGroupId){
    	return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbOrderDetailMapper.updateSaleInventoryPayment", orderGroupId);
    }
    
    @Override
    public int updateSaleInventoryStopOrder(Integer orderGroupId){
    	return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbOrderDetailMapper.updateSaleInventoryStopOrder", orderGroupId);
    }
    @Override
    public int stopOrderForSendGoods(Integer orderGroupId){
    	return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbOrderDetailMapper.stopOrderForSendGoods", orderGroupId);
    }
    
    @Override
    public int updateSaleInventoryConfigGoods(Integer orderGroupId){
    	return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbOrderDetailMapper.updateSaleInventoryConfigGoods", orderGroupId);
    }

	@Override
	public int batchInsert(List<TbOrderDetail> record) throws RuntimeException {
		return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbOrderDetailMapper.batchInsert", record);
	}
	@Override
	public List<OrderGroupDetailDto>selectOrderDetailBuyOrderID(Integer orderGroupId){
		return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbOrderDetailMapper.selectOrderDetailBuyOrderID", orderGroupId);
	}
	@Override
	public List<WapProductDto> selectProductIdByOId(Integer orderGroupId){
		return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbProductMapper.wapProductInfo", orderGroupId);
	}
	
	@Override
	public OrderGroupDetailDto selectOrderDetailDto(Integer id) {
		
		return this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbOrderDetailMapper.selectOrderDetailDto", id);
	}

	@Override
	public OrderGroupDetailDto selecOrderDetailTotalAmount(Integer orderGroupId) {
		
		return this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbOrderDetailMapper.selecOrderDetailTotalAmount", orderGroupId);
	}	
}