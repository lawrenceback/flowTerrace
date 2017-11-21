package com.jxgis.ffireserver.bean.vo;

import com.jxgis.ffireserver.bean.FFPDangerousFacilities;

import java.util.ArrayList;
import java.util.List;

/**
 * 林区危险及重要性实体模型
 */
public class FFPDangerousFacilitiesModel {
	private List<FFPDangerousFacilities> dangerousFacilities = new ArrayList<>();

	public List<FFPDangerousFacilities> getDangerousFacilities() {
		return dangerousFacilities;
	}

	public void setDangerousFacilities(List<FFPDangerousFacilities> dangerousFacilities) {
		this.dangerousFacilities = dangerousFacilities;
	}
}
