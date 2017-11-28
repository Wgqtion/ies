package com.vsc.business.gerd.entity.work;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.vsc.constants.Constants;
import com.vsc.modules.entity.IdEntity;

/**
 * 同步日志
 * 
 * @author Administrator
 * 
 */
@Entity
@Table(name = Constants.TABLE_PREFIX + "sync_log")
public class SyncLog extends IdEntity {

	private java.lang.String logType;
	private java.lang.String logContent;
	private java.util.Date createTime = new Date();
	private java.lang.String userName;

	/**
	 * @return
	 */
	@Column(name = "LOG_TYPE")
	public java.lang.String getLogType() {
		return this.logType;
	}

	public void setLogType(java.lang.String value) {
		this.logType = value;
	}

	/**
	 * @return
	 */
	@Column(name = "LOG_CONTENT")
	public java.lang.String getLogContent() {
		return this.logContent;
	}

	public void setLogContent(java.lang.String value) {
		this.logContent = value;
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
	@Column(name = "USER_NAME")
	public java.lang.String getUserName() {
		return this.userName;
	}

	public void setUserName(java.lang.String value) {
		this.userName = value;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
