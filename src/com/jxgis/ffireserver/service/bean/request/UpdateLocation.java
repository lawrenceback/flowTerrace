package com.jxgis.ffireserver.service.bean.request;

import javax.xml.bind.annotation.XmlRootElement;

import com.jxgis.ffireserver.bean.Location;

@XmlRootElement(name = "updateLocation")
public class UpdateLocation {
	
	private Location location;
	
	public UpdateLocation() {
		
	}
	
	public Location getLocation() {
		return location;
	}
	
	public void setLocation(Location location) {
		this.location = location;
	}
	
}
