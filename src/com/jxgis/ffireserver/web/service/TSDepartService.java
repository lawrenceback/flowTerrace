package com.jxgis.ffireserver.web.service;

import com.jxgis.ffireserver.bean.FFPAreaDepart;
import com.jxgis.ffireserver.web.bean.TSDepart;
import com.jxgis.ffireserver.web.bean.TSDepartUser;
import com.jxgis.ffireserver.core.common.model.json.DataGrid;
import com.jxgis.ffireserver.core.util.ReturnBody;

import java.util.List;
import java.util.Map;

/**
 * Created by YanWei on 2017/7/3.
 *
 * 系统部门表
 *
 * @author 言伟
 */
public interface TSDepartService extends SystemService{

	//添加部门
	ReturnBody addOrUpdateDepart(TSDepart depart) throws Exception;

	//删除部门
	ReturnBody deleteDepart(TSDepart depart) throws Exception;

	//编辑部门
	ReturnBody editDepart(TSDepart depart) throws Exception;

	//查询部门
	List<Map<String, Object>> getDepartTree(TSDepart depart, DataGrid dataGrid, int fetchType) throws Exception;

	//通过行政区查询部门
	List<Map<String, Object>> getDepartPac(FFPAreaDepart areaDepart, DataGrid dataGrid, int fetchType) throws Exception;

	//获得org_code
	ReturnBody getDepartOrgCode(TSDepart depart) throws Exception;

	//获得父部门
	ReturnBody getDeppartParent(TSDepart depart) throws Exception;

	//查询部门用户
	Map<String, Object> getDeparUsers(TSDepartUser departUser, DataGrid dataGrid, int fetchType) throws Exception;
}
