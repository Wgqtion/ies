package com.vsc.business.gerd.entity.work;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.common.collect.Lists;
import com.vsc.constants.Constants;
import com.vsc.modules.entity.IdEntity;

/**
 * @author feifl
 * 
 */
@Entity
@Table(name = Constants.TABLE_PREFIX + "cardInfo")
public class CardInfo extends IdEntity {

	private java.lang.String cardNo;
	private Integer cardType;// 1.进校证 2.临时进校证 3.evcard 4.vip自建
	private CardType cardTypeEntity;// '卡类型'
	private Integer status;
	private java.lang.String owner;
	private java.lang.String carNo;
	private Date expireDate;
	private Integer yname;
	private java.lang.String xmIds;
	private Long lasttime;
	private java.lang.String typeName;
	private Long typeId;
	private java.lang.String userNo;
	private java.lang.String userName;
	private Integer frontPrints;
	private Integer bgPrints;
	private Long userTypeId;
	private java.lang.String isPay;
	private java.lang.String carType;
	private Double dayStopMf;
	private Double nightStopMf;
	private java.lang.String useStatus;
	private java.lang.String deptNo;
	private java.lang.String deptName;
	private java.lang.String mobile;
	private java.lang.String note;
	private Long userId;
	private Long tempId;
	private Long oldCardInfoId;
	private java.util.Date createTime = new Date();

	private List<Campus> campusList = Lists.newArrayList();

	/**
	 * @return
	 */
	@Column(name = "CARD_NO")
	public java.lang.String getCardNo() {
		return this.cardNo;
	}

	public void setCardNo(java.lang.String value) {
		this.cardNo = value;
	}

	/**
	 * @return
	 */
	@Column(name = "CARD_TYPE")
	public Integer getCardType() {
		return this.cardType;
	}

	public void setCardType(Integer value) {
		this.cardType = value;
	}

	/**
	 * @return
	 */
	@ManyToOne
	@JoinColumn(name = "CARD_TYPE_ID")
	public CardType getCardTypeEntity() {
		return this.cardTypeEntity;
	}

	public void setCardTypeEntity(CardType value) {
		this.cardTypeEntity = value;
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
	@Column(name = "Y_NAME")
	public Integer getYname() {
		return this.yname;
	}

	public void setYname(Integer value) {
		this.yname = value;
	}

	/**
	 * @return
	 */
	@Column(name = "XM_IDS")
	public java.lang.String getXmIds() {
		return this.xmIds;
	}

	public void setXmIds(java.lang.String value) {
		this.xmIds = value;
	}

	/**
	 * @return
	 */
	@Column(name = "LASTTIME")
	public Long getLasttime() {
		return this.lasttime;
	}

	public void setLasttime(Long value) {
		this.lasttime = value;
	}

	/**
	 * @return
	 */
	@Column(name = "TYPE_NAME")
	public java.lang.String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(java.lang.String value) {
		this.typeName = value;
	}

	/**
	 * @return
	 */
	@Column(name = "TYPE_ID")
	public Long getTypeId() {
		return this.typeId;
	}

	public void setTypeId(Long value) {
		this.typeId = value;
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
	@Column(name = "USER_NAME")
	public java.lang.String getUserName() {
		return this.userName;
	}

	public void setUserName(java.lang.String value) {
		this.userName = value;
	}

	/**
	 * @return
	 */
	@Column(name = "FRONT_PRINTS")
	public Integer getFrontPrints() {
		return this.frontPrints;
	}

	public void setFrontPrints(Integer value) {
		this.frontPrints = value;
	}

	/**
	 * @return
	 */
	@Column(name = "BG_PRINTS")
	public Integer getBgPrints() {
		return this.bgPrints;
	}

	public void setBgPrints(Integer value) {
		this.bgPrints = value;
	}

	/**
	 * @return
	 */
	@Column(name = "USER_TYPE_ID")
	public Long getUserTypeId() {
		return this.userTypeId;
	}

	public void setUserTypeId(Long value) {
		this.userTypeId = value;
	}

	/**
	 * @return
	 */
	@Column(name = "IS_PAY")
	public java.lang.String getIsPay() {
		return this.isPay;
	}

	public void setIsPay(java.lang.String value) {
		this.isPay = value;
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
	@Column(name = "DAY_STOP_MF")
	public Double getDayStopMf() {
		return this.dayStopMf;
	}

	public void setDayStopMf(Double value) {
		this.dayStopMf = value;
	}

	/**
	 * @return
	 */
	@Column(name = "NIGHT_STOP_MF")
	public Double getNightStopMf() {
		return this.nightStopMf;
	}

	public void setNightStopMf(Double value) {
		this.nightStopMf = value;
	}

	/**
	 * @return
	 */
	@Column(name = "USE_STATUS")
	public java.lang.String getUseStatus() {
		return this.useStatus;
	}

	public void setUseStatus(java.lang.String value) {
		this.useStatus = value;
	}

	/**
	 * @return
	 */
	@Column(name = "DEPT_NO")
	public java.lang.String getDeptNo() {
		return this.deptNo;
	}

	public void setDeptNo(java.lang.String value) {
		this.deptNo = value;
	}

	/**
	 * @return
	 */
	@Column(name = "DEPT_NAME")
	public java.lang.String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(java.lang.String value) {
		this.deptName = value;
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
	@Column(name = "NOTE")
	public java.lang.String getNote() {
		return this.note;
	}

	public void setNote(java.lang.String value) {
		this.note = value;
	}

	/**
	 * @return
	 */
	@Column(name = "USER_ID")
	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long value) {
		this.userId = value;
	}

	/**
	 * @return
	 */
	@Column(name = "TEMP_ID")
	public Long getTempId() {
		return this.tempId;
	}

	public void setTempId(Long value) {
		this.tempId = value;
	}

	/**
	 * @return
	 */
	@Column(name = "OLD_CARD_INFO_ID")
	public Long getOldCardInfoId() {
		return this.oldCardInfoId;
	}

	public void setOldCardInfoId(Long value) {
		this.oldCardInfoId = value;
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

	@ManyToMany
	@JoinTable(name = Constants.TABLE_PREFIX + "relation_cardinfo_campus", joinColumns = { @JoinColumn(name = "cardinfo_id") }, inverseJoinColumns = { @JoinColumn(name = "campus_id") })
	public List<Campus> getCampusList() {
		return campusList;
	}

	public void setCampusList(List<Campus> campusList) {
		this.campusList = campusList;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
