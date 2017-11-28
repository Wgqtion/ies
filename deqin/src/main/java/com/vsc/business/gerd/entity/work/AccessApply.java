package com.vsc.business.gerd.entity.work;

import java.util.Date;

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
 * @author ThinkPad
 * 
 */
@Entity
@Table(name = Constants.TABLE_PREFIX + "accessApply")
public class AccessApply extends IdEntity {

	private CardInfo cardInfo;
	private String carNo;
	private Boolean accessStatus;
	private java.util.Date createTime = new Date();
	private User user;
	private User createUser;
	private java.util.Date applyTime;
	private Boolean applyState;

	private Passages passages;

	@ManyToOne
	@JoinColumn(name = "CAR_INFO_ID")
	public CardInfo getCardInfo() {
		return cardInfo;
	}

	public void setCardInfo(CardInfo cardInfo) {
		this.cardInfo = cardInfo;
	}

	@ManyToOne
	@JoinColumn(name = "APPLY_USER_ID")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return
	 */
	@ManyToOne
	@JoinColumn(name = "CREATE_USER_ID")
	public User getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(User value) {
		this.createUser = value;
	}

	/**
	 * @return
	 */
	@Column(name = "CAR_NO")
	public String getCarNo() {
		return this.carNo;
	}

	public void setCarNo(String value) {
		this.carNo = value;
	}

	/**
	 * @return
	 */
	@Column(name = "ACCESS_STATUS")
	public Boolean getAccessStatus() {
		return this.accessStatus;
	}

	public void setAccessStatus(Boolean value) {
		this.accessStatus = value;
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
	@Column(name = "APPLY_TIME")
	public java.util.Date getApplyTime() {
		return this.applyTime;
	}

	public void setApplyTime(java.util.Date value) {
		this.applyTime = value;
	}

	/**
	 * @return
	 */
	@Column(name = "APPLY_STATE")
	public Boolean getApplyState() {
		return this.applyState;
	}

	public void setApplyState(Boolean value) {
		this.applyState = value;
	}

	@ManyToOne
	@JoinColumn(name = "PASSAGES_ID")
	public Passages getPassages() {
		return passages;
	}

	public void setPassages(Passages passages) {
		this.passages = passages;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
