package com.vsc.modules.tcp.core;

import java.util.concurrent.ConcurrentHashMap;

import io.netty.channel.ChannelHandlerContext;

/**
 * 客户端连接集合
 * @author XiangXiaoLin
 *
 */
public class ClientMap {
	/**
	 * 地锁网关连接
	 */
	public static ConcurrentHashMap<String, ChannelHandlerContext> lockMap = new ConcurrentHashMap<String, ChannelHandlerContext>();
}
