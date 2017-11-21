package com.jxgis.ffireserver.web.controller;

import com.jxgis.ffireserver.bean.vo.FFPOfficeModel;
import com.jxgis.ffireserver.core.util.ReturnBody;
import com.jxgis.ffireserver.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/officeController")
public class FFPOfficeController {

	private OfficeService officeService;

	@Autowired
	public void setOfficeService(OfficeService officeService) {
		this.officeService = officeService;
	}

	@RequestMapping(value = "deleteOffices")
	@ResponseBody
	public ReturnBody deleteOffices(FFPOfficeModel officeModel) throws Exception{
		return officeService.deleteOffices(officeModel.getOffices());
	}
}
