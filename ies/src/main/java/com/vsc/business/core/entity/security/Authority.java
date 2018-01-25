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
@Table(name = Constants.TABLE_PREFIX+"authority")
public class Authority extends IdEntity {
	private String displayName;
	private String name;
	private List<Resource> resourceList = Lists.newArrayList();
	private List<Role> roleList= Lists.newArrayList();

	@Column(name = "display_name")
	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	@NotBlank
	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToMany
	@JoinTable(name = Constants.TABLE_PREFIX+"resource_authority", joinColumns = { @JoinColumn(name = "authority_id") }, inverseJoinColumns = { @JoinColumn(name = "resource_id") })
	public List<Resource> getResourceList() {
		return resourceList;
	}

	public void setResourceList(List<Resource> resourceList) {
		this.resourceList = resourceList;
	}
	
	
	@ManyToMany
	@JoinTable(name = Constants.TABLE_PREFIX+"role_authority", joinColumns = { @JoinColumn(name = "authority_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

}
