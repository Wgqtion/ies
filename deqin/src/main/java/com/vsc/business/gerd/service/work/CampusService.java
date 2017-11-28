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

import com.vsc.business.gerd.entity.work.Campus;
import com.vsc.business.gerd.entity.work.ParkingLot;
import com.vsc.business.gerd.repository.work.CampusDao;
import com.vsc.business.gerd.repository.work.ParkingLotDao;
import com.vsc.modules.entity.MapBean;
import com.vsc.modules.service.BaseService;
import com.vsc.util.CoreUtils;

/**
 * 
 * @author jerry
 *
 */
@Service
@Transactional
public class CampusService extends BaseService<Campus> {
	private static Logger logger = LoggerFactory.getLogger(CampusService.class);

	@Autowired
	private CampusDao campusDao;
	@Autowired
	private ParkingLotDao parkingLotDao;

	@Override
	public PagingAndSortingRepository<Campus, Long> getPagingAndSortingRepositoryDao() {
		return this.campusDao;
	}

	@Override
	public JpaSpecificationExecutor<Campus> getJpaSpecificationExecutorDao() {
		return this.campusDao;
	}
	
	public void synchronizationCampus(){
		List<MapBean<String, Object>> newvl = this.ibatisQueryDao.findAll("find.c_campus.door.new");
		Date now=CoreUtils.nowtime();
		for (MapBean<String, Object> mapBean : newvl) {
			Campus entity=new Campus();			
			entity.setName(MapUtils.getString(mapBean, "c_name"));
			entity.setCreateTime(now);
			entity.setLasttime(MapUtils.getInteger(mapBean, "lasttime"));
			 
			entity.setParkingLot(parkingLotDao.findOne(MapUtils.getLong(mapBean, "PARKING_LOT_ID")));
			entity.setCampusId(MapUtils.getLong(mapBean, "id"));
			this.campusDao.save(entity);
		}
		List<MapBean<String, Object>> updatevl = this.ibatisQueryDao.findAll("find.c_campus.door.update");
		for (MapBean<String, Object> mapBean : updatevl) {
			Campus entity=this.campusDao.findOne(MapUtils.getLong(mapBean, "DOOR_ID"))	;		
			entity.setName(MapUtils.getString(mapBean, "c_name"));		 
			entity.setLasttime(MapUtils.getInteger(mapBean, "lasttime"));
			entity.setParkingLot(parkingLotDao.findOne(MapUtils.getLong(mapBean, "PARKING_LOT_ID")));
			this.campusDao.save(entity);
		}
	}

}
