package com.vsc.business.gerd.entity.work;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.vsc.constants.Constants;
import com.vsc.modules.entity.IdEntity;

/**
 * 全视频实体类
 * @author XiangXiaoLin
 *
 */
@Entity
@Table(name = Constants.TABLE_PREFIX + "PARKING_VIDEO")
public class ParkingVideo extends IdEntity {

	/**
	 * 车位名称
	 */
	private java.lang.String parkingName;
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
