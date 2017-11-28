package com.vsc.business.gerd.service.work;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vsc.business.gerd.entity.work.YudingSetting;
import com.vsc.business.gerd.repository.work.YudingSettingDao;
import com.vsc.modules.service.BaseService;

/**
 * 
 * @author jerry
 *
 */
@Service
@Transactional
public class YudingSettingService extends BaseService<YudingSetting> {
	private static Logger logger = LoggerFactory.getLogger(YudingSettingService.class);

	@Autowired
	private YudingSettingDao yudingSettingDao;

	@Override
	public PagingAndSortingRepository<YudingSetting, Long> getPagingAndSortingRepositoryDao() {
		return this.yudingSettingDao;
	}

	@Override
	public JpaSpecificationExecutor<YudingSetting> getJpaSpecificationExecutorDao() {
		return this.yudingSettingDao;
	}

}
