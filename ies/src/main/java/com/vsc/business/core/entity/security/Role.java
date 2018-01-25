package com.vsc.business.core.entity.security;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

import com.google.common.collect.Lists;
import com.vsc.constants.Constants;
import com.vsc.modules.entity.IdEntity;

@Entity
@Table(name = Constants.TABLE_PREFIX+"role")
public class Role extends IdEntity {
	private String name;

	private List<Authority> authorityList = Lists.newArrayList();
	private List<User> userList = Lists.newArrayList();

	@NotBlank
	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToMany
	@JoinTable(name = Constants.TABLE_PREFIX+"role_authority", joinColumns = { @JoinColumn(name = "role_id") }, inverseJoinColumns = { @JoinColumn(name = "authority_id") })
	public List<Authority> getAuthorityList() {
		return authorityList;
	}

	public void setAuthorityList(List<Authority> authorityList) {
		this.authorityList = authorityList;
	}

	@ManyToMany
	@JoinTable(name = Constants.TABLE_PREFIX+"user_role", joinColumns = { @JoinColumn(name = "role_id") }, inverseJoinColumns = { @JoinColumn(name = "user_id") })
	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
}
