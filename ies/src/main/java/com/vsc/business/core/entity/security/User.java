package com.vsc.business.core.entity.security;

import java.util.Date;
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
import com.vsc.constants.Constants;
import com.vsc.modules.entity.IdEntity;

@Entity
@Table(name = Constants.TABLE_PREFIX + "user")
public class User extends IdEntity {

    private String weixinId;
    private String loginName;
    private String name;
    private String plainPassword;
    private String password;
    private String salt;
    private Date createTime;
    private Long userType = 1L;
    private Boolean isEnabled = Boolean.TRUE;
    private Date updateTime;
    private User createUser;
    private java.lang.String gender = "1";
    private java.lang.String mobilePhone;
    private java.lang.String contactAddress;
    private java.lang.String sourceType;
    private String carNumber;

    private List<Attach> attachs = Lists.newArrayList();
    private List<Role> roleList = Lists.newArrayList();



    /**
     * 微信ID
     *
     * @return
     */
    @Column(name = "WEIXIN_ID")
    public String getWeixinId() {
        return weixinId;
    }

    public void setWeixinId(String weixinId) {
        this.weixinId = weixinId;
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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @ManyToOne
    @JoinColumn(name = "create_user_id")
    public User getCreateUser() {
        return createUser;
    }

    public void setCreateUser(User createUser) {
        this.createUser = createUser;
    }

    @OneToMany(mappedBy = "user")
    public List<Attach> getAttachs() {
        return attachs;
    }

    public void setAttachs(List<Attach> attachs) {
        this.attachs = attachs;
    }

    public Long getUserType() {
        return userType;
    }

    public void setUserType(Long userType) {
        this.userType = userType;
    }

    public Boolean getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    
    /**
     * @return
     */
    @Column(name = "GENDER")
    public java.lang.String getGender() {
        return this.gender;
    }

    public void setGender(java.lang.String value) {
        this.gender = value;
    }

  

    /**
     * @return
     */
    @Column(name = "MOBILE_PHONE")
    public java.lang.String getMobilePhone() {
        return this.mobilePhone;
    }

    public void setMobilePhone(java.lang.String value) {
        this.mobilePhone = value;
    }



    /**
     * @return
     */
    @Column(name = "CONTACT_ADDRESS")
    public java.lang.String getContactAddress() {
        return this.contactAddress;
    }

    public void setContactAddress(java.lang.String value) {
        this.contactAddress = value;
    }

   

    /**
     * @return
     */
    @Column(name = "SOURCE_TYPE")
    public java.lang.String getSourceType() {
        return this.sourceType;
    }

    public void setSourceType(java.lang.String value) {
        this.sourceType = value;
    }


    @Column(name = "CAR_NUMBER")
    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
