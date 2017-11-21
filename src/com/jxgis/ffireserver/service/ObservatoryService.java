package com.jxgis.ffireserver.service;

import com.jxgis.ffireserver.bean.FFPObservatory;
import com.jxgis.ffireserver.bean.FFPObservatoryMedia;
import com.jxgis.ffireserver.core.common.model.json.DataGrid;
import com.jxgis.ffireserver.core.util.ReturnBody;
import com.jxgis.ffireserver.core.util.ReturnDataGrid;
import com.jxgis.ffireserver.web.service.SystemService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Created by YanWei on 2017/7/27.
 *
 */
public interface ObservatoryService extends SystemService {

	//添加瞭望台
	ReturnBody addObservatory(FFPObservatory observatory)throws Exception;

	//添加瞭望台
	ReturnBody addObservatory(FFPObservatory observatory, Map<String, MultipartFile> mediaFiles)throws Exception;

	//删除瞭望台
	ReturnBody deleteObservatory(FFPObservatory observatory) throws Exception;
	ReturnBody deleteObservatorys(List<FFPObservatory> observatories) throws Exception;

	//修改瞭望台
	ReturnBody editObservatory(FFPObservatory observatory) throws Exception;

	//获得瞭望台列表
	ReturnDataGrid getObservatoryList(FFPObservatory observatory, DataGrid dataGrid, int fetchType) throws Exception;

	//添加瞭望台媒体文件
	ReturnBody addObservatoryMedia(FFPObservatoryMedia observatoryMedia, MultipartFile mediaFile) throws Exception;

	//删除瞭望台媒体文件
	ReturnBody deleteObservatoryMedia(FFPObservatoryMedia observatoryMedia) throws Exception;

}
