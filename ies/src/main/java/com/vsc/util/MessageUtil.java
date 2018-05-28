package com.vsc.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * Java属性文件操作工具类
 * <p>由于每个人的需求不一样，所以本工具只提供常用的功能（包括根据键名获取键值，根据键名修改键值），
 * 如果不满足，自个完全可以修改，在本工具类中，我们假设属性文件已经存在，并且文件中已经存在我们要操作的key</p>
 * @author HsuChan
 * @version v1.0.0 2015-06-19
 */
public class MessageUtil {
    private static Properties messageProperties= new Properties();
    private static Logger logger = Logger.getLogger(MessageUtil.class);
    /**测试用 属性文件名*/
    private static final String PROPERTIES_FILE_PATH="/vsc_messages.properties";
    
    
    /**
     * 初始化properties
     * @param path 属性文件路径,如:/config.properties
     */
    private static void initProperties(String path) {
        
        try {
            InputStream ipsm = MessageUtil.class.getResourceAsStream(path);
            messageProperties.load(ipsm);
            ipsm.close();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * 根据键名key获取属性文件中对应的键值
     * @param path 属性文件路径,如:/config.properties
     * @param key 属性文件 键名
     * @return
     */
    public static String getValueByKey(String key){
        //如果properties为空，则初始化
        if(messageProperties.isEmpty())
            initProperties(PROPERTIES_FILE_PATH);
        return messageProperties.getProperty(key);
    }
   
}