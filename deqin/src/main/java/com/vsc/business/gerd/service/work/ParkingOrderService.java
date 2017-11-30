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

import com.vsc.business.gerd.entity.work.ParkingOrder;
import com.vsc.business.gerd.repository.work.ParkingOrderDao;
import com.vsc.modules.service.BaseService;

/**
 * 停车单控制
 * @author XiangXiaoLin
 *
 */
@Service
@Transactional
public class ParkingOrderService extends BaseService<ParkingOrder> {

	@Autowired
	private ParkingOrderDao parkingOrderDao;

	@Override
	public PagingAndSortingRepository<ParkingOrder, Long> getPagingAndSortingRepositoryDao() {
		return this.parkingOrderDao;
	}

	@Override
	public JpaSpecificationExecutor<ParkingOrder> getJpaSpecificationExecutorDao() {
		return this.parkingOrderDao;
	}

	/**
	 * 根据车牌，状态查询
	 * 
	 * @param parkingOrder
	 * @return
	 */
	private ParkingOrder getParkingOrderByPlateAndStatus(ParkingOrder parkingOrder, Integer orderStatus) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("EQ_plateNo", parkingOrder.getPlateNo());
		params.put("EQ_orderStatus", orderStatus);
		return this.find(params);
	}

	/**
	 * 进入控制
	 * 
	 * @param parkingOrder
	 */
	public void inParkingOrder(ParkingOrder parkingOrder) {

		if (StringUtils.isBlank(parkingOrder.getPlateNo())) {
			// 如果车牌空
			parkingOrder.setOrderStatus(Integer.valueOf(1));
		} else {
			// 查找之前状态0的停车单
			ParkingOrder upParkingOrder = getParkingOrderByPlateAndStatus(parkingOrder, Integer.valueOf(0));
			if (upParkingOrder != null) {
				upParkingOrder.setOrderStatus(Integer.valueOf(1));
				this.save(upParkingOrder);
			}
		}

		Date now = new Date();
		parkingOrder.setCreateTime(now);
		parkingOrder.setUpdateTime(now);
		parkingOrder.setPayNumber(UUID.randomUUID().toString());
		this.save(parkingOrder);
	}

	/**
	 * 出去控制
	 */
	public void outParkingOrder(ParkingOrder parkingOrder) {
		// 直接保存标志
		boolean flag = true;
		if (!StringUtils.isBlank(parkingOrder.getPlateNo())) {
			// 车牌不为空 ，查找之前状态0的停车单
			ParkingOrder upParkingOrder = getParkingOrderByPlateAndStatus(parkingOrder, Integer.valueOf(0));
			if (upParkingOrder != null&&upParkingOrder.getCreateTime()==upParkingOrder.getUpdateTime()) {
				flag = false;
				upParkingOrder.setOutTime(parkingOrder.getOutTime());
				upParkingOrder.setOutCameraIp(parkingOrder.getOutCameraIp());
				upParkingOrder.setOutPicName(parkingOrder.getOutPicName());
				upParkingOrder.setOutSchoolDoorName(parkingOrder.getOutSchoolDoorName());
				
				upParkingOrder.setUpdateTime(new Date());
				this.save(upParkingOrder);
			}
		}
		if (flag) {
			Date now = new Date();
			parkingOrder.setCreateTime(now);
			parkingOrder.setUpdateTime(now);
			parkingOrder.setPayNumber(UUID.randomUUID().toString());
			parkingOrder.setOrderStatus(Integer.valueOf(1));
			this.save(parkingOrder);
		}
	}

	/**
	 * 支付控制
	 */
	public void payParkingOrder(ParkingOrder parkingOrder) {
		// 直接保存标志
		boolean flag = true;
		if (!StringUtils.isBlank(parkingOrder.getPlateNo())) {
			// 车牌不为空 ，查找之前状态0的停车单
			ParkingOrder upParkingOrder = getParkingOrderByPlateAndStatus(parkingOrder, Integer.valueOf(0));
			if (upParkingOrder != null) {
				flag = false;
				upParkingOrder.setAmountOfConcessions(parkingOrder.getAmountOfConcessions());
				upParkingOrder.setBusCardPaymentAmount(parkingOrder.getBusCardPaymentAmount());
				upParkingOrder.setIsPayOk(parkingOrder.getIsPayOk());
				upParkingOrder.setMemberName(parkingOrder.getMemberName());
				upParkingOrder.setOnlinePaymentAmount(parkingOrder.getOnlinePaymentAmount());
				upParkingOrder.setPayTime(parkingOrder.getPayTime());
				upParkingOrder.setPreferentialNum(parkingOrder.getPreferentialNum());
				upParkingOrder.setPreferentialWay(parkingOrder.getPreferentialWay());
				upParkingOrder.setSsPayAmount(parkingOrder.getSsPayAmount());
				upParkingOrder.setYsPayAmount(parkingOrder.getYsPayAmount());
				upParkingOrder.setOutTimeLast(parkingOrder.getOutTimeLast());
				
				upParkingOrder.setOrderStatus(Integer.valueOf(1));
				upParkingOrder.setUpdateTime(new Date());
				this.save(upParkingOrder);
			}
		}
		if (flag) {
			Date now = new Date();
			parkingOrder.setCreateTime(now);
			parkingOrder.setUpdateTime(now);
			parkingOrder.setPayNumber(UUID.randomUUID().toString());
			parkingOrder.setOrderStatus(Integer.valueOf(1));
			this.save(parkingOrder);
		}
	}

}
