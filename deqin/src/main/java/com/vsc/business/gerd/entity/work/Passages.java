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
 * @author ThinkPad
 *
 */
@Entity
@Table(name = Constants.TABLE_PREFIX +"passages")
public class Passages extends IdEntity {

	 
     private ParkingLot parkinglot;
     private java.lang.String name;
     private java.lang.String xcoordinate;
     private java.lang.String ycoordinate;
     private Boolean isEnabled;
     private java.lang.String mark;
	 

     
    @ManyToOne
 	@JoinColumn(name = "PARKINGLOT_ID")
	public ParkingLot getParkinglot() {
		return parkinglot;
	}

	public void setParkinglot(ParkingLot parkinglot) {
		this.parkinglot = parkinglot;
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
	@Column(name = "XCOORDINATE" )
	public java.lang.String getXcoordinate() {
		return this.xcoordinate;
	}
	
	public void setXcoordinate(java.lang.String value) {
		this.xcoordinate = value;
	}
	
	
	/**
	 * @return
	 */
	@Column(name = "YCOORDINATE" )
	public java.lang.String getYcoordinate() {
		return this.ycoordinate;
	}
	
	public void setYcoordinate(java.lang.String value) {
		this.ycoordinate = value;
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

