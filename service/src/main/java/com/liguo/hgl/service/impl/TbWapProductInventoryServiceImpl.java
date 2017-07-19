package com.liguo.hgl.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liguo.hgl.proxydao.dao.TbWapProductInventoryMapper;
import com.liguo.hgl.proxydao.dto.TbWapProductInventoryDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.ProductImport;
import com.liguo.hgl.proxydao.model.TbWapProductInventory;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.TbWapProductInventoryService;

@Service
@Scope(value="prototype")
public class TbWapProductInventoryServiceImpl implements TbWapProductInventoryService {
    @Autowired
    private TbWapProductInventoryMapper tbWapProductInventoryMapper;

    private static final Logger logger = LoggerFactory.getLogger(TbWapProductInventoryServiceImpl.class);

    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbWapProductInventoryMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TbWapProductInventory selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbWapProductInventoryMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TbWapProductInventory> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbWapProductInventoryMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int deleteByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbWapProductInventoryMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKeySelective(TbWapProductInventory record) throws RuntimeException {
        try {
            return this.tbWapProductInventoryMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKey(TbWapProductInventory record) throws RuntimeException {
        try {
            return this.tbWapProductInventoryMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int insertSelective(TbWapProductInventory record) throws RuntimeException {
        try {
            return this.tbWapProductInventoryMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
	@Override
	public List<TbWapProductInventoryDto> selectByCriteria(Criteria criteria) throws RuntimeException {
		try {
			if (criteria.getOrderByClause() == null) {
				criteria.setOrderByClause("create_time");
				criteria.setOrderByClauseDesc("desc");
			}
			return this.tbWapProductInventoryMapper.selectByCriteria(criteria);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int batchUpOrdownFrame(Criteria parameter) throws RuntimeException {
		try {
            return this.tbWapProductInventoryMapper.batchUpOrdownFrame(parameter);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}

	@Override
	public List<TbWapProductInventoryDto> selectByCriteria(Criteria criteria,
			PageDto page) throws RuntimeException {
		try {
			return this.tbWapProductInventoryMapper.selectByCriteria(criteria,page);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int markDeleteByPrimaryKey(Criteria parameter) {
		try {
            return this.tbWapProductInventoryMapper.markDeleteByPrimaryKey(parameter);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}
	
	public TbWapProductInventoryDto selectById(Integer id) throws RuntimeException {
		try {
			return this.tbWapProductInventoryMapper.selectById(id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public int batchUpdatePrice(String[] id, String asPrice,String priceMethod, String price, String user)throws RuntimeException {
		Criteria criteria = new Criteria();
		criteria.put("asPrice", asPrice);
		criteria.put("priceMethod", priceMethod);
		if(!price.startsWith("-")){
            price = "+"+price;
        }
		criteria.put("price", price);
		criteria.put("user", user);
		criteria.put("inventoryIds", id);
		return this.tbWapProductInventoryMapper.batchUpdatePrice(criteria);
	}

	@Override
	public int batchUpdateStatus(Criteria parameter) throws RuntimeException {
		try {
			return this.tbWapProductInventoryMapper.batchUpdateStatus(parameter);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int insert(TbWapProductInventory record) {
		try {
            return this.tbWapProductInventoryMapper.insert(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}
	
	@Override
	public int batchInsertSelective(List<TbWapProductInventory> twis){
		return this.tbWapProductInventoryMapper.batchInsertSelective(twis);
	}
	
	@Override
	public int insertBatchInventory(List<TbWapProductInventory> twis){
	    return this.tbWapProductInventoryMapper.insertBatchInventory(twis);
	}

    @Override
    public List<ProductImport> findProNameAndCodeLimit(Criteria criteria) {
        
        return this.tbWapProductInventoryMapper.findProNameAndCodeLimit(criteria);
    }

	@Override
	public TbWapProductInventoryDto selectByProductNameCriteria(Criteria criteria) {
		 return this.tbWapProductInventoryMapper.selectByProductNameCriteria(criteria);
	}
	
}