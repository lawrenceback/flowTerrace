package com.jxgis.ffireserver.web.service;

import com.jxgis.ffireserver.web.bean.TSRole;
import com.jxgis.ffireserver.web.bean.TSRoleFunction;
import com.jxgis.ffireserver.core.common.model.json.DataGrid;
import com.jxgis.ffireserver.core.util.ReturnBody;
import com.jxgis.ffireserver.core.util.ReturnDataGrid;

import java.util.List;

/**
 * Created by YanWei on 2017/6/28.
 *
 * 系统角色管理
 *
 * @author 言伟
 *
 */
public interface TSRoleService extends SystemService{

	//添加角色
	ReturnBody addOrUpdateRole(TSRole role) throws Exception;

	//删除角色
	ReturnBody deleteRole(TSRole role) throws Exception;

	//编辑角色
	ReturnBody editRole(TSRole role) throws Exception;

	//获得角色列表（DataGrid）
	ReturnDataGrid getRoleDataGrid(TSRole role, DataGrid dataGrid) throws Exception;


	//设置系统角色菜单
	ReturnBody setRoleFunction(TSRole role, List<TSRoleFunction> roleFunctions) throws Exception;

}
