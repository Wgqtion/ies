package com.vsc.business.gerd.entity.work;

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
 * 
 * @author jerry
 *
 */
@Entity
@Table(name = Constants.TABLE_PREFIX + "parking_order")
public class ParkingOrder extends IdEntity {

	private java.lang.String inCameraIp;
	private java.lang.String inPlateNo;
	private java.util.Date inTime;
	private java.lang.String inChargeUser;
	private java.lang.String inChargeStarttime;
	private java.lang.String inPicName;
	private java.lang.String inFindstr;
	private Passages inDoor;
	private java.lang.String inSourceIp;
	private java.lang.String inSourceName;
	private java.util.Date createTime=new Date();
	private java.util.Date updateTime;
	private Boolean isEnabled;
	private Integer carGategory;
	private Integer carType;
	private java.util.Date outTime;
	private Double staytime;
	private Passages outDoor;
	private Integer parkingTimeType;
	private Double amountsReceivable;
	private Double amountsPaid;
	private Integer isAmountSuccess;
	private java.lang.String failReason;
	private java.lang.String outPicName;
	private Integer operateFlag;
	private Integer amountType;
	private Boolean amountOnlineOk;
	private java.util.Date amountTime;
	private java.lang.String orderNumber;
	private java.lang.String paymentOrderNumber;
	private ParkingGarage parkingGarage;
	
	
	private java.lang.String outCameraIp;
	private java.util.Date outTimeLast;
	private java.lang.String preferentialWay;
	private java.lang.String preferentialNum;
	private java.lang.String memberName;
	private Double onlinePaymentAmount;
	private Double amountOfConcessions;
	private Double busCardPaymentAmount;
	
	
	
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
	@Column(name = "PREFERENTIAL_WAY")
	public java.lang.String getPreferentialWay() {
		return preferentialWay;
	}

	public void setPreferentialWay(java.lang.String preferentialWay) {
		this.preferentialWay = preferentialWay;
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
	@Column(name = "IN_PLATE_NO")
	public java.lang.String getInPlateNo() {
		return this.inPlateNo;
	}

	public void setInPlateNo(java.lang.String value) {
		this.inPlateNo = value;
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
	@Column(name = "IN_CHARGE_USER")
	public java.lang.String getInChargeUser() {
		return this.inChargeUser;
	}

	public void setInChargeUser(java.lang.String value) {
		this.inChargeUser = value;
	}

	/**
	 * @return
	 */
	@Column(name = "IN_CHARGE_STARTTIME")
	public java.lang.String getInChargeStarttime() {
		return this.inChargeStarttime;
	}

	public void setInChargeStarttime(java.lang.String value) {
		this.inChargeStarttime = value;
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
	@Column(name = "IN_FINDSTR")
	public java.lang.String getInFindstr() {
		return this.inFindstr;
	}

	public void setInFindstr(java.lang.String value) {
		this.inFindstr = value;
	}

	/**
	 * @return
	 */
	@ManyToOne
	@JoinColumn(name = "IN_DOOR")
	public Passages getInDoor() {
		return this.inDoor;
	}

	public void setInDoor(Passages value) {
		this.inDoor = value;
	}

	/**
	 * @return
	 */
	@Column(name = "IN_SOURCE_IP")
	public java.lang.String getInSourceIp() {
		return this.inSourceIp;
	}

	public void setInSourceIp(java.lang.String value) {
		this.inSourceIp = value;
	}

	/**
	 * @return
	 */
	@Column(name = "IN_SOURCE_NAME")
	public java.lang.String getInSourceName() {
		return this.inSourceName;
	}

	public void setInSourceName(java.lang.String value) {
		this.inSourceName = value;
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
	@Column(name = "UPDATE_TIME")
	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(java.util.Date value) {
		this.updateTime = value;
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
	@Column(name = "CAR_GATEGORY")
	public Integer getCarGategory() {
		return this.carGategory;
	}

	public void setCarGategory(Integer value) {
		this.carGategory = value;
	}

	/**
	 * @return
	 */
	@Column(name = "CAR_TYPE")
	public Integer getCarType() {
		return this.carType;
	}

	public void setCarType(Integer value) {
		this.carType = value;
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
	@Column(name = "STAYTIME")
	public Double getStaytime() {
		return this.staytime;
	}

	public void setStaytime(Double value) {
		this.staytime = value;
	}

	/**
	 * @return
	 */
	@ManyToOne
	@JoinColumn(name = "OUT_DOOR")
	public Passages getOutDoor() {
		return this.outDoor;
	}

	public void setOutDoor(Passages value) {
		this.outDoor = value;
	}

	/**
	 * @return
	 */
	@Column(name = "PARKING_TIME_TYPE")
	public Integer getParkingTimeType() {
		return this.parkingTimeType;
	}

	public void setParkingTimeType(Integer value) {
		this.parkingTimeType = value;
	}

	/**
	 * @return
	 */
	@Column(name = "AMOUNTS_RECEIVABLE")
	public Double getAmountsReceivable() {
		return this.amountsReceivable;
	}

	public void setAmountsReceivable(Double value) {
		this.amountsReceivable = value;
	}

	/**
	 * @return
	 */
	@Column(name = "AMOUNTS_PAID")
	public Double getAmountsPaid() {
		return this.amountsPaid;
	}

	public void setAmountsPaid(Double value) {
		this.amountsPaid = value;
	}

	/**
	 * @return
	 */
	@Column(name = "IS_AMOUNT_SUCCESS")
	public Integer getIsAmountSuccess() {
		return this.isAmountSuccess;
	}

	public void setIsAmountSuccess(Integer value) {
		this.isAmountSuccess = value;
	}

	/**
	 * @return
	 */
	@Column(name = "FAIL_REASON")
	public java.lang.String getFailReason() {
		return this.failReason;
	}

	public void setFailReason(java.lang.String value) {
		this.failReason = value;
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

	/**
	 * @return
	 */
	@Column(name = "OPERATE_FLAG")
	public Integer getOperateFlag() {
		return this.operateFlag;
	}

	public void setOperateFlag(Integer value) {
		this.operateFlag = value;
	}

	/**
	 * @return
	 */
	@Column(name = "AMOUNT_TYPE")
	public Integer getAmountType() {
		return this.amountType;
	}

	public void setAmountType(Integer value) {
		this.amountType = value;
	}

	/**
	 * @return
	 */
	@Column(name = "AMOUNT_ONLINE_OK")
	public Boolean getAmountOnlineOk() {
		return this.amountOnlineOk;
	}

	public void setAmountOnlineOk(Boolean value) {
		this.amountOnlineOk = value;
	}

	/**
	 * @return
	 */
	@Column(name = "AMOUNT_TIME")
	public java.util.Date getAmountTime() {
		return this.amountTime;
	}

	public void setAmountTime(java.util.Date value) {
		this.amountTime = value;
	}

	/**
	 * @return
	 */
	@Column(name = "ORDER_NUMBER")
	public java.lang.String getOrderNumber() {
		return this.orderNumber;
	}

	public void setOrderNumber(java.lang.String value) {
		this.orderNumber = value;
	}

	/**
	 * @return
	 */
	@Column(name = "PAYMENT_ORDER_NUMBER")
	public java.lang.String getPaymentOrderNumber() {
		return this.paymentOrderNumber;
	}

	public void setPaymentOrderNumber(java.lang.String value) {
		this.paymentOrderNumber = value;
	}

	
	@ManyToOne
	@JoinColumn(name = "PARKING_GARAGE_ID")
	public ParkingGarage getParkingGarage() {
		return parkingGarage;
	}

	public void setParkingGarage(ParkingGarage parkingGarage) {
		this.parkingGarage = parkingGarage;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
