package com.jxgis.ffireserver.web.controller;

import com.jxgis.ffireserver.web.bean.TSUser;
import com.jxgis.ffireserver.core.util.ContextHolderUtils;
import com.jxgis.ffireserver.core.util.ResourceUtil;
import com.jxgis.ffireserver.core.util.StringUtil;
import com.jxgis.ffireserver.core.util.oConvertUtils;
import com.jxgis.ffireserver.web.service.SystemService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by YanWei on 2017/3/26.
 *
 * 后台管理系统内部跳转，每个JSP，都将会带上当前登录的用户信息
 *
 * 这一步，也为了方便配置菜单，如此将不需要配置多个跳转方法
 *
 * @author 言伟
 */
@Controller
@RequestMapping(value = "/serviceHrefController")
public class ServiceHrefController{

	private static final Logger logger = Logger.getLogger(ServiceHrefController.class);

	private SystemService systemService;

	@Autowired
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	@RequestMapping(value = "action")
	public ModelAndView action(HttpServletRequest req, HttpServletResponse res)throws Exception {

		int fun = oConvertUtils.getInt(req.getParameter("f"));
		switch (fun){
			case 1:
				//获得登陆用户信息的内部页面跳转
				return userHref(
						oConvertUtils.getString(req.getParameter("href")),
						req,
						res
				);
			case 2:
				//获得登陆用户信息的内部页面跳转
				return userHref2(
						oConvertUtils.getString(req.getParameter("href")),
						req,
						res
				);
			default:
				return new ModelAndView("href/404");
		}

	}


	/**获得登陆用户信息的内部页面跳转*/
	private ModelAndView userHref(String href, HttpServletRequest req, HttpServletResponse res) {

		HttpSession session = ContextHolderUtils.getSession();
		session.getAttribute(ResourceUtil.LOCAL_CLINET_USER);

		Object t = session.getAttribute(ResourceUtil.LOCAL_CLINET_USER);
		if (StringUtil.isNotEmpty(t)){
			try{
				TSUser user = (TSUser) t;
				user = systemService.get(TSUser.class, user.getId());
				if (StringUtil.isNotEmpty(user)){
					req.setAttribute("user",user);
					return new ModelAndView(href);
				}
			} catch (Exception e) {
				System.out.println("服务器重启啦！");
			}

		}

		return new ModelAndView("redirect:/loginController.do?reLogin");
	}
	/**获得登陆用户信息的内部页面跳转*/
	private ModelAndView userHref2(String href, HttpServletRequest req, HttpServletResponse res) {
		return new ModelAndView(href);
	}

}
