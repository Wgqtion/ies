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
 * 系统通知 
 * @author 付翀
 *
 */
@Entity
@Table(name = Constants.TABLE_PREFIX + "system_notice")
public class SystemNotice extends IdEntity {

	private java.lang.String title;
	private java.lang.String context;
	private User toUser;
	private java.util.Date sendTime;
	private java.lang.Boolean isRead = Boolean.FALSE;
	private java.util.Date readTime;
	private java.lang.Boolean isClose = Boolean.FALSE;
	private String linkUrl;

	/**
	 * @return
	 */
	@Column(name = "title")
	public java.lang.String getTitle() {
		return this.title;
	}

	public void setTitle(java.lang.String value) {
		this.title = value;
	}

	/**
	 * @return
	 */
	@Column(name = "context")
	public java.lang.String getContext() {
		return this.context;
	}

	public void setContext(java.lang.String value) {
		this.context = value;
	}

	/**
	 * @return
	 */
	@ManyToOne
	@JoinColumn(name = "to_user_id")
	public User getToUser() {
		return this.toUser;
	}

	public void setToUser(User value) {
		this.toUser = value;
	}

	/**
	 * @return
	 */
	@Column(name = "send_time")
	public java.util.Date getSendTime() {
		return this.sendTime;
	}

	public void setSendTime(java.util.Date value) {
		this.sendTime = value;
	}

	/**
	 * @return
	 */
	@Column(name = "is_read")
	public Boolean getIsRead() {
		return this.isRead;
	}

	public void setIsRead(Boolean value) {
		this.isRead = value;
	}

	/**
	 * @return
	 */
	@Column(name = "read_time")
	public java.util.Date getReadTime() {
		return this.readTime;
	}

	public void setReadTime(java.util.Date value) {
		this.readTime = value;
	}

	/**
	 * @return
	 */
	@Column(name = "is_close")
	public Boolean getIsClose() {
		return this.isClose;
	}

	public void setIsClose(Boolean value) {
		this.isClose = value;
	}

	@Column(name = "link_url")
	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
