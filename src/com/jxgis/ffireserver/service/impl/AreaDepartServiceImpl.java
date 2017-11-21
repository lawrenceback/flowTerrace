package com.jxgis.ffireserver.service.impl;

import com.jxgis.ffireserver.bean.FFPArea;
import com.jxgis.ffireserver.bean.FFPAreaDepart;
import com.jxgis.ffireserver.core.common.model.json.DataGrid;
import com.jxgis.ffireserver.core.util.ReturnBody;
import com.jxgis.ffireserver.core.util.ReturnDataGrid;
import com.jxgis.ffireserver.core.util.StringUtil;
import com.jxgis.ffireserver.core.util.oConvertUtils;
import com.jxgis.ffireserver.service.AreaDepartService;
import com.jxgis.ffireserver.util.FormatPac;
import com.jxgis.ffireserver.util.Glossary;
import com.jxgis.ffireserver.web.bean.TSDepart;
import com.jxgis.ffireserver.web.bean.TSDepartUser;
import com.jxgis.ffireserver.web.service.impl.SystemServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by YanWei on 2017/9/6.
 *
 * 行政单位 业务层 实现类
 *
 */
@Service("areaDepartService")
@Transactional
public class AreaDepartServiceImpl extends SystemServiceImpl implements AreaDepartService {

	@Override
	public ReturnBody addAreaDepart(FFPAreaDepart areaDepart) throws Exception {
		ReturnBody body =  new ReturnBody();

		if (!StringUtil.isNotEmpty(areaDepart)){
			body.setStatus(Glossary.Return_State_Parameter_Error);
			body.setMsg("parameter error.");
			return body;
		}
		TSDepart depart = areaDepart.getDepart();
		if (!StringUtil.isNotEmpty(depart)){
			body.setStatus(Glossary.Return_State_Parameter_Error);
			body.setMsg("parameter error [depart].");
			return body;
		}

		if (StringUtil.isEmpty(depart.getDepartName())){
			body.setStatus(Glossary.Return_State_Parameter_Error);
			body.setMsg("parameter error [departName].");
			return body;
		}

		FFPArea area = areaDepart.getArea();
		if (!StringUtil.isNotEmpty(area)){
			body.setStatus(Glossary.Return_State_Parameter_Error);
			body.setMsg("parameter error [area].");
			return body;
		}
		FFPArea area_db = get(FFPArea.class, area.getCode());
		if (!StringUtil.isNotEmpty(area_db)){
			body.setStatus(Glossary.Return_State_Parameter_Occupy);
			body.setMsg("Parameter error [code].");
			return body;
		}

		save(depart);
		areaDepart.setArea(area_db);
		areaDepart.setDepart(depart);
		save(areaDepart);

		return body;
	}

	@Override
	public ReturnBody editAreaDepart(FFPAreaDepart areaDepart) throws Exception {
		ReturnBody body = new ReturnBody();
		if (!StringUtil.isNotEmpty(areaDepart)){
			body.setStatus(Glossary.Return_State_Parameter_Error);
			body.setMsg("parameter error.");
			return body;
		}

		TSDepart depart = areaDepart.getDepart();
		TSDepart depart_db = null;


		if (StringUtil.isNotEmpty(depart)){

			depart_db = get(TSDepart.class, oConvertUtils.getString(depart.getId()));
			if (StringUtil.isNotEmpty(depart_db)){

				if (!StringUtil.isEmpty(depart.getDepartName())){
					depart_db.setDepartName(depart.getDepartName());
				}
				if (!StringUtil.isEmpty(depart.getDescription())){
					depart_db.setDescription(depart.getDescription());
				}

			}

			updateEntitie(depart_db);
			return body;
		}

		body.setMsg("parameter error [depart.id].");
		return body;
	}

	@Override
	public ReturnBody deleteAreaDepart(FFPAreaDepart areaDepart, int type) throws Exception {
		ReturnBody body = new ReturnBody();
		if (!StringUtil.isNotEmpty(areaDepart)){
			body.setStatus(Glossary.Return_State_Parameter_Error);
			body.setMsg("parameter error.");
			return body;
		}
		areaDepart = get(FFPAreaDepart.class, oConvertUtils.getString(areaDepart.getId()));
		if (StringUtil.isNotEmpty(areaDepart)){
			TSDepart depart = areaDepart.getDepart();
			List<TSDepartUser> users = findByProperty(TSDepartUser.class, "depart.id", depart.getId());
			if (users.size() > 0){
				body.setStatus(Glossary.Return_State_Data_Linked);
				body.setMsg("The data has been linked.");
				return body;
			}

			delete(depart);
			delete(areaDepart);
			return body;
		}
		body.setStatus(Glossary.Return_State_Parameter_Error);
		body.setMsg("parameter error [id]");
		return body;
	}

	@Override
	public ReturnDataGrid getAreaDepartList(FFPAreaDepart areaDepart, DataGrid dataGrid, int fetchType) throws Exception {
		ReturnDataGrid grid = new ReturnDataGrid();
		grid.setPage(dataGrid.getPage());

		if (!StringUtil.isNotEmpty(areaDepart)){
			grid.setStatus(Glossary.Return_State_Parameter_Error);
			grid.setMsg("parameter error.");
			return grid;
		}

		FFPArea area = get(FFPArea.class, oConvertUtils.getString(areaDepart.getArea().getCode()));
		if (!StringUtil.isNotEmpty(area)){
			grid.setStatus(Glossary.Return_State_Parameter_Error);
			grid.setMsg("parameter error [pac]");
			return grid;
		}


		String sql_select = "SELECT a.id AS area_id, b.*, c.`name`,c.`code` ";
		String sql = " FROM ffp_area_depart a LEFT JOIN t_s_depart b ON a.depart_id = b.id LEFT JOIN ffp_area_code c ON a.area_id = c.`code` WHERE 1 = 1 ";
		List sql_like = new ArrayList();


		switch (fetchType){
			case 1:
				//只加载下一级
				sql += " AND c.`code` LIKE ? ";
				sql_like.add(FormatPac.formatPacUnderline(area.getCode(),true) );
				break;
			case 2:
				//加载所有下一级，甚用
				sql += " AND c.`code` LIKE ? ";
				sql_like.add( FormatPac.formatPac(area.getCode()) + "%" );
				break;
			case 3:
				//加载所有下一级，最大到县
				sql += " AND c.`code` LIKE ? ";
				sql_like.add( FormatPac.formatPacUnderline(area.getCode()) );
				break;
			default:
				//默认： .eq()
				sql += " AND c.`code` = ? ";
				sql_like.add( area.getCode() );
				break;
		}


		grid.setTotal( getCountForJdbcParam("SELECT COUNT(*) " + sql, sql_like.toArray()) );

		if (!StringUtil.isEmpty(dataGrid.getSort())){
			sql += " ORDER BY " + dataGrid.getSort();

			if (dataGrid.getOrder().toString().toLowerCase().equals("asc")){
				sql += " ASC,sorting asc ";
			} else {
				sql += " DESC,sorting asc ";
			}
		} else {
			sql += " ORDER BY sorting asc";
		}


		List<Map<String, Object>> list = findForJdbcParam(sql_select + sql,dataGrid.getPage(), dataGrid.getRows(), sql_like.toArray());
		for (Map<String, Object> map: list){

			//统计该部门下有多少用户
			map.put("numberUsers", getCountForJdbcParam("SELECT COUNT(*) FROM t_s_depart_user WHERE depart_id=?", new Object[]{map.get("id")}));

		}
		grid.setRows(list);
		return grid;
	}
}
