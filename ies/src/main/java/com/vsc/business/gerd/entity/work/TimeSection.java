package com.vsc.business.gerd.entity.work;

import com.vsc.constants.Constants;
import com.vsc.modules.entity.BasicEntity;
import com.vsc.util.ChargeHandle;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Time;
import java.text.DecimalFormat;
import java.util.Objects;

/**
 * 分段计费规则
 *
 * @Athor: 吴广庆
 * @Date: 2018-06-21
 */
@Entity
@Table(name = Constants.TABLE_PREFIX + "time_section")
public class TimeSection extends BasicEntity {
    private ChargesSettings chargesSettings;
    private Integer week;
    private Long startTime;
    private Long endTime;
    private String startTimeString;
    private String endTimeString;
    private BigDecimal fee;

    /**
     * 收费规则
     *
     * @return
     */
    @ManyToOne
    @JoinColumn(name = "CHARGES_SETTINGS_ID", referencedColumnName = "ID")
    public ChargesSettings getChargesSettings() {
        return chargesSettings;
    }

    public void setChargesSettings(ChargesSettings chargesSettings) {
        this.chargesSettings = chargesSettings;
    }

    /**
     * 周
     * ["1","周日"],
     * ["2","周一"],
     * ["3","周二"],
     * ["4","周三"],
     * ["5","周四"],
     * ["6","周五"],
     * ["7","周六"],
     * ["0","指定日期"]
     */
    @Basic
    @Column(name = "WEEK")
    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    /**
     * 开始时间
     *
     * @return
     */
    @Basic
    @Column(name = "START_TIME")
    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    @Transient
    public String getStartTimeString() {
        String startTimeString = "00:00";
        DecimalFormat decimalFormat = new DecimalFormat("00");
        if (startTime != null) {
            String hour = decimalFormat.format(startTime / ChargeHandle.HOUR_1);
            String min = decimalFormat.format((startTime % ChargeHandle.HOUR_1) / ChargeHandle.MIN_1);
            startTimeString = hour + ":" + min;
        }
        return startTimeString;
    }

    public void setStartTimeString(String startTimeString) {
        String[] startTime = startTimeString.split(":");
        if (startTime != null && startTime.length != 0) {
            setStartTime(Long.valueOf(startTime[0]) * ChargeHandle.HOUR_1 + Long.valueOf(startTime[1]) * ChargeHandle.MIN_1);
        }
    }

    /**
     * 结束时间
     *
     * @return
     */
    @Basic
    @Column(name = "END_TIME")
    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    @Transient
    public String getEndTimeString() {
        String endTimeString = "00:00";
        DecimalFormat decimalFormat = new DecimalFormat("00");
        if (startTime != null) {
            String hour = decimalFormat.format(endTime / ChargeHandle.HOUR_1);
            String min = decimalFormat.format((endTime % ChargeHandle.HOUR_1) / ChargeHandle.MIN_1);
            endTimeString = hour + ":" + min;
        }
        return endTimeString;
    }

    public void setEndTimeString(String endTimeString) {
        String[] endTime = endTimeString.split(":");
        if (endTime != null && endTime.length != 0) {
            setEndTime(Long.valueOf(endTime[0]) * ChargeHandle.HOUR_1 + Long.valueOf(endTime[1]) * ChargeHandle.MIN_1);
        }
    }

    /**
     * 单价
     *
     * @return
     */
    @Basic
    @Column(name = "FEE")
    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeSection that = (TimeSection) o;
        return
                week == that.week &&
                        Objects.equals(startTime, that.startTime) &&
                        Objects.equals(endTime, that.endTime) &&
                        Objects.equals(fee, that.fee);
    }

    @Override
    public int hashCode() {

        return Objects.hash(week, startTime, endTime, fee);
    }
}
