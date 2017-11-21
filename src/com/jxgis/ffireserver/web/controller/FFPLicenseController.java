package com.jxgis.ffireserver.web.controller;

import com.jxgis.ffireserver.bean.vo.FFPLicenseModel;
import com.jxgis.ffireserver.core.util.ReturnBody;
import com.jxgis.ffireserver.service.LicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/licenseController")
public class FFPLicenseController {

	private LicenseService licenseiService;

	@Autowired
	public void setLicenseiService(LicenseService licenseiService) {
		this.licenseiService = licenseiService;
	}

	@RequestMapping(value = "deleteLicenses")
	@ResponseBody
	public ReturnBody deleteLicenses(FFPLicenseModel licenseModel, HttpServletRequest req) throws Exception{
		return licenseiService.deleteLicenses(licenseModel.getLicenses());
	}
}
