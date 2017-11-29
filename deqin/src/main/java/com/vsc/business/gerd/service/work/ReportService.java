package com.vsc.business.gerd.service.work;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vsc.business.gerd.repository.work.ReportDao;
import com.vsc.modules.service.BaseService;

/**
 * 
 * @author jerry
 *
 */
@Service
@Transactional
public class ReportService extends BaseService {
	private static Logger logger = LoggerFactory.getLogger(ReportService.class);

	@Autowired
	private ReportDao reportDao;

	@Override
	public PagingAndSortingRepository getPagingAndSortingRepositoryDao() {
		return this.reportDao;
	}

	@Override
	public JpaSpecificationExecutor getJpaSpecificationExecutorDao() {
		return this.reportDao;
	}
	
	public List getList(){
		//定义SQL   
        String sql = "SELECT * FROM t_user";   
        
		return null;
	}

}
