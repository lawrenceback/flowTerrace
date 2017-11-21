package com.jxgis.ffireserver.service;

import com.jxgis.ffireserver.bean.FFPRemoteMonitoring;
import com.jxgis.ffireserver.bean.FFPRemoteMonitoringMedia;
import com.jxgis.ffireserver.core.common.model.json.DataGrid;
import com.jxgis.ffireserver.core.util.ReturnBody;
import com.jxgis.ffireserver.core.util.ReturnDataGrid;
import com.jxgis.ffireserver.web.service.SystemService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Created by YanWei on 2017/6/26.
 *
 * 远程热点业务层
 *
 */
public interface RemoteMonitoringService extends SystemService {

	//添加远程监控点
	ReturnBody addRemoteMonitoring(FFPRemoteMonitoring remoteMonitoring, Map<String, MultipartFile> mediaFiles) throws Exception;

	//删除远程监控点
	ReturnBody deleteRemoteMonitoring(FFPRemoteMonitoring remoteMonitoring) throws Exception;
	ReturnBody deleteRemoteMonitorings(List<FFPRemoteMonitoring> remoteMonitorings) throws Exception;

	//修改远程监控点
	ReturnBody editRemoteMonitoring(FFPRemoteMonitoring remoteMonitoring) throws Exception;

	//获得远程监控点列表
	ReturnDataGrid getRemoteMonitoringList(FFPRemoteMonitoring remoteMonitoring, DataGrid dataGrid, int fetchTypt) throws Exception;

	//添加远程监控点媒体文件
	ReturnBody addRemoteMonitoringMedia(FFPRemoteMonitoringMedia monitoringMedia, MultipartFile mediaFile) throws Exception;

	//删除远程监控点媒体文件
	ReturnBody deleteRemoteMonitoringMedia(FFPRemoteMonitoringMedia monitoringMedia) throws Exception;
}
