package com.vsc.modules.tcp.core;

import java.util.Arrays;
import java.util.List;

import com.vsc.modules.tcp.entity.TcpMsg;
import com.vsc.util.Log4jUtils;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

/**
 * 解码器
 * @author XiangXiaoLin
 *
 */
public class TcpDecode extends ByteToMessageDecoder {
	
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        byte[] reds=new byte[13];
		byte red=0;
		int f=0;
		while(in.isReadable()){
			int len=in.readableBytes();
			if(f==0&&len<13){
				return;
			}
			red=in.readByte();
			reds[f]=red;
			//开头错误数据
			if(reds[0]!=127||(f>0&&reds[1]!=127)){
				Log4jUtils.tcpError.info("headError:"+Arrays.toString(reds));
				reds=new byte[13];
				f=0;
				continue;
			}
			//正常开头与结尾数据
			if(f==12&&reds[f-1]==13&&reds[f]==10){
				TcpMsg tcpMsg=new TcpMsg(reds);
				out.add(tcpMsg);
				Log4jUtils.tcpDecode.info("ok:"+tcpMsg.getHexMsg());
				reds=new byte[13];
				f=0;
				continue;
			}
			//错误数据
			if(f==12){
				Log4jUtils.tcpError.info("endError:"+Arrays.toString(reds));
				reds=new byte[13];
				f=0;
				continue;
			}
			f++;
		}
	}
}
