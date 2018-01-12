package com.vsc.business.gerd.service.work;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vsc.business.gerd.entity.work.Invitation;
import com.vsc.business.gerd.repository.work.InvitationDao;
import com.vsc.modules.service.BaseService;

/**
 * 
 * @author XiangXiaoLin
 *
 */
@Service
@Transactional
public class InvitationService extends BaseService<Invitation> {
	private static Logger logger = LoggerFactory
			.getLogger(InvitationService.class);

	@Autowired
	private InvitationDao invitationDao;

	@Override
	public PagingAndSortingRepository<Invitation, Long> getPagingAndSortingRepositoryDao() {
		return this.invitationDao;
	}

	@Override
	public JpaSpecificationExecutor<Invitation> getJpaSpecificationExecutorDao() {
		return this.invitationDao;
	}

}
