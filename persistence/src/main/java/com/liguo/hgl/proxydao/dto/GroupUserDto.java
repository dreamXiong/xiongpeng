package com.liguo.hgl.proxydao.dto;

import java.io.Serializable;
import java.util.List;

import com.liguo.hgl.proxydao.model.TbUserGroup;
import com.liguo.hgl.proxydao.model.TbWebUser;
/**
 * 根据分组id查询出用户
 * @fiGroupUserDto.java
 * @2016-7-2	
 * @author 周双双
 */
public class GroupUserDto  extends TbUserGroup{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4818904473501693034L;
	
	private Integer gid;
	private String groupName;
	private List<TbWebUser> userByGroupId;
	public Integer getGid() {
		return gid;
	}
	public void setGid(Integer gid) {
		this.gid = gid;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public List<TbWebUser> getUserByGroupId() {
		return userByGroupId;
	}
	public void setUserByGroupId(List<TbWebUser> userByGroupId) {
		this.userByGroupId = userByGroupId;
	}
	
	

}
