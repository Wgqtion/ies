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
import com.vsc.modules.entity.IdEntity;

/**
 * 校区[停车场]
 *
 * @author jerry
 *
 */
@Entity
@Table(name = Constants.TABLE_PREFIX + "parking_lot")
public class ParkingLot extends IdEntity {

    private java.lang.String name;
    private java.util.Date createTime;
    private Boolean isEnabled = true;
    private java.lang.String baiduLatitudeLng;
    private java.lang.String baiduLatitudeLat;
    private java.lang.String description;
    private Attach photoAttach;
    private String code;
    private Integer carNumber;
    private String address;

    
    private Integer freeCarNum = 0;

    private List<ParkingLotArea> parkingLotAreas = Lists.newArrayList();

    private Org org;
    
    
    @ManyToOne
    @JoinColumn(name="ORG_CODE",referencedColumnName="CODE")
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
    @Column(name = "CREATE_TIME")
    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(java.util.Date value) {
        this.createTime = value;
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

    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
