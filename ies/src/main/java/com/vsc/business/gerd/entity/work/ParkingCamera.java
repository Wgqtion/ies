package com.vsc.business.gerd.entity.work;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.vsc.constants.Constants;
import com.vsc.modules.entity.BasicEntity;

/**
 * 全视频相机实体类
 * @author XiangXiaoLin
 *
 */
@Entity
@Table(name = Constants.TABLE_PREFIX + "PARKING_CAMERA")
public class ParkingCamera extends BasicEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7804260704744487257L;
	
	private String code;
	
	private ParkingGarage parkingGarage;
	
	/**
	 * 相机IP
	 */
	private java.lang.String cameraIp;
	
	/**
	 * 车位状态
	 */
	private Integer status=Integer.valueOf(0);
	/**
	 * 车牌号
	 */
	private java.lang.String plateNo;
	
	/**
	 * 日志最后更新时间
	 */
	private Date logUpdateTime;
	
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
	@OneToOne
	@JoinColumn(name = "PARKING_GARAGE_CODE",referencedColumnName="CODE")
	public ParkingGarage getParkingGarage() {
		return this.parkingGarage;
	}

	public void setParkingGarage(ParkingGarage value) {
		this.parkingGarage = value;
	}
	
	@Column(name = "PLATE_NO")
	public String getPlateNo() {
		return plateNo;
	}

	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
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
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
