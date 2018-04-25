package com.vsc.business.gerd.entity.work;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.vsc.constants.Constants;
import com.vsc.modules.entity.BasicEntity;

 

/**
 * 出入口实体类
 * @author XiangXiaoLin
 *
 */
@Entity
@Table(name = Constants.TABLE_PREFIX +"parking_passages")
public class ParkingPassages extends BasicEntity implements Serializable{

	 
     /**
	 * 
	 */
	private static final long serialVersionUID = 9067534546355004129L;
	private ParkingLot parkingLot;
     private java.lang.String name;
     /**
      * 出入口编号
      */
     private String code;
     /**
      * 纬度坐标
      */
     private java.lang.String itudeLong;
     /**
      * 经度坐标
      */
     private java.lang.String itudeLat;

     private Boolean isEnabled;
     private java.lang.String mark;
	 

     /**
 	 * @return
 	 */
 	@ManyToOne
 	@JoinColumn(name = "PARKING_LOT_CODE",referencedColumnName="CODE")
 	public ParkingLot getParkingLot() {
 		return this.parkingLot;
 	}

 	public void setParkingLot(ParkingLot parkingLot) {
 		this.parkingLot = parkingLot;
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
	@Column(name = "CODE" )
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return
	 */
	@Column(name = "NAME" )
	public java.lang.String getName() {
		return this.name;
	}
	
	public void setName(java.lang.String value) {
		this.name = value;
	}
	
	/**
	 * @return
	 */
	@Column(name = "IS_ENABLED" )
	public Boolean getIsEnabled() {
		return this.isEnabled;
	}
	
	public void setIsEnabled(Boolean value) {
		this.isEnabled = value;
	}
	
	
	/**
	 * @return
	 */
	@Column(name = "MARK" )
	public java.lang.String getMark() {
		return this.mark;
	}
	
	public void setMark(java.lang.String value) {
		this.mark = value;
	}
	
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}

