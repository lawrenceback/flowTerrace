package com.jxgis.ffireserver.web.service.impl;

import com.jxgis.ffireserver.web.bean.TSControl;
import com.jxgis.ffireserver.web.bean.TSFunction;
import com.jxgis.ffireserver.core.common.model.json.DataGrid;
import com.jxgis.ffireserver.core.util.MyBeanUtils;
import com.jxgis.ffireserver.core.util.ReturnBody;
import com.jxgis.ffireserver.core.util.StringUtil;
import com.jxgis.ffireserver.core.util.oConvertUtils;
import com.jxgis.ffireserver.web.bean.TSRole;
import com.jxgis.ffireserver.web.bean.TSRoleFunction;
import com.jxgis.ffireserver.web.service.TSFunctionService;
import com.jxgis.ffireserver.util.Glossary;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by YanWei on 2017/6/23.
 *
 * 菜单功能业务层实现类
 *
 * @author 言伟
 *
 * 自动注入时，变量名应该为“functionService”，这里要注意
 *
 */
@Service("functionService")
@Transactional
public class TSFunctionServiceImpl extends SystemServiceImpl implements TSFunctionService {


	@Override
	public Object getFunctionTree(TSFunction function, DataGrid dataGrid) throws Exception {

		function = this.get(TSFunction.class, oConvertUtils.getString(function.getId()));

		List<Map<String, Object>> lists = new ArrayList<>();
		if (StringUtil.isNotEmpty(function)) {

			//子菜单
			lists = this.findForJdbc("SELECT a.*,b.icon_class FROM t_s_function a LEFT JOIN t_s_icon b ON a.icon_id=b.id WHERE function_parent_id=?", function.getId());

		} else {

			//一级菜单
			lists = this.findForJdbc("SELECT a.*,b.icon_class FROM t_s_function a LEFT JOIN t_s_icon b ON a.icon_id=b.id WHERE function_level=0");

		}

		//查询菜单下是否还有菜单
		for (Map<String, Object> mp: lists) {
			long count = this.getCountForJdbcParam("SELECT COUNT(*) FROM t_s_function WHERE function_parent_id=?", new Object[]{mp.get("id")});

			if (count > 0) {
				mp.put("state", "closed");
			} else {
				mp.put("state", "open");
			}
		}

		return lists;
	}
	@Override
	public Object getFunctionTree(TSFunction function, TSControl control, DataGrid dataGrid) throws Exception {

		function = this.get(TSFunction.class, oConvertUtils.getString(function.getId()));

		List<Map<String, Object>> lists = new ArrayList<>();
		if (StringUtil.isNotEmpty(function)) {

			//子菜单
			lists = this.findForJdbc("SELECT a.*,b.icon_class FROM t_s_function a LEFT JOIN t_s_icon b ON a.icon_id=b.id WHERE function_parent_id=?", function.getId());

		} else {

			//一级菜单
			lists = this.findForJdbc("SELECT a.*,b.icon_class FROM t_s_function a LEFT JOIN t_s_icon b ON a.icon_id=b.id WHERE function_level=0");

		}

		StringBuilder hql = new StringBuilder("select distinct c from TSControl c,TSControlFunction cf ").append("where cf.control.id=c.id and cf.function.id=?");

		//查询菜单下是否还有菜单
		for (Map<String, Object> mp: lists) {
			long count = this.getCountForJdbcParam("SELECT COUNT(*) FROM t_s_function WHERE function_parent_id=?", new Object[]{mp.get("id")});

			mp.put("controls", findHql(hql.toString(), mp.get("id")));
			if (count > 0) {
				mp.put("state", "closed");
			} else {
				mp.put("state", "open");
			}
		}

		return lists;
	}

	@Override
	public Object getFunctionTree(TSFunction function, TSRole role, DataGrid dataGrid) throws Exception {

		function = this.get(TSFunction.class, oConvertUtils.getString(function.getId()));

		List<Map<String, Object>> lists = new ArrayList<>();
		if (StringUtil.isNotEmpty(function)) {

			//子菜单
			lists = this.findForJdbc("SELECT a.*,b.icon_class FROM t_s_function a LEFT JOIN t_s_icon b ON a.icon_id=b.id WHERE function_parent_id=?", function.getId());

		} else {

			//一级菜单
			lists = this.findForJdbc("SELECT a.*,b.icon_class FROM t_s_function a LEFT JOIN t_s_icon b ON a.icon_id=b.id WHERE function_level=0");

		}

		StringBuilder hql = new StringBuilder("select distinct c from TSControl c,TSControlFunction cf ").append("where cf.control.id=c.id and cf.function.id=?");
		StringBuilder hql_role_function = new StringBuilder("select distinct c from TSControl c,TSControlRolePermissions crp ").append("where crp.control=c.id and crp.role.id=? ");
		List<TSControl> controls_role_function = findHql(hql_role_function.toString(),role.getId());
		//查询菜单下是否还有菜单
		for (Map<String, Object> mp: lists) {
			long count = this.getCountForJdbcParam("SELECT COUNT(*) FROM t_s_function WHERE function_parent_id=?", new Object[]{mp.get("id")});



			List<TSControl> controls = findHql(hql.toString(), mp.get("id"));
			for (TSControl control : controls){
				for (TSControl control_role_function: controls_role_function){
					if (control.getId().equals(control_role_function.getId())){
						control.setChecked(1);
					}
				}
			}
			mp.put("controls", controls);

			if (count > 0) {
				mp.put("state", "closed");
			} else {
				mp.put("state", "open");
			}
		}

		return lists;
	}

	@Override
	public ReturnBody addOrUpdateFunction(TSFunction function) throws Exception {

		ReturnBody body = new ReturnBody();

		if (StringUtil.isEmpty(function.getFunctionName())){
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg("Parameter error [functionName], must not be empty.");
			return body;
		}
		if (StringUtil.isNotEmpty(function.getIcon()) && StringUtil.isEmpty(oConvertUtils.getString(function.getIcon().getId()))){
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg("Parameter error [iconId], must not be empty.");
			return body;
		}
		if (StringUtil.isNotEmpty(function.getFunctionParent())){
			function.setFunctionLevel(function.getFunctionLevel()+1);
		}
		TSFunction function_db = get(TSFunction.class, oConvertUtils.getString(function.getId()));
		try {
			if (StringUtil.isNotEmpty(function_db)){
				MyBeanUtils.copyBeanNotNull2Bean(function, function_db);
				if ( !StringUtil.isNotEmpty(function.getFunctionParent())){
					function_db.setFunctionParent(null);
					function_db.setFunctionLevel(0);
				} else {
					function_db.setFunctionLevel(function_db.getFunctionParent().getFunctionLevel()+1);
				}

				this.updateEntitie(function_db);
			} else {
				this.save(function);
			}
		} catch (HibernateException e){
			e.printStackTrace();
			body.setStatus(Glossary.Return_State_Exception_Operation);
			body.setMsg("operation failed.");
			return body;
		}
		return body;
	}

	@Override
	public ReturnBody updFunction(TSFunction function) throws Exception {
		ReturnBody body = new ReturnBody();
		TSFunction function_db = this.get(TSFunction.class, function.getId());
		if (StringUtil.isNotEmpty(function_db)){

			if (function.getFunctionLevel() == Glossary.Filter_Int) {
				function.setFunctionLevel(function_db.getFunctionLevel());
			}

			MyBeanUtils.copyBeanNotNull2Bean(function, function_db);
			try {
				this.updateEntitie(function_db);
			} catch (HibernateException e) {
				e.printStackTrace();
				body.setStatus(Glossary.Return_State_Exception_Operation);
				body.setMsg("Update failed.");
				return body;
			}
			return body;
		}
		body.setStatus(Glossary.Return_State_Data_Not_Exist);
		body.setMsg("Data does not exist.");
		return body;
	}

	@Override
	public ReturnBody delFunction(TSFunction function, int type) throws Exception {
		ReturnBody body = new ReturnBody();
		function = this.get(TSFunction.class, function.getId());
		if (StringUtil.isNotEmpty(function)){
			try {
				List<TSRoleFunction> roleFunctions = findByProperty(TSRoleFunction.class, "function.id", function.getId());
				switch (type){
					case 1:
						deleteAllEntitie(roleFunctions);
						delete(function);
						break;
					default:
						if (roleFunctions.size()>0){
							body.setStatus(Glossary.Return_State_Parameter_Occupy);
							body.setMsg("Data Line Occupied.");
							return body;
						}
						this.delete(function);
						break;
				}
			}catch (HibernateException e){
				e.printStackTrace();
				body.setStatus(Glossary.Return_State_Exception_Operation);
				body.setMsg("Update failed.");
				return body;
			}
			return body;
		}
		body.setStatus(Glossary.Return_State_Data_Not_Exist);
		body.setMsg("Data does not exist.");
		return body;
	}
}
