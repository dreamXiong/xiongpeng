package com.liguo.hgl.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.dao.TbExperienceMapper;
import com.liguo.hgl.proxydao.dao.TbShopInfoMapper;
import com.liguo.hgl.proxydao.dao.TbShopLevelMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbExperience;
import com.liguo.hgl.proxydao.model.TbShopInfo;
import com.liguo.hgl.proxydao.model.TbShopLevel;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.TbExperienceService;

@Service
@Scope(value="prototype")
public class TbExperienceServiceImpl implements TbExperienceService {
    @Autowired
    private TbExperienceMapper tbExperienceMapper;
    
    @Autowired
    private TbShopLevelMapper levelMapper;
    
    @Autowired
    private TbShopInfoMapper tbShopInfoMapper;

    private static final Logger logger = LoggerFactory.getLogger(TbExperienceServiceImpl.class);

    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbExperienceMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TbExperience selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbExperienceMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TbExperience> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbExperienceMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int deleteByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbExperienceMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKeySelective(TbExperience record) throws RuntimeException {
        try {
            return this.tbExperienceMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKey(TbExperience record) throws RuntimeException {
        try {
            return this.tbExperienceMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int insertSelective(TbExperience record) throws RuntimeException {
        try {
            return this.tbExperienceMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

	@Override
	public List<TbExperience> selectExperienceList(Criteria criteria,
			PageDto page) {
		if(criteria.getOrderByClause()==null){
			criteria.setOrderByClause("id");
			criteria.setOrderByClauseDesc("desc");
		}
		
        return this.tbExperienceMapper.selectByCriteria(criteria,page);
       
	}

	@Override
	public int getMyExperience(Integer loginUserId) {
		TbExperience experience = tbExperienceMapper.getMyExperience(loginUserId);
		if(experience !=null){
			return experience.getExperienceSum();
		}
		return 0;
	}
	
	public TbShopLevel getMyLevel(int experience){
		TbShopLevel level = levelMapper.selectLevelByExperience(experience);
		return level;
	}

	@Override
	public int addExperience(int userId, double money,String orderNo, int type) {
		int count =0;
		TbShopInfo shopInfo = tbShopInfoMapper.selectByUserId(userId);
		if(shopInfo !=null){
			TbExperience experience = new TbExperience();
			experience.setCreateBy(userId);
			experience.setOrderNumber(orderNo);
			experience.setType(type);
			experience.setCreateDt(System.currentTimeMillis());
			//int myexperience = getMyExperience(userId);
			double exp_proportion = getMyLevel(shopInfo.getExp()).getExpProportion();
			double number = money*exp_proportion;
			int number1= new BigDecimal(number).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
			experience.setNumber(number1);
			int experienceSum = shopInfo.getExp()+number1;
			experience.setExperienceSum(experienceSum);
			logger.debug("---现有经验值："+shopInfo.getExp()+"---获得的经验值："+number1+"---规则："+exp_proportion);
			if(type == HglContants.BUY_EXPERIENCE){
				experience.setDetail("支付金额*等级经验"+money+"*"+exp_proportion);
			}else if(type == HglContants.SERVICE_EXPERIENCE){
				experience.setDetail("购买服务"+money+"*"+exp_proportion);
			}else if(type == HglContants.ACTIVITY_EXPERIENCE){
				experience.setDetail("平台活动直接赠送经验");
			}
			count = tbExperienceMapper.insert(experience);
			
			///修改shop_info
			
			shopInfo.setExp(experienceSum);
			shopInfo.setExpLevel(getMyLevel(shopInfo.getExp()).getId());
			count = tbShopInfoMapper.updateByPrimaryKeySelective(shopInfo);
		}
		return count;
	}
}