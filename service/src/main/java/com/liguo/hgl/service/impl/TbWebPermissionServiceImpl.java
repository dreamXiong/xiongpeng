package com.liguo.hgl.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.dao.TbWebPermissionMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbAdminRole;
import com.liguo.hgl.proxydao.model.TbAdminUser;
import com.liguo.hgl.proxydao.model.TbWebPermission;
import com.liguo.hgl.proxydao.model.TbWebRole;
import com.liguo.hgl.proxydao.model.TbWebUser;
import com.liguo.hgl.service.TbWebPermissionService;
import com.liguo.hgl.service.TbWebRoleService;

@Service
@Scope(value="prototype")
public class TbWebPermissionServiceImpl implements TbWebPermissionService {
    @Autowired
    private TbWebPermissionMapper tbWebPermissionMapper;
    
    @Autowired
    private TbWebRoleService webRoleService;
    
    private static final Logger logger = LoggerFactory.getLogger(TbWebPermissionServiceImpl.class);

    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbWebPermissionMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TbWebPermission selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbWebPermissionMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TbWebPermission> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbWebPermissionMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int deleteByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbWebPermissionMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKeySelective(TbWebPermission record) throws RuntimeException {
        try {
            return this.tbWebPermissionMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKey(TbWebPermission record) throws RuntimeException {
        try {
            return this.tbWebPermissionMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int insertSelective(TbWebPermission record) throws RuntimeException {
        try {
            return this.tbWebPermissionMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Map<String, Object>> selectByObjectToMap(Criteria example) throws RuntimeException {
        return this.tbWebPermissionMapper.selectByObjectToMap(example);
    }

    @Override
    public List<TbWebPermission> selectByStringList(List<String> strList) throws RuntimeException {
        return tbWebPermissionMapper.selectByStringList(strList);
    }

	@Override
	public TbWebPermission selectByUrl(Criteria example)
			throws RuntimeException {
		try{
			return tbWebPermissionMapper.selectByUrl(example);
		}catch(Exception e){			
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public boolean getUserLicense(TbWebUser webUser, String url) {		
		if(HglContants.ADMIN_USER_NAME.equals(webUser.getUserName())){
			return true;
		}
   		TbWebRole webRole =webRoleService.selectByPrimaryKey(webUser.getRoleId());
   		if(webRole!=null){
   			List<String> permissionIds = Arrays.asList(webRole.getPermissionIds());
   		
   			Criteria criteria = new Criteria();
   			criteria.put("permissionIds", permissionIds);
   			criteria.put("actionUrl",url);		    	
   			TbWebPermission webPermission= this.selectByUrl(criteria);
   			if(webPermission!=null){  			    	
   			    return false;	       		    	    	
   			}
   		}
   		return true;
	}
}