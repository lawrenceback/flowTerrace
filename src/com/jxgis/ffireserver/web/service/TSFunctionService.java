package com.jxgis.ffireserver.web.service;

import com.jxgis.ffireserver.web.bean.TSControl;
import com.jxgis.ffireserver.web.bean.TSFunction;
import com.jxgis.ffireserver.core.common.model.json.DataGrid;
import com.jxgis.ffireserver.core.util.ReturnBody;
import com.jxgis.ffireserver.web.bean.TSRole;

/**
 * Created by YanWei on 2017/6/23.
 *
 * 菜单功能业务层
 *
 * @author 言伟
 */
public interface TSFunctionService extends SystemService {

	//获得菜单列表 TreeGrid
	Object getFunctionTree(TSFunction function, DataGrid dataGrid) throws Exception;

	//获得菜单列表 TreeGrid
	Object getFunctionTree(TSFunction function, TSControl control, DataGrid dataGrid) throws Exception;

	//获得菜单列表 TreeGrid
	Object getFunctionTree(TSFunction function, TSRole role, DataGrid dataGrid) throws Exception;

	//添加菜单
	ReturnBody addOrUpdateFunction(TSFunction function) throws Exception;

	//修改菜单
	ReturnBody updFunction(TSFunction function) throws Exception;

	/**
	 * 删除菜单
	 *
	 * @param type 1_物理删除,0_普通删除
	 */
	ReturnBody delFunction(TSFunction function, int type) throws Exception;

}
