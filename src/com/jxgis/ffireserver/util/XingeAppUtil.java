package com.jxgis.ffireserver.util;

import com.tencent.xinge.ClickAction;
import com.tencent.xinge.Message;
import com.tencent.xinge.Style;
import com.tencent.xinge.XingeApp;

public class XingeAppUtil {

	// 下发所有设备
	public static void pushAllDevice(String title, String content) {
		XingeApp xinge = new XingeApp(2100050111, "bb1cb135fb45fa147bb58232f55a3e05");
		Message message = new Message();
		message.setExpireTime(86400);
		message.setTitle(title);
		message.setContent(content);
		message.setType(Message.TYPE_NOTIFICATION);
		Style style = new Style(1);
		style = new Style(3,1,0,1,0);
		message.setStyle(style);
		
		ClickAction action = new ClickAction();
		action.setActionType(ClickAction.TYPE_URL);
		action.setUrl("http://xg.qq.com");
		message.setAction(action);
		
		xinge.pushAllDevice(0, message);
		
	}

	// 下发单个账号
	public static void pushSingleAccount(String title, String content, String account) {
		XingeApp xinge = new XingeApp(2100050111, "bb1cb135fb45fa147bb58232f55a3e05");
		Message message = new Message();
		message.setExpireTime(86400);
		message.setTitle(title);
		message.setContent(content);
		message.setType(Message.TYPE_MESSAGE);
		xinge.pushSingleAccount(0, account, message);
	}

}
