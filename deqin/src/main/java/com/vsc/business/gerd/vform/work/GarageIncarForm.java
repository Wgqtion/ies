package com.vsc.business.gerd.vform.work;

import java.util.Date;

import com.vsc.business.gerd.entity.work.ParkingGarageCarnoLog;

public class GarageIncarForm {
	private Date intime;
	private ParkingGarageCarnoLog[] carnologs;

	public Date getIntime() {
		return intime;
	}

	public void setIntime(Date intime) {
		this.intime = intime;
	}

	public ParkingGarageCarnoLog[] getCarnologs() {
		return carnologs;
	}

	public void setCarnologs(ParkingGarageCarnoLog[] carnologs) {
		this.carnologs = carnologs;
	}

}
