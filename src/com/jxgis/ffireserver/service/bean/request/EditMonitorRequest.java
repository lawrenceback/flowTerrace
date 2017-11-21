package com.jxgis.ffireserver.service.bean.request;

import javax.xml.bind.annotation.XmlRootElement;

import com.jxgis.ffireserver.bean.Monitor;

@XmlRootElement(name = "editMonitor")
public class EditMonitorRequest {

	private Monitor monitor;

	public Monitor getMonitor() {
		return monitor;
	}

	public void setMonitor(Monitor monitor) {
		this.monitor = monitor;
	}

}
