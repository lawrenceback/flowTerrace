package com.jxgis.ffireserver.web.controller;

import com.jxgis.ffireserver.bean.vo.TSRoleModel;
import com.jxgis.ffireserver.bean.vo.TSUserModel;
import com.jxgis.ffireserver.core.util.ContextHolderUtils;
import com.jxgis.ffireserver.core.util.ReturnBody;
import com.jxgis.ffireserver.core.util.StringUtil;
import com.jxgis.ffireserver.core.util.oConvertUtils;
import com.jxgis.ffireserver.util.Glossary;
import com.jxgis.ffireserver.web.bean.*;
import com.jxgis.ffireserver.web.service.TSUserService;
import com.jxgis.ffireserver.web.system.manager.ClientManager;
import com.jxgis.ffireserver.web.system.pojo.base.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by YanWei on 2017/6/2.
 *
 * 用户控制器
 *
 * @author 言伟
 *
 */
@Controller
@RequestMapping("/userController")
public class TSUserController {

	private TSUserService userService;

	@Autowired
	public void setUserService(TSUserService userService) {
		this.userService = userService;
	}



	/***
	 *
	 * 页面重写向，并带上user对象
	 *
	 * @return ModelAndView
	 *
	 */
	@RequestMapping(params = "goHref")
	public ModelAndView goHref(TSUser user, String href, HttpServletRequest req) {
		req.setAttribute("user", this.userService.get(TSUser.class, user.getId()));
		return new ModelAndView(href);
	}


	//选择用户角色
	@RequestMapping(params = "choiceRole")
	public ModelAndView choiceRole(TSUser user, HttpServletRequest req){
		user = userService.get(TSUser.class, user.getId());
		req.setAttribute("user", user);
		List<TSRole> roles = new ArrayList<>();
		if (StringUtil.isNotEmpty(user)){
			StringBuilder hql_sb =new StringBuilder("select distinct r from TSRole r,TSRoleUser ru ").append("where ru.role.id=r.id and ru.user.id=? ");
			roles = userService.findHql(hql_sb.toString(), user.getId());
		}

		req.setAttribute("roles", roles);

		return new ModelAndView("system/user/user-choice-role");
	}

	//设置用户角色
	@RequestMapping(params = "setUserRole")
	@ResponseBody
	public ReturnBody setUserRole(TSUser user, TSRoleUser roleUser, HttpServletRequest req) throws Exception{
		return userService.setUserRole(user, roleUser.getRoleUsers());
	}


	//选择用户角色
	@RequestMapping(params = "choiceDepart")
	public ModelAndView choiceDepart(TSUser user, HttpServletRequest req){
		user = userService.get(TSUser.class, user.getId());

		List<TSDepart> departs = new ArrayList<>();
		if (StringUtil.isNotEmpty(user)){
			StringBuilder hql_sb =new StringBuilder("select distinct d from TSDepart d,TSDepartUser du ").append("where du.depart.id=d.id and du.user.id=? ");
			departs = userService.findHql(hql_sb.toString(), user.getId());
		}

		List<String> ids = new ArrayList();

		for (TSDepart depart: departs){
			ids.add(depart.getId());
		}

		req.setAttribute("ids", ids);
		req.setAttribute("user", user);

		return new ModelAndView("system/user/user-choice-depart");
	}

	//设置角色菜单
	@RequestMapping(params = "setUserDeparts")
	@ResponseBody
	public ReturnBody setUserDeparts(TSUser user, TSDepartUser departUser, HttpServletRequest req) throws Exception{
		return userService.setUserDepart(user, departUser.getDepartUsers());
	}


	//用户个人中心
	@RequestMapping(params = "getUserInfo")
	public ModelAndView getUserInfo(TSUser user, HttpServletRequest req){

		HttpSession session = ContextHolderUtils.getSession();

		Client client = ClientManager.getInstance().getClient(session.getId());

		req.setAttribute("user", userService.get(TSUser.class, client.getUser().getId()));

		return new ModelAndView("system/user/personal/info");
	}

	@ResponseBody
	@RequestMapping(value = "addUser")
	public ReturnBody addUser(TSRoleModel role, TSUser user, TSDepartUser depart, HttpServletRequest request) throws Exception{

		try{
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			return userService.addUser(user, multipartRequest.getFile("file"), role.getRoles(), depart.getDepart());
		} catch (ClassCastException e){
			return userService.addUser(user, null, role.getRoles(), depart.getDepart());
		}

	}

	@RequestMapping(value = "editGo")
	public ModelAndView editGo(TSUser user, HttpServletRequest request) throws Exception{
		user = userService.get(TSUser.class, user.getId());


		//查询角色
		StringBuilder hql_sb =new StringBuilder("select distinct r from TSRole r,TSRoleUser ru ").append("where ru.role.id=r.id and ru.user.id=? ");

		List<TSRole> roles = userService.findHql(hql_sb.toString(), user.getId());

		user.setRoles(roles);



		//查询部门
		hql_sb = new StringBuilder("select distinct d from TSDepart d,TSDepartUser du ").append("where du.depart.id=d.id and du.user.id=? order by d.orgCode asc");

		List<TSDepart> departs = userService.findHql(hql_sb.toString(), user.getId());

		if (departs.size() > 0){

			TSDepart depart = departs.get(0);

			request.getSession().setAttribute("orgCodes", new ArrayList<String>());

			List<String> orgCodes = peggingOrgCode(oConvertUtils.getString(depart.getOrgCode()), request);

			if (orgCodes.size() > 0){

				String sql = "SELECT * FROM t_s_depart WHERE 1=1 ";
				List<Object> sql_like = new ArrayList<>();
				int i=0;
				for(String orgCode: orgCodes){
					if (i==0){
						sql += " and org_code=?";
						sql_like.add(orgCode);
					} else {
						sql += " or org_code=?";
						sql_like.add(orgCode);
					}
					i++;
				}
				sql += " ORDER BY org_code ASC";

				user.setDeparts(userService.findForJdbc(sql, sql_like.toArray()));

			}

		}

		request.setAttribute("user", user);

		return new ModelAndView("system/user/user-upd");
	}

	@RequestMapping(value = "edit")
	@ResponseBody
	private ReturnBody edit(TSRoleModel role, TSUser user, TSDepartUser depart, HttpServletRequest req) throws Exception{

		String password = oConvertUtils.getString(req.getParameter("password"));

		

		try{
			// 获取用户上传的文件
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
			MultipartFile headPortrait = multipartRequest.getFile("headPortrait_file");

			return userService.editUser(role.getRoles(), user, headPortrait, depart, password);
		} catch (ClassCastException e){
			return userService.editUser(role.getRoles(), user, null, depart, password);
		}
	}

	@ResponseBody
	@RequestMapping(value = "deletes")
	private ReturnBody deletes(TSUserModel userModel, HttpServletRequest request) throws Exception{
		return userService.delUsers(userModel.getUsers());
	}

	@ResponseBody
	@RequestMapping(value = "clearRoles")
	private ReturnBody clearRoles(TSUser user, HttpServletRequest request) throws Exception{
		ReturnBody body = new ReturnBody();
		if (!StringUtil.isNotEmpty(user)){
			body.setStatus(Glossary.Return_State_Parameter_Error);
			body.setMsg("parameter error.");
			return body;
		}
		List<TSRoleUser> roleUsers = userService.findByProperty(TSRoleUser.class, "user.id", user.getId());
		if (roleUsers.size() < 1){
			body.setStatus(Glossary.Return_State_Data_Not_Exist);
			body.setMsg("data not find [roles].");
			return body;
		}
		userService.deleteAllEntitie(roleUsers);
		return body;
	}
	@ResponseBody
	@RequestMapping(value = "clearDeparts")
	private ReturnBody clearDeparts(TSUser user, HttpServletRequest request) throws Exception{
		ReturnBody body = new ReturnBody();
		if (!StringUtil.isNotEmpty(user)){
			body.setStatus(Glossary.Return_State_Parameter_Error);
			body.setMsg("parameter error.");
			return body;
		}
		List<TSDepartUser> departUsers = userService.findByProperty(TSDepartUser.class, "user.id", user.getId());
		if (departUsers.size() < 1){
			body.setStatus(Glossary.Return_State_Data_Not_Exist);
			body.setMsg("data not find [departs].");
			return body;
		}
		userService.deleteAllEntitie(departUsers);
		return body;
	}


	//向上追查所有的部门编号
	private List<String> peggingOrgCode(String orgCode,HttpServletRequest request) throws Exception{
		List<String> orgCodes = (List<String>) request.getSession().getAttribute("orgCodes");
		if (orgCode.length() > 3){
			orgCodes.add(orgCode);
			return peggingOrgCode(orgCode.substring(0, orgCode.length()-2), request);
		}
		return orgCodes;
	}




}
