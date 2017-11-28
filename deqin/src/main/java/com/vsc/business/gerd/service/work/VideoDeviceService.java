package com.vsc.business.gerd.service.work;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vsc.business.gerd.entity.work.VideoDevice;
import com.vsc.business.gerd.repository.work.VideoDeviceDao;
import com.vsc.modules.service.BaseService;

/**
 * 
 * @author Administrator
 * 
 */
@Service
@Transactional
public class VideoDeviceService extends BaseService<VideoDevice> {
	private static Logger logger = LoggerFactory
			.getLogger(VideoDeviceService.class);

	@Autowired
	private VideoDeviceDao videoDeviceDao;

	@Override
	public PagingAndSortingRepository<VideoDevice, Long> getPagingAndSortingRepositoryDao() {
		return this.videoDeviceDao;
	}

	@Override
	public JpaSpecificationExecutor<VideoDevice> getJpaSpecificationExecutorDao() {
		return this.videoDeviceDao;
	}

}
