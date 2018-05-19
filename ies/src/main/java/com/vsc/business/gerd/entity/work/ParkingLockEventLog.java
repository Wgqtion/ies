package com.vsc.business.gerd.entity.work;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.vsc.constants.Constants;
import com.vsc.modules.entity.IdEntity;

/**
 * 地锁上报日志表
 * @author XiangXiaoLin
 *
 */
@Entity
@Table(name = Constants.TABLE_PREFIX + "PARKING_LOCK_EVENT_LOG")
public class ParkingLockEventLog extends IdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6450631933698385278L;
	private String eventType;
	private ParkingLock parkingLock;
	private String parkingLockCode;
	private Date createTime;
	private String lockNum;
	private String ipAddress;
	private String state;
	private String mcOpen;

	/**
	 * 十六进制全部
	 */
	private String hexMsg;

	@Column(name = "HEX_MSG")
	public String getHexMsg() {
		return hexMsg;
	}

	public void setHexMsg(String hexMsg) {
		this.hexMsg = hexMsg;
	}

	/**
	 * @return
	 */
	@Column(name = "MCOPEN")
	public String getMcOpen() {
		return mcOpen;
	}

	public void setMcOpen(String mcOpen) {
		this.mcOpen = mcOpen;
	}

	/**
	 * @return
	 */
	@Column(name = "STATE")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return
	 */
	@Column(name = "EVENT_TYPE")
	public String getEventType() {
		return this.eventType;
	}

	public void setEventType(String value) {
		this.eventType = value;
	}

	@Column(name = "PARKING_LOCK_CODE")
	public String getParkingLockCode() {
		return parkingLockCode;
	}

	public void setParkingLockCode(String parkingLockCode) {
		this.parkingLockCode = parkingLockCode;
	}

	/**
	 * @return
	 */
	@ManyToOne
	@JoinColumn(name = "PARKING_LOCK_CODE",referencedColumnName="CODE",insertable=false,updatable=false)
	public ParkingLock getParkingLock() {
		return this.parkingLock;
	}

	public void setParkingLock(ParkingLock value) {
		this.parkingLock = value;
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
	@Column(name = "LOCK_NUM")
	public java.lang.String getLockNum() {
		return this.lockNum;
	}

	public void setLockNum(java.lang.String value) {
		this.lockNum = value;
	}

	/**
	 * @return
	 */
	@Column(name = "IP_ADDRESS")
	public java.lang.String getIpAddress() {
		return this.ipAddress;
	}

	public void setIpAddress(java.lang.String value) {
		this.ipAddress = value;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
