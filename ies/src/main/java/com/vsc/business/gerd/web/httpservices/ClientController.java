package com.vsc.business.gerd.web.httpservices;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.vsc.business.gerd.entity.validate.work.InParkingOrderValidate;
import com.vsc.business.gerd.entity.validate.work.OutParkingOrderValidate;
import com.vsc.business.gerd.entity.validate.work.PayParkingOrderValidate;
import com.vsc.business.gerd.entity.work.ParkingLock;
import com.vsc.business.gerd.entity.work.ParkingLockEventLog;
import com.vsc.business.gerd.entity.work.ParkingOrder;
import com.vsc.business.gerd.entity.work.ParkingVideo;
import com.vsc.business.gerd.service.work.ParkingLockEventLogService;
import com.vsc.business.gerd.service.work.ParkingLockService;
import com.vsc.business.gerd.service.work.ParkingOrderService;
import com.vsc.business.gerd.service.work.ParkingVideoService;
import com.vsc.constants.Constants;

/**
 * 客户端调用的接口控制类
 * @author XiangXiaoLin
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
	private ParkingLockService parkingLockService;
	@Autowired
	private ParkingLockEventLogService parkingLockEventLogService;
	@Autowired
	private ParkingVideoService parkingVideoService;

	@RequestMapping(value = "")
	public String index(Model model) {
		return V_PATH_INDEX;
	}

	/**
	 * 车辆进入接口 
	 * @throws Exception *
	 * 
	 */
	@RequestMapping(value = "order/parkingIn")
	public ModelAndView parkingIn(@Valid InParkingOrderValidate validate,BindingResult result,
			ParkingOrder parkingOrder,
			HttpServletRequest request) throws Exception {
		if(result.hasErrors()){
			StringBuffer sb=new StringBuffer();
            for (FieldError fieldError : result.getFieldErrors()) {
            	if(sb.length()>0){
            		sb.append(",");
            	}
                sb.append(fieldError.getDefaultMessage());
            }
            return this.ajaxDoneError(sb.toString());
        }
		parkingOrder.setCarNo(validate.getInPlateNo());
		this.parkingOrderService.inParkingOrder(parkingOrder);
		String jsonstr = "\"" + parkingOrder.getPayNumber() + "\"";
		return this.ajaxDoneSuccess(this.getMessage("httpservices.service_success"), jsonstr);
	}
	/**
	 * 车辆出去接口 *
	 * 
	 * @return
	 */
	@RequestMapping(value = "order/parkingOut")
	public ModelAndView parkingOut(@Valid OutParkingOrderValidate validate,BindingResult result,
			ParkingOrder parkingOrder,
			HttpServletRequest request) {
		if(result.hasErrors()){
			StringBuffer sb=new StringBuffer();
            for (FieldError fieldError : result.getFieldErrors()) {
            	if(sb.length()>0){
            		sb.append(",");
            	}
                sb.append(fieldError.getDefaultMessage());
            }
            return this.ajaxDoneError(sb.toString());
        }
		boolean flag=false;
		try {
			parkingOrder.setCarNo(validate.getOutPlateNo());
			this.parkingOrderService.outParkingOrder(parkingOrder);
			flag=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		String jsonstr = "\"" + flag + "\"";
		return this.ajaxDoneSuccess(this.getMessage("httpservices.service_success"), jsonstr);
	}
	
	/**
	 * 车辆支付接口 *
	 * 
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "order/parkingPay")
	public ModelAndView parkingPay(@Valid PayParkingOrderValidate validate,BindingResult result,
			ParkingOrder parkingOrder,
			HttpServletRequest request) throws Exception {
		if(result.hasErrors()){
			StringBuffer sb=new StringBuffer();
            for (FieldError fieldError : result.getFieldErrors()) {
            	if(sb.length()>0){
            		sb.append(",");
            	}
                sb.append(fieldError.getDefaultMessage());
            }
            return this.ajaxDoneError(sb.toString());
        }
		parkingOrder.setCarNo(validate.getPayPlateNo());
		this.parkingOrderService.payParkingOrder(parkingOrder);

		String jsonstr = "\"" + parkingOrder.getPayNumber() + "\"";
		return this.ajaxDoneSuccess(this.getMessage("httpservices.service_success"), jsonstr);
	}
	
	/**
	 * 全视频上报接口
	 * 
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "parking/parkingVideo")
	public ModelAndView service2(@Valid ParkingVideo parkingVideo, HttpServletRequest request) throws Exception {
		if (parkingVideo != null) {
			parkingVideo.setCreateTime(new Date());
			parkingVideoService.save(parkingVideo);
		}

		String jsonstr = "\"true\"";
		return this.ajaxDoneSuccess(this.getMessage("httpservices.service_success"), jsonstr);
	}
	
	/**
	 * 地锁上报接口
	 * 
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "locked/event/new")
	public ModelAndView service5(
			@RequestParam(required = true) String lockNum,
			@RequestParam(required = true) int eventType,
			@RequestParam(required = true) int state,
			@RequestParam(required = true) int mcOpen,
			@RequestParam(required = true) int lockArea,
			@RequestParam(required = false) Date reportedTime,
			HttpServletRequest request) throws Exception {

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
		
        ParkingLock parkingLock =parkingLockService.getByCode(lockArea+"",lockNum);
        
		boolean isSave=false;
		if(eventType!=34){
			isSave=true;
		}
		if(parkingLock!=null&&isSave){
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
			
			//下线
			if(eventType==68){
			}
			//上线
			else if(eventType==51){
				isOnline=true;
			}
			//心跳包
			else if(eventType==49){
				isOnline=true;
			}
			//事件类型
			else if(eventType==85){
				isOnline=parkingLock.getIsOnline();
			}
			
			if(eventType!=68){
				parkingLock.setIsCarOn(parkingLockEventLog.getIsCarOn());
				parkingLock.setIsForeverOpenClose(parkingLockEventLog.getIsForeverOpenClose());
				parkingLock.setIsOpen(parkingLockEventLog.getIsOpen());
				parkingLock.setPower(parkingLockEventLog.getPower());
				parkingLock.setMcOpen(parkingLockEventLog.getMcOpen());
			}
			parkingLock.setIsOnline(isOnline);
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


}