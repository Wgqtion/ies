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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.MapUtils;
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
import com.vsc.business.gerd.entity.work.ParkingLotArea;
import com.vsc.business.gerd.entity.work.UserOrder;
import com.vsc.business.gerd.entity.work.WeixinAttest;
import com.vsc.business.gerd.entity.work.WxUser;
import com.vsc.business.gerd.entity.work.Yuding;
import com.vsc.business.gerd.entity.work.YudingSetting;
import com.vsc.business.gerd.entity.work.Yuyue;
import com.vsc.business.gerd.service.work.OrgService;
import com.vsc.business.gerd.service.work.ParkingGarageService;
import com.vsc.business.gerd.service.work.ParkingLockService;
import com.vsc.business.gerd.service.work.ParkingLotAreaService;
import com.vsc.business.gerd.service.work.ParkingLotService;
import com.vsc.business.gerd.service.work.UserOrderService;
import com.vsc.business.gerd.service.work.WxUserService;
import com.vsc.business.gerd.service.work.YudingService;
import com.vsc.business.gerd.service.work.YudingSettingService;
import com.vsc.constants.Constants;
import com.vsc.modules.entity.MapBean;
import com.vsc.util.JSONUtil;

/**
 * 微信小程序接口控制类
 *
 * @author wgqki
 */
@Controller
@RequestMapping(value = Constants.SPT + WeixinController.PATH)
public class WeixinController extends HttpServiceBaseController {

    // 控制层 URL地址映射
    public static final String PATH = PATH_BASE + Constants.SPT + "weixin";

    // 视图地址映射
    public static final String V_PATH = V_PATH_BASE;
    public static final String V_PATH_INDEX = PATH_BASE + Constants.SPT + "weixin";

    //预约设置
    @Autowired
    private YudingSettingService yudingSettingService;

    //片区
    @Autowired
    private ParkingLotAreaService parkingLotAreaService;

    @Autowired
    private OrgService orgService;
    
    //预约
    @Autowired
    private YudingService yudingService;

    //车位
    @Autowired
    private ParkingGarageService parkingGarageService;

    //地锁
    @Autowired
    private ParkingLockService parkingLockService;

    //微信用户
    @Autowired
    private WxUserService wxUserService;

    //停车场
    @Autowired
    ParkingLotService parkingLotService;

    //用户订单
    @Autowired
    UserOrderService userOrderService;

    private String[] featureNames = new String[]{JSONUtil._Feature_WriteMapNullValue,
        JSONUtil._Feature_WriteNullListAsEmpty, JSONUtil._Feature_WriteNullStringAsEmpty,
        JSONUtil._Feature_WriteNullNumberAsZero, JSONUtil._Feature_WriteNullBooleanAsFalse,
        JSONUtil._Feature_PrettyFormat, JSONUtil._Feature_DisableCircularReferenceDetect};

    @RequestMapping(value = "")
    public String index(Model model) {
        return V_PATH_INDEX;
    }

    /**
     * 登陆
     *
     * @param js_code 微信登陆标识
     * @return
     * @throws ParseException
     * @throws java.io.IOException
     */
    @RequestMapping(value = "/login")
    public ModelAndView weixinLogin(
            @RequestParam(required = true) String js_code) throws ParseException, IOException {
        String result = null;
        HttpGet httpGet = new HttpGet("https://api.weixin.qq.com/sns/jscode2session?appid=wx7da35897ec0b8ac0&secret=80017a058ba9741f6c331ce3f2cd21b5&js_code=" + js_code + "&grant_type=authorization_code");
        HttpResponse httpResponse = new DefaultHttpClient().execute(httpGet);
        if (httpResponse.getStatusLine().getStatusCode() == 200) {
            HttpEntity httpEntity = httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity);//取出应答字符串  
            // 一般来说都要删除多余的字符   
            result.replaceAll("\r", "");//去掉返回结果中的"\r"字符，否则会在结果字符串后面显示一个小方格
        }
        System.out.println(result);
        //登录信息转对象
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

        String[] isNotIgnoreFieldNames = {"id", "telphone", "weixinId", "carNo", "name","sex","country","province","city"};
        String jsonstr = JSONUtil.toJSONString(wxUser, isNotIgnoreFieldNames, false, featureNames);
        return this.ajaxDoneSuccess("登陆成功", jsonstr);
    }
    
    /**
     * 注册
     *
     * @param weixinId 微信ID
     * @param name 微信昵称
     * @param carNumber 车牌号
     * @param tel 手机号
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "/register")
    public ModelAndView register(
            @RequestParam(required = true) String weixinId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String carNumber,
            @RequestParam(required = false) String tel,
            @RequestParam(required = false) Integer sex,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String province,
            @RequestParam(required = false) String city) throws ParseException {
        //登录信息查询
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
            wxUserService.save(wxUser);
        } else {
            return this.ajaxDoneError("已有注册信息");
        }
        // 返回新的登录信息
        wxUser = wxUserService.getByWeixinId(weixinId);
        String[] isNotIgnoreFieldNames = {"id", "telphone", "weixinId", "carNo", "name","sex","country","province","city"};
        String jsonstr = JSONUtil.toJSONString(wxUser, isNotIgnoreFieldNames, false, featureNames);
        return this.ajaxDoneSuccess("注册成功", jsonstr);
    }

    /**
     * 预约查询
     *
     * @param userId 用户ID
     * @return
     * @throws java.text.ParseException
     */
    @RequestMapping(value = "/yuyue/info")
    public ModelAndView yuyueInfo(
            @RequestParam(required = true) Long userId) throws ParseException {

        List<Yuyue> yuyues = new ArrayList<Yuyue>();
        List<YudingSetting> vl = yudingSettingService.getAllList();
        List<Yuding> yudings = yudingService.findByWxUser(userId);
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
            //查询地锁状态
            ParkingLock parkingLock=this.parkingLockService.getByGarageId(yuding.getParkingGarage().getId());
            yuyue.getParkingGarage().setParkingLock(parkingLock);
            
            yuyues.add(yuyue);
        }
        if (yuyues.isEmpty()) {
            return this.ajaxDoneError("没有预约信息");
        }
        String[] isNotIgnoreFieldNames = {"orderNumber", "userId","carNumber", "isEnabled", "parkingGarage", "name", "yuyueTime", "createTime", "description", "xcoordinate", "ycoordinate", "yudingSetting", "lockedCost", "lockedHourCost", "shoufei", "cardType", "id", "isCarOn"};
        String jsonstr = JSONUtil.toJSONString(yuyues, isNotIgnoreFieldNames, false, featureNames);
        return this.ajaxDoneSuccess(this.getMessage("httpservices.service_success"), jsonstr);
    }

    /**
     * 订单查询
     *
     * @param userId 用户ID
     * @return
     * @throws java.text.ParseException
     */
    @RequestMapping(value = "/order/info")
    public ModelAndView orderInfo(
            @RequestParam(required = true) Long userId) throws ParseException {

        //订单查询
        Map<String, Object> orderMap = new HashMap<String, Object>();
        orderMap.put("EQ_wxUser.id", userId);
        orderMap.put("NOTEQ_isDelete", new Integer(1));
        List<UserOrder> userOrderlList = userOrderService.findList(orderMap);
        if (userOrderlList == null || userOrderlList.isEmpty()) {
            return this.ajaxDoneError("没有订单信息");
        }
        for(UserOrder userOrder:userOrderlList){
            //查询地锁状态
            ParkingLock parkingLock=this.parkingLockService.getByGarageId(userOrder.getParkingGarage().getId());
            userOrder.getParkingGarage().setParkingLock(parkingLock);
            
        }
        String[] isNotIgnoreFieldNames = {"id", "createTime", "outTime", "wxUser", "name", "parkingGarage", "description", "isDelete", "isCarOn", "isOpen"};
        String jsonstr = JSONUtil.toJSONString(userOrderlList, isNotIgnoreFieldNames, false, featureNames);
        return this.ajaxDoneSuccess(this.getMessage("httpservices.service_success"), jsonstr);
    }

    /**
     * 停车场查询
     *
     * @param parkingLotId
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "/parkinglot/find")
    public ModelAndView parkinglotFind(@RequestParam(required = true) Long userId) throws ParseException {
    	Map<String, Object> orgParams = new HashMap<String, Object>();
    	orgParams.put("EQ_isEnabled",false);
    	List<Org> orgs=this.orgService.findList(orgParams);
    	orgParams.remove("EQ_isEnabled");
    	orgParams.put("EQ_users.id",userId);
    	orgs.addAll(this.orgService.findList(orgParams));
    	
        Map<String, Object> searchParams = new HashMap<String, Object>();
        searchParams.put("EQ_isEnabled", true);

        Set<ParkingLot> parkingLots =new HashSet<ParkingLot>();
        searchParams.put("ISNULL_orgCode",null);
		parkingLots.addAll(parkingLotService.findAllList(searchParams));
		searchParams.remove("ISNULL_orgCode");
        if(orgs!=null){
        	searchParams.put("EQ_org.isDelete",0);
        	for(Org org:orgs){
        		 searchParams.put("EQ_org.code",org.getCode());
        		 parkingLots.addAll(parkingLotService.findAllList(searchParams));
        	}
        }
        if (parkingLots != null && !parkingLots.isEmpty()) {
        	searchParams.remove("EQ_org.isDelete");
        	searchParams.remove("EQ_org.code");
            // 查询剩余车位
            for (ParkingLot parkingLot : parkingLots) {
                int sumfree = 0;
                searchParams.put("EQ_parkingLot", parkingLot.getId());
                searchParams.put("ISNULL_parent", null);
                List<ParkingLotArea> parkingLotAreas = this.parkingLotAreaService.findAllList(searchParams);
                //余位查询
                freeCarNum(parkingLotAreas);
                for (ParkingLotArea parkingLotArea : parkingLotAreas) {
                    sumfree += parkingLotArea.getFreeCarNum();
                }
                parkingLot.setFreeCarNum(sumfree);
            }
        } else {
            return this.ajaxDoneError("暂无停车场信息");
        }
        String[] isNotIgnoreFieldNames = {"id", "name", "baiduLatitudeLng", "baiduLatitudeLat", "isEnabled", "carNumber", "freeCarNum"};
        String jsonstr = JSONUtil.toJSONString(parkingLots, isNotIgnoreFieldNames, false, featureNames);
        return this.ajaxDoneSuccess(this.getMessage("httpservices.service_success"), jsonstr);
    }

    /**
     * 片区查询
     *
     * @param parkingLotId 停车场库ID
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "/parkinglotarea/find")
    public ModelAndView parkinglotAreaFind(@RequestParam(required = true) Long parkingLotId) throws ParseException {
        Map<String, Object> searchParams = new HashMap<String, Object>();
        searchParams.put("EQ_isEnabled", true);
        searchParams.put("EQ_parkingLot", parkingLotId);
        searchParams.put("ISNULL_parent", null);
        List<ParkingLotArea> parkingLotAreas = this.parkingLotAreaService.findAllList(searchParams);
        if (parkingLotAreas == null || parkingLotAreas.isEmpty()) {
            return ajaxDoneError("片区信息为空");
        }
        //余位查询
        freeCarNum(parkingLotAreas);
        String[] isNotIgnoreFieldNames = {"id", "name", "baiduLatitudeLng", "baiduLatitudeLat", "isEnabled", "carNumber", "children", "freeCarNum"};
        String jsonstr = JSONUtil.toJSONString(parkingLotAreas, isNotIgnoreFieldNames, false, featureNames);
        return this.ajaxDoneSuccess(this.getMessage("httpservices.service_success"), jsonstr);
    }

    /**
     * 车位查询
     *
     * @param parkingLotAreaId 片区ID
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "/parkinggarage/find")
    public ModelAndView parkinggarage(@RequestParam(required = false) Long parkingLotAreaId) throws ParseException {
        Map<String, Object> searchParams = new HashMap<String, Object>();
        searchParams.put("id", parkingLotAreaId);
        List<MapBean<String, Object>> parkingGarages = this.parkingGarageService.findIbatisQuery("t_parking_garage.surplusGarages",
        		searchParams);
        if (parkingGarages == null || parkingGarages.isEmpty()) {
            return this.ajaxDoneError("没有找到车位信息");
        }
        String[] isNotIgnoreFieldNames = {"id", "name", "parkingLotArea", "description", "garageType", "xcoordinate", "ycoordinate", "isOnline", "isEnabled"};
        String jsonstr = JSONUtil.toJSONString(parkingGarages, isNotIgnoreFieldNames, false, featureNames);
        // TODO 查询剩余车位
        return this.ajaxDoneSuccess(this.getMessage("httpservices.service_success"), jsonstr);
    }

    /**
     * 预约车位
     *
     * @param userId 用户ID
     * @param carNo 车牌
     * @param parkingId 车位ID
     * @param inTime 时间
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "/yuyue/new")
    public ModelAndView yuyueNew(
            @RequestParam(required = true) Long userId,
            @RequestParam(required = false) String carNo,
            @RequestParam(required = true) Long parkingId,
            @RequestParam(required = false) Date inTime
    ) throws ParseException {
        Yuding yuding = new Yuding();
        //订单查询
        Map<String, Object> orderMap = new HashMap<String, Object>();
        orderMap.put("EQ_wxUser.id", userId);
        orderMap.put("NOTEQ_isDelete", new Integer(1));
        List<UserOrder> userOrderlList = userOrderService.findList(orderMap);
        if (userOrderlList != null && !userOrderlList.isEmpty()) {
            return this.ajaxDoneError("已有订单，请先结束上一笔订单");
        }
        //订单查询
        Map<String, Object> orderParking = new HashMap<String, Object>();
        orderParking.put("EQ_parkingGarage", parkingId);
        orderParking.put("EQ_isDelete", new Integer(0));
        List<UserOrder> orderParkingList = userOrderService.findList(orderParking);
        if (orderParkingList != null && !orderParkingList.isEmpty()) {
            return this.ajaxDoneError("手速慢了，车位被别人占用");
        }

        // 判断是有有预约
        List<Yuding> isYuding = yudingService.findByWxUser(userId);
        if (isYuding != null && !isYuding.isEmpty()) {
            return this.ajaxDoneError("已有预约");
        }
        ParkingGarage parkingGarage = this.parkingGarageService.getObjectById(parkingId);
        if (parkingGarage == null) {
            return this.ajaxDoneError("车位不存在");
        } else if (!parkingGarage.getIsEnabled()) {
            return this.ajaxDoneError("车位不可用");
        } else {
            List<YudingSetting> ys = yudingSettingService.getAllList();
            WxUser wxUser = wxUserService.getObjectById(userId);
            if (wxUser == null || wxUser.getId() == null) {
                return this.ajaxDoneError("用户信息有误");
            }
            if (ys.isEmpty()) {
                return this.ajaxDoneError("系统未开放预约功能");
            }

            YudingSetting vm;
            //以上锁
            yuding.setIsLockedOk(Boolean.TRUE);
            vm = ys.get(0);
            // @TODO 还缺申请时间的判断逻辑
            Calendar now = Calendar.getInstance();
            Date nowDay = now.getTime();
            nowDay = DateUtils.setMilliseconds(nowDay, 0);
            nowDay = DateUtils.setSeconds(nowDay, 0);
//            Date nowStart = DateUtils.addMinutes(nowDay, vm.getStartAddMinutes());
//            Date nowEnd = DateUtils.addMinutes(nowDay, vm.getEndAddMinutes());
//            String nowString = DateFormatUtils.format(nowDay, "yyyy-MM-dd");
//            int week = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
//
//            Date dayStart = DateUtils.parseDate(nowString + " " + vm.getWeekStarttime(week), "yyyy-MM-dd HH:mm");
//            Date dayEnd = DateUtils.parseDate(nowString + " " + vm.getWeekEndtime(week), "yyyy-MM-dd HH:mm");
//
//            Date lockedStart = nowStart.getTime() > dayStart.getTime() ? nowStart : dayStart;
//            Date lockedEnd = nowEnd.getTime() < dayEnd.getTime() ? nowEnd : dayEnd;
//
//            if (!((lockedStart.getTime() <= nowDay.getTime()) && (lockedEnd.getTime() >= nowDay.getTime()))) {
//                return this.ajaxDoneError("申请时间不允许预约");
//            }

            // 地锁上锁
            ParkingLock vl = this.parkingLockService.getByGarageId(parkingId);
            if (vl == null) {
//                return this.ajaxDoneError("没有匹配的地锁");
            } else {
                String message= locked(vl, "01", userId);
                if(message.length()>0){
           		 return this.ajaxDoneError(message.toString());
                }
            }

            yuding.setWxUser(wxUser);
            yuding.setCarNo(carNo);
            yuding.setLasttime(now.getTime().getTime());
            yuding.setCreateTime(now.getTime());
            yuding.setYuyueTime(nowDay);
            yuding.setLockedCost(vm.getLockedCost());
            yuding.setLockedHourCost(vm.getLockedHourCost());
            if ((yuding.getYuyueTime() != null) && (vm.getLockedMinutes() != null)) {
                yuding.setLockedMinutes(vm.getLockedMinutes());
                Date lockedStarttime = DateUtils.addMinutes(yuding.getYuyueTime(), -1 * yuding.getLockedMinutes());
                yuding.setLockedStartTime(lockedStarttime);
            }

            // 取出Shiro中的当前用户,不查询数据库
            // yuding.setUser(this.getCurrentUser());
            yuding.setParkingGarage(parkingGarage);
            yudingService.save(yuding);
        }

        return this.ajaxDoneSuccess("预约成功", "{\"orderNumber\":" + yuding.getId() + "}");
    }

    /**
     * 取消预约
     *
     * @param userId 用户ID
     * @param orderNumber 订单号
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "/yuyue/cancel")
    public ModelAndView yuyueCancel(
            @RequestParam(required = true) Long userId,
            @RequestParam(required = true) String orderNumber
    ) throws ParseException {
    	if("undefined".equals(orderNumber)){
    		 return this.ajaxDoneError("orderNumber:undefined");
    	}
    	Long on=Long.valueOf(orderNumber);
        Yuding yuding = yudingService.getObjectById(on);
        if (userId != null && !userId.equals(yuding.getWxUser().getId())) {
            return this.ajaxDoneError("预约取消失败，没有权限");
        }

        if (yuding.getIsDelete()) {
            return this.ajaxDoneError("已被取消");
        }
        yuding.setIsDelete(true);
        yudingService.save(yuding);
        if (yuding.getIsDelete()) {
            ParkingGarage parkingGarage
                    = yuding.getParkingGarage();
            this.parkingGarageService.save(parkingGarage);
            return this.ajaxDoneSuccess("成功取消");
        }
        return this.ajaxDoneError("预约取消失败，没有找到预约信息");
    }

    /**
     * 车位上锁
     *
     * @param userId 用户ID
     * @param parkingId 车位ID
     * @return
     */
    @RequestMapping(value = "/parkinggarage/lock")
    public ModelAndView parkinggarageLocked(
            @RequestParam(required = true) Long userId,
            @RequestParam(required = true) Long parkingId) {
        String jsonstr = "\"false\"";

        Map<String, Object> orderMap = new HashMap<String, Object>();
        orderMap.put("EQ_wxUser.id", userId);
        orderMap.put("NOTEQ_isDelete", new Integer(1));
        List<UserOrder> userOrderlList = userOrderService.findList(orderMap);
        if (userOrderlList != null && !userOrderlList.isEmpty()) {
            UserOrder userOrder = userOrderlList.get(0);
            if (!parkingId.equals(userOrder.getParkingGarage().getId())) {
                return this.ajaxDoneError("没有该车位上锁权限，您的订单车位为" + userOrder.getParkingGarage().getId());
            } else {
                ParkingLock vl = this.parkingLockService.getByGarageId(parkingId);
                ParkingGarage parkingGarage = userOrder.getParkingGarage();
                // TODO 假上锁
                if (vl == null) {
                    fakeLocked(parkingGarage);
//                    return this.ajaxDoneError("没有匹配的地锁");
                } else {
                    fakeLocked(parkingGarage);
                    String message=  locked(vl, "01", userId);
                    if(message.length()>0){
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
     *
     * @param userId 用户id
     * @param orderId 订单id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/order/cancel")
    public ModelAndView orderCancel(
            @RequestParam(required = true) Long userId,
            @RequestParam(required = false) Long orderId) throws Exception {
        Calendar now = Calendar.getInstance();
        Map<String, Object> orderMap = new HashMap<String, Object>();
        orderMap.put("EQ_wxUser.id", userId);
        orderMap.put("NOTEQ_isDelete", new Integer(1));
        List<UserOrder> userOrderlList = userOrderService.findList(orderMap);
        if (userOrderlList != null && !userOrderlList.isEmpty()) {
            UserOrder userOrder = userOrderlList.get(0);
            // 判断选择订单是否合理
            if (orderId != null && !orderId.equals(userOrder.getId())) {
                return this.ajaxDoneError("您应取消订单" + userOrder.getId());
            }
            //设置订单取消时间
            userOrder.setOutTime(now.getTime());
            //设置订单状态
            userOrder.setIsDelete(1);
            userOrderService.save(userOrder);
            return this.ajaxDoneSuccess("成功结束订单");
        }
        return this.ajaxDoneError("用户没有订单信息");
    }

    /**
     * 车位解锁
     *
     * @param userId 用户ID
     * @param parkingId 车位ID
     * @return
     */
    @RequestMapping(value = "/parkinggarage/unlock")
    public ModelAndView parkinggarageUnlocked(
            @RequestParam(required = true) Long userId,
            @RequestParam(required = true) Long parkingId) {
        Calendar now = Calendar.getInstance();
        String jsonstr = "\"true\"";
        ParkingLock vl = this.parkingLockService.getByGarageId(parkingId);
        //预约单查询
        List<Yuding> yudings = yudingService.findByWxUser(userId);
        if (yudings != null && !yudings.isEmpty()) {
            if (!parkingId.equals(yudings.get(0).getParkingGarage().getId())) {
                return this.ajaxDoneError("没有该车位解锁权限，您预定的车位为" + yudings.get(0).getParkingGarage().getId());
            }
        }

        Map<String, Object> searchYuding = new HashMap<String, Object>();
        searchYuding.put("EQ_parkingGarage", parkingId);
        searchYuding.put("NOTEQ_wxUser.id", userId);
        searchYuding.put("EQ_isDelete", Boolean.FALSE);
        List<Yuding> yudingParking = this.yudingService.findList(searchYuding);
        if (yudingParking != null && !yudingParking.isEmpty()) {
            return this.ajaxDoneError("手速慢了,该车位被别人预约");
        }

        // TODO 没有地锁假开关
//        if (vl.isEmpty()) {
//            return this.ajaxDoneSuccess("没有匹配的地锁", jsonstr);
//        }
        Map<String, Object> orderUserMap = new HashMap<String, Object>();
        // 订单查询
        orderUserMap.put("EQ_wxUser.id", userId);
        orderUserMap.put("NOTEQ_isDelete", new Integer(1));
        List<UserOrder> userOrderlList = userOrderService.findList(orderUserMap);
        if (userOrderlList != null && !userOrderlList.isEmpty()) {
            if (!parkingId.equals(userOrderlList.get(0).getParkingGarage().getId())) {
                return this.ajaxDoneError("没有该车位解锁权限，您的订单车位为" + userOrderlList.get(0).getParkingGarage().getId());
            }
        } else {
            Map<String, Object> orderParkingMap = new HashMap<String, Object>();
            // 订单查询
            orderParkingMap.put("EQ_parkingGarage", parkingId);
            orderParkingMap.put("EQ_isDelete", new Integer(0));
            List<UserOrder> orderParkingList = userOrderService.findList(orderParkingMap);
            for (UserOrder userOrder : orderParkingList) {
                if (userOrder != null && userOrder.getWxUser() != null && !userId.equals(userOrder.getWxUser().getId())) {
                    return this.ajaxDoneError("该车位已被占用");
                }
            }
            if (vl != null) {
            	String message=  locked(vl, "02", userId);
            	if(message.length()>0){
            		 return this.ajaxDoneError(message.toString());
            	}
            }
            // 创建订单
            UserOrder userOrder = new UserOrder();
            userOrder.setWxUser(this.wxUserService.getObjectById(userId));
            userOrder.setCreateTime(now.getTime());
            userOrder.setIsDelete(0);
            ParkingGarage parkingGarage = parkingGarageService.getObjectById(parkingId);
            // TODO 假上锁
            fakeLocked(parkingGarage);

            userOrder.setParkingGarage(parkingGarage);
            userOrderService.save(userOrder);
        }
        return this.ajaxDoneSuccess("解锁指令发送成功", jsonstr);
    }

    //余位查询
    private void freeCarNum(List<ParkingLotArea> parkingLotAreas) {

        Map<String, Object> searchParamsCar = Maps.newHashMap();
        for (ParkingLotArea parkingLotArea : parkingLotAreas) {
            searchParamsCar.put("id", parkingLotArea.getId());
            List<MapBean<String, Object>> vlnum = this.parkingGarageService.findIbatisQuery("t_parking_garage.surplusNum",
                    searchParamsCar);
            if (!vlnum.isEmpty()) {
                parkingLotArea.setFreeCarNum(MapUtils.getInteger(vlnum.get(0), "num"));
            }
            if (parkingLotArea.getChildren() != null && !parkingLotArea.getChildren().isEmpty()) {
                freeCarNum(parkingLotArea.getChildren());
            }
        }
    }

    //开关锁
    private String locked(ParkingLock vl, String state, Long userId) {
        ParkingGarage parkingGarage = vl.getParkingGarage();
        fakeLocked(parkingGarage);
        return this.parkingLockService.reverse(new Long[]{vl.getId()}, state, userId, ParkingLockOperationEvent.SOURCETYPE_PHONE);
    }

    //假开关锁
    private void fakeLocked(ParkingGarage parkingGarage) {
        parkingGarageService.save(parkingGarage);
    }
    
    
    /**
     * 权限码查询
     *
     * @param userId
     */
    @RequestMapping(value = "/org/find")
    public ModelAndView orgFind(@RequestParam(required = true) Long userId) throws ParseException {
    	Map<String, Object> orgParams =  Maps.newHashMap();
    	orgParams.put("EQ_users.id",userId);
    	List<Org> orgs=this.orgService.findList(orgParams);
        String[] isNotIgnoreFieldNames = {"id", "name","code"};
        String jsonstr = JSONUtil.toJSONString(orgs, isNotIgnoreFieldNames, false, featureNames);
        return this.ajaxDoneSuccess(this.getMessage("httpservices.service_success"), jsonstr);
    }
    
    /**
     * 添加权限码
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/org/add")
    public ModelAndView orgAdd(
            @RequestParam(required = true) Long userId,
            @RequestParam(required = true) String code) throws Exception {
    	Org org=this.orgService.getByCode(code); 
        if (org==null)
        {
            return this.ajaxDoneError("没有权限信息");	
        }else{
        	WxUser wxUser=this.wxUserService.getObjectById(userId);
        	if(wxUser==null){
        		return this.ajaxDoneError("没有用户信息");	
        	}
        	if(!org.getUsers().contains(wxUser))
        	org.getUsers().add(wxUser);
        	this.orgService.save(org);
            return this.ajaxDoneSuccess("添加成功");
        }
    }
    
    /**
     * 删除权限码
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/org/delete")
    public ModelAndView orgDelete(
            @RequestParam(required = true) Long userId,
            @RequestParam(required = true) String code) throws Exception {
    	WxUser wxUser=this.wxUserService.getObjectById(userId);
    	if(wxUser==null){
    		return this.ajaxDoneError("没有用户信息");	
    	}
    	Org org=this.orgService.getByCode(code); 
        if (org==null) {
            return this.ajaxDoneError("没有权限信息");	
        }
    	org.getUsers().remove(wxUser);
    	this.orgService.save(org);
        return this.ajaxDoneSuccess("删除成功");
    }
}
