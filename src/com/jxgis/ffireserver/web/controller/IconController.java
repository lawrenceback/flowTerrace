package com.jxgis.ffireserver.web.controller;

import com.jxgis.ffireserver.web.bean.TSIcon;
import com.jxgis.ffireserver.web.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by YanWei on 2017/6/23.
 *
 * Icon控制器
 *
 * @author 言伟
 *
 */
@Controller
@RequestMapping("/iconController")
public class IconController {

	private SystemService systemService;

	@Autowired
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	@RequestMapping(params = "goHref")
	public ModelAndView goHref(String href, TSIcon icon, HttpServletRequest req) {
		icon = systemService.get(TSIcon.class, icon.getId());
		req.setAttribute("icon", icon);
		return new ModelAndView(href);
	}
}
