package com.vsc.modules.thread;

import com.vsc.modules.tcp.core.TcpServer;

public class TcpThread extends Thread {
	public void run() {  
		TcpServer.run();
	}
	
}
