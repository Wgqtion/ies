package com.vsc.modules.tcp.core;
import com.vsc.util.Log4jUtils;
import com.vsc.util.PropertiesUtil;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * tcp服务
 * @author XiangXiaoLin
 *
 */
public class TcpServer {
	private static final String IP;
	private static final int PORT;
	/**用于分配处理业务线程的线程组个数 */
	protected static final int BIZGROUPSIZE = Runtime.getRuntime().availableProcessors()*2;	//默认
	private static final EventLoopGroup bossGroup = new NioEventLoopGroup(BIZGROUPSIZE);
	private static final EventLoopGroup workerGroup;
	
	static{
		IP=PropertiesUtil.getValueByKey("tcp.ip");
		PORT=Integer.valueOf(PropertiesUtil.getValueByKey("tcp.port"));
		workerGroup=new NioEventLoopGroup(Integer.valueOf(PropertiesUtil.getValueByKey("biz.thread.size")));
	}
	//启动
	public static void run() {
		Log4jUtils.tcpLog.info("开始启动TCP服务器...");
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup);
			b.channel(NioServerSocketChannel.class);
			b.childHandler(new ChannelInitializer<SocketChannel>() {
				@Override
				public void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new TcpDecode(Integer.MAX_VALUE, 0,0, 0,0,false));  
					ch.pipeline().addLast(new TcpServerHandler());
				}
			});
			b.childOption(ChannelOption.SO_KEEPALIVE, true);

			ChannelFuture f=b.bind(IP, PORT).sync();
			Log4jUtils.tcpLog.info("TCP服务器已启动,IP:"+IP+",PORT:"+PORT);
			f.channel().closeFuture().sync();
		} catch (Exception e) {
			Log4jUtils.tcpLog.error("exception:"+TcpServer.class + ",Message:" + e.getMessage());
		}finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
	}
	//停止
	public static void stop(){
		workerGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();
        Log4jUtils.tcpLog.info("TCP服务器已停止");
	}
}