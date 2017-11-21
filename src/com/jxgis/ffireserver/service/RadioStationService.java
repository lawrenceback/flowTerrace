package com.jxgis.ffireserver.service;

import com.jxgis.ffireserver.bean.FFPRadioStation;
import com.jxgis.ffireserver.bean.FFPRadioStationMedia;
import com.jxgis.ffireserver.core.common.model.json.DataGrid;
import com.jxgis.ffireserver.core.util.ReturnBody;
import com.jxgis.ffireserver.core.util.ReturnDataGrid;
import com.jxgis.ffireserver.web.service.SystemService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Created by YanWei on 2017/7/31.
 *
 * 无线电台站业务层
 *
 */
public interface RadioStationService extends SystemService {

	//添加无线电台
	ReturnBody addRadioStation(FFPRadioStation station, Map<String, MultipartFile> mediaFiles) throws Exception;

	//删除无线电台
	ReturnBody deleteRadioStation(FFPRadioStation station) throws Exception;
	ReturnBody deleteRadioStations(List<FFPRadioStation> stations) throws Exception;

	//编辑无线电台
	ReturnBody editRadioStation(FFPRadioStation station) throws Exception;

	//获得无线电台列表
	ReturnDataGrid getRadioStationList(FFPRadioStation station, DataGrid dataGrid, int fetchType) throws Exception;

	//添加无线电台媒体文件
	ReturnBody addRadioStationMediaFile(FFPRadioStationMedia stationMedia, MultipartFile mediaFile) throws Exception;

	//删除无线电台媒体文件
	ReturnBody deleteRadioStationMediaFile(FFPRadioStationMedia stationMedia) throws Exception;
}
