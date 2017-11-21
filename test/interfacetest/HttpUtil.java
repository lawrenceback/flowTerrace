package interfacetest;

/**
 * @class IfsUtil
 * @Describtion ���������
 * @Date 2011-09-15
 * @Author zhouyouyi
 */

import java.io.IOException;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class HttpUtil {

	public static final String XML_HEAD = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>";

	public static final String ENTER = "\r\n";

	public static final String TYPE_XML = "text/xml";

	public static final String ENCODE_DEFAULT = "UTF-8"; // 默认编码：UTF-8

	// public static final String SERVERURL = "http://10.137.29.133:8080";
	public static final String SERVERURL = "http://127.0.0.1:8089";
	// public static final String SERVERURL="http://112.80.141.35:9999";
	// public static final String SERVERURL="http://192.168.1.103:8080";
	// public static final String SERVERURL="http://86.96.241.70:8000";

	public static String AUTH = "c81ef0edea420a7e7196919bf8017b2fcbf9559cec7a87c7c2016baac85e70c6a9cb738434d21fa10e4fab6824221c583beb5a2052f33f83301d26e8c39c7e2aba92329ecb78e6d11c32e773b9ea3519";

	// static
	// {
	// byte []b = AUTH.getBytes();
	// AUTH = new String(Base64.encode(b));
	// }

	/**
	 * "bmdHYnJyYWJ8MXwxfDEzMzg3NjUyODMyMzl8QXVCY0xidE0xMzF5dG43aHliOGFoMElNeFV3VDJLUGJSMDNNR2x1ek1lMTB5QlU2bVlsb01HQTZfVGVDSVlsZUpWZzJMRmpiaDVJekZINk1xSjBhQllKSjZCbGF2bWRuOG5zbWhiSWRBYWc5WFJ0LldjRHE0cnFhU1hsSTQ3NktzNUJQMGlfWXFwQVJmNDBnN0I1YkpPVG4wT0kwTUFsR3JmWVI5WlouVnh3LQ=="
	 * 
	 * @param url
	 *            QHL_aQdV|1|1|1338407076063|
	 *            CDhHS2aEBJfi67yBcrkCc9pdgUf4lp3bzXeU6K36DYM7F5MDm.ZZ5_VNVX.5
	 *            yJ8j63Poldo3XHxqEGu9RvUrvlKMWYbNatc8uctvIZueDfYZGfPIhgF
	 *            .uCIs6ImacjfMHl_1eaiO12rJ9BIsVDIPZYrgJPTwkuazAqBlSwexhaQ-
	 *            get方式请求的地�?
	 * @param auth
	 *            鉴权用的�?
	 * @return
	 */
	public static String getMethodRequest(String methodName, String url,
			String auth) {

		byte[] responseBody = null;
		// 构�?HttpClient的实�?�??
		HttpClient httpClient = new HttpClient();
		// 创建GET方法的实�?�??
		GetMethod getMethod = new GetMethod(url);
		// 使用系统提供的默认的恢复策略
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());
		getMethod.setRequestHeader("Authorization", auth);

		System.out.println(methodName + "  requestUrl: " + ENTER + url);
		try {
			// 执行getMethod
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: "
						+ getMethod.getStatusLine());
			}
			// 读取内容
			responseBody = getMethod.getResponseBody();
			// 处理内容
			String response = new String(responseBody);
			System.out
					.println(methodName + "responseDate: " + ENTER + response);
		} catch (HttpException e) {
			// 发生致命的异常，可能是协议不对或者返回的内容有问�?
			System.out.println("Please check your provided http address!");
			e.printStackTrace();
		} catch (IOException e) {
			// 发生网络异常
			e.printStackTrace();
		} finally {
			// 释放连接
			getMethod.releaseConnection();
		}

		return (new String(responseBody));
	}

	/**
	 * @throws IOException
	 * @throws HttpException
	 * 
	 */
	public static String postMethodRequest(String methodName, String reqXml,
			String url, String auth) throws HttpException, IOException {
		// 构�?HttpClient的实�?�??
		HttpClient httpClient = new HttpClient();
		// 创建POST方法的实�?�??
		PostMethod postMethod = new PostMethod(url);

		StringRequestEntity requestEntity = new StringRequestEntity(
				reqXml.toString(), TYPE_XML, ENCODE_DEFAULT);
		postMethod.setRequestEntity(requestEntity);
		postMethod.setRequestHeader("Authorization", auth);

		System.out.println(methodName + "  requestDate: " + ENTER + url);

		int statusCode = httpClient.executeMethod(postMethod);
		if (statusCode != HttpStatus.SC_OK) {
			System.err.println("Method failed: " + postMethod.getStatusLine());
		}

		// 读取内容
		byte[] responseBody = postMethod.getResponseBody();
		// 处理内容
		String response = new String(responseBody);
		System.out.println(methodName + "responseDate: " + ENTER + response);

		postMethod.releaseConnection();

		return response;

	}

	/**
	 * @throws IOException
	 * @throws HttpException
	 * 
	 */
	public static String putMethodRequest(String methodName, String reqXml,
			String url, String auth) throws HttpException, IOException {
		// 构�?HttpClient的实�?�??
		HttpClient httpClient = new HttpClient();
		// 创建POST方法的实�?�??
		PutMethod postMethod = new PutMethod(url);

		StringRequestEntity requestEntity = null;
		requestEntity = new StringRequestEntity(reqXml.toString(), TYPE_XML,
				ENCODE_DEFAULT);
		postMethod.setRequestEntity(requestEntity);
		postMethod.setRequestHeader("Authorization", auth);

		System.out.println(methodName + "  requestDate: " + ENTER + url);

		int statusCode = httpClient.executeMethod(postMethod);
		if (statusCode != HttpStatus.SC_OK) {
			System.err.println("Method failed: " + postMethod.getStatusLine());
		}

		// 读取内容
		byte[] responseBody = postMethod.getResponseBody();
		// 处理内容
		String response = new String(responseBody);
		System.out.println(methodName + "responseDate: " + ENTER + response);

		postMethod.releaseConnection();

		return response;

	}

	/**
	 * 
	 * @param url
	 *            delete方式请求的地�?
	 * @param auth
	 *            鉴权用的�?
	 * @return
	 */
	public static String deleteMethodRequest(String methodName, String url,
			String auth) {

		byte[] responseBody = null;
		// 构�?HttpClient的实�?�??
		HttpClient httpClient = new HttpClient();
		// 创建GET方法的实�?�??
		DeleteMethod delMethod = new DeleteMethod(url);
		// 使用系统提供的默认的恢复策略
		delMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());
		delMethod.setRequestHeader("Authorization", auth);

		System.out.println(methodName + "  requestUrl: " + ENTER + url);
		try {
			// 执行getMethod
			int statusCode = httpClient.executeMethod(delMethod);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: "
						+ delMethod.getStatusLine());
			}
			// 读取内容
			responseBody = delMethod.getResponseBody();
			// 处理内容
			String response = new String(responseBody);
			System.out
					.println(methodName + "responseDate: " + ENTER + response);
		} catch (HttpException e) {
			// 发生致命的异常，可能是协议不对或者返回的内容有问�?
			System.out.println("Please check your provided http address!");
			e.printStackTrace();
		} catch (IOException e) {
			// 发生网络异常
			e.printStackTrace();
		} finally {
			// 释放连接
			delMethod.releaseConnection();
		}

		return (new String(responseBody));
	}

}
