package com.vsc.business.gerd.entity.work;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.vsc.constants.Constants;
import com.vsc.modules.entity.IdEntity;

/**
 * 邀请码实体类
 * @author XiangXiaoLin
 *
 */
@Entity
@Table(name = Constants.TABLE_PREFIX + "invitation")
public class Invitation extends IdEntity {
	
	/**
	 * 邀请码
	 */
	private java.lang.String invitationCode;

	/**
	 * 是否删除
	 */
	private Boolean isDelete;

	/**
	 * 类型，1微信
	 */
	private Integer type;
	
	@Column(name = "TYPE")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "INVITATION_CODE")
	public java.lang.String getInvitationCode() {
		return invitationCode;
	}

	public void setInvitationCode(java.lang.String invitationCode) {
		this.invitationCode = invitationCode;
	}

	@Column(name = "IS_DELETE")
	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}
	
	
}
