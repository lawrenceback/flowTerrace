package com.jxgis.ffireserver.web.service.impl;

import com.jxgis.ffireserver.web.bean.TSIcon;
import com.jxgis.ffireserver.core.common.model.json.DataGrid;
import com.jxgis.ffireserver.core.util.MyBeanUtils;
import com.jxgis.ffireserver.core.util.ReturnBody;
import com.jxgis.ffireserver.core.util.ReturnDataGrid;
import com.jxgis.ffireserver.core.util.StringUtil;
import com.jxgis.ffireserver.web.service.TSIconService;
import com.jxgis.ffireserver.util.Glossary;
import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by YanWei on 2017/6/23.
 *
 */
@Service("iconService")
@Transactional
public class TSIconServiceImpl extends SystemServiceImpl implements TSIconService {

	@Override
	public ReturnBody addIcon(TSIcon icon) throws Exception {

		ReturnBody body = new ReturnBody();

		if (StringUtil.isEmpty(icon.getIconName())) {
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg("Parameter error [iconName], must not be empty.");
			return body;
		}

		if (StringUtil.isEmpty(icon.getIconClass())) {
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg("Parameter error [iconClass], must not be empty.");
			return body;
		}

		this.save(icon);
		return body;
	}

	@Override
	public ReturnBody delIcon(TSIcon icon) throws Exception {

		ReturnBody body = new ReturnBody();

		icon = this.get(TSIcon.class, icon.getId());
		if (StringUtil.isNotEmpty(icon)) {
			this.delete(icon);
			return body;
		}
		body.setStatus(Glossary.Return_State_Exception_Operation);
		body.setMsg("Data operation exception, icon does not exist.");
		return body;
	}

	@Override
	public ReturnBody updIcon(TSIcon icon) throws Exception {
		ReturnBody body = new ReturnBody();

		TSIcon icon_db = this.get(TSIcon.class, icon.getId());
		MyBeanUtils.copyBeanNotNull2Bean(icon, icon_db);
		this.updateEntitie(icon_db);

		return body;
	}

	@Override
	public ReturnDataGrid iconLists(TSIcon icon, DataGrid dataGrid) throws Exception {

		ReturnDataGrid out = new ReturnDataGrid();

		Criteria criteria = this.getSession().createCriteria(TSIcon.class);


		Disjunction dis = Restrictions.disjunction();

		if ( !StringUtil.isEmpty(icon.getIconClass())){
			dis.add( Restrictions.like("iconClass", "%" + icon.getIconClass() + "%"));
		}
		if (!StringUtil.isEmpty(icon.getIconName())){
			dis.add( Restrictions.like("iconName", "%" + icon.getIconName() + "%") );
		}
		criteria.add(dis);

		if ( !StringUtil.isEmpty(dataGrid.getSort())){
			if (dataGrid.getOrder().name().equals("asc")) {

				criteria.addOrder( Order.asc(dataGrid.getSort()) );

			} else {

				criteria.addOrder( Order.desc(dataGrid.getSort()) );

			}

		}

		out.setTotal(criteria.list().size());
		out.setPage(dataGrid.getPage());
		criteria.setMaxResults(dataGrid.getRows());
		criteria.setFirstResult( (dataGrid.getPage()-1) * dataGrid.getRows());
		out.setRows(criteria.list());
		return out;
	}
}
