package com.vsc.business.gerd.entity.work;

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
 * @author Administrator
 * 
 */
@Entity
@Table(name = Constants.TABLE_PREFIX + "shoufei")
public class Shoufei extends IdEntity {

	private CardType cardType;
	private ParkingLot parkingLot;
	private Double dayTime;
	private Double nightTime;
	private Double dayHourMoney;
	private Double nightHourMoney;
	private Double dayMaxMoney;
	private Double nightMaxMoney;
	private Double everydayMianfeiTime;
	private Long lasttime;
	private Long cshoufeiId;

	/**
	 * @return
	 */
	@ManyToOne
	@JoinColumn(name = "CARD_TYPE_ID")
	public CardType getCardType() {
		return this.cardType;
	}

	public void setCardType(CardType value) {
		this.cardType = value;
	}

	/**
	 * @return
	 * 
	 */
	@ManyToOne
	@JoinColumn(name = "PARKING_LOT_ID")
	public ParkingLot getParkingLot() {
		return this.parkingLot;
	}

	public void setParkingLot(ParkingLot value) {
		this.parkingLot = value;
	}

	/**
	 * @return
	 */
	@Column(name = "DAY_TIME")
	public Double getDayTime() {
		return this.dayTime;
	}

	public void setDayTime(Double value) {
		this.dayTime = value;
	}

	/**
	 * @return
	 */
	@Column(name = "NIGHT_TIME")
	public Double getNightTime() {
		return this.nightTime;
	}

	public void setNightTime(Double value) {
		this.nightTime = value;
	}

	/**
	 * @return
	 */
	@Column(name = "DAY_HOUR_MONEY")
	public Double getDayHourMoney() {
		return this.dayHourMoney;
	}

	public void setDayHourMoney(Double value) {
		this.dayHourMoney = value;
	}

	/**
	 * @return
	 */
	@Column(name = "NIGHT_HOUR_MONEY")
	public Double getNightHourMoney() {
		return this.nightHourMoney;
	}

	public void setNightHourMoney(Double value) {
		this.nightHourMoney = value;
	}

	/**
	 * @return
	 */
	@Column(name = "DAY_MAX_MONEY")
	public Double getDayMaxMoney() {
		return this.dayMaxMoney;
	}

	public void setDayMaxMoney(Double value) {
		this.dayMaxMoney = value;
	}

	/**
	 * @return
	 */
	@Column(name = "NIGHT_MAX_MONEY")
	public Double getNightMaxMoney() {
		return this.nightMaxMoney;
	}

	public void setNightMaxMoney(Double value) {
		this.nightMaxMoney = value;
	}

	/**
	 * @return
	 */
	@Column(name = "EVERYDAY_MIANFEI_TIME")
	public Double getEverydayMianfeiTime() {
		return this.everydayMianfeiTime;
	}

	public void setEverydayMianfeiTime(Double value) {
		this.everydayMianfeiTime = value;
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
	//@Column(name = "C_SHOUFEI_ID")
	@Column(name = "OLD_SHOUFEI_ID")
	public Long getCshoufeiId() {
		return this.cshoufeiId;
	}

	public void setCshoufeiId(Long value) {
		this.cshoufeiId = value;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
