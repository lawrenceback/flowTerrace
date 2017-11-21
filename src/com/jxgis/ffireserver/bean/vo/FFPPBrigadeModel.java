package com.jxgis.ffireserver.bean.vo;

import com.jxgis.ffireserver.bean.FFPPBrigade;

import java.util.ArrayList;
import java.util.List;

/**
 * 专业森林消防队 实体模型
 */
public class FFPPBrigadeModel {
	private List<FFPPBrigade> brigades = new ArrayList<>();

	public List<FFPPBrigade> getBrigades() {
		return brigades;
	}

	public void setBrigades(List<FFPPBrigade> brigades) {
		this.brigades = brigades;
	}
}
