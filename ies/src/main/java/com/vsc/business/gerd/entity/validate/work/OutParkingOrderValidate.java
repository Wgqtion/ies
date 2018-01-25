package com.vsc.business.gerd.entity.validate.work;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 停车出去验证类
 * @author XiangXiaoLin
 *
 */
public class OutParkingOrderValidate{

	/**
	 * 车牌号
	 */
	@NotBlank(message = "outPlateNo不能为空")
	private java.lang.String outPlateNo;

	/**
	 * 出去相机IP
	 */
	@NotBlank(message = "outCameraIp不能为空")
	private java.lang.String outCameraIp;
	/**
	 * 出去时间
	 */
	@NotNull(message = "outTime不能为空")
	private java.util.Date outTime;
	/**
	 * 出去照片URL
	 */
	@NotNull(message = "outPicName不能为空")
	private java.lang.String outPicName;
	/**
	 * 出去通道（校门）
	 */
	@NotBlank(message = "outSchoolDoorName不能为空")
	private String outSchoolDoorName;
	
	public java.lang.String getOutPlateNo() {
		return outPlateNo;
	}
	public void setOutPlateNo(java.lang.String outPlateNo) {
		this.outPlateNo = outPlateNo;
	}
	public java.lang.String getOutCameraIp() {
		return outCameraIp;
	}
	public void setOutCameraIp(java.lang.String outCameraIp) {
		this.outCameraIp = outCameraIp;
	}
	public java.util.Date getOutTime() {
		return outTime;
	}
	public void setOutTime(java.util.Date outTime) {
		this.outTime = outTime;
	}
	public java.lang.String getOutPicName() {
		return outPicName;
	}
	public void setOutPicName(java.lang.String outPicName) {
		this.outPicName = outPicName;
	}
	public String getOutSchoolDoorName() {
		return outSchoolDoorName;
	}
	public void setOutSchoolDoorName(String outSchoolDoorName) {
		this.outSchoolDoorName = outSchoolDoorName;
	}
	
	
}
