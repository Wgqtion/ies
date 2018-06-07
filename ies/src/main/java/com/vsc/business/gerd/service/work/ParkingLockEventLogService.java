package com.vsc.business.gerd.service.work;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vsc.business.gerd.entity.work.ParkingLock;
import com.vsc.business.gerd.entity.work.ParkingLockEventLog;
import com.vsc.business.gerd.repository.work.ParkingLockEventLogDao;
import com.vsc.modules.service.BaseService;
import com.vsc.modules.tcp.entity.TcpMsg;
import com.vsc.util.CoreUtils;
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
	 * 下线网关地锁
	 */
	public void downlineAllBy(String ipAddress){
		Log4jUtils.tcpLog.info("开始下线网关所有地锁："+ipAddress);
		Map<String, Object> searchParams = new HashMap<String,Object>();
		searchParams.put("EQ_ipAddress",ipAddress);
		try {
			List<ParkingLock> list=this.parkingLockService.findAllList(searchParams);
			if(list!=null){
				for (ParkingLock parkingLock : list) {
					parkingLock.setIsOnline(false);
					this.parkingLockService.update(parkingLock);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 地锁上报更新
	 */
	public void tcpUpload(TcpMsg tcpMsg) {
		if (tcpMsg.getFlagCRC()) {
			ParkingLock parkingLock = parkingLockService.getByCode(tcpMsg.getIpAddress(), tcpMsg.getLockNum());
			Boolean isOnline=true;
			Date curTime=new Date();
			String parkingLockCode=null;
			if(parkingLock!=null){
				parkingLockCode=parkingLock.getCode();
			}
			
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
					ParkingLockEventLog log=getMaxBy(parkingLockCode);
					if(!(log!=null&&log.getHexMsg()!=null&&log.getHexMsg().equals(tcpMsg.getHexMsg()))){
						ParkingLockEventLog parkingLockEventLog = new ParkingLockEventLog();
						parkingLockEventLog.setCreateTime(curTime);
						parkingLockEventLog.setEventType(tcpMsg.getEventType());
						parkingLockEventLog.setLockNum(tcpMsg.getLockNum());
						parkingLockEventLog.setState(tcpMsg.getState());
						parkingLockEventLog.setMcOpen(tcpMsg.getMcOpen());
						parkingLockEventLog.setIpAddress(tcpMsg.getIpAddress());
						parkingLockEventLog.setHexMsg(tcpMsg.getHexMsg());
						parkingLockEventLog.setParkingLockCode(parkingLockCode);
						try {
							save(parkingLockEventLog);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					
					if(parkingLock==null){
						Log4jUtils.tcpLog.info("NoLock:" + tcpMsg.getHexMsg());
						break;
					}
					parkingLock.setLogUpdateTime(curTime);
					parkingLock.setIsOnline(isOnline);
					parkingLock.setIsCarOn(tcpMsg.getIsCarOn());
					parkingLock.setIsOpen(tcpMsg.getIsOpen());
					parkingLock.setPower(tcpMsg.getPower());
					parkingLockService.update(parkingLock);
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
					Log4jUtils.tcpLog.info("NoEventType:" + tcpMsg.getHexMsg());
					break;	
			}
		} else {
			Log4jUtils.tcpError.info("CRCError:" + tcpMsg.getHexMsg());
		}
	}	
	/**
	 * 根据地锁编号查询最新数据
	 */
	public ParkingLockEventLog getMaxBy(String parkingLockCode){
		Map<String, Object> searchParams = new HashMap<String,Object>();
		searchParams.put("EQ_parkingLockCode",parkingLockCode);
		PageRequest pageRequest=CoreUtils.buildPageRequest(1,1, "createTime", "DESC");
		Page<ParkingLockEventLog> page=null;
		try {
			page=super.findPage(searchParams, pageRequest);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(page.getContent().size()>0){
			return page.getContent().get(0);
		}
		return null;
	}
}
