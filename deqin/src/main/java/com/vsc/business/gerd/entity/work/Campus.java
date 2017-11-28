package com.vsc.business.gerd.entity.work;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.vsc.constants.Constants;
import com.vsc.modules.entity.IdEntity;

/**
 * 
 * @author jerry
 *
 */
@Entity
@Table(name = Constants.TABLE_PREFIX + "campus")
public class Campus extends IdEntity {

	private java.lang.String name;
	private ParkingLot parkingLot;
	private java.util.Date createTime;
	private Integer lasttime;
	private Boolean isEnabled=true;
	private java.lang.String remark;
	private Long campusId;

	/**
	 * @return
	 */
	@Column(name = "NAME", unique = true)
	public java.lang.String getName() {
		return this.name;
	}

	public void setName(java.lang.String value) {
		this.name = value;
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
	@Column(name = "LASTTIME")
	public Integer getLasttime() {
		return this.lasttime;
	}

	public void setLasttime(Integer value) {
		this.lasttime = value;
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
	@Column(name = "REMARK")
	public java.lang.String getRemark() {
		return this.remark;
	}

	public void setRemark(java.lang.String value) {
		this.remark = value;
	}

	/**
	 * @return
	 */
	@Column(name = "CAMPUS_ID")
	public Long getCampusId() {
		return this.campusId;
	}

	public void setCampusId(Long value) {
		this.campusId = value;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
