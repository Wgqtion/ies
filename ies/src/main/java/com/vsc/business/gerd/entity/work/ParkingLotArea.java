package com.vsc.business.gerd.entity.work;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.google.common.collect.Lists;
import com.vsc.business.core.entity.sys.upload.Attach;
import com.vsc.constants.Constants;
import com.vsc.modules.entity.BasicEntity;

/**
 * 停车片区
 * @author XiangXiaoLin
 *
 */
@Entity
@Table(name = Constants.TABLE_PREFIX + "parking_lot_area")
public class ParkingLotArea extends BasicEntity {

	private java.lang.String name;
	private Boolean isEnabled;
	private java.lang.String baiduLatitudeLng;
	private java.lang.String baiduLatitudeLat;
	private java.lang.String description;
	private Attach photoAttach;
	private Integer carNumber;
	private ParkingLot parkingLot;
	private ParkingLotArea parent;
	private List<ParkingLotArea> children = Lists.newArrayList();
	private String fullIndexName;
	
	private Integer freeCarNum=0;

   
	
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

	/**
	 * @return
	 */
	@ManyToOne
	@JoinColumn(name = "PARKING_LOT_ID")
	public ParkingLot getParkingLot() {
		return this.parkingLot;
	}

	public void setParkingLot(ParkingLot value) {
		this.parkingLot = value;
	}

	@ManyToOne
	@JoinColumn(name = "pid")
	@NotFound(action = NotFoundAction.IGNORE)
	public ParkingLotArea getParent() {
		return parent;
	}

	public void setParent(ParkingLotArea parent) {
		this.parent = parent;
	}

	@OneToMany(mappedBy = "parent")
	@OrderBy("id asc")
	public List<ParkingLotArea> getChildren() {
		return children;
	}
	
	
	@Transient
	public String getFullName(){
		if(this.parent==null){
			return this.name;
		}
		return this.parent.getFullName()+"-"+this.name;
	}
	@Column(name = "FULL_INDEX_NAME")
	public String getFullIndexName() {
		return fullIndexName;
	}

	public void setFullIndexName(String fullIndexName) {
		this.fullIndexName = fullIndexName;
	}

	
	@Transient
	public Integer getFreeCarNum() {
		return freeCarNum;
	}

	public void setFreeCarNum(Integer freeCarNum) {
		this.freeCarNum = freeCarNum;
	}

	/**
	 * 子栏目
	 * @param children
	 */
	public void setChildren(List<ParkingLotArea> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
