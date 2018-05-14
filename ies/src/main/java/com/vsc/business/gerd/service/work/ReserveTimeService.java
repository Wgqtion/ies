package com.vsc.business.gerd.service.work;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.utils.Collections3;

import com.vsc.business.core.entity.security.User;
import com.vsc.business.gerd.entity.work.ReserveTime;
import com.vsc.business.gerd.repository.work.ReserveTimeDao;
import com.vsc.modules.service.BaseService;
import com.vsc.modules.shiro.ShiroUserUtils;
import com.vsc.util.CoreUtils;

/**
 * 预约时间逻辑操作
 * @author XiangXiaoLin
 *
 */
@Service
@Transactional
public class ReserveTimeService extends BaseService<ReserveTime> {

	@Autowired
	private ReserveTimeDao reserveTimeDao;

	@Override
	public PagingAndSortingRepository<ReserveTime, Long> getPagingAndSortingRepositoryDao() {
		return this.reserveTimeDao;
	}

	@Override
	public JpaSpecificationExecutor<ReserveTime> getJpaSpecificationExecutorDao() {
		return this.reserveTimeDao;
	}
	/**
	 * 是否在预约时间
	 */
	public boolean isCanReserveTime(ReserveTime reserveTime){
		Map<String, Object> searchParams = new HashMap<String, Object>();
		searchParams.put("EQ_parkingLotCode",reserveTime.getParkingLotCode()); 
		searchParams.put("EQ_isDelete", 0); 
		searchParams.put("EQ_week",0);
		searchParams.put("LTE_startTime",reserveTime.getStartTime());
		searchParams.put("GTE_endTime",reserveTime.getStartTime());
		try {
			List<ReserveTime> list=super.findList(searchParams);
			if(list!=null&&list.size()>0){
				return true;
			}
			searchParams.put("EQ_week",CoreUtils.getWeek(CoreUtils.formatw.parse(reserveTime.getStartTime())));
			searchParams.put("LTE_startTime",reserveTime.getStartTime().substring(11, 16));
			searchParams.put("GTE_endTime",reserveTime.getStartTime().substring(11, 16));
			list=super.findList(searchParams);
			if(list!=null&&list.size()>0){
				return true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 根据条件查询，未删除，like 用户公司code%
	 * @throws Exception 
	 */
	@Override
	public Page<ReserveTime> findPage(Map<String, Object> filterParams, PageRequest pageRequest) throws Exception {
		User user=ShiroUserUtils.GetCurrentUser();
		filterParams.put("RLIKE_parkingLot.companyCode", user.getCompany().getCode());
		filterParams.put("EQ_isDelete", 0); 
		return super.findPage(filterParams, pageRequest);
	}
	
	public ReserveTime save(ReserveTime entity) throws Exception {
		
		User user=ShiroUserUtils.GetCurrentUser();
		Date now=CoreUtils.nowtime();
		if(entity.getId()==null){
			entity.setCreateDate(now);
			entity.setCreateUser(user);	
		}
		entity.setUpdateUser(user);
		entity.setUpdateDate(now);

		return super.save(entity);
	}

	public void deleteUpdateById(Long id) throws Exception {
		ReserveTime entity=getObjectById(id);
		entity.setIsDelete(true);
		save(entity);
	}

	public void deleteUpdateByIds(Long[] ids) throws Exception {
		if (ArrayUtils.isNotEmpty(ids)) {
			for (int i = 0; i < ids.length; i++) {
				deleteUpdateById(ids[i]);
			}
		}
	}

	public void deleteUpdateByIds(List<Long> ids) throws Exception {
		if (Collections3.isNotEmpty(ids)) {
			for (Long id : ids) {
				if (id != null) {
					deleteUpdateById(id);
				}
			}
		}
	}

	
	
}
