package com.jxgis.ffireserver.core.interceptors;

import com.jxgis.ffireserver.core.util.ContextHolderUtils;
import com.jxgis.ffireserver.core.util.ResourceUtil;
import com.jxgis.ffireserver.web.system.manager.ClientManager;
import com.jxgis.ffireserver.web.system.pojo.base.Client;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * 权限拦截器
 *
 * @author 言伟
 */
public class AuthInterceptor implements HandlerInterceptor {
	private static final Logger logger = Logger.getLogger(AuthInterceptor.class);
	private List<String> excludeUrls;


	public List<String> getExcludeUrls() {
		return excludeUrls;
	}

	public void setExcludeUrls(List<String> excludeUrls) {
		this.excludeUrls = excludeUrls;
	}

	/**
	 * 在controller后拦截
	 */
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception) throws Exception {
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView modelAndView) throws Exception {

	}

	/**
	 * 在controller前拦截
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {

		String requestPath = "";
		if (request.getRequestURI().indexOf(".do") > 0 ){
			requestPath = ResourceUtil.getRequestPath(request);// 用户访问的资源地址
		} else {
			requestPath = ResourceUtil.getRequestPath(request,1);// 用户访问的资源地址
		}



		logger.info("-----authInterceptor----requestPath------" + requestPath);
		HttpSession session = ContextHolderUtils.getSession();
		Client client = ClientManager.getInstance().getClient(session.getId());
		if (client == null) {
			client = ClientManager.getInstance().getClient(request.getParameter("sessionId"));
		}

		if (excludeUrls.contains(requestPath)) {
			//如果该请求不在拦截范围内，直接返回true
			//System.out.println("如果该请求不在拦截范围内，直接返回true");
			return true;
		} else {
			if (client != null && client.getUser() != null) {

				return true;
			} else {
				forward(request, response);
			}

			return false;
		}


	}

	/**
	 * 判断用户是否有菜单访问权限
	 *
	 * @param request
	 * @return
	 */
	private boolean hasMenuAuth(HttpServletRequest request) {
		return true;
	}

	/**
	 * 转发
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "forword")
	public ModelAndView forword(HttpServletRequest request) {
		return new ModelAndView(new RedirectView("loginController.do?reLogin"));
	}

	private void forward(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//超时，未登陆页面跳转
		response.sendRedirect(request.getSession().getServletContext().getContextPath() + "/webpage/login/timeout.jsp");

	}


}
