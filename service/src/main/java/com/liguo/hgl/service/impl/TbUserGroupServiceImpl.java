package com.liguo.hgl.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liguo.hgl.proxydao.dao.TbUserGroupMapper;
import com.liguo.hgl.proxydao.dao.TbWebUserMapper;
import com.liguo.hgl.proxydao.dto.GroupUserDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbUserGroup;
import com.liguo.hgl.proxydao.model.TbWebUser;
import com.liguo.hgl.service.TbUserGroupService;

@Service
@Scope(value="prototype")
public class TbUserGroupServiceImpl implements TbUserGroupService {
    @Autowired
    private TbUserGroupMapper tbUserGroupMapper;
    
    @Autowired
    private TbWebUserMapper tbWebUserMapper;

    private static final Logger logger = LoggerFactory.getLogger(TbUserGroupServiceImpl.class);

    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbUserGroupMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TbUserGroup selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbUserGroupMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TbUserGroup> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbUserGroupMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int deleteByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbUserGroupMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKeySelective(TbUserGroup record) throws RuntimeException {
        try {
            return this.tbUserGroupMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKey(TbUserGroup record) throws RuntimeException {
        try {
            return this.tbUserGroupMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int insertSelective(TbUserGroup record) throws RuntimeException {
        try {
            return this.tbUserGroupMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

	@Override
	public Map<String, Object> selectUserListByGroup(Integer gid, Integer shopId) {
		Map<String, Object> map = new HashMap<String, Object>();
		Criteria criteria = new Criteria();
		criteria.put("groupId", gid);
		criteria.put("shopId", shopId);
		List<TbWebUser> tbWebUsers = tbWebUserMapper.selectUserByGroup(criteria);
		map.put("userList", tbWebUsers);
		return map;
	}

	@Override
	public List<GroupUserDto> selectgroupUser(Integer shopId) {
		List<GroupUserDto> userDtos = new ArrayList<GroupUserDto>();
		Criteria example = new Criteria();
		example.put("shopId", shopId);
		List<TbUserGroup> groups = tbUserGroupMapper.selectByObject(example);
		for (TbUserGroup tbUserGroup : groups) {
			GroupUserDto groupUserDto = new GroupUserDto();
			groupUserDto.setGid(tbUserGroup.getId());
			groupUserDto.setGroupName(tbUserGroup.getName());
			Criteria criteria = new Criteria();
			criteria.put("groupId", tbUserGroup.getId());
			criteria.put("shopId", shopId);
			List<TbWebUser> tbWebUsers = tbWebUserMapper.selectUserByGroup(criteria);
			groupUserDto.setUserByGroupId(tbWebUsers);
			userDtos.add(groupUserDto);
			 
		}
		return userDtos;
	}

	@Override
	public List<TbUserGroup> selectByGroup(Criteria example) {
		try {
            return this.tbUserGroupMapper.selectByGroup(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}

	@Override
	public List<TbUserGroup> selectByShopGroup(Criteria example) {
		try {
            return this.tbUserGroupMapper.selectByShopGroup(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}
}