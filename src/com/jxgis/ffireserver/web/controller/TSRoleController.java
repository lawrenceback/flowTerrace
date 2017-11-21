package com.jxgis.ffireserver.web.controller;

import com.jxgis.ffireserver.bean.vo.TSRoleModel;
import com.jxgis.ffireserver.core.util.ReturnBody;
import com.jxgis.ffireserver.core.util.StringUtil;
import com.jxgis.ffireserver.core.util.oConvertUtils;
import com.jxgis.ffireserver.util.Glossary;
import com.jxgis.ffireserver.web.bean.TSFunction;
import com.jxgis.ffireserver.web.bean.TSRole;
import com.jxgis.ffireserver.web.bean.TSRoleFunction;
import com.jxgis.ffireserver.web.service.TSControlService;
import com.jxgis.ffireserver.web.service.TSRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by YanWei on 2017/6/28.
 *
 * 角色控制器
 *
 * @author 言伟
 *
 */
@Controller
@RequestMapping("/roleController")
public class TSRoleController {

	private TSRoleService roleService;
	private TSControlService controlService;

	@Autowired
	public void setRoleService(TSRoleService roleService) {
		this.roleService = roleService;
	}

	@Autowired
	public void setControlService(TSControlService controlService) {
		this.controlService = controlService;
	}

	@RequestMapping(params = "goHref")
	public ModelAndView goHref(TSRole role, String href, HttpServletRequest req){
		role = roleService.get(TSRole.class, oConvertUtils.getString(role.getId()));
		req.setAttribute("role", role);
		return new ModelAndView(href);
	}

	//选择菜单
	@RequestMapping(params = "choiceFunction")
	public ModelAndView choiceFunction(TSRole role, HttpServletRequest req) throws Exception {

		StringBuilder hql_sb =new StringBuilder("select distinct f from TSFunction f,TSRoleFunction rf ").append("where rf.function.id=f.id and rf.role.id=?");

		List<TSFunction> lists = roleService.findHql(hql_sb.toString(), role.getId());

		role = roleService.get(TSRole.class, role.getId());

		List<String> ids = new ArrayList();

		for (TSFunction function: lists){
			ids.add(function.getId());
		}

		req.setAttribute("ids", ids);
		req.setAttribute("role", role);

		return new ModelAndView("system/role/role-choice-function");

	}

	//设置角色菜单
	@RequestMapping(params = "setRoleFunction")
	@ResponseBody
	public ReturnBody setRoleFunction(TSRole role, TSRoleFunction roleFunction, HttpServletRequest req) throws Exception{
		return roleService.setRoleFunction(role, roleFunction.getRoleFunctions());
	}

	@RequestMapping(value = "editRoles")
	@ResponseBody
	public ReturnBody editRoles(TSRoleModel roleModel, HttpServletRequest req) throws Exception{
		ReturnBody body = new ReturnBody();
		if (!StringUtil.isNotEmpty(roleModel)){
			body.setStatus(Glossary.Return_State_Parameter_Error);
			body.setMsg("参数错误");
			return body;
		}
		List<TSRole> roles = roleModel.getRoles();
		for (TSRole role: roles){
			roleService.editRole(role);
		}

		return body;
	}

}
