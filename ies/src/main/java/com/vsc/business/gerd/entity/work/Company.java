package com.vsc.business.gerd.entity.work;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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
