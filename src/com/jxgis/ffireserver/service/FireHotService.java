package com.jxgis.ffireserver.service;

import com.jxgis.ffireserver.bean.FFPFireHot;
import com.jxgis.ffireserver.bean.FFPFireHotMedia;
import com.jxgis.ffireserver.core.common.model.json.DataGrid;
import com.jxgis.ffireserver.core.util.ReturnBody;
import com.jxgis.ffireserver.core.util.ReturnDataGrid;
import com.jxgis.ffireserver.web.service.SystemService;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.Map;

/**
 * Created by YanWei on 2017/6/26.
 *
 * 火灾热点业务层
 *
 */
public interface FireHotService extends SystemService {

	//添加热点
	ReturnBody addHot(FFPFireHot hot, Map<String, MultipartFile> mediaFiles) throws Exception;

	//删除热点
	ReturnBody deleteHot(FFPFireHot hot) throws Exception;

	//修改热点
	ReturnBody editHot(FFPFireHot hot) throws Exception;

	//获得热点列表
	ReturnDataGrid getHotList(FFPFireHot hot, DataGrid dataGrid, int fetchType, Date reportTime_start,Date reportTime_end) throws Exception;

	//获得火灾热点详情
	ReturnBody getHotDetail(FFPFireHot hot) throws Exception;

	//添加 火灾热点 媒体文件
	ReturnBody addHotMedia(FFPFireHotMedia hotMedia, MultipartFile mediaFile) throws Exception;

	//删除 火灾热点 媒体文件
	ReturnBody deleteHotMedia(FFPFireHotMedia hotMedia) throws Exception;
}
