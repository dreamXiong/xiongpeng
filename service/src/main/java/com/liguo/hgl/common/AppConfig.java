package com.liguo.hgl.common;

import com.liguo.hgl.proxydao.util.StringUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.Properties;

/**
 * Created by Daisy on 2015/9/1.
 */
public class AppConfig {

    private static final Logger logger = LoggerFactory.getLogger(AppConfig.class);
    private static AppConfig appConfig;
    private static String configFile = HglContants.CONFIG_FILE;
    private Properties properties;

    private AppConfig(){
        try{
            File file = new File(configFile);
            if(!file.exists()){
                String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
                file = new File(path + File.separator + configFile);
                if(!file.exists()){
                    Properties props = System.getProperties();
                    Iterator iter = props.keySet().iterator();
                    logger.debug("======== JVM: System.Properties List ========");
                    while (iter.hasNext()){
                        String key = (String)iter.next();
                        logger.debug(key + "=" + props.get(key));
                    }
                    logger.error((new StringBuilder().append("<<<AppConfig>>> can't find the configuration file:").append(configFile).toString()));
                    return;
                }
            }
            logger.debug("<<<AppConfig>>> Load config file:" + configFile);
            FileInputStream fileInputStream = new FileInputStream(file);
            properties = new Properties();
            properties.load(fileInputStream);
            fileInputStream.close();
        }catch (Exception e){
            e.printStackTrace();
            logger.error("<<<AppConfig>>> Exception", e);
        }
    }

    public static AppConfig getInstance(){
        if(appConfig == null){
            appConfig = new AppConfig();
        }
        return appConfig;
    }

    public String getProperty(String key){
        String ret = null;
        if(null!=properties && StringUtil.isNotBlank(key)){
            ret = properties.getProperty(key);
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(AppConfig.getInstance().getProperty("mongodb.host"));
        System.out.println(AppConfig.getInstance().getProperty("jdbc.spring.url"));
    }

}
