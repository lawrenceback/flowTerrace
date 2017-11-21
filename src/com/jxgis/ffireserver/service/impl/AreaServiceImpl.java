package com.jxgis.ffireserver.service.impl;

import com.jxgis.ffireserver.bean.FFPArea;
import com.jxgis.ffireserver.bean.FFPAreaBoundary;
import com.jxgis.ffireserver.core.common.model.json.DataGrid;
import com.jxgis.ffireserver.core.util.*;
import com.jxgis.ffireserver.service.AreaService;
import com.jxgis.ffireserver.util.FormatPac;
import com.jxgis.ffireserver.util.Glossary;
import com.jxgis.ffireserver.web.service.impl.SystemServiceImpl;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by YanWei on 2017/6/14.
 *
 * 地区业务层
 */
@Service("areaService")
@Transactional
public class AreaServiceImpl extends SystemServiceImpl implements AreaService {

	Transaction transaction = null;
	Session session = null;
	@Override
	public ReturnBody areas(String code) throws Exception {
		ReturnBody body = new ReturnBody();
		int codeLength = code.length();

		List<Map<String,Object>> list = new ArrayList<>();

		//节点状态
		Map<String, Object> state = new HashMap<>();

		switch (codeLength){
			case 2:
				//获得市
				list = this.findForJdbc("SELECT `code` id,`code` `code`,`name` text FROM ffp_area_code WHERE `code` LIKE ?",code + "__00");
				for(Map<String,Object> map: list){

					long rows = this.getCountForJdbcParam("SELECT COUNT(*) FROM ffp_area_code WHERE `code` LIKE ?",new Object[]{map.get("code").toString().substring(0,4) + "__"});
					if (rows > 0){
						map.put("children",true);
					}
					map.put("icon", "iconfont icon-weizhi");
				}


				break;
			case 4:
				//获得区/县
				list = this.findForJdbc("SELECT `code` id,`code` `code`,`name` text FROM ffp_area_code WHERE `code` LIKE ?",code + "__");

				for(Map<String,Object> map: list){

					map.put("icon", "iconfont icon-weizhi");
				}
				break;
			default:
				//获得所有省
				list = this.findForJdbc("SELECT `code` id,`code` `code`,`name` text FROM ffp_area_code WHERE `code` LIKE ?","__0000");

				for(Map<String,Object> map: list){
					long rows = this.getCountForJdbcParam("SELECT COUNT(*) FROM ffp_area_code WHERE `code` LIKE ?",new Object[]{map.get("code").toString().substring(0,2) + "__00"});
					if (rows > 0){
						map.put("children",true);
					}
					map.put("icon", "iconfont icon-weizhi");
				}
				break;
		}
		Map<String, Object> obj = list.get(0);

		list.remove(0);
		obj.put("children", list);

		state.put("opened", true);
		obj.put("state", state);
		body.setObj(obj);
		return body;
	}

	@Override
	public ReturnBody areasAll(String code) throws Exception {
		ReturnBody body = new ReturnBody();

		List<Map<String,Object>> list = this.findForJdbc("SELECT `code` id,`code` `code`,`name` text FROM ffp_area_code WHERE `code` LIKE ?", FormatPac.formatPacUnderline(code, true));

		for(Map<String,Object> map: list){
			long rows = this.getCountForJdbcParam("SELECT COUNT(*) FROM ffp_area_code WHERE `code` LIKE ?",new Object[]{ FormatPac.formatPacUnderline( map.get("code").toString(), false)});
			if (rows > 0){
				map.put("children",true);
			}
			map.put("icon", "iconfont icon-weizhi");
		}
		if (list.size() > 0){

			//节点状态
			Map<String, Object> state = new HashMap<>();

			Map<String, Object> obj = list.get(0);

			list.remove(0);

			obj.put("children", list);

			state.put("opened", true);
			obj.put("state", state);
			body.setObj(obj);
			return body;
		}

		body.setStatus(Glossary.Return_State_Data_Not_Exist);
		body.setMsg("Data does not exist.");
		return body;
	}


	@Override
	public ReturnDataGrid getPac(String pac, DataGrid dataGrid, int fetchType, int fetchBoundary) throws Exception {

		ReturnDataGrid body = new ReturnDataGrid();

		Criteria criteria = this.getSession().createCriteria(FFPArea.class);

		switch (fetchType){
			case 1:
				//只加载下一级
				criteria.add( Restrictions.like("code", FormatPac.formatPacUnderline(pac,true) ) );
				break;
			case 2:
				//加载所有下级，甚用
				criteria.add( Restrictions.like("code", FormatPac.formatPac(pac) + "%" ) );
				break;
			case 3:
				//加载所有下一级，最大到县
				criteria.add( Restrictions.like("code", FormatPac.formatPacUnderline(pac) ) );
				break;
			default:
				//默认： .eq()
				criteria.add( Restrictions.eq("code", pac ) );
				break;
		}


		List<FFPArea> list = criteria.list();
		criteria.addOrder( Order.asc( "code" ) );
		body.setRows(list);
		return body;
	}

	@Override
	public ReturnBody addArea(FFPArea area) throws Exception {
		ReturnBody body = new ReturnBody();
		if (StringUtil.isNotEmpty(area)){
			if (StringUtil.isEmpty(area.getName())){
				body.setStatus(Glossary.Return_State_Parameter_Error);
				body.setMsg("parameter error [name]");
				return body;
			}
			if (StringUtil.isEmpty(area.getCode())){
				body.setStatus(Glossary.Return_State_Parameter_Error);
				body.setMsg("parameter error [code]");
				return body;
			}



			List<Map<String, Object>> list = findForJdbc("SELECT * FROM ffp_area_code WHERE `code` != ? and `code` LIKE ? ORDER BY `code` DESC LIMIT 1", area.getCode(), FormatPac.formatPacUnderline(area.getCode(), false));
			if (list.size() > 0){
				Map<String, Object> map = list.get(0);
				if (StringUtil.isNotEmpty(map) && StringUtil.isNotEmpty(map.get("code"))){

					String code = map.get("code").toString();
					code = FormatPac.formatPac(oConvertUtils.getString(code));

					code = oConvertUtils.getString(oConvertUtils.getInt(code)+1);
					area.setCode(FormatPac.repairPac(code));
					save(area);
					return body;
				}

			} else {
				//没有下一级的情况
				String code = area.getCode();

				if (code.substring(code.length()-2, code.length()).equals("00")){
					code = oConvertUtils.getString(oConvertUtils.getInt(code)+1);
					area.setCode(code);
					save(area);
					return body;
				}


				//判断添加哪一级【省、市、县、乡镇、村】
				code = FormatPac.formatPac(area.getCode());
				switch (code.length()){
					case 0:
						//省
						break;
					case 2:
						//市
						code += "00";
						break;
					case 4:
						//区/县
						code += "00";
						break;
					case 6:
						//乡镇
						code += "000";
						break;
					case 9:
						//村
						code += "000";
						break;
					case 12:
						code += "000";
						break;
					case 15:
						code += "000";
						break;
				}

				area.setCode(code);
				save(area);
				return body;
			}
			body.setStatus(Glossary.Return_State_Parameter_Error);
			body.setMsg("Parameter mismatch.");
			return body;
		}
		body.setStatus(Glossary.Return_State_Parameter_Error);
		body.setMsg("parameter error.");
		return body;
	}

	@Override
	public ReturnBody addAreaSimply(FFPAreaBoundary area) throws Exception {
		ReturnBody body = new ReturnBody();
		if (!StringUtil.isNotEmpty(area)){
			body.setStatus(Glossary.Return_State_Parameter_Error);
			body.setMsg("parameter error.");
			return body;
		}
		if (StringUtil.isEmpty(area.getName())){
			body.setStatus(Glossary.Return_State_Parameter_Error);
			body.setMsg("parameter error[name]");
			return body;
		}
		if (StringUtil.isEmpty(area.getCode())){
			body.setStatus(Glossary.Return_State_Parameter_Error);
			body.setMsg("parameter error[code]");
			return body;
		}

		FFPAreaBoundary boundary = get(FFPAreaBoundary.class, oConvertUtils.getString(area.getCode()));
		if (StringUtil.isNotEmpty(boundary)){
			body.setStatus(Glossary.Return_State_Data_Exist);
			body.setMsg("Data occupied.");
			return body;
		}
		save(area);
		body.setObj(area.getCode());
		return body;
	}

	@Override
	public ReturnBody editArea(FFPAreaBoundary area) throws Exception {
		ReturnBody body = new ReturnBody();
		if (!StringUtil.isNotEmpty(area)){
			body.setStatus(Glossary.Return_State_Geometry_Format_Error);
			body.setMsg( "Parameter error.");
			return body;
		}

		try{
			WKTReader fromText = new WKTReader();
			fromText.read(area.getBoundary());
		} catch (ParseException e) {
			body.setStatus(Glossary.Return_State_Geometry_Format_Error);
			body.setMsg( "Parameter error [boundary].");
			return body;
		}

		FFPAreaBoundary boundary = get(FFPAreaBoundary.class, oConvertUtils.getString(area.getCode()));
		if (StringUtil.isNotEmpty(boundary)){
			MyBeanUtils.copyBeanNotNull2Bean(area, boundary);
			if (!StringUtil.isEmpty(area.getBoundary())){
				executeSql("UPDATE ffp_area_code_boundary SET boundary=POINTFROMTEXT('"+boundary.getBoundary()+"') WHERE code=?", boundary.getCode());
			}
			updateEntitie(boundary);
			return body;
		}

		FFPArea area_db = get(FFPArea.class, oConvertUtils.getString(area.getCode()));
		if (!StringUtil.isNotEmpty(area_db)){
			body.setStatus(Glossary.Return_State_Geometry_Format_Error);
			body.setMsg( "Parameter error [code].");
			return body;
		}

		MyBeanUtils.copyBeanNotNull2Bean(area, area_db);
		if (!StringUtil.isEmpty(area.getBoundary())){
			executeSql("INSERT INTO ffp_area_code_boundary (`code`, `name`, boundary) VALUES (?, ? ,POINTFROMTEXT('"+area.getBoundary()+"') )", area.getCode(), area.getName());
		}
		updateEntitie(area_db);
		return body;
	}

	@Override
	public ReturnBody deleteArea(FFPAreaBoundary boundary) throws Exception {
		ReturnBody body = new ReturnBody();
		if (StringUtil.isNotEmpty(boundary)){
			FFPAreaBoundary boundary_db = get(FFPAreaBoundary.class, oConvertUtils.getString(boundary.getCode()));
			if (StringUtil.isNotEmpty(boundary_db)){

				long rows = getCountForJdbcParam("SELECT COUNT(*) FROM ffp_area_code_boundary WHERE `code` LIKE ?", new Object[]{boundary_db.getCode() + "%"});
				if (rows > 1){
					body.setStatus(Glossary.Return_State_Error);
					body.setMsg("data association.");
					return body;
				}
				delete(boundary_db);
				return body;
			}

			FFPArea area = get(FFPArea.class, oConvertUtils.getString(boundary.getCode()));
			if (StringUtil.isNotEmpty(area)){

				long rows = getCountForJdbcParam("SELECT COUNT(*) FROM ffp_area_code WHERE `code` LIKE ?", new Object[]{area.getCode() + "%"});
				if (rows > 1){
					body.setStatus(Glossary.Return_State_Error);
					body.setMsg("data association.");
					return body;
				}
				delete(area);
				return body;
			}
		}
		body.setStatus(Glossary.Return_State_Parameter_Error);
		body.setMsg("parameter error");
		return body;
	}

	@Override
	public ReturnBody getArea(String code, int format_boundary_type) throws Exception {
		ReturnBody body = new ReturnBody();

		FFPAreaBoundary boundary = get(FFPAreaBoundary.class, oConvertUtils.getString(code));
		if (StringUtil.isNotEmpty(boundary)){
			Object shape = null;
			switch (format_boundary_type){
				case 1:
					shape = findOneForJdbc("select ST_AsGeoJSON(ST_GeomFromText( ASTEXT(boundary) ) ) boundary FROM ffp_area_code_boundary where code=?", code).get("boundary");
					break;
				default:
					shape = findOneForJdbc("select ASTEXT(boundary) boundary FROM ffp_area_code_boundary where code=?", code).get("boundary");
					break;
			}
			if (StringUtil.isNotEmpty(shape)){
				boundary.setBoundary(shape.toString());
			}
			body.setObj(boundary);
			return body;
		}

		FFPArea area = get(FFPArea.class, oConvertUtils.getString(code));
		if (!StringUtil.isNotEmpty(area)){
			body.setStatus(Glossary.Return_State_Parameter_Error);
			body.setMsg("parameter error [code]");
			return body;
		}
		body.setObj(area);
		return body;
	}


	@Override
	public ReturnBody peggingPac(String code, HttpServletRequest req) throws Exception {
		ReturnBody body = new ReturnBody();
		FFPArea area = get(FFPArea.class, oConvertUtils.getString(code));
		if (!StringUtil.isNotEmpty(area)){
			body.setStatus(Glossary.Return_State_Parameter_Error);
			body.setMsg("Parameter error [code]");
			return body;
		}
		List<Map<String, Object>> areaList = new ArrayList<>();
		req.getSession().setAttribute("areaList", areaList);
		String pac = getAreaMap(area.getCode(), req);
		areaList = (List<Map<String, Object>>) req.getSession().getAttribute("areaList");
		if (pac == null){
			req.getSession().removeAttribute("areaList");
		}
		body.setObj(areaList);
		return body;
	}

	private String getAreaMap(String code, HttpServletRequest req) throws Exception{
		List<Map<String, Object>> areaList = (List<Map<String, Object>>) req.getSession().getAttribute("areaList");
		String code_db = FormatPac.formatPac(code);
		switch (code_db.length()){
			case 0: code_db += "000000";break;
			case 2: code_db += "0000";break;
			case 4: code_db += "00";break;
		}
		areaList.add(0, findOneForJdbc("SELECT * FROM ffp_area_code WHERE `code`=?", code_db));

		req.getSession().setAttribute("areaList", areaList);

		code_db = FormatPac.formatPac(code);
		switch (code_db.length()){
			case 2: code = "";break;
			case 4: code = code_db.substring(0,2);break;
			case 6: code = code_db.substring(0,4);break;
			case 9: code = code_db.substring(0,6);break;
			case 12: code = code_db.substring(0,9);break;
			case 15: code = code_db.substring(0,12);break;
			case 18: code = code_db.substring(0,15);break;
			default: return null;
		}

		return getAreaMap(FormatPac.repairPac(code), req);
	}


	public static void main(String[] args){
		String code = "451600";
		System.out.println(code.substring(code.length()-2, code.length()).equals("00"));
	}
}
