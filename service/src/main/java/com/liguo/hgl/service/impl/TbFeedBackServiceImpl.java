package com.liguo.hgl.service.impl;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.dao.TbFeedBackMapper;
import com.liguo.hgl.proxydao.dto.FeedBackDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbFeedBack;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.TbFeedBackService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(value="prototype")
public class TbFeedBackServiceImpl implements TbFeedBackService {
    @Autowired
    private TbFeedBackMapper tbFeedBackMapper;

    private static final Logger logger = LoggerFactory.getLogger(TbFeedBackServiceImpl.class);

    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbFeedBackMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TbFeedBack selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbFeedBackMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TbFeedBack> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbFeedBackMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int deleteByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbFeedBackMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKeySelective(TbFeedBack record) throws RuntimeException {
        try {
            return this.tbFeedBackMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKey(TbFeedBack record) throws RuntimeException {
        try {
            return this.tbFeedBackMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int insertSelective(TbFeedBack record) throws RuntimeException {
        try {
            return this.tbFeedBackMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

	@Override
	public int insertFeedBack(String feedBack, Integer userId) {
		if(userId == null){
			return HglContants.RE_LOGIN;
		}
		TbFeedBack fb = new TbFeedBack();
		fb.setUserId(userId);
		fb.setFeedBack(feedBack);
		fb.setCreateDt(System.currentTimeMillis());
		fb.setVersion(HglContants.VERSION);
		if(tbFeedBackMapper.insert(fb)==HglContants.INSERT_SUCCESS){
			return HglContants.INSERT_SUCCESS;
		}else{
			return HglContants.INSERT_FAIL;
		}
	}

	@Override
	public List<FeedBackDto> selectByObjectPage(Criteria criteria, PageDto pgo) {
		
		return tbFeedBackMapper.selectByObjectPage(criteria, pgo);
	}
}