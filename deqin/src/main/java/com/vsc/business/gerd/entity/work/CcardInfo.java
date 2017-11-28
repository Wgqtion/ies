package com.vsc.business.gerd.entity.work;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.vsc.constants.Constants;
import com.vsc.modules.entity.IdEntity;

 

/**
 * 原始进校证数据库card_info
 * @author Administrator
 *
 */
@Entity
@Table(name = Constants.TABLE_PREFIX +"ccardInfo")
public class CcardInfo extends IdEntity {

	 
     private java.lang.String cardNo;
     private Double cardTypeId;
     private java.lang.String status;
     private java.lang.String owner;
     private java.lang.String carNo;
     private java.util.Date expireDate;
     private java.lang.String yname;
     private java.lang.String xmIds;
     private Integer lasttime;
     private java.lang.String typeName;
     private Double typeId;
     private java.lang.String userNo;
     private java.lang.String userName;
     private Double frontPrints;
     private Double bgPrints;
     private Double userTypeId;
     private java.lang.String isPay;
     private java.lang.String carType;
     private Double dayStopMf;
     private Double nightStopMf;
     private java.lang.String useStatus;
	 

	
	/**
	 * @return
	 */
	@Column(name = "CARD_NO" )
	public java.lang.String getCardNo() {
		return this.cardNo;
	}
	
	public void setCardNo(java.lang.String value) {
		this.cardNo = value;
	}
	
	
	/**
	 * @return
	 */
	@Column(name = "CARD_TYPE_ID" )
	public Double getCardTypeId() {
		return this.cardTypeId;
	}
	
	public void setCardTypeId(Double value) {
		this.cardTypeId = value;
	}
	
	
	/**
	 * @return
	 */
	@Column(name = "STATUS" )
	public java.lang.String getStatus() {
		return this.status;
	}
	
	public void setStatus(java.lang.String value) {
		this.status = value;
	}
	
	
	/**
	 * @return
	 */
	@Column(name = "OWNER" )
	public java.lang.String getOwner() {
		return this.owner;
	}
	
	public void setOwner(java.lang.String value) {
		this.owner = value;
	}
	
	
	/**
	 * @return
	 */
	@Column(name = "CAR_NO" )
	public java.lang.String getCarNo() {
		return this.carNo;
	}
	
	public void setCarNo(java.lang.String value) {
		this.carNo = value;
	}
	
	
	/**
	 * @return
	 */
	@Column(name = "EXPIRE_DATE" )
	public java.util.Date getExpireDate() {
		return this.expireDate;
	}
	
	public void setExpireDate(java.util.Date value) {
		this.expireDate = value;
	}
	
	
	/**
	 * @return
	 */
	@Column(name = "Y_NAME" )
	public java.lang.String getYname() {
		return this.yname;
	}
	
	public void setYname(java.lang.String value) {
		this.yname = value;
	}
	
	
	/**
	 * @return
	 */
	@Column(name = "XM_IDS" )
	public java.lang.String getXmIds() {
		return this.xmIds;
	}
	
	public void setXmIds(java.lang.String value) {
		this.xmIds = value;
	}
	
	
	/**
	 * @return
	 */
	@Column(name = "LASTTIME" )
	public Integer getLasttime() {
		return this.lasttime;
	}
	
	public void setLasttime(Integer value) {
		this.lasttime = value;
	}
	
	
	/**
	 * @return
	 */
	@Column(name = "TYPE_NAME" )
	public java.lang.String getTypeName() {
		return this.typeName;
	}
	
	public void setTypeName(java.lang.String value) {
		this.typeName = value;
	}
	
	
	/**
	 * @return
	 */
	@Column(name = "TYPE_ID" )
	public Double getTypeId() {
		return this.typeId;
	}
	
	public void setTypeId(Double value) {
		this.typeId = value;
	}
	
	
	/**
	 * @return
	 */
	@Column(name = "USER_NO" )
	public java.lang.String getUserNo() {
		return this.userNo;
	}
	
	public void setUserNo(java.lang.String value) {
		this.userNo = value;
	}
	
	
	/**
	 * @return
	 */
	@Column(name = "USER_NAME" )
	public java.lang.String getUserName() {
		return this.userName;
	}
	
	public void setUserName(java.lang.String value) {
		this.userName = value;
	}
	
	
	/**
	 * @return
	 */
	@Column(name = "FRONT_PRINTS" )
	public Double getFrontPrints() {
		return this.frontPrints;
	}
	
	public void setFrontPrints(Double value) {
		this.frontPrints = value;
	}
	
	
	/**
	 * @return
	 */
	@Column(name = "BG_PRINTS" )
	public Double getBgPrints() {
		return this.bgPrints;
	}
	
	public void setBgPrints(Double value) {
		this.bgPrints = value;
	}
	
	
	/**
	 * @return
	 */
	@Column(name = "USER_TYPE_ID" )
	public Double getUserTypeId() {
		return this.userTypeId;
	}
	
	public void setUserTypeId(Double value) {
		this.userTypeId = value;
	}
	
	
	/**
	 * @return
	 */
	@Column(name = "IS_PAY" )
	public java.lang.String getIsPay() {
		return this.isPay;
	}
	
	public void setIsPay(java.lang.String value) {
		this.isPay = value;
	}
	
	
	/**
	 * @return
	 */
	@Column(name = "CAR_TYPE" )
	public java.lang.String getCarType() {
		return this.carType;
	}
	
	public void setCarType(java.lang.String value) {
		this.carType = value;
	}
	
	
	/**
	 * @return
	 */
	@Column(name = "DAY_STOP_MF" )
	public Double getDayStopMf() {
		return this.dayStopMf;
	}
	
	public void setDayStopMf(Double value) {
		this.dayStopMf = value;
	}
	
	
	/**
	 * @return
	 */
	@Column(name = "NIGHT_STOP_MF" )
	public Double getNightStopMf() {
		return this.nightStopMf;
	}
	
	public void setNightStopMf(Double value) {
		this.nightStopMf = value;
	}
	
	
	/**
	 * @return
	 */
	@Column(name = "USE_STATUS" )
	public java.lang.String getUseStatus() {
		return this.useStatus;
	}
	
	public void setUseStatus(java.lang.String value) {
		this.useStatus = value;
	}
	
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}

