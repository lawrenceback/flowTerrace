package com.jxgis.ffireserver.web.service;

import com.jxgis.ffireserver.core.common.model.json.DataGrid;
import com.jxgis.ffireserver.core.util.ReturnBody;
import com.jxgis.ffireserver.core.util.ReturnDataGrid;
import com.jxgis.ffireserver.web.bean.TSUser;
import com.jxgis.ffireserver.web.service.SystemService;

/**
 * Created by YanWei on 2017/7/21.
 *
 * 
 */
public interface FireUserService extends SystemService{

	//退出登陆
	ReturnBody logout(TSUser user);

	//获得在线用户
	ReturnDataGrid getOnlineUserList(TSUser user, DataGrid dataGrid, int fetchType);

}
