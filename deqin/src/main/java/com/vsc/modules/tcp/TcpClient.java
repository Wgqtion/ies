package com.vsc.modules.tcp;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vsc.business.gerd.service.work.ParkingLockOperationEventService;
import com.vsc.util.PropertiesUtil;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;



public class TcpClient {
	
	private static Logger logger = LoggerFactory.getLogger(TcpClient.class);
	
	/**
	 * tcp服务地址
	 */
	public static final String HOST;
	/**
	 * tcp服务端口
	 */
	public static final int PORT;

	public static Bootstrap bootstrap =null;
	
	static{
		HOST=PropertiesUtil.getValueByKey("tcpServer.ip");
		PORT=Integer.valueOf(PropertiesUtil.getValueByKey("tcpServer.port"));
	}
	
	/**
	 * 初始化Bootstrap
	 * @return
	 */
	public static final Bootstrap getBootstrap(final ParkingLockOperationEventService parkingLockOperationEventService){
		EventLoopGroup group = new NioEventLoopGroup();
		Bootstrap b = new Bootstrap();
		b.group(group).channel(NioSocketChannel.class);
		b.handler(new ChannelInitializer<Channel>() {
			@Override
			protected void initChannel(Channel ch) throws Exception {
				ChannelPipeline pipeline = ch.pipeline();
				pipeline.addLast("handler", new TcpClientHandler(parkingLockOperationEventService));
			}
		});
		b.option(ChannelOption.SO_KEEPALIVE, true);
		return b;
	}

	public static String sendMsg(ByteBuf msg,ParkingLockOperationEventService parkingLockOperationEventService) throws Exception {
		
		String str="";
		if(bootstrap==null){
			bootstrap=getBootstrap(parkingLockOperationEventService);
		}
		Channel channel = bootstrap.connect(HOST, PORT).sync().channel();
		if(channel!=null){
			channel.writeAndFlush(msg);
		}else{
			logger.error("消息发送失败,连接尚未建立!");
			str="请求失败，服务器与地锁失去连接";
		}
		return str;
	}
}


