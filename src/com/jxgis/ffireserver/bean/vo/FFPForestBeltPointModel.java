package com.jxgis.ffireserver.bean.vo;

import com.jxgis.ffireserver.bean.FFPForestBeltPoint;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 防火林带实体模型
 */
public class FFPForestBeltPointModel {

	private List<FFPForestBeltPoint> beltPointList = new ArrayList<>();

	public List<FFPForestBeltPoint> getBeltPointList() {
		return beltPointList;
	}

	public void setBeltPointList(List<FFPForestBeltPoint> beltPointList) {
		this.beltPointList = beltPointList;
	}
}
