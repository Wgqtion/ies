package com.vsc.business.gerd.service.work;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tuckey.web.filters.urlrewrite.utils.StringUtils;

import com.vsc.business.gerd.entity.work.ParkingInOut;
import com.vsc.business.gerd.repository.work.ParkingInOutDao;
import com.vsc.modules.service.BaseService;

/**
 * 进出口记录 控制
 * @author XiangXiaoLin
 *
 */
@Service
@Transactional
public class ParkingInOutService extends BaseService<ParkingInOut> {

	@Autowired
	private ParkingInOutDao parkingInOutDao;

	@Override
	public PagingAndSortingRepository<ParkingInOut, Long> getPagingAndSortingRepositoryDao() {
		return this.parkingInOutDao;
	}

	@Override
	public JpaSpecificationExecutor<ParkingInOut> getJpaSpecificationExecutorDao() {
		return this.parkingInOutDao;
	}

	/**
	 * 根据车牌，状态查询
	 * 
	 * @param parkingInOut
	 * @return
	 */
	private ParkingInOut getParkingOrderByPlateAndStatus(ParkingInOut parkingInOut, Integer orderStatus) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("EQ_carNo", parkingInOut.getCarNo());
		params.put("EQ_orderStatus", orderStatus);
		return this.find(params);
	}

	/**
	 * 1.进入控制
	 * 
	 * @param parkingInOut
	 * @throws Exception 
	 */
	public void inParkingOrder(ParkingInOut parkingInOut) throws Exception {

		if (StringUtils.isBlank(parkingInOut.getCarNo())) {
			// 如果车牌空
			parkingInOut.setOrderStatus(Integer.valueOf(1));
		} else {
			// 查找之前状态0的停车单
			ParkingInOut upParkingOrder = getParkingOrderByPlateAndStatus(parkingInOut, Integer.valueOf(0));
			if (upParkingOrder != null) {
				upParkingOrder.setOrderStatus(Integer.valueOf(1));
				this.save(upParkingOrder);
			}
		}

		Date now = new Date();
		parkingInOut.setCreateTime(now);
		parkingInOut.setPayNumber(UUID.randomUUID().toString());
		this.save(parkingInOut);
	}

	

	/**
	 * 2.支付控制
	 * @throws Exception 
	 */
	public void payParkingOrder(ParkingInOut parkingInOut) throws Exception {
		// 直接保存标志
		boolean flag = true;
		if (!(StringUtils.isBlank(parkingInOut.getCarNo())||"无车牌".equals(parkingInOut.getCarNo()))) {
			// 车牌不为空 ，查找之前状态0的停车单
			ParkingInOut upParkingOrder = getParkingOrderByPlateAndStatus(parkingInOut, Integer.valueOf(0));
			if (upParkingOrder != null&&upParkingOrder.getUpdatePayTime()==null) {
				flag = false;
				upParkingOrder.setAmountOfConcessions(parkingInOut.getAmountOfConcessions());
				upParkingOrder.setBusCardPaymentAmount(parkingInOut.getBusCardPaymentAmount());
				upParkingOrder.setIsPayOk(parkingInOut.getIsPayOk());
				upParkingOrder.setMemberName(parkingInOut.getMemberName());
				upParkingOrder.setOnlinePaymentAmount(parkingInOut.getOnlinePaymentAmount());
				upParkingOrder.setPayTime(parkingInOut.getPayTime());
				upParkingOrder.setPreferentialNum(parkingInOut.getPreferentialNum());
				upParkingOrder.setPreferentialWay(parkingInOut.getPreferentialWay());
				upParkingOrder.setSsPayAmount(parkingInOut.getSsPayAmount());
				upParkingOrder.setYsPayAmount(parkingInOut.getYsPayAmount());
				upParkingOrder.setOutTimeLast(parkingInOut.getOutTimeLast());
				
				upParkingOrder.setUpdatePayTime(new Date());
				this.save(upParkingOrder);
				parkingInOut.setPayNumber(upParkingOrder.getPayNumber());
			}
		}
		if (flag) {
			Date now = new Date();
			parkingInOut.setCreateTime(now);
			parkingInOut.setUpdatePayTime(now);
			parkingInOut.setPayNumber(UUID.randomUUID().toString());
			this.save(parkingInOut);
		}
	}
	
	/**
	 * 3.出去控制
	 * @throws Exception 
	 */
	public void outParkingOrder(ParkingInOut parkingInOut) throws Exception {
		// 直接保存标志
		boolean flag = true;
		if (!(StringUtils.isBlank(parkingInOut.getCarNo())||"无车牌".equals(parkingInOut.getCarNo()))) {
			// 车牌不为空 ，查找之前状态0的停车单
			ParkingInOut upParkingOrder = getParkingOrderByPlateAndStatus(parkingInOut, Integer.valueOf(0));
			if (upParkingOrder != null&&upParkingOrder.getUpdateOutTime()==null) {
				flag = false;
				upParkingOrder.setOutTime(parkingInOut.getOutTime());
				upParkingOrder.setOutCameraIp(parkingInOut.getOutCameraIp());
				upParkingOrder.setOutPicName(parkingInOut.getOutPicName());
				upParkingOrder.setOutSchoolDoorName(parkingInOut.getOutSchoolDoorName());

				upParkingOrder.setOrderStatus(Integer.valueOf(1));
				upParkingOrder.setUpdateOutTime(new Date());
				this.save(upParkingOrder);
			}
		}
		if (flag) {
			Date now = new Date();
			parkingInOut.setCreateTime(now);
			parkingInOut.setUpdateOutTime(now);
			parkingInOut.setPayNumber(UUID.randomUUID().toString());
			parkingInOut.setOrderStatus(Integer.valueOf(1));
			this.save(parkingInOut);
		}
	}
}
