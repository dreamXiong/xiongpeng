package com.liguo.hgl.service.impl;

import com.liguo.hgl.proxydao.dao.TbSystemConfigMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbSystemConfig;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.TbSystemConfigService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
@Scope(value="prototype")
public class TbSystemConfigServiceImpl implements TbSystemConfigService {
	
    @Autowired
    private TbSystemConfigMapper tbSystemConfigMapper;

    private static final Logger logger = LoggerFactory.getLogger(TbSystemConfigServiceImpl.class);

    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbSystemConfigMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TbSystemConfig selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbSystemConfigMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TbSystemConfig> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbSystemConfigMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TbSystemConfig> selectByObject(Criteria example,PageDto pgo) throws RuntimeException {
        try {
            return this.tbSystemConfigMapper.selectByObject(example,pgo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public int deleteByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbSystemConfigMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKeySelective(TbSystemConfig record) throws RuntimeException {
        try {
            return this.tbSystemConfigMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKey(TbSystemConfig record) throws RuntimeException {
        try {
            return this.tbSystemConfigMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
	public int insert(TbSystemConfig record) throws RuntimeException {
		try {
            return this.tbSystemConfigMapper.insert(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}

    public int insertSelective(TbSystemConfig record) throws RuntimeException {
        try {
            return this.tbSystemConfigMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

	@Override
	public boolean validateSystemConfigIsExist(String configKey) throws RuntimeException {
		try {
			TbSystemConfig tbSystemConfig = this.tbSystemConfigMapper.selectByConfigKey(configKey);
			if(tbSystemConfig != null){
				return true;
			}
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
		return false;
	}
	@Override
	public Integer getSignInregral(){
		logger.info(">>>>>>>>>>>>>>> getSignInregral start...");
		Integer signIntegral = 0;
        String systemConfig = getSystemConfig("signIntegral");
        if(systemConfig!=null){
        	signIntegral = Integer.valueOf(systemConfig);
        }
        logger.info(">>>>>>>>>>>>>>> getSignInregral Value "+signIntegral);
        return signIntegral;
	}

	@Override
	public Double getOrderInregral(){
		Double signIntegral=1d;
		logger.info(">>>>>>>>>>>>>>> getSignInregral start...");
		String systemConfig =getSystemConfig("orderInregral");
		 if(systemConfig!=null){
			 signIntegral= Double.valueOf(systemConfig);
		 }
        logger.info(">>>>>>>>>>>>>>> getSignInregral Value "+signIntegral);
        return signIntegral;
	}

	private String getSystemConfig(String configKey) {
		String configValue = null;
        Criteria criteria = new Criteria();
        criteria.put("configKey", configKey);
        List<TbSystemConfig>  tbSystemConfig =this.selectByObject(criteria);
        if(!CollectionUtils.isEmpty(tbSystemConfig)){
        	configValue = tbSystemConfig.get(0).getConfigValue();
        }
		return configValue;
	}
	
	@Override
	public TbSystemConfig selectByConfigKey(String configKey) {
		return tbSystemConfigMapper.selectByConfigKey(configKey);
	}
}