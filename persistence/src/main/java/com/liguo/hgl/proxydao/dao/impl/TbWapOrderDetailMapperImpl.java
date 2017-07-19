package com.liguo.hgl.proxydao.dao.impl;

import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbWapOrderDetailMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbWapOrderDetail;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class TbWapOrderDetailMapperImpl extends BaseMapperImpl<TbWapOrderDetail> implements TbWapOrderDetailMapper {

    public TbWapOrderDetailMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbWapOrderDetailMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbWapOrderDetail record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbWapOrderDetailMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbWapOrderDetail record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbWapOrderDetailMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbWapOrderDetailMapper.deleteByObject", parameter);
    }

    public int insert(TbWapOrderDetail record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbWapOrderDetailMapper.insert", record);
    }

    public int insertSelective(TbWapOrderDetail record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbWapOrderDetailMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbWapOrderDetailMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbWapOrderDetail> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbWapOrderDetailMapper.selectByObject", parameter);
    }

    public TbWapOrderDetail selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbWapOrderDetailMapper.selectByPrimaryKey", id);
        return obj != null ? (TbWapOrderDetail)obj : null;
    }

	@Override
	public int batchInsert(List<TbWapOrderDetail> record) throws RuntimeException {
		return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbWapOrderDetailMapper.batchInsert", record);
	}
	
	@Override 
    public int stopOrderForSendGoods(Integer orderGroupId){
    	return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbWapOrderDetailMapper.stopOrderForSendGoods", orderGroupId);
    }
	
	@Override 
    public int updateSaleInventoryStopOrder(Integer orderGroupId){
    	return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbWapOrderDetailMapper.updateSaleInventoryStopOrder", orderGroupId);
    }
	 @Override
    public int shopSendGoods(Integer orderGroupId){
    	return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbWapOrderDetailMapper.shopSendGoods", orderGroupId);
    }
	 @Override
	public int updateSaleInventoryPayment(Integer orderGroupId){
		 return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbWapOrderDetailMapper.updateSaleInventoryPayment", orderGroupId);
	 }

	@Override
	public Double selectTotalAmount(Criteria criteria){
		
		return this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbWapOrderDetailMapper.selectTotalAmount", criteria);
	}
}