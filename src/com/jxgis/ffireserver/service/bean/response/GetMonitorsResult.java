package com.jxgis.ffireserver.service.bean.response;

import javax.xml.bind.annotation.XmlRootElement;

import com.jxgis.ffireserver.bean.Result;

@XmlRootElement(name = "getMonitorsResult")
public class GetMonitorsResult {

	// 统一结果
	private Result result;

	private MonitorList monitorList;

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public MonitorList getMonitorList() {
		return monitorList;
	}

	public void setMonitorList(MonitorList monitorList) {
		this.monitorList = monitorList;
	}

}
