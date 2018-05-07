package com.vsc.business.gerd.service.work;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vsc.business.gerd.entity.work.ParkingVideo;
import com.vsc.business.gerd.repository.work.ParkingVideoDao;
import com.vsc.modules.service.BaseService;

/**
 * 
 * @author XiangXiaoLin
 *
 */
@Service
@Transactional
public class ParkingVideoService extends BaseService<ParkingVideo> {

	@Autowired
	private ParkingVideoDao parkingVideoDao;

	@Override
	public PagingAndSortingRepository<ParkingVideo, Long> getPagingAndSortingRepositoryDao() {
		return this.parkingVideoDao;
	}

	@Override
	public JpaSpecificationExecutor<ParkingVideo> getJpaSpecificationExecutorDao() {
		return this.parkingVideoDao;
	}

}
