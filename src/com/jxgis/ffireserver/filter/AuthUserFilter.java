package com.jxgis.ffireserver.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.xwork.StringUtils;

import com.jxgis.ffireserver.bean.Result;
import com.jxgis.ffireserver.service.bean.ServerToken;
import com.jxgis.ffireserver.util.AESCoderUtil;
import com.jxgis.ffireserver.util.Constants;
import com.jxgis.ffireserver.util.XmlUtil;

/**
 * 用户鉴权过滤器
 * 
 * @version 2012-12-3
 * @author tracy
 */
public class AuthUserFilter implements Filter
{

	private static final String CONTENT_TYPE_XML = "text/xml";
	private static final String AUTHORIZATION = "Authorization";

	/**
	 * 解密AMServer Token
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 *      javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException
	{

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		String reqPath = httpServletRequest.getRequestURI();

		String ip = request.getRemoteAddr();
		/**
		 * 登录和注册接口不需要鉴权
		 */
		if (reqPath.endsWith(Constants.LOGIN)
				|| reqPath.endsWith(Constants.REGIST)
				|| reqPath.endsWith(Constants.GETDATETIME))
		{
			chain.doFilter(request, response);
		}
		/**
		 * token鉴权
		 */
		else
		{
			Result result = null;
			String authorization = httpServletRequest.getHeader(AUTHORIZATION);
			// token为空返回错误码
			if (StringUtils.isBlank(authorization))
			{
				result = new Result(Constants.NO_OR_INVALID_CREDENTIAL,
						Constants.NO_OR_INVALID_CREDENTIAL_DESC);

				getResponseWriter(httpServletResponse).print(
						XmlUtil.toXml(result, "result"));
				return;
			} else
			{
				try
				{
					ServerToken serverToken = AESCoderUtil
							.decodeAMStoken(authorization);
					if (null != serverToken)
					{
						// 鉴权通过
						chain.doFilter(request, response);
					} else
					{
						result = new Result(Constants.NO_OR_INVALID_CREDENTIAL,
								Constants.NO_OR_INVALID_CREDENTIAL_DESC);

						getResponseWriter(httpServletResponse).print(
								XmlUtil.toXml(result, "result"));
						return;
					}

				} catch (Exception e)
				{
					// 解密失败，token非法
					e.printStackTrace();
					result = new Result(Constants.NO_OR_INVALID_CREDENTIAL,
							Constants.NO_OR_INVALID_CREDENTIAL_DESC);

					getResponseWriter(httpServletResponse).print(
							XmlUtil.toXml(result, "result"));
					return;
				}
			}
		}

	}

	/**
	 * 鉴权不通过直接输出错误码
	 * 
	 * @param servletResponse
	 * @return PrintWriter
	 */
	public PrintWriter getResponseWriter(HttpServletResponse servletResponse)
	{
		PrintWriter out = null;
		try
		{
			servletResponse.setContentType(CONTENT_TYPE_XML);
			servletResponse.setStatus(401);
			out = servletResponse.getWriter();
			out.flush();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return out;
	}

	/**
	 * 鉴权过滤器初始化
	 */
	@Override
	public void init(FilterConfig arg0) throws ServletException
	{

	}

	/**
	 * 鉴权过滤器销毁
	 */
	@Override
	public void destroy()
	{

	}

}
