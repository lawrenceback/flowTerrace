package com.jxgis.ffireserver.service;

import com.jxgis.ffireserver.bean.FFPLicense;
import com.jxgis.ffireserver.web.bean.TSUser;
import com.jxgis.ffireserver.core.common.model.json.DataGrid;
import com.jxgis.ffireserver.core.util.ReturnDataGrid;
import com.jxgis.ffireserver.core.util.ReturnBody;
import com.jxgis.ffireserver.web.service.SystemService;

import java.util.Date;
import java.util.List;

/**
 * Created by YanWei on 2017/6/12.
 *
 */
public interface LicenseService extends SystemService {
	//查询是否存在IMEI
	ReturnBody isExistLicense(String imei) throws Exception;

	//创建一条新的许可
	ReturnBody insertLicense(FFPLicense license) throws Exception;

	//创建一条新的许可
	ReturnBody insertLicenseExternal(FFPLicense license, TSUser user, int number) throws Exception;

	//移除许可绑定
	ReturnBody removeLicense(String key, String imei) throws Exception;

	//更新（添加许可绑定）
	ReturnBody updateLicense(FFPLicense license) throws Exception;

	//删除一个 许可
	ReturnBody deleteLicense(int id) throws Exception;
	ReturnBody deleteLicenses(List<FFPLicense> licenses) throws Exception;

	//注册设备
	ReturnBody registLicense(FFPLicense license) throws Exception;

	ReturnBody insertLicenseNumber(FFPLicense license, int number) throws Exception;

	//获得imei列表
	ReturnDataGrid getLicenseDataGrid(FFPLicense license, DataGrid dataGrid, int fetchType, Date createTime_start, Date createTime_end) throws Exception;

	//获得imei列表 [状态]
	ReturnDataGrid getLicenseDataGrid(FFPLicense license, DataGrid dataGrid, int fetchType, Date createTime_start, Date createTime_end, int fetchStatus, int license_status) throws Exception;

}
