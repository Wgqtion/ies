package com.vsc.business.gerd.service.work;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vsc.business.gerd.entity.work.UserOrder;
import com.vsc.business.gerd.repository.work.UserOrderDao;
import com.vsc.modules.service.BaseService;

/**
 * 
 * @author XiangXiaoLin
 *
 */
@Service
@Transactional
public class UserOrderService extends BaseService<UserOrder> {

	@Autowired
	private UserOrderDao userOrderDao;

	@Override
	public PagingAndSortingRepository<UserOrder, Long> getPagingAndSortingRepositoryDao() {
		return this.userOrderDao;
	}

	@Override
	public JpaSpecificationExecutor<UserOrder> getJpaSpecificationExecutorDao() {
		return this.userOrderDao;
	}
	

}
