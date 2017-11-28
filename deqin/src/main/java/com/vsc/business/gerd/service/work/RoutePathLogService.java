package com.vsc.business.gerd.service.work;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vsc.business.gerd.entity.work.RoutePathLog;
import com.vsc.business.gerd.repository.work.RoutePathLogDao;
import com.vsc.modules.service.BaseService;

/**
 * 
 * @author Administrator
 * 
 */
@Service
@Transactional
public class RoutePathLogService extends BaseService<RoutePathLog> {
	private static Logger logger = LoggerFactory.getLogger(RoutePathLogService.class);

	@Autowired
	private RoutePathLogDao routePathLogDao;

	@Override
	public PagingAndSortingRepository<RoutePathLog, Long> getPagingAndSortingRepositoryDao() {
		return this.routePathLogDao;
	}

	@Override
	public JpaSpecificationExecutor<RoutePathLog> getJpaSpecificationExecutorDao() {
		return this.routePathLogDao;
	}

}
