package com.vsc.business.gerd.service.work;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.vsc.business.core.entity.security.User;
import com.vsc.business.core.service.security.UserService;
import com.vsc.business.gerd.entity.work.Announcement;
import com.vsc.business.gerd.entity.work.AnnouncementSend;
import com.vsc.business.gerd.repository.work.AnnouncementDao;
import com.vsc.business.gerd.repository.work.AnnouncementSendDao;
import com.vsc.modules.service.BaseService;
import com.vsc.util.CoreUtils;

/**
 * 
 * @author jerry
 *
 */
@Service
@Transactional
public class AnnouncementService extends BaseService<Announcement> {
	private static Logger logger = LoggerFactory.getLogger(AnnouncementService.class);

	@Autowired
	private AnnouncementDao announcementDao;

	@Autowired
	private AnnouncementSendDao announcementSendDao;

	@Autowired
	private UserService userService;

	@Override
	public PagingAndSortingRepository<Announcement, Long> getPagingAndSortingRepositoryDao() {
		return this.announcementDao;
	}

	@Override
	public JpaSpecificationExecutor<Announcement> getJpaSpecificationExecutorDao() {
		return this.announcementDao;
	}

	public void destroyByIds(Long[] ids) {
		if (ArrayUtils.isNotEmpty(ids)) {
			for (int i = 0; i < ids.length; i++) {
				Announcement value = this.getObjectById(ids[i]);
				value.setState(0);
				this.save(value);
			}
		}
	}

	public Announcement save(Announcement entity, Long[] uids, Long adminUserId) {
		this.save(entity);
		
		return entity;
	}

	public Announcement save(Announcement entity, Long[] uids) {
		return save(entity, uids, null);
	}

}
