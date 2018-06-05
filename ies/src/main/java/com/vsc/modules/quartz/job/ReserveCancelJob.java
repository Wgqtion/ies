package com.vsc.modules.quartz.job;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.vsc.business.gerd.entity.work.WxCore;
import com.vsc.constants.Constants;
import com.vsc.util.Log4jUtils;
import com.vsc.util.WxCoreServiceUtil;

/**
 * 超时自动取消预约
 * @author XiangXiaoLin
 *
 */
public class ReserveCancelJob implements Job {
	
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
        		int status=WxCoreServiceUtil.getWxCoreService().cancelReserve(wxCore,false);
        		Log4jUtils.reserveCancel.info("系统取消超时预约："+Constants.CANCEL_RESERVE_MESSAGE_STATUS[status]);
        	}
		}
    }

}
