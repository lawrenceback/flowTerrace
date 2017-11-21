package com.jxgis.ffireserver.web.controller;

import com.jxgis.ffireserver.bean.vo.FFPPBrigadeModel;
import com.jxgis.ffireserver.core.util.ReturnBody;
import com.jxgis.ffireserver.service.PBrigadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/pBrigadeController")
public class FFPPBrigadeController {
	private PBrigadeService pBrigadeService;

	@Autowired
	public void setpBrigadeService(PBrigadeService pBrigadeService) {
		this.pBrigadeService = pBrigadeService;
	}

	@RequestMapping(value = "deleteBrigades")
	@ResponseBody
	public ReturnBody deleteBrigades(FFPPBrigadeModel brigadeModel) throws Exception{
		return pBrigadeService.deletePBrigades(brigadeModel.getBrigades());
	}
}
