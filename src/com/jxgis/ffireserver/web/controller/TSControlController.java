package com.jxgis.ffireserver.web.controller;

import com.jxgis.ffireserver.core.util.ReturnBody;
import com.jxgis.ffireserver.web.bean.TSControlRolePermissions;
import com.jxgis.ffireserver.web.service.TSControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by YanWei on 2017/7/20.
 *
 * 控件控制器
 *
 */
@Controller
@RequestMapping("/controlController")
public class TSControlController {

	private TSControlService controlService;

	@Autowired
	public void setControlService(TSControlService controlService) {
		this.controlService = controlService;
	}

	@RequestMapping(params = "addRoleControl")
	@ResponseBody
	public ReturnBody addRoleControl(TSControlRolePermissions rolePermissions, HttpServletRequest req) throws Exception{
		return controlService.addRoleControl(rolePermissions);
	}

	@RequestMapping(params = "removeRoleControl")
	@ResponseBody
	public ReturnBody removeRoleControl(TSControlRolePermissions rolePermissions, HttpServletRequest req) throws Exception{
		return controlService.removeRoleControl(rolePermissions);
	}
}
