package com.jxgis.ffireserver.web.service.impl;

import com.jxgis.ffireserver.bean.FFPAreaDepart;
import com.jxgis.ffireserver.core.util.MyBeanUtils;
import com.jxgis.ffireserver.web.bean.TSDepart;
import com.jxgis.ffireserver.web.bean.TSDepartUser;
import com.jxgis.ffireserver.web.bean.TSUser;
import com.jxgis.ffireserver.core.common.model.json.DataGrid;
import com.jxgis.ffireserver.core.util.ReturnBody;
import com.jxgis.ffireserver.core.util.StringUtil;
import com.jxgis.ffireserver.core.util.oConvertUtils;
import com.jxgis.ffireserver.util.FormatPac;
import com.jxgis.ffireserver.util.Glossary;
import com.jxgis.ffireserver.web.service.TSDepartService;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by YanWei on 2017/7/3.
 *
 *
 */
@Service("departService")
@Transactional
public class TSDepartServiceImpl extends SystemServiceImpl implements TSDepartService{

	@Override
	public ReturnBody addOrUpdateDepart(TSDepart depart) throws Exception {
		ReturnBody body = new ReturnBody();

		if (StringUtil.isEmpty(depart.getDepartName())){
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg("Parameter [departName] must not be null.");
			return body;
		}

		if (StringUtil.isEmpty(depart.getOrgCode())){
			body.setStatus(Glossary.Return_State_Parameter_Null);
			body.setMsg("Parameter [orgCode] must not be null.");
			return body;
		}
		if (StringUtil.isEmpty(depart.getId())){
			save(depart);
		} else {
			updateEntitie(depart);
		}

		return body;
	}

	@Override
	public ReturnBody deleteDepart(TSDepart depart) throws Exception {
		ReturnBody body = new ReturnBody();
		depart = get(TSDepart.class, depart.getId());
		if (StringUtil.isNotEmpty(depart)){
			delete(depart);
			return body;
		}
		body.setStatus(Glossary.Return_State_Data_Not_Exist);
		body.setMsg("Data does not exist.");
		return body;
	}

	@Override
	public ReturnBody editDepart(TSDepart depart) throws Exception {
		ReturnBody body = new ReturnBody();
		if (StringUtil.isNotEmpty(depart)){
			TSDepart depart_db = get(TSDepart.class, oConvertUtils.getString(depart.getId()));

			if (StringUtil.isNotEmpty(depart_db)){
				MyBeanUtils.copyBeanNotNull2Bean(depart, depart_db);
				updateEntitie(depart_db);
				return body;
			}
		}
		body.setStatus(Glossary.Return_State_Parameter_Error);
		body.setMsg("parameter error [id]");
		return body;
	}

	@Override
	public List<Map<String, Object>> getDepartTree(TSDepart depart, DataGrid dataGrid, int fetchType) throws Exception {

		depart = get(TSDepart.class, oConvertUtils.getString(depart.getId()));

		List<Map<String, Object>> lists = null;
		if (StringUtil.isNotEmpty(depart)){

			//子部门
			lists = findForJdbc("SELECT * FROM t_s_depart WHERE org_code LIKE ?", depart.getOrgCode() + "__");
		} else {
			lists = findForJdbc("SELECT * FROM t_s_depart WHERE org_code LIKE '00__'");
		}

		//查询是否还有子部门
		for (Map<String, Object> mp: lists) {
			long count = this.getCountForJdbcParam("SELECT COUNT(*) FROM t_s_depart WHERE org_code LIKE ?", new Object[]{mp.get("org_code") + "__"});

			if (count > 0) {
				mp.put("state", "closed");
			} else {
				mp.put("state", "open");
			}
		}


		return lists;
	}

	@Override
	public List<Map<String, Object>> getDepartPac(FFPAreaDepart areaDepart, DataGrid dataGrid, int fetchType) throws Exception {

		return null;
	}

	@Override
	public ReturnBody getDepartOrgCode(TSDepart depart) throws Exception {
		ReturnBody body = new ReturnBody();
		if (StringUtil.isEmpty(oConvertUtils.getString(depart.getOrgCode()))){
			depart.setOrgCode("00");
		}

		String orgCode = "";
		try{

			Map<String, Object> obj = findOneForJdbc("SELECT * FROM t_s_depart WHERE org_code LIKE ? ORDER BY org_code DESC limit 1", depart.getOrgCode() + "__");

			orgCode = obj.get("org_code").toString();
			int code = Integer.parseInt(orgCode.substring(orgCode.length()-2,orgCode.length())) + 1;
			String code_ = code + "";
			if (code_.length() < 2){
				code_ = "0" + code_;
			}
			orgCode = orgCode.substring(0,orgCode.length()-2) + code_;

		} catch (EmptyResultDataAccessException e){
			orgCode = depart.getOrgCode() + "01";
		}




		body.setObj(orgCode);
		return body;
	}

	@Override
	public ReturnBody getDeppartParent(TSDepart depart) throws Exception {
		ReturnBody body = new ReturnBody();
		String orgCode = depart.getOrgCode();
		if (orgCode.length() <= 4) {
			//没有父菜单
			body.setStatus(Glossary.Return_State_Data_Not_Exist);
			body.setMsg("Data does not exist.");
			return body;
		}

		depart.setOrgCode( orgCode.substring(0,orgCode.length()-2) );
		Map<String, Object> obj = findOneForJdbc("SELECT * FROM t_s_depart WHERE org_code = ?", depart.getOrgCode());
		body.setObj(obj);
		return body;
	}

	@Override
	public Map<String, Object> getDeparUsers(TSDepartUser departUser, DataGrid dataGrid, int fetchType) throws Exception {
		Criteria criteria = this.getSession().createCriteria(TSDepartUser.class,"du");
		Map<String, Object> out = new HashMap<>();

		criteria.setMaxResults(dataGrid.getRows());
		criteria.setFirstResult( (dataGrid.getPage()-1) * dataGrid.getRows());

		criteria.add( Restrictions.eq("depart.id", oConvertUtils.getString(departUser.getDepart().getId())) );

		if (oConvertUtils.isNotEmpty(departUser) && oConvertUtils.isNotEmpty(departUser.getUser())){

			TSUser user = departUser.getUser();

			if ( !StringUtil.isEmpty(oConvertUtils.getString(departUser.getUser().getPac())) ){
				switch (fetchType){
					case 1:
						//查询当前及下一级
						criteria.add( Restrictions.like("user.pac", FormatPac.formatPacUnderline(user.getPac(), true)) );
						break;
					case 2:
						//查询所有下一级
						criteria.add( Restrictions.like("user.pac", FormatPac.formatPac(user.getPac()) + "%") );
						break;
					case 3:
						//只查询下一级，最大到县
						criteria.add( Restrictions.like("user.pac", FormatPac.formatPacUnderline(user.getPac())) );
						break;
					default:
						//默认 .eq()
						criteria.add( Restrictions.eq("user.pac", user.getPac() ) );
						break;
				}
			}
		}

		if ( !StringUtil.isEmpty(dataGrid.getSort())){
			if (dataGrid.getOrder().name().equals("asc")) {

				criteria.addOrder( Order.asc(dataGrid.getSort()) );

			} else {

				criteria.addOrder( Order.desc(dataGrid.getSort()) );

			}

		}

		ProjectionList proList = Projections.projectionList();//设置投影集合
		proList.add(Projections.groupProperty( "user"));
		criteria.setProjection(proList);

		List list = criteria.list();
		out.put("page", dataGrid.getPage());
		out.put("total", list.size());
		out.put("rows", list);
		return out;
	}
}
