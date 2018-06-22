package com.vsc.business.gerd.service.work;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vsc.business.gerd.entity.work.ParkingCamera;
import com.vsc.business.gerd.entity.work.ParkingCameraLog;
import com.vsc.business.gerd.repository.work.ParkingCameraLogDao;
import com.vsc.modules.service.BaseService;

/**
 * 
 * @author XiangXiaoLin
 *
 */
@Service
@Transactional
public class ParkingCameraLogService extends BaseService<ParkingCameraLog> {

	@Autowired
	private ParkingCameraLogDao parkingCameraLogDao;
	
	@Autowired
	private ParkingCameraService parkingCameraService;

	@Override
	public PagingAndSortingRepository<ParkingCameraLog, Long> getPagingAndSortingRepositoryDao() {
		return this.parkingCameraLogDao;
	}

	@Override
	public JpaSpecificationExecutor<ParkingCameraLog> getJpaSpecificationExecutorDao() {
		return this.parkingCameraLogDao;
	}

	@Override
	public ParkingCameraLog save(ParkingCameraLog entity) throws Exception {
		entity.setCreateTime(new Date());
		ParkingCamera parkingCamera=parkingCameraService.getByParkingCameraLog(entity);
		if(parkingCamera!=null){
			entity.setParkingCameraCode(parkingCamera.getCode());	
		}	
		return super.save(entity);
	}
	

}
