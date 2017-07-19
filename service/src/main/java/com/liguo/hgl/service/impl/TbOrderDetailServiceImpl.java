package com.liguo.hgl.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.dao.TbOrderDetailMapper;
import com.liguo.hgl.proxydao.dao.TbWapProductInventoryMapper;
import com.liguo.hgl.proxydao.dao.TbWapProductMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.OrderGroupDetailDto;
import com.liguo.hgl.proxydao.model.ProductInventory;
import com.liguo.hgl.proxydao.model.TbOrderDetail;
import com.liguo.hgl.proxydao.model.TbWapProduct;
import com.liguo.hgl.proxydao.model.TbWapProductInventory;
import com.liguo.hgl.proxydao.model.WapProductDto;
import com.liguo.hgl.service.TbOrderDetailService;
import com.liguo.hgl.util.BeanCopyUtil;
import com.liguo.hgl.util.ImageUtil;

@Service
@Scope(value="prototype")
public class TbOrderDetailServiceImpl implements TbOrderDetailService {
    @Autowired
    private TbOrderDetailMapper tbOrderDetailMapper;
    
    @Autowired
    private TbWapProductMapper tbWapProductMapper;
    
    @Autowired
    TbWapProductInventoryMapper tbWapProductInventoryMapper;

    private static final Logger logger = LoggerFactory.getLogger(TbOrderDetailServiceImpl.class);

    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbOrderDetailMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TbOrderDetail selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbOrderDetailMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TbOrderDetail> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbOrderDetailMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int deleteByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbOrderDetailMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKeySelective(TbOrderDetail record) throws RuntimeException {
        try {
            return this.tbOrderDetailMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKey(TbOrderDetail record) throws RuntimeException {
        try {
            return this.tbOrderDetailMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int insertSelective(TbOrderDetail record) throws RuntimeException {
        try {
            return this.tbOrderDetailMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public int updateSaleInventorySendGoods(Integer orderGroupId){
      return this.tbOrderDetailMapper.updateSaleInventorySendGoods(orderGroupId);
    }
    
    @Override
    public int updateSaleInventoryPayment(Integer orderGroupId){
    	return this.tbOrderDetailMapper.updateSaleInventoryPayment(orderGroupId);
    }
    
    @Override
    public int updateSaleInventoryStopOrder(Integer orderGroupId){
    	return this.tbOrderDetailMapper.updateSaleInventoryStopOrder(orderGroupId);
    }
    @Override
    public int stopOrderForSendGoods(Integer orderGroupId){
    	return this.tbOrderDetailMapper.stopOrderForSendGoods(orderGroupId);
    }
    @Override
    public int updateSaleInventoryConfigGoods(Integer orderGroupId){
    	return this.tbOrderDetailMapper.updateSaleInventoryConfigGoods(orderGroupId);
    }
    @Override
    public List<OrderGroupDetailDto>selectOrderDetailBuyOrderID(Integer orderGroupId){
    	return this.tbOrderDetailMapper.selectOrderDetailBuyOrderID(orderGroupId);
    }
    
    public List<WapProductDto> selectProductIdByOId(Integer orderGroupId){
    	return this.tbOrderDetailMapper.selectProductIdByOId(orderGroupId);
    }
    
    public void batchWapInventory(Integer orderGroupId,Integer shopId){
    	//查询订单下所有的品牌
    	List<WapProductDto> pList = selectProductIdByOId(orderGroupId);
    	for(WapProductDto p:pList){
    		//将购买的产品插入wap表中的产品表
    		TbWapProduct t = new TbWapProduct();
    		try{
    			BeanCopyUtil.CopyBeanToBean(p,t);
    			t.setId(null);
    			t.setShopFlag(1);
    			t.setShopId(shopId);
    		}catch(Exception e){
    			e.printStackTrace();
    		}
    		tbWapProductMapper.insert(t);
    		try{
    			//把产品图片copy到wap文件目录中
    			ImageUtil.copyPathChangeName(HglContants.WAP_PRODUCT,HglContants.PRODUCT_PATH+p.getId(),t.getId());
    		}catch(Exception e){
    			e.printStackTrace();
    		}
    		List<TbWapProductInventory> iList = new ArrayList<TbWapProductInventory>();
    		for(ProductInventory i : p.getProductInventoryList() ){
    			TbWapProductInventory twi = new TbWapProductInventory();
    			try{
    				BeanCopyUtil.CopyBeanToBean(i,twi);
        		}catch(Exception e){
        			e.printStackTrace();
        		}
    			twi.setProductId(t.getId());
    			//将占用量设置为0
    			twi.setUnsaleInventory(0);
    			twi.setInstockPrice(i.getOutstockPrice());
    			twi.setSalesPrice(i.getOutstockPrice());
    			
    			iList.add(twi);
    		}
    		tbWapProductInventoryMapper.batchInsertSelective(iList);
    	}
    }
    
    @Override
	public OrderGroupDetailDto selectOrderDetailDto(Integer id) {
		
		return this.tbOrderDetailMapper.selectOrderDetailDto(id);
	}

	@Override
	public OrderGroupDetailDto selecOrderDetailTotalAmount(Integer orderGroupId) {
		
		return this.tbOrderDetailMapper.selecOrderDetailTotalAmount(orderGroupId);
	}	
}