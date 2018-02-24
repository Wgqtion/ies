package com.vsc.constants;

import com.vsc.util.SystemSettings;

public class Constants {
	/**
	 * 路径分隔符
	 */
	public static final char SPT = '/';
	public static final String SYS_NAME = "上海宜事智能停车管理平台";
	/**
	 * 系统表名前缀
	 */
	public static final String TABLE_PREFIX="t_";
	
	
	/**
	 * 附件上传存放的相对文件夹名称
	 */
	public static final String UPLOAD_ROOT_FOLDER = "upload";

	/**
	 * 字符串  转  数组分隔符
	 */
	public static final String SPT_ARRAY = ",";

	/**
	 * 文件保存编码设置
	 */
	public static final String ENCODING = "UTF-8";

	/**
	 * 用户路径
	 */
	public static final String USER_BASE = "user_base";

	/**
	 * WEB-INF
	 */
	public static final String WEBINF = "WEB-INF";

	public static final String USER_ROOT = SPT + WEBINF + SPT + USER_BASE + SPT;

	/**
	 * 获取系统配置
	 * @return
	 */
	public static SystemSettings getSystemSetting() { 
		return  SystemSettings.getInstance();
	}

	/**
	 * 图像文件的类型
	 */
	public static final String[] IMG_TYPE = { "gif", "jpg", "png", "jpe", "jpeg" };

}
