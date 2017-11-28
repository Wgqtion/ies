package com.vsc.business.core.service.sys;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vsc.business.core.entity.sys.LoginLog;
import com.vsc.business.core.repository.sys.LoginLogDao;
import com.vsc.modules.service.BaseService;

@Service
@Transactional
public class LoginLogService extends BaseService<LoginLog> {
	private static Logger logger = LoggerFactory.getLogger(LoginLogService.class);

	private LoginLogDao loginLogDao;
	
	@Autowired
	public void setLoginLogDao(LoginLogDao loginLogDao) {
		this.loginLogDao = loginLogDao;
	}

	@Override
	public PagingAndSortingRepository<LoginLog, Long> getPagingAndSortingRepositoryDao() {
		return this.loginLogDao;
	}

	@Override
	public JpaSpecificationExecutor<LoginLog> getJpaSpecificationExecutorDao() {
		return this.loginLogDao;
	}

}
