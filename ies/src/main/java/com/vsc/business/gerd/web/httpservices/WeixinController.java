/*
 * To change this license header, choose License Headers in Project Prope-rties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vsc.business.gerd.web.httpservices;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

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
import com.vsc.business.gerd.entity.work.ParkingLot;
import com.vsc.business.gerd.entity.work.ParkingParam;
import com.vsc.business.gerd.entity.work.WeixinAttest;
import com.vsc.business.gerd.entity.work.WxCore;
import com.vsc.business.gerd.entity.work.WxOrder;
import com.vsc.business.gerd.entity.work.WxUser;
import com.vsc.business.gerd.service.work.OrgService;
import com.vsc.business.gerd.service.work.ParkingLotService;
import com.vsc.business.gerd.service.work.ParkingParamService;
import com.vsc.business.gerd.service.work.WxCoreService;
import com.vsc.business.gerd.service.work.WxOrderService;
import com.vsc.business.gerd.service.work.WxUserService;
import com.vsc.constants.Constants;
import com.vsc.modules.entity.MessageException;
import com.vsc.util.CoreUtils;
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

	@Autowired
	private OrgService orgService;

	// 微信用户
	@Autowired
	private WxUserService wxUserService;

	// 停车场区
	@Autowired
	private ParkingLotService parkingLotService;
	
	@Autowired
	private WxCoreService wxCoreService;
	
	@Autowired
	private WxOrderService wxOrderService;
	
	@Autowired
	private ParkingParamService parkingParamService;

	static Map<Object,Object> locks = new HashMap<Object,Object>();
	static List<Object> lockKeys = new ArrayList<Object>();
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
				return this.ajaxDone(status,message,null);
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
				return this.ajaxDone(status,message,null);
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
		int status=this.wxCoreService.cancelReserve(wxCore,false);
		return this.ajaxDone(status,Constants.CANCEL_RESERVE_MESSAGE_STATUS[status],null);
	}
	
	/**
	 * 上锁请求
	 */
	@RequestMapping(value = "/lock")
	public ModelAndView lock(@RequestParam(required = true) String weixinId){
		WxCore wxCore=new WxCore();
		wxCore.setWeixinId(weixinId);
		wxCore.setType(Integer.valueOf(2));
		int status=-1;
		String message="上锁失败";
		try {
			status = this.wxCoreService.lock(wxCore);
			message=Constants.LOCK_MESSAGE_STATUS[status];
		} catch (MessageException e) {
			message=e.getMessage();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.ajaxDone(status,message,null);
	}
	
	/**
	 * 支付订单
	 */
	@RequestMapping(value = "/pay")
	public ModelAndView pay(@RequestParam(required = true) String weixinId){
		WxOrder wxOrder=new WxOrder();
		wxOrder.setWeixinId(weixinId);
		int status=this.wxOrderService.payOrder(wxOrder);
		return this.ajaxDone(status,Constants.PAY_MESSAGE_STATUS[status],null);
	}
	
	/**
	 * core查询，预约与停车使用中的core
	 */
	@RequestMapping(value = "/core")
	public ModelAndView core(@RequestParam(required = true) String weixinId) throws Exception {

		WxCore entity=new WxCore();
		entity.setWeixinId(weixinId);
		WxCore wxCore=this.wxCoreService.findBy(entity);
		if(wxCore==null){
			return this.ajaxDone(1, null, null);
		}
		Integer freeMin=0;
		Integer privilegeMin=0;
		ParkingParam parkingParam=parkingParamService.getByParkingLotCode(wxCore.getParkingLock().getParkingGarage().getParkingLotCode());
		if(parkingParam!=null){
			if(wxCore.getType()==1){
				freeMin=parkingParam.getFreeReserveMin();
				privilegeMin=parkingParam.getPrivilegeReserveMin();
				if(parkingParam.getReserveMin()!=null&&parkingParam.getReserveMin()>0){
					wxCore.setCancelTime(CoreUtils.addMin(wxCore.getStartTime(), parkingParam.getReserveMin()));	
				}
			}else if(wxCore.getType()==2){
				freeMin=parkingParam.getFreeParkingMin();
				privilegeMin=parkingParam.getPrivilegeParkingMin();
			}
		}
		if(freeMin!=null&&freeMin>0){
			wxCore.setFreeTime(CoreUtils.addMin(wxCore.getStartTime(), freeMin));		
		}
		if(privilegeMin!=null&&privilegeMin>0){
			wxCore.setStartFeeTime(CoreUtils.addMin(wxCore.getStartTime(), privilegeMin));
		}
		String[] isNotIgnoreFieldNames = {"type","typeStr","startFeeTime","freeTime","cancelTime", "parkingLockCode","startTime","parkingLock","parkingGarage","name"};
		String jsonstr = JSONUtil.toJSONString(wxCore, isNotIgnoreFieldNames, false, featureNames);
		return this.ajaxDone(0,this.getMessage("httpservices.service_success"), jsonstr);
	}
	
	/**
	 * 订单查询
	 * @throws Exception 
	 */
	@RequestMapping(value = "/order")
	public ModelAndView order(@RequestParam(required = true) String weixinId,String code) throws Exception {

		// 订单查询
		WxOrder wxOrder=this.wxOrderService.getByWeixinId(weixinId);
		if(code!=null){
			wxOrder=this.wxOrderService.getByCode(code);
		}
		if(wxOrder==null){
			return this.ajaxDone(1, "无订单信息", null);
		}
		String[] isNotIgnoreFieldNames = { "code","totalFee","wxCores","typeStr","isFreeStr","amount","startTime","endTime","parkingLock","parkingGarage","name"};
		String jsonstr = JSONUtil.toJSONString(wxOrder, isNotIgnoreFieldNames, false, featureNames);
		return this.ajaxDone(0,this.getMessage("httpservices.service_success"), jsonstr);
	}
	
	/**
	 * 二维码查询
	 */
	@RequestMapping(value = "/qrcode")
	public void qrcode(@RequestParam(required = true) String weixinId,String code,HttpServletResponse response) throws Exception {

		// 订单查询
		WxOrder wxOrder=null;
		if(!CoreUtils.isBlank(code)){
			wxOrder=this.wxOrderService.getByCode(code);
		}else{
			wxOrder=this.wxOrderService.getLastByWeixinId(weixinId);
		}
		if(wxOrder==null){
			return;
		}
		String fileName=wxOrder.getCode();
    	String path=wxOrder.getQrcodePath();
        File file=new File(path);  
        if(file.exists()){  
            //设置MIME类型  
            response.setContentType("application/octet-stream");              
            //或者为response.setContentType("application/x-msdownload");  
              
            //设置头信息,设置文件下载时的默认文件名，同时解决中文名乱码问题  
            response.addHeader("Content-disposition", "attachment;filename="+new String(fileName.getBytes(), "ISO-8859-1"));  
              
            InputStream inputStream=new FileInputStream(file);  
            ServletOutputStream outputStream=response.getOutputStream();  
            byte[] bs=new byte[1024];  
            while((inputStream.read(bs)>0)){  
                outputStream.write(bs);  
            }  
            outputStream.close();  
            inputStream.close();              
        }  
	}
	
	/**
	 * 历史订单查询
	 * @throws Exception 
	 */
	@RequestMapping(value = "/orderlist")
	public ModelAndView orderlist(@RequestParam(required = true) String weixinId) throws Exception {

		// 订单查询
		List<WxOrder> wxOrders=this.wxOrderService.getListByWeixinId(weixinId);
		if(wxOrders==null){
			return this.ajaxDone(1, null, null);
		}
		String[] isNotIgnoreFieldNames = { "code","createTime","payTime","totalFee","wxCores","type","typeStr","amount","startTime","endTime","parkingLock","parkingGarage","name"};
		String jsonstr = JSONUtil.toJSONString(wxOrders, isNotIgnoreFieldNames, false, featureNames);
		return this.ajaxDone(0,this.getMessage("httpservices.service_success"), jsonstr);
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
