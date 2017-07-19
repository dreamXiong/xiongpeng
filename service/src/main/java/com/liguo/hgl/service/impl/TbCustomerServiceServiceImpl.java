package com.liguo.hgl.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liguo.hgl.proxydao.dao.TbCustomerServiceMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.CustomerServiceDto;
import com.liguo.hgl.proxydao.model.TbCustomerService;
import com.liguo.hgl.proxydao.model.TbProduct;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.TbCustomerServiceService;
import com.liguo.hgl.util.ImageUtil;
import com.liguo.hgl.util.StringUtil;

@Service
@Scope(value="prototype")
public class TbCustomerServiceServiceImpl implements TbCustomerServiceService {
    @Autowired
    private TbCustomerServiceMapper tbCustomerServiceMapper;

    private static final Logger logger = LoggerFactory.getLogger(TbCustomerServiceServiceImpl.class);

    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbCustomerServiceMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TbCustomerService selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbCustomerServiceMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TbCustomerService> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbCustomerServiceMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int deleteByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbCustomerServiceMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKeySelective(TbCustomerService record) throws RuntimeException {
        try {
            return this.tbCustomerServiceMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKey(TbCustomerService record) throws RuntimeException {
        try {
            return this.tbCustomerServiceMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int insertSelective(TbCustomerService record) throws RuntimeException {
        try {
            return this.tbCustomerServiceMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<CustomerServiceDto> selectInfoListByName(String name, PageDto page){
    	Criteria criteria = new Criteria();
    	criteria.put("name", name);
    	return tbCustomerServiceMapper.selectInfoListByName(criteria,page);
    }
    public List<CustomerServiceDto> selectInfoListByName(Criteria criteria){
    /*	Criteria criteria = new Criteria();
    	criteria.put("name", name);
    	criteria.put("wapSize", size);*/
    	return tbCustomerServiceMapper.selectInfoListByName(criteria);
    }
    public int insertCustomerService(TbCustomerService record) throws RuntimeException {
        try {
        	this.tbCustomerServiceMapper.insertSelective(record);
        	Integer id = record.getId();
        	if(!StringUtils.isBlank(record.getCimgOne())){
        		String newName = StringUtil.changeFileName(record.getCimgOne().split("_")[0],record.getCimgOne());
        		ImageUtil.uploadNewImage(record.getCimgOne(), newName, id);
        		record.setCimgOne(newName);
        	}
        	if(!StringUtils.isBlank(record.getCimgTwo())){
        		String newName = StringUtil.changeFileName(record.getCimgTwo().split("_")[0],record.getCimgTwo());
        		ImageUtil.uploadNewImage(record.getCimgTwo(), newName, id);
        		record.setCimgTwo(newName);
        	}
        	if(!StringUtils.isBlank(record.getCimgThree())){
        		String newName = StringUtil.changeFileName(record.getCimgThree().split("_")[0],record.getCimgThree());
        		ImageUtil.uploadNewImage(record.getCimgThree(), newName, id);
        		record.setCimgThree(newName);
        	}
        	record.setVersion(0);
        	updateByPrimaryKey(record);
            return id;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public CustomerServiceDto selectInfoById(Integer id){
    	return tbCustomerServiceMapper.selectInfoById(id);
    }

	@Override
	public int UpdateCustomerService(TbCustomerService record) throws RuntimeException {
    	Integer id = record.getId();
    	TbCustomerService tbProduct = selectByPrimaryKey(record.getId());
        try {
        	if((!StringUtils.isBlank(record.getCimgOne())) && !tbProduct.getCimgOne().equals(record.getCimgOne())){
        		String newName = StringUtil.changeFileName(record.getCimgOne().split("_")[0],record.getCimgOne());
        		ImageUtil.uploadNewImage(record.getCimgOne(), newName, id);
        		record.setCimgOne(newName);
        	}
        	if((!StringUtils.isBlank(record.getCimgTwo()))&& !tbProduct.getCimgTwo().equals(record.getCimgTwo())){
        		String newName = StringUtil.changeFileName(record.getCimgTwo().split("_")[0],record.getCimgTwo());
        		ImageUtil.uploadNewImage(record.getCimgTwo(), newName, id);
        		record.setCimgTwo(newName);
        	}
        	if((!StringUtils.isBlank(record.getCimgThree()))&& !tbProduct.getCimgThree().equals(record.getCimgThree())){
        		String newName = StringUtil.changeFileName(record.getCimgThree().split("_")[0],record.getCimgThree());
        		ImageUtil.uploadNewImage(record.getCimgThree(), newName, id);
        		record.setCimgThree(newName);
        	}
        	updateByPrimaryKeySelective(record);
            return id;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}