package com.vsc.business.gerd.entity.work;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.vsc.constants.Constants;
import com.vsc.modules.entity.BasicEntity;

/**
 * 停车参数 实体类
 * 
 * @author XiangXiaoLin
 *
 */
@Entity
@Table(name = Constants.TABLE_PREFIX + "parking_param")
public class ParkingParam extends BasicEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3716340901319279210L;
	private ParkingLot parkingLot;
	private String parkingLotCode;

	/**
	 * 预约保留分钟
	 */
	private Integer reserveMin = Integer.valueOf(0);

	/**
	 * 预约限免时间分钟
	 */
	private Integer freeReserveMin = Integer.valueOf(0);

	/**
	 * 停车限免时间分钟
	 */
	private Integer freeParkingMin = Integer.valueOf(0);

	/**
	 * 最高预约费（总）
	 */
	private Integer maxReserveFee = Integer.valueOf(0);

	/**
	 * 最高停车费（总）
	 */
	private Integer maxParkingFee = Integer.valueOf(0);

	/**
	 * 预约优惠分钟
	 */
	private Integer privilegeReserveMin = Integer.valueOf(0);

	/**
	 * 停车优惠分钟
	 */
	private Integer privilegeParkingMin = Integer.valueOf(0);

	/**
	 * 预约取消次数，上限后不可预约
	 */
	private Integer cancelNum = Integer.valueOf(0);

	@Column(name = "RESERVE_MIN")
	public Integer getReserveMin() {
		return reserveMin;
	}

	public void setReserveMin(Integer reserveMin) {
		this.reserveMin = reserveMin;
	}

	@Column(name = "FREE_RESERVE_MIN")
	public Integer getFreeReserveMin() {
		return freeReserveMin;
	}

	public void setFreeReserveMin(Integer freeReserveMin) {
		this.freeReserveMin = freeReserveMin;
	}

	@Column(name = "FREE_PARKING_MIN")
	public Integer getFreeParkingMin() {
		return freeParkingMin;
	}

	public void setFreeParkingMin(Integer freeParkingMin) {
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
	public Integer getPrivilegeReserveMin() {
		return privilegeReserveMin;
	}

	public void setPrivilegeReserveMin(Integer privilegeReserveMin) {
		this.privilegeReserveMin = privilegeReserveMin;
	}

	@Column(name = "PRIVILEGE_PARKING_MIN")
	public Integer getPrivilegeParkingMin() {
		return privilegeParkingMin;
	}

	public void setPrivilegeParkingMin(Integer privilegeParkingMin) {
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
	@JoinColumn(name = "PARKING_LOT_CODE", referencedColumnName = "CODE", insertable = false, updatable = false)
	public ParkingLot getParkingLot() {
		return this.parkingLot;
	}

	public void setParkingLot(ParkingLot parkingLot) {
		this.parkingLot = parkingLot;
	}
}
