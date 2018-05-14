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
 * 超时追加费用 实体类
 * @author XiangXiaoLin
 *
 */
@Entity
@Table(name = Constants.TABLE_PREFIX + "parking_overtime")
public class ParkingOvertime extends BasicEntity{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1640003962935663877L;
	private ParkingLot parkingLot;
	private String parkingLotCode;
	

	/**
	 * 超时分钟
	 */
	private Integer overtimeMin;
	/**
	 * 追加费
	 */
	private BigDecimal appendFee;

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

	@Column(name = "APPEND_FEE")
	public BigDecimal getAppendFee() {
		return appendFee;
	}

	public void setAppendFee(BigDecimal appendFee) {
		this.appendFee = appendFee;
	}
	
	@Column(name = "OVERTIME_MIN")
	public Integer getOvertimeMin() {
		return overtimeMin;
	}

	public void setOvertimeMin(Integer overtimeMin) {
		this.overtimeMin = overtimeMin;
	}
}
