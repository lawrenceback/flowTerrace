package com.jxgis.ffireserver.bean.vo;

import com.jxgis.ffireserver.bean.FFPObservatory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YanWei on 2017/10/1.
 *
 * 瞭望台实体模型
 *
 */
public class FFPObservatoryModel {

	List<FFPObservatory> observatories = new ArrayList<>();

	public List<FFPObservatory> getObservatories() {
		return observatories;
	}

	public void setObservatories(List<FFPObservatory> observatories) {
		this.observatories = observatories;
	}
}
