package com.liguo.hgl.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liguo.hgl.proxydao.dao.TbWapInventoryLockMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbWapInventoryLock;
import com.liguo.hgl.service.TbWapInventoryLockService;

@Service
@Scope(value="prototype")
public class TbWapInventoryLockServiceImpl implements TbWapInventoryLockService {
    @Autowired
    private TbWapInventoryLockMapper tbWapInventoryLockMapper;

    private static final Logger logger = LoggerFactory.getLogger(TbWapInventoryLockServiceImpl.class);

    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbWapInventoryLockMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TbWapInventoryLock selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbWapInventoryLockMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TbWapInventoryLock> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbWapInventoryLockMapper.selectByObject(example);
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
			List<TbWapInventoryLock> ls = selectByObject(example); 
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
            return this.tbWapInventoryLockMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKeySelective(TbWapInventoryLock record) throws RuntimeException {
        try {
            return this.tbWapInventoryLockMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKey(TbWapInventoryLock record) throws RuntimeException {
        try {
            return this.tbWapInventoryLockMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int insertSelective(TbWapInventoryLock record) throws RuntimeException {
        try {
            return this.tbWapInventoryLockMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}