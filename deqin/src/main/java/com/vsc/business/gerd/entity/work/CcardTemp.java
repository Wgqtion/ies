package com.vsc.business.gerd.entity.work;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.vsc.constants.Constants;
import com.vsc.modules.entity.IdEntity;

 

/**
 * 原始进校证数据库c_card_temp
 * @author Administrator
 *
 */
@Entity
@Table(name = Constants.TABLE_PREFIX +"ccardTemp")
public class CcardTemp extends IdEntity {

	 
     private Integer year;
     private Integer month;
     private java.lang.String deptNo;
     private java.lang.String deptName;
     private java.lang.String owner;
     private java.lang.String mobile;
     private java.lang.String carNo;
     private java.util.Date useDate;
     private java.lang.String note;
     private Double userId;
     private Double status;
     private Double carType;
     private Double tempId;
     private java.lang.String xmIds;
	 

	
	/**
	 * @return
	 */
	@Column(name = "YEAR" )
	public Integer getYear() {
		return this.year;
	}
	
	public void setYear(Integer value) {
		this.year = value;
	}
	
	
	/**
	 * @return
	 */
	@Column(name = "MONTH" )
	public Integer getMonth() {
		return this.month;
	}
	
	public void setMonth(Integer value) {
		this.month = value;
	}
	
	
	/**
	 * @return
	 */
	@Column(name = "DEPT_NO" )
	public java.lang.String getDeptNo() {
		return this.deptNo;
	}
	
	public void setDeptNo(java.lang.String value) {
		this.deptNo = value;
	}
	
	
	/**
	 * @return
	 */
	@Column(name = "DEPT_NAME" )
	public java.lang.String getDeptName() {
		return this.deptName;
	}
	
	public void setDeptName(java.lang.String value) {
		this.deptName = value;
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
	@Column(name = "MOBILE" )
	public java.lang.String getMobile() {
		return this.mobile;
	}
	
	public void setMobile(java.lang.String value) {
		this.mobile = value;
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
	@Column(name = "USE_DATE" )
	public java.util.Date getUseDate() {
		return this.useDate;
	}
	
	public void setUseDate(java.util.Date value) {
		this.useDate = value;
	}
	
	
	/**
	 * @return
	 */
	@Column(name = "NOTE" )
	public java.lang.String getNote() {
		return this.note;
	}
	
	public void setNote(java.lang.String value) {
		this.note = value;
	}
	
	
	/**
	 * @return
	 */
	@Column(name = "USER_ID" )
	public Double getUserId() {
		return this.userId;
	}
	
	public void setUserId(Double value) {
		this.userId = value;
	}
	
	
	/**
	 * @return
	 */
	@Column(name = "STATUS" )
	public Double getStatus() {
		return this.status;
	}
	
	public void setStatus(Double value) {
		this.status = value;
	}
	
	
	/**
	 * @return
	 */
	@Column(name = "CAR_TYPE" )
	public Double getCarType() {
		return this.carType;
	}
	
	public void setCarType(Double value) {
		this.carType = value;
	}
	
	
	/**
	 * @return
	 */
	@Column(name = "TEMP_ID" )
	public Double getTempId() {
		return this.tempId;
	}
	
	public void setTempId(Double value) {
		this.tempId = value;
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
	
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}

