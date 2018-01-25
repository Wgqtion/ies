package com.vsc.modules.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ConcurrentAccessException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.subject.WebSubject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.vsc.business.core.entity.security.User;
import com.vsc.business.core.entity.sys.LoginLog;
import com.vsc.business.core.service.security.ShiroDbRealm.ShiroUser;
import com.vsc.business.core.service.sys.LoginLogService;
import com.vsc.util.CoreUtils;

/**
 * 实现shiro框架验证监听来完成登录登出日志记录
 * @author Administrator
 *
 */
public class AuthenticationListener implements org.apache.shiro.authc.AuthenticationListener{
	private static Logger logger = LoggerFactory.getLogger(AuthenticationListener.class);
	@Autowired
	private LoginLogService loginLogService;
	
	@Override
	public void onSuccess(AuthenticationToken token, AuthenticationInfo info) {
		ShiroUser su = (ShiroUser)info.getPrincipals().getPrimaryPrincipal();
		loginLog("登录成功",token,su);
	}

	@Override
	public void onFailure(AuthenticationToken token, AuthenticationException ae) {
		String msg = "登录失败";
		if(ae != null){
			if(ae instanceof ExpiredCredentialsException){
				//msg += "-用户不存在";
			}else if(ae instanceof IncorrectCredentialsException){
				msg += "-密码错误";
			}else if(ae instanceof ExcessiveAttemptsException){
				//msg += "-用户不存在";
			}else if(ae instanceof LockedAccountException){
				msg += "-锁定用户";
			}else if(ae instanceof ConcurrentAccessException){
				//msg += "-用户不存在";
			}else if(ae instanceof UnknownAccountException){
				msg += "-用户不存在";
			}
		}
		loginLog(msg,token,null);
	}

	@Override
	public void onLogout(PrincipalCollection principals) {
		ShiroUser su = (ShiroUser)principals.getPrimaryPrincipal();
		loginLog("退出",null,su);
	}
	
	private void loginLog(String logContent,AuthenticationToken token,ShiroUser su){
		try{
			LoginLog entity = new LoginLog();
			entity.setCreateTime(CoreUtils.nowtime());
			entity.setLogContent(logContent);
		
			if(su != null){
				User u = new User();
				u.setId(su.id);
				entity.setUserName(su.getName());
				entity.setUser(u);
			}else{
				entity.setUserName(((UsernamePasswordToken)token).getUsername());
			}
			Subject subject = SecurityUtils.getSubject();
			if(subject instanceof WebSubject){
				entity.setSessionId(subject.getSession().getId().toString()); 
				entity.setUserIp(CoreUtils.getIpAddr((javax.servlet.http.HttpServletRequest)((WebSubject)subject).getServletRequest()));
			}
			loginLogService.save(entity);
		}catch(Exception e){
			logger.error("记录登录日志出错",e);
		}
	}

}
