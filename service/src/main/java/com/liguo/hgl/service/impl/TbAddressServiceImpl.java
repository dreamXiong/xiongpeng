package com.liguo.hgl.service.impl;

import com.liguo.hgl.proxydao.dao.TbAddressMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbAddress;
import com.liguo.hgl.service.TbAddressService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(value="prototype")
public class TbAddressServiceImpl implements TbAddressService {
    @Autowired
    private TbAddressMapper tbAddressMapper;

    private static final Logger logger = LoggerFactory.getLogger(TbAddressServiceImpl.class);

    /**
     * 根据条件查询出条数
     */
    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbAddressMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据id查询出对象
     */
    public TbAddress selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbAddressMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据条件查询返回集合
     */
    public List<TbAddress> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbAddressMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据id删除
     */
    public int deleteByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbAddressMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据对象选择更新
     */
    public int updateByPrimaryKeySelective(TbAddress record) throws RuntimeException {
        try {
            return this.tbAddressMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据对象更新
     */
    public int updateByPrimaryKey(TbAddress record) throws RuntimeException {
        try {
            return this.tbAddressMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 选择插入
     */
    public int insertSelective(TbAddress record) throws RuntimeException {
        try {
            return this.tbAddressMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 插入
     */
	@Override
	public int insert(TbAddress record) throws RuntimeException {
		 try {
	            return this.tbAddressMapper.insert(record);
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	}

	/**
	 * 根据条件删除
	 */
	@Override
	public int deleteByObject(Criteria example) {
		try {
            return this.tbAddressMapper.deleteByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}
	
	/**
	 * 设置默认地址
	 */
	public int defaultAddress(String id,String newId,Integer userId) {
		try {
			TbAddress record = new TbAddress();
			record.setId(Integer.parseInt(id));
			record.setCreateBy(userId);
			record.setCheckFlag(1);  //设置成不默认地址
			int count = tbAddressMapper.updateByPrimaryKeySelective(record);
			if(count >0){
				record.setId(Integer.parseInt(newId));
				record.setCheckFlag(0); //默认地址
				return tbAddressMapper.updateByPrimaryKeySelective(record);
			}
			return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}
}