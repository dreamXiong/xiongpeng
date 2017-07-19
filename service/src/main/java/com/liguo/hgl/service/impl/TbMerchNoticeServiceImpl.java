package com.liguo.hgl.service.impl;

import com.liguo.hgl.proxydao.dao.TbMerchNoticeMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbMerchNotice;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.TbMerchNoticeService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(value="prototype")
public class TbMerchNoticeServiceImpl implements TbMerchNoticeService {
    @Autowired
    private TbMerchNoticeMapper tbMerchNoticeMapper;

    private static final Logger logger = LoggerFactory.getLogger(TbMerchNoticeServiceImpl.class);

    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbMerchNoticeMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TbMerchNotice selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbMerchNoticeMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TbMerchNotice> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbMerchNoticeMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

  
    public int deleteByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbMerchNoticeMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKeySelective(TbMerchNotice record) throws RuntimeException {
        try {
            return this.tbMerchNoticeMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKey(TbMerchNotice record) throws RuntimeException {
        try {
            return this.tbMerchNoticeMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int insertSelective(TbMerchNotice record) throws RuntimeException {
        try {
            return this.tbMerchNoticeMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

	@Override
	public List<TbMerchNotice> selectByObjectPage(Criteria example, PageDto page) {
		
		return this.tbMerchNoticeMapper.selectByObjectPage(example, page);
	}
}