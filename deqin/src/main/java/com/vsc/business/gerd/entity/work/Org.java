package com.vsc.business.gerd.entity.work;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.vsc.constants.Constants;
import com.vsc.modules.entity.BasicEntity;

/**
 * 用户分组实体类
 * @author XiangXiaoLin
 *
 */
@Entity
@Table(name = Constants.TABLE_PREFIX + "org")
public class Org extends BasicEntity {
	
	/**
	 * 组织名称
	 */
	private String name;
	/**
	 * 编码
	 */
	private String code;
	
	/**
	 * 邀请码
	 */
	private java.lang.String invitationCode;

	
	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "CODE")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "INVITATION_CODE")
	public java.lang.String getInvitationCode() {
		return invitationCode;
	}

	public void setInvitationCode(java.lang.String invitationCode) {
		this.invitationCode = invitationCode;
	}
	
}
