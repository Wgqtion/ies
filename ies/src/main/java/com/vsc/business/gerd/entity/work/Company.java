package com.vsc.business.gerd.entity.work;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.common.collect.Lists;
import com.vsc.business.core.entity.security.Role;
import com.vsc.business.core.entity.security.User;
import com.vsc.constants.Constants;
import com.vsc.modules.entity.BasicEntity;

/**
 * 公司实体类
 * @author XiangXiaoLin
 *
 */
@Entity
@Table(name = Constants.TABLE_PREFIX + "company")
public class Company extends BasicEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3913899680524266661L;
	/**
	 * 公司名称
	 */
	private String name;
	/**
	 * 公司编码
	 */
	private String code;
	
	/**
	 * 电话号码
	 */
	private String telephone;
	/**
	 * 地址
	 */
	private String address;
	
    private List<Role> roleList = Lists.newArrayList();

    private List<User> users= Lists.newArrayList();
    
    
    @OneToMany(mappedBy = "company")
    public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@ManyToMany
    @JoinTable(name = Constants.TABLE_PREFIX + "company_role", joinColumns = {
        @JoinColumn(name = "COMPANY_CODE",referencedColumnName="CODE")}, inverseJoinColumns = {
        @JoinColumn(name = "ROLE_CODE",referencedColumnName="CODE")})
    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
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

	@Column(name = "TELEPHONE")
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Column(name = "ADDRESS")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
}
