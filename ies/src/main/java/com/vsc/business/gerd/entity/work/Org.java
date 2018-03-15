package com.vsc.business.gerd.entity.work;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.vsc.constants.Constants;
import com.vsc.modules.entity.BasicEntity;

/**
 * 用户分组实体类
 * @author XiangXiaoLin
 *
 */
@Entity
@Table(name = Constants.TABLE_PREFIX + "org")
public class Org extends BasicEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3913899680524266661L;
	/**
	 * 组织名称
	 */
	private String name;
	/**
	 * 编码
	 */
	private String code;

	private List<ParkingLot> parkingLots=new ArrayList<ParkingLot>();
	
	private List<WxUser> users=new ArrayList<WxUser>();
	
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
	
	
	@ManyToMany
	@JoinTable(name = Constants.TABLE_PREFIX+"org_user", joinColumns = { @JoinColumn(name = "org_code",referencedColumnName="code") }, inverseJoinColumns = { @JoinColumn(name = "user_id") })
	public List<WxUser> getUsers() {
		return users;
	}

	public void setUsers(List<WxUser> users) {
		this.users = users;
	}

	@OneToMany
	@JoinColumn(name = "ORG_CODE",referencedColumnName="CODE")
	public List<ParkingLot> getParkingLots() {
		return parkingLots;
	}

	public void setParkingLots(List<ParkingLot> parkingLots) {
		this.parkingLots = parkingLots;
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
	
}
