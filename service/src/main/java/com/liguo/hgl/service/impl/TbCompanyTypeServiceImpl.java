package com.liguo.hgl.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.dao.TbCompanyTypeMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbCompanyType;
import com.liguo.hgl.service.TbCompanyTypeService;
import com.liguo.hgl.util.ImageUtil;
import com.liguo.hgl.util.StringUtil;

@Service
@Scope(value="prototype")
public class TbCompanyTypeServiceImpl implements TbCompanyTypeService {
    @Autowired
    private TbCompanyTypeMapper tbCompanyTypeMapper;

    private static final Logger logger = LoggerFactory.getLogger(TbCompanyTypeServiceImpl.class);

    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbCompanyTypeMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TbCompanyType selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbCompanyTypeMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TbCompanyType> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbCompanyTypeMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int deleteByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbCompanyTypeMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKeySelective(TbCompanyType record) throws RuntimeException {
        try {
            return this.tbCompanyTypeMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKey(TbCompanyType record) throws RuntimeException {
        try {
            return this.tbCompanyTypeMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int insertSelective(TbCompanyType record) throws RuntimeException {
        try {
            return this.tbCompanyTypeMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public int insertTbCompanyType(TbCompanyType record){
    	this.tbCompanyTypeMapper.insertSelective(record);
    	Integer id = record.getId();
    	if(!StringUtils.isBlank(record.getImgName())){
    		String newName = StringUtil.changeFileName(id.toString(),record.getImgName());
    		try{
    			ImageUtil.commonUploadNewImage(HglContants.CUSTOMER_TESTPATH + record.getImgName(), newName, null, HglContants.CUSTOMER_TESTPATH, HglContants.COMPANY_TYPE);
    		}catch(Exception e){
    			e.printStackTrace();
    		}
    		record.setImgName(newName);
    	}
    	record.setVersion(0);
    	updateByPrimaryKey(record);
        return id;
    	}
    
    public int updateTbCompanyTypeInfo(TbCompanyType record) throws RuntimeException {
    	if(!StringUtils.isBlank(record.getImgName())){
    		String newName = StringUtil.changeFileName(record.getId().toString(),record.getImgName());
    		try{
    			ImageUtil.commonUploadNewImage(HglContants.CUSTOMER_TESTPATH + record.getImgName(), newName, null, HglContants.CUSTOMER_TESTPATH, HglContants.COMPANY_TYPE);
    		}catch(Exception e){
    			e.printStackTrace();
    		}
    		record.setImgName(newName);
    	}
         return this.tbCompanyTypeMapper.updateByPrimaryKey(record);
    }
    
    public List<TbCompanyType> selectCompanyTypeInfo(){
    	return this.tbCompanyTypeMapper.selectCompanyTypeInfo();
    }
}