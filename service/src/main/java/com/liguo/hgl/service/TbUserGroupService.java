package com.liguo.hgl.service;

import java.util.List;
import java.util.Map;

import com.liguo.hgl.proxydao.dto.GroupUserDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbUserGroup;

public interface TbUserGroupService {
    /**
     * 根据条件获取总行数
     */
    int countByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键查询记录
     */
    TbUserGroup selectByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据指定条件查询记录
     */
    List<TbUserGroup> selectByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbUserGroup record) throws RuntimeException;

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbUserGroup record) throws RuntimeException;

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbUserGroup record) throws RuntimeException;
    /**
     * 根据分组Id查询出对于的用户
     * @param gid
     * @return
     * @author zss
     * @param shopId 
     */
	Map<String, Object> selectUserListByGroup(Integer gid, Integer shopId);

	List<GroupUserDto> selectgroupUser(Integer shopId);
	
    List<TbUserGroup> selectByGroup(Criteria example);
    
    List<TbUserGroup> selectByShopGroup(Criteria example);
}