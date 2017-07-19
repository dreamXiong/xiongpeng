/*
 * Copyright (c)2014 快诺迅汇(长沙)网络技术股份有限公司。
 * 版权所有
 */
package com.liguo.hgl.proxydao.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.ibatis.datasource.DataSourceFactory;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

/**
 * 实现一个C3P0连接池供Mybatis调用<br>
 *
 * @author 王丹
 * @FileName C3P0DataSourceFactory.java<br>
 * @Language Java<br>
 * @date 2014-12-10<br>
 */
public class C3P0DataSourceFactory implements DataSourceFactory {

    private ComboPooledDataSource dataSource;

    public C3P0DataSourceFactory(){
        dataSource = new ComboPooledDataSource();
    }

    @Override
    public void setProperties(Properties properties) {
        try {
            dataSource.setDriverClass(properties.getProperty("driver"));
            dataSource.setJdbcUrl(properties.getProperty("url"));
            dataSource.setUser(properties.getProperty("username"));
            dataSource.setPassword(properties.getProperty("password"));
            dataSource.setMaxPoolSize(Integer.parseInt(properties.getProperty("maxPoolSize")));
            dataSource.setMinPoolSize(Integer.parseInt(properties.getProperty("minPoolSize")));
            dataSource.setInitialPoolSize(Integer.parseInt(properties.getProperty("initialPoolSize")));
            dataSource.setMaxIdleTime(Integer.parseInt(properties.getProperty("maxIdleTime")));
            dataSource.setCheckoutTimeout(Integer.parseInt(properties.getProperty("checkoutTimeout")));
            dataSource.setAcquireIncrement(Integer.parseInt(properties.getProperty("acquireIncrement")));
            dataSource.setAcquireRetryAttempts(Integer.parseInt(properties.getProperty("acquireRetryAttempts")));
            dataSource.setAcquireRetryDelay(Integer.parseInt(properties.getProperty("acquireRetryDelay")));
            dataSource.setAutoCommitOnClose(Boolean.parseBoolean(properties.getProperty("autoCommitOnClose")));
            dataSource.setPreferredTestQuery(properties.getProperty("preferredTestQuery"));
            dataSource.setTestConnectionOnCheckin(Boolean.parseBoolean(properties.getProperty("testConnectionOnCheckin")));
            dataSource.setBreakAfterAcquireFailure(Boolean.parseBoolean(properties.getProperty("breakAfterAcquireFailure")));
            dataSource.setIdleConnectionTestPeriod(Integer.parseInt(properties.getProperty("idleConnectionTestPeriod")));
            dataSource.setMaxStatements(Integer.parseInt(properties.getProperty("maxStatements")));
            dataSource.setMaxStatementsPerConnection(Integer.parseInt(properties.getProperty("maxStatementsPerConnection")));
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
//        dataSource.setProperties(properties);
    }

    @Override
    public DataSource getDataSource() {
        return dataSource;
    }
}
