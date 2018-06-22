package com.vsc.business.gerd.vform.work;

import java.util.Date;

import com.vsc.business.gerd.entity.work.ParkingCameraLog;

public class GarageIncarForm {
	private Date intime;
	private ParkingCameraLog[] parkingVideos;

	public Date getIntime() {
		return intime;
	}

	public void setIntime(Date intime) {
		this.intime = intime;
	}

	public ParkingCameraLog[] getParkingVideos() {
		return parkingVideos;
	}

	public void setParkingVideos(ParkingCameraLog[] parkingVideos) {
		this.parkingVideos = parkingVideos;
	}

}
