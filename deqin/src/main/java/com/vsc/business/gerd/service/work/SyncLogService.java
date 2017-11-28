package com.vsc.business.gerd.service.work;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.vsc.business.gerd.entity.work.SyncLog;
import com.vsc.business.gerd.repository.work.SyncLogDao;
import com.vsc.modules.service.BaseService;

/**
 * 
 * @author Administrator
 * 
 */
@Service
@Transactional
public class SyncLogService extends BaseService<SyncLog> {
	private static Logger logger = LoggerFactory
			.getLogger(SyncLogService.class);

	@Autowired
	private SyncLogDao syncLogDao;

	@Override
	public PagingAndSortingRepository<SyncLog, Long> getPagingAndSortingRepositoryDao() {
		return this.syncLogDao;
	}

	@Override
	public JpaSpecificationExecutor<SyncLog> getJpaSpecificationExecutorDao() {
		return this.syncLogDao;
	}

	public void addSyncLog(String logType, String logContent, String userName) {
		SyncLog entity = new SyncLog();
		entity.setLogType(logType);
		entity.setLogContent(logContent);
		entity.setUserName(userName);
		entity.setCreateTime(new Date());
		this.save(entity);

	}
}
