package com.jxgis.ffireserver.web.controller;

import com.jxgis.ffireserver.bean.FFPFireHot;
import com.jxgis.ffireserver.service.FireHotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by YanWei on 2017/7/14.
 *
 * 火灾热点控制器
 *
 */
@Controller
@RequestMapping("/fireHotController")
public class FireHotController {

	private FireHotService fireHotService;

	@Autowired
	public void setFireHotService(FireHotService fireHotService) {
		this.fireHotService = fireHotService;
	}

	@RequestMapping(params = "goHref")
	public ModelAndView goHref(FFPFireHot fireHot, String href, HttpServletRequest req, HttpServletResponse res) throws Exception{
		fireHot = fireHotService.get(FFPFireHot.class, fireHot.getId());
		req.setAttribute("fireHot", fireHot);
		return new ModelAndView(href);
	}
}
