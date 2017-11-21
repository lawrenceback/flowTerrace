package com.jxgis.ffireserver.service;

import com.jxgis.ffireserver.bean.Result;
import com.jxgis.ffireserver.service.bean.request.*;
import com.jxgis.ffireserver.service.bean.response.FindFireResult;
import com.jxgis.ffireserver.service.bean.response.GetFireRecordResult;
import com.jxgis.ffireserver.service.bean.response.PushToTalkResponse;
import com.jxgis.ffireserver.service.bean.response.UploadFileResult;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * 火灾档案接口定义类
 * 
 * @author 何桂明
 * 
 */
@Produces({ MediaType.APPLICATION_XML })
@Path("fireService")
public interface FireService {

	/**
	 * 获取火灾档案列表
	 * 
	 * @return
	 */
	@POST
	@Path("getFireRecordList")
	GetFireRecordResult getFireRecordList(GetFireRecord getFireRecord);
	
	/**
	 * 获取火灾档案列表
	 * 
	 * @return
	 */
	@POST
	@Path("findFire")
	FindFireResult getFindFire(FindFire findFire);
	

	/**
	 * 上传文件
	 */
	@POST
	@Path("uploadFile")
	UploadFileResult uploadFile(UploadFile uploadFile);
	
	/**
	 * 上传文件
	 */
	@POST
	@Path("uploadFileList")
	UploadFileResult uploadFileList(UploadFileList uploadFileList);
	
	/**
	 * 按下说话
	 */
	@POST
	@Path("pushToTalk")
	PushToTalkResponse pushToTalk(PushToTalkRequest request);
	
	/**
	 * 按下说话
	 */
	@POST
	@Path("pushTalkToAll")
	PushToTalkResponse pushTalkToAll(PushToTalkRequest request);
	
	/**
	 * sendBroadcastAudio
	 */
	@POST
	@Path("pcSendBroadcastAudio")
	Result pcSendBroadcastAudio(PcSendBroadcastAudioRequest request);

}
