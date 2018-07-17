package com.vsc.modules.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.vsc.business.core.entity.security.User;

@MappedSuperclass
public class BasicEntity extends IdEntity{
	private User createUser;
	private Date createDate;
	private User updateUser;
	private Date updateDate;
	/**
	 * 是否删除
	 */
	private Boolean isDelete=false;
	
	@ManyToOne
    @JoinColumn(name = "CREATE_USER",updatable=false)
	public User getCreateUser() {
		return createUser;
	}
	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}
	
	@Column(name = "CREATE_DATE",updatable=false)
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	@ManyToOne
    @JoinColumn(name = "UPDATE_USER")
	public User getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(User updateUser) {
		this.updateUser = updateUser;
	}
	
	@Column(name = "UPDATE_DATE")
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}


	@Column(name = "IS_DELETE")
	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}
	
}
