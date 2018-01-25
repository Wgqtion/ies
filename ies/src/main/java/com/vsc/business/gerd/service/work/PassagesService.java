package com.vsc.business.gerd.service.work;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.vsc.business.gerd.entity.work.Passages;
import com.vsc.business.gerd.repository.work.PassagesDao;
import com.vsc.modules.service.BaseService;

/**
 * 
 * @author ThinkPad
 *
 */
@Service
@Transactional
public class PassagesService extends BaseService<Passages>{
	private static Logger logger = LoggerFactory.getLogger(PassagesService.class);
  
	@Autowired
	private PassagesDao passagesDao;

    
  	
	@Override
	public PagingAndSortingRepository<Passages, Long> getPagingAndSortingRepositoryDao() {
		return this.passagesDao;
	}

	@Override
	public JpaSpecificationExecutor<Passages> getJpaSpecificationExecutorDao() {
		return this.passagesDao;
	}
	
}
