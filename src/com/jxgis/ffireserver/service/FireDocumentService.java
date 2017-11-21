package com.jxgis.ffireserver.service;

import com.jxgis.ffireserver.bean.FFPFireDocument;
import com.jxgis.ffireserver.bean.FFPFireDocumentMedia;
import com.jxgis.ffireserver.core.common.model.json.DataGrid;
import com.jxgis.ffireserver.core.util.ReturnBody;
import com.jxgis.ffireserver.core.util.ReturnDataGrid;
import com.jxgis.ffireserver.web.service.SystemService;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * Created by YanWei on 2017/8/17.
 *
 * 火灾档案业务层
 *
 */
public interface FireDocumentService extends SystemService {

	//添加火灾档案
	ReturnBody addFireDocument(FFPFireDocument fireDocument, Map<String, MultipartFile> mediaFiles) throws Exception;

	//删除火灾档案
	ReturnBody deleteFireDocument(FFPFireDocument fireDocument) throws Exception;

	//修改火灾档案
	ReturnBody editFireDocument(FFPFireDocument fireDocument) throws Exception;

	//获得火灾档案列表
	ReturnDataGrid getFireDocumentList(FFPFireDocument fireDocument, DataGrid dataGrid, int fetchType) throws Exception;

	//添加火灾档案媒体文件
	ReturnBody addFireDocumentMedia(FFPFireDocumentMedia fireDocumentMedia, MultipartFile multipartFile) throws Exception;

	//删除火灾档案媒体文件
	ReturnBody deleteFireDocumentMedia(FFPFireDocumentMedia fireDocumentMedia) throws Exception;

}
