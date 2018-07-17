package com.vsc.business.gerd.service.work;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.vsc.business.gerd.entity.work.ParkingCamera;
import com.vsc.business.gerd.entity.work.ParkingCameraLog;
import com.vsc.business.gerd.repository.work.ParkingCameraLogDao;
import com.vsc.constants.Constants;
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

	/**
	 * 保存相关信息及文件
	 */
	public ParkingCameraLog save(ParkingCameraLog entity, MultipartFile picture) throws Exception {
		entity.setCreateDate(new Date());
		ParkingCamera parkingCamera = parkingCameraService.getByParkingCameraLog(entity);
		boolean isSave = true;
		if (parkingCamera != null) {
			entity.setParkingCameraCode(parkingCamera.getCode());
			ParkingCameraLog log = getMaxBy(parkingCamera.getCode());
			if (log != null &&log.getInDate()!=null&&entity.getInDate()!=null&& CoreUtils.compare_date(log.getInDate(), entity.getInDate()) == 0
					&& log.getStatus().equals(entity.getStatus())) {
				isSave = false;
			}
			parkingCamera.setStatus(entity.getStatus());
			if (entity.getStatus().intValue() == 0) {
				parkingCamera.setPlateNo(null);
			} else {
				parkingCamera.setPlateNo(entity.getPlateNo());
				entity.setPicturePath(Constants.UPLOAD_CAMERA_FOLDER + CoreUtils.getStoragePath() + Constants.SPT
						+ CoreUtils.getFileKey()+"."+StringUtils.lowerCase(StringUtils.substringAfterLast(picture.getOriginalFilename(), ".")));
				CoreUtils.saveFile(picture.getInputStream(),
						entity.getPicturePath());
			}
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
