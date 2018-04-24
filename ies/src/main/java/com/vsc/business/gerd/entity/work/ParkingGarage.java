package com.vsc.business.gerd.entity.work;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.vsc.constants.Constants;
import com.vsc.modules.entity.BasicEntity;

/**
 * 车位实体类
 * @author XiangXiaoLin
 *
 */
@Entity
@Table(name = Constants.TABLE_PREFIX + "parking_garage")
public class ParkingGarage extends BasicEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3874215079062066010L;
	private java.lang.String code;
	private java.lang.String name;//车位编号
	private Boolean isEnabled=Boolean.TRUE;
	private ParkingLot parkingLot;
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
	@JoinColumn(name = "PARKING_LOT_CODE",referencedColumnName="CODE")
	public ParkingLot getParkingLot() {
		return this.parkingLot;
	}

	public void setParkingLot(ParkingLot parkingLot) {
		this.parkingLot = parkingLot;
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
