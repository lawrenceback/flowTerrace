package com.jxgis.ffireserver.web.controller;

import com.jxgis.ffireserver.bean.FFPRemoteMonitoring;
import com.jxgis.ffireserver.bean.vo.FFPRemoteMonitoringModel;
import com.jxgis.ffireserver.core.util.ReturnBody;
import com.jxgis.ffireserver.service.RemoteMonitoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by YanWei on 2017/7/12.
 *
 * 远程监控
 *
 */
@Controller
@RequestMapping("/remoteMonitoringController")
public class RemoteMonitoringController {

	private RemoteMonitoringService remoteMonitoringService;

	@Autowired
	public void setRemoteMonitoringService(RemoteMonitoringService remoteMonitoringService) {
		this.remoteMonitoringService = remoteMonitoringService;
	}


	@RequestMapping(params = "goHref")
	public ModelAndView goHref(FFPRemoteMonitoring remoteMonitoring, String href, HttpServletRequest req, HttpServletResponse res) throws Exception{
		remoteMonitoring = remoteMonitoringService.get(FFPRemoteMonitoring.class, remoteMonitoring.getId());
		req.setAttribute("remoteMonitoring", remoteMonitoring);
		return new ModelAndView(href);
	}

	@RequestMapping(value = "deleteRemoteMonitorings")
	@ResponseBody
	public ReturnBody deleteRemoteMonitorings(FFPRemoteMonitoringModel remoteMonitoringModel) throws Exception{
		return remoteMonitoringService.deleteRemoteMonitorings(remoteMonitoringModel.getRemoteMonitorings());
	}
}
