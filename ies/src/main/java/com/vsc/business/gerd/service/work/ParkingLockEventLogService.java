package com.vsc.business.gerd.service.work;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vsc.business.gerd.entity.work.ParkingLock;
import com.vsc.business.gerd.entity.work.ParkingLockEventLog;
import com.vsc.business.gerd.repository.work.ParkingLockEventLogDao;
import com.vsc.modules.service.BaseService;
import com.vsc.modules.tcp.entity.TcpMsg;
import com.vsc.util.Log4jUtils;

/**
 * 
 * @author XiangXiaoLin
 *
 */
@Service
@Transactional
public class ParkingLockEventLogService extends BaseService<ParkingLockEventLog>{
  
	@Autowired
	private ParkingLockEventLogDao parkingLockEventLogDao;

	@Autowired
	private ParkingLockService parkingLockService;
  	
	@Override
	public PagingAndSortingRepository<ParkingLockEventLog, Long> getPagingAndSortingRepositoryDao() {
		return this.parkingLockEventLogDao;
	}

	@Override
	public JpaSpecificationExecutor<ParkingLockEventLog> getJpaSpecificationExecutorDao() {
		return this.parkingLockEventLogDao;
	}
	
	/**
	 * 地锁上报更新
	 */
	public void tcpUpload(TcpMsg tcpMsg) {
		ParkingLock parkingLock = parkingLockService.getByCode(tcpMsg.getIpAddress(), tcpMsg.getLockNum());
		Boolean isOnline=true;
		switch (tcpMsg.getEventType()) {
			// 网关上线
			case "30":
			// 网关心跳
			case "22":
				break;
			// 地锁下线
			case "44":
				isOnline=false;
			// 空余位（地锁状态上报）
			case "31":
			// 地锁上线
			case "33":
			// 控制事件
			case "55":
			// 特殊情况
			case "66":
				if(parkingLock==null){
					Log4jUtils.tcpLog.error("NoLock:" + tcpMsg.getHexMsg());
					break;
				}
				Date curTime=new Date();
				parkingLock.setLogUpdateTime(curTime);
				parkingLock.setIsOnline(isOnline);
				parkingLock.setIsCarOn(tcpMsg.getIsCarOn());
				parkingLock.setIsOpen(tcpMsg.getIsOpen());
				parkingLock.setPower(tcpMsg.getPower());
				parkingLockService.update(parkingLock);
				
				ParkingLockEventLog log=getMaxBy(parkingLock.getCode());
				if(!(log!=null&&log.getHexMsg()!=null&&log.getHexMsg().equals(tcpMsg.getHexMsg()))){
					ParkingLockEventLog parkingLockEventLog = new ParkingLockEventLog();
					parkingLockEventLog.setCreateTime(curTime);
					parkingLockEventLog.setEventType(tcpMsg.getEventType());
					parkingLockEventLog.setLockNum(tcpMsg.getLockNum());
					parkingLockEventLog.setState(tcpMsg.getState());
					parkingLockEventLog.setMcOpen(tcpMsg.getMcOpen());
					parkingLockEventLog.setIpAddress(tcpMsg.getIpAddress());
					parkingLockEventLog.setHexMsg(tcpMsg.getHexMsg());
					parkingLockEventLog.setParkingLockCode(parkingLock.getCode());
					try {
						save(parkingLockEventLog);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
			// 下降控制返回
			case "06":
			// 上升控制返回
			case "07":
			// 锁地锁控制返回
			case "35":
			// 解地锁控制返回
			case "37":
			// 控制返回 数据错误
			case "38":
				break;
			// 无匹配EventType
			default:
				Log4jUtils.tcpLog.error("NoEventType:" + tcpMsg.getHexMsg());
				break;	
		}
				
	}	
	
	/**
	 * 根据地锁编号查询最新记录
	 */
	public ParkingLockEventLog getMaxBy(String parkingLockCode){
		Map<String, Object> filterParams = new HashMap<String, Object>();
		filterParams.put("EQ_parkingLock.code",parkingLockCode);
		List<ParkingLockEventLog> list=findAll(filterParams, "createTime", "DESC");
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
}
