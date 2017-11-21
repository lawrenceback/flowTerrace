package com.jxgis.ffireserver.web.controller;

import com.jxgis.ffireserver.bean.vo.FFPPlanewaterpointModel;
import com.jxgis.ffireserver.core.util.ReturnBody;
import com.jxgis.ffireserver.service.PlanewaterpointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/planewaterpointController")
public class FFPPlanewaterpointController {

	private PlanewaterpointService planewaterpointService;

	@Autowired
	public void setPlanewaterpointService(PlanewaterpointService planewaterpointService) {
		this.planewaterpointService = planewaterpointService;
	}

	@RequestMapping(value = "deleteplanewaterpoints")
	@ResponseBody
	public ReturnBody deleteplanewaterpoints(FFPPlanewaterpointModel planewaterpointModel) throws Exception{
		return planewaterpointService.deleteplanewaterpoints(planewaterpointModel.getPlanewaterpoints());
	}
}
