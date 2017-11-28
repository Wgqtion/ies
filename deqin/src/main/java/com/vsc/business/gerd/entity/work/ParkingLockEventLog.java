package com.vsc.business.gerd.entity.work;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.vsc.constants.Constants;
import com.vsc.modules.entity.IdEntity;

/**
 * 
 * @author jerry
 *
 */
@Entity
@Table(name = Constants.TABLE_PREFIX + "PARKING_LOCK_EVENT_LOG")
public class ParkingLockEventLog extends IdEntity {

	private Integer eventType;
	private Integer sourceType;
	private ParkingLock parkingLock;
	private java.util.Date reportedTime;
	private java.util.Date createTime;
	private java.lang.String message;
	private java.lang.String lockNum;
	private java.lang.String deviceNum;
	private java.lang.String ipAddress;
	private Integer state;
	private Integer mcOpen;

	

	/**
	 * @return
	 */
	@Column(name = "MCOPEN")
	public Integer getMcOpen() {
		return mcOpen;
	}

	public void setMcOpen(Integer mcOpen) {
		this.mcOpen = mcOpen;
	}

	/**
	 * @return
	 */
	@Column(name = "STATE")
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	/**
	 * @return
	 */
	@Column(name = "EVENT_TYPE")
	public Integer getEventType() {
		return this.eventType;
	}

	public void setEventType(Integer value) {
		this.eventType = value;
	}

	/**
	 * @return
	 */
	@Column(name = "SOURCE_TYPE")
	public Integer getSourceType() {
		return this.sourceType;
	}

	public void setSourceType(Integer value) {
		this.sourceType = value;
	}

	/**
	 * @return
	 */
	@ManyToOne
	@JoinColumn(name = "PARKING_LOCK_ID")
	public ParkingLock getParkingLock() {
		return this.parkingLock;
	}

	public void setParkingLock(ParkingLock value) {
		this.parkingLock = value;
	}

	/**
	 * @return
	 */
	@Column(name = "REPORTED_TIME")
	public java.util.Date getReportedTime() {
		return this.reportedTime;
	}

	public void setReportedTime(java.util.Date value) {
		this.reportedTime = value;
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
	@Column(name = "MESSAGE")
	public java.lang.String getMessage() {
		return this.message;
	}

	public void setMessage(java.lang.String value) {
		this.message = value;
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
	@Column(name = "DEVICE_NUM")
	public java.lang.String getDeviceNum() {
		return this.deviceNum;
	}

	public void setDeviceNum(java.lang.String value) {
		this.deviceNum = value;
	}
	
	/**
	 * 获取电量
	 */
	@Transient
	public double getPower(){
		double returnD=0;
		String s=this.getDianLiang();
		switch (s) {
		case "000":
			returnD=4.5;
			break;
		case "001":
			returnD=4.8;
			break;
		case "011":
			returnD=5.1;
			break;
		case "010":
			returnD=5.4;
			break;
		case "100":
			returnD=5.7;
			break;
		case "101":
			returnD=6.0;
			break;
		case "110":
			returnD=6.3;
			break;
		case "111":
			returnD=6.6;
			break;
		default:
			break;
		}
		return returnD;
	}

	/**
	 * 电量解析
	 */
	@Transient
	public String getDianLiang(){
		if(this.state==null){
			return "";
		}		
		String dlState=this.getStateString();
		return StringUtils.substring(dlState,dlState.length()-3);
	}
	
	/**
	 * 地锁开关状态
	 */
	@Transient
	public Boolean getIsOpen(){
		if(this.state==null){
			return null;
		}		
	 
		return  "0".equals(StringUtils.substring(this.getStateString(),4,5))?true:false;
	}
	/**
	 * 状态码转换，补足9位
	 * @return
	 */
	@Transient
	public String getStateString(){
		if(this.state==null){
			return null;
		}		
		String dlState=Integer.toBinaryString(state);		
		
		return StringUtils.leftPad(dlState, 8, "0");
	}
	
	
	/**
	 * 是否有车
	 */
	@Transient
	public Boolean getIsCarOn(){
		if(this.state==null){
			return null;
		}		
		 
		return  "0".equals(StringUtils.substring(this.getStateString(),3,4))?false:true;
	}
	
	/**
	 * 系统被锁住
	 */
	@Transient
	public Integer getIsForeverOpenClose(){
		if(this.state==null){
			return null;
		}		
	 
		return  "0".equals(StringUtils.substring(this.getStateString(),2,3))?0:1;
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
