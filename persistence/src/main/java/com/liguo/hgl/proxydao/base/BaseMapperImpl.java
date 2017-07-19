package com.liguo.hgl.proxydao.base;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-2-20
 * Time: 上午9:40
 * To change this template use File | Settings | File Templates.
 */
public class BaseMapperImpl<T> extends DAO<T> implements BaseMapper {

    @Autowired
    protected SqlSessionTemplate sqlSessionTemplate;

}
