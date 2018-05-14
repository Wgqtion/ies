/*
 * To change this license header, choose License Headers in Project Prope-rties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vsc.business.gerd.web.httpservices;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.vsc.business.gerd.entity.work.Org;
import com.vsc.business.gerd.entity.work.ParkingGarage;
import com.vsc.business.gerd.entity.work.ParkingLock;
import com.vsc.business.gerd.entity.work.ParkingLockOperationEvent;
import com.vsc.business.gerd.entity.work.ParkingLot;
import com.vsc.business.gerd.entity.work.UserOrder;
import com.vsc.business.gerd.entity.work.WeixinAttest;
import com.vsc.business.gerd.entity.work.WxCore;
import com.vsc.business.gerd.entity.work.WxOrder;
import com.vsc.business.gerd.entity.work.WxUser;
import com.vsc.business.gerd.entity.work.Yuding;
import com.vsc.business.gerd.entity.work.YudingSetting;
import com.vsc.business.gerd.entity.work.Yuyue;
import com.vsc.business.gerd.service.work.OrgService;
import com.vsc.business.gerd.service.work.ParkingGarageService;
import com.vsc.business.gerd.service.work.ParkingLockService;
import com.vsc.business.gerd.service.work.ParkingLotService;
import com.vsc.business.gerd.service.work.ParkingParamService;
import com.vsc.business.gerd.service.work.ReserveTimeService;
import com.vsc.business.gerd.service.work.UserOrderService;
import com.vsc.business.gerd.service.work.WxCoreService;
import com.vsc.business.gerd.service.work.WxOrderService;
import com.vsc.business.gerd.service.work.WxUserService;
import com.vsc.business.gerd.service.work.YudingService;
import com.vsc.business.gerd.service.work.YudingSettingService;
import com.vsc.constants.Constants;
import com.vsc.modules.entity.MessageException;
import com.vsc.util.JSONUtil;

/**
 * 微信小程序接口控制类
 * @author XiangXiaoLin
 *
 */
@Controller
@RequestMapping(value = Constants.SPT + WeixinController.PATH)
public class WeixinController extends HttpServiceBaseController {

	// 控制层 URL地址映射
	public static final String PATH = PATH_BASE + Constants.SPT + "weixin";

	// 视图地址映射
	public static final String V_PATH = V_PATH_BASE;
	public static final String V_PATH_INDEX = PATH_BASE + Constants.SPT + "weixin";

	// 预约设置
	@Autowired
	private YudingSettingService yudingSettingService;

	@Autowired
	private OrgService orgService;

	// 预约
	@Autowired
	private YudingService yudingService;

	// 车位
	@Autowired
	private ParkingGarageService parkingGarageService;

	// 地锁
	@Autowired
	private ParkingLockService parkingLockService;

	// 微信用户
	@Autowired
	private WxUserService wxUserService;

	// 停车场区
	@Autowired
	private ParkingLotService parkingLotService;

	// 用户订单
	@Autowired
	private UserOrderService userOrderService;
	
	@Autowired
	private ParkingParamService parkingParamService;
	
	@Autowired
	private ReserveTimeService reserveTimeService;
	
	@Autowired
	private WxCoreService wxCoreService;
	
	@Autowired
	private WxOrderService wxOrderService;

	static Map locks = new HashMap();
	static List lockKeys = new ArrayList();
	static {
		for (int i = 0; i < 10000; i++) {
			Object lockKey = new Object();
			lockKeys.add(lockKey);
			locks.put(lockKey, new Object());
		}
	}

	private String[] featureNames = new String[] { JSONUtil._Feature_WriteMapNullValue,
			JSONUtil._Feature_WriteNullListAsEmpty, JSONUtil._Feature_WriteNullStringAsEmpty,
			JSONUtil._Feature_WriteNullNumberAsZero, JSONUtil._Feature_WriteNullBooleanAsFalse,
			JSONUtil._Feature_PrettyFormat, JSONUtil._Feature_DisableCircularReferenceDetect };

	@RequestMapping(value = "")
	public String index(Model model) {
		return V_PATH_INDEX;
	}

	/**
	 * 登陆
	 */
	@RequestMapping(value = "/login")
	public ModelAndView weixinLogin(@RequestParam(required = true) String js_code) throws ParseException, IOException {
		String result = null;
		HttpGet httpGet = new HttpGet(
				"https://api.weixin.qq.com/sns/jscode2session?appid=wx7da35897ec0b8ac0&secret=80017a058ba9741f6c331ce3f2cd21b5&js_code="
						+ js_code + "&grant_type=authorization_code");
		HttpResponse httpResponse = new DefaultHttpClient().execute(httpGet);
		if (httpResponse.getStatusLine().getStatusCode() == 200) {
			HttpEntity httpEntity = httpResponse.getEntity();
			result = EntityUtils.toString(httpEntity);// 取出应答字符串
			// 一般来说都要删除多余的字符
			result.replaceAll("\r", "");// 去掉返回结果中的"\r"字符，否则会在结果字符串后面显示一个小方格
		}
		System.out.println(result);
		// 登录信息转对象
		WeixinAttest weixinAttest = JSONObject.parseObject(result, WeixinAttest.class);
		WxUser wxUser = null;
		try {
			wxUser = wxUserService.getByWeixinId(weixinAttest.getOpenid());
			if (wxUser == null || wxUser.getId() == null) {
				return this.ajaxDoneError("没有注册信息", result);
			}
		} catch (Exception e) {
			return this.ajaxDoneError("获取openId失败", result);
		}

		String[] isNotIgnoreFieldNames = {"weixinId"};
		String jsonstr = JSONUtil.toJSONString(wxUser, isNotIgnoreFieldNames, false, featureNames);
		return this.ajaxDoneSuccess("登陆成功", jsonstr);
	}

	/**
	 * 注册
	 * @throws Exception 
	 */
	@RequestMapping(value = "/register")
	public ModelAndView register(@RequestParam(required = true) String weixinId,
			@RequestParam(required = false) String name, @RequestParam(required = false) String carNumber,
			@RequestParam(required = false) String tel, @RequestParam(required = false) Integer sex,
			@RequestParam(required = false) String country, @RequestParam(required = false) String province,
			@RequestParam(required = false) String city){
		// 登录信息查询
		WxUser wxUser = wxUserService.getByWeixinId(weixinId);
		if (wxUser == null) {
			wxUser = new WxUser();
			wxUser.setWeixinId(weixinId);
			wxUser.setName(name);
			wxUser.setCarNo(carNumber);
			wxUser.setTelphone(tel);
			wxUser.setSex(sex);
			wxUser.setCountry(country);
			wxUser.setProvince(province);
			wxUser.setCity(city);
			wxUser.setCreateDate(new Date());
			try {
				wxUserService.save(wxUser);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			return this.ajaxDoneError("已有注册信息");
		}
		return this.ajaxDoneSuccess("注册成功");
	}

	/**
	 * 场区 根节点查询
	 * @throws Exception 
	 */
	@RequestMapping(value = "/parkinglot/findParkingLots")
	public ModelAndView findParkingLots(@RequestParam(required = true) String weixinId,
			Long parkingLotId){
		Set<ParkingLot> parkingLots=this.parkingLotService.findParkingLots(weixinId, parkingLotId);
		String[] isNotIgnoreFieldNames = { "id", "name","isLast", "itudeLong", "itudeLat", "garageNum", "surplusNum",
				"parkingLocks", "code", "name", "parkingGarage", "id", "name" };
		String jsonstr = JSONUtil.toJSONString(parkingLots, isNotIgnoreFieldNames, false, featureNames);
		return this.ajaxDoneSuccess(this.getMessage("httpservices.service_success"), jsonstr);
	}

	
	/**
	 * 预约 请求
	 * @throws Exception 
	 */
	@RequestMapping(value = "/reserve")
	public ModelAndView reserve(@RequestParam(required = true) String weixinId,
			@RequestParam(required = true) String parkingLockCode){
		return ReserveOrUnlocked(weixinId,parkingLockCode,1);
	}
	
	/**
	 * 解锁 请求
	 */
	@RequestMapping(value = "/unlock")
	public ModelAndView unlock(@RequestParam(required = true) String weixinId,
			@RequestParam(required = true) String parkingLockCode){
		return ReserveOrUnlocked(weixinId,parkingLockCode,2);
	}
	
	/**
	 * 预约或解锁
	 */
	private ModelAndView ReserveOrUnlocked(String weixinId, String parkingLockCode,int flag) {
		Object lockKey = lockKeys.get(parkingLockCode.hashCode() % lockKeys.size());
		Object lock = locks.get(lockKey);
		synchronized (lock) {
			WxCore wxCore=new WxCore();
			wxCore.setWeixinId(weixinId);
			wxCore.setParkingLockCode(parkingLockCode);
			wxCore.setStatus(1);
			wxCore.setStartTime(new Date());
			if (flag==1) {
				/*
				 * 预约逻辑
				 */
				wxCore.setType(Integer.valueOf(1));
				int status=-1;
				String message="预约失败";
				try {
					status = this.wxCoreService.reserve(wxCore);
					message=Constants.RESERVE_MESSAGE_STATUS[status];
				} catch (MessageException e) {
					message=e.getMessage();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return this.ajaxDoneSuccess(status,message,null);
			}else{
				/*
				 * 解锁逻辑
				 */
				wxCore.setType(Integer.valueOf(2));
				int status=-1;
				String message="解锁失败";
				try {
					status = this.wxCoreService.unlock(wxCore);
					message=Constants.UNLOCK_MESSAGE_STATUS[status];
				} catch (MessageException e) {
					message=e.getMessage();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return this.ajaxDoneSuccess(status,message,null);
			}
		}
	}
	
	/**
	 * 取消预约
	 */
	@RequestMapping(value = "/cancelReserve")
	public ModelAndView cancelReserve(@RequestParam(required = true) String weixinId){
		WxCore wxCore=new WxCore();
		wxCore.setWeixinId(weixinId);
		wxCore.setIsCancel(true);
		wxCore.setType(Integer.valueOf(1));
		int status=this.wxCoreService.cancelReserve(wxCore);
		return this.ajaxDoneSuccess(status,Constants.CANCEL_RESERVE_MESSAGE_STATUS[status],null);
	}
	
	/**
	 * 上锁请求
	 */
	@RequestMapping(value = "/lock")
	public ModelAndView lock(@RequestParam(required = true) String weixinId){
		WxCore wxCore=new WxCore();
		wxCore.setWeixinId(weixinId);
		wxCore.setType(Integer.valueOf(2));
		int status=this.wxCoreService.lock(wxCore);
		return this.ajaxDoneSuccess(status,Constants.LOCK_MESSAGE_STATUS[status],null);
	}
	
	/**
	 * 支付订单
	 */
	@RequestMapping(value = "/payOrder")
	public ModelAndView payOrder(@RequestParam(required = true) String weixinId){
		WxOrder wxOrder=new WxOrder();
		wxOrder.setWeixinId(weixinId);
		int status=this.wxOrderService.payOrder(wxOrder);
		return this.ajaxDoneSuccess(status,Constants.PAY_MESSAGE_STATUS[status],null);
	}
	
	/**
	 * 订单查询
	 * @throws Exception 
	 */
	@RequestMapping(value = "/order/info")
	public ModelAndView orderInfo(@RequestParam(required = true) String weixinId) throws Exception {

		// 订单查询
		Map<String, Object> orderMap = new HashMap<String, Object>();
		orderMap.put("EQ_wxUser.weixinId", weixinId);
		orderMap.put("NOTEQ_isDelete", new Integer(1));
		List<UserOrder> userOrderlList = userOrderService.findList(orderMap);
		//预约单查询
		List<Yuyue> yuyues = new ArrayList<Yuyue>();
		List<YudingSetting> vl = yudingSettingService.getAllList();
		List<Yuding> yudings = yudingService.findByWxUser(weixinId);
		
		if (userOrderlList.isEmpty()&&yudings.isEmpty()) {
			return this.ajaxDoneError("没有订单信息");
		}
		if(userOrderlList!=null&&userOrderlList.size()>0){
			String[] isNotIgnoreFieldNames = { "id", "createTime","isDelete", "parkingGarage","id","name", 
					"description"};
			String jsonstr = JSONUtil.toJSONString(userOrderlList, isNotIgnoreFieldNames, false, featureNames);
			return this.ajaxDoneSuccess(this.getMessage("httpservices.service_success"), jsonstr);
		}
		
		for (Yuding yuding : yudings) {
			Yuyue yuyue = new Yuyue();
			yuyue.setWxUserId(yuding.getWxUser().getId());
			yuyue.setOrderNumber(yuding.getId());
			yuyue.setYuyueTime(yuding.getYuyueTime());
			yuyue.setCarNumber(yuding.getCarNo());
			yuyue.setCreateTime(yuding.getCreateTime());
			yuyue.setParkingGarage(yuding.getParkingGarage());
			yuyue.setIsDelete(yuding.getIsDelete());
			yuyue.setIsEnabled(yuding.getIsEnabled());
			if (!vl.isEmpty()) {
				yuyue.setYudingSetting(vl.get(0));
			}
			yuyue.setShoufei(new Double(0));

			yuyues.add(yuyue);
		}
		String[] isNotIgnoreFieldNames = { "orderNumber","isDelete", "parkingGarage","id","name","yuyueTime", "createTime", "description"};
		String jsonstr = JSONUtil.toJSONString(yuyues, isNotIgnoreFieldNames, false, featureNames);
		return this.ajaxDoneSuccess(this.getMessage("httpservices.service_success"), jsonstr);
	}
	
	/**
	 * 预约车位 请求
	 * @throws Exception 
	 */
	@RequestMapping(value = "/yuyue/new")
	public ModelAndView yuyueNew(@RequestParam(required = true) String weixinId,
			@RequestParam(required = true) Long parkingLockId) throws Exception {
		return UnlockedOrYuyue(weixinId, parkingLockId, "Yuyue");
	}

	/**
	 * 车位解锁 请求
	 */
	@RequestMapping(value = "/parkinggarage/unlock")
	public ModelAndView parkinggarageUnlocked(@RequestParam(required = true) String weixinId,
			@RequestParam(required = true) Long parkingLockId) throws Exception {
		return UnlockedOrYuyue(weixinId, parkingLockId, "Unlocked");
	}

	/**
	 * 车位解锁或预约
	 * @throws Exception 
	 */
	private ModelAndView UnlockedOrYuyue(String weixinId, Long parkingId, String flag) throws Exception {
		Object lockKey = lockKeys.get(parkingId.hashCode() % lockKeys.size());
		Object lock = locks.get(lockKey);
		synchronized (lock) {
			if ("Unlocked".equals(flag)) {
				Calendar now = Calendar.getInstance();
				String jsonstr = "\"true\"";
				ParkingLock vl = this.parkingLockService.getByGarageId(parkingId);
				if(vl==null){
					return this.ajaxDoneError("无车位信息");
				}
				// 预约单查询
				List<Yuding> yudings = yudingService.findByWxUser(weixinId);
				if (yudings != null && !yudings.isEmpty()) {
					if (!parkingId.equals(yudings.get(0).getParkingGarage().getId())) {
						return this.ajaxDoneError("没有该车位解锁权限，您预定的车位为" + yudings.get(0).getParkingGarage().getId());
					}
				}

				Map<String, Object> searchYuding = new HashMap<String, Object>();
				searchYuding.put("EQ_parkingGarage.id", parkingId);
				searchYuding.put("NOTEQ_wxUser.weixinId", weixinId);
				searchYuding.put("EQ_isDelete", Boolean.FALSE);
				List<Yuding> yudingParking = this.yudingService.findList(searchYuding);
				if (yudingParking != null && !yudingParking.isEmpty()) {
					return this.ajaxDoneError("手速慢了,该车位被别人预约");
				}

				// TODO 没有地锁假开关
				// if (vl.isEmpty()) {
				// return this.ajaxDoneSuccess("没有匹配的地锁", jsonstr);
				// }
				Map<String, Object> orderUserMap = new HashMap<String, Object>();
				// 订单查询
				orderUserMap.put("EQ_wxUser.weixinId", weixinId);
				orderUserMap.put("NOTEQ_isDelete", new Integer(1));
				List<UserOrder> userOrderlList = userOrderService.findList(orderUserMap);
				if (userOrderlList != null && !userOrderlList.isEmpty()) {
					if (!parkingId.equals(userOrderlList.get(0).getParkingGarage().getId())) {
						return this
								.ajaxDoneError("没有该车位解锁权限，您的订单车位为" + userOrderlList.get(0).getParkingGarage().getId());
					}
				} else {
					Map<String, Object> orderParkingMap = new HashMap<String, Object>();
					// 订单查询
					orderParkingMap.put("EQ_parkingGarage.id", parkingId);
					orderParkingMap.put("EQ_isDelete", new Integer(0));
					List<UserOrder> orderParkingList = userOrderService.findList(orderParkingMap);
					for (UserOrder userOrder : orderParkingList) {
						if (userOrder != null && userOrder.getWxUser() != null
								&& !weixinId.equals(userOrder.getWxUser().getWeixinId())) {
							return this.ajaxDoneError("该车位已被占用");
						}
					}
					String message = locked(vl, "02", weixinId);
					if (message.length() > 0) {
						return this.ajaxDoneError(message.toString());
					}
					// 创建订单
					UserOrder userOrder = new UserOrder();
					userOrder.setWxUser(this.wxUserService.getByWeixinId(weixinId));
					userOrder.setCreateTime(now.getTime());
					userOrder.setIsDelete(0);
					ParkingGarage parkingGarage = this.parkingGarageService.getObjectById(parkingId);
					userOrder.setParkingGarage(parkingGarage);
					userOrderService.save(userOrder);
				}
				return this.ajaxDoneSuccess("解锁指令发送成功", jsonstr);
			} else if ("Yuyue".equals(flag)) {
				Yuding yuding = new Yuding();
				// 订单查询
				Map<String, Object> orderMap = new HashMap<String, Object>();
				orderMap.put("EQ_wxUser.weixinId", weixinId);
				orderMap.put("NOTEQ_isDelete", new Integer(1));
				List<UserOrder> userOrderlList = userOrderService.findList(orderMap);
				if (userOrderlList != null && !userOrderlList.isEmpty()) {
					return this.ajaxDoneError("已有订单，请先结束上一笔订单");
				}
				// 订单查询
				Map<String, Object> orderParking = new HashMap<String, Object>();
				orderParking.put("EQ_parkingGarage.id", parkingId);
				orderParking.put("EQ_isDelete", new Integer(0));
				List<UserOrder> orderParkingList = userOrderService.findList(orderParking);
				if (orderParkingList != null && !orderParkingList.isEmpty()) {
					return this.ajaxDoneError("手速慢了，车位被别人占用");
				}

				// 判断是否重复预约
				List<Yuding> isYuding = yudingService.findByWxUser(weixinId);
				if (isYuding != null && !isYuding.isEmpty()) {
					return this.ajaxDoneError("重复预约");
				}
				// 判断是否别人预约
				Map<String, Object> searchYuding = new HashMap<String, Object>();
				searchYuding.put("EQ_parkingGarage.id", parkingId);
				searchYuding.put("NOTEQ_wxUser.weixinId", weixinId);
				searchYuding.put("EQ_isDelete", Boolean.FALSE);
				List<Yuding> yudingParking = this.yudingService.findList(searchYuding);
				if (yudingParking != null && !yudingParking.isEmpty()) {
					return this.ajaxDoneError("手速慢了,该车位被别人预约");
				}
				ParkingGarage parkingGarage = this.parkingGarageService.getObjectById(parkingId);
				if (!parkingGarage.getIsEnabled()) {
					return this.ajaxDoneError("车位不可用");
				} else {
					List<YudingSetting> ys = yudingSettingService.getAllList();
					WxUser wxUser = wxUserService.getByWeixinId(weixinId);
					if (wxUser == null || wxUser.getId() == null) {
						return this.ajaxDoneError("用户信息有误");
					}
					if (ys.isEmpty()) {
						return this.ajaxDoneError("系统未开放预约功能");
					}

					YudingSetting vm;
					// 以上锁
					yuding.setIsLockedOk(Boolean.TRUE);
					vm = ys.get(0);
					// @TODO 还缺申请时间的判断逻辑
					Calendar now = Calendar.getInstance();
					Date nowDay = now.getTime();
					nowDay = DateUtils.setMilliseconds(nowDay, 0);
					nowDay = DateUtils.setSeconds(nowDay, 0);

					// 地锁上锁
					ParkingLock vl = this.parkingLockService.getByGarageId(parkingId);
					if (vl == null) {
						// return this.ajaxDoneError("没有匹配的地锁");
					} else {
						String message = locked(vl, "01", weixinId);
						if (message.length() > 0) {
							return this.ajaxDoneError(message.toString());
						}
					}

					yuding.setWxUser(wxUser);
					yuding.setLasttime(now.getTime().getTime());
					yuding.setCreateTime(now.getTime());
					yuding.setYuyueTime(nowDay);
					yuding.setLockedCost(vm.getLockedCost());
					yuding.setLockedHourCost(vm.getLockedHourCost());
					if ((yuding.getYuyueTime() != null) && (vm.getLockedMinutes() != null)) {
						yuding.setLockedMinutes(vm.getLockedMinutes());
						Date lockedStarttime = DateUtils.addMinutes(yuding.getYuyueTime(),
								-1 * yuding.getLockedMinutes());
						yuding.setLockedStartTime(lockedStarttime);
					}

					// 取出Shiro中的当前用户,不查询数据库
					// yuding.setUser(this.getCurrentUser());
					yuding.setParkingGarage(parkingGarage);
					yudingService.save(yuding);
				}

				return this.ajaxDoneSuccess("预约成功", "{\"orderNumber\":" + yuding.getId() + "}");
			}
			return null;
		}
	}

	/**
	 * 取消预约 
	 */
	@RequestMapping(value = "/yuyue/cancel")
	public ModelAndView yuyueCancel(@RequestParam(required = true) String weixinId,
			@RequestParam(required = true) String orderNumber) throws Exception {
		if ("undefined".equals(orderNumber)) {
			return this.ajaxDoneError("orderNumber:undefined");
		}
		Long on = Long.valueOf(orderNumber);
		Yuding yuding = yudingService.getObjectById(on);
		if(yuding==null){
			return this.ajaxDoneError("预约取消失败，没有找到预约信息");
		}
		if (weixinId != null && !weixinId.equals(yuding.getWxUser().getWeixinId())) {
			return this.ajaxDoneError("预约取消失败，没有权限");
		}

		if (yuding.getIsDelete()) {
			return this.ajaxDoneError("已被取消");
		}
		yuding.setIsDelete(true);
		yudingService.save(yuding);
		return this.ajaxDoneSuccess("成功取消");
	}

	/**
	 * 车位上锁
	 */
	@RequestMapping(value = "/parkinggarage/lock")
	public ModelAndView parkinggarageLocked(@RequestParam(required = true) String weixinId,
			@RequestParam(required = true) Long parkingId) throws Exception {
		String jsonstr = "\"false\"";

		Map<String, Object> orderMap = new HashMap<String, Object>();
		orderMap.put("EQ_wxUser.weixinId", weixinId);
		orderMap.put("NOTEQ_isDelete", new Integer(1));
		List<UserOrder> userOrderlList = userOrderService.findList(orderMap);
		if (userOrderlList != null && !userOrderlList.isEmpty()) {
			UserOrder userOrder = userOrderlList.get(0);
			if (!parkingId.equals(userOrder.getParkingGarage().getId())) {
				return this.ajaxDoneError("没有该车位上锁权限，您的订单车位为" + userOrder.getParkingGarage().getId());
			} else {
				ParkingLock vl = this.parkingLockService.getByGarageId(parkingId);
				// TODO 假上锁
				if (vl == null) {
					// return this.ajaxDoneError("没有匹配的地锁");
				} else {
					String message = locked(vl, "01", weixinId);
					if (message.length() > 0) {
						return this.ajaxDoneError(message.toString());
					}
				}
				userOrder.setIsDelete(2);
				userOrderService.save(userOrder);
				return this.ajaxDoneSuccess("上锁成功", jsonstr);
			}
		}
		return this.ajaxDoneError("上锁失败，没有权限");
	}

	/**
	 * 取消订单接口
	 */
	@RequestMapping(value = "/order/cancel")
	public ModelAndView orderCancel(@RequestParam(required = true) String weixinId,
			@RequestParam(required = false) Long orderId) throws Exception {
		Calendar now = Calendar.getInstance();
		Map<String, Object> orderMap = new HashMap<String, Object>();
		orderMap.put("EQ_wxUser.weixinId", weixinId);
		orderMap.put("NOTEQ_isDelete", new Integer(1));
		List<UserOrder> userOrderlList = userOrderService.findList(orderMap);
		if (userOrderlList != null && !userOrderlList.isEmpty()) {
			UserOrder userOrder = userOrderlList.get(0);
			// 判断选择订单是否合理
			if (orderId != null && !orderId.equals(userOrder.getId())) {
				return this.ajaxDoneError("您应取消订单" + userOrder.getId());
			}
			// 设置订单取消时间
			userOrder.setOutTime(now.getTime());
			// 设置订单状态
			userOrder.setIsDelete(1);
			userOrderService.save(userOrder);
			return this.ajaxDoneSuccess("成功结束订单");
		}
		return this.ajaxDoneError("用户没有订单信息");
	}

	// 开关锁
	private String locked(ParkingLock vl, String state, String weixinId) {
		return this.parkingLockService.reverse(new Long[] { vl.getId() }, state, weixinId,
				ParkingLockOperationEvent.SOURCETYPE_PHONE);
	}

	/**
	 * 权限码查询
	 */
	@RequestMapping(value = "/org/find")
	public ModelAndView orgFind(@RequestParam(required = true) String weixinId) throws ParseException {
		Map<String, Object> orgParams = Maps.newHashMap();
		orgParams.put("EQ_users.weixinId", weixinId);
		List<Org> orgs = this.orgService.findList(orgParams);
		String[] isNotIgnoreFieldNames = { "id", "name", "code" };
		String jsonstr = JSONUtil.toJSONString(orgs, isNotIgnoreFieldNames, false, featureNames);
		return this.ajaxDoneSuccess(this.getMessage("httpservices.service_success"), jsonstr);
	}

	/**
	 * 添加权限码
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/org/add")
	public ModelAndView orgAdd(@RequestParam(required = true) String weixinId, @RequestParam(required = true) String code)
			throws Exception {
		Org org = this.orgService.getByCode(code);
		if (org == null) {
			return this.ajaxDoneError("没有权限信息");
		} else {
			WxUser wxUser = this.wxUserService.getByWeixinId(weixinId);
			if (wxUser == null) {
				return this.ajaxDoneError("没有用户信息");
			}
			if (!org.getUsers().contains(wxUser))
				org.getUsers().add(wxUser);
			this.orgService.save(org);
			return this.ajaxDoneSuccess("添加成功");
		}
	}

	/**
	 * 删除权限码
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/org/delete")
	public ModelAndView orgDelete(@RequestParam(required = true) String weixinId,
			@RequestParam(required = true) String code) throws Exception {
		WxUser wxUser = this.wxUserService.getByWeixinId(weixinId);
		if (wxUser == null) {
			return this.ajaxDoneError("没有用户信息");
		}
		Org org = this.orgService.getByCode(code);
		if (org == null) {
			return this.ajaxDoneError("没有权限信息");
		}
		org.getUsers().remove(wxUser);
		this.orgService.save(org);
		return this.ajaxDoneSuccess("删除成功");
	}
}
