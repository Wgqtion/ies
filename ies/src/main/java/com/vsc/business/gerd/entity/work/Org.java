package com.vsc.business.gerd.entity.work;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
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
	 * 组织名称
	 */
	private String name;
	/**
	 * 编码
	 */
	private String code;

	private List<ParkingLot> parkingLots=new ArrayList<ParkingLot>();
	
	
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
