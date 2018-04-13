package com.vsc.modules.quartz.job;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class MyJob implements Job {
	@Override 
    public void execute(JobExecutionContext context) throws JobExecutionException { 
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS"); 
        System.out.println(sdf.format(new Date())); 
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();  
    } 
}
