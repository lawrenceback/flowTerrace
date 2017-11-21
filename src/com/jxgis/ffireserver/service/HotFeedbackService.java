package com.jxgis.ffireserver.service;

import com.jxgis.ffireserver.bean.FFPHotFeedback;
import com.jxgis.ffireserver.bean.FFPHotFeedbackMedia;
import com.jxgis.ffireserver.core.common.model.json.DataGrid;
import com.jxgis.ffireserver.core.util.ReturnBody;
import com.jxgis.ffireserver.core.util.ReturnDataGrid;
import com.jxgis.ffireserver.web.service.SystemService;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * Created by YanWei on 2017/8/14.
 *
 * 热点反馈业务层
 *
 */
public interface HotFeedbackService extends SystemService{

	//添加热点反馈
	ReturnBody createHotFeedback(FFPHotFeedback feedback, Map<String, MultipartFile> mediaFiles) throws Exception;

	//修改热点反馈
	ReturnBody editHotFeedback(FFPHotFeedback feedback) throws Exception;

	//删除热点反馈
	ReturnBody deleteHotFeedback(FFPHotFeedback feedback) throws Exception;

	//审核火情
	ReturnBody examineHotFeedback(FFPHotFeedback feedback) throws Exception;

	//热点反馈列表
	ReturnDataGrid getHotFeedbackList(FFPHotFeedback feedback, DataGrid dataGrid) throws Exception;

	//添加热点反馈媒体文件
	ReturnBody addHotFeedbackMedia(FFPHotFeedbackMedia feedbackMedia, MultipartFile multipartFile) throws Exception;

	//删除热点反馈媒体文件
	ReturnBody deleteHotFeedbackMedia(FFPHotFeedbackMedia feedbackMedia) throws Exception;

}
