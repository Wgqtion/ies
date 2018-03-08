package com.vsc.business.gerd.entity.work;


import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class Yuyue implements Serializable {

    private Long orderNumber;
    private java.util.Date yuyueTime;
    private java.util.Date createTime;
    private Boolean isLockedOk = false;
    private Boolean isEnabled = true;
    private Boolean isDelete = false;
    private Long wxUserId;
    private String carNumber;
    private ParkingGarage parkingGarage;
    private YudingSetting yudingSetting;
    private Double shoufei;

    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     * @return
     */
    public java.util.Date getYuyueTime() {
        return this.yuyueTime;
    }

    public void setYuyueTime(java.util.Date value) {
        this.yuyueTime = value;
    }

    /**
     * @return
     */
    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(java.util.Date value) {
        this.createTime = value;
    }

    /**
     * @return
     */
    public Boolean getIsLockedOk() {
        return this.isLockedOk;
    }

    public void setIsLockedOk(Boolean value) {
        this.isLockedOk = value;
    }

    /**
     * @return
     */
    public Boolean getIsEnabled() {
        return this.isEnabled;
    }

    public void setIsEnabled(Boolean value) {
        this.isEnabled = value;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Long getWxUserId() {
        return wxUserId;
    }

    public void setWxUserId(Long wxUserId) {
        this.wxUserId = wxUserId;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }
    public ParkingGarage getParkingGarage() {
        return parkingGarage;
    }

    public void setParkingGarage(ParkingGarage parkingGarage) {
        this.parkingGarage = parkingGarage;
    }

    //预约设置
    public YudingSetting getYudingSetting() {
        return yudingSetting;
    }

    public void setYudingSetting(YudingSetting yudingSetting) {
        this.yudingSetting = yudingSetting;
    }

    //收费

    public Double getShoufei() {
        return shoufei;
    }

    public void setShoufei(Double shoufei) {
        this.shoufei = shoufei;
    }
    
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
