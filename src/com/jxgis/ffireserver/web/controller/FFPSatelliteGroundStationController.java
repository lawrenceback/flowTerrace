package com.jxgis.ffireserver.web.controller;

import com.jxgis.ffireserver.bean.vo.FFPSatelliteGroundStationModel;
import com.jxgis.ffireserver.core.util.ReturnBody;
import com.jxgis.ffireserver.service.SatelliteGroundStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/satelliteGroundStationController")
public class FFPSatelliteGroundStationController  {

	private SatelliteGroundStationService satelliteGroundStationService;

	@Autowired
	public void setSatelliteGroundStationService(SatelliteGroundStationService satelliteGroundStationService) {
		this.satelliteGroundStationService = satelliteGroundStationService;
	}

	@RequestMapping(value = "deleteSatelliteGroundStations")
	@ResponseBody
	public ReturnBody deleteSatelliteGroundStations(FFPSatelliteGroundStationModel satelliteGroundStationModel) throws Exception{
		return satelliteGroundStationService.deleteSatelliteGroundStations(satelliteGroundStationModel.getSatelliteGroundStations());
	}
}
