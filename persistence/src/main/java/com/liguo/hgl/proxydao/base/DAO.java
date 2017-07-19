package com.liguo.hgl.proxydao.base;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Mybatis dao普通增删改查
 * 
 * @author Daniel
 * @version 1.0
 */
public abstract class DAO<T> {

    private final static Log logger = LogFactory.getLog(DAO.class);
    private Class<T> entityClass;

    public DAO() {

        //        entityClass = (Class<T>)((ParameterizedType)getClass()
        //                .getGenericSuperclass()).getActualTypeArguments()[0];
        //        logger.debug("entityClass: " + entityClass.getName());
    }

    public String getNamespace() {

        return entityClass.getSimpleName();
    }
}
