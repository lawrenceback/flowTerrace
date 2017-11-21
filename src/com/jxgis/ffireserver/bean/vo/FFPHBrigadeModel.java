package com.jxgis.ffireserver.bean.vo;

import com.jxgis.ffireserver.bean.FFPHBrigade;

import java.util.ArrayList;
import java.util.List;

/***
 * 半专业森林消防队实体模型
 */
public class FFPHBrigadeModel {

	private List<FFPHBrigade> brigades = new ArrayList<>();

	public List<FFPHBrigade> getBrigades() {
		return brigades;
	}

	public void setBrigades(List<FFPHBrigade> brigades) {
		this.brigades = brigades;
	}
}
