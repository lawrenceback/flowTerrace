package com.jxgis.ffireserver.web.service;

import com.jxgis.ffireserver.web.bean.TSIcon;
import com.jxgis.ffireserver.core.common.model.json.DataGrid;
import com.jxgis.ffireserver.core.util.ReturnBody;
import com.jxgis.ffireserver.core.util.ReturnDataGrid;

/**
 * Created by YanWei on 2017/6/23.
 *
 * Icon业务层
 *
 * @author 言伟
 *
 */
public interface TSIconService extends SystemService {

	//添加图标
	ReturnBody addIcon(TSIcon icon) throws Exception;

	//删除图标
	ReturnBody delIcon(TSIcon icon) throws Exception;

	//修改图标
	ReturnBody updIcon(TSIcon icon) throws Exception;

	//获得图标列表
	ReturnDataGrid iconLists(TSIcon icon, DataGrid dataGrid) throws Exception;
}
