package com.liguo.hgl.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liguo.hgl.proxydao.dao.TbInventoryLockMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbInventoryLock;
import com.liguo.hgl.service.TbInventoryLockService;

@Service
@Scope(value="prototype")
public class TbInventoryLockServiceImpl implements TbInventoryLockService {
    @Autowired
    private TbInventoryLockMapper tbInventoryLockMapper;

    private static final Logger logger = LoggerFactory.getLogger(TbInventoryLockServiceImpl.class);

    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbInventoryLockMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TbInventoryLock selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbInventoryLockMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TbInventoryLock> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbInventoryLockMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

 /*   public DataPackage getDatapackage(Criteria example, int lines, int page) throws RuntimeException {
        try { 
			int totalRecords = countByObject(example); 
			Page pg = PageUtil.createPage(page, lines, totalRecords); 
			example.setMysqlOffset(pg.getBeginIndex()); 
			example.setMysqlLength(pg.getEveryPage()); 
			DataPackage result = new DataPackage(); 
			List<TbInventoryLock> ls = selectByObject(example); 
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
            return this.tbInventoryLockMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKeySelective(TbInventoryLock record) throws RuntimeException {
        try {
            return this.tbInventoryLockMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKey(TbInventoryLock record) throws RuntimeException {
        try {
            return this.tbInventoryLockMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int insertSelective(TbInventoryLock record) throws RuntimeException {
        try {
            return this.tbInventoryLockMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public int  batchInsertSelective(List<TbInventoryLock> record) throws RuntimeException {
    	 try {
             return this.tbInventoryLockMapper.batchInsertSelective(record);
         } catch (Exception e) {
             throw new RuntimeException(e);
         }
    	
    }
    public int batchDeleteTbInventoryLock(List<Integer> record){
    	 return this.tbInventoryLockMapper.batchDeleteTbInventoryLock(record);
    }
}