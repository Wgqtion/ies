package com.vsc.business.gerd.service.work;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vsc.business.gerd.entity.work.ParkingGarageCarnoLog;
import com.vsc.business.gerd.repository.work.ParkingGarageCarnoLogDao;
import com.vsc.modules.service.BaseService;

/**
 * 
 * @author jerry
 *
 */
@Service
@Transactional
public class ParkingGarageCarnoLogService extends BaseService<ParkingGarageCarnoLog> {
	private static Logger logger = LoggerFactory.getLogger(ParkingGarageCarnoLogService.class);

	@Autowired
	private ParkingGarageCarnoLogDao parkingGarageCarnoLogDao;

	@Override
	public PagingAndSortingRepository<ParkingGarageCarnoLog, Long> getPagingAndSortingRepositoryDao() {
		return this.parkingGarageCarnoLogDao;
	}

	@Override
	public JpaSpecificationExecutor<ParkingGarageCarnoLog> getJpaSpecificationExecutorDao() {
		return this.parkingGarageCarnoLogDao;
	}

}
