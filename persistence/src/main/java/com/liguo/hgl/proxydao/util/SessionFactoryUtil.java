package com.liguo.hgl.proxydao.util;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * Mybatis SessionFactory工具类
 * 
 * @author Daniel
 * @version 1.0
 */
public class SessionFactoryUtil {

    private static final String RESOURCE = "mybatis-configuration.xml";
    private static SqlSessionFactory sqlSessionFactory = null;
    private static ThreadLocal<SqlSession> threadLocal = new ThreadLocal<SqlSession>();
    private static ThreadLocal<Boolean> autoCommit = new ThreadLocal<Boolean>();

    static {
        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader(RESOURCE);
        } catch (IOException e) {
            throw new RuntimeException("Get resource error:"+RESOURCE, e);
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
    }

    /**
     *  获得SqlSessionFactory
     */
    public static SqlSessionFactory getSqlSessionFactory(){
        return sqlSessionFactory;
    }

    /**
     *  重新创建SqlSessionFactory
     */
    public static void rebuildSqlSessionFactory(){
        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader(RESOURCE);
        } catch (IOException e) {
            throw new RuntimeException("Get resource error:"+RESOURCE, e);
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
    }

    /**
     *  获取sqlSession
     */
    public static SqlSession getSession(){
        SqlSession session = threadLocal.get();
        if(session==null){
//            try {
//                TimeUnit.MILLISECONDS.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//            }
            if(sqlSessionFactory == null){
                getSqlSessionFactory();
            }
            //如果sqlSessionFactory不为空则获取sqlSession，否则返回null
            session = (sqlSessionFactory!=null) ? sqlSessionFactory.openSession(): null;
            threadLocal.set(session);
        }
        return session;
    }

    /**
     *  获取sqlSession
     */
    public static SqlSession getSession(ExecutorType executorType, boolean arg){
        SqlSession session = threadLocal.get();
        if(session==null){
            if(sqlSessionFactory == null){
                getSqlSessionFactory();
            }
            //如果sqlSessionFactory不为空则获取sqlSession，否则返回null
            session = (sqlSessionFactory!=null) ? sqlSessionFactory.openSession(executorType, arg): null;
            threadLocal.set(session);
        }
        return session;
    }
    
    /**
     *  关闭sqlSession
     */
    public static void closeSession(){
        SqlSession session = threadLocal.get();
        threadLocal.set(null);
        if(session!=null){
            session.close();
        }
        if(null != autoCommit.get()){
            autoCommit.set(true);
        }
    }

    public static boolean getAutoCommit() {
        if(null == autoCommit.get()){
            return true;
        }
        return autoCommit.get();
    }

    public static void setAutoCommit(boolean auto) {
//        SessionFactoryUtil.autoCommit = autoCommit;
        autoCommit.set(auto);
    }

}
