package com.vsc.business.gerd.service.work;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vsc.business.gerd.entity.work.ParkingGarage;
import com.vsc.business.gerd.repository.work.ParkingGarageDao;
import com.vsc.modules.service.BaseService;

/**
 * 
 * @author jerry
 * 
 */
@Service
@Transactional
public class ParkingGarageService extends BaseService<ParkingGarage> {
	private static Logger logger = LoggerFactory
			.getLogger(ParkingGarageService.class);

	@Autowired
	private ParkingGarageDao parkingGarageDao;

	@Override
	public PagingAndSortingRepository<ParkingGarage, Long> getPagingAndSortingRepositoryDao() {
		return this.parkingGarageDao;
	}

	@Override
	public JpaSpecificationExecutor<ParkingGarage> getJpaSpecificationExecutorDao() {
		return this.parkingGarageDao;
	}
}
