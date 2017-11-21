package com.jxgis.ffireserver.service.bean.response;

import javax.xml.bind.annotation.XmlRootElement;

import com.jxgis.ffireserver.bean.Result;

@XmlRootElement(name = "locationResult")
public class LocationResult {

	private Result result;

	private LocationList locationList;

	public LocationResult() {

	}

	public LocationResult(Result result) {
		this.result = result;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public LocationList getLocationList() {
		return locationList;
	}

	public void setLocationList(LocationList locationList) {
		this.locationList = locationList;
	}

}
