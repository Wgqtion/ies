package com.vsc.business.core.service.sys;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vsc.business.core.entity.sys.SysLog;
import com.vsc.business.core.repository.sys.SysLogDao;
import com.vsc.modules.service.BaseService;

@Service
@Transactional
public class SysLogService extends BaseService<SysLog> {
	private static Logger logger = LoggerFactory.getLogger(SysLogService.class);

	private SysLogDao sysLogDao;
	
	@Autowired
	public void setSysLogDao(SysLogDao sysLogDao) {
		this.sysLogDao = sysLogDao;
	}

	@Override
	public PagingAndSortingRepository<SysLog, Long> getPagingAndSortingRepositoryDao() {
		return this.sysLogDao;
	}

	@Override
	public JpaSpecificationExecutor<SysLog> getJpaSpecificationExecutorDao() {
		return this.sysLogDao;
	}

}
