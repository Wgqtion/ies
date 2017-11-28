package com.vsc.business.gerd.entity.work;

import java.util.Date;

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
@Table(name = Constants.TABLE_PREFIX + "parking_lock")
public class ParkingLock extends IdEntity {

	private java.lang.String lockNum;
	private java.lang.String deviceNum;
	private java.lang.String ipAddress;
	private java.util.Date createTime;
	private Boolean isEnabled = true;
	private ParkingGarage parkingGarage;
	private Boolean isCarOn = false;
	private java.util.Date isCarOnTime;
	private Boolean isOk = true;
	private java.util.Date isOkTime;
	private Boolean isOnline = false;
	private java.util.Date isOnlineTime;
	private Boolean isOpen;
	private Integer isForeverOpenClose = 0;

	private java.lang.String description;

	/**
	 * 电量
	 */
	private Double power; 
	
	private Integer mcOpen;
	/**
	 * 余位判断
	 */
	private String surplusDetection;
	/**
	 * 日志最后更新时间
	 */
	private Date logUpdateTime;
	/**
	 * @return 日志最后更新时间
	 */
	@Column(name = "LOG_UPDATE_TIME")
	public Date getLogUpdateTime() {
		return logUpdateTime;
	}

	public void setLogUpdateTime(Date logUpdateTime) {
		this.logUpdateTime = logUpdateTime;
	}

	/**
	 * @return 
	 */
	@Column(name = "SURPLUS_DETECTION")
	public String getSurplusDetection() {
		return surplusDetection;
	}

	public void setSurplusDetection(String surplusDetection) {
		this.surplusDetection = surplusDetection;
	}

	/**
	 * @return 
	 */
	@Column(name = "MCOPEN")
	public Integer getMcOpen() {
		return mcOpen;
	}

	public void setMcOpen(Integer mcOpen) {
		this.mcOpen = mcOpen;
	}

	/**
	 * @return 电量
	 */
	@Column(name = "POWER")
	public Double getPower() {
		return power;
	}

	public void setPower(Double power) {
		this.power = power;
	}
	
	/**
	 * @return
	 */
	@Column(name = "LOCK_NUM")
	public java.lang.String getLockNum() {
		return this.lockNum;
	}

	public void setLockNum(java.lang.String value) {
		this.lockNum = value;
	}

	/**
	 * @return
	 */
	@Column(name = "DEVICE_NUM")
	public java.lang.String getDeviceNum() {
		return this.deviceNum;
	}

	public void setDeviceNum(java.lang.String value) {
		this.deviceNum = value;
	}

	/**
	 * @return
	 */
	@Column(name = "IP_ADDRESS")
	public java.lang.String getIpAddress() {
		return this.ipAddress;
	}

	public void setIpAddress(java.lang.String value) {
		this.ipAddress = value;
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
	@Column(name = "IS_ENABLED")
	public Boolean getIsEnabled() {
		return this.isEnabled;
	}

	public void setIsEnabled(Boolean value) {
		this.isEnabled = value;
	}

	/**
	 * @return
	 */
	@ManyToOne
	@JoinColumn(name = "PARKING_GARAGE_ID")
	public ParkingGarage getParkingGarage() {
		return this.parkingGarage;
	}

	public void setParkingGarage(ParkingGarage value) {
		this.parkingGarage = value;
	}

	/**
	 * @return
	 */
	@Column(name = "IS_CAR_ON")
	public Boolean getIsCarOn() {
		return this.isCarOn;
	}

	public void setIsCarOn(Boolean value) {
		this.isCarOn = value;
	}

	/**
	 * @return
	 */
	@Column(name = "IS_CAR_ON_TIME")
	public java.util.Date getIsCarOnTime() {
		return this.isCarOnTime;
	}

	public void setIsCarOnTime(java.util.Date value) {
		this.isCarOnTime = value;
	}

	/**
	 * @return
	 */
	@Column(name = "IS_OK")
	public Boolean getIsOk() {
		return this.isOk;
	}

	public void setIsOk(Boolean value) {
		this.isOk = value;
	}

	/**
	 * @return
	 */
	@Column(name = "IS_OK_TIME")
	public java.util.Date getIsOkTime() {
		return this.isOkTime;
	}

	public void setIsOkTime(java.util.Date value) {
		this.isOkTime = value;
	}

	/**
	 * @return
	 */
	@Column(name = "IS_ONLINE")
	public Boolean getIsOnline() {
		return this.isOnline;
	}

	public void setIsOnline(Boolean value) {
		this.isOnline = value;
	}

	/**
	 * @return
	 */
	@Column(name = "IS_ONLINE_TIME")
	public java.util.Date getIsOnlineTime() {
		return this.isOnlineTime;
	}

	public void setIsOnlineTime(java.util.Date value) {
		this.isOnlineTime = value;
	}

	/**
	 * @return
	 */
	@Column(name = "IS_OPEN")
	public Boolean getIsOpen() {
		return this.isOpen;
	}

	public void setIsOpen(Boolean value) {
		this.isOpen = value;
	}

	/**
	 * @return
	 */
	@Column(name = "DESCRIPTION")
	public java.lang.String getDescription() {
		return this.description;
	}

	@Column(name = "IS_FOREVER_OPEN_CLOSE")
	public Integer getIsForeverOpenClose() {
		return isForeverOpenClose;
	}

	public void setIsForeverOpenClose(Integer isForeverOpenClose) {
		this.isForeverOpenClose = isForeverOpenClose;
	}

	public void setDescription(java.lang.String value) {
		this.description = value;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
