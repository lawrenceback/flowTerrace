package com.jxgis.ffireserver.service;

import com.jxgis.ffireserver.bean.FFPWarehouse;
import com.jxgis.ffireserver.bean.FFPWarehouseMedia;
import com.jxgis.ffireserver.core.common.model.json.DataGrid;
import com.jxgis.ffireserver.core.util.ReturnBody;
import com.jxgis.ffireserver.core.util.ReturnDataGrid;
import com.jxgis.ffireserver.web.service.SystemService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Created by YanWei on 2017/8/30.
 *
 * 森林防火物资储备库 业务层
 * 
 */
public interface WarehouseService extends SystemService{

	//添加 森林防火物资储备库
	ReturnBody addSWarehouse(FFPWarehouse warehouse, Map<String, MultipartFile> mediaFiles) throws Exception;

	//删除 森林防火物资储备库
	ReturnBody deleteSWarehouse(FFPWarehouse warehouse) throws Exception;
	ReturnBody deleteSWarehouses(List<FFPWarehouse> warehouses) throws Exception;

	//修改 森林防火物资储备库
	ReturnBody editSWarehouse(FFPWarehouse warehouse) throws Exception;

	//获得 森林防火物资储备库 列表
	ReturnDataGrid getSWarehouseList(FFPWarehouse warehouse, DataGrid dataGrid, int fetchTypt) throws Exception;

	//添加 森林防火物资储备库媒体文件
	ReturnBody addSWarehouseMedia(FFPWarehouseMedia warehouseMedia, MultipartFile mediaFile) throws Exception;

	//删除 森林防火物资储备库媒体文件
	ReturnBody deleteSWarehouseMedia(FFPWarehouseMedia warehouseMedia) throws Exception;
}
