package com.vsc.util;

import com.vsc.business.gerd.entity.work.ChargeRecord;
import com.vsc.business.gerd.entity.work.ChargesSettings;
import com.vsc.business.gerd.entity.work.ReserveSettings;
import com.vsc.business.gerd.entity.work.TimeSection;
import org.apache.commons.lang3.time.DateUtils;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.*;

/**
 * @Athor: 吴广庆
 * @Date: 2018-05-25
 */
public class ChargeHandle {

    public static final Long MIN_1 = 60000L;
    public static final Long HOUR_1 = 3600000L;

    /**
     * 按次停车收费。
     *
     * @param chargesSettings 收费规则
     */
    public Double charge(ChargesSettings chargesSettings) {
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

    public static ChargeRecord chargeRecord(@NotNull Date startTime, @NotNull Date endTime, ChargesSettings chargesSettings){
        ChargeRecord chargeRecord = new ChargeRecord();
        List<ChargeRecord> chargeRecordList = new ArrayList<>();
        chargeRecord.setStartComputeTime(startTime);
        chargeRecord.setEndComputeTime(endTime);
        chargeRecord.setMaxFee(new BigDecimal(chargesSettings.getMaxFee()));

//        Double price = 0.00;
        List<TimeSection> timeSectionList = chargesSettings.getTimeSectionList();
        // null判断
        if (chargesSettings == null) {
            return chargeRecord;
        }
        // 跨天计费
        for (int i = 0; !DateUtils.isSameDay(startTime, endTime); i++) {
            // 天数加一（一天的结束时间是下一天的开始时间）
            Date endDate = DateUtils.addDays(getDate(startTime), 1);
            ChargeRecord chargeRecordDay = getDayFeeRecord(startTime, endDate, timeSectionList, chargesSettings.getPriceTime());
            chargeRecordDay.setMaxFee(new BigDecimal(chargesSettings.getMaxFee()));
            chargeRecordList.add(chargeRecordDay);
            if (chargeRecordDay.getSumFee().compareTo(new BigDecimal(chargesSettings.getMaxFee())) > 0 && !(chargesSettings.getMaxFee().compareTo(0.0) == 0)) {
                chargeRecord.setSumFee(chargeRecord.getSumFee().add(new BigDecimal(chargesSettings.getMaxFee())));
            } else {
                chargeRecord.setSumFee(chargeRecord.getSumFee().add(chargeRecordDay.getSumFee()));
            }
            startTime = endDate;
        }
        // 当天计费
        if (DateUtils.isSameDay(startTime, endTime)) {
            ChargeRecord chargeRecordDay = getDayFeeRecord(startTime, endTime, timeSectionList, chargesSettings.getPriceTime());
            chargeRecordDay.setMaxFee(new BigDecimal(chargesSettings.getMaxFee()));
            chargeRecordList.add(chargeRecordDay);
            if (chargeRecordDay.getSumFee().compareTo(new BigDecimal(chargesSettings.getMaxFee())) > 0 && !(chargesSettings.getMaxFee().compareTo(0.0) == 0)) {
                chargeRecord.setSumFee(chargeRecord.getSumFee().add(new BigDecimal(chargesSettings.getMaxFee())));
            } else {
                chargeRecord.setSumFee(chargeRecord.getSumFee().add(chargeRecordDay.getSumFee()));
            }
        }
        chargeRecord.setChargeRecordList(chargeRecordList);
        return chargeRecord;
    }

    /**
     * 分段计费。
     *
     * @param startTime       开始时间
     * @param endTime         结束时间
     * @param chargesSettings
     * @return
     */
    public static Double charge(@NotNull Date startTime, @NotNull Date endTime, ChargesSettings chargesSettings) {

        Double price = 0.00;
        List<TimeSection> timeSectionList = chargesSettings.getTimeSectionList();
        // null判断
        if (chargesSettings == null) {
            return price;
        }
        // 跨天计费
        for (int i = 0; !DateUtils.isSameDay(startTime, endTime); i++) {
            // 天数加一（一天的结束时间是下一天的开始时间）
            Date endDate = DateUtils.addDays(getDate(startTime), 1);
            Double dayFee = getDayFee(startTime, endDate, timeSectionList, chargesSettings.getPriceTime());
            if (dayFee > chargesSettings.getMaxFee() && chargesSettings.getMaxFee().equals(0)) {
                price += chargesSettings.getMaxFee();
            } else {
                price += dayFee;
            }
            startTime = endDate;
        }
        // 当天计费
        if (DateUtils.isSameDay(startTime, endTime)) {
            Double dayFee = getDayFee(startTime, endTime, timeSectionList, chargesSettings.getPriceTime());
            if (dayFee > chargesSettings.getMaxFee() && chargesSettings.getMaxFee().equals(0)) {
                price += chargesSettings.getMaxFee();
            } else {
                price += dayFee;
            }
        }

        return price;
    }


    /**
     * 分段计费，计算一天停车收费金额
     *
     * @param startTime       开始时间
     * @param endTime         结束时间
     * @param timeSectionList 分段计费规则
     * @param priceTimeLong   计费时间单位
     * @return
     */
    private static ChargeRecord getDayFeeRecord(@NotNull Date startTime, @NotNull Date endTime, List<TimeSection> timeSectionList, Long priceTimeLong) {
        ChargeRecord chargeRecordDay = new ChargeRecord();
//        Double price = 0.0;
        List<ChargeRecord> children = new ArrayList<>();
        Calendar cStart = Calendar.getInstance();
        Calendar cEnd = Calendar.getInstance();
        cStart.setTime(startTime);
        cEnd.setTime(endTime);
        chargeRecordDay.setStartComputeTime(startTime);
        chargeRecordDay.setEndComputeTime(endTime);

        // 带日期的区段开始时间
        Long startTimeLong;
        Date startSectionTime;
        // 带日期的区段结束时间
        Long endTimeLong;
        Date endSectionTime;
        //
        Long surplusTime = 0L;
        for (TimeSection timeSection : timeSectionList) {
            // 判断开始时间是否在当前时段星期内
            if (timeSection.getWeek().equals(cStart.get(Calendar.DAY_OF_WEEK))) {
                ChargeRecord chargeRecordChildren = new ChargeRecord();
                System.out.println("\n\n-------------------");
                // 计算当前周的时段开始日期
                startTimeLong = getDateTime(startTime, timeSection.getStartTime());
                System.out.println("开始：" + new Date(startTimeLong));
                // 计算当前周的时段结束日期
                endTimeLong = getDateTime(startTime, timeSection.getEndTime());
                if (startTime.before(new Date(startTimeLong)) || startTime.after(new Date(endTimeLong))) {
                    System.out.println("开始时间：" + startTime + "区段开始时间之前或结束时间之后：" + new Date(startTimeLong));
                    continue;
                }
                // 开始时间等于上一段的结束时间，所以开始时间计算应在上段剩余时间计算
                startSectionTime = new Date(startTimeLong + surplusTime);
                // 计算跨段取整后剩余时间
                surplusTime = priceTimeLong - ((endTimeLong - startTime.getTime()) % priceTimeLong);
                surplusTime = surplusTime.compareTo(priceTimeLong)==0 ? 0 : surplusTime;
                System.out.println("结束：" + new Date(endTimeLong));
                endSectionTime = new Date(endTimeLong + surplusTime);
                System.out.println("开始时间：" + startTime + "；取整剩余时间" + surplusTime / MIN_1 + ", 区段开始时间：" + startSectionTime);
                if (!startTime.before(startSectionTime)) {
                    System.out.println("开始时间：" + startTime + "区段开始时间之后：" + startSectionTime);
                    // 计算最后一段费用
                    if (!endTime.after(endSectionTime)) {
                        chargeRecordChildren.setStartComputeTime(startTime);
                        chargeRecordChildren.setEndComputeTime(endTime);
                        Double thisFee = ((endTime.getTime() - startTime.getTime()) / priceTimeLong + (((endTime.getTime() - startTime.getTime()) % priceTimeLong) != 0 ? 1 : 0)) * timeSection.getFee().doubleValue();
                        chargeRecordDay.setSumFee(chargeRecordDay.getSumFee().add(new BigDecimal(thisFee)));
                        chargeRecordChildren.setSumFee(new BigDecimal(thisFee));
                        System.out.println("结束时间：" + endTime + "；区段结束时间之前：" + endSectionTime + "；金额：" + thisFee + "；单价：" + timeSection.getFee());
                        children.add(chargeRecordChildren);
                        break;
                    }
                    // 计算开时段或中间段费用
                    else if (!endTime.before(endSectionTime)) {
                        chargeRecordChildren.setStartComputeTime(startTime);
                        chargeRecordChildren.setEndComputeTime(endSectionTime);
                        Double thisFee = (endSectionTime.getTime() - startTime.getTime()) / priceTimeLong * timeSection.getFee().doubleValue();
                        chargeRecordDay.setSumFee(chargeRecordDay.getSumFee().add(new BigDecimal(thisFee)));
                        chargeRecordChildren.setSumFee(new BigDecimal(thisFee));
                        System.out.println("结束时间：" + endTime + "区段结束时间之后：" + endSectionTime + "；金额：" + thisFee + "；单价：" + timeSection.getFee());
                        children.add(chargeRecordChildren);
                        startTime = endSectionTime;
                    }
                }
            }
        }

        chargeRecordDay.setChargeRecordList(children);
        return chargeRecordDay;
    }

    private static Double getDayFee(@NotNull Date startTime, @NotNull Date endTime, List<TimeSection> timeSectionList, Long priceTimeLong) {
        Double price = 0.0;
        Calendar cStart = Calendar.getInstance();
        Calendar cEnd = Calendar.getInstance();
        cStart.setTime(startTime);
        cEnd.setTime(endTime);

        // 带日期的区段开始时间
        Long startTimeLong;
        Date startSectionTime;
        // 带日期的区段结束时间
        Long endTimeLong;
        Date endSectionTime;
        //
        Long surplusTime = 0L;
        for (TimeSection timeSection : timeSectionList) {
            // 判断开始时间是否在当前时段星期内
            if (timeSection.getWeek().equals(cStart.get(Calendar.DAY_OF_WEEK))) {
                System.out.println("\n\n-------------------");
                // 计算当前周的时段开始日期
                startTimeLong = getDateTime(startTime, timeSection.getStartTime());
                System.out.println("开始：" + new Date(startTimeLong));
                // 计算当前周的时段结束日期
                endTimeLong = getDateTime(startTime, timeSection.getEndTime());
                if (startTime.before(new Date(startTimeLong)) || startTime.after(new Date(endTimeLong))) {
                    System.out.println("开始时间：" + startTime + "区段开始时间之前或结束时间之后：" + new Date(startTimeLong));
                    continue;
                }
                // 开始时间等于上一段的结束时间，所以开始时间计算应在上段剩余时间计算
                startSectionTime = new Date(startTimeLong + surplusTime);
                // 计算跨段取整后剩余时间
                surplusTime = priceTimeLong - ((endTimeLong - startTime.getTime()) % priceTimeLong);
                surplusTime = surplusTime.compareTo(priceTimeLong)==0 ? 0 : surplusTime;
                System.out.println("结束：" + new Date(endTimeLong));
                endSectionTime = new Date(endTimeLong + surplusTime);
                System.out.println("开始时间：" + startTime + "；取整剩余时间" + surplusTime / MIN_1 + ", 区段开始时间：" + startSectionTime);
                if (!startTime.before(startSectionTime)) {
                    System.out.println("开始时间：" + startTime + "区段开始时间之后：" + startSectionTime);
                    // 计算最后一段费用
                    if (!endTime.after(endSectionTime)) {
                        price += ((endTime.getTime() - startTime.getTime()) / priceTimeLong + (((endTime.getTime() - startTime.getTime()) % priceTimeLong) != 0 ? 1 : 0)) * timeSection.getFee().doubleValue();
                        System.out.println("结束时间：" + endTime + "；区段结束时间之前：" + endSectionTime + "；金额：" + price + "；单价：" + timeSection.getFee());
                        break;
                    }
                    // 计算开时段或中间段费用
                    else if (!endTime.before(endSectionTime)) {
                        price += (endSectionTime.getTime() - startTime.getTime()) / priceTimeLong * timeSection.getFee().doubleValue();
                        System.out.println("结束时间：" + endTime + "区段结束时间之后：" + endSectionTime + "；金额：" + price + "；单价：" + timeSection.getFee());
                        startTime = endSectionTime;
                    }
                }
            }
        }
        return price;
    }

    /**
     * 计算时刻加上日期数
     *
     * @param dateTime       需要截取日期参数
     * @param chargesSection 需要时时刻数
     * @return
     */
    private static Long getDateTime(Date dateTime, Long chargesSection) {
        return getDate(dateTime).getTime() + chargesSection;
    }

    /**
     * 截取时间到日
     *
     * @param startTime
     * @return
     */
    private static Date getDate(Date startTime) {
        return DateUtils.truncate(startTime, Calendar.DATE);
    }

    /**
     * 预约收费
     *
     * @param startTime
     * @param endTime
     * @param reserveSettings
     */
    public static void appointment(Date startTime, Date endTime, ReserveSettings reserveSettings) {

    }
}
