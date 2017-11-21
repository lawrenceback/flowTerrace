package com.jxgis.ffireserver.web.controller;


import com.jxgis.ffireserver.util.WebSocketSessionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * 功能说明：websocket处理类, 使用J2EE7的标准
 * 切忌直接在该连接处理类中加入业务处理代码
 * 作者：liuxing(2014-11-14 04:20)
 */
//relationId和userId是我的业务标识参数,websocket.ws是连接的路径，可以自行定义
@Controller
@ServerEndpoint("/websocket.ws/{user}")
@RequestMapping(params = "tweetWebSocket")
public class TweetWebSocket {

	private static Log log = LogFactory.getLog(TweetWebSocket.class);

	private Session session;


	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	String fileName = null;

	/**
	 * 打开连接时触发
	 *
	 * @param session
	 */

	@OnOpen
	public void onOpen(@PathParam("user") String user,Session session) {
		log.info("Websocket Start Connecting:");
		setSession(session);
		WebSocketSessionUtils.put(user, session);

	}

	/**
	 * 收到客户端消息时触发
	 *
	 * @param message
	 * @return
	 */

	@OnMessage(maxMessageSize = 1024*10*6)
	public String onMessage(@PathParam("user") String user,String message,Session session) {

		return "11111";
	}



	/**
	 * 异常时触发
	 *
	 * @param session
	 */

	@OnError
	public void onError(@PathParam("user") String user,Throwable throwable, Session session) {
		log.info("Websocket Connection Exception:");
		log.info(throwable.getMessage(), throwable);
		WebSocketSessionUtils.remove(user);
	}

	/**
	 * 关闭连接时触发
	 */

	@OnClose
	public void onClose(@PathParam("user") String user,Session session) {
		log.info("Websocket Close Connection:");
		WebSocketSessionUtils.remove(user);
	}

	public static boolean saveFileFromBytes(byte[] b, String outputFile)
	{
		BufferedOutputStream stream = null;
		File file = null;
		try
		{
			file = new File(outputFile);
			FileOutputStream fstream = new FileOutputStream(file);
			stream = new BufferedOutputStream(fstream);
			stream.write(b);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
		finally
		{
			if (stream != null)
			{
				try
				{
					stream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return true;
	}


}
