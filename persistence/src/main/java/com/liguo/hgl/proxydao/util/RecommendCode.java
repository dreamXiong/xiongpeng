package com.liguo.hgl.proxydao.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class RecommendCode implements InitializingBean, ApplicationContextAware ,IdCreator {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    /**数据源，默认使用系统配置的数据源id="jndiDataSource",如果要使用其他数据源，自行配置*/
    private DataSource dataSource;
    
    /**保存序列号信息的表，这个可配置，不配默认是tb_sys_seq_no*/
    private String tableName = "tb_sys_seq_no";
    
    /**序列号类型，标示是那个序列号生成器，必须配置*/
    private String type;
    
    /**步长：一次取多少个序号*/
    private Integer step = 1;
    
    //当前步数
    private int currentStep = 0;
    
    private ApplicationContext applicationContext;
    
    
    
    //当前系列号
    private int currentSequenceNo;

    private void updateSeqNo() throws Exception {
        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedstatement = null;
        ResultSet result = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            result = statement.executeQuery("select seq_no, datetime from " + tableName + " where type = '" + type + "' for update");
            result.next();
            preparedstatement = connection.prepareStatement("UPDATE " + tableName + " SET seq_no=?, datetime=? WHERE type=?");
            currentSequenceNo = result.getInt(1);
            preparedstatement.setInt(1, currentSequenceNo + step);
            preparedstatement.setTimestamp(2, new Timestamp(new Date().getTime()));
            preparedstatement.setString(3, type);
            preparedstatement.executeUpdate();
            connection.commit();
            ++currentSequenceNo;
        }
        catch (Exception e) {
            logger.error("holdStpNoAndUpdate " + tableName + " error happen.", e);
            if (connection != null)
                connection.rollback();
            throw new Exception("holdStpNoAndUpdate " + tableName + " error happen.", e);
        }
        finally {
            if (result != null) {
                result.close();
                result = null;
            }
            if (statement != null) {
                statement.close();
                statement = null;
            }
            if (preparedstatement != null) {
                preparedstatement.close();
                preparedstatement = null;
            }
            if (connection != null) {
                connection.close();
                connection = null;
            }
        }
    }
    
    
    private  synchronized Integer getRecommendCode() {
        try {
            if (currentStep == step || currentStep == 0) {
                resetStep();
            }
            else if (currentStep > step) {
                // 这种情况不会发生，发生了就有问题了。
                logger.error("create sequence error happen: currentStep gt step, currentStep: " + currentStep);
                resetStep();
            }
            else {
                ++currentSequenceNo;
                ++currentStep;
            }
            return currentSequenceNo;
        }
        catch (Exception e) {
            logger.error("create id error happen.");
            throw new RuntimeException("create id error happen.", e);
        }
    }
    
    /*
     * 重新取当前序号
     * @throws Exception
     * @author Administrator
     * @since   2015年8月3日
     */
    private void resetStep() throws Exception {
        updateSeqNo();
        currentStep = 1;
    }
    
    

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            this.applicationContext = applicationContext;
    }
    
    private void initTable() throws Exception {
        logger.info(">>>>>>>>>>>>>>>>>>>>>>tb_sys_seq_no initTable start ...");
        Connection connection = null; 
        Statement statement = null;
        PreparedStatement preparedstatement = null;
        ResultSet result = null;
        /**序列号类型长度，最长10位*/
        Integer typeLen = 10;
        try {        
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            result = connection.getMetaData().getTables(null, null, tableName, null);
            statement = connection.createStatement();
            if(result.next()) {
                logger.warn(">>>>>>>>>>>>>>>>>>"+tableName + " already exist.");
            } else {
                String createTable = (new StringBuilder("create table ")).append(tableName).append("(").append("type").append(" varchar("+typeLen+") not null, ").append("seq_no").append(" bigint, datetime TIMESTAMP, primary key(").append("type").append("))").toString();
                logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>initTable sql :"+createTable);
                statement.execute(createTable);
            }
            logger.debug("check data.");
            result = statement.executeQuery("select count(*) from " + tableName + " where type = '" + type + "'");
            result.next();
            int count = result.getInt(1);
            if(count == 0) {
                preparedstatement = connection.prepareStatement("INSERT INTO " + tableName + " (type, seq_no, datetime) VALUES (?, ?, ?)");
                preparedstatement.setString(1, type);
                preparedstatement.setInt(2, 1000);
                preparedstatement.setTimestamp(3, new Timestamp(new Date().getTime()));
                preparedstatement.execute();
            }
            connection.commit();
        } catch(SQLIntegrityConstraintViolationException e) {
            //如果是报主键约束异常，则忽略。因为在初始数据时，可能并发。
            logger.debug("ignore exception: constraint (primary key) has been violated. ", e);
            if(connection != null)
                connection.rollback();
        } catch(SQLSyntaxErrorException e) {
            //如果是报SQLSyntaxErrorException异常，则忽略。因为在创建表的时候，可能并发
            logger.debug("ignore exception: table '" + tableName + "' already exists", e);
            if(connection != null)
                connection.rollback();
        } catch(Exception e) {
            logger.error("init table " + tableName + " error happen.", e);
            if(connection != null)
                connection.rollback();
            throw new Exception("init table " + tableName + " error happen.", e);
        } finally {
            if(result != null) {
                result.close();
                result = null;
            }
            if(statement != null) {
                statement.close();
                statement = null;
            }
            if(preparedstatement != null) {
                preparedstatement.close();
                preparedstatement = null;
            }
            if(connection != null) {
                connection.close();
                connection = null;
            }
        }
    }
    

    @Override
    public String create() {
        Integer code =getRecommendCode();
        return String.valueOf(code);
    }


    public String getType() {
        return type;
    }


    public void setType(String type) {
        this.type = type;
    }


    public Integer getStep() {
        return step;
    }


    public void setStep(Integer step) {
        this.step = step;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        if (dataSource == null)
            dataSource = (DataSource) applicationContext.getBean("jndiDataSource");
        try {
            initTable();
        }
        catch (Exception e) {
            logger.error(e.getMessage());            
        }
    }
    
    
    

}
