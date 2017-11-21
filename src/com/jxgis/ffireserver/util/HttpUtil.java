package com.jxgis.ffireserver.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class HttpUtil {

	public static final String ENTER = "\r\n";

	public static final String CONTENT_TYPE = "text/html";
	
	public static final String ENCODING = "UTF-8";
	
	/**
	 * @throws IOException
	 * @throws HttpException
	 * 
	 */
	public static InputStream getMethodRequest(String url, String encoding) {
		
		HttpClient httpClient = new HttpClient();
		GetMethod getMethod = new GetMethod(url);
		getMethod.getParams().setContentCharset(encoding);
		
//		String response = null;
		InputStream inputStream = null;
		try {
			int status = httpClient.executeMethod(getMethod);
			if (status == HttpStatus.SC_OK) {
				inputStream = getMethod.getResponseBodyAsStream();
//				BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, ENCODING));
//				StringBuffer stringBuffer = new StringBuffer();
//				String str = "";
//				while ((str = br.readLine()) != null) {
//					stringBuffer.append(str);
//				}
//				// 处理内容
//				response = stringBuffer.toString();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return inputStream;

	}

	/**
	 * @throws IOException
	 * @throws HttpException
	 * 
	 */
	public static String postMethodRequest(String reqStr, String url) {
		try {
			HttpClient httpClient = new HttpClient();
			PostMethod postMethod = new PostMethod(url);
			
			StringRequestEntity requestEntity = new StringRequestEntity(
					reqStr.toString(), CONTENT_TYPE, ENCODING);
			postMethod.setRequestEntity(requestEntity);
			
			httpClient.executeMethod(postMethod);
			
			InputStream inputStream = postMethod.getResponseBodyAsStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, ENCODING));
			StringBuffer stringBuffer = new StringBuffer();
			String str = "";
			while ((str = br.readLine()) != null) {
				stringBuffer.append(str);
			}
			// 处理内容
			String response = stringBuffer.toString();
			
			postMethod.releaseConnection();

			return response;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 *
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public String sendPost(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！"+e);
			e.printStackTrace();
		}
		//使用finally块来关闭输出流、输入流
		finally{
			try{
				if(out!=null){
					out.close();
				}
				if(in!=null){
					in.close();
				}
			}
			catch(IOException ex){
				ex.printStackTrace();
			}
		}
		return result;
	}

	public static void main(String[] args){
		HttpUtil httpUtil = new HttpUtil();
		String sql = httpUtil.sendPost("http://test.iloveyu.cn/php/interface/shipin.php", "method=GetCurrentInfo&time=1511154645&sign=d0ea5cf70acb6e97fddd1ca4002e34c0");

		System.out.println(sql);
	}

}
