package com.jxgis.ffireserver.web.controller;

import com.jxgis.ffireserver.bean.vo.FFPForestBeltPointModel;
import com.jxgis.ffireserver.core.util.ReturnBody;
import com.jxgis.ffireserver.service.ForestBeltPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/forestBeltPointController")
public class FFPForestBeltPointController {

	private ForestBeltPointService forestBeltPointService;

	@Autowired
	public void setForestBeltPointService(ForestBeltPointService forestBeltPointService) {
		this.forestBeltPointService = forestBeltPointService;
	}

	@RequestMapping(value = "deleteForestBeltPoints")
	@ResponseBody
	public ReturnBody deleteForestBeltPoints(FFPForestBeltPointModel beltPointModels) throws Exception{
		return forestBeltPointService.deleteBeltPoints(beltPointModels.getBeltPointList());
	}

}
