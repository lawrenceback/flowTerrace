package com.jxgis.ffireserver.core.extend.datasource;

import java.util.Map;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.jdbc.datasource.lookup.DataSourceLookup;

/**
 * Created by Administrator on 2017/6/6.
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
	/*
	 * 该方法必须要重写  方法是为了根据数据库标示符取得当前的数据库
	 */

	protected Object determineCurrentLookupKey() {
		DataSourceType dataSourceType= DataSourceContextHolder.getDataSourceType();
		return dataSourceType;
	}


	public void setDataSourceLookup(DataSourceLookup dataSourceLookup) {
		super.setDataSourceLookup(dataSourceLookup);
	}


	public void setDefaultTargetDataSource(Object defaultTargetDataSource) {
		super.setDefaultTargetDataSource(defaultTargetDataSource);
	}


	public void setTargetDataSources(Map targetDataSources) {
		super.setTargetDataSources(targetDataSources);
	}

	public java.util.logging.Logger getParentLogger() {
		return null;
	}
}
