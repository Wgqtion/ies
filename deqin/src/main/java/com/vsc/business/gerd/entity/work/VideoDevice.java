package com.vsc.business.gerd.entity.work;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.vsc.constants.Constants;
import com.vsc.modules.entity.IdEntity;

/**
 * 视频设备
 * @author Administrator
 * 
 */
@Entity
@Table(name = Constants.TABLE_PREFIX + "VIDEO_DEVICE")
public class VideoDevice extends IdEntity {

	private java.lang.String deviceNo;
	private java.lang.String name;
	private java.lang.String deviceIp;
	private Boolean isEnabled;
	private java.util.Date createTime;
	private java.lang.String onlineUrl;

	/**
	 * @return
	 */
	@Column(name = "DEVICE_NO")
	public java.lang.String getDeviceNo() {
		return this.deviceNo;
	}

	public void setDeviceNo(java.lang.String value) {
		this.deviceNo = value;
	}

	/**
	 * @return
	 */
	@Column(name = "NAME")
	public java.lang.String getName() {
		return this.name;
	}

	public void setName(java.lang.String value) {
		this.name = value;
	}

	/**
	 * @return
	 */
	@Column(name = "DEVICE_IP")
	public java.lang.String getDeviceIp() {
		return this.deviceIp;
	}

	public void setDeviceIp(java.lang.String value) {
		this.deviceIp = value;
	}

	/**
	 * @return
	 */
	@Column(name = "IS_ENABLED")
	public Boolean getIsEnabled() {
		return this.isEnabled;
	}

	public void setIsEnabled(Boolean value) {
		this.isEnabled = value;
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

	public java.lang.String getOnlineUrl() {
		return onlineUrl;
	}
	@Column(name = "ONLINE_URL")
	public void setOnlineUrl(java.lang.String onlineUrl) {
		this.onlineUrl = onlineUrl;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
