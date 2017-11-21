package com.jxgis.ffireserver.service.bean.request;

import javax.xml.bind.annotation.XmlRootElement;

import com.jxgis.ffireserver.bean.Monitor;

@XmlRootElement(name = "addMonitor")
public class AddMonitorRequest {

	private Monitor monitor;

	public Monitor getMonitor() {
		return monitor;
	}

	public void setMonitor(Monitor monitor) {
		this.monitor = monitor;
	}

}
