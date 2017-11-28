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

import com.vsc.business.gerd.entity.work.CardType;
import com.vsc.business.gerd.entity.work.Shoufei;
import com.vsc.business.gerd.repository.work.CardTypeDao;
import com.vsc.business.gerd.repository.work.ParkingLotDao;
import com.vsc.business.gerd.repository.work.ShoufeiDao;
import com.vsc.modules.entity.MapBean;
import com.vsc.modules.service.BaseService;
import com.vsc.util.CoreUtils;

/**
 * 
 * @author Administrator
 * 
 */
@Service
@Transactional
public class ShoufeiService extends BaseService<Shoufei> {
	private static Logger logger = LoggerFactory
			.getLogger(ShoufeiService.class);

	@Autowired
	private ShoufeiDao shoufeiDao;

	@Autowired
	private CardTypeDao cardTypeDao;
	@Autowired
	private ParkingLotDao parkingLotDao;

	@Override
	public PagingAndSortingRepository<Shoufei, Long> getPagingAndSortingRepositoryDao() {
		return this.shoufeiDao;
	}

	@Override
	public JpaSpecificationExecutor<Shoufei> getJpaSpecificationExecutorDao() {
		return this.shoufeiDao;
	}

	public void synchronizationShoufei() {
		Date now = CoreUtils.nowtime();
		List<MapBean<String, Object>> newvl = this.ibatisQueryDao
				.findAll("find.t_shoufei.new");
		for (MapBean<String, Object> mapBean : newvl) {
			Shoufei entity = new Shoufei();
			entity.setCardType(this.cardTypeDao.findOne(MapUtils.getLong(
					mapBean, "type_id")));
			entity.setParkingLot(this.parkingLotDao.findOne(MapUtils.getLong(
					mapBean, "cid")));
			entity.setDayTime(MapUtils.getDouble(mapBean, "day_time"));
			entity.setNightTime(MapUtils.getDouble(mapBean, "night_time"));
			entity.setDayHourMoney(MapUtils
					.getDouble(mapBean, "day_hour_money"));
			entity.setNightHourMoney(MapUtils.getDouble(mapBean,
					"night_hour_money"));
			entity.setDayMaxMoney(MapUtils.getDouble(mapBean, "day_max_money"));
			entity.setNightMaxMoney(MapUtils.getDouble(mapBean,
					"night_max_money"));
			entity.setEverydayMianfeiTime(MapUtils.getDouble(mapBean,
					"everyday_mianfei_time"));
			entity.setLasttime(MapUtils.getLong(mapBean, "lasttime") * 1000);
			entity.setCshoufeiId(MapUtils.getLong(mapBean, "id"));

			this.shoufeiDao.save(entity);
		}
		List<MapBean<String, Object>> updatevl = this.ibatisQueryDao
				.findAll("find.t_shoufei.update");
		for (MapBean<String, Object> mapBean : updatevl) {
			Shoufei entity = this.shoufeiDao.findOne(MapUtils.getLong(mapBean,
					"NEWID"));
			entity.setCardType(this.cardTypeDao.findOne(MapUtils.getLong(
					mapBean, "type_id")));
			entity.setParkingLot(this.parkingLotDao.findOne(MapUtils.getLong(
					mapBean, "cid")));
			entity.setDayTime(MapUtils.getDouble(mapBean, "day_time"));
			entity.setNightTime(MapUtils.getDouble(mapBean, "night_time"));
			entity.setDayHourMoney(MapUtils
					.getDouble(mapBean, "day_hour_money"));
			entity.setNightHourMoney(MapUtils.getDouble(mapBean,
					"night_hour_money"));
			entity.setDayMaxMoney(MapUtils.getDouble(mapBean, "day_max_money"));
			entity.setNightMaxMoney(MapUtils.getDouble(mapBean,
					"night_max_money"));
			entity.setEverydayMianfeiTime(MapUtils.getDouble(mapBean,
					"everyday_mianfei_time"));
			entity.setLasttime(now.getTime());

			this.shoufeiDao.save(entity);
		}
	}

}
