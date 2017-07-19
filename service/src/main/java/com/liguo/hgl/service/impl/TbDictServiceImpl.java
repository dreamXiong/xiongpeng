package com.liguo.hgl.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.liguo.hgl.proxydao.dao.TbDictMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbDict;
import com.liguo.hgl.service.TbDictService;

@Service
@Scope(value="prototype")
public class TbDictServiceImpl implements TbDictService {
    @Autowired
    private TbDictMapper tbDictMapper;

    private static final Logger logger = LoggerFactory.getLogger(TbDictServiceImpl.class);

    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbDictMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TbDict selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbDictMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TbDict> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbDictMapper.selectByObject(example);
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
			List<TbDict> ls = selectByObject(example); 
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
            return this.tbDictMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKeySelective(TbDict record) throws RuntimeException {
        try {
            return this.tbDictMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKey(TbDict record) throws RuntimeException {
        try {
            return this.tbDictMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int insertSelective(TbDict record) throws RuntimeException {
        try {
            return this.tbDictMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}