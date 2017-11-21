package com.jxgis.ffireserver.web.controller;

import com.jxgis.ffireserver.web.bean.TSFunction;
import com.jxgis.ffireserver.web.bean.TSIcon;
import com.jxgis.ffireserver.core.util.oConvertUtils;
import com.jxgis.ffireserver.web.service.TSFunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by YanWei on 2017/6/27.
 *
 * 系统菜单管理
 *
 * @author 言伟
 *
 */
@Controller
@RequestMapping("/functionController")
public class TSFunctionController {

	private TSFunctionService functionService;

	@Autowired
	public void setFunctionService(TSFunctionService functionService) {
		this.functionService = functionService;
	}

	@RequestMapping(params = "goHref")
	public ModelAndView goHref(TSFunction function, String href, HttpServletRequest req){
		function = functionService.get(TSFunction.class, oConvertUtils.getString(function.getId()));
		req.setAttribute("function", function);
		return new ModelAndView(href);
	}

	//添加菜单-选择图标
	@RequestMapping(params = "choiceIcon")
	public ModelAndView choiceIcon(TSIcon icon, HttpServletRequest req){
		List icons = functionService.findHql("from TSIcon");
		icon = functionService.get(TSIcon.class, icon.getId());
		req.setAttribute("icons", icons);
		req.setAttribute("icon", icon);
		return new ModelAndView("system/function/function-choice-icon");
	}

	//添加菜单-选择父菜单
	@RequestMapping(params = "choiceParentFunction")
	public ModelAndView choiceParentFunction(TSFunction function, HttpServletRequest req){
		function = functionService.get(TSFunction.class, function.getId());
		req.setAttribute("function", function);
		return new ModelAndView("system/function/function-choice-parentFunction");
	}

	
}
