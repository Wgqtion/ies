package com.vsc.business.core.entity.security;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.common.collect.Lists;
import com.vsc.constants.Constants;
import com.vsc.modules.entity.IdEntity;

/**
 * 数据权限实体类
 * @author XiangXiaoLin
 *
 */
@Entity
@Table(name = Constants.TABLE_PREFIX+"authority")
public class Authority extends IdEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3346989985174835049L;
	private Authority parent;
	private String parentCode;
	/**
	 * 类型
	 */
	private Integer resourceType;
	private String name;
	private String value;
	/**
	 * 排序
	 */
	private Integer sort;
	private String code;
	private List<Role> roleList= Lists.newArrayList();

	private List<Authority> children=Lists.newArrayList();
	
	@Column(name = "PARENT_CODE")
	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	@Column(name = "CODE")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@ManyToOne
	@JoinColumn(name = "PARENT_CODE",referencedColumnName="CODE",insertable=false,updatable=false)
	public Authority getParent() {
		return parent;
	}

	public void setParent(Authority parent) {
		this.parent = parent;
	}

	@Column(name = "RESOURCE_TYPE")
	public Integer getResourceType() {
		return resourceType;
	}

	public void setResourceType(Integer resourceType) {
		this.resourceType = resourceType;
	}
	
	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "VALUE")
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Column(name = "SORT")
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@ManyToMany
	@JoinTable(name = Constants.TABLE_PREFIX+"role_authority", joinColumns = { @JoinColumn(name = "AUTHORITY_CODE",referencedColumnName="CODE") }, inverseJoinColumns = { @JoinColumn(name = "ROLE_CODE",referencedColumnName="CODE") })
	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
	
	@OneToMany(mappedBy = "parent")
	public List<Authority> getChildren() {
		return children;
	}

	public void setChildren(List<Authority> children) {
		this.children = children;
	}
	
	
}
