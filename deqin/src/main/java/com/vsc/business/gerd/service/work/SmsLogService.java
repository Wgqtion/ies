package com.vsc.business.gerd.service.work;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vsc.business.gerd.entity.work.SmsLog;
import com.vsc.business.gerd.repository.work.SmsLogDao;
import com.vsc.modules.service.BaseService;

/**
 * 
 * @author jerry
 *
 */
@Service
@Transactional
public class SmsLogService extends BaseService<SmsLog> {
	private static Logger logger = LoggerFactory.getLogger(SmsLogService.class);

	@Autowired
	private SmsLogDao smsLogDao;

	@Override
	public PagingAndSortingRepository<SmsLog, Long> getPagingAndSortingRepositoryDao() {
		return this.smsLogDao;
	}

	@Override
	public JpaSpecificationExecutor<SmsLog> getJpaSpecificationExecutorDao() {
		return this.smsLogDao;
	}

}
