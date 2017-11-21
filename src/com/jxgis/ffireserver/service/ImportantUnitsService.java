package com.jxgis.ffireserver.service;

import com.jxgis.ffireserver.bean.FFPImportantUnits;
import com.jxgis.ffireserver.bean.FFPImportantUnitsMedia;
import com.jxgis.ffireserver.core.common.model.json.DataGrid;
import com.jxgis.ffireserver.core.util.ReturnBody;
import com.jxgis.ffireserver.core.util.ReturnDataGrid;
import com.jxgis.ffireserver.web.service.SystemService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Created by YanWei on 2017/8/24.
 *
 * 重点防火单位 业务层
 *
 */
public interface ImportantUnitsService extends SystemService {

	//添加 重点防火单位
	ReturnBody addImportant(FFPImportantUnits importantUnits, Map<String, MultipartFile> mediaFiles) throws Exception;

	//删除 重点防火单位
	ReturnBody deleteImportant(FFPImportantUnits importantUnits) throws Exception;
	ReturnBody deleteImportants(List<FFPImportantUnits> importantUnits) throws Exception;

	//编辑 重点防火单位
	ReturnBody editImportant(FFPImportantUnits importantUnits) throws Exception;

	//获得 重点防火单位 列表
	ReturnDataGrid getImportantList(FFPImportantUnits importantUnits, DataGrid dataGrid, int fetchType) throws Exception;

	//添加 重点防火单位 媒体文件
	ReturnBody addImportantMedia(FFPImportantUnitsMedia importantUnitsMedia, MultipartFile mediaFile) throws Exception;

	//删除 重点防火单位 媒体文件
	ReturnBody deleteImportantMedia(FFPImportantUnitsMedia importantUnitsMedia) throws Exception;
}
