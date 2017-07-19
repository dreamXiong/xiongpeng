package com.liguo.hgl.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.dao.TbCompanyServiceMapper;
import com.liguo.hgl.proxydao.dao.TbProvinceInfoMapper;
import com.liguo.hgl.proxydao.dto.TbCompanyServiceDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbCompanyService;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.proxydao.util.GaoDeMapUtil;
import com.liguo.hgl.service.TbCompanyServiceService;
import com.liguo.hgl.util.ImageUtil;
import com.liguo.hgl.util.StringUtil;

@Service
@Scope(value="prototype")
public class TbCompanyServiceServiceImpl implements TbCompanyServiceService {
	
    @Autowired
    private TbCompanyServiceMapper tbCompanyServiceMapper;
    
    @Autowired
	private TbProvinceInfoMapper tbProvinceInfoMapper;

    private static final Logger logger = LoggerFactory.getLogger(TbCompanyServiceServiceImpl.class);

    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbCompanyServiceMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TbCompanyService selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbCompanyServiceMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TbCompanyService> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbCompanyServiceMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
   
    public int deleteByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbCompanyServiceMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKeySelective(TbCompanyService record) throws RuntimeException {
        try {
            return this.tbCompanyServiceMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKey(TbCompanyService record) throws RuntimeException {
        try {
            return this.tbCompanyServiceMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int insertSelective(TbCompanyService record) throws RuntimeException {
        try {
            return this.tbCompanyServiceMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<TbCompanyServiceDto> selectTbCompanyServicePage(Criteria criteria, PageDto page){
    	  return this.tbCompanyServiceMapper.selectTbCompanyServicePage(criteria,page);
    }

    public List<TbCompanyServiceDto> selectTbCompanyService(Criteria criteria){
    	  return this.tbCompanyServiceMapper.selectTbCompanyService(criteria);
    }
    
    public int saveAddInfo(TbCompanyService record) throws Exception{
    	
    	Criteria parameter = new Criteria();
		parameter.put("provinceCode", record.getRegProvince());
		parameter.put("cityCode", record.getRegCity());
		parameter.put("countryCode", record.getRegCountry());
		parameter.put("streetCode", record.getRegStreet());
		
		String addressInfo = tbProvinceInfoMapper.selectAddressByCode(parameter);
		String baiduAddress = addressInfo+record.getRegAddress();
		
		Map<String, String> map = GaoDeMapUtil.getLatitude(baiduAddress);
		record.setLat(Double.parseDouble(map.get("lat")));
		record.setLon(Double.parseDouble(map.get("lon")));
    	this.tbCompanyServiceMapper.insertSelective(record);
    	Integer id = record.getId();
    	if(!StringUtils.isBlank(record.getCompanyImage1())){
    		String newName = StringUtil.changeFileName(record.getCompanyImage1().split("_")[0],record.getCompanyImage1());
    			ImageUtil.commonUploadNewImage(HglContants.CUSTOMER_TESTPATH + record.getCompanyImage1(), newName, id, HglContants.CUSTOMER_TESTPATH, HglContants.COMPANY_SERVICE);
    			record.setCompanyImage1(newName);
    	}
    	if(!StringUtils.isBlank(record.getCompanyImage2())){
    		String newName = StringUtil.changeFileName(record.getCompanyImage2().split("_")[0],record.getCompanyImage1());
    			ImageUtil.commonUploadNewImage(HglContants.CUSTOMER_TESTPATH + record.getCompanyImage2(), newName, id, HglContants.CUSTOMER_TESTPATH, HglContants.COMPANY_SERVICE);
    		record.setCompanyImage2(newName);
    	}
    	if(!StringUtils.isBlank(record.getCompanyImage3())){
    		String newName = StringUtil.changeFileName(record.getCompanyImage3().split("_")[0],record.getCompanyImage1());
    			ImageUtil.commonUploadNewImage(HglContants.CUSTOMER_TESTPATH + record.getCompanyImage3(), newName, id, HglContants.CUSTOMER_TESTPATH, HglContants.COMPANY_SERVICE);
    		record.setCompanyImage3(newName);
    	}
    	record.setVersion(0);
    	updateByPrimaryKey(record);
    	return 0;
    }
    
    public int saveUpdateInfo(TbCompanyService record) throws Exception{
    	
    	Criteria parameter = new Criteria();
		parameter.put("provinceCode", record.getRegProvince());
		parameter.put("cityCode", record.getRegCity());
		parameter.put("countryCode", record.getRegCountry());
		parameter.put("streetCode", record.getRegStreet());
		
		String addressInfo = tbProvinceInfoMapper.selectAddressByCode(parameter);
		String baiduAddress = addressInfo+record.getRegAddress();
		
		Map<String, String> map = GaoDeMapUtil.getLatitude(baiduAddress);
		if(map != null){
			record.setLat(Double.parseDouble(map.get("lat")));
			record.setLon(Double.parseDouble(map.get("lon")));
		}
		
    	TbCompanyService tbCompanyService = this.tbCompanyServiceMapper.selectByPrimaryKey(record.getId());
    	if(!StringUtils.isBlank(record.getCompanyImage1()) && !tbCompanyService.getCompanyImage1().equals(record.getCompanyImage1())){
    		String newName = StringUtil.changeFileName(record.getCompanyImage1().split("_")[0],record.getCompanyImage1());
    		ImageUtil.commonUploadNewImage(HglContants.CUSTOMER_TESTPATH + record.getCompanyImage1(), newName,record.getId(), HglContants.CUSTOMER_TESTPATH, HglContants.COMPANY_SERVICE);
    		record.setCompanyImage1(newName);
    	}
    	if(!StringUtils.isBlank(record.getCompanyImage2()) && !tbCompanyService.getCompanyImage2().equals(record.getCompanyImage2())){
    		String newName = StringUtil.changeFileName(record.getCompanyImage2().split("_")[0],record.getCompanyImage2());
    		ImageUtil.commonUploadNewImage(HglContants.CUSTOMER_TESTPATH + record.getCompanyImage2(), newName, record.getId(), HglContants.CUSTOMER_TESTPATH, HglContants.COMPANY_SERVICE);
    		record.setCompanyImage2(newName);
    	}
    	if(!StringUtils.isBlank(record.getCompanyImage3()) && !tbCompanyService.getCompanyImage3().equals(record.getCompanyImage3())){
    		String newName = StringUtil.changeFileName(record.getCompanyImage3().split("_")[0],record.getCompanyImage3());
    		ImageUtil.commonUploadNewImage(HglContants.CUSTOMER_TESTPATH + record.getCompanyImage3(), newName, record.getId(), HglContants.CUSTOMER_TESTPATH, HglContants.COMPANY_SERVICE);
    		record.setCompanyImage3(newName);
    	}
         return this.tbCompanyServiceMapper.updateByPrimaryKey(record);
    }
}