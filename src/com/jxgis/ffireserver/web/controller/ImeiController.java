package com.jxgis.ffireserver.web.controller;

import com.jxgis.ffireserver.bean.FFPLicense;
import com.jxgis.ffireserver.core.util.ReturnBody;
import com.jxgis.ffireserver.core.util.oConvertUtils;
import com.jxgis.ffireserver.service.LicenseService;
import com.jxgis.ffireserver.util.Glossary;
import org.apache.commons.beanutils.BeanUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Administrator on 2017/6/2.
 * 言伟拓展公共对外接口控制器
 *
 * 可能返回JSP、JSON
 */
@Controller
@RequestMapping("/imeiController")
public class ImeiController {

	ObjectMapper mapper = null;

	private LicenseService imeiService;

	@Autowired
	public void setImeiService(LicenseService imeiService) {
		this.imeiService = imeiService;
	}


	final static String DO = "f";

	@RequestMapping(params = "action")
	@ResponseBody
	public <T> Object action(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ReturnBody body = new ReturnBody();

		switch (oConvertUtils.getInt(req.getParameter(DO))) {
			case 110001:
				//添加IMEI
				return f110001(req);
			case 110008:
				//添加若干个设备序列号
				return f110008(req);
			default:
				body.setStatus(Glossary.Return_State_Not_Function);
				body.setMsg( "接口功能不明确");
				return body;
		}
	}


	@RequestMapping(params = "service")
	@ResponseBody
	public <T> Object service(HttpServletRequest req, HttpServletResponse res) throws Exception{


		int f = oConvertUtils.getInt(req.getParameter("f"));
		String imei = oConvertUtils.getString(req.getParameter("imei"));
		System.out.println(imei);

		switch (f){
			case 110002:
				//获得IMEI列表所有数据
				return f110002();
			case 110003:
				//删除某个IMEI
				return f110003(req);
			case 110004:
				//移除某个IMEI序列号绑定
				return f110004(req);
			case 110005:
				//IMEI-判断IMEI的合法性【是否存在、过期、作废】
				return f110005(req);
			case 110006:
				//更新imei
				return f110006(req);
			case 110007:
				//设备注册
				return f110007(req);
			default:
				ReturnBody body = new ReturnBody();
				body.setStatus(Glossary.Return_State_Not_Function);
				body.setMsg( "接口功能不明确");
				return body;
		}
	}





	private ReturnBody f110001(HttpServletRequest req) throws Exception {
		FFPLicense imei = new FFPLicense();
		BeanUtils.populate(imei, req.getParameterMap());
		return imeiService.insertLicense(imei);
	}

	private List<FFPLicense> f110002() throws Exception {

		return this.imeiService.loadAll(FFPLicense.class);
	}

	private ReturnBody f110003(HttpServletRequest req) throws Exception {
		return imeiService.deleteLicense(oConvertUtils.getInt(req.getParameter("id")));
	}
	private ReturnBody f110004(HttpServletRequest req) throws Exception {
		String key = oConvertUtils.getString(req.getParameter("key"));
		String imei = oConvertUtils.getString(req.getParameter("imei"));
		return imeiService.removeLicense(key, imei);
	}

	private ReturnBody f110005(HttpServletRequest req) throws Exception {
		return imeiService.isExistLicense(oConvertUtils.getString(req.getParameter("imei")));
	}

	private ReturnBody f110006(HttpServletRequest req) throws Exception {
		FFPLicense imei = new FFPLicense();
		BeanUtils.populate(imei, req.getParameterMap());
		return imeiService.updateLicense(imei);
	}
	private ReturnBody f110007(HttpServletRequest req) throws Exception {
		FFPLicense license = new FFPLicense();
		BeanUtils.populate(license, req.getParameterMap());
		return imeiService.registLicense(license);
	}
	private ReturnBody f110008(HttpServletRequest req) throws Exception {
		FFPLicense imei = new FFPLicense();
		BeanUtils.populate(imei, req.getParameterMap());
		int number = oConvertUtils.getInt(req.getParameter("number"));
		return this.imeiService.insertLicenseNumber(imei, number);
	}


}
