package com.vsc.business.gerd.service.work;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vsc.business.gerd.entity.work.SystemNotice;
import com.vsc.business.gerd.repository.work.SystemNoticeDao;
import com.vsc.modules.service.BaseService;

/**
 * 
 * @author jerry
 *
 */
@Service
@Transactional
public class SystemNoticeService extends BaseService<SystemNotice> {
	private static Logger logger = LoggerFactory.getLogger(SystemNoticeService.class);

	@Autowired
	private SystemNoticeDao systemNoticeDao;

	@Override
	public PagingAndSortingRepository<SystemNotice, Long> getPagingAndSortingRepositoryDao() {
		return this.systemNoticeDao;
	}

	@Override
	public JpaSpecificationExecutor<SystemNotice> getJpaSpecificationExecutorDao() {
		return this.systemNoticeDao;
	}

}
