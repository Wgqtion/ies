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
 * 停车参数 实体类
 * @author XiangXiaoLin
 *
 */
@Entity
@Table(name = Constants.TABLE_PREFIX + "parking_param")
public class ParkingParam extends BasicEntity{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3716340901319279210L;
	private ParkingLot parkingLot;
	private String parkingLotCode;
	
	/**
	 * 预约保留分钟
	 */
	private Time reserveMin;
	
	/**
	 * 预约免费分钟
	 */
	private Time freeReserveMin;
	
	/**
	 * 停车免费分钟
	 */
	private Time freeParkingMin;
	
	/**
	 * 最高预约费
	 */
	private Integer maxReserveFee;
	
	/**
	 * 最高停车费
	 */
	private Integer maxParkingFee;
	
	/**
	 * 预约优惠分钟
	 */
	private Time privilegeReserveMin;
	
	/**
	 * 停车优惠分钟
	 */
	private Time privilegeParkingMin;
	
	/**
	 * 预约取消次数，上限后不可预约
	 */
	private Integer cancelNum;
	
	
	@Column(name = "RESERVE_MIN")
	public Time getReserveMin() {
		return reserveMin;
	}

	public void setReserveMin(Time reserveMin) {
		this.reserveMin = reserveMin;
	}

	@Column(name = "FREE_RESERVE_MIN")
	public Time getFreeReserveMin() {
		return freeReserveMin;
	}

	public void setFreeReserveMin(Time freeReserveMin) {
		this.freeReserveMin = freeReserveMin;
	}

	@Column(name = "FREE_PARKING_MIN")
	public Time getFreeParkingMin() {
		return freeParkingMin;
	}

	public void setFreeParkingMin(Time freeParkingMin) {
		this.freeParkingMin = freeParkingMin;
	}

	@Column(name = "CANCEL_NUM")
	public Integer getCancelNum() {
		return cancelNum;
	}

	public void setCancelNum(Integer cancelNum) {
		this.cancelNum = cancelNum;
	}

	@Column(name = "PRIVILEGE_RESERVE_MIN")
	public Time getPrivilegeReserveMin() {
		return privilegeReserveMin;
	}

	public void setPrivilegeReserveMin(Time privilegeReserveMin) {
		this.privilegeReserveMin = privilegeReserveMin;
	}

	@Column(name = "PRIVILEGE_PARKING_MIN")
	public Time getPrivilegeParkingMin() {
		return privilegeParkingMin;
	}

	public void setPrivilegeParkingMin(Time privilegeParkingMin) {
		this.privilegeParkingMin = privilegeParkingMin;
	}

	@Column(name = "MAX_RESERVE_FEE")
	public Integer getMaxReserveFee() {
		return maxReserveFee;
	}

	public void setMaxReserveFee(Integer maxReserveFee) {
		this.maxReserveFee = maxReserveFee;
	}

	@Column(name = "MAX_PARKING_FEE")
	public Integer getMaxParkingFee() {
		return maxParkingFee;
	}

	public void setMaxParkingFee(Integer maxParkingFee) {
		this.maxParkingFee = maxParkingFee;
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
}
