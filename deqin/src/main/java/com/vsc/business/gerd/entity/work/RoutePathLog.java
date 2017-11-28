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
 * 
 * @author Administrator
 * 
 */
@Entity
@Table(name = Constants.TABLE_PREFIX + "ROUTE_PATH_LOG")
public class RoutePathLog extends IdEntity {

	private java.util.Date createTime;
	private User user;
	private java.lang.String subtype;
	private java.lang.String logContent;

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
	@Column(name = "SUBTYPE")
	public java.lang.String getSubtype() {
		return this.subtype;
	}

	public void setSubtype(java.lang.String value) {
		this.subtype = value;
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

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
