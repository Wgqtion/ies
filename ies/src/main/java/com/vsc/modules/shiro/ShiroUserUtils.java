package com.vsc.modules.shiro;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.vsc.business.core.entity.security.User;
import com.vsc.business.core.service.security.ShiroDbRealm.ShiroUser;
import com.vsc.business.core.service.security.UserService;

/**
 * Shiro用户辅助类
 * @author XiangXiaoLin
 *
 */
public class ShiroUserUtils {
	
	private static UserService userService;

	
	@Autowired
	public static void setUserService(UserService userService) {
		ShiroUserUtils.userService = userService;
	}
	/**
	 * 返回当前用户
	 * @return
	 * @throws Exception 
	 */
	public static User GetCurrentUser() throws Exception{
		ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		if (shiroUser != null && shiroUser.id != null) {
			return userService.getObjectById(shiroUser.id);
		}
		throw new Exception("无用户信息，请重新登录");
	}
}
