package com.jxgis.ffireserver.service;

import com.jxgis.ffireserver.core.util.ReturnBody;
import com.jxgis.ffireserver.web.bean.TSUser;
import com.jxgis.ffireserver.web.service.SystemService;

/**
 * Created by YanWei on 2017/8/3.
 *
 */
public interface XmppManagerService extends SystemService {

	//下发消息到下级用户
	ReturnBody issuedMsgByCompetence(TSUser user, String content) throws Exception;

	//发送广播到所有在线用户
	ReturnBody sendBroadcastToAllOnline(String content) throws Exception;

	//发送消息
	ReturnBody sendMsg(TSUser user, String content) throws Exception;
}
