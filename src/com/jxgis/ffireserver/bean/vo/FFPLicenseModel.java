package com.jxgis.ffireserver.bean.vo;

import com.jxgis.ffireserver.bean.FFPLicense;

import java.util.ArrayList;
import java.util.List;

public class FFPLicenseModel {
	private List<FFPLicense> licenses = new ArrayList<>();

	public List<FFPLicense> getLicenses() {
		return licenses;
	}

	public void setLicenses(List<FFPLicense> licenses) {
		this.licenses = licenses;
	}
}
