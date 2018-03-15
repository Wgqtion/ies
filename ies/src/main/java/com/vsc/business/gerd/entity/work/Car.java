package com.vsc.business.gerd.entity.work;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.vsc.constants.Constants;
import com.vsc.modules.entity.BasicEntity;

/**
 * 车辆 实体类
 * @author XiangXiaoLin
 *
 */
@Entity
@Table(name = Constants.TABLE_PREFIX + "car")
public class Car extends BasicEntity {

	private java.lang.String owner;
	private java.lang.String carNo;
	private Date expireDate;
	private java.lang.String carType;
	private java.lang.String telphone;

	/**
     * 所属公司
     */
    private Company company;
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
	
	@Column(name = "TELPHONE")
	public java.lang.String getTelphone() {
		return telphone;
	}

	public void setTelphone(java.lang.String telphone) {
		this.telphone = telphone;
	}

	/**
	 * @return
	 */
	@Column(name = "OWNER")
	public java.lang.String getOwner() {
		return this.owner;
	}

	public void setOwner(java.lang.String value) {
		this.owner = value;
	}

	/**
	 * @return
	 */
	@Column(name = "CAR_NO")
	public java.lang.String getCarNo() {
		return this.carNo;
	}

	public void setCarNo(java.lang.String value) {
		this.carNo = value;
	}

	/**
	 * @return
	 */
	@Column(name = "EXPIRE_DATE")
	public Date getExpireDate() {
		return this.expireDate;
	}

	public void setExpireDate(Date value) {
		this.expireDate = value;
	}

	/**
	 * @return
	 */
	@Column(name = "CAR_TYPE")
	public java.lang.String getCarType() {
		return this.carType;
	}

	public void setCarType(java.lang.String value) {
		this.carType = value;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
