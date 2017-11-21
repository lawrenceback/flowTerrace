package com.jxgis.ffireserver.service;

import com.jxgis.ffireserver.bean.FFPFireSituation;
import com.jxgis.ffireserver.bean.FFPFireSituationMedia;
import com.jxgis.ffireserver.core.common.model.json.DataGrid;
import com.jxgis.ffireserver.core.util.ReturnBody;
import com.jxgis.ffireserver.core.util.ReturnDataGrid;
import com.jxgis.ffireserver.web.service.SystemService;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.Map;

/**
 * Created by YanWei on 2017/8/17.
 *
 * 火情调度业务层
 *
 */
public interface FireSituationService extends SystemService {

	//添加火情调度信息
	ReturnBody addFireSituation(FFPFireSituation fireSituation, Map<String, MultipartFile> mediaFiles) throws Exception;

	//删除火情调度
	ReturnBody deleteFireSituation(FFPFireSituation fireSituation) throws Exception;

	//修改火情调度
	ReturnBody editFireSituation(FFPFireSituation fireSituation) throws Exception;

	//获得火情调度列表
	ReturnDataGrid getFireSituationList(FFPFireSituation facilities, DataGrid dataGrid, Date time_start,Date time_end) throws Exception;

	//添加火情调度媒体文件
	ReturnBody addFireSituationMedia(FFPFireSituationMedia beltPointMedia, MultipartFile mediaFile) throws Exception;

	//删除火情调度媒体文件
	ReturnBody deleteFireSituationMedia(FFPFireSituationMedia beltPointMedia) throws Exception;
}
