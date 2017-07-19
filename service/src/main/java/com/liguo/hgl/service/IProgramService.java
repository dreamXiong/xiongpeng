/*
 * Copyright (c)2014 快诺迅汇(长沙)网络技术股份有限公司。
 * 版权所有
 */
package com.liguo.hgl.service;

import java.util.List;

import com.liguo.hgl.common.ResultDto;
import com.liguo.hgl.proxydao.dto.TbProgramInfoDto;
import com.liguo.hgl.proxydao.model.TbProgramInfo;
import com.liguo.hgl.proxydao.model.TbProgramPraise;

/**
 * 节目<br>
 * 
 * @filename IProgramService.java<br>
 * @author 张勇<br>
 * @date 2016-1-8<br>
 * @Language java<br>
 */
public interface IProgramService {
	/**
	 * 该用户是否关注了公众号
	 * @param openId
	 * @return
	 */
	public boolean isSubscribe(String openId);
	/**
	 * 节目列表查询
	 * 
	 * @return
	 */
	List<TbProgramInfoDto> queryProgramList(String openId,boolean isLimit);

	/**
	 * 节目点赞排序列表查询
	 * 
	 * @return
	 */
	List<TbProgramInfoDto> queryProgramSortList();

	/**
	 * 点赞保存
	 * 
	 * @return
	 */
	ResultDto savePraise(TbProgramPraise tbProgramPraise);
	
	/**
	 * 根据授权code 获取用户openId
	 * @param code
	 * @return
	 */
	String queryUserOpenId(String code);

	/**
	 * 节目列表查询
	 * 
	 * @return
	 */
	List<TbProgramInfoDto> queryTopProgramList(int topNumber);

	
	TbProgramInfo queryProgramById(Integer id);
	
	int updateProgramPraiseFlagById(TbProgramInfo info);
}
