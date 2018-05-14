package com.vsc.business.gerd.entity.work;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.vsc.constants.Constants;
import com.vsc.modules.entity.BasicEntity;

/**
 * 收费设置 实体类
 * @author XiangXiaoLin
 *
 */
@Entity
@Table(name = Constants.TABLE_PREFIX + "parking_fee")
public class ParkingFee extends BasicEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6689416264357311246L;
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
	private String startTime;
	/**
	 * 结束时间
	 */
	private String endTime;
	/**
	 * 类型，1预约费，2停车费
	 */
	private Integer type;
	/**
	 * 费用
	 */
	private BigDecimal fee;
	
	 @Column(name = "TYPE")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	 @Column(name = "FEE")
	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
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
