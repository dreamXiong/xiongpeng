package com.liguo.hgl.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liguo.hgl.proxydao.dao.TbOrderTrackingMapper;
import com.liguo.hgl.proxydao.dto.OrderTrackingDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbOrderTracking;
import com.liguo.hgl.service.TbOrderTrackingService;

@Service
@Scope(value="prototype")
public class TbOrderTrackingServiceImpl implements TbOrderTrackingService {
    @Autowired
    private TbOrderTrackingMapper tbOrderTrackingMapper;

    private static final Logger logger = LoggerFactory.getLogger(TbOrderTrackingServiceImpl.class);

    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbOrderTrackingMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TbOrderTracking selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbOrderTrackingMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TbOrderTracking> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbOrderTrackingMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

  /*  public DataPackage getDatapackage(Criteria example, int lines, int page) throws RuntimeException {
        try { 
			int totalRecords = countByObject(example); 
			Page pg = PageUtil.createPage(page, lines, totalRecords); 
			example.setMysqlOffset(pg.getBeginIndex()); 
			example.setMysqlLength(pg.getEveryPage()); 
			DataPackage result = new DataPackage(); 
			List<TbOrderTracking> ls = selectByObject(example); 
			result.setTotalCount(ls.size()); 
			result.setPageindex(page); 
			result.setDatas(ls); 
			Result rs = new Result(); 
			rs.setPage(pg); 
			rs.setContent(ls); 
			result.setResult(rs); 
			return result; 
		} catch (Exception e) { 
			throw new RuntimeException(e); 
		}
    }*/

    public int deleteByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbOrderTrackingMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKeySelective(TbOrderTracking record) throws RuntimeException {
        try {
            return this.tbOrderTrackingMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKey(TbOrderTracking record) throws RuntimeException {
        try {
            return this.tbOrderTrackingMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int insertSelective(TbOrderTracking record) throws RuntimeException {
        try {
            return this.tbOrderTrackingMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

	@Override
	public List<TbOrderTracking> selectByObjectStatus(Criteria example) {
		
		return this.tbOrderTrackingMapper.selectByObjectStatus(example);
	}

	@Override
	public List<OrderTrackingDto> selectByObjectDto(Criteria example) {
		
		return this.tbOrderTrackingMapper.selectByObjectDto(example);
	}
}