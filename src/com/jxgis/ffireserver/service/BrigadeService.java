package com.jxgis.ffireserver.service;

import com.jxgis.ffireserver.bean.FFPHBrigade;
import com.jxgis.ffireserver.bean.FFPHBrigadeMedia;
import com.jxgis.ffireserver.core.common.model.json.DataGrid;
import com.jxgis.ffireserver.core.util.ReturnBody;
import com.jxgis.ffireserver.core.util.ReturnDataGrid;
import com.jxgis.ffireserver.web.service.SystemService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Created by YanWei on 2017/8/23.
 *
 * 半专业森林消防队 业务逻辑层
 *
 */
public interface BrigadeService extends SystemService {

	//添加 半专业森林消防队
	ReturnBody addBrigade(FFPHBrigade brigade, Map<String, MultipartFile> mediaFiles) throws Exception;

	//删除 半专业森林消防队
	ReturnBody deleteBrigade(FFPHBrigade brigade) throws Exception;
	ReturnBody deleteBrigades(List<FFPHBrigade> brigades) throws Exception;

	//编辑 半专业森林消防队
	ReturnBody editBrigade(FFPHBrigade brigade) throws Exception;

	//获得 半专业森林消防队 列表
	ReturnDataGrid getBrigadeList(FFPHBrigade brigade, DataGrid dataGrid, int fetchType) throws Exception;

	//添加 半专业森林消防队 媒体文件
	ReturnBody addBrigadeMedia(FFPHBrigadeMedia brigadeMedia, MultipartFile mediaFile) throws Exception;

	//删除 半专业森林消防队 媒体文件
	ReturnBody deleteBrigadeMedia(FFPHBrigadeMedia brigadeMedia) throws Exception;
}
