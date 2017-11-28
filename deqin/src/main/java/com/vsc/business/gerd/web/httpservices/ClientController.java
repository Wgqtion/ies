package com.vsc.business.gerd.web.httpservices;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.vsc.business.gerd.entity.work.ParkingGarage;
import com.vsc.business.gerd.entity.work.ParkingGarageCarnoLog;
import com.vsc.business.gerd.entity.work.ParkingLock;
import com.vsc.business.gerd.entity.work.ParkingLockEventLog;
import com.vsc.business.gerd.entity.work.ParkingLockOperationEvent;
import com.vsc.business.gerd.entity.work.ParkingOrder;
import com.vsc.business.gerd.entity.work.Passages;
import com.vsc.business.gerd.service.work.ParkingGarageCarnoLogService;
import com.vsc.business.gerd.service.work.ParkingGarageService;
import com.vsc.business.gerd.service.work.ParkingLockEventLogService;
import com.vsc.business.gerd.service.work.ParkingLockService;
import com.vsc.business.gerd.service.work.ParkingOrderService;
import com.vsc.business.gerd.service.work.PassagesService;
import com.vsc.business.gerd.vform.work.GarageIncarForm;
import com.vsc.constants.Constants;
import com.vsc.util.JSONUtil;

/**
 * 提供给客户端调用的接口控制类
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping(value = Constants.SPT + ClientController.PATH)
public class ClientController extends HttpServiceBaseController {
	// 控制层 URL地址映射
	public static final String PATH = PATH_BASE + Constants.SPT + "client";
	// 视图地址映射
	public static final String V_PATH = V_PATH_BASE;
	public static final String V_PATH_INDEX = PATH_BASE + Constants.SPT + "client";
	@Autowired
	private ParkingOrderService parkingOrderService;
	@Autowired
	private ParkingGarageService parkingGarageService;
	@Autowired
	private ParkingLockService parkingLockService;
	@Autowired
	private ParkingLockEventLogService parkingLockEventLogService;
	@Autowired
	private ParkingGarageCarnoLogService parkingGarageCarnoLogService;
	@Autowired
	private PassagesService passagesService;

	private String[] featureNames = new String[] { JSONUtil._Feature_WriteMapNullValue, JSONUtil._Feature_WriteNullListAsEmpty, JSONUtil._Feature_WriteNullStringAsEmpty, JSONUtil._Feature_WriteNullNumberAsZero, JSONUtil._Feature_WriteNullBooleanAsFalse };

	@RequestMapping(value = "")
	public String index(Model model) {
		return V_PATH_INDEX;
	}

	/**
	 * 车辆进校接口 *
	 * 
	 * @return 进校停车单编号
	 */
	@RequestMapping(value = "order/inschool")
	public ModelAndView service1(@Valid ParkingOrder parkingOrder, @RequestParam(required = true) Long inDoorId, HttpServletRequest request) {
		parkingOrder.setCreateTime(new Date());
		parkingOrder.setOrderNumber(UUID.randomUUID().toString());

		Passages passages = passagesService.getObjectById(inDoorId);

		parkingOrder.setInDoor(passages);
		parkingOrder.setIsEnabled(Boolean.TRUE);

		parkingOrderService.save(parkingOrder);

		String jsonstr = "\"" + parkingOrder.getOrderNumber() + "\"";
		return this.ajaxDoneSuccess(this.getMessage("httpservices.service_success"), jsonstr);
	}

	/**
	 * 单个上报车位有车状态 *
	 * 
	 * @return
	 */
	@RequestMapping(value = "garage/oneincar")
	public ModelAndView service2(@Valid ParkingGarageCarnoLog parkingGarageCarnoLog, HttpServletRequest request) {
		if (parkingGarageCarnoLog != null) {
			parkingGarageCarnoLog.setCreateTime(new Date());
			parkingGarageCarnoLogService.save(parkingGarageCarnoLog);

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("isOnline", parkingGarageCarnoLog.getStatus());
			params.put("carNo", parkingGarageCarnoLog.getCarNo());
			params.put("intime", parkingGarageCarnoLog.getIntime());
			params.put("name", parkingGarageCarnoLog.getParkingName());
			this.parkingGarageService.updateParkingGarageByName(params);
		}

		String jsonstr = "\"true\"";
		return this.ajaxDoneSuccess(this.getMessage("httpservices.service_success"), jsonstr);
	}

	/**
	 * 批量上报车位有车状态 *
	 * 
	 * @return
	 */
	@RequestMapping(value = "garage/incar")
	public ModelAndView service3(@Valid GarageIncarForm vo, HttpServletRequest request) {
		if (vo.getCarnologs() != null) {
			ParkingGarageCarnoLog[] vl = vo.getCarnologs();
			Date now = new Date();
			for (int i = 0; i < vl.length; i++) {
				if (vl[i] != null) {
					ParkingGarageCarnoLog parkingGarageCarnoLog = vl[i];
					parkingGarageCarnoLog.setCreateTime(now);
					parkingGarageCarnoLog.setIntime(vo.getIntime());
					parkingGarageCarnoLogService.save(parkingGarageCarnoLog);

					Map<String, Object> params = new HashMap<String, Object>();
					params.put("isOnline", parkingGarageCarnoLog.getStatus());
					params.put("carNo", parkingGarageCarnoLog.getCarNo());
					params.put("intime", parkingGarageCarnoLog.getIntime());
					params.put("name", parkingGarageCarnoLog.getParkingName());
					this.parkingGarageService.updateParkingGarageByName(params);
				}
			}
		}

		String jsonstr = "\"true\"";
		return this.ajaxDoneSuccess(this.getMessage("httpservices.service_success"), jsonstr);
	}

	/**
	 * 批量上报车位有车状态 *
	 * 
	 * @return
	 */
	@RequestMapping(value = "garage/campus")
	public ModelAndView service4(@Valid GarageIncarForm vo, HttpServletRequest request) {
		if (vo.getCarnologs() != null) {
			ParkingGarageCarnoLog[] vl = vo.getCarnologs();
			Date now = new Date();
			for (int i = 0; i < vl.length; i++) {
				if (vl[i] != null) {
					ParkingGarageCarnoLog parkingGarageCarnoLog = vl[i];
					parkingGarageCarnoLog.setCreateTime(now);
					parkingGarageCarnoLog.setIntime(vo.getIntime());
					parkingGarageCarnoLogService.save(parkingGarageCarnoLog);

					Map<String, Object> params = new HashMap<String, Object>();
					params.put("isOnline", parkingGarageCarnoLog.getStatus());
					params.put("carNo", parkingGarageCarnoLog.getCarNo());
					params.put("intime", parkingGarageCarnoLog.getIntime());
					this.parkingGarageService.updateParkingGarageByName(params);
				}
			}
		}

		String jsonstr = "\"true\"";
		return this.ajaxDoneSuccess(this.getMessage("httpservices.service_success"), jsonstr);
	}

	/**
	 * 车位解锁 *
	 * 
	 * @return
	 */
	@RequestMapping(value = "jiesuo/locked")
	public ModelAndView service4(@RequestParam(required = true) Long parkingGarageId, HttpServletRequest request) {
		String jsonstr = "\"true\"";

		Map<String, Object> searchParams = this.getSearchRequest();
		searchParams.put("EQ_parkingGarage", parkingGarageId);
		List<ParkingLock> vl = this.parkingLockService.findList(searchParams);

		if (vl.isEmpty()) {
			return this.ajaxDoneSuccess("没有匹配的地锁", jsonstr);
		}

		ParkingLock vm = vl.get(0);

		ParkingGarage parkingGarage = vm.getParkingGarage();

		Date now = new Date();
		parkingGarage.setIsOnline(Boolean.TRUE);
		parkingGarage.setIntime(now);
		parkingGarageService.save(parkingGarage);

		ParkingGarageCarnoLog parkingGarageCarnoLog = new ParkingGarageCarnoLog();
		parkingGarageCarnoLog.setParkingGarage(parkingGarage);
		parkingGarageCarnoLog.setCreateTime(now);
		parkingGarageCarnoLog.setIntime(now);
		parkingGarageCarnoLog.setParkingName(parkingGarage.getName());

		parkingGarageCarnoLogService.save(parkingGarageCarnoLog);

		this.parkingLockService.reverse(new Long[] { vm.getId() }, "02", this.getCurrentShiroUser() == null ? null : this.getCurrentShiroUser().id, ParkingLockOperationEvent.SOURCETYPE_PC);

		return this.ajaxDoneSuccess(this.getMessage("httpservices.service_success"), jsonstr);
	}

	/**
	 * 车位上锁 *
	 * 
	 * @return
	 */
	@RequestMapping(value = "shangsuo/locked")
	public ModelAndView service8(@RequestParam(required = true) Long parkingGarageId, HttpServletRequest request) {
		String jsonstr = "\"false\"";

		Map<String, Object> searchParams = this.getSearchRequest();
		searchParams.put("EQ_parkingGarage", parkingGarageId);
		List<ParkingLock> vl = this.parkingLockService.findList(searchParams);

		if (vl.isEmpty()) {
			return this.ajaxDoneSuccess("没有匹配的地锁", jsonstr);
		}

		ParkingLock vm = vl.get(0);

		ParkingGarage parkingGarage = vm.getParkingGarage();

		Date now = new Date();
		parkingGarage.setIsOnline(Boolean.TRUE);
		parkingGarage.setIntime(now);
		parkingGarageService.save(parkingGarage);

		ParkingGarageCarnoLog parkingGarageCarnoLog = new ParkingGarageCarnoLog();
		parkingGarageCarnoLog.setParkingGarage(parkingGarage);
		parkingGarageCarnoLog.setCreateTime(now);
		parkingGarageCarnoLog.setIntime(now);
		parkingGarageCarnoLog.setParkingName(parkingGarage.getName());

		parkingGarageCarnoLogService.save(parkingGarageCarnoLog);

		this.parkingLockService.reverse(new Long[] { vm.getId() }, "01", this.getCurrentShiroUser() == null ? null : this.getCurrentShiroUser().id, ParkingLockOperationEvent.SOURCETYPE_PC);

		return this.ajaxDoneSuccess(this.getMessage("httpservices.service_success"), jsonstr);
	}

	/**
	 * 地锁事件上报服务 *
	 * 
	 * @return
	 */
	@RequestMapping(value = "locked/event/new")
	public ModelAndView service5(
			@RequestParam(required = true) String lockNum,
			@RequestParam(required = true) int eventType,
			@RequestParam(required = true) int state,
			@RequestParam(required = true) int mcOpen,
			@RequestParam(required = true) int lockArea,
			@RequestParam(required = false) Date reportedTime,
			HttpServletRequest request) {

		Date curTime=new Date();
		
		ParkingLockEventLog parkingLockEventLog = new ParkingLockEventLog();
		parkingLockEventLog.setCreateTime(curTime);
		parkingLockEventLog.setEventType(eventType);
		parkingLockEventLog.setLockNum(lockNum);
		parkingLockEventLog.setState(state);
		if (null != reportedTime) {
			parkingLockEventLog.setReportedTime(reportedTime);
		} else {
			parkingLockEventLog.setReportedTime(curTime);
		}

		parkingLockEventLog.setMcOpen(mcOpen);
		parkingLockEventLog.setSourceType(3);
		parkingLockEventLog.setDeviceNum(lockNum);
		parkingLockEventLog.setIpAddress(lockArea+"");
		
		
		//查询地锁信息
		Map<String, Object> orderMap = new HashMap<String, Object>();
        orderMap.put("EQ_ipAddress", lockArea);
        orderMap.put("EQ_lockNum", lockNum);
//		ParkingLock parkingLock = parkingLockService.findUniqueBy("lockNum", lockNum);
        List<ParkingLock> parkingLocks =parkingLockService.findList(orderMap);
        
		ParkingLock parkingLock = null;
		boolean isSave=false;
		if(eventType!=34){
			isSave=true;
		}
		if(parkingLocks!=null&&parkingLocks.size()>0&&isSave){
			parkingLock = parkingLocks.get(0);
			
			//是否保存日志
			boolean flag=parkingLock.getIsOpen()==parkingLockEventLog.getIsOpen()
						&&parkingLock.getIsCarOn()==parkingLockEventLog.getIsCarOn()
						&&parkingLock.getIsForeverOpenClose()==parkingLockEventLog.getIsForeverOpenClose()
						&&parkingLockEventLog.getMcOpen().equals(parkingLock.getMcOpen())
						&&parkingLock.getPower()!=null&&parkingLockEventLog.getPower()==parkingLock.getPower().doubleValue();
			if((flag&&eventType==68&&parkingLock.getIsOnline()==false)
					||(flag&&eventType==51&&parkingLock.getIsOnline()==true)
					||(flag&&eventType==49)){
				isSave=false;
			}
			
			Boolean isOnline=false;
			Date onlineTime=null;
			Date carOnTime=null;
			
			//下线
			if(eventType==68){
			}
			//上线
			else if(eventType==51){
				onlineTime=parkingLockEventLog.getReportedTime();
				isOnline=true;
			}
			//心跳包
			else if(eventType==49){
				isOnline=true;
				if(parkingLock.getIsOnlineTime()==null){
					onlineTime=parkingLockEventLog.getReportedTime();
				}else{
					onlineTime=parkingLock.getIsOnlineTime();	
				}
			}
			//事件类型
			else if(eventType==85){
				isOnline=parkingLock.getIsOnline();
				onlineTime=parkingLock.getIsOnlineTime();
			}
			
			if(eventType!=68){
				if(parkingLockEventLog.getIsCarOn()){
					if(parkingLock.getIsCarOnTime()==null){
						carOnTime=parkingLockEventLog.getReportedTime();
					}else{
						carOnTime=parkingLock.getIsCarOnTime();
					}	
				}
				parkingLock.setIsCarOn(parkingLockEventLog.getIsCarOn());
				parkingLock.setIsForeverOpenClose(parkingLockEventLog.getIsForeverOpenClose());
				parkingLock.setIsOpen(parkingLockEventLog.getIsOpen());
				parkingLock.setIsCarOnTime(carOnTime);
				parkingLock.setPower(parkingLockEventLog.getPower());
				parkingLock.setMcOpen(parkingLockEventLog.getMcOpen());
			}
			parkingLock.setIsOnline(isOnline);
			parkingLock.setIsOnlineTime(onlineTime);
			parkingLock.setLogUpdateTime(curTime);
			this.parkingLockService.save(parkingLock);
		}
		parkingLockEventLog.setParkingLock(parkingLock);
		
		//保存地锁日志
		if(isSave){
			this.parkingLockEventLogService.save(parkingLockEventLog);	
		}	

		String jsonstr = "\"true\"";
		return this.ajaxDoneSuccess(this.getMessage("httpservices.service_success"), jsonstr);
	}

	/**
	 * 车辆出校接口 *
	 * 
	 * @return
	 */
	@RequestMapping(value = "order/outschool")
	public ModelAndView outSchool(@Valid ParkingOrder parkingOrder, @RequestParam(required = true) String outCameraIp, @RequestParam(required = true) String outPlateNo, @RequestParam(required = true) String outTime, @RequestParam(required = true) String outPicName, @RequestParam(required = true) Long outDoorId,

	HttpServletRequest request) {

		parkingOrder.setInCameraIp(outCameraIp);
		parkingOrder.setInPlateNo(outPlateNo);

		Passages passages = passagesService.getObjectById(outDoorId);

		parkingOrder.setOutDoor(passages);
		int resultInt = this.parkingOrderService.outSchool(parkingOrder);

		String jsonstr = "\"" + resultInt + "\"";
		return this.ajaxDoneSuccess(this.getMessage("httpservices.service_success"), jsonstr);
	}

}