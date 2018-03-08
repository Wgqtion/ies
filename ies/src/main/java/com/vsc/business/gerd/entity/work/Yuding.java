package com.vsc.business.gerd.entity.work;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.vsc.constants.Constants;
import com.vsc.modules.entity.IdEntity;

/**
 *
 * @author jerry
 *
 */
@Entity
@Table(name = Constants.TABLE_PREFIX + "yuding")
public class Yuding extends IdEntity implements Serializable {

    private java.util.Date lockedStartTime;
    private java.util.Date yuyueTime;
    private Integer lockedMinutes;
    private Double lockedCost;
    private Double lockedHourCost;
    private java.util.Date createTime;
    private Long lasttime;
    private Boolean isLockedOk = false;
    private Boolean isEnabled = true;
    private Boolean isDelete = false;
    private WxUser wxUser;
    private String carNo;
    private CardInfo cardInfo;
    private ParkingLotArea parkingLotArea;
    private ParkingGarage parkingGarage;

    /**
     * @return
     */
    @Column(name = "LOCKED_START_TIME")
    public java.util.Date getLockedStartTime() {
        return this.lockedStartTime;
    }

    public void setLockedStartTime(java.util.Date value) {
        this.lockedStartTime = value;
    }

    /**
     * @return
     */
    @Column(name = "YUYUE_TIME")
    public java.util.Date getYuyueTime() {
        return this.yuyueTime;
    }

    public void setYuyueTime(java.util.Date value) {
        this.yuyueTime = value;
    }

    /**
     * @return
     */
    @Column(name = "LOCKED_MINUTES")
    public Integer getLockedMinutes() {
        return this.lockedMinutes;
    }

    public void setLockedMinutes(Integer value) {
        this.lockedMinutes = value;
    }

    /**
     * @return
     */
    @Column(name = "LOCKED_COST")
    public Double getLockedCost() {
        return this.lockedCost;
    }

    public void setLockedCost(Double value) {
        this.lockedCost = value;
    }

    /**
     * @return
     */
    @Column(name = "LOCKED_HOUR_COST")
    public Double getLockedHourCost() {
        return this.lockedHourCost;
    }

    public void setLockedHourCost(Double value) {
        this.lockedHourCost = value;
    }

    /**
     * @return
     */
    @Column(name = "CREATE_TIME")
    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(java.util.Date value) {
        this.createTime = value;
    }

    /**
     * @return
     */
    @Column(name = "LASTTIME")
    public Long getLasttime() {
        return this.lasttime;
    }

    public void setLasttime(Long value) {
        this.lasttime = value;
    }

    /**
     * @return
     */
    @Column(name = "IS_LOCKED_OK")
    public Boolean getIsLockedOk() {
        return this.isLockedOk;
    }

    public void setIsLockedOk(Boolean value) {
        this.isLockedOk = value;
    }

    /**
     * @return
     */
    @Column(name = "IS_ENABLED")
    public Boolean getIsEnabled() {
        return this.isEnabled;
    }

    public void setIsEnabled(Boolean value) {
        this.isEnabled = value;
    }

    @Column(name = "IS_DELETE")
    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    @ManyToOne
    @JoinColumn(name = "WX_USER_ID")
    public WxUser getWxUser() {
		return wxUser;
	}

	public void setWxUser(WxUser wxUser) {
		this.wxUser = wxUser;
	}

	@Column(name = "CAR_NO")
    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    @ManyToOne
    @JoinColumn(name = "CAR_INFO_ID")
    public CardInfo getCardInfo() {
        return cardInfo;
    }

    public void setCardInfo(CardInfo cardInfo) {
        this.cardInfo = cardInfo;
    }

    @ManyToOne
    @JoinColumn(name = "PARKING_LOT_AREA_ID")
    public ParkingLotArea getParkingLotArea() {
        return parkingLotArea;
    }

    public void setParkingLotArea(ParkingLotArea parkingLotArea) {
        this.parkingLotArea = parkingLotArea;
    }

    @ManyToOne
    @JoinColumn(name = "PARKING_GARAGE_ID")
    public ParkingGarage getParkingGarage() {
        return parkingGarage;
    }

    public void setParkingGarage(ParkingGarage parkingGarage) {
        this.parkingGarage = parkingGarage;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
