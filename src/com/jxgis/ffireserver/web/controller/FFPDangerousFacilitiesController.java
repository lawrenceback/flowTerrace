package com.jxgis.ffireserver.web.controller;

import com.jxgis.ffireserver.bean.vo.FFPDangerousFacilitiesModel;
import com.jxgis.ffireserver.core.util.ReturnBody;
import com.jxgis.ffireserver.service.DangerousFacilitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/dangerousFacilitiesController")
public class FFPDangerousFacilitiesController {

	private DangerousFacilitiesService dangerousFacilitiesService;

	@Autowired
	public void setDangerousFacilitiesService(DangerousFacilitiesService dangerousFacilitiesService) {
		this.dangerousFacilitiesService = dangerousFacilitiesService;
	}

	@RequestMapping(value = "dangerousFacilities")
	@ResponseBody
	public ReturnBody dangerousFacilities(FFPDangerousFacilitiesModel dangerousFacilitiesModel) throws Exception{
		return dangerousFacilitiesService.deleteDangerousFacilities(dangerousFacilitiesModel.getDangerousFacilities());
	}


}
