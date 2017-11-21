package com.jxgis.ffireserver.service;

import com.jxgis.ffireserver.bean.FFPForestBeltPoint;
import com.jxgis.ffireserver.bean.FFPForestBeltPointMedia;
import com.jxgis.ffireserver.core.common.model.json.DataGrid;
import com.jxgis.ffireserver.core.util.ReturnBody;
import com.jxgis.ffireserver.core.util.ReturnDataGrid;
import com.jxgis.ffireserver.web.service.SystemService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Created by YanWei on 2017/8/2.
 *
 * 防火林带业务层
 *
 */
public interface ForestBeltPointService extends SystemService {

	//添加防火林带
	ReturnBody addBeltPoint(FFPForestBeltPoint beltPoint, Map<String, MultipartFile> mediaFiles) throws Exception;

	//删除防火林带
	ReturnBody deleteBeltPoint(FFPForestBeltPoint beltPoint) throws Exception;
	ReturnBody deleteBeltPoints(List<FFPForestBeltPoint> beltPoints) throws Exception;

	//编辑防火林带
	ReturnBody editBeltPoint(FFPForestBeltPoint beltPoint) throws Exception;

	//获得防火林带列表
	ReturnDataGrid getBeltPointList(FFPForestBeltPoint beltPoint, DataGrid dataGrid, int fetchType) throws Exception;

	//添加防火林带媒体文件
	ReturnBody addBeltPointMedia(FFPForestBeltPointMedia beltPointMedia, MultipartFile mediaFile) throws Exception;

	//删除防火林带媒体文件
	ReturnBody deleteBeltPointMedia(FFPForestBeltPointMedia beltPointMedia) throws Exception;

}
