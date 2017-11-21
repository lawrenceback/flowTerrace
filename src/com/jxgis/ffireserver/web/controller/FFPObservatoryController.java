package com.jxgis.ffireserver.web.controller;

import com.jxgis.ffireserver.bean.vo.FFPObservatoryModel;
import com.jxgis.ffireserver.core.util.ReturnBody;
import com.jxgis.ffireserver.service.ObservatoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by YanWei on 2017/10/1.
 *
 * 瞭望台控制器
 *
 */
@Controller
@RequestMapping(value = "/observatoryController")
public class FFPObservatoryController {

	private ObservatoryService observatoryService;

	@Autowired
	public void setObservatoryService(ObservatoryService observatoryService) {
		this.observatoryService = observatoryService;
	}

	@RequestMapping(value = "deleteObservatorys")
	@ResponseBody
	public ReturnBody deleteObservatorys(FFPObservatoryModel observatoryModel) throws Exception{
		return observatoryService.deleteObservatorys(observatoryModel.getObservatories());
	}
}
