package com.liguo.hgl.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.dao.TbIntegralDetailedMapper;
import com.liguo.hgl.proxydao.dto.IntegralDetailedDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbIntegralDetailed;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.TbIntegralDetailedService;
import com.liguo.hgl.service.TbIntegralService;
import com.liguo.hgl.service.TbSystemConfigService;

@Service
@Scope(value="prototype")
public class TbIntegralDetailedServiceImpl implements TbIntegralDetailedService {
    @Autowired
    private TbIntegralDetailedMapper tbIntegralDetailedMapper;
    
    @Autowired
    private TbSystemConfigService tbSystemConfigService;

    
    @Autowired
    private TbIntegralService tbIntegralService;
    private static final Logger logger = LoggerFactory.getLogger(TbIntegralDetailedServiceImpl.class);

    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbIntegralDetailedMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TbIntegralDetailed selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbIntegralDetailedMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TbIntegralDetailed> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbIntegralDetailedMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public int deleteByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbIntegralDetailedMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKeySelective(TbIntegralDetailed record) throws RuntimeException {
        try {
            return this.tbIntegralDetailedMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKey(TbIntegralDetailed record) throws RuntimeException {
        try {
            return this.tbIntegralDetailedMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int insertSelective(TbIntegralDetailed record) throws RuntimeException {
        try {
            return this.tbIntegralDetailedMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
   @Override
   public List<IntegralDetailedDto> selectIntegralDetailedPage(Criteria example,PageDto page,Integer pageIndex){
	   example.put("pageFrom", page.getPageSize()*pageIndex);
	   example.put("pageSize", page.getPageSize());
	   return this.tbIntegralDetailedMapper.selectIntegralDetailedPage(example,page,pageIndex);
   }
   /**
    * 根据userId 获得当天的签到记录
    * @param userId
    * @return
    */
	@Override
	public IntegralDetailedDto selectTodaySignByUserId(Integer userId) {
		try {
			Criteria example =new Criteria();
			example.put("userId", userId);
			example.put("opearDt", System.currentTimeMillis());
            return this.tbIntegralDetailedMapper.selectTodaySignByUserId(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}

	@Override
	public boolean signByUserId(Integer userId) {
		try {
			Integer signInregral = tbSystemConfigService.getSignInregral();
			IntegralDetailedDto todaySign = selectTodaySignByUserId(userId);
			if(todaySign!=null){
				return false;
			}
			tbIntegralService.addIntegral(userId, signInregral, null, null, HglContants.SIGN_INTEGRAL);			
			return true;
		 } catch (Exception e) {
            throw new RuntimeException(e);
		 }
	}
}