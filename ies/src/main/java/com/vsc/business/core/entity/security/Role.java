package com.vsc.business.core.entity.security;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.google.common.collect.Lists;
import com.vsc.business.gerd.entity.work.Company;
import com.vsc.constants.Constants;
import com.vsc.modules.entity.IdEntity;

@Entity
@Table(name = Constants.TABLE_PREFIX+"role")
public class Role extends IdEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2250341265166180478L;
	private String code;
	private String name;

	private List<Authority> authorityList = Lists.newArrayList();
	private List<Company> companyList = Lists.newArrayList();

	
	
	@Column(name = "code")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToMany
	@JoinTable(name = Constants.TABLE_PREFIX+"role_authority", joinColumns = { @JoinColumn(name = "ROLE_CODE",referencedColumnName="CODE") }, inverseJoinColumns = { @JoinColumn(name = "AUTHORITY_CODE",referencedColumnName="CODE") })
	public List<Authority> getAuthorityList() {
		return authorityList;
	}

	public void setAuthorityList(List<Authority> authorityList) {
		this.authorityList = authorityList;
	}
	

	@ManyToMany
	@JoinTable(name = Constants.TABLE_PREFIX+"company_role", joinColumns = { @JoinColumn(name = "ROLE_CODE",referencedColumnName="CODE") }, inverseJoinColumns = { @JoinColumn(name = "COMPANY_CODE",referencedColumnName="CODE") })
	public List<Company> getCompanyList() {
		return companyList;
	}

	public void setCompanyList(List<Company> companyList) {
		this.companyList = companyList;
	}
}
