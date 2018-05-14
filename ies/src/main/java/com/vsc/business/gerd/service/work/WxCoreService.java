package com.vsc.business.gerd.service.work;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vsc.business.gerd.entity.work.ParkingLock;
import com.vsc.business.gerd.entity.work.ParkingParam;
import com.vsc.business.gerd.entity.work.ReserveTime;
import com.vsc.business.gerd.entity.work.WxCore;
import com.vsc.business.gerd.entity.work.WxOrder;
import com.vsc.business.gerd.repository.work.WxCoreDao;
import com.vsc.modules.entity.MessageException;
import com.vsc.modules.service.BaseService;
import com.vsc.util.CoreUtils;

/**
 * 小程序核心 逻辑操作
 * 
 * @author XiangXiaoLin
 *
 */
@Service
@Transactional
public class WxCoreService extends BaseService<WxCore> {

	@Autowired
	private WxCoreDao wxCoreDao;

	@Autowired
	private ParkingParamService parkingParamService;

	@Autowired
	private ReserveTimeService reserveTimeService;

	@Autowired
	private ParkingLockService parkingLockService;

	@Autowired
	private ParkingFeeService parkingFeeService;
	
	@Autowired
	private WxOrderService wxOrderService;

	@Override
	public PagingAndSortingRepository<WxCore, Long> getPagingAndSortingRepositoryDao() {
		return this.wxCoreDao;
	}

	@Override
	public JpaSpecificationExecutor<WxCore> getJpaSpecificationExecutorDao() {
		return this.wxCoreDao;
	}

	/**
	 * 查询日期当天已取消的预约次数 日期 weixinId
	 */
	public int getReserveNum(WxCore wxCore) {
		Map<String, Object> searchParams = new HashMap<String, Object>();
		searchParams.put("EQ_type", 1);
		searchParams.put("EQ_isCancel", true);
		searchParams.put("GTE_startTime", CoreUtils.minHourDate(wxCore.getStartTime()));
		searchParams.put("LTE_startTime", CoreUtils.biggestHourDate(wxCore.getStartTime()));
		searchParams.put("EQ_weixinId", wxCore.getWeixinId());
		try {
			return findList(searchParams).size();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * 查询使用状态
	 */
	public int getCoreStatus(WxCore wxCore){
		WxOrder wxOrder=this.wxOrderService.getByWeixinId(wxCore.getWeixinId());
		if (wxOrder != null) {
			return 1;
		}
		Map<String, Object> searchParams = new HashMap<String, Object>();
		searchParams.put("EQ_weixinId", wxCore.getWeixinId());
		searchParams.put("EQ_status",wxCore.getStatus());
		WxCore wc = this.find(searchParams);
		if (wc != null) {
			return 2;
		}
		searchParams.remove("EQ_weixinId");
		searchParams.put("EQ_parkingLock.code", wxCore.getParkingLockCode());
		wc = this.find(searchParams);
		if (wc != null) {
			return 3;
		}
		return 0;
	}
	
	/**
	 * 查询使用中的记录，status=1，根据type，weixinId 
	 * @param wxCore
	 * @return
	 */
	public WxCore findBy(WxCore wxCore){
		Map<String, Object> searchParams = new HashMap<String, Object>();
		searchParams.put("EQ_status",1);
		searchParams.put("EQ_type", wxCore.getType());
		searchParams.put("EQ_weixinId", wxCore.getWeixinId());
		return this.find(searchParams);
	}
	
	/**
	 * 预约操作
	 */
	public int reserve(WxCore wxCore) throws MessageException {
		ParkingLock parkingLock = this.parkingLockService.getByCode(wxCore.getParkingLockCode());

		// 查询使用记录
		int status=getCoreStatus(wxCore);
		if(status!=0){
			return status;
		}

		// 查询可预约时间
		ReserveTime reserveTime = new ReserveTime();
		reserveTime.setStartTime(CoreUtils.formatDate(wxCore.getStartTime(), 1));
		reserveTime.setParkingLotCode(parkingLock.getParkingGarage().getParkingLotCode());
		boolean isCanReserveTime = this.reserveTimeService.isCanReserveTime(reserveTime);
		if (!isCanReserveTime) {
			return 4;
		}

		// 查询参数
		ParkingParam parkingParam = this.parkingParamService
				.getByParkingLotCode(parkingLock.getParkingGarage().getParkingLotCode());
		if (parkingParam != null) {
			int reserveNum = getReserveNum(wxCore);
			if (reserveNum >= parkingParam.getCancelNum()) {
				return 5;
			}
		}

		try {
			super.save(wxCore);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		String message=this.parkingLockService.reverse(new Long[] {parkingLock.getId()}, "01", wxCore.getWeixinId(),
//				ParkingLockOperationEvent.SOURCETYPE_PHONE);
//		if (message.length() > 0) {
//			throw new MessageException(message);
//		}
		return 0;
	}

	
	/**
	 * 解锁操作
	 */
	public int unlock(WxCore wxCore) throws MessageException {
		ParkingLock parkingLock = this.parkingLockService.getByCode(wxCore.getParkingLockCode());
		wxCore.setType(Integer.valueOf(1));
		int reserveStatus=cancelReserve(wxCore);
		wxCore.setType(Integer.valueOf(2));
		// 查询使用记录
		int status=getCoreStatus(wxCore);
		if((status==1&&reserveStatus==1)||(status!=0&&reserveStatus!=0)){
			return status;
		}


		try {
			super.save(wxCore);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		String message=this.parkingLockService.reverse(new Long[] {parkingLock.getId()}, "02", wxCore.getWeixinId(),
//				ParkingLockOperationEvent.SOURCETYPE_PHONE);
//		if (message.length() > 0) {
//			throw new MessageException(message);
//		}
		return 0;
	}
	
	/**
	 * 取消预约
	 */
	public int cancelReserve(WxCore wxCore) {
		try {
			// 查询记录
			WxCore wc=findBy(wxCore);
			if(wc==null){
				return 1;
			}
			wc.setStatus(0);
			wc.setIsCancel(wxCore.getIsCancel());
			wc.setEndTime(new Date());
			// 查询参数
			ParkingParam parkingParam = this.parkingParamService
					.getByParkingLotCode(wc.getParkingLock().getParkingGarage().getParkingLotCode());
			// 预约免费分钟
			Integer freeReserveMin = 0;
			if (parkingParam != null) {
				freeReserveMin = parkingParam.getFreeReserveMin();
			}
			Date startDate = CoreUtils.addMin(wc.getStartTime(), freeReserveMin);
			if (CoreUtils.compare_date(startDate, wc.getEndTime()) != -1) {
				super.save(wc);
				return 2;
			}
			// 收费计算
			BigDecimal fee = this.parkingFeeService.calculateFee(wc);
			wc.setAmount(fee);
			super.save(wc);
			wxOrderService.save(wc);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * 上锁操作
	 */
	public int lock(WxCore wxCore) {
		// 查询记录
		WxCore wc=findBy(wxCore);
		try {
			if(wc==null){
				return 1;
			}
			wc.setStatus(0);
			wc.setEndTime(new Date());
			// 查询参数
			ParkingParam parkingParam = this.parkingParamService
					.getByParkingLotCode(wc.getParkingLock().getParkingGarage().getParkingLotCode());
			// 停车免费分钟
			Integer freeParkingMin = 0;
			if (parkingParam != null) {
				freeParkingMin = parkingParam.getFreeParkingMin();
			}
			Date startDate = CoreUtils.addMin(wc.getStartTime(), freeParkingMin);
			if (CoreUtils.compare_date(startDate, wc.getEndTime()) != -1) {
				super.save(wc);
				return 2;
			}
			// 收费计算
			BigDecimal fee = this.parkingFeeService.calculateFee(wc);
			wc.setAmount(fee);
			super.save(wc);
			wxOrderService.save(wc);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		String message=this.parkingLockService.reverse(new Long[] {wc.getParkingLock().getId()}, "02", wxCore.getWeixinId(),
//		ParkingLockOperationEvent.SOURCETYPE_PHONE);
//if (message.length() > 0) {
//	throw new MessageException(message);
//}
		return 0;
	}
}
