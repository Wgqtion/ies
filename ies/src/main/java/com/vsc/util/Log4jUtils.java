package com.vsc.util;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log4jUtils {
	/**
	 * 通用日志
	 */
	public static Logger tcpLog = Logger.getLogger("tcpLog");
	/**
	 * 编码解析日志
	 */
	public static Logger tcpDecode = Logger.getLogger("tcpDecode");
	/**
	 * Handler日志
	 */
	public static Logger tcpHandler = Logger.getLogger("tcpHandler");
	
	static{
		Properties props=new Properties();  
        try {  
            props.load(Log4jUtils.class  
                    .getClassLoader()  
                    .getResourceAsStream("log4j.properties")  
                    ); 
		PropertyConfigurator.configure(props);
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
	}
}
