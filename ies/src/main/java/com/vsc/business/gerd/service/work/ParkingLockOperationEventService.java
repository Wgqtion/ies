package com.vsc.business.gerd.service.work;

import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vsc.business.core.entity.security.User;
import com.vsc.business.gerd.entity.work.ParkingLock;
import com.vsc.business.gerd.entity.work.ParkingLockOperationEvent;
import com.vsc.business.gerd.repository.work.ParkingLockOperationEventDao;
import com.vsc.modules.service.BaseService;
import com.vsc.modules.shiro.ShiroUserUtils;
import com.vsc.util.CoreUtils;

/**
 * 
 * @author XiangXiaoLin
 *
 */
@Service
@Transactional
public class ParkingLockOperationEventService extends BaseService<ParkingLockOperationEvent>{
	private static Logger logger = LoggerFactory.getLogger(ParkingLockOperationEventService.class);
  
	@Autowired
	private ParkingLockOperationEventDao parkingLockOperationEventDao;


	@Autowired
    private ParkingLockService parkingLockService;
	
	@Override
	public PagingAndSortingRepository<ParkingLockOperationEvent, Long> getPagingAndSortingRepositoryDao() {
		return this.parkingLockOperationEventDao;
	}

	@Override
	public JpaSpecificationExecutor<ParkingLockOperationEvent> getJpaSpecificationExecutorDao() {
		return this.parkingLockOperationEventDao;
	}
	
	public ParkingLock getParkingLockByCode(String ipAddress,String lockNum){
		return parkingLockService.getByCode(ipAddress, lockNum);
	}
	
	/**
	 * 根据条件查询，未删除，like 用户公司code%
	 */
	@Override
	public Page<ParkingLockOperationEvent> findPage(Map<String, Object> filterParams, PageRequest pageRequest) {
		User user=ShiroUserUtils.GetCurrentUser();
		filterParams.put("RLIKE_parkingLock.parkingGarage.parkingLotArea.parkingLot.companyCode", user.getCompany().getCode());
		return super.findPage(filterParams, pageRequest);
	}
	
	public ParkingLockOperationEvent save(ParkingLockOperationEvent entity) {
		User user=ShiroUserUtils.GetCurrentUser();
		Date now=CoreUtils.nowtime();
		if(entity.getId()==null){
			entity.setCreateTime(now);
		}
		if(user!=null){
			if(entity.getId()==null){
				entity.setCreateTime(now);
				entity.setUser(user);	
			}
			
		}
		return this.parkingLockOperationEventDao.save(entity);
	}
}
