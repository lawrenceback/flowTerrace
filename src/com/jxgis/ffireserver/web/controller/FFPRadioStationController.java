package com.jxgis.ffireserver.web.controller;

import com.jxgis.ffireserver.bean.vo.FFPRadioStationModel;
import com.jxgis.ffireserver.core.util.ReturnBody;
import com.jxgis.ffireserver.service.RadioStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/radioStationController")
public class FFPRadioStationController {

	private RadioStationService radioStationService;

	@Autowired
	public void setRadioStationService(RadioStationService radioStationService) {
		this.radioStationService = radioStationService;
	}

	@RequestMapping(value = "deleteRadioStations")
	@ResponseBody
	public ReturnBody deleteRadioStations(FFPRadioStationModel radioStationModel) throws Exception{
		return radioStationService.deleteRadioStations(radioStationModel.getRadioStations());
	}
}
