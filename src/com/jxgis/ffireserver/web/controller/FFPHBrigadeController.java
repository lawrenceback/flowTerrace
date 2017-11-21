package com.jxgis.ffireserver.web.controller;

import com.jxgis.ffireserver.bean.vo.FFPHBrigadeModel;
import com.jxgis.ffireserver.core.util.ReturnBody;
import com.jxgis.ffireserver.service.BrigadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 半专业森林消防队 控制器
 *
 */
@Controller
@RequestMapping(value = "/hBrigadeController")
public class FFPHBrigadeController {

	private BrigadeService brigadeService;

	@Autowired
	public void setBrigadeService(BrigadeService brigadeService) {
		this.brigadeService = brigadeService;
	}

	@RequestMapping(value = "deleteBrigades")
	@ResponseBody
	public ReturnBody deleteBrigades(FFPHBrigadeModel brigadeModel) throws Exception{
		return brigadeService.deleteBrigades(brigadeModel.getBrigades());
	}
}
