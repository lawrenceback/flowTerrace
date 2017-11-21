package com.jxgis.ffireserver.web.service;

import com.jxgis.ffireserver.core.common.model.json.DataGrid;
import com.jxgis.ffireserver.core.util.ReturnBody;
import com.jxgis.ffireserver.web.bean.*;

import javax.naming.ldap.Control;
import java.util.List;

/**
 * Created by YanWei on 2017/7/19.
 *
 */
public interface TSControlService extends SystemService {

	//添加控件
	ReturnBody addControl(TSControl control) throws Exception;

	//删除控件
	ReturnBody deleteControl(TSControl control) throws Exception;

	//编辑控件
	ReturnBody editControl(TSControl control) throws Exception;

	//获得所有控件
	List<Control> getControlAll() throws Exception;

	//获得角色控件权限列表
	List<TSControl> getRoleControlPermissions(TSRole role, DataGrid dataGrid) throws Exception;

	//添加角色控件
	ReturnBody addRoleControl(TSControlRolePermissions controlRolePermissions) throws Exception;

	//移除角色控件
	ReturnBody removeRoleControl(TSControlRolePermissions controlRolePermissions) throws Exception;

	//获得菜单控件列表
	List<TSControl> getFunctionControl(TSFunction function, DataGrid dataGrid) throws Exception;

	//添加菜单控件
	ReturnBody addFunctionControl(TSControlFunction controlFunction) throws Exception;

	//移除菜单控件
	ReturnBody removeFunctionControl(TSControlFunction controlFunction) throws Exception;


}
