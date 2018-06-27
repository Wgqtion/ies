package com.vsc.util;

import com.vsc.business.gerd.entity.work.AppointmentSettings;
import com.vsc.business.gerd.entity.work.ChargeBinding;
import com.vsc.business.gerd.entity.work.ChargesSection;
import com.vsc.business.gerd.entity.work.ChargesSettings;
import com.vsc.business.gerd.service.work.ChargesSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Athor: 吴广庆
 * @Date: 2018-05-25
 */
@Controller
@RequestMapping("/text")
public class ChargeHandle {


    @Autowired
    private ChargesSectionService chargesSectionService;

    /**
     * 按次停车收费。
     *
     * @param chargesSettings 收费规则
     */
    public static Double charge(ChargesSettings chargesSettings) {
        Double price = 0.00;
        if (chargesSettings != null) {
            //单价
            Double priceFee = chargesSettings.getUnitPrice();
            //减免金额
            Double privilegeFee = chargesSettings.getPrivilegeFee();
            //单价大于减免金额则返回优惠后的金额，小于则直接返回单价
            price = priceFee > privilegeFee ? priceFee - privilegeFee : priceFee;
        }
        return price;
    }

    /**
     * 分段计费。
     *
     * @param startTime     开始时间
     * @param endTime
     * @param chargeBinding
     * @return
     */
    public Double charge(@NotNull Date startTime, @NotNull Date endTime, ChargeBinding chargeBinding) {
        Double price = 0.00;
        List<ChargesSection> chargesSectionList = null;
        // null判断
        if (chargeBinding == null) {
            return price;
        }
        Map<String, Object> filterParams = new HashMap<>();
        filterParams.put("EQ_chargeBinding", chargeBinding.getId());
        filterParams.put("EQ_isDelete", false);
        // 根据区段开始时间排序
        Sort sort = new Sort(Sort.Direction.ASC, "timeSection.startTime");
        try {
            // 查询收费区段
            chargesSectionList = chargesSectionService.findAll(filterParams, sort);
        } catch (Exception e) {
            e.printStackTrace();
        }

        price = getDayFee(startTime, endTime, price, chargesSectionList);
        return price;
    }


    /**
     * 计算一天停车收费金额
     *
     * @param startTime
     * @param endTime
     * @param price
     * @param chargesSectionList
     * @return
     */
    private Double getDayFee(@NotNull Date startTime, @NotNull Date endTime, Double price, List<ChargesSection> chargesSectionList) {
        Calendar cStart = Calendar.getInstance();

        Calendar cEnd = Calendar.getInstance();
        cStart.setTime(startTime);
        cEnd.setTime(endTime);

        Long[] addTime;
        // 带日期的区段开始时间
        Long startTimeLong;
        Date startDateTime;
        // 带日期的区段结束时间
        Long endTimeLong;
        Date endDateTime;
        for (int i = 0; i < chargesSectionList.size(); i++) {

            // 计算跨段取整后剩余时间



            ChargesSection chargesSection = chargesSectionList.get(i);
            // 判断开始时间是否在当前时段星期内
            if (chargesSection.getTimeSection().getWeek().equals(cStart.get(Calendar.DAY_OF_WEEK))) {
                startTimeLong = startTime.getTime();
                System.out.println();
                System.out.println();
                System.out.println("-------------------");
                // 计算当前周的时段开始日期
                startDateTime = getDateTime(startTimeLong, chargesSection.getTimeSection().getStartTime().getTime());
                System.out.println("开始：" + startDateTime);
                endTimeLong = endTime.getTime();
                // 计算当前周的时段结束日期
                endDateTime = getDateTime(endTimeLong, chargesSection.getTimeSection().getEndTime().getTime());
                System.out.println("结束：" + endDateTime);
                Long surplusTime = (startTime.getTime() - endDateTime.getTime()) % 3600000;
                addTime = getSuplusTime(surplusTime);

                if (i == 1 && startTime.before(startDateTime)) {
                    System.out.println("开始时间：" + startTime + "区段开始时间之前：" + startDateTime);
                    break;
                }
                if (!startTime.before(new Date(startDateTime.getTime() - addTime[0]))) {
                    System.out.println("开始时间：" + startTime + "区段开始时间之后：" + startDateTime);
                    endDateTime = new Date(endDateTime.getTime() + addTime[1]);
                    endTime =new Date(endTime.getTime() + addTime[1]);
                    if (!endTime.after(endDateTime)) {
                        price = price + ((endTime.getTime() - startTime.getTime()) / 3600000 + 1) * chargesSection.getTimeSection().getFee().doubleValue();
                        System.out.println("结束时间：" + endTime + "；区段结束时间之前：" + endDateTime + "；金额：" + price + "；单价：" + chargesSection.getTimeSection().getFee());
                        break;
                    } else if (!endTime.before(endDateTime)) {
                        price = price + (endDateTime.getTime() - startTime.getTime()) / 3600000 * chargesSection.getTimeSection().getFee().doubleValue();
                        System.out.println("结束时间：" + endTime + "区段结束时间之后：" + endDateTime + endDateTime + "；金额：" + price + "；单价：" + chargesSection.getTimeSection().getFee());
                        startTime = new Date(endDateTime.getTime() - addTime[0]);
                    }
                }
            }
        }
        return price;
    }


    /**
     * 获取区段取整后的剩余时间
     * @param surplusTime
     */
    private Long[] getSuplusTime( Long surplusTime) {
        Long[] addTime = new Long[2];
        if(surplusTime<1800000){
            addTime[0] = surplusTime;
            addTime[1] = 0L;
        }else {
            addTime[0] = 0L;
            addTime[1] = surplusTime;
        }
        return addTime;
    }

    /**
     * 计算时刻加上日期数
     *
     * @param startTimeLong  带日期的long
     * @param chargesSection 需要时时刻数
     * @return
     */
    private Date getDateTime(Long startTimeLong, Long chargesSection) {
        return new Date(startTimeLong - ((startTimeLong + 28800000) % 86400000) + chargesSection + 28800000);
    }

    public static Date getNowDate() {


        return new Date();
    }


    /**
     * 两段时间相差几天
     *
     * @param d1
     * @param d2
     * @return
     */
    public static final int getDayFromTimeDiff(Date d1, Date d2) {
        long l1 = d1.getTime();
        long l2 = d2.getTime();
        long diff = l2 - l1;
        int day = (int) (diff / (1000 * 3600 * 24));
        return day;
    }


    /**
     * 分段计费计算方法。
     *
     * @return
     */
    private static Double section() {
        Double priceFee = 0.00;


        return priceFee;
    }


    /**
     * 预约收费
     *
     * @param startTime
     * @param endTime
     * @param appointmentSettings
     */
    public static void appointment(Date startTime, Date endTime, AppointmentSettings appointmentSettings) {

    }


    /**
     * 支付订单
     */
    @RequestMapping(value = "")
    @ResponseBody
    public String pay(@RequestParam(required = true) String id) throws ParseException {
        ChargeBinding chargeBinding = new ChargeBinding();
        chargeBinding.setId(Long.valueOf(1));
        String startTime = "2018-06-25 03:30:00";
        String endTime = "2018-06-25 14:59:00";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return String.valueOf(charge(sdf.parse(startTime), sdf.parse(endTime), chargeBinding));
    }
}
