package com.liguo.hgl.service.impl;

import com.liguo.hgl.proxydao.dao.TbWapAddressMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbWapAddress;
import com.liguo.hgl.service.TbWapAddressService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(value="prototype")
public class TbWapAddressServiceImpl implements TbWapAddressService {
    @Autowired
    private TbWapAddressMapper tbWapAddressMapper;

    private static final Logger logger = LoggerFactory.getLogger(TbWapAddressServiceImpl.class);

    /**
     * 根据条件获取总行数
     */
    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbWapAddressMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据主键查询记录
     */
    public TbWapAddress selectByPrimaryKey(Criteria parameter) throws RuntimeException {
        try {
            return this.tbWapAddressMapper.selectByPrimaryKey(parameter);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据指定条件查询记录
     */
    public List<TbWapAddress> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbWapAddressMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据主键删除记录
     */
    public int deleteByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbWapAddressMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据主键更新属性不为空的记录
     */
    public int updateByPrimaryKeySelective(TbWapAddress record) throws RuntimeException {
        try {
            return this.tbWapAddressMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据主键更新记录
     */
    public int updateByPrimaryKey(TbWapAddress record) throws RuntimeException {
        try {
            return this.tbWapAddressMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 保存属性不为空的记录
     */
    public int insertSelective(TbWapAddress record) throws RuntimeException {
        try {
            return this.tbWapAddressMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * 保存属性不为空的记录
     */
	@Override
	public int insert(TbWapAddress record) throws RuntimeException {
		 try {
	            return this.tbWapAddressMapper.insert(record);
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	}
	   
    /**
     * 根据条件删除记录
     */
	@Override
	public int deleteByObject(Criteria example) {
		try {
            return this.tbWapAddressMapper.deleteByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}

    /**
     * 根据id查询出默认地址
     * @param id
     * @param newId
     * @param userId
     * @return
     */
	public int defaultAddress(String id,String newId,Integer userId) {
		try {
			TbWapAddress record = new TbWapAddress();
			record.setId(Integer.parseInt(id));
			record.setCreateBy(userId);
			record.setCheckFlag(1);  //设置成不默认地址
			int count = tbWapAddressMapper.updateByDefaultPrimaryKey(record);
			if(count >0){
				record.setId(Integer.parseInt(newId));
				record.setCheckFlag(0); //默认地址
				return tbWapAddressMapper.updateByDefaultPrimaryKey(record);
			}
			return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}
	
    /**
     * 根据条件查询出地址对象
     * @param parameter
     * @return
     */
	 @Override
	 public TbWapAddress selectByDefaultObject(Criteria parameter) {
		try {
            return this.tbWapAddressMapper.selectByDefaultObject(parameter);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	 }
}