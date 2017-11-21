package com.jxgis.ffireserver.bean.vo;

import com.jxgis.ffireserver.bean.FFPOffice;

import java.util.ArrayList;
import java.util.List;

/***
 * 森林防火办公室 实体模型
 */
public class FFPOfficeModel {
	private List<FFPOffice> offices = new ArrayList<>();

	public List<FFPOffice> getOffices() {
		return offices;
	}

	public void setOffices(List<FFPOffice> offices) {
		this.offices = offices;
	}
}
