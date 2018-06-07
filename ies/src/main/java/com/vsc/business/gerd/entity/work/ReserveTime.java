package com.vsc.business.gerd.entity.work;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.vsc.constants.Constants;
import com.vsc.modules.entity.BasicEntity;

import java.sql.Time;

/**
 * 预约时间实体类
 * @author XiangXiaoLin
 *
 */
@Entity
@Table(name = Constants.TABLE_PREFIX + "reserve_time")
public class ReserveTime extends BasicEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 448501124281877054L;
	private ParkingLot parkingLot;
	private String parkingLotCode;
	
	/**
	 * 周
	 * ["1","周一"],
	["2","周二"],
	["3","周三"],
	["4","周四"],
	["5","周五"],
	["6","周六"],
	["7","周日"],
	["0","指定日期"]
	 */
	private Integer week;
	/**
	 * 开始时间
	 */
	private Time startTime;
	/**
	 * 结束时间
	 */
	private Time endTime;
	
	/**
     * @return
     */
    @Column(name = "PARKING_LOT_CODE")
	public String getParkingLotCode() {
		return parkingLotCode;
	}

	public void setParkingLotCode(String parkingLotCode) {
		this.parkingLotCode = parkingLotCode;
	}
	/**
	 * @return
	 */
	@ManyToOne
	@JoinColumn(name = "PARKING_LOT_CODE",referencedColumnName="CODE",insertable=false,updatable=false)
	public ParkingLot getParkingLot() {
		return this.parkingLot;
	}

	public void setParkingLot(ParkingLot parkingLot) {
		this.parkingLot = parkingLot;
	}

	@Column(name = "WEEK")
	public Integer getWeek() {
		return week;
	}

	public void setWeek(Integer week) {
		this.week = week;
	}

	@Column(name = "START_TIME")
	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	@Column(name = "END_TIME")
	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}
	
}
