/*
 * Copyright (c)2014 快诺迅汇(长沙)网络技术股份有限公司。
 * 版权所有
 */
package com.liguo.hgl.proxydao.dto;

import com.liguo.hgl.proxydao.model.TbProgramInfo;

/**
 * 节目信息DTO 比原生态TbProgramInfo新增了累计点赞数<br>
 * @filename TbProgramInfoDto.java<br>
 * @author 张勇<br>
 * @date 2016-1-8<br>
 * @Language java<br>
 */
public class TbProgramInfoDto extends TbProgramInfo implements Comparable<TbProgramInfoDto>{

	/** 投票状态 */
	private Integer praiseStatu;
	
	public int compareTo(TbProgramInfoDto o) {
		return  o.getPraise() - super.getPraise();
	}

	public Integer getPraiseStatu() {
		return praiseStatu;
	}

	public void setPraiseStatu(Integer praiseStatu) {
		this.praiseStatu = praiseStatu;
	}
	
}
