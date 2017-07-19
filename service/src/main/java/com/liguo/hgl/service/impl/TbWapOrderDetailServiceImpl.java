package com.liguo.hgl.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liguo.hgl.proxydao.dao.TbWapOrderDetailMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbWapOrderDetail;
import com.liguo.hgl.service.TbWapOrderDetailService;

@Service
@Scope(value="prototype")
public class TbWapOrderDetailServiceImpl implements TbWapOrderDetailService {
    @Autowired
    private TbWapOrderDetailMapper tbWapOrderDetailMapper;

    private static final Logger logger = LoggerFactory.getLogger(TbWapOrderDetailServiceImpl.class);

    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbWapOrderDetailMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TbWapOrderDetail selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbWapOrderDetailMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TbWapOrderDetail> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbWapOrderDetailMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

   /* public DataPackage getDatapackage(Criteria example, int lines, int page) throws RuntimeException {
        try { 
			int totalRecords = countByObject(example); 
			Page pg = PageUtil.createPage(page, lines, totalRecords); 
			example.setMysqlOffset(pg.getBeginIndex()); 
			example.setMysqlLength(pg.getEveryPage()); 
			DataPackage result = new DataPackage(); 
			List<TbWapOrderDetail> ls = selectByObject(example); 
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
            return this.tbWapOrderDetailMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKeySelective(TbWapOrderDetail record) throws RuntimeException {
        try {
            return this.tbWapOrderDetailMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKey(TbWapOrderDetail record) throws RuntimeException {
        try {
            return this.tbWapOrderDetailMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int insertSelective(TbWapOrderDetail record) throws RuntimeException {
        try {
            return this.tbWapOrderDetailMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

	@Override
	public Double selectTotalAmount(Criteria criteria) {
		
		return this.tbWapOrderDetailMapper.selectTotalAmount(criteria);
	}
}