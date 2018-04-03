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

	public static int EVENTTYPE_OPEN = 0;
	public static int EVENTTYPE_CLOSE = 1;
	public static int EVENTTYPE_FOREVER_OPEN = 2;
	public static int EVENTTYPE_FOREVER_CLOSE = 3;
	public static int EVENTTYPE_FOREVER_NONE = 4;
	
	public static int SOURCETYPE_PC=1;
	public static int SOURCETYPE_PHONE=2;
	
	//ISFOREVEROPENCLOSE转换为EVENTTYPE_FOREVER_OPEN常量的数组
	public static int[] EVENTTYPE_ISFOREVEROPENCLOSE={EVENTTYPE_FOREVER_NONE,EVENTTYPE_FOREVER_OPEN,EVENTTYPE_FOREVER_CLOSE};
	public static int[] EVENTTYPE_OPENCLOSE={EVENTTYPE_OPEN,EVENTTYPE_CLOSE};

	private Integer eventType;
	private Integer sourceType;
	private ParkingLock parkingLock;
	private java.util.Date reportedTime;
	private java.util.Date createTime;
	private User user;
	private WxUser wxUser;
	
	private Integer status=0;

	/**
	 * @return
	 */
	@Column(name = "EVENT_STATUS")
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
