package com.vsc.business.gerd.entity.work;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.vsc.constants.Constants;
import com.vsc.modules.entity.IdEntity;

/**
 * @author feifl
 * 
 */
@Entity
@Table(name = Constants.TABLE_PREFIX + "cardInfo")
public class CardInfo extends IdEntity {

	private java.lang.String owner;
	private java.lang.String carNo;
	private Date expireDate;
	private java.lang.String userNo;
	private java.lang.String carType;
	private java.lang.String mobile;
	private java.util.Date createTime = new Date();

	
	/**
	 * @return
	 */
	@Column(name = "OWNER")
	public java.lang.String getOwner() {
		return this.owner;
	}

	public void setOwner(java.lang.String value) {
		this.owner = value;
	}

	/**
	 * @return
	 */
	@Column(name = "CAR_NO")
	public java.lang.String getCarNo() {
		return this.carNo;
	}

	public void setCarNo(java.lang.String value) {
		this.carNo = value;
	}

	/**
	 * @return
	 */
	@Column(name = "EXPIRE_DATE")
	public Date getExpireDate() {
		return this.expireDate;
	}

	public void setExpireDate(Date value) {
		this.expireDate = value;
	}

	

	/**
	 * @return
	 */
	@Column(name = "USER_NO")
	public java.lang.String getUserNo() {
		return this.userNo;
	}

	public void setUserNo(java.lang.String value) {
		this.userNo = value;
	}

	
	/**
	 * @return
	 */
	@Column(name = "CAR_TYPE")
	public java.lang.String getCarType() {
		return this.carType;
	}

	public void setCarType(java.lang.String value) {
		this.carType = value;
	}

	
	

	/**
	 * @return
	 */
	@Column(name = "MOBILE")
	public java.lang.String getMobile() {
		return this.mobile;
	}

	public void setMobile(java.lang.String value) {
		this.mobile = value;
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
