package com.jxgis.ffireserver.service;

import com.jxgis.ffireserver.bean.FFPGridUserTable;
import com.jxgis.ffireserver.bean.FFPGridUserTablePhoto;
import com.jxgis.ffireserver.core.common.model.json.DataGrid;
import com.jxgis.ffireserver.core.util.ReturnBody;
import com.jxgis.ffireserver.core.util.ReturnDataGrid;
import com.jxgis.ffireserver.web.service.SystemService;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * Created by YanWei on 2017/9/14.
 *
 * 网格人员管理表 业务层
 *
 */
public interface GridUserTableService extends SystemService {

	//添加 风格人员管理
	ReturnBody addGridUserTable(FFPGridUserTable gridUserTable, Map<String, MultipartFile> mediaFiles) throws Exception;

	//删除 风格人员管理
	ReturnBody deleteGridUserTable(FFPGridUserTable gridUserTable) throws Exception;

	//修改 风格人员管理
	ReturnBody editGridUserTable(FFPGridUserTable gridUserTable) throws Exception;

	//查询 风格人员管理
	ReturnDataGrid getGridUserTableList(FFPGridUserTable gridUserTable, DataGrid dataGrid, int fetchType) throws Exception;

	//添加 风格人员管理 照片（单张）
	ReturnBody addBeltPointPhoto(FFPGridUserTablePhoto gridUserTablePhoto, MultipartFile mediaFile) throws Exception;

	//删除 风格人员管理 照片
	ReturnBody deleteBeltPointPhoto(FFPGridUserTablePhoto gridUserTablePhoto) throws Exception;
}
