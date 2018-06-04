package com.vsc.business.gerd.entity.work;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.vsc.business.core.entity.security.User;
import com.vsc.constants.Constants;
import com.vsc.modules.entity.IdEntity;

/**
 * 地锁操作记录实体类
 * @author XiangXiaoLin
 *
 */
@Entity
@Table(name = Constants.TABLE_PREFIX + "PARKING_LOCK_OPERATION_EVENT")
public class ParkingLockOperationEvent extends IdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8051121348721241729L;
	
	
	public static int SOURCETYPE_PC=1;
	public static int SOURCETYPE_PHONE=2;
	public static int SOURCETYPE_TONGJI=3;

	private String eventType;
	private Integer sourceType;
	private ParkingLock parkingLock;
	private java.util.Date createTime;
	private User user;
	private WxUser wxUser;
	
	/**
	 * 新增状态0，2离线，3已经是要控制的状态，1轮询成功
	 */
	private Integer status=0;

	/**
	 * @return
	 */
	@Column(name = "STATUS")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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
	@JoinColumn(name = "PARKING_LOCK_CODE",referencedColumnName="CODE")
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
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	public User getUser() {
		return this.user;
	}

	public void setUser(User value) {
		this.user = value;
	}
	
	
	/**
	 * @return
	 */
	@ManyToOne
	@JoinColumn(name = "WEIXIN_ID",referencedColumnName="WEIXIN_ID")
	public WxUser getWxUser() {
		return wxUser;
	}

	public void setWxUser(WxUser wxUser) {
		this.wxUser = wxUser;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
