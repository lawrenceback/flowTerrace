package com.jxgis.ffireserver.service;

import com.jxgis.ffireserver.bean.FFPPBrigade;
import com.jxgis.ffireserver.bean.FFPPBrigadeMedia;
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
 * 专业森林消防队 业务层
 *
 */
public interface PBrigadeService extends SystemService {

	//添加 专业森林消防队
	ReturnBody addPBrigade(FFPPBrigade brigade, Map<String, MultipartFile> mediaFiles) throws Exception;

	//删除 专业森林消防队
	ReturnBody deletePBrigade(FFPPBrigade brigade) throws Exception;
	ReturnBody deletePBrigades(List<FFPPBrigade> brigade) throws Exception;

	//编辑 专业森林消防队
	ReturnBody editPBrigade(FFPPBrigade brigade) throws Exception;

	//获得 专业森林消防队 列表
	ReturnDataGrid getPBrigadeList(FFPPBrigade brigade, DataGrid dataGrid, int fetchType) throws Exception;

	//添加 专业森林消防队 媒体文件
	ReturnBody addPBrigadeMedia(FFPPBrigadeMedia brigadeMedia, MultipartFile mediaFile) throws Exception;

	//删除 专业森林消防队 媒体文件
	ReturnBody deletePBrigadeMedia(FFPPBrigadeMedia brigadeMedia) throws Exception;
	
}
