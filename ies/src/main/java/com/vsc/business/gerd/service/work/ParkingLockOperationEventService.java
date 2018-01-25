package com.vsc.business.gerd.service.work;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.vsc.business.gerd.entity.work.ParkingLockOperationEvent;
import com.vsc.business.gerd.repository.work.ParkingLockOperationEventDao;
import com.vsc.modules.service.BaseService;

/**
 * 
 * @author jerry
 *
 */
@Service
@Transactional
public class ParkingLockOperationEventService extends BaseService<ParkingLockOperationEvent>{
	private static Logger logger = LoggerFactory.getLogger(ParkingLockOperationEventService.class);
  
	@Autowired
	private ParkingLockOperationEventDao parkingLockOperationEventDao;

    
  	
	@Override
	public PagingAndSortingRepository<ParkingLockOperationEvent, Long> getPagingAndSortingRepositoryDao() {
		return this.parkingLockOperationEventDao;
	}

	@Override
	public JpaSpecificationExecutor<ParkingLockOperationEvent> getJpaSpecificationExecutorDao() {
		return this.parkingLockOperationEventDao;
	}
	
}
