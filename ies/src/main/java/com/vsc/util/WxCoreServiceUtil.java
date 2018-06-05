package com.vsc.util;

import org.springframework.beans.factory.annotation.Autowired;

import com.vsc.business.gerd.service.work.WxCoreService;

/**
 * 获取wxCoreService
 * @author XiangXiaoLin
 *
 */
public class WxCoreServiceUtil {
	private static WxCoreService wxCoreService;

	@Autowired
	public static void setWxCoreService(WxCoreService wxCoreService) {
		WxCoreServiceUtil.wxCoreService = wxCoreService;
	} 
	
	public static WxCoreService getWxCoreService(){
		return WxCoreServiceUtil.wxCoreService;
	}
}
