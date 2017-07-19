package com.liguo.hgl.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.liguo.hgl.proxydao.dao.TbCityLowestPriceMapper;
import com.liguo.hgl.proxydao.dao.TbOpenedRegionalMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbCityLowestPrice;
import com.liguo.hgl.proxydao.model.TbOpenedRegional;
import com.liguo.hgl.proxydao.util.StringUtil;
import com.liguo.hgl.service.TbCityLowestPriceService;

@Service
@Scope(value="prototype")
public class TbCityLowestPriceServiceImpl implements TbCityLowestPriceService {
    @Autowired
    private TbCityLowestPriceMapper tbCityLowestPriceMapper;
    
    @Autowired
    private TbOpenedRegionalMapper tbOpenedRegionalMapper;

    private static final Logger logger = LoggerFactory.getLogger(TbCityLowestPriceServiceImpl.class);

    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbCityLowestPriceMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TbCityLowestPrice selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbCityLowestPriceMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TbCityLowestPrice> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbCityLowestPriceMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public int deleteByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbCityLowestPriceMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKeySelective(TbCityLowestPrice record) throws RuntimeException {
        try {
            return this.tbCityLowestPriceMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKey(TbCityLowestPrice record) throws RuntimeException {
        try {
            return this.tbCityLowestPriceMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int insertSelective(TbCityLowestPrice record) throws RuntimeException {
        try {
            return this.tbCityLowestPriceMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Map<String, Object>> findLowestPriceBatchByCityId(List<String> list) throws RuntimeException {
        
        // TODO Auto-generated method stub
        return this.tbCityLowestPriceMapper.findLowestPriceBatchByCityId(list);
    }

    @Override
    public List<String> InsertOrDelLowestPrice(TbOpenedRegional tbOpenedRegional) throws RuntimeException {
        
        //需改变内容
        String openCity =tbOpenedRegional.getOpenCity();
        List<String> cityList = new ArrayList<String>();
        if(StringUtil.isNotBlank(openCity)){
            String[]  cityArr =openCity.split(",");
            cityList.addAll(Arrays.asList(cityArr));
          
        }
        List<String> backList = new ArrayList<String>(cityList);
        //删除
        Criteria c = new Criteria();
        
        Integer proviceId =tbOpenedRegional.getProviceId();
        c.put("proviceId", proviceId);
        List<TbOpenedRegional>  tbOpenedRegionalList =tbOpenedRegionalMapper.findOpenedRegByproviceId(c);
        if(!CollectionUtils.isEmpty(tbOpenedRegionalList)){
            String [] openCityArr =tbOpenedRegionalList.get(0).getOpenCity().split(",");
            List<String>  openCityList =new ArrayList<String>();
            openCityList.addAll(Arrays.asList(openCityArr));
            //区别与原值不同的内容删除
            openCityList.removeAll(cityList);
            //如果原值集合中还存在值，说明该值需要删除
            if(!CollectionUtils.isEmpty(openCityList)){
                tbCityLowestPriceMapper.deleteLowestPrice_Batch(openCityList);
            }
            
        }
        
        c = new Criteria();
        List<String> cityAllList =tbCityLowestPriceMapper.selectCityIds();
        cityList.removeAll(cityAllList);
        
        //如果cityList还有值就 批量新增
        if(!CollectionUtils.isEmpty(cityList)){
            tbCityLowestPriceMapper.insertLowestPriceBatch(cityList);
        }
        return backList;
    }
}