package com.vsc.business.gerd.entity.work;


import com.vsc.constants.Constants;
import com.vsc.modules.entity.BasicEntity;
import com.vsc.util.ChargeHandle;

import javax.persistence.*;
import java.sql.Time;
import java.text.DecimalFormat;
import java.util.List;

@Entity
@Table(name = Constants.TABLE_PREFIX + "charges_settings")
public class ChargesSettings extends BasicEntity {

    private String name;
    private Integer chargeStandard;
    private Double unitPrice;
    private Long priceTime;
    private String priceTimeString;
    private Double privilegeFee;
    private Double maxFee = 0.0;
    private String description;
    private List<TimeSection> timeSectionList;


    /**
     * 名称
     *
     * @return
     */
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 计费类型：1按次收费；2按时收费跨段取左；3跨段收费取右；4跨段收费取两段；5跨段收费取最大
     *
     * @return
     */
    @Column(name = "CHARGE_STANDARD")
    public Integer getChargeStandard() {
        return chargeStandard;
    }

    public void setChargeStandard(Integer chargeStandard) {
        this.chargeStandard = chargeStandard;
    }


    /**
     * 按次收费单价
     *
     * @return
     */
    @Column(name = "UNIT_PRICE")
    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * 按时收费时间单位
     */
    @Column(name = "PRICE_TIME")
    public Long getPriceTime() {
        return priceTime;
    }

    public void setPriceTime(Long priceTime) {
        this.priceTime = priceTime;
    }

    @Transient
    public String getPriceTimeString() {
        String priceTimeString = "00:00";
        DecimalFormat decimalFormat = new DecimalFormat("00");
        if (priceTime != null) {
            String hour = decimalFormat.format(priceTime / ChargeHandle.HOUR_1);
            String min = decimalFormat.format((priceTime % ChargeHandle.HOUR_1) / ChargeHandle.MIN_1);
            priceTimeString = hour + ":" + min;
        }
        return priceTimeString;
    }

    public void setPriceTimeString(String priceTimeString) {
        String[] priceTime = priceTimeString.split(":");
        if (priceTime != null && priceTime.length != 0) {
            setPriceTime(Long.valueOf(priceTime[0])*ChargeHandle.HOUR_1+Long.valueOf(priceTime[1])*ChargeHandle.MIN_1);
        }
    }

    /**
     * 优惠减免费用
     *
     * @return
     */
    @Column(name = "PRIVILEGE_FEE")
    public Double getPrivilegeFee() {
        return privilegeFee;
    }

    public void setPrivilegeFee(Double privilegeFee) {
        this.privilegeFee = privilegeFee;
    }

    /**
     * 最高费用
     *
     * @return
     */
    @Column(name = "MAX_FEE")
    public Double getMaxFee() {
        return maxFee;
    }

    public void setMaxFee(Double maxFee) {
        this.maxFee = maxFee;
    }

    /**
     * 描述
     *
     * @return
     */
    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToMany(mappedBy = "chargesSettings")
    @OrderBy("week ASC,startTime ASC")
    public List<TimeSection> getTimeSectionList() {
        return timeSectionList;
    }

    public void setTimeSectionList(List<TimeSection> timeSectionList) {
        this.timeSectionList = timeSectionList;
    }
}
