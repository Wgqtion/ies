package com.vsc.business.gerd.service.work;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vsc.business.gerd.entity.work.ParkingOrder;
import com.vsc.business.gerd.repository.work.ParkingOrderDao;
import com.vsc.modules.entity.MapBean;
import com.vsc.modules.service.BaseService;

/**
 * 
 * @author jerry
 *
 */
@Service
@Transactional
public class ParkingOrderService extends BaseService<ParkingOrder> {
	private static Logger logger = LoggerFactory.getLogger(ParkingOrderService.class);

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
	 * 出场
	 */
	public int outSchool(ParkingOrder parkingOrder) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isEnabled", 1);
		map.put("inPlateNo", parkingOrder.getInPlateNo());
		map.put("outPicName", parkingOrder.getOutPicName());
		map.put("outTime", parkingOrder.getOutTime());
		map.put("outDoorId", parkingOrder.getOutDoor().getId());

		//获取最新的那一条记录 将id放入map再更新
				List<MapBean<String, Object>>  maxvl=this.ibatisQueryDao.findAll("maxParkingOrderPay.select", map);
				if(!maxvl.isEmpty()){
					map.put("id", maxvl.get(0).get("id"));
				}
		
		return this.ibatisQueryDao.update("updateParkingOrderOutSchool.update", map);
	};

	/**
	 * 支付
	 */
	public int orderPay(ParkingOrder parkingOrder) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("inPlateNo", parkingOrder.getInPlateNo());
		map.put("payTime", parkingOrder.getAmountTime());
		map.put("outTimeLast", parkingOrder.getOutTimeLast());
		map.put("isPayOk", parkingOrder.getAmountOnlineOk());
		map.put("ssPayAmount", parkingOrder.getAmountsReceivable());
		map.put("ysPayAmount", parkingOrder.getAmountsPaid());
		map.put("preferentialWay", parkingOrder.getPreferentialWay());
		map.put("preferentialNum", parkingOrder.getPreferentialNum());
		map.put("memberName", parkingOrder.getMemberName());
		map.put("onlinePaymentAmount", parkingOrder.getOnlinePaymentAmount());
		map.put("amountOfConcessions", parkingOrder.getAmountOfConcessions());
		map.put("busCardPaymentAmount", parkingOrder.getBusCardPaymentAmount());
		//获取最新的那一条记录 将id放入map再更新
		List<MapBean<String, Object>>  maxvl=this.ibatisQueryDao.findAll("maxParkingOrderPay.select", map);
		if(!maxvl.isEmpty()){
			map.put("id", maxvl.get(0).get("id"));
		}

		return this.ibatisQueryDao.update("updateParkingOrderPay.update", map);
	};

}
