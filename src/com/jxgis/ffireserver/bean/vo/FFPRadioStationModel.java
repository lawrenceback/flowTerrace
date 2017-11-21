package com.jxgis.ffireserver.bean.vo;

import com.jxgis.ffireserver.bean.FFPRadioStation;

import java.util.ArrayList;
import java.util.List;

/**
 * 无线电台站实体模型
 */
public class FFPRadioStationModel {

	private List<FFPRadioStation> radioStations = new ArrayList<>();

	public List<FFPRadioStation> getRadioStations() {
		return radioStations;
	}

	public void setRadioStations(List<FFPRadioStation> radioStations) {
		this.radioStations = radioStations;
	}
}
