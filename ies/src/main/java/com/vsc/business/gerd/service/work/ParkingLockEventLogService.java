package com.vsc.business.gerd.service.work;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.vsc.business.gerd.entity.work.ParkingLockEventLog;
import com.vsc.business.gerd.repository.work.ParkingLockEventLogDao;
import com.vsc.modules.service.BaseService;

/**
 * 
 * @author jerry
 *
 */
@Service
@Transactional
public class ParkingLockEventLogService extends BaseService<ParkingLockEventLog>{
	private static Logger logger = LoggerFactory.getLogger(ParkingLockEventLogService.class);
  
	@Autowired
	private ParkingLockEventLogDao parkingLockEventLogDao;

    
  	
	@Override
	public PagingAndSortingRepository<ParkingLockEventLog, Long> getPagingAndSortingRepositoryDao() {
		return this.parkingLockEventLogDao;
	}

	@Override
	public JpaSpecificationExecutor<ParkingLockEventLog> getJpaSpecificationExecutorDao() {
		return this.parkingLockEventLogDao;
	}
	
}