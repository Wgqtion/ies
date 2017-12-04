package com.vsc.business.gerd.service.work;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vsc.business.gerd.entity.work.AccessApply;
import com.vsc.business.gerd.repository.work.AccessApplyDao;
import com.vsc.modules.service.BaseService;

/**
 * 
 * @author ThinkPad
 *
 */
@Service
@Transactional
public class AccessApplyService extends BaseService<AccessApply>{
	private static Logger logger = LoggerFactory.getLogger(AccessApplyService.class);
  
	@Autowired
	private AccessApplyDao accessApplyDao;

    
  	
	@Override
	public PagingAndSortingRepository<AccessApply, Long> getPagingAndSortingRepositoryDao() {
		return this.accessApplyDao;
	}

	@Override
	public JpaSpecificationExecutor<AccessApply> getJpaSpecificationExecutorDao() {
		return this.accessApplyDao;
	}
	
	

}
