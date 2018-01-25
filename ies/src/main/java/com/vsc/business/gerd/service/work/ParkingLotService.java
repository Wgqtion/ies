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
import com.vsc.business.gerd.repository.work.ParkingLotDao;
import com.vsc.modules.ibatis.IbatisQueryDao;
import com.vsc.modules.service.BaseService;

/**
 * 
 * @author jerry
 *
 */
@Service
@Transactional
public class ParkingLotService extends BaseService<ParkingLot> {
	private static Logger logger = LoggerFactory.getLogger(ParkingLotService.class);

	@Autowired
	private ParkingLotDao parkingLotDao;
	@Autowired
	private IbatisQueryDao ibatisQueryDao;
	@Autowired
	private AttachDao attachDao;

	@Override
	public PagingAndSortingRepository<ParkingLot, Long> getPagingAndSortingRepositoryDao() {
		return this.parkingLotDao;
	}

	@Override
	public JpaSpecificationExecutor<ParkingLot> getJpaSpecificationExecutorDao() {
		return this.parkingLotDao;
	}

	public ParkingLot save(ParkingLot entity, Long photoAttachId) {

		if (photoAttachId != null) {
			entity.setPhotoAttach(attachDao.findOne(photoAttachId));
		}else{
			entity.setPhotoAttach(null);
		}

		return this.parkingLotDao.save(entity);
	}
	
	
}
