package com.jxgis.ffireserver.bean.vo;

import com.jxgis.ffireserver.bean.FFPWarehouse;

import java.util.ArrayList;
import java.util.List;

/**
 * 森林防火物资储备库 实体模型
 */
public class FFPWarehouseModel {
	private List<FFPWarehouse> warehouses = new ArrayList<>();

	public List<FFPWarehouse> getWarehouses() {
		return warehouses;
	}

	public void setWarehouses(List<FFPWarehouse> warehouses) {
		this.warehouses = warehouses;
	}
}
