package com.vsc.business.gerd.entity.work;

import java.io.Serializable;
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
 * 进出口记录实体
 * @author XiangXiaoLin
 *
 */
@Entity
@Table(name = Constants.TABLE_PREFIX + "parking_in_out")
public class ParkingInOut extends IdEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -844106451317077383L;
	/**
	 * 车牌号
	 */
	private java.lang.String plateNo;
	/**
	 * 进入相机ip
	 */
	private java.lang.String inCameraIp;
	/**
	 * 进入时间
	 */
	private java.util.Date inTime;
	/**
	 * 进入照片url
	 */
	private java.lang.String inPicName;
	/**
	 * 进去通道（校门）
	 */
	private String inSchoolDoorName;
	/**
	 * 进去通道 查询用
	 */
	private ParkingPassages inPassages;

	/**
	 * 出去相机IP
	 */
	private java.lang.String outCameraIp;
	/**
	 * 出去时间
	 */
	private java.util.Date outTime;
	/**
	 * 出去照片URL
	 */
	private java.lang.String outPicName;
	/**
	 * 出去通道（校门）
	 */
	private String outSchoolDoorName;
	/**
	 * 出去通道 查询用
	 */
	private ParkingPassages outPassages;

	/**
	 * 收费时间
	 */
	private java.util.Date payTime;
	/**
	 * 最迟离校时间
	 */
	private java.util.Date outTimeLast;
	/**
	 * 是否完成收费 0否 1是
	 */
	private Integer isPayOk=Integer.valueOf(0);
	/**
	 * 实收金额
	 */
	private Double ssPayAmount;
	/**
	 * 应收金额
	 */
	private Double ysPayAmount;
	/**
	 * 优惠方式 (0.无 1.人工优惠 2.二维码优惠 3.优惠券优惠 4.evcard优惠)
	 */
	private Integer preferentialWay;
	/**
	 * 优惠劵编号
	 */
	private java.lang.String preferentialNum;
	/**
	 * 收费员
	 */
	private java.lang.String memberName;
	/**
	 * 在线支付金额
	 */
	private Double onlinePaymentAmount;
	/**
	 * 优惠金额
	 */
	private Double amountOfConcessions;
	/**
	 * 公交卡支付金额
	 */
	private Double busCardPaymentAmount;

	/**
	 * 支付编号
	 */
	private String payNumber;
	/**
	 * 单子状态 0未完成，1表示结束（只允许存在一个车牌一个0的单子）
	 */
	private Integer orderStatus=Integer.valueOf(0);
	/**
	 * 创建时间
	 */
	private java.util.Date createTime;
	/**
	 * 更新出去时间
	 */
	private Date updateOutTime;
	/**
	 * 更新支付时间
	 */
	private java.util.Date updatePayTime;

	@Column(name = "PLATE_NO")
	public String getPlateNo() {
		return plateNo;
	}

	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}
	
	@Column(name = "ORDER_STATUS")
	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	@Column(name = "PAY_NUMBER")
	public String getPayNumber() {
		return payNumber;
	}

	public void setPayNumber(String payNumber) {
		this.payNumber = payNumber;
	}

	@Column(name = "PAY_TIME")
	public java.util.Date getPayTime() {
		return payTime;
	}

	public void setPayTime(java.util.Date payTime) {
		this.payTime = payTime;
	}

	@Column(name = "SS_PAY_AMOUNT")
	public Double getSsPayAmount() {
		return ssPayAmount;
	}

	public void setSsPayAmount(Double ssPayAmount) {
		this.ssPayAmount = ssPayAmount;
	}

	@Column(name = "YS_PAY_AMOUNT")
	public Double getYsPayAmount() {
		return ysPayAmount;
	}

	public void setYsPayAmount(Double ysPayAmount) {
		this.ysPayAmount = ysPayAmount;
	}

	@Column(name = "PREFERENTIAL_WAY")
	public Integer getPreferentialWay() {
		return preferentialWay;
	}

	public void setPreferentialWay(Integer preferentialWay) {
		this.preferentialWay = preferentialWay;
	}

	@Column(name = "IN_SCHOOL_DOOR_NAME")
	public String getInSchoolDoorName() {
		return inSchoolDoorName;
	}

	public void setInSchoolDoorName(String inSchoolDoorName) {
		this.inSchoolDoorName = inSchoolDoorName;
	}
	
	@ManyToOne
	@JoinColumn(name = "IN_SCHOOL_DOOR_NAME",referencedColumnName ="CODE",insertable=false,updatable=false)
	public ParkingPassages getInPassages() {
		return inPassages;
	}

	public void setInPassages(ParkingPassages inPassages) {
		this.inPassages = inPassages;
	}

	@Column(name = "OUT_SCHOOL_DOOR_NAME")
	public String getOutSchoolDoorName() {
		return outSchoolDoorName;
	}

	public void setOutSchoolDoorName(String outSchoolDoorName) {
		this.outSchoolDoorName = outSchoolDoorName;
	}

	@ManyToOne
	@JoinColumn(name = "OUT_SCHOOL_DOOR_NAME",referencedColumnName ="CODE",insertable=false,updatable=false)
	public ParkingPassages getOutPassages() {
		return outPassages;
	}

	public void setOutPassages(ParkingPassages outPassages) {
		this.outPassages = outPassages;
	}

	@Column(name = "IS_PAY_OK")
	public Integer getIsPayOk() {
		return isPayOk;
	}

	public void setIsPayOk(Integer isPayOk) {
		this.isPayOk = isPayOk;
	}

	@Column(name = "ONLINE_PAYMENT_AMOUNT")
	public Double getOnlinePaymentAmount() {
		return onlinePaymentAmount;
	}

	public void setOnlinePaymentAmount(Double onlinePaymentAmount) {
		this.onlinePaymentAmount = onlinePaymentAmount;
	}

	@Column(name = "AMOUNT_OF_CONCESSIONS")
	public Double getAmountOfConcessions() {
		return amountOfConcessions;
	}

	public void setAmountOfConcessions(Double amountOfConcessions) {
		this.amountOfConcessions = amountOfConcessions;
	}

	@Column(name = "BUS_CARD_PAYMENT_AMOUNT")
	public Double getBusCardPaymentAmount() {
		return busCardPaymentAmount;
	}

	public void setBusCardPaymentAmount(Double busCardPaymentAmount) {
		this.busCardPaymentAmount = busCardPaymentAmount;
	}

	@Column(name = "MEMBER_NAME")
	public java.lang.String getMemberName() {
		return memberName;
	}

	public void setMemberName(java.lang.String memberName) {
		this.memberName = memberName;
	}

	@Column(name = "PREFERENTIAL_NUM")
	public java.lang.String getPreferentialNum() {
		return preferentialNum;
	}

	public void setPreferentialNum(java.lang.String preferentialNum) {
		this.preferentialNum = preferentialNum;
	}

	/**
	 * @return
	 */
	@Column(name = "OUT_TIME_LAST")
	public java.util.Date getOutTimeLast() {
		return this.outTimeLast;
	}

	public void setOutTimeLast(java.util.Date outTimeLast) {
		this.outTimeLast = outTimeLast;
	}

	/**
	 * @return
	 */
	@Column(name = "OUT_CAMERA_IP")
	public java.lang.String getOutCameraIp() {
		return this.outCameraIp;
	}

	public void setOutCameraIp(java.lang.String value) {
		this.outCameraIp = value;
	}

	/**
	 * @return
	 */
	@Column(name = "IN_CAMERA_IP")
	public java.lang.String getInCameraIp() {
		return this.inCameraIp;
	}

	public void setInCameraIp(java.lang.String value) {
		this.inCameraIp = value;
	}

	/**
	 * @return
	 */
	@Column(name = "IN_TIME")
	public java.util.Date getInTime() {
		return this.inTime;
	}

	public void setInTime(java.util.Date value) {
		this.inTime = value;
	}

	/**
	 * @return
	 */
	@Column(name = "IN_PIC_NAME")
	public java.lang.String getInPicName() {
		return this.inPicName;
	}

	public void setInPicName(java.lang.String value) {
		this.inPicName = value;
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

	
	@Column(name = "UPDATE_OUT_TIME")
	public Date getUpdateOutTime() {
		return updateOutTime;
	}

	public void setUpdateOutTime(Date updateOutTime) {
		this.updateOutTime = updateOutTime;
	}
	@Column(name = "UPDATE_PAY_TIME")
	public java.util.Date getUpdatePayTime() {
		return updatePayTime;
	}

	public void setUpdatePayTime(java.util.Date updatePayTime) {
		this.updatePayTime = updatePayTime;
	}

	/**
	 * @return
	 */
	@Column(name = "OUT_TIME")
	public java.util.Date getOutTime() {
		return this.outTime;
	}

	public void setOutTime(java.util.Date value) {
		this.outTime = value;
	}

	/**
	 * @return
	 */
	@Column(name = "OUT_PIC_NAME")
	public java.lang.String getOutPicName() {
		return this.outPicName;
	}

	public void setOutPicName(java.lang.String value) {
		this.outPicName = value;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
