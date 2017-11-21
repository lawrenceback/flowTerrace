package com.jxgis.ffireserver.web.controller;

import com.jxgis.ffireserver.core.constant.DataBaseConstant;
import com.jxgis.ffireserver.core.util.*;
import com.jxgis.ffireserver.util.Glossary;
import com.jxgis.ffireserver.web.bean.*;
import com.jxgis.ffireserver.web.service.SystemService;
import com.jxgis.ffireserver.web.system.manager.ClientManager;
import com.jxgis.ffireserver.web.system.pojo.base.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by YanWei on 2017/6/5.
 * 登录控制器
 * @author 言伟
 */
@Controller
@RequestMapping("/loginController")
public class LoginController {


	private SystemService systemService;


	@Autowired
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}


	@RequestMapping(params = "login")
	@ResponseBody
	public ReturnBody login(TSUser user, HttpServletRequest req) throws Exception{

		ReturnBody out = new ReturnBody();

		TSUser user_db = systemService.findUniqueByProperty(TSUser.class, "account", user.getAccount());

		if( !StringUtil.isNotEmpty(user_db) ){

			out.setStatus( Glossary.Return_State_User_Not_Exist );
			out.setMsg( "用户不存在" );
			return out;
		}


		if ( !StringUtil.isNotEmpty(user_db.getState()) || user_db.getState() == 1 ){
			out.setStatus( Glossary.Return_State_User_Not_Power );
			out.setMsg( "Insufficient user permissions." );
			return out;
		}
		//下面开始校验密码
		String password = "";
		try{
			password = PasswordUtil.decrypt(user_db.getPassword(), user.getPassword(), PasswordUtil.getSalt());
		} catch (Exception e){
			out.setStatus( Glossary.Return_State_User_Password_Error );
			out.setMsg( "密码错误" );
			return out;
		}

		if ( !user_db.getAccount().equals( password )){

			out.setStatus( Glossary.Return_State_User_Password_Error );
			out.setMsg( "密码错误" );
			return out;

		}


		//密码正确


		//Web 网站用户

		saveLoginSuccessInfo(req, user_db);

		System.out.println("Web 登陆 ----" + user_db.getAccount() + "登录成功!");

		req.getSession().setAttribute("menuMap", getFunctionMap(user_db));

		//用户角色信息

		StringBuilder hql_sb =new StringBuilder("select distinct r from TSRole r,TSRoleUser ru ").append("where ru.role.id=r.id and ru.user.id=? ");

		List<TSRole> roles = systemService.findHql(hql_sb.toString(),user_db.getId());

		req.getSession().setAttribute("roles", roles);

		//用户控件权限

		hql_sb =new StringBuilder("select distinct c from TSControl c,TSControlRolePermissions crp,TSRoleUser ru ").append("where c.id=crp.control.id and ru.role.id=crp.role.id and ru.user.id=? ");

		List<TSControl> controls = systemService.findHql(hql_sb.toString(),user_db.getId());

		req.getSession().setAttribute(DataBaseConstant.Role_Permissions, controls);

		//用户部门信息

		hql_sb = new StringBuilder("select distinct d from TSDepart d,TSDepartUser du ").append("where d.id=du.depart.id and du.user.id=? ");

		List<TSDepart> departs = systemService.findHql(hql_sb.toString(),user_db.getId());

		req.getSession().setAttribute(DataBaseConstant.This_Depart_Content, departs);

		user_db.setLastIp(IpUtil.getIpAddr(req));
		user_db.setLastTime(new Date());
		this.systemService.updateEntitie( user_db );

		//返回用户数据
		out.setObj(user_db.loginReturn());
		return out;
	}

	@RequestMapping(params = "reLogin")
	public ModelAndView reLogin(HttpServletRequest req){
		return new ModelAndView("login/login");
	}


	/**
	 * 退出系统
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "logout")
	public ModelAndView logout(HttpServletRequest request) {
		try{
			HttpSession session = ContextHolderUtils.getSession();
			ClientManager.getInstance().removeClinet(session.getId());
			session.invalidate();

		} catch (Exception e){
			e.printStackTrace();
		}
		return new ModelAndView("login/login");
	}


	/**
	 * 保存用户登录的信息，并将当前登录用户的组织机构赋值到用户实体中；
	 * @param req request
	 * @param user 当前登录用户
	 */
	private void saveLoginSuccessInfo(HttpServletRequest req, TSUser user) throws Exception{

		HttpSession session = ContextHolderUtils.getSession();
		session.setAttribute(ResourceUtil.LOCAL_CLINET_USER, user);

		//当前session为空 或者 当前session的用户信息与刚输入的用户信息一致时，则更新Client信息
		Client clientOld = ClientManager.getInstance().getClient(session.getId());
		if(clientOld == null || clientOld.getUser() ==null ||user.getName().equals(clientOld.getUser().getName())){
			Client client = new Client();
			client.setIp(IpUtil.getIpAddr(req));
			client.setLogindatetime(new Date());
			client.setUser(user);
			ClientManager.getInstance().addClinet(session.getId(), client);
		} else {//如果不一致，则注销session并通过session=req.getSession(true)初始化session
			ClientManager.getInstance().removeClinet(session.getId());
			session.invalidate();
			session = req.getSession(true);//session初始化
			session.setAttribute(ResourceUtil.LOCAL_CLINET_USER, user);
			login(user,req);
		}

	}

	/**
	 * 获取权限的map
	 *
	 * @param user
	 * @return
	 */
	private Map<Integer, List<TSFunction>> getFunctionMap(TSUser user) {

		HttpSession session = ContextHolderUtils.getSession();

		Client client = ClientManager.getInstance().getClient(session.getId());

		if (client.getFunctionMap() == null || client.getFunctionMap().size() == 0) {

			Map<Integer, List<TSFunction>> functionMap = new HashMap<>();

			Map<String, TSFunction> loginActionlist = getUserFunction(user);

			if (loginActionlist.size() > 0) {
				Collection<TSFunction> allFunctions = loginActionlist.values();
				for (TSFunction function : allFunctions) {

					if (!functionMap.containsKey(function.getFunctionLevel() + 0)) {
						functionMap.put(function.getFunctionLevel() + 0,
								new ArrayList<TSFunction>());
					}
					functionMap.get(function.getFunctionLevel() + 0).add(function);
				}
				// 菜单栏排序
				Collection<List<TSFunction>> c = functionMap.values();
				for (List<TSFunction> list : c) {
					Collections.sort(list, new NumberComparator());
				}
			}
			client.setFunctionMap(functionMap);
			//清空变量，降低内存使用
			loginActionlist.clear();
			return functionMap;
		}else{
			return client.getFunctionMap();
		}
	}


	/**
	 * 获取用户菜单列表
	 *
	 * @param user
	 * @return
	 */
	private Map<String, TSFunction> getUserFunction(TSUser user) {
		HttpSession session = ContextHolderUtils.getSession();
		Client client = ClientManager.getInstance().getClient(session.getId());

		if (client.getFunctions() == null || client.getFunctions().size() == 0) {
			Map<String, TSFunction> loginActionlist = new HashMap<>();

			StringBuilder hqlsb1 =new StringBuilder("select distinct f from TSFunction f,TSRoleFunction rf,TSRoleUser ru ").append("where ru.role.id=rf.role.id and rf.function.id=f.id and ru.user.id=? ");

			List<TSFunction> list1 = systemService.findHql(hqlsb1.toString(),user.getId());

			for(TSFunction function:list1){
				loginActionlist.put(function.getId(),function);
			}

			client.setFunctions(loginActionlist);
			//清空变量，降低内存使用
			list1.clear();
		}
		return client.getFunctions();
	}


}
