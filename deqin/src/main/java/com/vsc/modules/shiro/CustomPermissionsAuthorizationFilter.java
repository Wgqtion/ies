package com.vsc.modules.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
/**
 * 定制授权过滤器，用于实现任意授权功能。
 * permission any
 * @author houyj
 *
 */
public class CustomPermissionsAuthorizationFilter extends AuthorizationFilter{

	@Override
	protected boolean isAccessAllowed(ServletRequest request,ServletResponse response, Object mappedValue) throws Exception {
		Subject subject = getSubject(request, response);
        String[] perms = (String[]) mappedValue;

        boolean isPermitted = true;
        if (perms != null && perms.length > 0) {
            if (perms.length == 1) {
                if (!subject.isPermitted(perms[0])) {
                    isPermitted = false;
                }
            } else {
            	isPermitted = false;
            	for(String perm : perms){
                	if (subject.isPermitted(perm)) {
                		isPermitted = true;
                        break;
                    }
                }
            }
        }
        return isPermitted;
	}

}
