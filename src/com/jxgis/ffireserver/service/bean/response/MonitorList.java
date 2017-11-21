package com.jxgis.ffireserver.service.bean.response;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.jxgis.ffireserver.bean.Monitor;

@XmlRootElement(name = "monitorList")
public class MonitorList {

	private List<Monitor> monitor;

	public List<Monitor> getMonitor() {
		return monitor;
	}

	public void setMonitor(List<Monitor> monitor) {
		this.monitor = monitor;
	}

}
