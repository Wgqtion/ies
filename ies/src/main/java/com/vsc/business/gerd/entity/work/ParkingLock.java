package com.vsc.business.gerd.entity.work;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.vsc.constants.Constants;
import com.vsc.modules.entity.BasicEntity;

/**
 * 地锁实体类
 * @author XiangXiaoLin
 *
 */
@Entity
@Table(name = Constants.TABLE_PREFIX + "parking_lock")
public class ParkingLock extends BasicEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2340178890670270784L;
	private String code;
	private java.lang.String lockNum;
	private java.lang.String ipAddress;
	private Boolean isEnabled = true;
	private ParkingGarage parkingGarage;
	private Boolean isCarOn = false;
	private Boolean isOnline = false;
	private Boolean isOpen = false;
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
	 * 是否剩余，是否可用的意思
	 */
	@Transient
	public boolean getIsSurplus(){
		if(surplusDetection!=null){
			if("1,2".equals(surplusDetection)){
				if(isCarOn||isOpen){
					return false;
				}
			}else if(surplusDetection.contains("1")){
				if(isCarOn){
					return false;
				}
			}else if(surplusDetection.contains("2")){
				if(isOpen){
					return false;
				}
			}	
		}
		return true;
	}
	
	
	/**
	 * @return
	 */
	@Column(name = "CODE")
	public java.lang.String getCode() {
		return this.code;
	}

	public void setCode(java.lang.String value) {
		this.code = value;
	}
	
	
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
	@OneToOne
	@JoinColumn(name = "PARKING_GARAGE_CODE",referencedColumnName="CODE")
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

	public void setDescription(java.lang.String value) {
		this.description = value;
	}

	@Column(name = "IS_FOREVER_OPEN_CLOSE")
	public Integer getIsForeverOpenClose() {
		return isForeverOpenClose;
	}

	public void setIsForeverOpenClose(Integer isForeverOpenClose) {
		this.isForeverOpenClose = isForeverOpenClose;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
