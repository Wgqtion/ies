package com.vsc.business.gerd.entity.work;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.vsc.constants.Constants;
import com.vsc.modules.entity.IdEntity;

/**
 * 
 * @author jerry
 *
 */
@Entity
@Table(name = Constants.TABLE_PREFIX + "yuding_setting")
public class YudingSetting extends IdEntity {

	private java.lang.String mondayStartTime;
	private java.lang.String tuesdayStartTime;
	private java.lang.String wednesdayStartTime;
	private java.lang.String thursdayStartTime;
	private java.lang.String fridayStartTime;
	private java.lang.String saturdayStartTime;
	private java.lang.String sundayStartTime;
	private java.lang.String mondayEndTime;
	private java.lang.String tuesdayEndTime;
	private java.lang.String wednesdayEndTime;
	private java.lang.String thursdayEndTime;
	private java.lang.String fridayEndTime;
	private java.lang.String saturdayEndTime;
	private java.lang.String sundayEndTime;
	private Integer startAddMinutes;
	private Integer endAddMinutes;
	private Integer lockedMinutes;
	private Double lockedCost;
	private Double lockedHourCost;
	private java.util.Date createTime;
	private Long lasttime;

	/**
	 * @return
	 */
	@Column(name = "MONDAY_START_TIME")
	public java.lang.String getMondayStartTime() {
		return this.mondayStartTime;
	}

	public void setMondayStartTime(java.lang.String value) {
		this.mondayStartTime = value;
	}

	/**
	 * @return
	 */
	@Column(name = "TUESDAY_START_TIME")
	public java.lang.String getTuesdayStartTime() {
		return this.tuesdayStartTime;
	}

	public void setTuesdayStartTime(java.lang.String value) {
		this.tuesdayStartTime = value;
	}

	/**
	 * @return
	 */
	@Column(name = "WEDNESDAY_START_TIME")
	public java.lang.String getWednesdayStartTime() {
		return this.wednesdayStartTime;
	}

	public void setWednesdayStartTime(java.lang.String value) {
		this.wednesdayStartTime = value;
	}

	/**
	 * @return
	 */
	@Column(name = "THURSDAY_START_TIME")
	public java.lang.String getThursdayStartTime() {
		return this.thursdayStartTime;
	}

	public void setThursdayStartTime(java.lang.String value) {
		this.thursdayStartTime = value;
	}

	/**
	 * @return
	 */
	@Column(name = "FRIDAY_START_TIME")
	public java.lang.String getFridayStartTime() {
		return this.fridayStartTime;
	}

	public void setFridayStartTime(java.lang.String value) {
		this.fridayStartTime = value;
	}

	/**
	 * @return
	 */
	@Column(name = "SATURDAY_START_TIME")
	public java.lang.String getSaturdayStartTime() {
		return this.saturdayStartTime;
	}

	public void setSaturdayStartTime(java.lang.String value) {
		this.saturdayStartTime = value;
	}

	/**
	 * @return
	 */
	@Column(name = "SUNDAY_START_TIME")
	public java.lang.String getSundayStartTime() {
		return this.sundayStartTime;
	}

	public void setSundayStartTime(java.lang.String value) {
		this.sundayStartTime = value;
	}

	/**
	 * @return
	 */
	@Column(name = "MONDAY_END_TIME")
	public java.lang.String getMondayEndTime() {
		return this.mondayEndTime;
	}

	public void setMondayEndTime(java.lang.String value) {
		this.mondayEndTime = value;
	}

	/**
	 * @return
	 */
	@Column(name = "TUESDAY_END_TIME")
	public java.lang.String getTuesdayEndTime() {
		return this.tuesdayEndTime;
	}

	public void setTuesdayEndTime(java.lang.String value) {
		this.tuesdayEndTime = value;
	}

	/**
	 * @return
	 */
	@Column(name = "WEDNESDAY_END_TIME")
	public java.lang.String getWednesdayEndTime() {
		return this.wednesdayEndTime;
	}

	public void setWednesdayEndTime(java.lang.String value) {
		this.wednesdayEndTime = value;
	}

	/**
	 * @return
	 */
	@Column(name = "THURSDAY_END_TIME")
	public java.lang.String getThursdayEndTime() {
		return this.thursdayEndTime;
	}

	public void setThursdayEndTime(java.lang.String value) {
		this.thursdayEndTime = value;
	}

	/**
	 * @return
	 */
	@Column(name = "FRIDAY_END_TIME")
	public java.lang.String getFridayEndTime() {
		return this.fridayEndTime;
	}

	public void setFridayEndTime(java.lang.String value) {
		this.fridayEndTime = value;
	}

	/**
	 * @return
	 */
	@Column(name = "SATURDAY_END_TIME")
	public java.lang.String getSaturdayEndTime() {
		return this.saturdayEndTime;
	}

	public void setSaturdayEndTime(java.lang.String value) {
		this.saturdayEndTime = value;
	}

	/**
	 * @return
	 */
	@Column(name = "SUNDAY_END_TIME")
	public java.lang.String getSundayEndTime() {
		return this.sundayEndTime;
	}

	public void setSundayEndTime(java.lang.String value) {
		this.sundayEndTime = value;
	}

	/**
	 * @return
	 */
	@Column(name = "START_ADD_MINUTES")
	public Integer getStartAddMinutes() {
		return this.startAddMinutes;
	}

	public void setStartAddMinutes(Integer value) {
		this.startAddMinutes = value;
	}

	/**
	 * @return
	 */
	@Column(name = "END_ADD_MINUTES")
	public Integer getEndAddMinutes() {
		return this.endAddMinutes;
	}

	public void setEndAddMinutes(Integer value) {
		this.endAddMinutes = value;
	}

	/**
	 * @return
	 */
	@Column(name = "LOCKED_MINUTES")
	public Integer getLockedMinutes() {
		return this.lockedMinutes;
	}

	public void setLockedMinutes(Integer value) {
		this.lockedMinutes = value;
	}

	/**
	 * @return
	 */
	@Column(name = "LOCKED_COST")
	public Double getLockedCost() {
		return this.lockedCost;
	}

	public void setLockedCost(Double value) {
		this.lockedCost = value;
	}

	/**
	 * @return
	 */
	@Column(name = "LOCKED_HOUR_COST")
	public Double getLockedHourCost() {
		return this.lockedHourCost;
	}

	public void setLockedHourCost(Double value) {
		this.lockedHourCost = value;
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
	@Column(name = "LASTTIME")
	public Long getLasttime() {
		return this.lasttime;
	}

	public void setLasttime(Long value) {
		this.lasttime = value;
	}

	@Transient
	public String getWeekStarttime(int week) {
		String weekStarttime = "";
		switch (week) {

		case 0:
			weekStarttime = this.sundayStartTime;
			break;

		case 1:
			weekStarttime = this.mondayStartTime;
			break;

		case 2:
			weekStarttime = this.tuesdayStartTime;
			break;
		case 3:
			weekStarttime = this.wednesdayStartTime;
			break;

		case 4:
			weekStarttime = this.thursdayStartTime;
			break;

		case 5:
			weekStarttime = this.fridayStartTime;
			break;

		case 6:
			weekStarttime = this.saturdayStartTime;
			break;

		}

		return weekStarttime;

	}

	@Transient
	public String getWeekEndtime(int week) {
		String weekEndtime = "";
		switch (week) {

		case 0:
			weekEndtime = this.sundayEndTime;
			break;

		case 1:
			weekEndtime = this.mondayEndTime;
			break;

		case 2:
			weekEndtime = this.tuesdayEndTime;
			break;
		case 3:
			weekEndtime = this.wednesdayEndTime;
			break;

		case 4:
			weekEndtime = this.thursdayEndTime;
			break;

		case 5:
			weekEndtime = this.fridayEndTime;
			break;

		case 6:
			weekEndtime = this.saturdayEndTime;
			break;

		}

		return weekEndtime;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
