package com.liguo.hgl.service.impl;

/*
 * Copyright (c)2014 快诺迅汇(长沙)网络技术股份有限公司。
 * 版权所有
 */

import com.liguo.hgl.base.AbstractService;
import com.liguo.hgl.proxydao.dao.TbWeixinUserMapper;
import com.liguo.hgl.service.IDemoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DemoService extends AbstractService implements IDemoService {

    @Autowired
    protected TbWeixinUserMapper tbWeixinUserMapper;

    /**
    * 入金记录统计查询
    */
    @Override
    public int getUserCount() {
        return tbWeixinUserMapper.countByObject(null);
    }

}
