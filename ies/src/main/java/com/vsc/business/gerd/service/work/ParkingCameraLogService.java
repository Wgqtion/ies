package com.vsc.business.gerd.service.work;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vsc.business.gerd.entity.work.ParkingCamera;
import com.vsc.business.gerd.entity.work.ParkingCameraLog;
import com.vsc.business.gerd.repository.work.ParkingCameraLogDao;
import com.vsc.modules.service.BaseService;
import com.vsc.util.CoreUtils;
import com.vsc.util.Log4jUtils;

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
		entity.setCreateDate(new Date());
		ParkingCamera parkingCamera = parkingCameraService.getByParkingCameraLog(entity);
		boolean isSave = true;
		if (parkingCamera != null) {
			entity.setParkingCameraCode(parkingCamera.getCode());
			ParkingCameraLog log = getMaxBy(parkingCamera.getCode());
			if (log != null && CoreUtils.compare_date(log.getInDate(), entity.getInDate()) == 0
					&& log.getStatus().equals(entity.getStatus())) {
				isSave = false;
			}
			parkingCamera.setStatus(entity.getStatus());
			parkingCamera.setPlateNo(entity.getPlateNo());
			parkingCamera.setLogUpdateTime(entity.getCreateDate());
			parkingCameraService.update(parkingCamera);
		} else {
			Log4jUtils.mainInfo.info("全视频上报日志为空" + entity.toString());
		}
		if (isSave) {
			return super.save(entity);
		}
		return null;
	}

	/**
	 * 根据相机编码查询最新数据
	 * 
	 * @param entity
	 * @return
	 */
	public ParkingCameraLog getMaxBy(String parkingCameraCode) {
		Map<String, Object> searchParams = new HashMap<String, Object>();
		searchParams.put("EQ_parkingCameraCode", parkingCameraCode);
		PageRequest pageRequest = CoreUtils.buildPageRequest(1, 1, "createDate", "DESC");
		Page<ParkingCameraLog> page = null;
		try {
			page = super.findPage(searchParams, pageRequest);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (page.getContent().size() > 0) {
			return page.getContent().get(0);
		}
		return null;
	}
}
