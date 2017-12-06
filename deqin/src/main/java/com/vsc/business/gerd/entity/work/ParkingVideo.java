package com.vsc.business.gerd.entity.work;

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
@Table(name = Constants.TABLE_PREFIX + "parking_garage_carno_log")
public class ParkingGarageCarnoLog extends IdEntity {

	private ParkingGarage parkingGarage;// 车位编号
	private java.lang.String parkingName;// 车位编号
	private java.lang.String cameraIp;
	private Integer status;
	private java.lang.String carNo;
	private java.util.Date intime;
	private java.util.Date createTime;

	/**
	 * @return
	 */
	@Column(name = "PARKING_NAME")
	public java.lang.String getParkingName() {
		return this.parkingName;
	}

	public void setParkingName(java.lang.String value) {
		this.parkingName = value;
	}

	/**
	 * @return
	 */
	@Column(name = "CAMERA_IP")
	public java.lang.String getCameraIp() {
		return this.cameraIp;
	}

	public void setCameraIp(java.lang.String value) {
		this.cameraIp = value;
	}

	/**
	 * @return
	 */
	@Column(name = "STATUS")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer value) {
		this.status = value;
	}

	/**
	 * @return
	 */
	@Column(name = "CAR_NO")
	public java.lang.String getCarNo() {
		return this.carNo;
	}

	public void setCarNo(java.lang.String value) {
		this.carNo = value;
	}

	@ManyToOne
	@JoinColumn(name = "PARKING_GARAGE_ID")
	public ParkingGarage getParkingGarage() {
		return parkingGarage;
	}

	public void setParkingGarage(ParkingGarage parkingGarage) {
		this.parkingGarage = parkingGarage;
	}

	/**
	 * @return
	 */
	@Column(name = "INTIME")
	public java.util.Date getIntime() {
		return this.intime;
	}

	public void setIntime(java.util.Date value) {
		this.intime = value;
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

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
