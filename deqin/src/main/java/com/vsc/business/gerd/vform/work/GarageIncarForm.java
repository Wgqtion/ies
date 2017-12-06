package com.vsc.business.gerd.vform.work;

import java.util.Date;

import com.vsc.business.gerd.entity.work.ParkingVideo;

public class GarageIncarForm {
	private Date intime;
	private ParkingVideo[] parkingVideos;

	public Date getIntime() {
		return intime;
	}

	public void setIntime(Date intime) {
		this.intime = intime;
	}

	public ParkingVideo[] getParkingVideos() {
		return parkingVideos;
	}

	public void setParkingVideos(ParkingVideo[] parkingVideos) {
		this.parkingVideos = parkingVideos;
	}

}
