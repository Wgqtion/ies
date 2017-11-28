package com.vsc.business.gerd.web.httpservices;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Maps;
import com.vsc.business.core.entity.security.User;
import com.vsc.business.gerd.entity.work.Campus;
import com.vsc.business.gerd.entity.work.ParkingGarage;
import com.vsc.business.gerd.entity.work.ParkingLock;
import com.vsc.business.gerd.entity.work.ParkingLot;
import com.vsc.business.gerd.entity.work.ParkingLotArea;
import com.vsc.business.gerd.entity.work.Passages;
import com.vsc.business.gerd.entity.work.RoutePathLog;
import com.vsc.business.gerd.entity.work.Yuding;
import com.vsc.business.gerd.entity.work.YudingSetting;
import com.vsc.business.gerd.service.work.AccessApplyService;
import com.vsc.business.gerd.service.work.CampusService;
import com.vsc.business.gerd.service.work.ParkingGarageService;
import com.vsc.business.gerd.service.work.ParkingLockService;
import com.vsc.business.gerd.service.work.ParkingLotAreaService;
import com.vsc.business.gerd.service.work.ParkingLotService;
import com.vsc.business.gerd.service.work.PassagesService;
import com.vsc.business.gerd.service.work.RoutePathLogService;
import com.vsc.business.gerd.service.work.YudingService;
import com.vsc.business.gerd.service.work.YudingSettingService;
import com.vsc.constants.Constants;
import com.vsc.modules.entity.MapBean;
import com.vsc.util.HttpRequestUtil;
import com.vsc.util.JSONUtil;

/**
 * 提供给客户端调用的接口控制类
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping(value = Constants.SPT + PhoneController.PATH)
public class PhoneController extends HttpServiceBaseController {
	
	private static Logger logger = Logger.getLogger(PhoneController.class);
	
	// 控制层 URL地址映射
	public static final String PATH = PATH_BASE + Constants.SPT + "phone";
	// 视图地址映射
	public static final String V_PATH = V_PATH_BASE;
	public static final String V_PATH_INDEX = PATH_BASE + Constants.SPT + "phone";

	@Autowired
	private ParkingLotService parkingLotService;
	@Autowired
	private CampusService campusService;
	@Autowired
	private ParkingLotAreaService parkingLotAreaService;
	@Autowired
	private ParkingGarageService parkingGarageService;

	@Autowired
	private YudingSettingService yudingSettingService;

	@Autowired
	private YudingService yudingService;
	@Autowired
	private PassagesService passagesService;
	
	@Autowired
	private AccessApplyService accessApplyService;
	@Autowired
	private RoutePathLogService routePathLogService;
	@Autowired
	private ParkingLockService parkingLockService;
	private String[] featureNames = new String[] { JSONUtil._Feature_WriteMapNullValue,
			JSONUtil._Feature_WriteNullListAsEmpty, JSONUtil._Feature_WriteNullStringAsEmpty,
			JSONUtil._Feature_WriteNullNumberAsZero, JSONUtil._Feature_WriteNullBooleanAsFalse,
			JSONUtil._Feature_PrettyFormat, JSONUtil._Feature_DisableCircularReferenceDetect };

	@RequestMapping(value = "")
	public String index(Model model) {
		return V_PATH_INDEX;
	}

	// 会员登录接口
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public ModelAndView service1(@RequestParam(required = true) String username,
			@RequestParam(required = true) String password,
			@RequestParam(required = false, defaultValue = "false") boolean rememberMe, HttpServletRequest request) {
		String host = request.getRemoteHost();

		AuthenticationToken token = new UsernamePasswordToken(username, password, rememberMe, host);
		try {
			Subject subject = SecurityUtils.getSubject();
			subject.login(token);

			User user = this.getCurrentUser();
			String[] isNotIgnoreFieldNames = { "loginName", "name", "isEnabled" };

			String json = JSONUtil.toJSONString(user, isNotIgnoreFieldNames, false,
					new String[] { JSONUtil._Feature_PrettyFormat });

			return this.ajaxDoneSuccess("登录成功", json);
		} catch (AuthenticationException e) {
			return this.ajaxDoneError("登录失败,请检查登录名与密码是否有误", "{}");
		}
	}

	/**
	 * http://localhost:8080/tjtc/httpservices/phone 获取所有校区的接口 *
	 * 
	 * @return
	 */
	@RequestMapping(value = "parkinglot/find", method = RequestMethod.POST)
	public ModelAndView service2(Model model, HttpServletRequest request) {
		Map<String, Object> searchParams = this.getSearchRequest();
		searchParams.put("EQ_isEnabled", true);

		List<ParkingLot> vl = this.parkingLotService.findList(searchParams);

		String[] isNotIgnoreFieldNames = { "id", "name", "baiduLatitudeLng", "baiduLatitudeLat", "carNumber",
				"canGetCard" };

		String jsonstr = JSONUtil.toJSONString(vl, isNotIgnoreFieldNames, false, featureNames);
		return this.ajaxDoneSuccess(this.getMessage("httpservices.service_success"), jsonstr);
	}

	/**
	 * 获取所有校门的接口 *
	 * 
	 * @return
	 */
	@RequestMapping(value = "campus/find", method = RequestMethod.POST)
	public ModelAndView service3(Model model, HttpServletRequest request) {
		Map<String, Object> searchParams = this.getSearchRequest();
		searchParams.put("EQ_isEnabled", true);

		List<Campus> vl = this.campusService.findList(searchParams);

		String[] isNotIgnoreFieldNames = { "id", "name", "parkingLot", "remark" };

		String jsonstr = JSONUtil.toJSONString(vl, isNotIgnoreFieldNames, false, featureNames);
		return this.ajaxDoneSuccess(this.getMessage("httpservices.service_success"), jsonstr);
	}

	/**
	 * 获取所有停车片区的接口 *
	 * 
	 * @return
	 */
	@RequestMapping(value = "parkinglotarea/find", method = RequestMethod.POST)
	public ModelAndView service4(Model model, HttpServletRequest request,
			@RequestParam(required = false, defaultValue = "false") boolean hasChildren) {
		Map<String, Object> searchParams = this.getSearchRequest();
		searchParams.put("EQ_isEnabled", true);

		List<ParkingLotArea> vl = this.parkingLotAreaService.findList(searchParams);

		String[] isNotIgnoreFieldNames = { "id", "code", "name", "baiduLatitudeLng", "baiduLatitudeLat", "description",
				"carNumber" };

		if (hasChildren) {
			isNotIgnoreFieldNames = ArrayUtils.add(isNotIgnoreFieldNames, "children");
		}

		String jsonstr = JSONUtil.toJSONString(vl, isNotIgnoreFieldNames, false, featureNames);
		return this.ajaxDoneSuccess(this.getMessage("httpservices.service_success"), jsonstr);
	}

	/**
	 * 获取所有车位的接口 *
	 * 
	 * @return
	 */
	@RequestMapping(value = "parkinggarage/find", method = RequestMethod.POST)
	public ModelAndView service5(Model model, HttpServletRequest request) {
		Map<String, Object> searchParams = this.getSearchRequest();
		searchParams.put("EQ_isEnabled", true);

		List<ParkingGarage> vl = this.parkingGarageService.findList(searchParams);

		String[] isNotIgnoreFieldNames = { "id", "name", "ipAddress","parkingLotArea", "description", "garageType","xcoordinate","ycoordinate","isOnline" };

		String jsonstr = JSONUtil.toJSONString(vl, isNotIgnoreFieldNames, false, featureNames);
		return this.ajaxDoneSuccess(this.getMessage("httpservices.service_success"), jsonstr);
	}

	/**
	 * 获取所有余车的接口 *
	 * 
	 * @return
	 */
	@RequestMapping(value = "remainingcar/find", method = RequestMethod.POST)
	public ModelAndView service6(Model model, HttpServletRequest request) {
		Map<String, Object> searchParams = this.getSearchRequest();
		searchParams.put("EQ_isEnabled", true);
		List<ParkingLotArea> vl = this.parkingLotAreaService.findList(searchParams);

		Map<String, Object> searchParamsCar = Maps.newHashMap();
		for (ParkingLotArea parkingLotArea : vl) {
			searchParamsCar.put("id", parkingLotArea.getId());
			List<MapBean<String, Object>> vlnum = this.parkingGarageService.findIbatisQuery("t_parking_garage.num",
					searchParamsCar);
			if (!vlnum.isEmpty()) {

				parkingLotArea.setFreeCarNum(MapUtils.getInteger(vlnum.get(0), "num"));
			}

		}

		String[] isNotIgnoreFieldNames = { "id", "code", "name", "carNumber", "freeCarNum" };

		String jsonstr = JSONUtil.toJSONString(vl, isNotIgnoreFieldNames, false, featureNames);
		return this.ajaxDoneSuccess(this.getMessage("httpservices.service_success"), jsonstr);
	}

	/**
	 * 获取当前可以申请预约的时间
	 * 
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "yuyue/nowtime", method = RequestMethod.POST)
	public ModelAndView service7(Model model, HttpServletRequest request) throws ParseException {

		List<YudingSetting> vl = yudingSettingService.getAllList();

		if (vl.isEmpty()) {
			return this.ajaxDoneSuccess(this.getMessage("httpservices.service_success") + ":当前无可预约时间", "{\"canLocked \": false,\"starttime\":\"\",\"endTime\":\"\"}");
		}
		YudingSetting vm = new YudingSetting();
		vm = vl.get(0);
		Calendar now=Calendar.getInstance();
		Date nowDay = now.getTime();
		nowDay=DateUtils.setMilliseconds(nowDay, 0);
		nowDay=DateUtils.setSeconds(nowDay, 0);
		Date nowStart=DateUtils.addMinutes(nowDay, vm.getStartAddMinutes());
		Date nowEnd=DateUtils.addMinutes(nowDay, vm.getEndAddMinutes());
		String nowString=DateFormatUtils.format(nowDay, "yyyy-MM-dd");
		int week=Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
		
		
		Date dayStart=DateUtils.parseDate(nowString+" "+vm.getWeekStarttime(week), "yyyy-MM-dd HH:mm");
		Date dayEnd=DateUtils.parseDate(nowString+" "+vm.getWeekEndtime(week), "yyyy-MM-dd HH:mm");
		
		Date lockedStart=nowStart.getTime()>dayStart.getTime()?nowStart:dayStart;
		Date lockedEnd=nowEnd.getTime()<dayEnd.getTime()?nowEnd:dayEnd;
		
		if (lockedStart.getTime()>lockedEnd.getTime()) {
			return this.ajaxDoneSuccess(this.getMessage("httpservices.service_success") + ":当前无可预约时间", "{\"canLocked \": false,\"starttime\":\"\",\"endTime\":\"\"}");
		}

		String jsonstr="{\"canLocked \": true,\"starttime\":\""+DateFormatUtils.format(lockedStart, "yyyy-MM-dd HH:mm")+"\",\"endTime\":\""+DateFormatUtils.format(lockedEnd, "yyyy-MM-dd HH:mm")+"\"}";
		return this.ajaxDoneSuccess(this.getMessage("httpservices.service_success"), jsonstr);
	}

	/**
	 * 预约申请
	 * 
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "yuyue/locked", method = RequestMethod.POST)
	public ModelAndView service8(
			@RequestParam(required = true) Date yuyueTime,
			@RequestParam(required = true) Long parkingLotAreaId, 
			@RequestParam(required = true) String carNo,
			HttpServletRequest request) throws ParseException {
		ParkingLotArea parkingLotArea = this.parkingLotAreaService.getObjectById(parkingLotAreaId);
		/**开发所有区域都可以预约，原自子节点限制取消
		if (!parkingLotArea.getChildren().isEmpty()) {
			return this.ajaxDoneSuccess(this.getMessage("httpservices.service_success") + ":必须预约无子节点区域", "false");
		}**/
		
	
		
		

		List<YudingSetting> vl = yudingSettingService.getAllList();

		if (vl.isEmpty()) {
			return this.ajaxDoneSuccess(this.getMessage("httpservices.service_success") + ":系统未开放预约功能", "false");
		}
		YudingSetting vm = new YudingSetting();
		vm = vl.get(0);
		// @TODO 还缺申请时间的判断逻辑
		Calendar now=Calendar.getInstance();
		Date nowDay = now.getTime();
		nowDay=DateUtils.setMilliseconds(nowDay, 0);
		nowDay=DateUtils.setSeconds(nowDay, 0);
		Date nowStart=DateUtils.addMinutes(nowDay, vm.getStartAddMinutes());
		Date nowEnd=DateUtils.addMinutes(nowDay, vm.getEndAddMinutes());
		String nowString=DateFormatUtils.format(nowDay, "yyyy-MM-dd");
		int week=Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
		
		
		Date dayStart=DateUtils.parseDate(nowString+" "+vm.getWeekStarttime(week), "yyyy-MM-dd HH:mm");
		Date dayEnd=DateUtils.parseDate(nowString+" "+vm.getWeekEndtime(week), "yyyy-MM-dd HH:mm");
		
		Date lockedStart=nowStart.getTime()>dayStart.getTime()?nowStart:dayStart;
		Date lockedEnd=nowEnd.getTime()<dayEnd.getTime()?nowEnd:dayEnd;
		
		if (!((lockedStart.getTime()<=yuyueTime.getTime())&&  (lockedEnd.getTime()>=yuyueTime.getTime()))) {
			return this.ajaxDoneSuccess(this.getMessage("httpservices.service_success") + ":申请时间不允许预约", "false");
		}
		

	 
	 
		Yuding yuding = new Yuding();
		yuding.setLasttime(now.getTime().getTime());
		yuding.setCreateTime(now.getTime());
		yuding.setYuyueTime(yuyueTime);
		yuding.setCarNo(carNo);
		yuding.setLockedCost(vm.getLockedCost());
		yuding.setLockedHourCost(vm.getLockedHourCost());
		if ((yuding.getYuyueTime() != null) && (vm.getLockedMinutes() != null)) {
			yuding.setLockedMinutes(vm.getLockedMinutes());
			Date lockedStarttime = DateUtils.addMinutes(yuding.getYuyueTime(), -1 * yuding.getLockedMinutes());
			yuding.setLockedStartTime(lockedStarttime);
		}
		yuding.setUser(this.getCurrentUser());
		yuding.setParkingLotArea(parkingLotArea);
	 
		
		
		
		
		// 预约成功修改车位信息-将是否预约修改为1,预约车牌号添加上
		Map<String, Object> params = Maps.newHashMap();
		// 加入在区域下随机抽取一个车位的方法
		Map<String, Object> searchParamsCar = Maps.newHashMap();
		searchParamsCar.put("fullIndexName", parkingLotArea.getFullIndexName());
		List<MapBean<String, Object>> vlnum = this.parkingGarageService.findIbatisQuery("queryParkingGarage.select", searchParamsCar);
		if (!vlnum.isEmpty()) {
			ParkingGarage parkingGarage = this.parkingGarageService.getObjectById(MapUtils.getLong(vlnum.get(0), "id"));
			yuding.setParkingGarage(parkingGarage);// 设置随机的车位至预约单
			params.put("id", parkingGarage.getId());
		}

		yudingService.save(yuding);
		params.put("isYuDing", Boolean.TRUE);
		params.put("yudingCarNo", carNo);
		
		this.parkingGarageService.updateParkingGarageYuding(params);
		
		//String[] isNotIgnoreFieldNames = { "id", "code", "name", "carNumber", "freeCarNum" };
		//String jsonstr = JSONUtil.toJSONString(null, isNotIgnoreFieldNames, false, featureNames);
		//return this.ajaxDoneSuccess(this.getMessage("httpservices.service_success"), "true");
		
		String[] isNotIgnoreFieldNames = { "id","carNo","parkingGarage","name","yuyueTime","lockedMinutes","lockedStartTime","cardInfo","cardNo"};

		String jsonstr = JSONUtil.toJSONString(yuding, isNotIgnoreFieldNames, false, featureNames);
		
		return this.ajaxDoneSuccess(this.getMessage("httpservices.service_success"), jsonstr);
	}
	
	
	/**
	 * 解锁车位
	 * 
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "jiesuo/locked", method = RequestMethod.POST)
	public ModelAndView service9(
			@RequestParam(required = true) String parkingGarageNum,
			HttpServletRequest request) throws ParseException {
		//处理解锁车位业务
		return this.ajaxDoneSuccess(this.getMessage("httpservices.service_success"), "true");
	}
	
	/**
	 * 开始路径服务
	 * 
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "start/pathService", method = RequestMethod.POST)
	public ModelAndView service10(
			@RequestParam(required = true) String paramInput,//车辆id或者车牌号
			HttpServletRequest request)  {
	 
		RoutePathLog vm=new RoutePathLog();
		vm.setCreateTime(new Date());
		vm.setLogContent(paramInput);
		vm.setSubtype("1"); 
		routePathLogService.save(vm);
		
		return this.ajaxDoneSuccess(this.getMessage("httpservices.service_success"), "true");
	}
	
	/**
	 * 
	 * @param request
	 * @param paramInput:
	 * @return
	 * @throws ParseException
	 */
	/**
	 * 结束路径服务
	 * @param request
	 * @param paramType:0/1/2
	 * @param paramInput:出入申请ID/车辆ID/车牌号
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "stop/pathService", method = RequestMethod.POST)
	public ModelAndView service11(
			HttpServletRequest request, 
			@RequestParam(required = false) String paramInput
		 
			) throws ParseException {
		RoutePathLog vm=new RoutePathLog();
		vm.setCreateTime(new Date());
		vm.setLogContent(paramInput);
		vm.setSubtype("2"); 
		routePathLogService.save(vm);		
		
		return this.ajaxDoneSuccess(this.getMessage("httpservices.service_success"), "true");
	}
	
	/**
	 * 下发出入口
	 * 
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "passages/find", method = RequestMethod.POST)
	public ModelAndView crk(Model model, HttpServletRequest request) {
		Map<String, Object> searchParams = this.getSearchRequest();
		searchParams.put("EQ_isEnabled", true);

		List<Passages> vl = this.passagesService.findList(searchParams);

		String[] isNotIgnoreFieldNames = { "id", "name", "xcoordinate", "ycoordinate" };

		String jsonstr = JSONUtil.toJSONString(vl, isNotIgnoreFieldNames, false, featureNames);
		return this.ajaxDoneSuccess(this.getMessage("httpservices.service_success"), jsonstr);
	} 
	
	
	/**
	 * 开始路径服务
	 * 
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "pathService/route")
	public ModelAndView pathServiceRoute(
			@RequestParam(required = true) String startpoint,//请求路径的起点的ID
			@RequestParam(required = true) String endpoint,//请求路径的终点的ID
			HttpServletRequest request) throws ParseException {
		String resultMsg = HttpRequestUtil.sendGet("route", "startpoint=" + startpoint + "&endpoint=" + endpoint);

		ModelAndView mav = new ModelAndView("noneDone");
		mav.addObject("callbackData", resultMsg);
		return mav;
	}
	
	/**
	 * 请求开始导航
	 * 
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "pathService/startfix")
	public ModelAndView pathServiceStartfix(
			@RequestParam(required = true) String deviceid,//定位终端ID
			@RequestParam(required = false) String pin,//PIN码
			@RequestParam(required = false) String plateno,//车牌号
			
			HttpServletRequest request) throws ParseException {
		String resultMsg = HttpRequestUtil.sendGet("startfix", "deviceid=" + deviceid + "&pin=" + pin + "&plateno=" + plateno);
		ModelAndView mav = new ModelAndView("noneDone");
		mav.addObject("callbackData", resultMsg);
		return mav;
		 
	}
	
	/**
	 * 结束路径服务
	 * 
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "pathService/endfix")
	public ModelAndView pathServiceEndfix(
			@RequestParam(required = true) String Pin,//Pin码
			HttpServletRequest request) throws ParseException {
		String resultMsg = HttpRequestUtil.sendGet("endfix", "pin=" + Pin);

		ModelAndView mav = new ModelAndView("noneDone");
		mav.addObject("callbackData", resultMsg);
		return mav;
	}
	
	
	/**
	 * 获取车位及其关联地锁信息 *
	 * 
	 * @return
	 */
	@RequestMapping(value = "parkinggaragelock")
	public ModelAndView service10(@RequestParam(required = true) Long parkingName,Model model, HttpServletRequest request) {
		String jsonstr = "{}";
		Map<String, Object> searchParams = this.getSearchRequest();
		searchParams.put("EQ_parkingGarage.name", parkingName);
		List<ParkingLock> vl = this.parkingLockService.findList(searchParams);

		if (vl.isEmpty()) {
			return this.ajaxDoneSuccess("没有匹配的地锁", jsonstr);
		}

		ParkingLock vm = vl.get(0);

		String[] isNotIgnoreFieldNames = { "id", "lockNum", "ipAddress","isCarOn","isOk","isOnline","isOpen","parkingGarage","name" };

		 jsonstr = JSONUtil.toJSONString(vm, isNotIgnoreFieldNames, false, featureNames);
		return this.ajaxDoneSuccess(this.getMessage("httpservices.service_success"), jsonstr);
	}
	
}