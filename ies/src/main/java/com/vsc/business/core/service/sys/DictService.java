package com.vsc.business.core.service.sys;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vsc.business.core.entity.sys.Dict;
import com.vsc.business.core.repository.sys.DictDao;
import com.vsc.modules.service.BaseService;

/**
 * 
 * @author 陈婷
 *
 */
@Service
@Transactional
public class DictService extends BaseService<Dict> {
	private static Logger logger = LoggerFactory.getLogger(DictService.class);

	@Autowired
	private DictDao dictDao;
	
	@Override
	public PagingAndSortingRepository<Dict, Long> getPagingAndSortingRepositoryDao() {
		return this.dictDao;
	}

	@Override
	public JpaSpecificationExecutor<Dict> getJpaSpecificationExecutorDao() {
		return this.dictDao;
	}

	/**
	 * 查询distinct后所有的父类型
	 * @return 父类型的List
	 */
	public List<String> findAllType(){
		List<String> lists = this.dictDao.findAllType();
		return lists;
	}
	
	
}
