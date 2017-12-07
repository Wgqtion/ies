package com.vsc.business.gerd.entity.work;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.vsc.constants.Constants;
import com.vsc.modules.entity.IdEntity;

/**
 * 
 * @author Administrator
 * 
 */
@Entity
@Table(name = Constants.TABLE_PREFIX + "parking_garage")
public class ParkingGarage extends IdEntity {

	private java.lang.String code;
	private java.lang.String name;//车位编号
	private java.lang.String ipAddress;//节点编号
	private java.util.Date createTime;
	private Boolean isEnabled=Boolean.TRUE;
	private ParkingLotArea parkingLotArea;
	private Integer garageType;
	private java.lang.String description;
	private java.lang.String xcoordinate;//x坐标
	private java.lang.String ycoordinate;//y坐标

	
	private ParkingLock parkingLock=new ParkingLock();
	
	@Transient
	public ParkingLock getParkingLock() {
		return parkingLock;
	}

	public void setParkingLock(ParkingLock parkingLock) {
		this.parkingLock = parkingLock;
	}
	
	@Transient
	public Boolean getIsCarOn() {
		return parkingLock.getIsCarOn();
	}
	
	@Transient
	public Boolean getIsOpen() {
		return parkingLock.getIsOpen();
	}

	@Column(name = "XCOORDINATE")
	public java.lang.String getXcoordinate() {
		return xcoordinate;
	}

	public void setXcoordinate(java.lang.String xcoordinate) {
		this.xcoordinate = xcoordinate;
	}
	@Column(name = "YCOORDINATE")
	public java.lang.String getYcoordinate() {
		return ycoordinate;
	}

	public void setYcoordinate(java.lang.String ycoordinate) {
		this.ycoordinate = ycoordinate;
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
	 * @return
	 */
	@Column(name = "NAME")
	public java.lang.String getName() {
		return this.name;
	}

	public void setName(java.lang.String value) {
		this.name = value;
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
	@JoinColumn(name = "PARKING_LOT_AREA_ID")
	public ParkingLotArea getParkingLotArea() {
		return this.parkingLotArea;
	}

	public void setParkingLotArea(ParkingLotArea value) {
		this.parkingLotArea = value;
	}

	/**
	 * @return
	 */
	@Column(name = "GARAGE_TYPE")
	public Integer getGarageType() {
		return this.garageType;
	}

	public void setGarageType(Integer value) {
		this.garageType = value;
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

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
