package com.jxgis.ffireserver.web.controller;

import com.jxgis.ffireserver.bean.vo.FFPImportantUnitsModel;
import com.jxgis.ffireserver.core.util.ReturnBody;
import com.jxgis.ffireserver.service.ImportantUnitsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/importantUnitsController")
public class FFPImportantUnitsController {

	private ImportantUnitsService importantUnitsService;

	@Autowired
	public void setImportantUnitsService(ImportantUnitsService importantUnitsService) {
		this.importantUnitsService = importantUnitsService;
	}

	@RequestMapping(value = "deleteImportants")
	@ResponseBody
	public ReturnBody deleteImportants(FFPImportantUnitsModel importantUnitsModel) throws Exception{
		return importantUnitsService.deleteImportants(importantUnitsModel.getImportantUnits());
	}
}
