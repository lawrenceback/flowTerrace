package com.jxgis.ffireserver.service;

import com.jxgis.ffireserver.bean.FFPCommand;
import com.jxgis.ffireserver.bean.FFPCommandMedia;
import com.jxgis.ffireserver.core.common.model.json.DataGrid;
import com.jxgis.ffireserver.core.util.ReturnBody;
import com.jxgis.ffireserver.core.util.ReturnDataGrid;
import com.jxgis.ffireserver.web.service.SystemService;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * Created by YanWei on 2017/8/1.
 *
 * 森林防火指挥部业务层
 *
 */
public interface CommandService extends SystemService {

	//添加森林防火指挥部
	ReturnBody addCommand(FFPCommand command, Map<String, MultipartFile> mediaFiles) throws Exception;

	//删除森林防火指挥部
	ReturnBody deleteCommand(FFPCommand command) throws Exception;

	//编辑森林防火指挥部
	ReturnBody editCommand(FFPCommand command) throws Exception;

	//获得森林防火指挥部列表
	ReturnDataGrid getCommandList(FFPCommand command, DataGrid dataGrid, int fetchType) throws Exception;

	//添加森林防火指挥部媒体文件
	ReturnBody addCommandMedia(FFPCommandMedia commandMedia, MultipartFile mediaFile) throws Exception;

	//删除森林防火指挥部媒体文件
	ReturnBody deleteCommandMedia(FFPCommandMedia commandMedia) throws Exception;
}
