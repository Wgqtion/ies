package com.vsc.business.gerd.entity.work;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Athor: 吴广庆
 * @Date: 2018-08-17
 */
public class ChargeRecord {
    private BigDecimal sumFee = new BigDecimal(0);
    private Date startComputeTime;
    private Date endComputeTime;
    private BigDecimal MaxFee;
    private List<ChargeRecord> chargeRecordList;

    public BigDecimal getSumFee() {
        return sumFee;
    }

    public void setSumFee(BigDecimal sumFee) {
        this.sumFee = sumFee;
    }

    public Date getStartComputeTime() {
        return startComputeTime;
    }

    public void setStartComputeTime(Date startComputeTime) {
        this.startComputeTime = startComputeTime;
    }

    public Date getEndComputeTime() {
        return endComputeTime;
    }

    public void setEndComputeTime(Date endComputeTime) {
        this.endComputeTime = endComputeTime;
    }

    public BigDecimal getMaxFee() {
        return MaxFee;
    }

    public void setMaxFee(BigDecimal maxFee) {
        MaxFee = maxFee;
    }

    public List<ChargeRecord> getChargeRecordList() {
        return chargeRecordList;
    }

    public void setChargeRecordList(List<ChargeRecord> chargeRecordList) {
        this.chargeRecordList = chargeRecordList;
    }
}
