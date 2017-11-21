package com.jxgis.ffireserver.service;

import com.jxgis.ffireserver.bean.FFPArea;
import com.jxgis.ffireserver.bean.FFPAreaBoundary;
import com.jxgis.ffireserver.core.common.model.json.DataGrid;
import com.jxgis.ffireserver.core.util.ReturnBody;
import com.jxgis.ffireserver.core.util.ReturnDataGrid;
import com.jxgis.ffireserver.web.service.SystemService;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by YanWei on 2017/6/14.
 *
 */
public interface AreaService extends SystemService {

	ReturnBody areas(String code) throws Exception;

	ReturnBody areasAll(String code) throws Exception;

	ReturnDataGrid getPac(String pac, DataGrid dataGrid, int fetchType, int fetchBoundary) throws Exception;

	//添加行政区
	ReturnBody addArea(FFPArea area) throws Exception;
	ReturnBody addAreaSimply(FFPAreaBoundary area) throws Exception;

	//修改行政区
	ReturnBody editArea(FFPAreaBoundary area) throws Exception;

	//删除行政区
	ReturnBody deleteArea(FFPAreaBoundary area) throws Exception;

	//获得行政区名称
	ReturnBody getArea(String code, int format_boundary_type) throws Exception;


	//PAC反查行政区
	ReturnBody peggingPac(String code, HttpServletRequest req) throws Exception;

}
