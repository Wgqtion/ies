package com.vsc.business.gerd.entity.work;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.common.collect.Lists;
import com.vsc.constants.Constants;
import com.vsc.modules.entity.IdEntity;

/**
 * 
 * @author jerry
 * 
 */
@Entity
@Table(name = Constants.TABLE_PREFIX + "card_type")
public class CardType extends IdEntity {

	private Integer cardType = 1;
	private java.lang.String name;
	private java.lang.String cardNumber;
	private Integer cmod = 1;
	private Boolean needRel = false;
	private java.lang.String dcar;
	private java.lang.String canSearch;
	private Integer dayStopMf;
	private Integer nightStopMf;
	private Integer year;
	private Double fee;
	private java.lang.String xmIds;
	private Integer buban;
	private Integer biangeng;
	private Long lasttime;
	private Boolean status = true;
	private Integer oldCardTypeId;

	private List<Campus> campusList = Lists.newArrayList();

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
	@Column(name = "CNAME")
	public java.lang.String getName() {
		return this.name;
	}

	public void setName(java.lang.String value) {
		this.name = value;
	}

	/**
	 * @return
	 */
	@Column(name = "CARD_NUMBER")
	public java.lang.String getCardNumber() {
		return this.cardNumber;
	}

	public void setCardNumber(java.lang.String value) {
		this.cardNumber = value;
	}

	@Column(name = "CMOD")
	public Integer getCmod() {
		return cmod;
	}

	public void setCmod(Integer cmod) {
		this.cmod = cmod;
	}

	/**
	 * @return
	 */
	@Column(name = "NEED_REL")
	public Boolean getNeedRel() {
		return this.needRel;
	}

	public void setNeedRel(Boolean value) {
		this.needRel = value;
	}

	/**
	 * @return
	 */
	@Column(name = "D_CAR")
	public java.lang.String getDcar() {
		return this.dcar;
	}

	public void setDcar(java.lang.String value) {
		this.dcar = value;
	}

	/**
	 * @return
	 */
	@Column(name = "CAN_SEARCH")
	public java.lang.String getCanSearch() {
		return this.canSearch;
	}

	public void setCanSearch(java.lang.String value) {
		this.canSearch = value;
	}

	/**
	 * @return
	 */
	@Column(name = "DAY_STOP_MF")
	public Integer getDayStopMf() {
		return this.dayStopMf;
	}

	public void setDayStopMf(Integer value) {
		this.dayStopMf = value;
	}

	/**
	 * @return
	 */
	@Column(name = "NIGHT_STOP_MF")
	public Integer getNightStopMf() {
		return this.nightStopMf;
	}

	public void setNightStopMf(Integer value) {
		this.nightStopMf = value;
	}

	/**
	 * @return
	 */
	@Column(name = "CYEAR")
	public Integer getYear() {
		return this.year;
	}

	public void setYear(Integer value) {
		this.year = value;
	}

	/**
	 * @return
	 */
	@Column(name = "FEE")
	public Double getFee() {
		return this.fee;
	}

	public void setFee(Double value) {
		this.fee = value;
	}

	@Column(name = "XM_IDS")
	public java.lang.String getXmIds() {
		return xmIds;
	}

	public void setXmIds(java.lang.String xmIds) {
		this.xmIds = xmIds;
	}

	/**
	 * @return
	 */
	@Column(name = "BUBAN")
	public Integer getBuban() {
		return this.buban;
	}

	public void setBuban(Integer value) {
		this.buban = value;
	}

	/**
	 * @return
	 */
	@Column(name = "BIANGENG")
	public Integer getBiangeng() {
		return this.biangeng;
	}

	public void setBiangeng(Integer value) {
		this.biangeng = value;
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
	@Column(name = "STATUS")
	public Boolean getStatus() {
		return this.status;
	}

	public void setStatus(Boolean value) {
		this.status = value;
	}

	@Column(name = "OLD_CARD_TYPE_ID")
	public Integer getOldCardTypeId() {
		return oldCardTypeId;
	}

	public void setOldCardTypeId(Integer oldCardTypeId) {
		this.oldCardTypeId = oldCardTypeId;
	}

	@ManyToMany
	@JoinTable(name = Constants.TABLE_PREFIX + "relation_cardtype_campus", joinColumns = { @JoinColumn(name = "cardtype_id") }, inverseJoinColumns = { @JoinColumn(name = "campus_id") })
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
