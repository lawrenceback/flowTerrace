package com.jxgis.ffireserver.dao;

import java.util.List;

import com.jxgis.ffireserver.bean.Monitor;

public interface MonitorDao {
	
	List<Monitor> getMonitors();

	boolean insert(Monitor monitor);
	
	boolean update(Monitor monitor);
	
	void delete(int id);
	
	void delete(String id);
	
}
