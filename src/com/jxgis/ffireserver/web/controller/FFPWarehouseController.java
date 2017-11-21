package com.jxgis.ffireserver.web.controller;

import com.jxgis.ffireserver.bean.vo.FFPWarehouseModel;
import com.jxgis.ffireserver.core.util.ReturnBody;
import com.jxgis.ffireserver.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/warehouseController")
public class FFPWarehouseController {
	private WarehouseService warehouseService;

	@Autowired
	public void setWarehouseService(WarehouseService warehouseService) {
		this.warehouseService = warehouseService;
	}

	@RequestMapping(value = "deleteSWarehouses")
	@ResponseBody
	public ReturnBody deleteSWarehouses(FFPWarehouseModel warehouseModel) throws Exception{
		return warehouseService.deleteSWarehouses(warehouseModel.getWarehouses());
	}
}
