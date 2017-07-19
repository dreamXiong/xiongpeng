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
import com.liguo.hgl.proxydao.dao.TbAdminPermissionMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbAdminPermission;
import com.liguo.hgl.proxydao.model.TbAdminRole;
import com.liguo.hgl.proxydao.model.TbAdminUser;
import com.liguo.hgl.service.TbAdminPermissionService;
import com.liguo.hgl.service.TbAdminRoleService;

@Service
@Scope(value="prototype")
public class TbAdminPermissionServiceImpl implements TbAdminPermissionService {
    @Autowired
    private TbAdminPermissionMapper tbAdminPermissionMapper;
    
    @Autowired
    private TbAdminRoleService adminRoleService;

    private static final Logger logger = LoggerFactory.getLogger(TbAdminPermissionServiceImpl.class);

    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbAdminPermissionMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TbAdminPermission selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbAdminPermissionMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TbAdminPermission> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbAdminPermissionMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int deleteByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbAdminPermissionMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKeySelective(TbAdminPermission record) throws RuntimeException {
        try {
            return this.tbAdminPermissionMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKey(TbAdminPermission record) throws RuntimeException {
        try {
            return this.tbAdminPermissionMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int insertSelective(TbAdminPermission record) throws RuntimeException {
        try {
            return this.tbAdminPermissionMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<TbAdminPermission> selectByStringList(List<String> strList) throws RuntimeException {
        return tbAdminPermissionMapper.selectByStringList(strList);
    }

    @Override
    public List<Map<String, Object>> selectByObjectToMap(Criteria example) throws RuntimeException {
        return tbAdminPermissionMapper.selectByObjectToMap(example);
    }

	@Override
	public TbAdminPermission selectByURL(Criteria criteria)
			throws RuntimeException {
		try{
			return tbAdminPermissionMapper.selectByURL(criteria);
		}catch(Exception e){			
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean getAdminUserLicense(TbAdminUser adminUser, String url) {
		
		if(HglContants.ADMIN_USER_NAME.equals(adminUser.getUserName())){
			return true;
		}		
		TbAdminRole adminRole = adminRoleService.selectByPrimaryKey(adminUser.getRoleId());
		if(adminRole!=null){
			List<String> permissionIds = Arrays.asList(adminRole.getPermissionIds());
			Criteria criteria = new Criteria();
			criteria.put("permissionIds",permissionIds);
			criteria.put("actionUrl", url);
			TbAdminPermission adminPermission = this.selectByURL(criteria);
			if(adminPermission!=null){
				return false;
			}
		}
		return true;
	}
}