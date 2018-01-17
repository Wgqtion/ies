package com.vsc.business.gerd.entity.validate.work;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 停车进入验证类
 * @author XiangXiaoLin
 *
 */
public class InParkingOrderValidate{

	/**
	 * 车牌号
	 */
	@NotBlank(message = "inPlateNo不能为空")
	private java.lang.String inPlateNo;
	/**
	 * 进入相机ip
	 */
	@NotNull(message = "inCameraIp不能为空")
	private java.lang.String inCameraIp;
	/**
	 * 进入时间
	 */
	@NotNull(message = "inTime不能为空")
	private java.util.Date inTime;
	/**
	 * 进入照片url
	 */
	@NotNull(message = "inPicName不能为空")
	private java.lang.String inPicName;
	/**
	 * 进去通道（校门）
	 */
	@NotBlank(message = "inSchoolDoorName不能为空")
	private String inSchoolDoorName;
	
	public java.lang.String getInPlateNo() {
		return inPlateNo;
	}
	public void setInPlateNo(java.lang.String inPlateNo) {
		this.inPlateNo = inPlateNo;
	}
	public java.lang.String getInCameraIp() {
		return inCameraIp;
	}
	public void setInCameraIp(java.lang.String inCameraIp) {
		this.inCameraIp = inCameraIp;
	}
	public java.util.Date getInTime() {
		return inTime;
	}
	public void setInTime(java.util.Date inTime) {
		this.inTime = inTime;
	}
	public java.lang.String getInPicName() {
		return inPicName;
	}
	public void setInPicName(java.lang.String inPicName) {
		this.inPicName = inPicName;
	}
	public String getInSchoolDoorName() {
		return inSchoolDoorName;
	}
	public void setInSchoolDoorName(String inSchoolDoorName) {
		this.inSchoolDoorName = inSchoolDoorName;
	}
	
}
