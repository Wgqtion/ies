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
 * 停车场
 * @author XiangXiaoLin
 *
 */
@Entity
@Table(name = Constants.TABLE_PREFIX + "parking_lot")
public class ParkingLot extends BasicEntity {

    private java.lang.String name;
    private java.lang.String baiduLatitudeLng;
    private java.lang.String baiduLatitudeLat;
    private java.lang.String description;
    private Attach photoAttach;
    private Integer carNumber;
    private String address;

    private Boolean isEnabled = true;
    
    private Integer freeCarNum = 0;

    private List<ParkingLotArea> parkingLotAreas = Lists.newArrayList();

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

    /**
     * @return
     */
    @Column(name = "BAIDU_LATITUDE_LNG")
    public java.lang.String getBaiduLatitudeLng() {
        return this.baiduLatitudeLng;
    }

    public void setBaiduLatitudeLng(java.lang.String value) {
        this.baiduLatitudeLng = value;
    }

    /**
     * @return
     */
    @Column(name = "BAIDU_LATITUDE_LAT")
    public java.lang.String getBaiduLatitudeLat() {
        return this.baiduLatitudeLat;
    }

    public void setBaiduLatitudeLat(java.lang.String value) {
        this.baiduLatitudeLat = value;
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

    /**
     * @return
     */
    @Column(name = "CAR_NUMBER")
    public Integer getCarNumber() {
        return this.carNumber;
    }

    public void setCarNumber(Integer value) {
        this.carNumber = value;
    }

    @Column(name = "ADDRESS")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @OneToMany(mappedBy = "parkingLot")
    public List<ParkingLotArea> getParkingLotAreas() {
        return parkingLotAreas;
    }

    public void setParkingLotAreas(List<ParkingLotArea> parkingLotAreas) {
        this.parkingLotAreas = parkingLotAreas;
    }

    /**
     *
     * @return 所有root停车区域，最顶层的区域
     */
    @Transient
    public List<ParkingLotArea> getRootParkingLotAreas() {
        List<ParkingLotArea> vl = Lists.newArrayList();
        for (ParkingLotArea parkingLotArea : this.parkingLotAreas) {
            if (parkingLotArea.getParent() == null) {
            	List<ParkingLotArea> list=parkingLotArea.getChildren();
            	if(list!=null){
            		for(int i=0;i<list.size();i++){
            			if(list.get(i).getIsDelete()){
            				list.remove(i);
            			}
            		}
            	}
            	if(!parkingLotArea.getIsDelete())
                vl.add(parkingLotArea);
            }
        }
        return vl;

    }
    @Transient
    public Integer getFreeCarNum() {
        return freeCarNum;
    }

    public void setFreeCarNum(Integer freeCarNum) {
        this.freeCarNum = freeCarNum;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
