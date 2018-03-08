package com.vsc.business.core.repository.security;

import com.vsc.business.core.entity.security.User;
import com.vsc.modules.repository.BaseDao;

public interface UserDao extends BaseDao<User> {
	User findByLoginName(String loginName);
}
