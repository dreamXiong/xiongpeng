package com.liguo.hgl.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.dao.TbMerchantsMapper;
import com.liguo.hgl.proxydao.dao.TbStatisticalMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbMerchants;
import com.liguo.hgl.proxydao.model.TbStatistical;
import com.liguo.hgl.proxydao.util.ToolsUtil;
import com.liguo.hgl.service.TbStatisticalService;
import com.liguo.hgl.util.IpConvertUtil;

@Service
@Scope(value="prototype")
public class TbStatisticalServiceImpl implements TbStatisticalService {
    @Autowired
    private TbStatisticalMapper tbStatisticalMapper;
    
    @Autowired
    private TbMerchantsMapper tbMerchantsMapper;

    private static final Logger logger = LoggerFactory.getLogger(TbStatisticalServiceImpl.class);

    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbStatisticalMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TbStatistical selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbStatisticalMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TbStatistical> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbStatisticalMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

   

    public int updateByPrimaryKeySelective(TbStatistical record) throws RuntimeException {
        try {
            return this.tbStatisticalMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKey(TbStatistical record) throws RuntimeException {
        try {
            return this.tbStatisticalMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int insertSelective(TbStatistical record) throws RuntimeException {
        try {
            return this.tbStatisticalMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

	@Override
	public void addStatisticalView(Integer id,String browser, String ip) {
		this.editSatistical(id,browser,ip,HglContants.STATISTICAL_VIEW);
		TbMerchants merchants = tbMerchantsMapper.selectByPrimaryKey(id);
		merchants.setViews(merchants.getViews()+1); 
		tbMerchantsMapper.updateByPrimaryKeySelective(merchants);
		
	}

	@Override
	public void addStatisticalParticipate(Integer id, String browser, String ip) {
		this.editSatistical(id, browser, ip, HglContants.STATISTICAL_PARTICIPATE);
		TbMerchants merchants = tbMerchantsMapper.selectByPrimaryKey(id);
		merchants.setParticipate(merchants.getParticipate()+1); 
		tbMerchantsMapper.updateByPrimaryKeySelective(merchants);
		
	}

	@Override
	public void addStatisticalWinning(Integer id, String browser, String ip) {
		this.editSatistical(id, browser, ip, HglContants.STATISTICAL_WINNING);
		TbMerchants merchants = tbMerchantsMapper.selectByPrimaryKey(id);
		merchants.setWinning(merchants.getWinning()+1); 
		tbMerchantsMapper.updateByPrimaryKeySelective(merchants);
		
	}
	
	public void editSatistical(Integer id,String browser, String ip,int type){
		TbStatistical statistical = new TbStatistical();
		statistical.setIp(ip);
		System.out.println("--------------"+IpConvertUtil.getBrowserByAgent(browser));
		statistical.setBrowser(IpConvertUtil.getBrowserByAgent(browser));
		statistical.setMerchansid(id);
		statistical.setType(type);
		statistical.setCreateTime(ToolsUtil.getDateYMDHMS_14bit());
		tbStatisticalMapper.insert(statistical);
	}
}