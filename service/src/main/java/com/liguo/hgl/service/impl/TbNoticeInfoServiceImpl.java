package com.liguo.hgl.service.impl;

import com.liguo.hgl.proxydao.dao.TbNoticeInfoMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbBrand;
import com.liguo.hgl.proxydao.model.TbNoticeInfo;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.TbNoticeInfoService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(value="prototype")
public class TbNoticeInfoServiceImpl implements TbNoticeInfoService {
    @Autowired
    private TbNoticeInfoMapper tbNoticeInfoMapper;

    private static final Logger logger = LoggerFactory.getLogger(TbNoticeInfoServiceImpl.class);

    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbNoticeInfoMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TbNoticeInfo selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbNoticeInfoMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TbNoticeInfo> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbNoticeInfoMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

   
    /*
    public DataPackage getDatapackage(Criteria example, int lines, int page) throws RuntimeException {
        try { 
			int totalRecords = countByObject(example); 
			Page pg = PageUtil.createPage(page, lines, totalRecords); 
			example.setMysqlOffset(pg.getBeginIndex()); 
			example.setMysqlLength(pg.getEveryPage()); 
			DataPackage result = new DataPackage(); 
			List<TbNoticeInfo> ls = selectByObject(example); 
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
            return this.tbNoticeInfoMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKeySelective(TbNoticeInfo record) throws RuntimeException {
        try {
            return this.tbNoticeInfoMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKey(TbNoticeInfo record) throws RuntimeException {
        try {
            return this.tbNoticeInfoMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int insertSelective(TbNoticeInfo record) throws RuntimeException {
        try {
            return this.tbNoticeInfoMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public int insert(TbNoticeInfo record) throws RuntimeException {
        try {
            return this.tbNoticeInfoMapper.insert(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

	@Override
	public List<TbNoticeInfo> showNewestNoticeInfo(Integer from, Integer length) {
		Criteria criteria = new Criteria();
		criteria.setMysqlOffset(from);
		criteria.setMysqlLength(length);
		criteria.setOrderByClause("create_dt desc");
			
		return this.tbNoticeInfoMapper.selectByObject(criteria);
	}



	@Override
	public List<TbNoticeInfo> selectByObjectPage(Criteria example, PageDto pgo) throws RuntimeException {
		
		try{
			return tbNoticeInfoMapper.selectByObjectPage(example, pgo);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
}