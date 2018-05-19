package com.vsc.modules.tcp.core;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.vsc.business.gerd.service.work.ParkingLockEventLogService;
import com.vsc.modules.tcp.entity.TcpMsg;
import com.vsc.util.Log4jUtils;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * 业务处理
 * 
 * @author XiangXiaoLin
 *
 */
public class TcpServerHandler extends ChannelInboundHandlerAdapter {

	private static ParkingLockEventLogService parkingLockEventLogService;

	@Autowired
	public static void setParkingLockEventLogService(ParkingLockEventLogService parkingLockEventLogService) {
		TcpServerHandler.parkingLockEventLogService = parkingLockEventLogService;
	}

	

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		super.channelActive(ctx);
		Log4jUtils.tcpHandler.info("用户建立连接:" + ctx.channel().remoteAddress());
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		super.channelInactive(ctx);
		Log4jUtils.tcpHandler.info("不活跃的用户:" + ctx.channel().remoteAddress());
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		try {
			List<TcpMsg> tcpMsgs = null;
			if (msg instanceof List) {
				tcpMsgs = (List<TcpMsg>) msg;
			} else {
				Log4jUtils.tcpLog.warn("非解析器数据");
			}
			if (tcpMsgs != null) {
				for (TcpMsg tcpMsg : tcpMsgs) {
					ClientMap.lockMap.put(tcpMsg.getIpAddress(), ctx);
					if (tcpMsg.getFlagCRC()) {
						this.parkingLockEventLogService.tcpUpload(tcpMsg);
					} else {
						Log4jUtils.tcpLog.error("CRCError:" + tcpMsg.getHexMsg());
					}
				}
			}
		} catch (Exception exception) {
			Log4jUtils.tcpLog.error("exception:" + this.getClass() + ",Message:" + exception.getMessage());
		} finally {
			ReferenceCountUtil.release(msg);
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
		// 当出现异常就关闭连接
		Log4jUtils.tcpHandler.error("异常断开连接的用户:" + ctx.channel().remoteAddress() + ",Message:" + cause.getMessage());
		ctx.close();
	}
}
