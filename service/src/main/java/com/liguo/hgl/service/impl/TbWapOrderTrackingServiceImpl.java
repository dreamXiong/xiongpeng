package com.liguo.hgl.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liguo.hgl.proxydao.dao.TbWapOrderTrackingMapper;
import com.liguo.hgl.proxydao.dto.WapOrderTrackingDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbWapOrderTracking;
import com.liguo.hgl.service.TbWapOrderTrackingService;

@Service
@Scope(value="prototype")
public class TbWapOrderTrackingServiceImpl implements TbWapOrderTrackingService {
    @Autowired
    private TbWapOrderTrackingMapper tbWapOrderTrackingMapper;

    private static final Logger logger = LoggerFactory.getLogger(TbWapOrderTrackingServiceImpl.class);

    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbWapOrderTrackingMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TbWapOrderTracking selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbWapOrderTrackingMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TbWapOrderTracking> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbWapOrderTrackingMapper.selectByObject(example);
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
			List<TbWapOrderTracking> ls = selectByObject(example); 
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
    }
*/
    public int deleteByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbWapOrderTrackingMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKeySelective(TbWapOrderTracking record) throws RuntimeException {
        try {
            return this.tbWapOrderTrackingMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKey(TbWapOrderTracking record) throws RuntimeException {
        try {
            return this.tbWapOrderTrackingMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int insertSelective(TbWapOrderTracking record) throws RuntimeException {
        try {
            return this.tbWapOrderTrackingMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

	@Override
	public List<WapOrderTrackingDto> selectByObjectDto(Criteria example) {
		 try {
            return this.tbWapOrderTrackingMapper.selectByObjectDto(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}

	@Override
	public List<WapOrderTrackingDto> selectByObjectStatus(Criteria example) {
		try {
            return this.tbWapOrderTrackingMapper.selectByObjectStatus(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}

    @Override
    public int insertOrderTrackIsBatch(Criteria example) throws RuntimeException {
        return this.tbWapOrderTrackingMapper.insertOrderTrackIsBatch(example);
    }
}