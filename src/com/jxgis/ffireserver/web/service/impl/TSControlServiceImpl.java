package com.jxgis.ffireserver.web.service.impl;

import com.jxgis.ffireserver.core.common.model.json.DataGrid;
import com.jxgis.ffireserver.core.util.MyBeanUtils;
import com.jxgis.ffireserver.core.util.ReturnBody;
import com.jxgis.ffireserver.core.util.StringUtil;
import com.jxgis.ffireserver.core.util.oConvertUtils;
import com.jxgis.ffireserver.util.Glossary;
import com.jxgis.ffireserver.web.bean.*;
import com.jxgis.ffireserver.web.service.TSControlService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.ldap.Control;
import java.util.List;

/**
 * Created by YanWei on 2017/7/19.
 *
 */
@Service("controlService")
@Transactional
public class TSControlServiceImpl extends SystemServiceImpl implements TSControlService {

	@Override
	public ReturnBody addControl(TSControl control) throws Exception {
		ReturnBody body = new ReturnBody();
		if (StringUtil.isEmpty(control.getControlCode())) {
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg("parameter error [controlCode]");
			return body;
		}
		if (StringUtil.isEmpty(control.getControlName())){
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg("parameter error [controlName]");
			return body;
		}
		TSControl control_db = findUniqueByProperty(TSControl.class, "controlCode", control.getControlCode());
		if (StringUtil.isNotEmpty(control_db)){
			body.setStatus(Glossary.Return_State_Data_Exist);
			body.setMsg("parameter error [controlCode]");
			return body;
		}
		save(control);
		return body;
	}

	@Override
	public ReturnBody deleteControl(TSControl control) throws Exception {
		ReturnBody body = new ReturnBody();
		control = get(TSControl.class, oConvertUtils.getString(control.getId()));
		if (StringUtil.isNotEmpty(control)){
			List<TSControlFunction> controlFunctions = findByProperty(TSControlFunction.class, "control.id", control.getId());
			deleteAllEntitie(controlFunctions);
			delete(control);
			return body;
		}
		body.setStatus(Glossary.Return_State_Parameter_Null);
		body.setMsg("parameter error [id]");
		return body;
	}

	@Override
	public ReturnBody editControl(TSControl control) throws Exception {
		ReturnBody body = new ReturnBody();
		TSControl control_db = get(TSControl.class, oConvertUtils.getString(control.getId()));
		if (StringUtil.isNotEmpty(control_db)){
			if (control.getType() == Glossary.Filter_Int){
				control.setType(control_db.getType());
			}
			MyBeanUtils.copyBeanNotNull2Bean(control, control_db);
			updateEntitie(control_db);
			return body;
		}
		body.setStatus(Glossary.Return_State_Data_Not_Exist);
		body.setMsg("parameter error [id]");
		return body;
	}

	@Override
	public List<Control> getControlAll() throws Exception {
		return loadAll(Control.class);
	}

	@Override
	public List<TSControl> getRoleControlPermissions(TSRole role, DataGrid dataGrid) throws Exception {
		StringBuilder hql = new StringBuilder("select distinct c from TSControl c, TSControlRolePermissions crp ")
				.append("where c.id=crp.control.id and crp.role.id=?");
		return findHql(hql.toString(), role.getId());
	}

	@Override
	public ReturnBody addRoleControl(TSControlRolePermissions controlRolePermissions) throws Exception {
		ReturnBody body = new ReturnBody();
		if (StringUtil.isNotEmpty(controlRolePermissions.getControl()) && !StringUtil.isEmpty(controlRolePermissions.getControl().getId())){

			if (StringUtil.isNotEmpty(controlRolePermissions.getRole() ) && !StringUtil.isEmpty(controlRolePermissions.getRole().getId())){
				save(controlRolePermissions);
				return body;
			}
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg("parameter error [role.id]");
			return body;
		}
		body.setStatus(Glossary.Return_State_Parameter_Null);
		body.setMsg("parameter error [control.id]");
		return body;
	}

	@Override
	public ReturnBody removeRoleControl(TSControlRolePermissions controlRolePermissions) throws Exception {
		ReturnBody body = new ReturnBody();

		StringBuilder hql = new StringBuilder("select crp from TSControlRolePermissions crp ").append("where crp.control.id=? and crp.role.id=?");
		List<TSControlRolePermissions> rolePermissions = findHql(hql.toString(), controlRolePermissions.getControl().getId(), controlRolePermissions.getRole().getId());
		if (StringUtil.isNotEmpty(rolePermissions)){
			deleteAllEntitie(rolePermissions);
			if (rolePermissions.size() > 0){
				return body;
			}
		}
//		TSRole role = controlRolePermissions.getRole();
//		if (StringUtil.isNotEmpty(role)){
//			role = get(TSRole.class, oConvertUtils.getString(role.getId()));
//			if (StringUtil.isNotEmpty(role)){
//
//				TSControl control = controlRolePermissions.getControl();
//				if (StringUtil.isNotEmpty(control)){
//					control = get(TSControl.class, oConvertUtils.getString(controlRolePermissions));
//					if (StringUtil.isNotEmpty(control)){
//						hql = new StringBuilder("select crp from TSControlRolePermissions crp ").append("where crp.control.id=? and crp.role.id=?");
//					}
//				}
//
//			}
//
//		}
		body.setStatus(Glossary.Return_State_Parameter_Null);
		body.setMsg("parameter error [id]");
		return body;
	}

	@Override
	public List<TSControl> getFunctionControl(TSFunction function, DataGrid dataGrid) throws Exception {
		StringBuilder hql = new StringBuilder("select distinct c from TSControl c, TSControlFunction cf ")
				.append("where c.id=cf.control.id and cf.function.id=?");
		return findHql(hql.toString(), function.getId());
	}

	@Override
	public ReturnBody addFunctionControl(TSControlFunction controlFunction) throws Exception {
		ReturnBody body = new ReturnBody();
		if (StringUtil.isNotEmpty(controlFunction.getFunction()) && !StringUtil.isEmpty(controlFunction.getFunction().getId())){
			if (StringUtil.isNotEmpty(controlFunction.getControl()) && !StringUtil.isEmpty(controlFunction.getControl().getId())){
				save(controlFunction);
				return body;
			}
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg("parameter error [control.id]");
			return body;
		}
		body.setStatus(Glossary.Return_State_Parameter_Null);
		body.setMsg("parameter error [function.id]");
		return body;
	}

	@Override
	public ReturnBody removeFunctionControl(TSControlFunction controlFunction) throws Exception {
		ReturnBody body = new ReturnBody();
		controlFunction = get(TSControlFunction.class, oConvertUtils.getString(controlFunction.getId()));
		if (StringUtil.isNotEmpty(controlFunction)){
			delete(controlFunction);
			return body;
		}
		body.setStatus(Glossary.Return_State_Data_Not_Exist);
		body.setMsg("parameter error [id]");
		return body;
	}
}
