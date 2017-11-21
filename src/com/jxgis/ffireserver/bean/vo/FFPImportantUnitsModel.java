package com.jxgis.ffireserver.bean.vo;

import com.jxgis.ffireserver.bean.FFPImportantUnits;

import java.util.ArrayList;
import java.util.List;

/**
 * 重点防火单位 实体模型
 */
public class FFPImportantUnitsModel {
	private List<FFPImportantUnits> importantUnits = new ArrayList<>();

	public List<FFPImportantUnits> getImportantUnits() {
		return importantUnits;
	}

	public void setImportantUnits(List<FFPImportantUnits> importantUnits) {
		this.importantUnits = importantUnits;
	}
}
