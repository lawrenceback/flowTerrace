package com.jxgis.ffireserver.web.service;

import com.jxgis.ffireserver.core.common.model.json.DataGrid;
import com.jxgis.ffireserver.core.util.ReturnBody;
import com.jxgis.ffireserver.core.util.ReturnDataGrid;
import com.jxgis.ffireserver.web.bean.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by YanWei on 2017/6/15.
 *
 * 用户管理业务层
 *
 * @author 言伟
 *
 */
public interface TSUserService extends SystemService {
	/***
	 * 通过pac获得用户列表
	 */
	ReturnDataGrid pacByUser(TSUser user, DataGrid dataGrid, int fetchType) throws Exception;
	ReturnDataGrid pacByUser(TSUser user, DataGrid dataGrid, int fetchType, HttpServletRequest request) throws Exception;

	//获得用户信息
	ReturnBody getUser(TSUser user, HttpServletRequest request) throws Exception;

	/***
	 * 添加用户
	 */
	ReturnBody addUser(TSUser user) throws Exception;
	ReturnBody addUser(TSUser user, MultipartFile headPortrait, List<TSRole> roles, TSDepart depart) throws Exception;

	/***
	 * 删除用户
	 */
	ReturnBody delUser(TSUser user) throws Exception;
	ReturnBody delUsers(List<TSUser> users) throws Exception;

	//设置用户角色
	ReturnBody setUserRole(TSUser user, List<TSRoleUser> roleUsers) throws Exception;

	//修改用户
	ReturnBody editUser(TSUser user, MultipartFile headPortrait) throws Exception;
	ReturnBody editUser(TSUser user, MultipartFile headPortrait, TSDepartUser departUser, String password) throws Exception;
	ReturnBody editUser(List<TSRole> roles, TSUser user, MultipartFile headPortrait, TSDepartUser departUser, String password) throws Exception;

	//修改用户密码
	ReturnBody updateUserPassword(int id, String password, String passwordNew) throws Exception;

	//设置用户部门
	ReturnBody setUserDepart(TSUser user, List<TSDepartUser> departUsers) throws Exception;

}
