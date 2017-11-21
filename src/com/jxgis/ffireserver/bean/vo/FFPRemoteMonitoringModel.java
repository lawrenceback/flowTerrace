package com.jxgis.ffireserver.bean.vo;

import com.jxgis.ffireserver.bean.FFPRemoteMonitoring;

import java.util.ArrayList;
import java.util.List;

/**
 * 远程监控实体类模型
 */
public class FFPRemoteMonitoringModel {
	private List<FFPRemoteMonitoring> remoteMonitorings = new ArrayList<>();

	public List<FFPRemoteMonitoring> getRemoteMonitorings() {
		return remoteMonitorings;
	}

	public void setRemoteMonitorings(List<FFPRemoteMonitoring> remoteMonitorings) {
		this.remoteMonitorings = remoteMonitorings;
	}
}
