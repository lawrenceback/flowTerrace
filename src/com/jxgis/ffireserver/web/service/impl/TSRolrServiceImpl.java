package com.jxgis.ffireserver.web.service.impl;

import com.jxgis.ffireserver.core.common.model.json.DataGrid;
import com.jxgis.ffireserver.core.util.*;
import com.jxgis.ffireserver.util.Glossary;
import com.jxgis.ffireserver.web.bean.TSControlRolePermissions;
import com.jxgis.ffireserver.web.bean.TSRole;
import com.jxgis.ffireserver.web.bean.TSRoleFunction;
import com.jxgis.ffireserver.web.bean.TSRoleUser;
import com.jxgis.ffireserver.web.service.TSRoleService;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by YanWei on 2017/6/28.
 *
 * 系统角色业务层
 *
 * @author 言伟
 */
@Service("roleService")
@Transactional
public class TSRolrServiceImpl extends SystemServiceImpl implements TSRoleService {

	@Override
	public ReturnBody addOrUpdateRole(TSRole role) throws Exception {
		ReturnBody body = new ReturnBody();

		if (StringUtil.isEmpty(role.getRoleName())){
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg("Parameter error [roleName], must not be empty.");
			return body;
		}

		try{
			TSRole role_db = get(TSRole.class, oConvertUtils.getString(role.getId()));

			if (StringUtil.isNotEmpty(role_db)){
				//这里要注意，不允许用户修改【角色编码】
				role.setRoleCode(role_db.getRoleCode());
				MyBeanUtils.copyBeanNotNull2Bean(role, role_db);
				updateEntitie(role_db);
			} else {

				TSRole role_code_db = findUniqueByProperty(TSRole.class, "roleCode", role.getRoleCode());
				if (StringUtil.isNotEmpty(role_code_db)){
					body.setStatus(Glossary.Return_State_Data_Exist);
					body.setMsg("Parameter repetition.");
					return body;
				}

				if (StringUtil.isEmpty(role.getRoleCode())){
					body.setStatus(Glossary.Return_State_Parameter_Null);
					body.setMsg("Parameter error [roleCode], must not be empty.");
					return body;
				}

				save(role);
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
	public ReturnBody deleteRole(TSRole role) throws Exception {
		ReturnBody body = new ReturnBody();
		role = get(TSRole.class, role.getId());
		if (StringUtil.isNotEmpty(role)){
			try{
				List<TSRoleUser> roleUsers = findByProperty(TSRoleUser.class, "role.id", role.getId());
				List<TSRoleFunction> roleFunctions = findByProperty(TSRoleFunction.class, "role.id", role.getId());
				List<TSControlRolePermissions> rolePermissions = findByProperty(TSControlRolePermissions.class, "role.id", role.getId());
				if (roleUsers.size() > 0 || roleFunctions.size() > 0 || rolePermissions.size() > 0){
					body.setStatus(Glossary.Return_State_Data_Linked);
					body.setMsg("The data has been linked.");
					return body;
				}
				delete(role);
			} catch (HibernateException e){
				e.printStackTrace();
				body.setStatus(Glossary.Return_State_Exception_Operation);
				body.setMsg("operation failed.");
				return body;
			}
			return body;
		}
		body.setStatus(Glossary.Return_State_Data_Not_Exist);
		body.setMsg("Data does not exist.");
		return body;
	}

	@Override
	public ReturnBody editRole(TSRole role) throws Exception {
		ReturnBody body = new ReturnBody();

		if (!StringUtil.isNotEmpty(role)){
			body.setStatus(Glossary.Return_State_Parameter_Error);
			body.setMsg("parameter error.");
			return body;
		}
		TSRole role_db = get(TSRole.class, oConvertUtils.getString(role.getId()));
		if (!StringUtil.isNotEmpty(role_db)){
			role_db = findUniqueByProperty(TSRole.class, "roleCode", oConvertUtils.getString(role.getRoleCode()));
			if (!StringUtil.isNotEmpty(role_db)){
				body.setStatus(Glossary.Return_State_Parameter_Error);
				body.setMsg("parameter error [roleCode].");
				return body;
			}
			body.setStatus(Glossary.Return_State_Parameter_Error);
			body.setMsg("parameter error [id].");
			return body;
		}

		if (!StringUtil.isEmpty(role.getRoleCode())){
			if (!role.getRoleCode().equals(role_db.getRoleCode())){
				TSRole role1 = findUniqueByProperty(TSRole.class, "roleCode", oConvertUtils.getString(role.getRoleCode()));
				if (StringUtil.isNotEmpty(role1)){
					body.setStatus(Glossary.Return_State_Data_Exist);
					body.setMsg("Parameter occupied.");
					return body;
				}
			}
		}
		MyBeanUtils.copyBeanNotNull2Bean(role, role_db);

		updateEntitie(role_db);
		return body;
	}

	@Override
	public ReturnDataGrid getRoleDataGrid(TSRole role, DataGrid dataGrid) throws Exception {

		ReturnDataGrid grid = new ReturnDataGrid();

		Criteria criteria = this.getSession().createCriteria(TSRole.class);


		Disjunction dis = Restrictions.disjunction();

		if ( !StringUtil.isEmpty(role.getRoleName()) ){
			dis.add( Restrictions.like("roleName", "%" +role.getRoleName() + "%") );
		}
		if ( !StringUtil.isEmpty(role.getRoleCode()) ){
			dis.add( Restrictions.like("roleCode", "%" +role.getRoleCode() + "%") );
		}

		criteria.add(dis);


		if ( !StringUtil.isEmpty(dataGrid.getSort())){
			if (dataGrid.getOrder().name().equals("asc")) {

				criteria.addOrder( Order.asc(dataGrid.getSort()) );

			} else {

				criteria.addOrder( Order.desc(dataGrid.getSort()) );

			}

		}

		grid.setTotal(criteria.list().size());
		criteria.setMaxResults(dataGrid.getRows());
		criteria.setFirstResult( (dataGrid.getPage()-1) * dataGrid.getRows());

		grid.setPage(dataGrid.getPage());
		grid.setRows(criteria.list());
		return grid;
	}

	@Override
	public ReturnBody setRoleFunction(TSRole role, List<TSRoleFunction> roleFunctions) throws Exception {
		ReturnBody body = new ReturnBody();
		role = get(TSRole.class, role.getId());

		if (!StringUtil.isNotEmpty(role)){
			body.setStatus(Glossary.Return_State_Data_Not_Exist);
			body.setMsg("Data does not exist.");
			return body;
		}

		List<TSRoleFunction> roleFunctions_db = findByProperty(TSRoleFunction.class, "role.id", oConvertUtils.getString(role.getId()));
		List<TSRoleFunction> roleFunctions_db_back = findByProperty(TSRoleFunction.class, "role.id", oConvertUtils.getString(role.getId()));
		try {
			//在设置前，先清除现有的角色权限
			deleteAllEntitie(roleFunctions_db);

		} catch (HibernateException e){
			e.printStackTrace();
			body.setStatus(Glossary.Return_State_Exception_Operation);
			body.setMsg("operation failed.");
			return body;
		}


		try{
			//保存新的角色权限
			batchSave(roleFunctions);

		} catch (HibernateException e){
			e.printStackTrace();

			//当保存新的角色权限异常时
			//应还原之前的角色权限
			try{
				deleteAllEntitie(roleFunctions_db_back);
			} catch (HibernateException j){
				j.printStackTrace();
				System.out.println("============================ 角色权限还原失败【角色ID：“ "+role.getId()+" ”】 =================================");
				body.setStatus(Glossary.Return_State_Exception_Operation);
				body.setMsg("operation failed.Role restore failure.");
				return body;
			}
			body.setStatus(Glossary.Return_State_Exception_Operation);
			body.setMsg("operation failed.");
			return body;
		}

		return body;
	}
}
