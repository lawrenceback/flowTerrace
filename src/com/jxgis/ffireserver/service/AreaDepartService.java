package com.jxgis.ffireserver.service;

import com.jxgis.ffireserver.bean.FFPAreaDepart;
import com.jxgis.ffireserver.core.common.model.json.DataGrid;
import com.jxgis.ffireserver.core.util.ReturnBody;
import com.jxgis.ffireserver.core.util.ReturnDataGrid;
import com.jxgis.ffireserver.web.service.SystemService;

/**
 * Created by YanWei on 2017/9/6.
 *
 * 行政单位业务层 实现类
 *
 */
public interface AreaDepartService extends SystemService  {

	//添加行政单位
	ReturnBody addAreaDepart(FFPAreaDepart areaDepart) throws Exception;

	ReturnBody editAreaDepart(FFPAreaDepart areaDepart) throws Exception;

	//删除行政单位
	ReturnBody deleteAreaDepart(FFPAreaDepart areaDepart, int type) throws Exception;

	//获得行政单位列表
	ReturnDataGrid getAreaDepartList(FFPAreaDepart areaDepart, DataGrid dataGrid, int fetchType) throws Exception;
}
