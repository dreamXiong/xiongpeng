package com.liguo.hgl.service.impl;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liguo.hgl.proxydao.dao.TbServiceTypeMapper;
import com.liguo.hgl.proxydao.dao.TbUserInfoMapper;
import com.liguo.hgl.proxydao.dto.ServiceTypeDto;
import com.liguo.hgl.proxydao.dto.WapServiceTypeDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbServiceType;
import com.liguo.hgl.proxydao.model.TbUserInfo;
import com.liguo.hgl.service.TbServiceTypeService;

@Service
@Scope(value="prototype")
public class TbServiceTypeServiceImpl implements TbServiceTypeService {
    @Autowired
    private TbServiceTypeMapper tbServiceTypeMapper;
    
    @Autowired
    private TbUserInfoMapper userInfoMapper;

    private static final Logger logger = LoggerFactory.getLogger(TbServiceTypeServiceImpl.class);

    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbServiceTypeMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TbServiceType selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbServiceTypeMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TbServiceType> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbServiceTypeMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int deleteByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbServiceTypeMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKeySelective(TbServiceType record) throws RuntimeException {
        try {
            return this.tbServiceTypeMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKey(TbServiceType record) throws RuntimeException {
        try {
            return this.tbServiceTypeMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int insertSelective(TbServiceType record) throws RuntimeException {
        try {
            return this.tbServiceTypeMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
	@Override
	public List<ServiceTypeDto> selectServiceTypeByIds(Criteria example)
			throws RuntimeException {
		try{
			return this.tbServiceTypeMapper.selectServiceTypeByIds(example);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public ServiceTypeDto selectDtoByPrimaryKey(Integer id)
			throws RuntimeException {
		try{
			return this.tbServiceTypeMapper.selectDtoByPrimaryKey(id);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<ServiceTypeDto> selectDtoByObject(Criteria example)
			throws RuntimeException {
		try{
			return this.tbServiceTypeMapper.selectDtoByObject(example);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		
	}
	
    public List<TbServiceType> selectByWap(){
    	 return this.tbServiceTypeMapper.selectByWap();
    }
    
    public List<WapServiceTypeDto> selectServiceType(){
    	 return this.tbServiceTypeMapper.selectServiceType();
    }
    
    /*根据条件查找WAP端师傅技能*/
    public List<ServiceTypeDto> getSkills(Integer userInfoId){
    	TbUserInfo userInfo = userInfoMapper.selectByPrimaryKey(userInfoId);
		Criteria criteria = new Criteria();
		criteria.put("parentId",0);
		criteria.setOrderByClause("id");
		List<ServiceTypeDto> list = tbServiceTypeMapper.selectDtoByObject(criteria);			
		for(int i=0;i<list.size();i++){
			Integer parentId = list.get(i).getId();
			ServiceTypeDto serviceTypeDto = list.get(i);	
			criteria = new Criteria();
			criteria.put("parentId",parentId);
			if(userInfo!=null && userInfo.getServiceType()!=null){
				String[] strList = userInfo.getServiceType().split(",");
				List<String> strListId = Arrays.asList(strList);
				criteria.put("item",strListId);	
			}
					
			List<ServiceTypeDto> listChild = tbServiceTypeMapper.selectServiceTypeByIds(criteria);
			serviceTypeDto.setChildList(listChild);			
			list.remove(i);
			list.add(i,serviceTypeDto);
		}
		return list;		
    }
}