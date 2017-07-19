package com.liguo.hgl.service.impl;

import com.liguo.hgl.proxydao.dao.TbManualorderDetailMapper;
import com.liguo.hgl.proxydao.dto.ManualOrderDetailDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbManualorderDetail;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.TbManualorderDetailService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(value="prototype")
public class TbManualorderDetailServiceImpl implements TbManualorderDetailService {
    @Autowired
    private TbManualorderDetailMapper tbManualorderDetailMapper;

    private static final Logger logger = LoggerFactory.getLogger(TbManualorderDetailServiceImpl.class);

    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbManualorderDetailMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TbManualorderDetail selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbManualorderDetailMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TbManualorderDetail> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbManualorderDetailMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int deleteByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbManualorderDetailMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKeySelective(TbManualorderDetail record) throws RuntimeException {
        try {
            return this.tbManualorderDetailMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKey(TbManualorderDetail record) throws RuntimeException {
        try {
            return this.tbManualorderDetailMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int insertSelective(TbManualorderDetail record) throws RuntimeException {
        try {
            return this.tbManualorderDetailMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

	@Override
	public List<ManualOrderDetailDto> selectObjectByPage(Criteria example,
			PageDto page) throws RuntimeException {		
		try{
			return this.tbManualorderDetailMapper.selectObjectByPage(example, page);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
}