package com.vsc.business.gerd.entity.work;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.common.collect.Lists;
import com.vsc.business.core.entity.security.User;
import com.vsc.constants.Constants;
import com.vsc.modules.entity.IdEntity;

/**
 * 系统公告
 * @author 付翀
 *
 */
@Entity
@Table(name = Constants.TABLE_PREFIX + "announcement")
public class Announcement extends IdEntity {

	private java.lang.String title;
	private java.lang.String context;
	private User createUser;
	private java.util.Date startDate;
	private java.util.Date createTime;
	private java.lang.Integer state;
	private Boolean toAllUser = Boolean.FALSE;

	private List<AnnouncementSend> sends = Lists.newArrayList();

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
	@JoinColumn(name = "create_user_id")
	public User getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(User value) {
		this.createUser = value;
	}

	/**
	 * @return
	 */
	@Column(name = "start_date")
	public java.util.Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(java.util.Date value) {
		this.startDate = value;
	}

	/**
	 * @return
	 */
	@Column(name = "create_time")
	public java.util.Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}

	/**
	 * @return
	 */
	@Column(name = "state")
	public java.lang.Integer getState() {
		return this.state;
	}

	public void setState(java.lang.Integer value) {
		this.state = value;
	}

	/**
	 * 发送给所有人 
	 * @return
	 */
	@Column(name = "to_all_user")
	public Boolean getToAllUser() {
		return toAllUser;
	}

	public void setToAllUser(Boolean toAllUser) {
		this.toAllUser = toAllUser;
	}

	@OneToMany(mappedBy = "announcement")
	public List<AnnouncementSend> getSends() {
		return sends;
	}

	public void setSends(List<AnnouncementSend> sends) {
		this.sends = sends;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
