package com.jxgis.ffireserver.service;

import com.jxgis.ffireserver.bean.FFPSatelliteGroundStation;
import com.jxgis.ffireserver.bean.FFPSatelliteGroundStationMedia;
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
 * 卫星地面站 业务层
 *
 */
public interface SatelliteGroundStationService extends SystemService{

	//添加 卫星地面站
	ReturnBody addSatelliteGroundStation(FFPSatelliteGroundStation station, Map<String, MultipartFile> mediaFiles) throws Exception;

	//删除 卫星地面站
	ReturnBody deleteSatelliteGroundStation(FFPSatelliteGroundStation station) throws Exception;
	ReturnBody deleteSatelliteGroundStations(List<FFPSatelliteGroundStation> stations) throws Exception;

	//修改 卫星地面站
	ReturnBody editSatelliteGroundStation(FFPSatelliteGroundStation station) throws Exception;

	//获得 卫星地面站 列表
	ReturnDataGrid getSatelliteGroundStationList(FFPSatelliteGroundStation station, DataGrid dataGrid, int fetchTypt) throws Exception;

	//添加 卫星地面站媒体文件
	ReturnBody addSatelliteGroundStationMedia(FFPSatelliteGroundStationMedia stationMedia, MultipartFile mediaFile) throws Exception;

	//删除 卫星地面站媒体文件
	ReturnBody deleteSatelliteGroundStationMedia(FFPSatelliteGroundStationMedia stationMedia) throws Exception;
}
