package com.vsc.business.gerd.service.work;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vsc.business.gerd.entity.work.AnnouncementSend;
import com.vsc.business.gerd.repository.work.AnnouncementSendDao;
import com.vsc.modules.service.BaseService;

/**
 * 
 * @author jerry
 *
 */
@Service
@Transactional
public class AnnouncementSendService extends BaseService<AnnouncementSend> {
	private static Logger logger = LoggerFactory.getLogger(AnnouncementSendService.class);

	@Autowired
	private AnnouncementSendDao announcementSendDao;

	@Override
	public PagingAndSortingRepository<AnnouncementSend, Long> getPagingAndSortingRepositoryDao() {
		return this.announcementSendDao;
	}

	@Override
	public JpaSpecificationExecutor<AnnouncementSend> getJpaSpecificationExecutorDao() {
		return this.announcementSendDao;
	}

}
