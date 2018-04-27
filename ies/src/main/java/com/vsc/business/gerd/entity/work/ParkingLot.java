package com.vsc.business.gerd.entity.work;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.common.collect.Lists;
import com.vsc.business.core.entity.sys.upload.Attach;
import com.vsc.constants.Constants;
import com.vsc.modules.entity.BasicEntity;

/**
 * 场区 实体类
 * @author XiangXiaoLin
 *
 */
@Entity
@Table(name = Constants.TABLE_PREFIX + "parking_lot")
public class ParkingLot extends BasicEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1079864856828289383L;
	private String code;
	private ParkingLot parent;
	private String parentCode;
    private java.lang.String name;
    /**
     * 纬度坐标
     */
    private java.lang.String itudeLong;
    /**
     * 经度坐标
     */
    private java.lang.String itudeLat;
    
    private java.lang.String description;
    private Attach photoAttach;
    private String address;

    private Boolean isEnabled = true;
    
    private Org org;
    
    private String orgCode;
    
    /**
     * 所属公司
     */
    private Company company;
    /**
     * 公司code
     */
    private String companyCode;
    
    private List<ParkingLot> children=Lists.newArrayList();
    
    
    /*
     * 非持久字段
     */
    //车位总数
    private int garageNum;
    //可用余位数
    private int surplusNum;
    //可用地锁列表
    private List<ParkingLock> parkingLocks=Lists.newArrayList();
    private boolean isLast;
    
    @Transient
    public boolean getIsLast() {
		return isLast;
	}

	public void setIsLast(boolean isLast) {
		this.isLast = isLast;
	}

	@Transient
    public List<ParkingLock> getParkingLocks() {
		return parkingLocks;
	}

	public void setParkingLocks(List<ParkingLock> parkingLocks) {
		this.parkingLocks = parkingLocks;
	}

	@Transient
    public int getGarageNum() {
		return garageNum;
	}

	public void setGarageNum(int garageNum) {
		this.garageNum = garageNum;
	}
	@Transient
	public int getSurplusNum() {
		return surplusNum;
	}

	public void setSurplusNum(int surplusNum) {
		this.surplusNum = surplusNum;
	}

	@Column(name = "CODE")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Column(name = "PARENT_CODE")
	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	@ManyToOne
	@JoinColumn(name = "PARENT_CODE",referencedColumnName="CODE",insertable=false,updatable=false)
	public ParkingLot getParent() {
		return parent;
	}

	public void setParent(ParkingLot parent) {
		this.parent = parent;
	}
	
	@OneToMany(mappedBy = "parent")
	public List<ParkingLot> getChildren() {
		return children;
	}

	public void setChildren(List<ParkingLot> children) {
		this.children = children;
	}
	
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
    
    /**
     * @return
     */
    @Column(name = "ORG_CODE")
    public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	@ManyToOne
    @JoinColumn(name="ORG_CODE",referencedColumnName="CODE",insertable=false,updatable=false)
    public Org getOrg() {
		return org;
	}

	public void setOrg(Org org) {
		this.org = org;
	}

	/**
     * @return
     */
    @Column(name = "NAME")
    public java.lang.String getName() {
        return this.name;
    }

    public void setName(java.lang.String value) {
        this.name = value;
    }

    /**
     * @return
     */
    @Column(name = "IS_ENABLED")
    public Boolean getIsEnabled() {
        return this.isEnabled;
    }

    public void setIsEnabled(Boolean value) {
        this.isEnabled = value;
    }

    @Column(name = "ITUDE_LONG")
    public java.lang.String getItudeLong() {
		return itudeLong;
	}

	public void setItudeLong(java.lang.String itudeLong) {
		this.itudeLong = itudeLong;
	}
	@Column(name = "ITUDE_LAT")
	public java.lang.String getItudeLat() {
		return itudeLat;
	}

	public void setItudeLat(java.lang.String itudeLat) {
		this.itudeLat = itudeLat;
	}

	/**
     * @return
     */
    @Column(name = "DESCRIPTION")
    public java.lang.String getDescription() {
        return this.description;
    }

    public void setDescription(java.lang.String value) {
        this.description = value;
    }

    @ManyToOne
    @JoinColumn(name = "photo_attach_id")
    public Attach getPhotoAttach() {
        return photoAttach;
    }

    public void setPhotoAttach(Attach photoAttach) {
        this.photoAttach = photoAttach;
    }

    @Column(name = "ADDRESS")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
