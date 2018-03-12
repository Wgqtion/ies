package com.vsc.business.core.entity.security;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.vsc.business.core.entity.sys.upload.Attach;
import com.vsc.business.gerd.entity.work.Company;
import com.vsc.constants.Constants;
import com.vsc.modules.entity.BasicEntity;

@Entity
@Table(name = Constants.TABLE_PREFIX + "user")
public class User extends BasicEntity {

    private String loginName;
    private String name;
    private String plainPassword;
    private String password;
    private Boolean isEnabled = Boolean.TRUE;
    
    /**
     * 公司
     */
    private Company company;

    private List<Attach> attachs = Lists.newArrayList();
    private List<Role> roleList = Lists.newArrayList();
    /**
     * 公司code
     */
    private String companyCode;
    
    /**
     * @return
     */
    @Column(name = "COMPANY_CODE")
    public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

    @ManyToOne
	@JoinColumn(name = "COMPANY_CODE",referencedColumnName="CODE",insertable=false,updatable=false)
    public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	/*
	 * 新增字段-结束
     */
    @NotBlank
    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    @NotBlank
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // 不持久化到数据库，也不显示在Restful接口的属性.
    @Transient
    @JsonIgnore
    public String getPlainPassword() {
        return plainPassword;
    }

    public void setPlainPassword(String plainPassword) {
        this.plainPassword = plainPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @ManyToMany
    @JoinTable(name = Constants.TABLE_PREFIX + "user_role", joinColumns = {
        @JoinColumn(name = "user_id")}, inverseJoinColumns = {
        @JoinColumn(name = "role_id")})
    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    @OneToMany(mappedBy = "user")
    public List<Attach> getAttachs() {
        return attachs;
    }

    public void setAttachs(List<Attach> attachs) {
        this.attachs = attachs;
    }

    public Boolean getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
