package com.jxgis.ffireserver.web.controller;

import com.jxgis.ffireserver.web.bean.TSDepart;
import com.jxgis.ffireserver.core.util.oConvertUtils;
import com.jxgis.ffireserver.web.service.TSDepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by YanWei on 2017/7/3.
 *
 */
@Controller
@RequestMapping("/departController")
public class TSDepartController {

	private TSDepartService departService;

	@Autowired
	public void setDepartService(TSDepartService departService) {
		this.departService = departService;
	}

	@RequestMapping(params = "goHref")
	public ModelAndView goHref(TSDepart depart, String href, HttpServletRequest req){
		depart = departService.get(TSDepart.class, oConvertUtils.getString(depart.getId()));
		req.setAttribute("depart", depart);
		return new ModelAndView(href);
	}


}
