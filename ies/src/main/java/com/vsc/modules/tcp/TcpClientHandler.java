package com.vsc.modules.tcp;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.vsc.business.gerd.entity.work.ParkingLock;
import com.vsc.business.gerd.entity.work.ParkingLockOperationEvent;
import com.vsc.business.gerd.service.work.ParkingLockOperationEventService;
import com.vsc.util.HexUtils;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

public class TcpClientHandler extends ChannelInboundHandlerAdapter {
	
	private static Logger logger = LoggerFactory.getLogger(TcpClientHandler.class);
	
	
	@Autowired
	private ParkingLockOperationEventService parkingLockOperationEventService;

	public TcpClientHandler(){
		super();
	}
	public TcpClientHandler(ParkingLockOperationEventService parkingLockOperationEventService){
		super();
		this.parkingLockOperationEventService=parkingLockOperationEventService;
	}
	
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf buffer = (ByteBuf) msg; // (1)
        try {
        	String str=ByteBufUtil.hexDump(buffer);
        	logger.info(str);
//        	String user=HexUtils.HexToDec(str, 0,8);//不适用，多个消息user会被覆盖
    		String lockArea=HexUtils.HexToDec(str, 8,12);
    		String lockNum=HexUtils.HexToDec(str, 12,16);
    		Integer state=Integer.valueOf(HexUtils.HexToDec(str, 16,18));//1正常，2失败
    		Map<String, Object> filterParams=new HashMap<String, Object>();
    		ParkingLock parkingLock=parkingLockOperationEventService.getParkingLockByCode(lockArea,lockNum);
    		filterParams.put("EQ_parkingLock.id",parkingLock.getId());
    		filterParams.put("EQ_status",0);
    		List<ParkingLockOperationEvent> ploes=parkingLockOperationEventService.findAll(filterParams, "createTime","DESC");
    		if(ploes!=null){
    			ParkingLockOperationEvent event=ploes.get(0);
    			if(event!=null){
    				event.setStatus(state);
    				this.parkingLockOperationEventService.save(event);
    			}
    		}
    		ctx.close();
        } catch(Exception exception){
        	logger.error(this.getClass()+exception.getMessage());
        	exception.printStackTrace();
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    	logger.error(cause.getMessage());
        cause.printStackTrace();
        ctx.close();
    }
   
}

