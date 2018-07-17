package com.vsc.business.gerd.service.work;

import java.math.BigDecimal;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.*;

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
import com.vsc.business.gerd.entity.work.ParkingParam;
import com.vsc.business.gerd.entity.work.ReserveTime;
import com.vsc.business.gerd.entity.work.WxCore;
import com.vsc.business.gerd.entity.work.WxOrder;
import com.vsc.business.gerd.repository.work.WxCoreDao;
import com.vsc.constants.Constants;
import com.vsc.modules.entity.MessageException;
import com.vsc.modules.quartz.job.ReserveCancelJob;
import com.vsc.modules.quartz.manager.QuartzManager;
import com.vsc.modules.service.BaseService;
import com.vsc.modules.shiro.ShiroUserUtils;
import com.vsc.util.CoreUtils;
import com.vsc.util.Log4jUtils;
import com.vsc.util.WxCoreServiceUtil;

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
	 * 根据条件查询，未删除，like 用户公司code%
	 */
	@Override
	public Page<WxCore> findPage(Map<String, Object> filterParams, PageRequest pageRequest) throws Exception {
		User user = ShiroUserUtils.GetCurrentUser();
		filterParams.put("RLIKE_parkingLock.parkingGarage.parkingLot.companyCode", user.getCompany().getCode());
		return super.findPage(filterParams, pageRequest);
	}
	/**
	 * 把未完成的预约单加入定时任务
	 */
	public void addCancelJobs(){
		Log4jUtils.reserveCancel.info("查询需要系统取消的预约：");
		Map<String, Object> searchParams = new HashMap<String, Object>();
		searchParams.put("EQ_status", 1);
		searchParams.put("EQ_type", 1);
		try {
			List<WxCore> wxCores=super.findList(searchParams);
			if(wxCores!=null){
				for (WxCore wxCore : wxCores) {
					if(CoreUtils.compare_date(new Date(),wxCore.getStartTime())==1){
						int status=WxCoreServiceUtil.getWxCoreService().cancelReserve(wxCore,false);
		        		Log4jUtils.reserveCancel.info("系统取消超时预约："+Constants.CANCEL_RESERVE_MESSAGE_STATUS[status]);
						continue;
					}
					ParkingParam parkingParam = this.parkingParamService
							.getByParkingLotCode(wxCore.getParkingLock().getParkingGarage().getParkingLotCode());
					if(parkingParam!=null){
						// 预约保留时间
						Time reserveMin = parkingParam.getReserveMin();
						if (reserveMin!=null) {
							Log4jUtils.reserveCancel.info("添加自动取消任务："+wxCore.getId());
							QuartzManager.addJob(wxCore.getWeixinId(), wxCore.getWeixinId(), wxCore.getWeixinId(),
									wxCore.getWeixinId(), CoreUtils.getCron(CoreUtils.addMin(wxCore.getStartTime(), reserveMin)),
									ReserveCancelJob.class, wxCore.getWeixinId());
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	public int getCoreStatus(WxCore wxCore) {
		Map<String, Object> searchParams = new HashMap<String, Object>();
		searchParams.put("EQ_weixinId", wxCore.getWeixinId());
		searchParams.put("EQ_status", 1);
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
		WxOrder wxOrder = this.wxOrderService.getByWeixinId(wxCore.getWeixinId());
		if (wxOrder != null) {
			return 1;
		}
		return 0;
	}

	/**
	 * 查询使用中的记录，status=1，weixinId
	 * 
	 * @param wxCore
	 * @return
	 */
	public WxCore findBy(WxCore wxCore) {
		Map<String, Object> searchParams = new HashMap<String, Object>();
		searchParams.put("EQ_status", 1);
		searchParams.put("EQ_weixinId", wxCore.getWeixinId());
		return this.find(searchParams);
	}

	/**
	 * 预约操作
	 */
	public int reserve(WxCore wxCore) throws MessageException {
		ParkingLock parkingLock = this.parkingLockService.getByCode(wxCore.getParkingLockCode());

		// 查询使用记录
		int status = getCoreStatus(wxCore);
		if (status != 0) {
			return status;
		}

		// 查询可预约时间
		ReserveTime reserveTime = new ReserveTime();
		SimpleDateFormat format=new SimpleDateFormat("HH:mm:ss");
		String sss = format.format(wxCore.getStartTime());
		System.out.println(sss);
		reserveTime.setStartTime(Time.valueOf(sss));
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
			if (reserveNum >= parkingParam.getCancelNum() && parkingParam.getCancelNum() > 0) {
				return 5;
			}
			// 预约保留时间
			Time reserveMin = parkingParam.getReserveMin();
			if (reserveMin != null) {
				QuartzManager.addJob(wxCore.getWeixinId(), wxCore.getWeixinId(), wxCore.getWeixinId(),
						wxCore.getWeixinId(), CoreUtils.getCron(CoreUtils.addMin(new Date(), reserveMin)),
						ReserveCancelJob.class, wxCore.getWeixinId());
			}
		}

		try {
			super.save(wxCore);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String message = this.parkingLockService.reverse(new Long[] { parkingLock.getId() }, "01", wxCore.getWeixinId(),
				ParkingLockOperationEvent.SOURCETYPE_PHONE);
		if (message.length() > 0) {
			throw new MessageException(message);
		}
		return 0;
	}

	/**
	 * 解锁操作
	 */
	public int unlock(WxCore wxCore) throws MessageException {
		ParkingLock parkingLock = this.parkingLockService.getByCode(wxCore.getParkingLockCode());
		wxCore.setType(Integer.valueOf(2));
		// 查询使用记录
		int status = getCoreStatus(wxCore);
		if (status == 2) {
			WxCore wc = this.findBy(wxCore);
			if (!wc.getParkingLockCode().equals(wxCore.getParkingLockCode())) {
				return status;
			} else if (wc.getParkingLockCode().equals(wxCore.getParkingLockCode()) && wc.getType().intValue() == 2) {
				return status;
			}
		} else if (status != 0) {
			return status;
		}
		wxCore.setType(Integer.valueOf(1));
		// 取消预约
		int reserveStatus = cancelReserve(wxCore, true);
		if (reserveStatus != 1) {
			return status;
		}
		wxCore.setType(Integer.valueOf(2));
		try {
			super.save(wxCore);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String message = this.parkingLockService.reverse(new Long[] { parkingLock.getId() }, "02", wxCore.getWeixinId(),
				ParkingLockOperationEvent.SOURCETYPE_PHONE);
		if (message.length() > 0) {
			throw new MessageException(message);
		}
		return 0;
	}

	/**
	 * 取消预约
	 */
	public int cancelReserve(WxCore wxCore, boolean flag) {
		Integer status = 0;
		try {
			QuartzManager.delJob(wxCore.getWeixinId(), wxCore.getWeixinId(), wxCore.getWeixinId(),
					wxCore.getWeixinId());
			// 查询记录
			WxCore wc = findBy(wxCore);
			if (wc == null || Integer.valueOf(2).equals(wc.getType())) {
				return 1;
			}
			wc.setStatus(status);
			wc.setIsCancel(wxCore.getIsCancel());
			wc.setIsSystemCancel(wxCore.getIsSystemCancel());
			wc.setEndTime(new Date());
			// 查询参数
			ParkingParam parkingParam = this.parkingParamService
					.getByParkingLotCode(wc.getParkingLock().getParkingGarage().getParkingLotCode());
			boolean isFree = false;
			// 预约限免分钟
			Time freeReserveMin = null;
			if (parkingParam != null) {
				freeReserveMin = parkingParam.getFreeReserveMin();
			}
			Date startDate = CoreUtils.addMin(wc.getStartTime(), freeReserveMin);
			if (CoreUtils.compare_date(startDate, wc.getEndTime()) != -1) {
				isFree = true;
				status = 2;
			}
			// 预约优惠分钟
			Time privilegeReserveMin = null;
			if (parkingParam != null) {
				privilegeReserveMin = parkingParam.getPrivilegeReserveMin();
			}
			wc.setStartTime(CoreUtils.addMin(wc.getStartTime(), privilegeReserveMin));
			if (CoreUtils.compare_date(wc.getStartTime(), wc.getEndTime()) != -1) {
				isFree = true;
				status = 3;
			}
			BigDecimal fee = new BigDecimal(0.0);
			if (!isFree) {
				// 收费计算
				fee = this.parkingFeeService.calculateFee(wc);
				if (parkingParam != null && fee != null && parkingParam.getMaxReserveFee() != null
						&& parkingParam.getMaxReserveFee().intValue() > 0
						&& fee.intValue() > parkingParam.getMaxReserveFee().intValue()) {
					fee = new BigDecimal(parkingParam.getMaxReserveFee());
				}
			}
			wc.setAmount(fee);
			wc.setIsFree(isFree);
			super.save(wc);
			wxOrderService.save(wc);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (flag) {
			status = 1;
		}
		return status;
	}

	/**
	 * 上锁操作
	 */
	public int lock(WxCore wxCore) throws MessageException {
		String message = this.parkingLockService.reverse(new Long[] { wxCore.getParkingLock().getId() }, "01",
				wxCore.getWeixinId(), ParkingLockOperationEvent.SOURCETYPE_PHONE);
//		if (message.length() > 0) {
//			throw new MessageException(message);
//		}
		return 0;
	}
}
