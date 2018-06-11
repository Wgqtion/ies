package com.vsc.modules.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.vsc.modules.quartz.manager.QuartzManager;
import com.vsc.util.Log4jUtils;
import com.vsc.util.WxCoreServiceUtil;

/**
 * 需要加入的定时任务
 * @author XiangXiaoLin
 *
 */
public class QuartzListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		WxCoreServiceUtil.getWxCoreService().addCancelJobs();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		Log4jUtils.reserveCancel.info("开始关闭所有定时任务");
		QuartzManager.shutdownJobs();
	}

}
