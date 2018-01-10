package com.vsc.business.gerd.entity.validate.work;

import javax.validation.constraints.NotNull;

/**
 * 停车支付验证类
 * @author XiangXiaoLin
 *
 */
public class PayParkingOrderValidate{
	/**
	 * 车牌号
	 */
	@NotNull(message = "payPlateNo不能为空")
	private java.lang.String payPlateNo;
	
	/**
	 * 收费时间
	 */
	@NotNull(message = "payTime不能为空")
	private java.util.Date payTime;
	/**
	 * 最迟离校时间
	 */
	@NotNull(message = "outTimeLast不能为空")
	private java.util.Date outTimeLast;
	/**
	 * 是否完成收费 0否 1是
	 */
	@NotNull(message = "isPayOk不能为空")
	private Integer isPayOk=Integer.valueOf(0);
	/**
	 * 实收金额
	 */
	@NotNull(message = "ssPayAmount不能为空")
	private Double ssPayAmount;
	/**
	 * 应收金额
	 */
	@NotNull(message = "ysPayAmount不能为空")
	private Double ysPayAmount;
	/**
	 * 收费员
	 */
	@NotNull(message = "memberName不能为空")
	private java.lang.String memberName;
	/**
	 * 在线支付金额
	 */
	@NotNull(message = "onlinePaymentAmount不能为空")
	private Double onlinePaymentAmount;
	/**
	 * 优惠金额
	 */
	@NotNull(message = "amountOfConcessions不能为空")
	private Double amountOfConcessions;
	/**
	 * 公交卡支付金额
	 */
	@NotNull(message = "busCardPaymentAmount不能为空")
	private Double busCardPaymentAmount;
	public java.lang.String getPayPlateNo() {
		return payPlateNo;
	}
	public void setPayPlateNo(java.lang.String payPlateNo) {
		this.payPlateNo = payPlateNo;
	}
	public java.util.Date getPayTime() {
		return payTime;
	}
	public void setPayTime(java.util.Date payTime) {
		this.payTime = payTime;
	}
	public java.util.Date getOutTimeLast() {
		return outTimeLast;
	}
	public void setOutTimeLast(java.util.Date outTimeLast) {
		this.outTimeLast = outTimeLast;
	}
	public Integer getIsPayOk() {
		return isPayOk;
	}
	public void setIsPayOk(Integer isPayOk) {
		this.isPayOk = isPayOk;
	}
	public Double getSsPayAmount() {
		return ssPayAmount;
	}
	public void setSsPayAmount(Double ssPayAmount) {
		this.ssPayAmount = ssPayAmount;
	}
	public Double getYsPayAmount() {
		return ysPayAmount;
	}
	public void setYsPayAmount(Double ysPayAmount) {
		this.ysPayAmount = ysPayAmount;
	}
	public java.lang.String getMemberName() {
		return memberName;
	}
	public void setMemberName(java.lang.String memberName) {
		this.memberName = memberName;
	}
	public Double getOnlinePaymentAmount() {
		return onlinePaymentAmount;
	}
	public void setOnlinePaymentAmount(Double onlinePaymentAmount) {
		this.onlinePaymentAmount = onlinePaymentAmount;
	}
	public Double getAmountOfConcessions() {
		return amountOfConcessions;
	}
	public void setAmountOfConcessions(Double amountOfConcessions) {
		this.amountOfConcessions = amountOfConcessions;
	}
	public Double getBusCardPaymentAmount() {
		return busCardPaymentAmount;
	}
	public void setBusCardPaymentAmount(Double busCardPaymentAmount) {
		this.busCardPaymentAmount = busCardPaymentAmount;
	}

	
}
