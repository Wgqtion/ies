package com.vsc.business.gerd.service.work;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.MapUtils;
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
import com.vsc.modules.entity.MapBean;
import com.vsc.modules.ibatis.IbatisQueryDao;
import com.vsc.modules.service.BaseService;
import com.vsc.util.CoreUtils;

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
	
	public void synchronizationParkingLot(){
	 
		List<MapBean<String, Object>> newvl = this.ibatisQueryDao.findAll("find.c_campus.new");
		Date now=CoreUtils.nowtime();
		for (MapBean<String, Object> mapBean : newvl) {
			ParkingLot entity=new ParkingLot();			
			entity.setName(MapUtils.getString(mapBean, "c_name"));
			entity.setCreateTime(now);
			entity.setLasttime(MapUtils.getInteger(mapBean, "lasttime"));
			entity.setCanGetCard(MapUtils.getBoolean(mapBean, "can_get_card"));	
			entity.setCampusId(MapUtils.getInteger(mapBean, "id"));
			this.parkingLotDao.save(entity);
		}
		List<MapBean<String, Object>> updatevl = this.ibatisQueryDao.findAll("find.c_campus.update");
		for (MapBean<String, Object> mapBean : updatevl) {
			ParkingLot entity=this.parkingLotDao.findOne(MapUtils.getLong(mapBean, "PARKING_LOT_ID"))	;		
			entity.setName(MapUtils.getString(mapBean, "c_name"));		 
			entity.setLasttime(MapUtils.getInteger(mapBean, "lasttime"));
			entity.setCanGetCard(MapUtils.getBoolean(mapBean, "can_get_card"));	
			this.parkingLotDao.save(entity);
		}
	}
	
	/**
	 * 更新校区可用车位数量
	 */
	public void syncParkingLotCarNumber(){
		this.ibatisQueryDao.update("updateParkinglotCarNumber.update", null);
	}
	
	/**
	 * 更新校区片区可用车位数量
	 */
	public void syncParkingLotAreaCarNumber(){
		this.ibatisQueryDao.update("updateParkinglotAreaCarNumber.update", null);
	}
}
