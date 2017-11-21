package com.jxgis.ffireserver.bean.vo;

import com.jxgis.ffireserver.bean.FFPSatelliteGroundStation;

import java.util.ArrayList;
import java.util.List;


public class FFPSatelliteGroundStationModel {

	private List<FFPSatelliteGroundStation> satelliteGroundStations = new ArrayList<>();

	public List<FFPSatelliteGroundStation> getSatelliteGroundStations() {
		return satelliteGroundStations;
	}

	public void setSatelliteGroundStations(List<FFPSatelliteGroundStation> satelliteGroundStations) {
		this.satelliteGroundStations = satelliteGroundStations;
	}
}
