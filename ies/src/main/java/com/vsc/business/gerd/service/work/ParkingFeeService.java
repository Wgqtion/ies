package com.vsc.business.gerd.service.work;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.vsc.business.gerd.entity.work.ParkingFee;
import com.vsc.business.gerd.entity.work.WxCore;
import com.vsc.business.gerd.repository.work.ParkingFeeDao;
import com.vsc.modules.service.BaseService;
import com.vsc.modules.shiro.ShiroUserUtils;
import com.vsc.util.CoreUtils;

/**
 * 收费设置逻辑操作
 * 
 * @author XiangXiaoLin
 *
 */
@Service
@Transactional
public class ParkingFeeService extends BaseService<ParkingFee> {

	@Autowired
	private ParkingFeeDao parkingFeeDao;

	@Override
	public PagingAndSortingRepository<ParkingFee, Long> getPagingAndSortingRepositoryDao() {
		return this.parkingFeeDao;
	}

	@Override
	public JpaSpecificationExecutor<ParkingFee> getJpaSpecificationExecutorDao() {
		return this.parkingFeeDao;
	}
	/**
	 * 根据type,week，查询开始时间结束时间段是否已存在
	 */
	public boolean isExistsTime(ParkingFee entity) throws Exception{
		boolean flag=false;
		Set<ParkingFee> list = new HashSet<ParkingFee>();
		Map<String, Object> searchParams = new HashMap<String, Object>();
		searchParams.put("EQ_parkingLotCode", entity.getParkingLotCode());
		searchParams.put("EQ_type", entity.getType());
		searchParams.put("EQ_week",entity.getWeek());
		searchParams.put("EQ_isDelete", 0);
		searchParams.put("LTE_startTime", entity.getStartTime());
		searchParams.put("GTE_endTime", entity.getStartTime());
		if(entity.getId()!=null){
			searchParams.put("NOTEQ_id", entity.getId());
		}
		list.addAll(super.findList(searchParams));
		searchParams.put("LTE_startTime", entity.getEndTime());
		searchParams.put("GTE_endTime", entity.getEndTime());
		list.addAll(super.findList(searchParams));
		searchParams.remove("GTE_endTime");
		searchParams.remove("LTE_startTime");
		searchParams.put("GTE_startTime",entity.getStartTime());
		searchParams.put("LTE_endTime", entity.getEndTime());
		list.addAll(super.findList(searchParams));
		if(list!=null&&list.size()>0){
			flag=true;
		}
		return flag;
	}

	/**
	 * 根据时间场区获取收费计算列表
	 */
	public BigDecimal calculateFee(WxCore wxCore) {
		BigDecimal totalFee=new BigDecimal(0);
		return totalFee;
//		Set<ParkingFee> list = new HashSet<ParkingFee>();
//		try {
//			Date startDate = wxCore.getStartTime();
//			Date endDate = wxCore.getEndTime();
//
//			Map<String, Object> searchParams = new HashMap<String, Object>();
//			
//			searchParams.put("EQ_parkingLotCode", wxCore.getParkingLock().getParkingGarage().getParkingLotCode());
//			searchParams.put("EQ_isDelete", 0);
//			searchParams.put("EQ_type", wxCore.getType());
//
//			/*
//			 *  2.查询所有week时间范围内的费用设置
//			 *  AND( 
//			 *  	(START_TIME<='00:12' AND END_TIME>='00:12')
//			 *  	 OR (START_TIME<='22:30' AND END_TIME>='22:30')
//			 *  	 OR (START_TIME>='00:12' AND END_TIME<='22:30')
//			 *  )
//			 */
//			searchParams.put("NOTEQ_week", 0);
//			String startTime=CoreUtils.formath.format(startDate);
//			String endTime=CoreUtils.formath.format(endDate);
//			searchParams.put("LTE_startTime", startTime);
//			searchParams.put("GTE_endTime", startTime);
//			list.addAll(super.findList(searchParams));
//			searchParams.put("LTE_startTime", endTime);
//			searchParams.put("GTE_endTime", endTime);
//			list.addAll(super.findList(searchParams));
//			searchParams.remove("GTE_endTime");
//			searchParams.remove("LTE_startTime");
//			searchParams.put("GTE_startTime",startTime);
//			searchParams.put("LTE_endTime", endTime);
//			list.addAll(super.findList(searchParams));
//			
//			//2.1  list转map
//			Map<Integer,List<ParkingFee>> map=new HashMap<Integer,List<ParkingFee>>();
//			for(ParkingFee fee:list){
//				List<ParkingFee> ls=map.get(fee.getWeek());
//				if(ls==null){
//					ls=new ArrayList<ParkingFee>();
//				}
//				ls.add(fee);
//				map.put(fee.getWeek(),ls);
//			}
//			
//			//2.2  计算周天的价格
//
//			long days = CoreUtils.getDaySub(startDate, endDate);
//			for (int i = 0; i <= days; i++) {
//				//2.3 设置日期
//				Date minDate = CoreUtils.addDay(startDate, i);
//				if (CoreUtils.compare_date(minDate, endDate) == 1) {
//					minDate = endDate;
//				}
//				if(i>0){
//					minDate=CoreUtils.minHourDate(minDate);
//				}
//				int week=CoreUtils.getWeek(minDate);
//				Date maxDate=CoreUtils.biggestHourDate(minDate);
//				if (CoreUtils.compare_date(maxDate, endDate) == 1) {
//					maxDate = endDate;
//				}
//				//2.4 计算费用
//				List<ParkingFee> ls=map.get(week);
//				if(ls!=null){
//					for(ParkingFee fee:ls){
//						int startHour=Integer.valueOf(fee.getStartTime().substring(0, 2));
//						int endHour=Integer.valueOf(fee.getEndTime().substring(0, 2));
//						int minHour=CoreUtils.getHour(minDate);
//						int maxHour=CoreUtils.getHour(maxDate);
//						if(minHour>startHour){
//							startHour=minHour;
//						}
//						if(maxHour<endHour){
//							endHour=maxHour;
//						}
//						totalFee=totalFee.add(fee.getFee().multiply(new BigDecimal(endHour-startHour+1)));
//					}
//				}
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return totalFee;
	}
	
	/**
	 * 根据条件查询，未删除，like 用户公司code%
	 * 
	 * @throws Exception
	 */
	@Override
	public Page<ParkingFee> findPage(Map<String, Object> filterParams, PageRequest pageRequest) throws Exception {
		User user = ShiroUserUtils.GetCurrentUser();
		filterParams.put("RLIKE_parkingLot.companyCode", user.getCompany().getCode());
		filterParams.put("EQ_isDelete", 0);
		return super.findPage(filterParams, pageRequest);
	}

	public ParkingFee save(ParkingFee entity) throws Exception {

		User user = ShiroUserUtils.GetCurrentUser();
		Date now = CoreUtils.nowtime();
		if (entity.getId() == null) {
			entity.setCreateDate(now);
			entity.setCreateUser(user);
		}
		entity.setUpdateUser(user);
		entity.setUpdateDate(now);

		return super.save(entity);
	}

	public void deleteUpdateById(Long id) throws Exception {
		ParkingFee entity = getObjectById(id);
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
