package com.vsc.business.gerd.web.httpservices;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.vsc.business.gerd.entity.work.ParkingLock;
import com.vsc.business.gerd.service.work.ParkingLockService;
import com.vsc.business.gerd.service.work.ParkingLotService;
import com.vsc.constants.Constants;
import com.vsc.modules.service.BaseService;
import com.vsc.util.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Athor: 吴广庆
 * @Date: 2018-05-31
 */
@Controller
@RequestMapping(value = Constants.SPT + TongjiController.PATH)
public class TongjiController extends HttpServiceBaseController {

    @Autowired
    private ParkingLockService parkingLockService;

    private BaseService baseService;

    @Autowired
    private ParkingLotService parkingLotService;

    private String[] featureNames = new String[] { JSONUtil._Feature_WriteMapNullValue,
            JSONUtil._Feature_WriteNullListAsEmpty, JSONUtil._Feature_WriteNullStringAsEmpty,
            JSONUtil._Feature_WriteNullNumberAsZero, JSONUtil._Feature_WriteNullBooleanAsFalse,
            JSONUtil._Feature_DisableCircularReferenceDetect };

    // 控制层 URL地址映射
    public static final String PATH = PATH_BASE + Constants.SPT + "tongji";

    // 视图地址映射
    public static final String V_PATH = V_PATH_BASE;
    public static final String V_PATH_INDEX = PATH_BASE + Constants.SPT + "tongji";

    @RequestMapping(value = "getParkingLock",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getParkingLock(
            @RequestParam(required = false)Integer pageNumber,
            @RequestParam(required = false)Integer pagzSize,
            @RequestParam(required = false)Boolean EQ_isCarOn,
            @RequestParam(required = false)Boolean EQ_isEnabled,
            @RequestParam(required = false)String LIKE_ipAddress,
            @RequestParam(required = false)String LIKE_lockNum,
            @RequestParam(required = false)String parkingLotArea,
            Model model, HttpServletRequest request)throws Exception {

        PageRequest pageRequest = this.getPageRequest(pageNumber==null?1:pageNumber,pagzSize==null?45:pagzSize);
        Map<String, Object> searchParams = new HashMap<>();
        if(EQ_isCarOn!=null){
            searchParams.put("EQ_isCarOn", EQ_isCarOn);
        }
        if(EQ_isEnabled!=null){
            searchParams.put("EQ_isEnabled", EQ_isEnabled);
        }
        if(LIKE_ipAddress!=null){
            searchParams.put("LIKE_ipAddress", LIKE_ipAddress);
        }
        if(parkingLotArea!=null){
            searchParams.put("LIKE_parkingGarage.parkingLot.name",parkingLotArea);
        }
        if(LIKE_lockNum!=null){
            searchParams.put("LIKE_lockNum", LIKE_lockNum);
        }

        Page<ParkingLock> page = parkingLockService.findPageTongji(searchParams, pageRequest);
        System.out.println("页数："+pageNumber+",页显最大显示数："+pagzSize+"  "+EQ_isCarOn+"   "+EQ_isEnabled+"  "+LIKE_ipAddress+"  "+LIKE_lockNum+"  "+parkingLotArea);
        String[] isNotIgnoreFieldNames = { "content","id","code", "lockNum", "ipAddress", "isEnabled", "parkingGarage","parkingLot",
                "isCarOn" ,"isOnline","isOpen","power","surplusDetection","logUpdateTime","description","name",
                "firstPage","lastPage","number","numberOfElements","numberOfElements","totalElements","size"};
        String json = JSONUtil.toJSONString(page,isNotIgnoreFieldNames, false, featureNames);
        System.out.println(json);
        return json;
    }
}
