package com.jxgis.ffireserver.service;

import com.jxgis.ffireserver.bean.FFPWarningBoards;
import com.jxgis.ffireserver.bean.FFPWarningBoardsMedia;
import com.jxgis.ffireserver.core.common.model.json.DataGrid;
import com.jxgis.ffireserver.core.util.ReturnBody;
import com.jxgis.ffireserver.core.util.ReturnDataGrid;
import com.jxgis.ffireserver.web.service.SystemService;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * Created by YanWei on 2017/8/30.
 *
 * 大型警示牌(大型宣传牌) 业务层
 *
 */
public interface WarningBoardsService extends SystemService {
	//添加 大型警示牌
	ReturnBody addBoards(FFPWarningBoards boards, Map<String, MultipartFile> mediaFiles) throws Exception;

	//删除 大型警示牌
	ReturnBody deleteBoards(FFPWarningBoards boards) throws Exception;

	//修改 大型警示牌
	ReturnBody editBoards(FFPWarningBoards boards) throws Exception;

	//获得 大型警示牌 列表
	ReturnDataGrid getBoardsList(FFPWarningBoards boards, DataGrid dataGrid, int fetchTypt) throws Exception;

	//添加 大型警示牌媒体文件
	ReturnBody addBoardsMedia(FFPWarningBoardsMedia boardsMedia, MultipartFile mediaFile) throws Exception;

	//删除 大型警示牌媒体文件
	ReturnBody deleteBoardsMedia(FFPWarningBoardsMedia boardsMedia) throws Exception;
}
