package com.liguo.hgl.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liguo.hgl.proxydao.dao.TbCompanyConsultMapper;
import com.liguo.hgl.proxydao.dto.TbCompanyConsultDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbCompanyConsult;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.TbCompanyConsultService;

@Service
@Scope(value="prototype")
public class TbCompanyConsultServiceImpl implements TbCompanyConsultService {
    @Autowired
    private TbCompanyConsultMapper tbCompanyConsultMapper;

    private static final Logger logger = LoggerFactory.getLogger(TbCompanyConsultServiceImpl.class);

    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbCompanyConsultMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TbCompanyConsult selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbCompanyConsultMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TbCompanyConsult> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbCompanyConsultMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int deleteByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbCompanyConsultMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKeySelective(TbCompanyConsult record) throws RuntimeException {
        try {
            return this.tbCompanyConsultMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKey(TbCompanyConsult record) throws RuntimeException {
        try {
            return this.tbCompanyConsultMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int insertSelective(TbCompanyConsult record) throws RuntimeException {
        try {
            return this.tbCompanyConsultMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<TbCompanyConsultDto> selectByObjectPage(Criteria example,PageDto page){
    	  return this.tbCompanyConsultMapper.selectByObjectPage(example,page);
    }
}