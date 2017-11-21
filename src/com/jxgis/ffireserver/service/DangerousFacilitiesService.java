package com.jxgis.ffireserver.service;

import com.jxgis.ffireserver.bean.FFPDangerousFacilities;
import com.jxgis.ffireserver.bean.FFPDangerousFacilitiesMedia;
import com.jxgis.ffireserver.core.common.model.json.DataGrid;
import com.jxgis.ffireserver.core.util.ReturnBody;
import com.jxgis.ffireserver.core.util.ReturnDataGrid;
import com.jxgis.ffireserver.web.service.SystemService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Created by YanWei on 2017/8/4.
 *
 * 林区危险及重要性业务层
 *
 */
public interface DangerousFacilitiesService extends SystemService {

	//添加林区危险及重要性
	ReturnBody addDangerousFacilities(FFPDangerousFacilities facilities, Map<String, MultipartFile> mediaFiles) throws Exception;

	//删除林区危险及重要性
	ReturnBody deleteDangerousFacilities(FFPDangerousFacilities facilities) throws Exception;
	ReturnBody deleteDangerousFacilities(List<FFPDangerousFacilities> facilities) throws Exception;

	//编辑林区危险及重要性
	ReturnBody editDangerousFacilities(FFPDangerousFacilities facilities) throws Exception;

	//获得林区危险及重要性列表
	ReturnDataGrid getDangerousFacilitiesList(FFPDangerousFacilities facilities, DataGrid dataGrid, int fetchType) throws Exception;

	//添加林区危险及重要性媒体文件
	ReturnBody addDangerousFacilitiesMedia(FFPDangerousFacilitiesMedia facilitiesMedia, MultipartFile mediaFile) throws Exception;

	//删除林区危险及重要性媒体文件
	ReturnBody deleteDangerousFacilitiesMedia(FFPDangerousFacilitiesMedia facilitiesMedia) throws Exception;

}
