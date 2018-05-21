package com.vsc.modules.tcp.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.vsc.modules.tcp.entity.TcpMsg;
import com.vsc.util.Log4jUtils;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * 解码器
 * @author XiangXiaoLin
 *
 */
public class TcpDecode extends LengthFieldBasedFrameDecoder {
	
	/** 
     *  
     * @param maxFrameLength 解码时，处理每个帧数据的最大长度 
     * @param lengthFieldOffset 该帧数据中，存放该帧数据的长度的数据的起始位置 
     * @param lengthFieldLength 记录该帧数据长度的字段本身的长度 
     * @param lengthAdjustment 修改帧数据长度字段中定义的值，可以为负数 
     * @param initialBytesToStrip 解析的时候需要跳过的字节数 
     * @param failFast 为true，当frame长度超过maxFrameLength时立即报TooLongFrameException异常，为false，读取完整个帧再报异常 
     */  
    public TcpDecode(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength,  
            int lengthAdjustment, int initialBytesToStrip, boolean failFast) {  
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength,  
                lengthAdjustment, initialBytesToStrip, failFast);  
    } 
    
    
    @Override  
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) {
        List<TcpMsg> tcpMsgs=new ArrayList<TcpMsg>();
    	try {
	        if (in == null) {  
	            return null;  
	        }  
	        byte[] reds=new byte[13];
			byte red=0;
			int f=0;
			while(in.isReadable()){
				red=in.readByte();
				reds[f]=red;
				//开头错误数据
				if(reds[0]!=127&&reds[1]!=127){
					Log4jUtils.tcpLog.error("headError:"+Arrays.toString(reds));
					reds=new byte[13];
					f=0;
					continue;
				}
				//正常开头与结尾数据
				if(f==12&&reds[f-1]==13&&reds[f]==10){
					TcpMsg tcpMsg=new TcpMsg(reds);
					tcpMsgs.add(tcpMsg);
					Log4jUtils.tcpDecode.info("ok:"+tcpMsg.getHexMsg());
					reds=new byte[13];
					f=0;
					continue;
				}
				//错误数据
				if(f==12){
					Log4jUtils.tcpLog.error("endError:"+Arrays.toString(reds));
					reds=new byte[13];
					f=0;
					continue;
				}
				f++;
			}
		} catch (Exception e) {
			Log4jUtils.tcpLog.error("exception:"+this.getClass()+","+e.getMessage());
		}
        return tcpMsgs;  
    }
}
