package com.jxgis.ffireserver.web.service.impl;

import com.jxgis.ffireserver.core.common.model.json.DataGrid;
import com.jxgis.ffireserver.core.util.ReturnBody;
import com.jxgis.ffireserver.core.util.ReturnDataGrid;
import com.jxgis.ffireserver.core.util.StringUtil;
import com.jxgis.ffireserver.util.Glossary;
import com.jxgis.ffireserver.web.bean.TSUser;
import com.jxgis.ffireserver.web.service.FireUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by YanWei on 2017/7/21.
 *
 */
@Service("fireUserService")
@Transactional
public class FireUserServiceImpl extends SystemServiceImpl implements FireUserService {
	@Override
	public ReturnBody logout(TSUser user) {
		ReturnBody body = new ReturnBody();

		TSUser user_db = findUniqueByProperty(TSUser.class, "account", user.getAccount());
		if (StringUtil.isNotEmpty(user_db)){
			updateEntitie(user_db);
			return body;
		}
		body.setStatus(Glossary.Return_State_Parameter_Error);
		body.setMsg("parameter error [body]");
		return body;
	}

	@Override
	public ReturnDataGrid getOnlineUserList(TSUser user, DataGrid dataGrid, int fetchType) {

		return null;
	}
}
