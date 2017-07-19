package com.liguo.hgl.service.impl;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.common.HglContants.CheckType;
import com.liguo.hgl.proxydao.dao.TbAdminUserMapper;
import com.liguo.hgl.proxydao.dto.TbAdminUserDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbAdminUser;
import com.liguo.hgl.proxydao.model.TbWebUser;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.TbAdminUserService;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;


@Service
@Scope(value="prototype")
public class TbAdminUserServiceImpl implements TbAdminUserService {
    @Autowired
    private TbAdminUserMapper tbAdminUserMapper;

    private static final Logger logger = LoggerFactory.getLogger(TbAdminUserServiceImpl.class);

    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbAdminUserMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<TbAdminUser> selectByTbWarehouseId(Integer tbWIntegerId) throws RuntimeException{
    	  try {
              return this.tbAdminUserMapper.selectByTbWarehouseId(tbWIntegerId);
          } catch (Exception e) {
              throw new RuntimeException(e);
          }
      }
    public TbAdminUser selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbAdminUserMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TbAdminUser> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbAdminUserMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int deleteByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbAdminUserMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKeySelective(TbAdminUser record) throws RuntimeException {
        try {
            return this.tbAdminUserMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKey(TbAdminUser record) throws RuntimeException {
        try {
            return this.tbAdminUserMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int insertSelective(TbAdminUser record) throws RuntimeException {
        try {
            return this.tbAdminUserMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }  
    }
    
    @Override
	public TbAdminUserDto selectByUsernameAndPwd(String userName, String pwd)
			throws RuntimeException {
		try {
			Criteria example = new Criteria();
			example.put("userName", userName);
			example.put("password", pwd);			
			TbAdminUserDto tbAdminUserDto = this.tbAdminUserMapper
					.selectByUsernameAndPwd(example);
			return tbAdminUserDto;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<TbAdminUserDto> selectByObjectPage(Criteria example, PageDto pgo)
			throws RuntimeException {
		try{
			return this.tbAdminUserMapper.selectByObject(example, pgo);
		}catch(Exception e){
			throw new RuntimeException();
		}
	}

	@Override
	public List<TbAdminUser> selectDuplicateRecord(String params,Integer checkType) {
		Criteria criteria = new Criteria();
		if(checkType==HglContants.CheckType.CheckUserName.ordinal()){
			criteria.put("userName", params);
		}else if(checkType==HglContants.CheckType.CheckMobile.ordinal()){
			criteria.put("mobile", params);
		}else if(checkType==HglContants.CheckType.CheckEmail.ordinal()){
			criteria.put("email", params);
		}else if(checkType==HglContants.CheckType.CheckIdCard.ordinal()){
			criteria.put("idCard", params);
		}		
		List<TbAdminUser> list = this.tbAdminUserMapper.selectByObject(criteria);
		
		return list;
	}

}