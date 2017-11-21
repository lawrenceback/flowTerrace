package com.jxgis.ffireserver.web.controller;

import com.jxgis.ffireserver.bean.vo.FFPArtificiallakeModel;
import com.jxgis.ffireserver.core.util.ReturnBody;
import com.jxgis.ffireserver.service.ArtificiallakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/artificiallakeController")
public class FFPArtificiallakeController {

	private ArtificiallakeService artificiallakeService;

	@Autowired
	public void setArtificiallakeService(ArtificiallakeService artificiallakeService) {
		this.artificiallakeService = artificiallakeService;
	}

	@RequestMapping(value = "deleteArtificiallakes")
	@ResponseBody
	public ReturnBody deleteArtificiallakes(FFPArtificiallakeModel artificiallakeModel) throws Exception{
		return artificiallakeService.deleteArtificiallakes(artificiallakeModel.getArtificiallake());
	}
}
