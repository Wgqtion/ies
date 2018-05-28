package com.vsc.modules.quartz.job;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import com.vsc.business.gerd.entity.work.WxCore;
import com.vsc.business.gerd.service.work.WxCoreService;
import com.vsc.constants.Constants;
import com.vsc.util.Log4jUtils;

/**
 * 超时自动取消预约
 * @author XiangXiaoLin
 *
 */
public class ReserveCancelJob implements Job {
	
	private static WxCoreService wxCoreService;

	@Autowired
	public static void setWxCoreService(WxCoreService wxCoreService) {
		ReserveCancelJob.wxCoreService = wxCoreService;
	} 
	
	@Override 
    public void execute(JobExecutionContext context) throws JobExecutionException { 
		Log4jUtils.reserveCancel.info("系统开始取消超时预约：");
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();  
        if(dataMap!=null){
        	String weixinId=(String) dataMap.get("data1");
        	if(weixinId!=null){
            	WxCore wxCore=new WxCore();
        		wxCore.setWeixinId(weixinId);
        		wxCore.setIsCancel(true);
        		wxCore.setType(Integer.valueOf(1));
        		wxCore.setIsSystemCancel(true);
        		int status=wxCoreService.cancelReserve(wxCore,false);
        		Log4jUtils.reserveCancel.info("系统取消超时预约："+Constants.CANCEL_RESERVE_MESSAGE_STATUS[status]);
        	}
		}
    }

}
