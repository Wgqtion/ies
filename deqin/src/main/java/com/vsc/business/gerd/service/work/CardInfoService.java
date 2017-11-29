package com.vsc.business.gerd.service.work;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vsc.business.gerd.entity.work.CardInfo;
import com.vsc.business.gerd.repository.work.CardInfoDao;
import com.vsc.modules.service.BaseService;

/**
 * 
 * @author Administrator
 * 
 */
@Service
@Transactional
public class CardInfoService extends BaseService<CardInfo> {
	private static Logger logger = LoggerFactory
			.getLogger(CardInfoService.class);

	@Autowired
	private CardInfoDao cardInfoDao;

	@Override
	public PagingAndSortingRepository<CardInfo, Long> getPagingAndSortingRepositoryDao() {
		return this.cardInfoDao;
	}

	@Override
	public JpaSpecificationExecutor<CardInfo> getJpaSpecificationExecutorDao() {
		return this.cardInfoDao;
	}

}
