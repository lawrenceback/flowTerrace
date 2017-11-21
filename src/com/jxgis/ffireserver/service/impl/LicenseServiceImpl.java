package com.jxgis.ffireserver.service.impl;

import com.jxgis.ffireserver.bean.FFPLicense;
import com.jxgis.ffireserver.core.common.model.json.DataGrid;
import com.jxgis.ffireserver.core.util.*;
import com.jxgis.ffireserver.service.LicenseService;
import com.jxgis.ffireserver.util.Constants;
import com.jxgis.ffireserver.util.FormatPac;
import com.jxgis.ffireserver.util.Glossary;
import com.jxgis.ffireserver.web.bean.TSUser;
import com.jxgis.ffireserver.web.service.impl.SystemServiceImpl;
import com.jxgis.ffireserver.web.system.manager.ClientManager;
import com.jxgis.ffireserver.web.system.pojo.base.Client;
import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by YanWei on 2017/6/12.
 *
 */
@Service("licenseiService")
@Transactional
public class LicenseServiceImpl extends SystemServiceImpl implements LicenseService {

	@Override
	public ReturnBody isExistLicense(String imei) throws Exception {
		ReturnBody body = new ReturnBody();
		FFPLicense license_db = this.findUniqueByProperty(FFPLicense.class, "imei", imei);
		if (!StringUtil.isNotEmpty(license_db)){
			//imei不存在时
			body.setStatus(Constants.Exist_No_Imei);
			body.setMsg("Parameter error [imei],The [imei] is not registered");
			return body;
		}

		if (license_db.getStatus() == 2) {
			//许可类型为试用
			//判断试用期是否结束
			if (license_db.getExpTime().getTime() < System.currentTimeMillis()){
				body.setStatus( Constants.Imei_Overdue );
				body.setMsg("IMEI has expired.");
				return body;
			}
		} else if (license_db.getStatus() == 3) {
			//imei已注销
			body.setStatus( Constants.Imei_Invalid );
			body.setMsg("IMEI cancelled.");
			return body;
		}


		return body;
	}

	@Override
	public ReturnBody insertLicense(FFPLicense license) throws Exception {

		ReturnBody body = new ReturnBody();

		HttpSession session = ContextHolderUtils.getSession();
		Client clientOld = ClientManager.getInstance().getClient(session.getId());


		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.MONTH, 2);//有效期两个月

		license.setKey(key(RandomUtil.generateString(6)).toUpperCase());
		license.setStatus(2);
		license.setFounder(clientOld.getUser().getAccount());
		license.setExpTime(calendar.getTime());

		if (StringUtil.isEmpty(license.getPac())){
			body.setStatus(Glossary.Return_State_Parameter_Error);
			body.setMsg("参数错误[pac]");
			return body;
		}

		if (license.getDeviceType() < 1 || license.getDeviceType() > 2){
			body.setStatus(Glossary.Return_State_Parameter_Error);
			body.setMsg("Parameter error [deviceType].");
			return body;
		}


		this.save(license);

		body.setMsg("添加成功");
		Map<String, Object> map = new HashMap<>();
		map.put("id", license.getId());
		body.setObj(map);
		return body;
	}

	@Override
	public ReturnBody insertLicenseExternal(FFPLicense license, TSUser user, int number) throws Exception {
		ReturnBody body = new ReturnBody();

		user = this.findUniqueByProperty(TSUser.class, "account", user.getAccount());
		if ( !StringUtil.isNotEmpty(user) ) {
			body.setStatus(Glossary.Return_State_User_Not_Exist);
			body.setMsg("Parameter error [account] ,user does not exist.");
			return body;
		}


		if (StringUtil.isEmpty(license.getPac())){
			body.setStatus(Constants.Imei_Pac_Exist_No);
			body.setMsg("Parameter error [pac] ,Must not be empty.");
			return body;
		}

		if (license.getDeviceType() < 1 || license.getDeviceType() > 2){
			body.setStatus(Glossary.Return_State_Parameter_Error);
			body.setMsg("Parameter error [deviceType].");
			return body;
		}

		Date expDate = null;
		if(license.getStatus() == 2){
			//试许可
			if ( !StringUtil.isNotEmpty(license.getExpTime())){
				//当用户没有填写截止日期
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(new Date());
				calendar.add(Calendar.MONTH, 2);//有效期两个月
				expDate = calendar.getTime();
			}
		}




		List<FFPLicense> licenses = new ArrayList<>();
		for (int i=0; i<number; i++) {
			FFPLicense license_new = new FFPLicense();
			license_new.setKey(key(RandomUtil.generateString(6)).toUpperCase());
			license_new.setStatus(license.getStatus());
			license_new.setFounder( user.getAccount() );
			license_new.setPac(license.getPac());
			if (StringUtil.isNotEmpty(license.getExpTime())){
				license_new.setExpTime(license.getExpTime());
			} else {
				license_new.setExpTime(expDate);
			}

			license_new.setDeviceType(license.getDeviceType());
			license_new.setDevice(license.getDevice());
			licenses.add(license_new);
		}

		this.batchSave(licenses);

		List<Map<String,Object>> obj = new ArrayList<>();

		for (FFPLicense license1: licenses){
			Map<String,Object> map = new HashMap<>();
			map.put("id", license1.getId());
			map.put("key", license1.getKey());
			obj.add(map);
		}


		body.setObj(obj);

		return body;
	}

	//查询该key(授权码)是否存在，如果存在递归再次获得新的key,直到key不存在
	public String key(String key){
		FFPLicense imei = this.findUniqueByProperty(FFPLicense.class, "key", key);
		if(StringUtil.isNotEmpty(imei)){
			key( RandomUtil.generateString(6).toUpperCase() );
		}
		return key;
	}

	@Override
	public ReturnBody removeLicense(String key, String imei) throws Exception {
		FFPLicense license_db = this.findUniqueByProperty(FFPLicense.class, "key", key);
		ReturnBody body = new ReturnBody();
		if(StringUtil.isNotEmpty(license_db)){

			if(!StringUtil.isEmpty(license_db.getImei()) && license_db.getImei().equals(imei)){
				license_db.setImei(null);
				license_db.setDevice(0);
				this.updateEntitie(license_db);
				return body;
			}
			body.setStatus(Constants.Exist_No_Imei);
			body.setMsg("Parameter error [imei], must not be null.");
			return body;
		}
		body.setStatus(Constants.Imei_Key_Exist_No);
		body.setMsg("Parameter error [key], must not be null.");
		return body;
	}

	@Override
	public ReturnBody updateLicense(FFPLicense license) throws Exception {
		FFPLicense license_db = this.get(FFPLicense.class, license.getId());
		ReturnBody body = new ReturnBody();
		if (StringUtil.isNotEmpty(license_db)){
			MyBeanUtils.copyBeanNotNull2Bean(license, license_db);
			if (license_db.getStatus() ==1){
			    license_db.setExpTime(null);
            }
			this.updateEntitie(license_db);
			return body;
		}
		body.setStatus(Glossary.Return_State_Parameter_Error);
		body.setMsg("数据不存在");
		return body;
	}

	@Override
	public ReturnBody deleteLicense(int id) throws Exception {
		FFPLicense imei = this.get(FFPLicense.class, id);
		ReturnBody body = new ReturnBody();
		if (StringUtil.isNotEmpty(imei)){
			this.delete(imei);
			body.setMsg("删除成功");
			return body;
		}
		body.setStatus(Glossary.Return_State_Parameter_Error);
		body.setMsg("数据不存在");
		return body;
	}

	@Override
	public ReturnBody deleteLicenses(List<FFPLicense> licenses) throws Exception {
		ReturnBody body = new ReturnBody();
		if (!StringUtil.isNotEmpty(licenses)){
			body.setStatus(Glossary.Return_State_Parameter_Error);
			body.setMsg("parameter error.");
			return body;
		}
		deleteAllEntitie(licenses);
		return body;
	}

	@Override
	public ReturnBody registLicense(FFPLicense license) throws Exception {
		ReturnBody body = new ReturnBody();


		FFPLicense license_db = findUniqueByProperty(FFPLicense.class, "key", license.getKey());
		if(StringUtil.isNotEmpty(license_db)) {

			switch (license_db.getDeviceType()) {
				case 1:
					//移动设备
					if (license.getDevice() < 1 || license.getDevice() > 2) {
						body.setStatus(Glossary.Return_State_Exception_Operation);
						body.setMsg("Bind error [key]. The mobile license code cannot bind PC device, or the parameter is incorrect.");
						return body;
					}
					break;
				case 2:
					//桌面PC终端
					if (license.getDevice() != 3) {
						body.setStatus(Glossary.Return_State_Exception_Operation);
						body.setMsg("Bind error [key]. The PC authorization code cannot bind the mobile device, or the parameter is incorrect.");
						return body;
					}
			}


			//判断key是否被注册
			if( !StringUtil.isEmpty(license_db.getImei()) ){

				if (license_db.getStatus() == 3){
					body.setStatus(Constants.Imei_Invalid);
					body.setMsg("Parameter error [key] canceled.");
					return body;
				}
				body.setStatus(Constants.Imei_Key_Occupy);
				body.setMsg("Parameter error [key] is occupied.");
				return body;
			}

			if (!StringUtil.isEmpty(license.getImei())){
				FFPLicense imei_db_imei = findUniqueByProperty(FFPLicense.class, "imei", license.getImei());
				if (StringUtil.isNotEmpty(imei_db_imei)){
					body.setStatus(Constants.Exist_Imei);
					body.setMsg("[imei] is occupied.");
					return body;
				}
				if (license_db.getStatus()==2 && license_db.getExpTime().getTime() < System.currentTimeMillis()) {
					body.setStatus( Constants.Imei_Overdue );
					body.setMsg("[key] expired.");
					return body;
				}
				license_db.setImei(license.getImei());
				license_db.setDevice(license.getDevice());
				this.updateEntitie(license_db);
				return body;
			}
			body.setStatus(Constants.Exist_No_Imei);
			body.setMsg("Parameter error [imei].");
			return body;
		}
		body.setStatus(Constants.Imei_Key_Exist_No);
		body.setMsg("Parameter error [key].");
		return body;
	}

	@Override
	public ReturnBody insertLicenseNumber(FFPLicense license, int number) throws Exception {
		ReturnBody body = new ReturnBody();

		HttpSession session = ContextHolderUtils.getSession();
		Client clientOld = ClientManager.getInstance().getClient(session.getId());
		if (StringUtil.isEmpty(license.getPac())){
			body.setStatus(Glossary.Return_State_Parameter_Error);
			body.setMsg("参数错误[pac]");
			return body;
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.MONTH, 2);//有效期两个月
		Date expDate = calendar.getTime();

		List<FFPLicense> imeis = new ArrayList<>();
		for (int i=0; i<number; i++) {
			FFPLicense imei_new = new FFPLicense();
			imei_new.setKey(key(RandomUtil.generateString(6)).toUpperCase());
			imei_new.setStatus(2);
			imei_new.setFounder(clientOld.getUser().getName());
			imei_new.setPac(license.getPac());
			imei_new.setExpTime(expDate);
			imeis.add(imei_new);
		}

		this.batchSave(imeis);

		List<Map<String,Object>> obj = new ArrayList<>();

		for (FFPLicense imei1: imeis){
			Map<String,Object> map = new HashMap<>();
			map.put("id", imei1.getId());
			map.put("key", imei1.getKey());
			obj.add(map);
		}

		body.setMsg("添加成功");
		body.setObj(obj);
		return body;
	}

	@Override
	public ReturnDataGrid getLicenseDataGrid(FFPLicense license, DataGrid dataGrid, int fetchType, Date createTime_start, Date createTime_end) throws Exception {
		ReturnDataGrid out = new ReturnDataGrid();

		Criteria criteria = this.getSession().createCriteria(FFPLicense.class);

		Disjunction dis = Restrictions.disjunction();

		if ( !StringUtil.isEmpty(license.getImei())){
			dis.add( Restrictions.like( "imei","%" + license.getImei() + "%") );
		}
		if ( !StringUtil.isEmpty(license.getKey())){
			dis.add( Restrictions.like( "key","%" + license.getKey() + "%") );
		}
		if ( StringUtil.isNotEmpty(createTime_start)){
			dis.add( Restrictions.ge("createTime", createTime_start));
		}
		if ( StringUtil.isNotEmpty(createTime_end)){
			dis.add( Restrictions.le("createTime", createTime_end));
		}
		if (StringUtil.isNotEmpty(license.getStatus()) && license.getStatus() > 0){
			dis.add( Restrictions.eq("status", license.getStatus()));
		}
		if (StringUtil.isNotEmpty(license.getDeviceType()) && license.getDeviceType() > 0){
			dis.add( Restrictions.eq("deviceType", license.getDeviceType()));
		}
		if (StringUtil.isNotEmpty(license.getDevice()) && license.getDevice() > 0){
			dis.add( Restrictions.eq("device", license.getDevice()));
		}
		criteria.add(dis);

		if ( !StringUtil.isEmpty(license.getPac()) ){
			switch (fetchType){
				case 1:
					//查询下一级
					criteria.add( Restrictions.like("pac", FormatPac.formatPacUnderline(license.getPac())) );
					break;
				case 2:
					//查询所有下一级
					criteria.add( Restrictions.like("pac", FormatPac.formatPac(license.getPac()) + "%") );
					break;
				default:
					//默认 .eq()
					criteria.add( Restrictions.eq("pac", license.getPac() ) );
					break;
			}
		}

		if ( !StringUtil.isEmpty(dataGrid.getSort())){
			if (dataGrid.getOrder().name().equals("asc")) {

				criteria.addOrder( Order.asc(dataGrid.getSort()) );

			} else {

				criteria.addOrder( Order.desc(dataGrid.getSort()) );

			}

		}

		out.setTotal(criteria.list().size());
		criteria.setMaxResults(dataGrid.getRows());
		criteria.setFirstResult( (dataGrid.getPage() - 1) * dataGrid.getRows());

		out.setPage(dataGrid.getPage());
		out.setRows(criteria.list());
		return out;
	}

	@Override
	public ReturnDataGrid getLicenseDataGrid(FFPLicense license, DataGrid dataGrid, int fetchType, Date createTime_start, Date createTime_end, int fetchStatus, int license_status) throws Exception {
		ReturnDataGrid out = new ReturnDataGrid();

		Criteria criteria = this.getSession().createCriteria(FFPLicense.class);


		if ( !StringUtil.isEmpty(license.getKey())){
			criteria.add( Restrictions.like( "key","%" + license.getKey() + "%") );
		}

		switch (license_status){
			case 1:
				//可用
				//未过期
				//永久
				criteria.add(Restrictions.or(Restrictions.eq("status", 1),Restrictions.eq("status", 2)));
				criteria.add(Restrictions.or(Restrictions.ge("expTime", new Date()), Restrictions.isNull("expTime")));
				break;
			case 2:
				//不可用
				//已过期
				//作废
				criteria.add(Restrictions.or(Restrictions.eq("status", 2),Restrictions.eq("status", 3)));
				criteria.add(Restrictions.or(Restrictions.le("expTime", new Date()), Restrictions.isNull("expTime")));
				break;
			default:

				if ( StringUtil.isNotEmpty(createTime_start)){
					criteria.add( Restrictions.ge("createTime", createTime_start));
				}
				if ( StringUtil.isNotEmpty(createTime_end)){
					criteria.add( Restrictions.le("createTime", createTime_end));
				}

				break;
		}

		if (StringUtil.isNotEmpty(license.getStatus()) && license.getStatus() > 0){
			criteria.add( Restrictions.eq("status", license.getStatus()));
		}

		switch (fetchStatus){
			case 1:
				//未分配
				criteria.add( Restrictions.isNull("imei"));
				break;
			case 2:
				//已分配
				criteria.add( Restrictions.isNotNull("imei"));
				break;
			default:
				if ( !StringUtil.isEmpty(license.getImei())){
					criteria.add( Restrictions.like( "imei","%" + license.getImei() + "%") );
				}
				break;
		}

		if (StringUtil.isNotEmpty(license.getDeviceType()) && license.getDeviceType() > 0){
			criteria.add( Restrictions.eq("deviceType", license.getDeviceType()));
		}
		if (StringUtil.isNotEmpty(license.getDevice()) && license.getDevice() > 0){
			criteria.add( Restrictions.eq("device", license.getDevice()));
		}



		if ( !StringUtil.isEmpty(license.getPac()) ){
			switch (fetchType){
				case 1:
					//查询下一级
					criteria.add( Restrictions.like("pac", FormatPac.formatPacUnderline(license.getPac(), true)) );
					criteria.add( Restrictions.ne("pac", license.getPac()) );
					break;
				case 2:
					//查询所有
					criteria.add( Restrictions.like("pac", FormatPac.formatPac(license.getPac()) + "%") );
					break;
				default:
					//默认 .eq()
					criteria.add( Restrictions.eq("pac", license.getPac() ) );
					break;
			}
		}

		if ( !StringUtil.isEmpty(dataGrid.getSort())){
			if (dataGrid.getOrder().name().equals("asc")) {

				criteria.addOrder( Order.asc(dataGrid.getSort()) );

			} else {

				criteria.addOrder( Order.desc(dataGrid.getSort()) );

			}

		}

		out.setTotal(criteria.list().size());

		criteria.setMaxResults(dataGrid.getRows());
		criteria.setFirstResult( (dataGrid.getPage() - 1) * dataGrid.getRows());

		out.setPage(dataGrid.getPage());
		out.setRows(criteria.list());
		return out;
	}
}
