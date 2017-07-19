/*
 * Copyright (c)2014 快诺迅汇(长沙)网络技术股份有限公司。
 * 版权所有
 */
package com.liguo.hgl.proxydao.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dto.TbProgramInfoDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbProgramInfo;

/**
 * <br>
 * 
 * @filename TbProgramInfoDtoMapper.java<br>
 * @author 张勇<br>
 * @date 2016-1-8<br>
 * @Language java<br>
 */
@Repository
public class TbProgramInfoDtoMapperImpl extends BaseMapperImpl<TbProgramInfo> {
	
	public List<TbProgramInfoDto> queryProgramSortList() {
		return this.sqlSessionTemplate
				.selectList("com.liguo.hgl.proxydao.dao.TbProgramInfoDtoMapper.queryProgramSortList");
	}
	
	public List<TbProgramInfoDto> queryProgramList(Criteria criteria) {
		return this.sqlSessionTemplate
				.selectList("com.liguo.hgl.proxydao.dao.TbProgramInfoDtoMapper.queryProgramList",criteria);
	}
	
	
	public List<TbProgramInfoDto> queryTopProgramList(Criteria criteria) {
		return this.sqlSessionTemplate
				.selectList("com.liguo.hgl.proxydao.dao.TbProgramInfoDtoMapper.queryTopProgramList",criteria);
	}
}
