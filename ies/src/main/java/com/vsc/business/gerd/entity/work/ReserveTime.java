package com.vsc.business.gerd.entity.work;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.vsc.constants.Constants;
import com.vsc.modules.entity.BasicEntity;

/**
 * 预约时间实体类
 * @author XiangXiaoLin
 *
 */
@Entity
@Table(name = Constants.TABLE_PREFIX + "reserve_time")
public class ReserveTime extends BasicEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 448501124281877054L;
	private ParkingLot parkingLot;
	private String parkingLotCode;
	
	/**
	 * 周几？
	 */
	private Integer week;
	/**
	 * 开始时间
	 */
	private String startTime;
	/**
	 * 结束时间
	 */
	private String endTime;
	/**
	 * 非持久字段
	 */
	//可预约时间
	private Date time;
	
	
	@Transient
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

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
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	@Column(name = "END_TIME")
	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
}
