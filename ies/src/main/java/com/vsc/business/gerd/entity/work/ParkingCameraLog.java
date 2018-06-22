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
 * 全视频相机日志实体类
 * @author XiangXiaoLin
 *
 */
@Entity
@Table(name = Constants.TABLE_PREFIX + "PARKING_CAMERA_LOG")
public class ParkingCameraLog extends IdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4169534004967141035L;
	/**
	 * 相机编码
	 */
	private java.lang.String parkingCameraCode;
	private ParkingCamera parkingCamera;
	
	/**
	 * 相机IP
	 */
	private java.lang.String cameraIp;
	
	/**
	 * 车位状态
	 */
	private Integer status;
	/**
	 * 车牌号
	 */
	private java.lang.String plateNo;
	/**
	 * 进入时间
	 */
	private java.util.Date inTime;
	/**
	 * 创建时间
	 */
	private java.util.Date createTime;

	/**
	 * 场区编码
	 */
	private String parkingLotCode;
	
	@Transient
	public String getParkingLotCode() {
		return parkingLotCode;
	}

	public void setParkingLotCode(String parkingLotCode) {
		this.parkingLotCode = parkingLotCode;
	}

	/**
	 * @return
	 */
	@Column(name = "PARKING_CAMERA_CODE")
	public java.lang.String getParkingCameraCode() {
		return parkingCameraCode;
	}

	public void setParkingCameraCode(java.lang.String parkingCameraCode) {
		this.parkingCameraCode = parkingCameraCode;
	}

	/**
	 * @return
	 */
	@ManyToOne
	@JoinColumn(name = "PARKING_CAMERA_CODE",referencedColumnName="CODE",insertable=false,updatable=false)	
	public ParkingCamera getParkingCamera() {
		return parkingCamera;
	}

	public void setParkingCamera(ParkingCamera parkingCamera) {
		this.parkingCamera = parkingCamera;
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
	@Column(name = "PLATE_NO")
	public java.lang.String getPlateNo() {
		return plateNo;
	}

	public void setPlateNo(java.lang.String plateNo) {
		this.plateNo = plateNo;
	}
	/**
	 * @return
	 */
	@Column(name = "IN_TIME")
	public java.util.Date getInTime() {
		return inTime;
	}

	public void setInTime(java.util.Date inTime) {
		this.inTime = inTime;
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
