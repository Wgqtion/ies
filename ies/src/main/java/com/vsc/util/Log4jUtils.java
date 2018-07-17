package com.vsc.util;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log4jUtils {
	/**
	 * tcp通用日志
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
	
	/**
	 * tcpError日志
	 */
	public static Logger tcpError = Logger.getLogger("tcpError");
	
	/**
	 * 预约超时取消日志
	 */
	public static Logger reserveCancel = Logger.getLogger("reserveCancel");
	
	/**
	 * 主信息日志
	 */
	public static Logger mainInfo = Logger.getLogger("mainInfo");
	
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
