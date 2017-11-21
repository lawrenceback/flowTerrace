package com.jxgis.ffireserver.service.bean.response;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.jxgis.ffireserver.bean.Location;

@XmlRootElement(name = "locationList")
public class LocationList {

	private List<Location> location;

	public List<Location> getLocation() {
		return location;
	}

	public void setLocation(List<Location> location) {
		this.location = location;
	}

}
