package com.vsc.util;

import com.vsc.business.gerd.entity.work.ChargesSettings;
import com.vsc.business.gerd.entity.work.ReserveSettings;
import com.vsc.business.gerd.entity.work.TimeSection;
import org.apache.commons.lang3.time.DateUtils;

import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Athor: 吴广庆
 * @Date: 2018-05-25
 */
public class ChargeHandle {


    public static final Long HOUR_8 = 28800000L;
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
            Date endDate = DateUtils.addDays(getaDayLong(startTime), 1);
            price = getDayFee(startTime, endDate, price, timeSectionList, chargesSettings.getPriceTime());
            startTime = endDate;
        }
        // 当天计费
        if (DateUtils.isSameDay(startTime, endTime)) {
            price = getDayFee(startTime, endTime, price, timeSectionList, chargesSettings.getPriceTime());
        }

        return price;
    }


    /**
     * 分段计费，计算一天停车收费金额
     *
     * @param startTime       开始时间
     * @param endTime         结束时间
     * @param price           费用
     * @param timeSectionList 分段计费规则
     * @param priceTimeLong   计费时间单位
     * @return
     */
    private static Double getDayFee(@NotNull Date startTime, @NotNull Date endTime, Double price, List<TimeSection> timeSectionList, Long priceTimeLong) {
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
                // 计算当前周的时段开始日期
                startTimeLong = getDateTime(startTime, timeSection.getStartTime());
                // 计算当前周的时段结束日期
                endTimeLong = getDateTime(startTime, timeSection.getEndTime());
                if (startTime.before(new Date(startTimeLong)) || startTime.after(new Date(endTimeLong))) {
                    continue;
                }
                // 开始时间等于上一段的结束时间，所以开始时间计算应在上段剩余时间计算
                startSectionTime = new Date(startTimeLong + surplusTime);
                // 计算跨段取整后剩余时间
                surplusTime = priceTimeLong - ((endTimeLong - startTime.getTime()) % priceTimeLong);
                endSectionTime = new Date(endTimeLong + surplusTime);
                if (!startTime.before(startSectionTime)) {
                    // 计算最后一段费用
                    if (!endTime.after(endSectionTime)) {
                        price = price + ((endTime.getTime() - startTime.getTime()) / priceTimeLong + (((endTime.getTime() - startTime.getTime()) % priceTimeLong) != 0 ? 1 : 0)) * timeSection.getFee().doubleValue();
                        break;
                    }
                    // 计算开时段或中间段费用
                    else if (!endTime.before(endSectionTime)) {
                        price = price + (endSectionTime.getTime() - startTime.getTime()) / priceTimeLong * timeSection.getFee().doubleValue();
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
        return getaDayLong(dateTime).getTime() + chargesSection;
    }

    /**
     * 截取时间到日
     *
     * @param startTime
     * @return
     */
    private static Date getaDayLong(Date startTime) {
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
