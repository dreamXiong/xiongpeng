package com.liguo.hgl.service.impl;

import com.liguo.hgl.proxydao.dao.TbDeliveryTermsMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbDeliveryTerms;
import com.liguo.hgl.service.TbDeliveryTermsService;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(value="prototype")
public class TbDeliveryTermsServiceImpl implements TbDeliveryTermsService {
    @Autowired
    private TbDeliveryTermsMapper tbDeliveryTermsMapper;

    private static final Logger logger = LoggerFactory.getLogger(TbDeliveryTermsServiceImpl.class);

    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbDeliveryTermsMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TbDeliveryTerms selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbDeliveryTermsMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TbDeliveryTerms> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbDeliveryTermsMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int deleteByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbDeliveryTermsMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKeySelective(TbDeliveryTerms record) throws RuntimeException {
        try {
            return this.tbDeliveryTermsMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKey(TbDeliveryTerms record) throws RuntimeException {
        try {
            return this.tbDeliveryTermsMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int insertSelective(TbDeliveryTerms record) throws RuntimeException {
        try {
            return this.tbDeliveryTermsMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

	@Override
	public int insert(TbDeliveryTerms record) throws RuntimeException {
		try{
			return this.tbDeliveryTermsMapper.insert(record);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public List<TbDeliveryTerms> getDeliveryTerms(List<TbDeliveryTerms> deliveryTerms) throws RuntimeException {
		try{
			List<TbDeliveryTerms> reList=new ArrayList<TbDeliveryTerms>();
			int maxDistance=0;//当前最低距离
			int minDistance=0;//当前最低距离
			int maxAmount=0;//当前最大金额
			int minAmount=0;//当前最小金额
			for (TbDeliveryTerms tbDeliveryTerms : deliveryTerms) {
				tbDeliveryTerms.getMinAmount();
			}
			return reList;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
}