package com.jxgis.ffireserver.service;

import com.jxgis.ffireserver.bean.FFPArtificiallake;
import com.jxgis.ffireserver.bean.FFPArtificiallakeMedia;
import com.jxgis.ffireserver.core.common.model.json.DataGrid;
import com.jxgis.ffireserver.core.util.ReturnBody;
import com.jxgis.ffireserver.core.util.ReturnDataGrid;
import com.jxgis.ffireserver.web.service.SystemService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Created by YanWei on 2017/8/3.
 *
 * 航空灭火蓄水池业务层
 *
 */
public interface ArtificiallakeService extends SystemService {

	//添加航空灭火蓄水池
	ReturnBody addArtificiallake(FFPArtificiallake artificiallake, Map<String, MultipartFile> mediaFiles) throws Exception;

	//删除航空灭火蓄水池
	ReturnBody deleteArtificiallake(FFPArtificiallake artificiallake) throws Exception;
	ReturnBody deleteArtificiallakes(List<FFPArtificiallake> artificiallakes) throws Exception;

	//编辑航空灭火蓄水池
	ReturnBody editArtificiallake(FFPArtificiallake artificiallake) throws Exception;

	//获得航空灭火蓄水池列表
	ReturnDataGrid getArtificiallakeList(FFPArtificiallake artificiallake, DataGrid dataGrid, int fetchType) throws Exception;

	//添加航空灭火蓄水池媒体文件
	ReturnBody addArtificiallakeMedia(FFPArtificiallakeMedia artificiallakeMedia, MultipartFile mediaFile) throws Exception;

	//删除航空灭火蓄水池媒体文件
	ReturnBody deleteArtificiallakeMedia(FFPArtificiallakeMedia artificiallakeMedia) throws Exception;
}
