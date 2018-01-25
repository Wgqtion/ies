package com.vsc.business.gerd.service.work;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vsc.business.gerd.entity.work.Org;
import com.vsc.business.gerd.repository.work.OrgDao;
import com.vsc.modules.service.BaseService;

/**
 * 
 * @author XiangXiaoLin
 *
 */
@Service
@Transactional
public class OrgService extends BaseService<Org> {
	private static Logger logger = LoggerFactory
			.getLogger(OrgService.class);

	@Autowired
	private OrgDao orgDao;

	@Override
	public PagingAndSortingRepository<Org, Long> getPagingAndSortingRepositoryDao() {
		return this.orgDao;
	}

	@Override
	public JpaSpecificationExecutor<Org> getJpaSpecificationExecutorDao() {
		return this.orgDao;
	}

}
