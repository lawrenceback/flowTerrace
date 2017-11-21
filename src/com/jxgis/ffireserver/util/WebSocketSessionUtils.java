package com.jxgis.ffireserver.util;

import com.jxgis.ffireserver.core.util.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.websocket.Session;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 功能说明：用来存储业务定义的sessionId和连接的对应关系
 * 利用业务逻辑中组装的sessionId获取有效连接后进行后续操作
 * 作者：liuxing(2014-12-26 02:32)
 */
public class WebSocketSessionUtils {

	private static Log log = LogFactory.getLog(WebSocketSessionUtils.class);
	public static Map<String, Session> clients = new ConcurrentHashMap<>();

	public static void put(String userCode, Session session){
		clients.put(userCode, session);
	}

	public static Session get(String userCode){
		return clients.get(userCode);
	}

	public static void remove(String userCode){
		if (hasConnection(userCode)){
			clients.remove(userCode);
		}
	}

	/**
	 * 判断是否有连接
	 * @param userCode
	 * @return
	 */
	public static boolean hasConnection(String userCode) {
		if (userCode != null){
			return clients.containsKey(userCode);
		}
		return false;
	}

	/**
	 * 组装唯一识别的key
	 * @param userCode
	 * @return
	 */
	public static String getKey(String userCode) {
		return userCode;
	}


	/**
	 * 消息发送方法
	 * @param msg
	 */
	private static void broadcast(String user,String msg) throws IOException {
		if(msg.indexOf(user)!=-1){
			sendUser(user,msg);
		} else{
			sendAll(msg);
		}
	}

	/**
	 * 向指定用户发送消息
	 * @param msg
	 */
	public static void sendUser(String user,String msg) throws IOException{
		WebSocketSessionUtils.get(user).getBasicRemote().sendText(msg);
	}

	/**
	 * 向所有用户发送
	 * @param msg
	 */
	public static void sendAll(String msg) throws IOException{
		for (String key : WebSocketSessionUtils.clients.keySet()) {
			Session client = null ;
			try {
				client = WebSocketSessionUtils.get(key);
				synchronized (client) {
					client.getBasicRemote().sendText(msg);
				}
			} catch (IOException e) {
				log.debug("Chat Error: Failed to send message to client", e);
				WebSocketSessionUtils.remove(key);
				try {
					client.close();
				} catch (IOException e1) {
					// Ignore
				}
				String message = String.format("* %s %s", key, "has been disconnected.");
				broadcast(key, message);
			}
		}
	}

}
