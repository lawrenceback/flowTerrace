package com.jxgis.ffireserver.service;

import com.jxgis.ffireserver.bean.FFPOffice;
import com.jxgis.ffireserver.bean.FFPOfficeMedia;
import com.jxgis.ffireserver.core.common.model.json.DataGrid;
import com.jxgis.ffireserver.core.util.ReturnBody;
import com.jxgis.ffireserver.core.util.ReturnDataGrid;
import com.jxgis.ffireserver.web.service.SystemService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Created by YanWei on 2017/8/28.
 *
 * 森林防火办公室 业务层
 *
 *
 */
public interface OfficeService extends SystemService {

	//添加 森林防火办公室
	ReturnBody addOffice(FFPOffice office, Map<String, MultipartFile> mediaFiles) throws Exception;

	//删除 森林防火办公室
	ReturnBody deleteOffice(FFPOffice office) throws Exception;
	ReturnBody deleteOffices(List<FFPOffice> offices) throws Exception;

	//修改 森林防火办公室
	ReturnBody editOffice(FFPOffice office) throws Exception;

	//获得 森林防火办公室 列表
	ReturnDataGrid getOfficeList(FFPOffice office, DataGrid dataGrid, int fetchType) throws Exception;

	//添加 森林防火办公室 媒体文件
	ReturnBody addOfficeMedia(FFPOfficeMedia officeMedia, MultipartFile multipartFile) throws Exception;

	//删除 森林防火办公室 媒体文件
	ReturnBody deleteOfficeMedia(FFPOfficeMedia officeMedia) throws Exception;

}
