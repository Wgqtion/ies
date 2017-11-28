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
 * 公告接受信息
 * @author 付翀
 *
 */
@Entity
@Table(name = Constants.TABLE_PREFIX + "announcement_send")
public class AnnouncementSend extends IdEntity {

	private Announcement announcement;
	private User sendUser;
	private java.util.Date sendDate;
	private Boolean isRead = Boolean.FALSE;
	private java.util.Date readTime;
	private Boolean isClose = Boolean.FALSE;

	/**
	 * @return 公告
	 */
	@ManyToOne
	@JoinColumn(name = "announcement_id")
	public Announcement getAnnouncement() {
		return this.announcement;
	}

	public void setAnnouncement(Announcement value) {
		this.announcement = value;
	}

	/**
	 * @return 接受人
	 */
	@ManyToOne
	@JoinColumn(name = "send_user_id")
	public User getSendUser() {
		return this.sendUser;
	}

	public void setSendUser(User value) {
		this.sendUser = value;
	}

	/**
	 * @return 公告发送日期
	 */
	@Column(name = "send_date")
	public java.util.Date getSendDate() {
		return this.sendDate;
	}

	public void setSendDate(java.util.Date value) {
		this.sendDate = value;
	}

	/**
	 * @return 是否已读
	 */
	@Column(name = "is_read")
	public Boolean getIsRead() {
		return this.isRead;
	}

	public void setIsRead(Boolean value) {
		this.isRead = value;
	}

	/**
	 * @return 阅读时间
	 */
	@Column(name = "read_time")
	public java.util.Date getReadTime() {
		return this.readTime;
	}

	public void setReadTime(java.util.Date value) {
		this.readTime = value;
	}

	/**
	 * @return 是否关闭
	 */
	@Column(name = "is_close")
	public Boolean getIsClose() {
		return this.isClose;
	}

	public void setIsClose(Boolean value) {
		this.isClose = value;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
