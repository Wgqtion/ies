package com.vsc.business.gerd.entity.work;

import com.vsc.constants.Constants;
import com.vsc.modules.entity.BasicEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Time;
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
    private Time startTime;
    private Time endTime;
    private BigDecimal fee;

    /**
     * 收费规则
     * @return
     */
    @ManyToOne
    @JoinColumn(name = "CHARGES_SETTINGS_ID",referencedColumnName="ID")
    public ChargesSettings getChargesSettings() {
        return chargesSettings;
    }

    public void setChargesSettings(ChargesSettings chargesSettings) {
        this.chargesSettings = chargesSettings;
    }

    /**
     * 周
     * ["1","周一"],
     ["2","周二"],
     ["3","周三"],
     ["4","周四"],
     ["5","周五"],
     ["6","周六"],
     ["7","周日"],
     ["0","指定日期"]
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
     * @return
     */
    @Basic
    @Column(name = "START_TIME")
    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    /**
     * 结束时间
     * @return
     */
    @Basic
    @Column(name = "END_TIME")
    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    /**
     * 单价
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
                Objects.equals(fee, that.fee) ;
    }

    @Override
    public int hashCode() {

        return Objects.hash(week, startTime, endTime, fee);
    }
}
