package com.jxgis.ffireserver.service;

import com.jxgis.ffireserver.bean.FFPPlanewaterpoint;
import com.jxgis.ffireserver.bean.FFPPlanewaterpointMedia;
import com.jxgis.ffireserver.core.common.model.json.DataGrid;
import com.jxgis.ffireserver.core.util.ReturnBody;
import com.jxgis.ffireserver.core.util.ReturnDataGrid;
import com.jxgis.ffireserver.web.service.SystemService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Created by YanWei on 2017/8/29.
 *
 * 飞机吊桶取水点 业务层
 *
 */
public interface PlanewaterpointService extends SystemService{
	
	//添加 飞机吊桶取水点
	ReturnBody addplanewaterpoint(FFPPlanewaterpoint planewaterpoint, Map<String, MultipartFile> mediaFiles)throws Exception;

	//删除 飞机吊桶取水点
	ReturnBody deleteplanewaterpoint(FFPPlanewaterpoint planewaterpoint) throws Exception;
	ReturnBody deleteplanewaterpoints(List<FFPPlanewaterpoint> planewaterpoints) throws Exception;

	//修改 飞机吊桶取水点
	ReturnBody editplanewaterpoint(FFPPlanewaterpoint planewaterpoint) throws Exception;

	//获得 飞机吊桶取水点 列表
	ReturnDataGrid getplanewaterpointList(FFPPlanewaterpoint planewaterpoint, DataGrid dataGrid, int fetchType) throws Exception;

	//添加 飞机吊桶取水点 媒体文件
	ReturnBody addplanewaterpointMedia(FFPPlanewaterpointMedia planewaterpointMedia, MultipartFile mediaFile) throws Exception;

	//删除 飞机吊桶取水点 媒体文件
	ReturnBody deleteplanewaterpointMedia(FFPPlanewaterpointMedia planewaterpointMedia) throws Exception;
}
