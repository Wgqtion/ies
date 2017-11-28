package com.vsc.business.gerd.service.work;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vsc.business.core.repository.sys.upload.AttachDao;
import com.vsc.business.gerd.entity.work.ParkingLot;
import com.vsc.business.gerd.entity.work.ParkingLotArea;
import com.vsc.business.gerd.repository.work.ParkingLotAreaDao;
import com.vsc.modules.service.BaseService;

/**
 * 
 * @author jerry
 *
 */
@Service
@Transactional
public class ParkingLotAreaService extends BaseService<ParkingLotArea> {
	private static Logger logger = LoggerFactory.getLogger(ParkingLotAreaService.class);

	@Autowired
	private ParkingLotAreaDao parkingLotAreaDao;
	@Autowired
	private AttachDao attachDao;

	@Override
	public PagingAndSortingRepository<ParkingLotArea, Long> getPagingAndSortingRepositoryDao() {
		return this.parkingLotAreaDao;
	}

	@Override
	public JpaSpecificationExecutor<ParkingLotArea> getJpaSpecificationExecutorDao() {
		return this.parkingLotAreaDao;
	}
	
	public ParkingLotArea save(ParkingLotArea entity, Long photoAttachId) {

		if (photoAttachId != null) {
			entity.setPhotoAttach(attachDao.findOne(photoAttachId));
		}else{
			entity.setPhotoAttach(null);
		}

		entity.setFullIndexName(entity.getFullName());
		return this.parkingLotAreaDao.save(entity);
	}

}
