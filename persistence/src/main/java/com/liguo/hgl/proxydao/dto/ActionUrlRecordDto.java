package com.liguo.hgl.proxydao.dto;

import java.io.Serializable;
import java.util.List;

public class ActionUrlRecordDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String actionUrl;
	private List<KeyValueDto> list;

	public String getActionUrl() {
		return actionUrl;
	}

	public void setActionUrl(String actionUrl) {
		this.actionUrl = actionUrl;
	}

	public List<KeyValueDto> getList() {
		return list;
	}

	public void setList(List<KeyValueDto> list) {
		this.list = list;
	}

}
