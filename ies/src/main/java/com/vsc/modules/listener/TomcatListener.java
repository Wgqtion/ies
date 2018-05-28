package com.vsc.modules.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.vsc.modules.tcp.core.TcpServer;
import com.vsc.modules.thread.TcpThread;

/**
 * 
 * @author XiangXiaoLin
 *
 */
public class TomcatListener implements ServletContextListener{
	
	TcpThread tcpThread=null;
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		if(tcpThread==null){
			tcpThread=new TcpThread();	
		}
		tcpThread.start();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		TcpServer.stop();
		if(tcpThread!=null){
			tcpThread.interrupt();	
		}
	}

}
