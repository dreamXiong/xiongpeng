package com.liguo.hgl.proxydao.util;

import com.liguo.hgl.proxydao.base.BaseMapper;

public interface IAddress extends BaseMapper{

	String getAddressName(int pid,int cid,int nid,int sid);
}
