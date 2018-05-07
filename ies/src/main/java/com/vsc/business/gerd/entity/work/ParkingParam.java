package com.vsc.business.gerd.entity.work;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.vsc.constants.Constants;
import com.vsc.modules.entity.BasicEntity;

/**
 * 停车参数 实体类
 * @author XiangXiaoLin
 *
 */
@Entity
@Table(name = Constants.TABLE_PREFIX + "parking_param")
public class ParkingParam extends BasicEntity implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3716340901319279210L;
	private ParkingLot parkingLot;
	private String parkingLotCode;
	
	/**
	 * 预约保留分钟
	 */
	private Integer reserveMin;
	/**
	 * 预约免费分钟
	 */
	private Integer freeReserveMin;
	/**
	 * 停车免费分钟
	 */
	private Integer freeParkingMin;
	/**
	 * 预约取消次数，上限后不可预约
	 */
	private Integer cancelNum;
	
	
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
