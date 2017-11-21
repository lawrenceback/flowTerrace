package com.jxgis.ffireserver.web.controller;

import com.jxgis.ffireserver.bean.*;
import com.jxgis.ffireserver.core.common.model.json.DataGrid;
import com.jxgis.ffireserver.core.util.*;
import com.jxgis.ffireserver.service.*;
import com.jxgis.ffireserver.util.Glossary;
import com.jxgis.ffireserver.web.bean.*;
import com.jxgis.ffireserver.web.service.*;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by YanWei on 2017/6/14.
 *
 * 对外接口
 *
 * @author 言伟
 *
 * 未上线之前，所有异常将向外抛出
 *
 *
 * 接口编写规范，在此类中，不要直接进行数据的增、删、改、查，及任务对数据直接的操作
 *
 * 而应通过Service（业务层）来进行对数据的操作
 *
 *
 * 不要让此类复杂化，该控制器只做接口转发控制。不处理任何业务功能操作
 *
 */
@Controller
@RequestMapping(value = "/serviceController")
public class ServiceController {


	final PageWrite pageWrite = new PageWrite();

	private SystemService systemService;
	private AreaService areaService;
	private TSUserService userService;
	private LicenseService licenseService;
	private TSIconService iconService;
	private TSFunctionService functionService;
	private FireHotService fireHotService;
	private RemoteMonitoringService remoteMonitoringService;
	private TSRoleService roleService;
	private TSDepartService departService;
	private TSControlService controlService;
	private ObservatoryService observatoryService;
	private RadioStationService radioStationService;
	private CommandService commandService;
	private ForestBeltPointService forestBeltPointService;
	private ArtificiallakeService artificiallakeService;
	private DangerousFacilitiesService dangerousFacilitiesService;
	private HotFeedbackService hotFeedbackService;
	private FireSituationService fireSituationService;
	private BrigadeService brigadeService;
	private FireDocumentService fireDocumentService;
	private PBrigadeService pBrigadeService;
	private ImportantUnitsService importantUnitsService;
	private OfficeService officeService;
	private PlanewaterpointService planewaterpointService;
	private SatelliteGroundStationService satelliteGroundStationService;
	private WarehouseService warehouseService;
	private WarningBoardsService warningBoardsService;
	private AreaDepartService areaDepartService;
	private GridUserTableService gridUserTableService;

	@Autowired
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}
	@Autowired
	public void setAreaService(AreaService areaService) {
		this.areaService = areaService;
	}
	@Autowired
	public void setUserService(TSUserService userService) {
		this.userService = userService;
	}
	@Autowired
	public void setLicenseService(LicenseService licenseService) {
		this.licenseService = licenseService;
	}
	@Autowired
	public void setIconService(TSIconService iconService) {
		this.iconService = iconService;
	}
	@Autowired
	public void setFunctionService(TSFunctionService functionService) {
		this.functionService = functionService;
	}
	@Autowired
	public void setFireHotService(FireHotService fireHotService) {
		this.fireHotService = fireHotService;
	}
	@Autowired
	public void setRemoteMonitoringService(RemoteMonitoringService remoteMonitoringService) {
		this.remoteMonitoringService = remoteMonitoringService;
	}
	@Autowired
	public void setDepartService(TSDepartService departService) {
		this.departService = departService;
	}
	@Autowired
	public void setRoleService(TSRoleService roleService) {
		this.roleService = roleService;
	}
	@Autowired
	public void setControlService(TSControlService controlService) {
		this.controlService = controlService;
	}
	@Autowired
	public void setObservatoryService(ObservatoryService observatoryService) {
		this.observatoryService = observatoryService;
	}
	@Autowired
	public void setRadioStationService(RadioStationService radioStationService) {
		this.radioStationService = radioStationService;
	}
	@Autowired
	public void setCommandService(CommandService commandService) {
		this.commandService = commandService;
	}
	@Autowired
	public void setForestBeltPointService(ForestBeltPointService forestBeltPointService) {
		this.forestBeltPointService = forestBeltPointService;
	}
	@Autowired
	public void setArtificiallakeService(ArtificiallakeService artificiallakeService) {
		this.artificiallakeService = artificiallakeService;
	}
	@Autowired
	public void setDangerousFacilitiesService(DangerousFacilitiesService dangerousFacilitiesService) {
		this.dangerousFacilitiesService = dangerousFacilitiesService;
	}
	@Autowired
	public void setHotFeedbackService(HotFeedbackService hotFeedbackService) {
		this.hotFeedbackService = hotFeedbackService;
	}
	@Autowired
	public void setFireSituationService(FireSituationService fireSituationService) {
		this.fireSituationService = fireSituationService;
	}
	@Autowired
	public void setBrigadeService(BrigadeService brigadeService) {
		this.brigadeService = brigadeService;
	}
	@Autowired
	public void setFireDocumentService(FireDocumentService fireDocumentService) {
		this.fireDocumentService = fireDocumentService;
	}
	@Autowired
	public void setpBrigadeService(PBrigadeService pBrigadeService) {
		this.pBrigadeService = pBrigadeService;
	}
	@Autowired
	public void setImportantUnitsService(ImportantUnitsService importantUnitsService) {
		this.importantUnitsService = importantUnitsService;
	}
	@Autowired
	public void setOfficeService(OfficeService officeService) {
		this.officeService = officeService;
	}
	@Autowired
	public void setPlanewaterpointService(PlanewaterpointService planewaterpointService) {
		this.planewaterpointService = planewaterpointService;
	}
	@Autowired
	public void setSatelliteGroundStationService(SatelliteGroundStationService satelliteGroundStationService) {
		this.satelliteGroundStationService = satelliteGroundStationService;
	}
	@Autowired
	public void setWarehouseService(WarehouseService warehouseService) {
		this.warehouseService = warehouseService;
	}
	@Autowired
	public void setWarningBoardsService(WarningBoardsService warningBoardsService) {
		this.warningBoardsService = warningBoardsService;
	}
	@Autowired
	public void setAreaDepartService(AreaDepartService areaDepartService) {
		this.areaDepartService = areaDepartService;
	}
	@Autowired
	public void setGridUserTableService(GridUserTableService gridUserTableService) {
		this.gridUserTableService = gridUserTableService;
	}

	/***
	 *
	 * 向工程外部提供的接口控制器
	 *
	 * param f
	 * @return Object[Map,List,ReturnBody]
	 * @throws Exception
	 *
	 * 21**** 地区操作接口
	 * 210*** __________  查询
	 *
	 * 22**** 用户操作接口
	 * 220*** __________  查询
	 * 221*** __________  添加
	 * 222*** __________  修改
	 * 223*** __________  删除
	 *
	 * 23**** 行政单位操作接口
	 * 230*** __________  查询
	 * 231*** __________  添加
	 * 232*** __________  修改
	 * 233*** __________  删除
	 *
	 *
	 *
	 * 32**** IMEI操作接口
	 * 320*** __________  查询
	 * 321*** __________  添加
	 * 322*** __________  修改
	 * 323*** __________  删除
	 *
	 * 41***** 基础数据(防火办、视频监控点等)管理
	 *
	 * 411**** 火灾热点操作接口
	 * 4110*** _______________  查询
	 * 4111*** _______________  添加
	 * 4112*** _______________  修改
	 * 4113*** _______________  删除
	 *
	 * 412**** 火灾监控操作接口
	 * 4120*** ______________  查询
	 * 4121*** ______________  添加
	 * 4122*** ______________  修改
	 * 4123*** ______________  删除
	 *
	 * 413**** 瞭望台操作接口
	 * 4130*** ______________  查询
	 * 4131*** ______________  添加
	 * 4132*** ______________  修改
	 * 4133*** ______________  删除
	 *
	 * 414**** 无线电台站操作接口
	 * 4140*** ______________  查询
	 * 4141*** ______________  添加
	 * 4142*** ______________  修改
	 * 4143*** ______________  删除
	 *
	 * 415**** 森林防火指挥部操作接口
	 * 4150*** ______________  查询
	 * 4151*** ______________  添加
	 * 4152*** ______________  修改
	 * 4153*** ______________  删除
	 *
	 * 416**** 防火林带操作接口
	 * 4160*** ______________  查询
	 * 4161*** ______________  添加
	 * 4162*** ______________  修改
	 * 4163*** ______________  删除
	 *
	 * 417**** 航空灭火蓄水池操作接口
	 * 4170*** ______________  查询
	 * 4171*** ______________  添加
	 * 4172*** ______________  修改
	 * 4173*** ______________  删除
	 *
	 * 418**** 林区危险及重要性操作接口
	 * 4180*** ______________  查询
	 * 4181*** ______________  添加
	 * 4182*** ______________  修改
	 * 4183*** ______________  删除
	 *
	 * 419**** 火情上报管理操作接口
	 * 4190*** ______________  查询
	 * 4191*** ______________  添加
	 * 4192*** ______________  修改
	 * 4193*** ______________  删除
	 *
	 * 420**** 半专业森林消防队 操作接口
	 * 4200*** ______________  查询
	 * 4201*** ______________  添加
	 * 4202*** ______________  修改
	 * 4203*** ______________  删除
	 *
	 *
	 * 421**** 火灾档案 操作接口
	 * 4210*** ______________  查询
	 * 4211*** ______________  添加
	 * 4212*** ______________  修改
	 * 4213*** ______________  删除
	 *
	 * 422**** 专业森林消防队 操作接口
	 * 4220*** ______________  查询
	 * 4221*** ______________  添加
	 * 4222*** ______________  修改
	 * 4223*** ______________  删除
	 *
	 * 423**** 重点防火单位 操作接口
	 * 4230*** ______________  查询
	 * 4231*** ______________  添加
	 * 4232*** ______________  修改
	 * 4233*** ______________  删除
	 *
	 * 424**** 森林防火办公室 操作接口
	 * 4240*** ______________  查询
	 * 4241*** ______________  添加
	 * 4242*** ______________  修改
	 * 4243*** ______________  删除
	 *
	 *
	 * 425**** 飞机吊桶取水点 操作接口
	 * 4250*** ______________  查询
	 * 4251*** ______________  添加
	 * 4252*** ______________  修改
	 * 4253*** ______________  删除
	 *
	 * 426**** 卫星地面站 操作接口
	 * 4260*** ______________  查询
	 * 4261*** ______________  添加
	 * 4262*** ______________  修改
	 * 4263*** ______________  删除
	 *
	 * 427**** 森林防火物资储备库 操作接口
	 * 4270*** ______________  查询
	 * 4271*** ______________  添加
	 * 4272*** ______________  修改
	 * 4273*** ______________  删除
	 *
	 * 428**** 大型警示牌(大型宣传牌) 操作接口
	 * 4280*** ______________  查询
	 * 4281*** ______________  添加
	 * 4282*** ______________  修改
	 * 4283*** ______________  删除
	 *
	 * 429**** 网格人员管理表 操作接口
	 * 4290*** ______________  查询
	 * 4291*** ______________  添加
	 * 4292*** ______________  修改
	 * 4293*** ______________  删除
	 *
	 * 7****** XMPP 消息接口
	 *
	 * 71***** 推送消息
	 *
	 * 9***** 系统操作接口
	 *
	 * 91**** icon操作接口
	 * 910*** ___________ 查询
	 * 911*** ___________ 添加
	 * 912*** ___________ 修改
	 * 913*** ___________ 删除
	 *
	 * 92**** 菜单操作接口
	 * 920*** __________  查询
	 * 921*** __________  添加
	 * 922*** __________  修改
	 * 923*** __________  删除
	 *
	 * 93**** 角色操作接口
	 * 930*** __________  查询
	 * 931*** __________  添加
	 * 932*** __________  修改
	 * 933*** __________  删除
	 *
	 * 94**** 部门操作接口
	 * 940*** __________  查询
	 * 941*** __________  添加
	 * 942*** __________  修改
	 * 943*** __________  删除
	 *
	 * 95**** 控件操作接口
	 * 950*** __________  查询
	 * 951*** __________  添加
	 * 952*** __________  修改
	 * 953*** __________  删除
	 *
	 *
	 *
	 * 7777** 测试接口
	 */
	@RequestMapping(value = "action")
	//@ResponseBody
	public <T> Object action(HttpServletRequest req, HttpServletResponse res, DataGrid dataGrid) throws Exception {


		/*
		 *
		 * 为了增加该接口的灵活性这里注释了 ”@ResponseBody“配置，
		 *
		 * 所以 在下面的功能接口中，返回类型可以是多种。
		 *
		 * 返回类型有： ModelAndView, null
		 *
		 * 示例1：return new ModelAndView("user/list");
		 * 示例2：return null;
		 *
		 * 示例1，实际返回的页面会是 WebContent/webpage/user/list.jsp ( 这是因为 Spring-mvc.xml 视图解析器配置 )
		 *
		 */


		//提出来为了，接口的调试
		int f = oConvertUtils.getInt(req.getParameter("f"));

		switch ( f ) {


			case 210001:
				//获得地区数据 （老接口，暂时弃用）
				pageWrite.outputJSON(f210001(req).getObj(), res, req);
				break;
			case 210002:
				//获得地区数据(包含子地区所有数据)
				pageWrite.outputJSON(f210002(req, dataGrid), res, req);
				break;
			case 210003:
				//获得IMEI列表DataGrid（灵活）
				pageWrite.outputJSON(f210003(req, dataGrid), res, req);
				break;

			case 210004:
				//获得行政地区数据 （推荐）
				f210004(req, res, dataGrid);
				break;
			case 210005:
				//获得行政区名称
				f210005(req, res);
				break;
			case 210006:
				//行政区反查
				f210006(req, res);
				break;
			case 211001:
				//添加 行政区
				f211001(req, res);
				break;
			case 211002:
				//添加 行政区
				f211002(req, res);
				break;
			case 212001:
				//修改 行政区
				f212001(req, res);
				break;
			case 213001:
				//删除 行政区
				f213001(req, res);
				break;



			case 220001:
				//根据pac获得用户列表
				pageWrite.outputJSON(f220001(req, dataGrid), res, req);
				break;
			case 220002:
				//根据id 或者 帐号名，获得用户信息
				pageWrite.outputJSON(f220002(req), res, req);
				break;
			case 221001:
				//添加用户
				pageWrite.outputJSON(f221001(req), res, req);
				break;
			case 222001:
				//修改用户
				pageWrite.outputJSON(f222001(req), res, req);
				break;
			case 222002:
				//修改用户密码
				f222002(req, res);
				break;
			case 223001:
				//删除用户
				pageWrite.outputJSON(f223001(req), res, req);
				break;



			case 230001:
				//获得 行政单位 列表
				f230001(req, res, dataGrid);
				break;
			case 231001:
				//添加 行政单位
				f231001(req, res);
				break;
			case 232001:
				//修改 行政单位
				f232001(req, res);
				break;
			case 233001:
				//删除 行政单位
				f233001(req, res);
				break;


			case 320001:
				//FFPLicense-判断IMEI的合法性【是否存在、过期、作废】
				pageWrite.outputJSON(f320001(req), res, req);
				break;
			case 321001:
				//添加 FFPLicense
				pageWrite.outputJSON(f321001(req), res, req);
				break;
			case 322001:
				//移除某个IMEI序列号绑定
				pageWrite.outputJSON(f322001(req), res, req);
				break;
			case 322002:
				//更新 FFPLicense
				pageWrite.outputJSON(f322002(req), res, req);
				break;
			case 322003:
				//设备注册
				f322003(req, res);
				break;
			case 323001:
				//删除IMEI
				pageWrite.outputJSON(f323001(req), res, req);
				break;
			case 4110001:
				//获得火灾热点列表
				pageWrite.outputJSON(f4110001(req, dataGrid), res, req);
				break;
			case 4110002:
				//获得火灾热点详情
				pageWrite.outputJSON(f4110002(req), res, req);
				break;
			case 4111001:
				//添加火灾热点
				pageWrite.outputJSON(f4111001(req), res, req);
				break;
			case 4111002:
				//添加火灾热点媒体文件
				f4111002(req, res);
				break;
			case 4113001:
				//删除火灾热点
				pageWrite.outputJSON(f4113001(req), res, req);
				break;
			case 4113002:
				//删除火灾热点媒体文件
				f4113002(req, res);
				break;
			case 4113003:
				//删除热点反馈
				f4113003(req, res);
				break;
			case 4112001:
				//修改火灾热点
				pageWrite.outputJSON(f4112001(req), res, req);
				break;
			case 4112002:
				//审核反馈热点
				f4112002(req, res);
				break;
			case 4112003:
				//修改热点反馈
				f4112003(req, res);
				break;
			case 4120001:
				//获得火灾监控列表 DataGrid
				pageWrite.outputJSON(f4120001(req, dataGrid), res,req);
				break;
			case 4120002:
				//导入火灾热点
				f4120002(req, res);
				break;
			case 4120003:
				//获得反馈热点列表
				f4120003(req, res, dataGrid);
				break;
			case 4121001:
				//添加火灾监控
				pageWrite.outputJSON(f4121001(req), res, req);
				break;
			case 4121002:
				//添加火灾监控视频
				f4121002(req, res);
				break;
			case 4122001:
				//修改火灾监控
				pageWrite.outputJSON(f4122001(req), res, req);
				break;
			case 4123001:
				//删除火灾监控
				pageWrite.outputJSON(f4123001(req), res, req);
				break;
			case 4123002:
				//删除火灾监控媒体文件
				f4123002(req, res);
				break;
			case 4130001:
				//获得瞭望台列表
				f4130001(req, res, dataGrid);
				break;
			case 4131001:
				//添加瞭望台
				f4131001(req, res);
				break;
			case 4131002:
				//添加瞭望台媒体文件
				f4131002(req, res);
				break;
			case 4132001:
				//修改瞭望台
				f4132001(req, res);
				break;
			case 4133001:
				//删除瞭望台
				f4133001(req, res);
				break;
			case 4133002:
				//删除瞭望台媒体文件
				f4133002(req, res);
				break;
			case 4140001:
				//获得无线电台站列表
				f4140001(req, res, dataGrid);
				break;
			case 4141001:
				//添加无线电台站
				f4141001(req, res);
				break;
			case 4141002:
				//添加无线电台站媒体文件
				f4141002(req, res);
				break;
			case 4142001:
				//修改无线电台站
				f4142001(req, res);
				break;
			case 4143001:
				//删除无线电台站
				f4143001(req, res);
				break;
			case 4143002:
				//删除无线电台站媒体文件
				f4143002(req, res);
				break;
			case 4150001:
				//获得森林防火指挥部列表
				f4150001(req, res, dataGrid);
				break;
			case 4151001:
				//添加森林防火指挥部
				f4151001(req, res);
				break;
			case 4151002:
				//添加森林防火指挥部媒体文件
				f4151002(req, res);
				break;
			case 4152001:
				//修改森林防火指挥部
				f4152001(req, res);
				break;
			case 4153001:
				//删除森林防火指挥部
				f4153001(req, res);
				break;
			case 4153002:
				//删除森林防火指挥部媒体文件
				f4153002(req, res);
				break;
			case 4160001:
				//获得防火林带列表
				f4160001(req, res, dataGrid);
				break;
			case 4161001:
				//添加防火林带
				f4161001(req, res);
				break;
			case 4161002:
				//添加 防火林带 媒体文件
				f4161002(req, res);
				break;
			case 4162001:
				//修改防火林带
				f4162001(req, res);
				break;
			case 4163001:
				//删除防火林带
				f4163001(req, res);
				break;
			case 4163002:
				//删除 防火林带 媒体文件
				f4163002(req, res);
				break;


			case 4170001:
				//获得航空灭火蓄水池列表
				f4170001(req, res, dataGrid);
				break;
			case 4171001:
				//添加航空灭火蓄水池
				f4171001(req, res);
				break;
			case 4171002:
				//添加 航空灭火蓄水池 媒体文件
				f4171002(req, res);
				break;
			case 4172001:
				//修改航空灭火蓄水池
				f4172001(req, res);
				break;
			case 4173001:
				//删除 航空灭火蓄水池
				f4173001(req, res);
				break;
			case 4173002:
				//删除 航空灭火蓄水池
				f4173002(req, res);
				break;


			case 4180001:
				//获得林区危险及重要性列表
				f4180001(req, res, dataGrid);
				break;
			case 4181001:
				//添加林 区危险及重要性
				f4181001(req, res);
				break;
			case 4181002:
				//添加林 区危险及重要性 媒体文件
				f4181002(req, res);
				break;
			case 4182001:
				//修改林区危险及重要性
				f4182001(req, res);
				break;
			case 4183001:
				//删除 林区危险及重要性
				f4183001(req, res);
				break;
			case 4183002:
				//删除 林区危险及重要性 媒体文件
				f4183002(req, res);
				break;


			case 4190001:
				//获得火情调度列表
				f4190001(req,res,dataGrid);
				break;
			case 4191002:
				//添加火情上报管理媒体文件
				f4191002(req,res);
				break;
			case 4192001:
				//修改火情上报管理
				f4192001(req,res);
				break;
			case 4193001:
				//删除火情上报管理
				f4193001(req,res);
				break;
			case 4193002:
				//删除火情上报管理媒体文件
				f4193002(req,res);
				break;

			case 4200001:
				//获得半专业森林消防队列表
				f4200001(req,res,dataGrid);
				break;
			case 4201001:
				//添加半专业森林消防队
				f4201001(req,res);
				break;
			case 4201002:
				//添加半专业森林消防队媒体文件
				f4201002(req,res);
				break;
			case 4202001:
				//修改半专业森林消防队
				f4202001(req,res);
				break;
			case 4203001:
				//删除半专业森林消防队
				f4203001(req,res);
				break;
			case 4203002:
				//删除半专业森林消防队媒体文件
				f4203002(req,res);
				break;


			case 4210001:
				//获得 火灾档案 列表
				f4210001(req,res,dataGrid);
				break;
			case 4211001:
				//添加 火灾档案
				f4211001(req,res);
				break;
			case 4211002:
				//添加 火灾档案 媒体文件
				f4211002(req,res);
				break;
			case 4212001:
				//修改 火灾档案
				f4212001(req,res);
				break;
			case 4213001:
				//删除 火灾档案
				f4213001(req,res);
				break;
			case 4213002:
				//删除 火灾档案 媒体文件
				f4213002(req,res);
				break;


			case 4220001:
				//获得 专业森林消防队 列表
				f4220001(req,res,dataGrid);
				break;
			case 4221001:
				//添加 专业森林消防队
				f4221001(req,res);
				break;
			case 4221002:
				//添加 专业森林消防队 媒体文件
				f4221002(req,res);
				break;
			case 4222001:
				//修改 专业森林消防队
				f4222001(req,res);
				break;
			case 4223001:
				//删除 专业森林消防队
				f4223001(req,res);
				break;
			case 4223002:
				//删除 专业森林消防队 媒体文件
				f4223002(req,res);
				break;


			case 4230001:
				//获得 重点防火单位 列表
				f4230001(req,res,dataGrid);
				break;
			case 4231001:
				//添加 重点防火单位
				f4231001(req,res);
				break;
			case 4231002:
				//添加 重点防火单位 媒体文件
				f4231002(req,res);
				break;
			case 4232001:
				//修改 重点防火单位
				f4232001(req,res);
				break;
			case 4233001:
				//删除 重点防火单位
				f4233001(req,res);
				break;
			case 4233002:
				//删除 重点防火单位 媒体文件
				f4233002(req,res);
				break;


			case 4240001:
				//获得 森林防火办公室 列表
				f4240001(req,res,dataGrid);
				break;
			case 4241001:
				//添加 森林防火办公室
				f4241001(req,res);
				break;
			case 4241002:
				//添加 森林防火办公室 媒体文件
				f4241002(req,res);
				break;
			case 4242001:
				//修改 森林防火办公室
				f4242001(req,res);
				break;
			case 4243001:
				//删除 森林防火办公室
				f4243001(req,res);
				break;
			case 4243002:
				//删除 森林防火办公室 媒体文件
				f4243002(req,res);
				break;


			case 4250001:
				//获得 飞机吊桶取水点 列表
				f4250001(req,res,dataGrid);
				break;
			case 4251001:
				//添加 飞机吊桶取水点
				f4251001(req,res);
				break;
			case 4251002:
				//添加 飞机吊桶取水点 媒体文件
				f4251002(req,res);
				break;
			case 4252001:
				//修改 飞机吊桶取水点
				f4252001(req,res);
				break;
			case 4253001:
				//删除 飞机吊桶取水点
				f4253001(req,res);
				break;
			case 4253002:
				//删除 飞机吊桶取水点 媒体文件
				f4253002(req,res);
				break;


			case 4260001:
				//获得 卫星地面站 列表
				f4260001(req,res,dataGrid);
				break;
			case 4261001:
				//添加 卫星地面站
				f4261001(req,res);
				break;
			case 4261002:
				//添加 卫星地面站 媒体文件
				f4261002(req,res);
				break;
			case 4262001:
				//修改 卫星地面站
				f4262001(req,res);
				break;
			case 4263001:
				//删除 卫星地面站
				f4263001(req,res);
				break;
			case 4263002:
				//删除 卫星地面站 媒体文件
				f4263002(req,res);
				break;


			case 4270001:
				//获得 森林防火物资储备库 列表
				f4270001(req,res,dataGrid);
				break;
			case 4271001:
				//添加 森林防火物资储备库
				f4271001(req,res);
				break;
			case 4271002:
				//添加 森林防火物资储备库 媒体文件
				f4271002(req,res);
				break;
			case 4272001:
				//修改 森林防火物资储备库
				f4272001(req,res);
				break;
			case 4273001:
				//删除 森林防火物资储备库
				f4273001(req,res);
				break;
			case 4273002:
				//删除 森林防火物资储备库 媒体文件
				f4273002(req,res);
				break;

			case 4280001:
				//获得 大型警示牌(大型宣传牌) 列表
				f4280001(req,res,dataGrid);
				break;
			case 4281001:
				//添加 大型警示牌(大型宣传牌)
				f4281001(req,res);
				break;
			case 4281002:
				//添加 大型警示牌(大型宣传牌) 媒体文件
				f4281002(req,res);
				break;
			case 4282001:
				//修改 大型警示牌(大型宣传牌)
				f4282001(req,res);
				break;
			case 4283001:
				//删除 大型警示牌(大型宣传牌)
				f4283001(req,res);
				break;
			case 4283002:
				//删除 大型警示牌(大型宣传牌) 媒体文件
				f4283002(req,res);
				break;

			case 4290001:
				//获得 网格人员管理表 列表
				f4290001(req,res,dataGrid);
				break;
			case 4291001:
				//添加 网格人员管理表
				f4291001(req,res);
				break;
			case 4291002:
				//添加 网格人员管理表 媒体文件
				f4291002(req,res);
				break;
			case 4292001:
				//修改 网格人员管理表
				f4292001(req,res);
				break;
			case 4293001:
				//删除 网格人员管理表
				f4293001(req,res);
				break;
			case 4293002:
				//删除 网格人员管理表 媒体文件
				f4293002(req,res);
				break;


			case 910001:
				//获得icon
				pageWrite.outputJSON(f910001(req, dataGrid), res, req);
				break;
			case 911001:
				//添加icon
				pageWrite.outputJSON(f911001(req), res, req);
				break;
			case 912001:
				//修改图标
				pageWrite.outputJSON(f912001(req), res, req);
				break;
			case 913001:
				//删除图标
				pageWrite.outputJSON(f913001(req), res, req);
				break;
			case 920001:
				//获得菜单TreeGrid列表
				pageWrite.outputJSON(f920001(req, dataGrid), res, req);
				break;
			case 920002:
				//获得角色菜单TreeGrid列表
				pageWrite.outputJSON(f920002(req, dataGrid), res, req);
				break;
			case 920003:
				//获得角色菜单TreeGrid列表
				pageWrite.outputJSON(f920003(req, dataGrid), res, req);
				break;
			case 921001:
				//添加菜单
				f921001(req,res);
				break;
			case 922001:
				//修改菜单
				f922001(req, res);
				break;
			case 923002:
				//删除菜单
				f923002(req,res);
				break;
			case 930001:
				//查询角色列表DataGrid
				f930001(req, res,dataGrid);
				break;
			case 931001:
				//添加 OR 修改角色
				f931001(req, res);
				break;
			case 933001:
				//删除角色
				f933001(req, res);
				break;
			case 940001:
				//获得部门列表TreeGrid
				f940001(req, res, dataGrid);
				break;
			case 940002:
				//获得org_code
				f940002(req, res);
				break;
			case 940003:
				//获得父部门
				f940003(req, res);
				break;
			case 940004:
				//获得部门用户
				f940004(req, res, dataGrid);
				break;
			case 941001:
				//添加部门
				f941001(req, res);
				break;
			case 942001:
				//修改部门
				f942001(req, res);
				break;
			case 943001:
				//删除部门
				f943001(req, res);
				break;
			case 950001:
				//通过某个菜单控件列表 DataGrid
				f950001(req, res, dataGrid);
				break;
			case 951001:
				//添加菜单控件
				f951001(req ,res);
				break;
			case 952001:
				//修改控件
				f952001(req, res);
				break;
			case 953001:
				//删除菜单控件
				f953001(req, res);
				break;
			default:
				ReturnBody body = new ReturnBody();
				body.setStatus(Glossary.Return_State_Not_Function);
				body.setMsg("接口功能不明确");
				pageWrite.outputJSON(body, res);
				break;
		}

		return null;

	}


	private void f953001(HttpServletRequest req, HttpServletResponse res) throws Exception{
		TSControl control = new TSControl();
		BeanUtils.populate(control, req.getParameterMap());
		pageWrite.outputJSON(controlService.deleteControl(control), res);

	}

	private void f952001(HttpServletRequest req, HttpServletResponse res) throws Exception{
		TSControl control = new TSControl();
		BeanUtils.populate(control, req.getParameterMap());
		if (StringUtil.isEmpty(oConvertUtils.getString("type"))){
			control.setType(Glossary.Filter_Int);
		}
		pageWrite.outputJSON(controlService.editControl(control), res);
	}

	private void f951001(HttpServletRequest req, HttpServletResponse res) throws Exception{
		TSControl control = new TSControl();
		BeanUtils.populate(control, req.getParameterMap());
		TSFunction function = controlService.get(TSFunction.class, oConvertUtils.getString(req.getParameter("functionId")));
		TSControlFunction controlFunction = new TSControlFunction();
		controlFunction.setControl(control);
		controlFunction.setFunction(function);
		//TODO 事务待处理
		controlService.save(control);
		pageWrite.outputJSON(controlService.addFunctionControl(controlFunction), res);
	}

	private void f950001(HttpServletRequest req, HttpServletResponse res, DataGrid dataGrid) throws Exception{
		TSFunction function = new TSFunction();
		BeanUtils.populate(function, req.getParameterMap());
		pageWrite.outputJSON(controlService.getFunctionControl(function, dataGrid), res);
	}

	private void f943001(HttpServletRequest req, HttpServletResponse res) throws Exception{
		TSDepart depart = new TSDepart();
		BeanUtils.populate(depart, req.getParameterMap());
		pageWrite.outputJSON(departService.deleteDepart(depart), res, req);
	}

	private void f942001(HttpServletRequest req, HttpServletResponse res) throws Exception{
		TSDepart depart = new TSDepart();
		BeanUtils.populate(depart, req.getParameterMap());
		pageWrite.outputJSON(departService.editDepart(depart), res, req);
	}
	private void f941001(HttpServletRequest req, HttpServletResponse res) throws Exception{
		TSDepart depart = new TSDepart();
		BeanUtils.populate(depart, req.getParameterMap());
		pageWrite.outputJSON(departService.addOrUpdateDepart(depart), res, req);
	}

	private void f940004(HttpServletRequest req, HttpServletResponse res, DataGrid dataGrid)throws Exception{
		TSDepartUser departUser = new TSDepartUser();
		BeanUtils.populate(departUser, req.getParameterMap());
		String departId = oConvertUtils.getString(req.getParameter("departId"));
		TSDepart depart = new TSDepart();
		depart.setId(departId);
		departUser.setDepart(depart);
		int fetchType = oConvertUtils.getInt(req.getParameter("fetchType"));
		pageWrite.outputJSON(departService.getDeparUsers(departUser, dataGrid, fetchType), res, req);

	}
	private void f940003(HttpServletRequest req, HttpServletResponse res) throws Exception{
		TSDepart depart = new TSDepart();
		BeanUtils.populate(depart, req.getParameterMap());
		pageWrite.outputJSON(departService.getDeppartParent(depart), res, req);
	}
	private void f940002(HttpServletRequest req, HttpServletResponse res) throws Exception{
		TSDepart depart = new TSDepart();
		BeanUtils.populate(depart, req.getParameterMap());
		pageWrite.outputJSON(departService.getDepartOrgCode(depart), res, req);
	}

	private void f940001(HttpServletRequest req, HttpServletResponse res, DataGrid dataGrid) throws Exception{
		TSDepart depart = new TSDepart();
		BeanUtils.populate(depart, req.getParameterMap());
		pageWrite.outputJSON(departService.getDepartTree(depart, dataGrid, 2), res, req);
	}

	private void f933001(HttpServletRequest req, HttpServletResponse res) throws Exception{
		TSRole role = new TSRole();
		BeanUtils.populate(role, req.getParameterMap());
		pageWrite.outputJSON(roleService.deleteRole(role), res, req);
	}

	private void f931001(HttpServletRequest req, HttpServletResponse res) throws Exception{

		TSRole role = new TSRole();
		BeanUtils.populate(role, req.getParameterMap());
		pageWrite.outputJSON(roleService.addOrUpdateRole(role), res, req);

	}

	private void f930001(HttpServletRequest req, HttpServletResponse res, DataGrid dataGrid) throws Exception{
		TSRole role = new TSRole();
		BeanUtils.populate(role, req.getParameterMap());
		pageWrite.outputJSON(roleService.getRoleDataGrid(role, dataGrid), res, req);
	}

	private void f923002(HttpServletRequest req, HttpServletResponse res) throws Exception{
		TSFunction function = new TSFunction();
		BeanUtils.populate(function, req.getParameterMap());
		int type = oConvertUtils.getInt(req.getParameter("type"));
		pageWrite.outputJSON(functionService.delFunction(function, type), res, req);
	}

	private void f922001(HttpServletRequest req, HttpServletResponse res) throws Exception{
		TSFunction function = new TSFunction();
		BeanUtils.populate(function, req.getParameterMap());

		//下面是double,int的赋值
		if (StringUtil.isEmpty(oConvertUtils.getString(req.getParameter("functionLevel")))) {
			function.setFunctionLevel(Glossary.Filter_Int);
		}

		String iconId = oConvertUtils.getString(req.getParameter("iconId"));
		TSIcon icon = systemService.get(TSIcon.class, iconId);
		if (StringUtil.isNotEmpty(icon)){
			function.setIcon(icon);
		}

		String parentFunctionId = oConvertUtils.getString(req.getParameter("parentFunctionId"));
		TSFunction parentFunction = systemService.get(TSFunction.class, parentFunctionId);
		if (StringUtil.isNotEmpty(parentFunction)){
			function.setFunctionParent(parentFunction);
		}

		pageWrite.outputJSON(functionService.updFunction(function), res, req);
	}
	private void f921001(HttpServletRequest req,HttpServletResponse res) throws Exception {
		TSFunction function = new TSFunction();
		BeanUtils.populate(function, req.getParameterMap());
		String iconId = oConvertUtils.getString(req.getParameter("iconId"));
		TSIcon icon = systemService.get(TSIcon.class, iconId);
		if (StringUtil.isNotEmpty(icon)){
			function.setIcon(icon);
		}
		String parentFunctionId = oConvertUtils.getString(req.getParameter("parentFunctionId"));
		TSFunction parentFunction = systemService.get(TSFunction.class, parentFunctionId);
		if (StringUtil.isNotEmpty(parentFunction)){
			function.setFunctionParent(parentFunction);
		}

		pageWrite.outputJSON(functionService.addOrUpdateFunction(function),res, req);
	}

	private Object f920003(HttpServletRequest req, DataGrid dataGrid) throws Exception {
		TSFunction function = new TSFunction();
		BeanUtils.populate(function, req.getParameterMap());
		return functionService.getFunctionTree(function, new TSControl(), dataGrid);
	}
	private Object f920002(HttpServletRequest req, DataGrid dataGrid) throws Exception {
		TSFunction function = new TSFunction();
		BeanUtils.populate(function, req.getParameterMap());
		TSRole role = functionService.get(TSRole.class, oConvertUtils.getString(req.getParameter("roleId")));
		return functionService.getFunctionTree(function, role, dataGrid);
	}
	private Object f920001(HttpServletRequest req, DataGrid dataGrid) throws Exception {
		TSFunction function = new TSFunction();
		BeanUtils.populate(function, req.getParameterMap());
		return functionService.getFunctionTree(function,dataGrid);
	}

	private ReturnBody f913001(HttpServletRequest req) throws Exception {
		TSIcon icon = new TSIcon();
		BeanUtils.populate(icon, req.getParameterMap());
		return iconService.delIcon(icon);
	}

	private ReturnBody f912001(HttpServletRequest req) throws Exception {
		TSIcon icon = new TSIcon();
		BeanUtils.populate(icon, req.getParameterMap());
		return iconService.updIcon(icon);
	}

	private ReturnBody f911001(HttpServletRequest req) throws Exception {
		TSIcon icon = new TSIcon();
		BeanUtils.populate(icon, req.getParameterMap());
		return iconService.addIcon(icon);
	}

	private ReturnDataGrid f910001(HttpServletRequest req, DataGrid dataGrid) throws Exception {
		TSIcon icon = new TSIcon();
		BeanUtils.populate(icon, req.getParameterMap());
		return iconService.iconLists(icon, dataGrid);
	}


	private void f4293002(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPGridUserTablePhoto userTablePhoto = new FFPGridUserTablePhoto();
		userTablePhoto.setId(oConvertUtils.getString(req.getParameter("id")));
		pageWrite.outputJSON(gridUserTableService.deleteBeltPointPhoto(userTablePhoto), res, req);
	}

	private void f4293001(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPGridUserTable gridUserTable = new FFPGridUserTable();
		gridUserTable.setId(oConvertUtils.getString(req.getParameter("id")));
		pageWrite.outputJSON(gridUserTableService.deleteGridUserTable(gridUserTable), res, req);
	}

	private void f4292001(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPGridUserTable gridUserTable = new FFPGridUserTable();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), gridUserTable);
		pageWrite.outputJSON(gridUserTableService.editGridUserTable(gridUserTable), res, req);
	}

	private void f4291002(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPGridUserTable gridUserTable = new FFPGridUserTable();
		gridUserTable.setId(oConvertUtils.getString(req.getParameter("id")));

		FFPGridUserTablePhoto tablePhoto = new FFPGridUserTablePhoto();
		tablePhoto.setGridUserTable(gridUserTable);
		// 获取用户上传的文件
		try{
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
			pageWrite.outputJSON(gridUserTableService.addBeltPointPhoto(tablePhoto, multipartRequest.getFile("file")), res);
		} catch (ClassCastException e){
			ReturnBody body = new ReturnBody();
			body.setStatus(Glossary.Return_State_Parameter_Error);
			body.setMsg("Parameter error [file], The file must not be empty.");
			pageWrite.outputJSON(body, res);
		}
	}

	private void f4291001(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPGridUserTable gridUserTable = new FFPGridUserTable();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), gridUserTable);

		ReturnBody body = null;

		// 获取用户上传的文件
		try{
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
			body = gridUserTableService.addGridUserTable(gridUserTable, multipartRequest.getFileMap());
		} catch (ClassCastException e){
			body = gridUserTableService.addGridUserTable(gridUserTable, null);
		}
		pageWrite.outputJSON(body,res,req);
	}
	private void f4290001(HttpServletRequest req, HttpServletResponse res, DataGrid dataGrid) throws Exception{
		FFPGridUserTable gridUserTable = new FFPGridUserTable();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), gridUserTable);
		int fetchType = oConvertUtils.getInt(req.getParameter("fetchType"));
		pageWrite.outputJSON(gridUserTableService.getGridUserTableList(gridUserTable, dataGrid, fetchType), res, req);
	}

	private void f4283002(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPWarningBoardsMedia boardsMedia = new FFPWarningBoardsMedia();
		boardsMedia.setId(oConvertUtils.getString(req.getParameter("id")));
		pageWrite.outputJSON(warningBoardsService.deleteBoardsMedia(boardsMedia), res);
	}

	private void f4283001(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPWarningBoards boards = new FFPWarningBoards();
		boards.setId(oConvertUtils.getString(req.getParameter("id")));
		pageWrite.outputJSON(warningBoardsService.deleteBoards(boards), res);
	}

	private void f4282001(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPWarningBoards boards = new FFPWarningBoards();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), boards);
		pageWrite.outputJSON(warningBoardsService.editBoards(boards), res);
	}

	private void f4281002(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPWarningBoards boards = new FFPWarningBoards();
		boards.setId(oConvertUtils.getString(req.getParameter("id")));

		FFPWarningBoardsMedia boardsMedia = new FFPWarningBoardsMedia();
		boardsMedia.setWarningBoards(boards);
		// 获取用户上传的文件
		try{
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
			pageWrite.outputJSON(warningBoardsService.addBoardsMedia(boardsMedia, multipartRequest.getFile("file")), res);
		} catch (ClassCastException e){
			ReturnBody body = new ReturnBody();
			body.setStatus(Glossary.Return_State_Parameter_Error);
			body.setMsg("Parameter error [file], The file must not be empty.");
			pageWrite.outputJSON(body, res);
		}
	}

	private void f4281001(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPWarningBoards boards = new FFPWarningBoards();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), boards);

		ReturnBody body = null;

		// 获取用户上传的文件
		try{
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
			body = warningBoardsService.addBoards(boards, multipartRequest.getFileMap());
		} catch (ClassCastException e){
			body = warningBoardsService.addBoards(boards, null);
		}

		if (body.getStatus() == Glossary.Return_State_Success && !StringUtil.isEmpty(oConvertUtils.getString(body.getObj()))){

			if (!StringUtil.isEmpty(boards.getShape())){
				int rows = warningBoardsService.executeSql("UPDATE ffp_warningBoards SET shape=POINTFROMTEXT('"+boards.getShape()+"') WHERE id=?", body.getObj());
				if (rows> 0){
					pageWrite.outputJSON(body, res);
					return;
				}

				warningBoardsService.deleteBoards(warningBoardsService.get(FFPWarningBoards.class, body.getObj().toString()));
				body.setStatus(Glossary.Return_State_Exception_Operation);
				body.setMsg("operation failed.");
				pageWrite.outputJSON(body, res);
				return;
			}


		}
		pageWrite.outputJSON(body,res,req);
	}

	private void f4280001(HttpServletRequest req, HttpServletResponse res, DataGrid dataGrid) throws Exception{
		FFPWarningBoards warningBoards = new FFPWarningBoards();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), warningBoards);
		int fetchType = oConvertUtils.getInt(req.getParameter("fetchType"));
		pageWrite.outputJSON(warningBoardsService.getBoardsList(warningBoards, dataGrid,fetchType), res, req);
	}

	private void f4273002(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPWarehouseMedia warehouseMedia = new FFPWarehouseMedia();
		warehouseMedia.setId(oConvertUtils.getString(req.getParameter("id")));
		pageWrite.outputJSON(warehouseService.deleteSWarehouseMedia(warehouseMedia), res);
	}

	private void f4273001(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPWarehouse warehouse = new FFPWarehouse();
		warehouse.setId(oConvertUtils.getString(req.getParameter("id")));
		pageWrite.outputJSON(warehouseService.deleteSWarehouse(warehouse), res);
	}

	private void f4272001(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPWarehouse warehouse = new FFPWarehouse();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), warehouse);
		pageWrite.outputJSON(warehouseService.editSWarehouse(warehouse), res, req);
	}

	private void f4271002(HttpServletRequest req,HttpServletResponse res) throws Exception{
		FFPWarehouse warehouse = new FFPWarehouse();
		warehouse.setId(oConvertUtils.getString(req.getParameter("id")));

		FFPWarehouseMedia warehouseMedia = new FFPWarehouseMedia();
		warehouseMedia.setWarehouse(warehouse);
		// 获取用户上传的文件
		try{
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
			pageWrite.outputJSON(warehouseService.addSWarehouseMedia(warehouseMedia, multipartRequest.getFile("file")), res);
		} catch (ClassCastException e){
			ReturnBody body = new ReturnBody();
			body.setStatus(Glossary.Return_State_Parameter_Error);
			body.setMsg("Parameter error [file], The file must not be empty.");
			pageWrite.outputJSON(body, res);
		}
	}

	private void f4271001(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPWarehouse warehouse = new FFPWarehouse();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), warehouse);

		ReturnBody body = null;

		// 获取用户上传的文件
		try{
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
			body = warehouseService.addSWarehouse(warehouse, multipartRequest.getFileMap());
		} catch (ClassCastException e){
			body = warehouseService.addSWarehouse(warehouse, null);
		}

		if (body.getStatus() == Glossary.Return_State_Success && !StringUtil.isEmpty(oConvertUtils.getString(body.getObj()))){

			if (!StringUtil.isEmpty(warehouse.getShape())){
				int rows = satelliteGroundStationService.executeSql("UPDATE ffp_warehouse SET shape=POINTFROMTEXT('"+warehouse.getShape()+"') WHERE id=?", body.getObj());
				if (rows> 0){
					pageWrite.outputJSON(body, res);
					return;
				}

				planewaterpointService.deleteplanewaterpoint(planewaterpointService.get(FFPPlanewaterpoint.class, body.getObj().toString()));
				body.setStatus(Glossary.Return_State_Exception_Operation);
				body.setMsg("operation failed.");
				pageWrite.outputJSON(body, res);
				return;
			}


		}
		pageWrite.outputJSON(body,res,req);
	}

	private void f4270001(HttpServletRequest req, HttpServletResponse res, DataGrid dataGrid) throws Exception{
		FFPWarehouse warehouse = new FFPWarehouse();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), warehouse);
		int fetchType = oConvertUtils.getInt(req.getParameter("fetchType"));
		pageWrite.outputJSON(warehouseService.getSWarehouseList(warehouse, dataGrid, fetchType), res, req);
	}

	private void f4263002(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPSatelliteGroundStationMedia stationMedia = new FFPSatelliteGroundStationMedia();
		stationMedia.setId(oConvertUtils.getString(req.getParameter("id")));
		pageWrite.outputJSON(satelliteGroundStationService.deleteSatelliteGroundStationMedia(stationMedia), res);
	}

	private void f4263001(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPSatelliteGroundStation station = new FFPSatelliteGroundStation();
		station.setId(oConvertUtils.getString(req.getParameter("id")));
		pageWrite.outputJSON(satelliteGroundStationService.deleteSatelliteGroundStation(station), res);
	}

	private void f4262001(HttpServletRequest req,HttpServletResponse res) throws Exception{
		FFPSatelliteGroundStation station = new FFPSatelliteGroundStation();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), station);
		pageWrite.outputJSON(satelliteGroundStationService.editSatelliteGroundStation(station), res);
	}

	private void f4261002(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPSatelliteGroundStation station = new FFPSatelliteGroundStation();
		station.setId(oConvertUtils.getString(req.getParameter("id")));

		FFPSatelliteGroundStationMedia stationMedia = new FFPSatelliteGroundStationMedia();
		stationMedia.setSatelliteGroundStation(station);
		// 获取用户上传的文件
		try{
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
			pageWrite.outputJSON(satelliteGroundStationService.addSatelliteGroundStationMedia(stationMedia, multipartRequest.getFile("file")), res);
		} catch (ClassCastException e){
			ReturnBody body = new ReturnBody();
			body.setStatus(Glossary.Return_State_Parameter_Error);
			body.setMsg("Parameter error [file], The file must not be empty.");
			pageWrite.outputJSON(body, res);
		}
	}

	private void f4261001(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPSatelliteGroundStation station = new FFPSatelliteGroundStation();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), station);

		ReturnBody body = null;

		// 获取用户上传的文件
		try{
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
			body = satelliteGroundStationService.addSatelliteGroundStation(station, multipartRequest.getFileMap());
		} catch (ClassCastException e){
			body = satelliteGroundStationService.addSatelliteGroundStation(station, null);
		}

		if (body.getStatus() == Glossary.Return_State_Success && !StringUtil.isEmpty(oConvertUtils.getString(body.getObj()))){

			if (!StringUtil.isEmpty(station.getShape())){
				int rows = satelliteGroundStationService.executeSql("UPDATE ffp_satelliteGroundStation SET shape=POINTFROMTEXT('"+station.getShape()+"') WHERE id=?", body.getObj());
				if (rows> 0){
					pageWrite.outputJSON(body, res);
					return;
				}

				planewaterpointService.deleteplanewaterpoint(planewaterpointService.get(FFPPlanewaterpoint.class, body.getObj().toString()));
				body.setStatus(Glossary.Return_State_Exception_Operation);
				body.setMsg("operation failed.");
				pageWrite.outputJSON(body, res);
				return;
			}


		}
		pageWrite.outputJSON(body,res,req);

	}
	private void f4260001(HttpServletRequest req, HttpServletResponse res, DataGrid dataGrid) throws Exception{
		FFPSatelliteGroundStation station = new FFPSatelliteGroundStation();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), station);
		int fetchType = oConvertUtils.getInt(req.getParameter("fetchType"));
		pageWrite.outputJSON(satelliteGroundStationService.getSatelliteGroundStationList(station, dataGrid, fetchType), res, req);
	}

	private void f4253002(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPPlanewaterpointMedia planewaterpointMedia = new FFPPlanewaterpointMedia();
		planewaterpointMedia.setId(oConvertUtils.getString(req.getParameter("id")));
		pageWrite.outputJSON(planewaterpointService.deleteplanewaterpointMedia(planewaterpointMedia), res);
	}

	private void f4253001(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPPlanewaterpoint planewaterpoint = new FFPPlanewaterpoint();
		planewaterpoint.setId(oConvertUtils.getString(req.getParameter("id")));
		pageWrite.outputJSON(planewaterpointService.deleteplanewaterpoint(planewaterpoint), res);
	}

	private void f4252001(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPPlanewaterpoint planewaterpoint = new FFPPlanewaterpoint();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), planewaterpoint);
		pageWrite.outputJSON(planewaterpointService.editplanewaterpoint(planewaterpoint), res, req);
	}

	private void f4251002(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPPlanewaterpoint planewaterpoint = new FFPPlanewaterpoint();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), planewaterpoint);

		FFPPlanewaterpointMedia planewaterpointMedia = new FFPPlanewaterpointMedia();
		planewaterpointMedia.setPlanewaterpoint(planewaterpoint);
		// 获取用户上传的文件
		try{
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
			pageWrite.outputJSON(planewaterpointService.addplanewaterpointMedia(planewaterpointMedia, multipartRequest.getFile("file")), res);
		} catch (ClassCastException e){
			ReturnBody body = new ReturnBody();
			body.setStatus(Glossary.Return_State_Parameter_Error);
			body.setMsg("Parameter error [file], The file must not be empty.");
			pageWrite.outputJSON(body, res);
		}
	}

	private void f4251001(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPPlanewaterpoint planewaterpoint = new FFPPlanewaterpoint();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), planewaterpoint);

		ReturnBody body = null;

		// 获取用户上传的文件
		try{
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
			body = planewaterpointService.addplanewaterpoint(planewaterpoint, multipartRequest.getFileMap());
		} catch (ClassCastException e){
			body = planewaterpointService.addplanewaterpoint(planewaterpoint, null);
		}

		if (body.getStatus() == Glossary.Return_State_Success && !StringUtil.isEmpty(oConvertUtils.getString(body.getObj()))){

			if (!StringUtil.isEmpty(planewaterpoint.getShape())){
				int rows = planewaterpointService.executeSql("UPDATE ffp_planewaterpoint SET shape=POINTFROMTEXT('"+planewaterpoint.getShape()+"') WHERE id=?", body.getObj());
				if (rows> 0){
					pageWrite.outputJSON(body, res);
					return;
				}

				planewaterpointService.deleteplanewaterpoint(planewaterpointService.get(FFPPlanewaterpoint.class, body.getObj().toString()));
				body.setStatus(Glossary.Return_State_Exception_Operation);
				body.setMsg("operation failed.");
				pageWrite.outputJSON(body, res);
				return;
			}


		}
		pageWrite.outputJSON(body,res,req);
	}

	private void f4250001(HttpServletRequest req, HttpServletResponse res, DataGrid dataGrid) throws Exception{
		FFPPlanewaterpoint planewaterpoint = new FFPPlanewaterpoint();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), planewaterpoint);
		int fetchType = oConvertUtils.getInt(req.getParameter("fetchType"));
		pageWrite.outputJSON(planewaterpointService.getplanewaterpointList(planewaterpoint, dataGrid, fetchType), res, req);
	}

	private void f4243002(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPOfficeMedia officeMedia = new FFPOfficeMedia();
		officeMedia.setId(oConvertUtils.getString(req.getParameter("id")));
		pageWrite.outputJSON(officeService.deleteOfficeMedia(officeMedia), res, req);
	}

	private void f4243001(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPOffice office = new FFPOffice();
		office.setId(oConvertUtils.getString(req.getParameter("id")));
		pageWrite.outputJSON(officeService.deleteOffice(office), res, req);
	}

	private void f4242001(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPOffice office = new FFPOffice();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), office);
		pageWrite.outputJSON(officeService.editOffice(office), res, req);
	}

	private void f4241002(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPOffice office = new FFPOffice();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), office);

		FFPOfficeMedia officeMedia = new FFPOfficeMedia();
		officeMedia.setOffice(office);
		// 获取用户上传的文件
		try{
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
			pageWrite.outputJSON(officeService.addOfficeMedia(officeMedia, multipartRequest.getFile("file")), res);
		} catch (ClassCastException e){
			ReturnBody body = new ReturnBody();
			body.setStatus(Glossary.Return_State_Parameter_Error);
			body.setMsg("Parameter error [file], The file must not be empty.");
			pageWrite.outputJSON(body, res);
		}
	}

	private void f4241001(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPOffice office = new FFPOffice();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), office);

		ReturnBody body = null;

		// 获取用户上传的文件
		try{
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
			body = officeService.addOffice(office, multipartRequest.getFileMap());
		} catch (ClassCastException e){
			body = officeService.addOffice(office, null);
		}

		if (body.getStatus() == Glossary.Return_State_Success && !StringUtil.isEmpty(oConvertUtils.getString(body.getObj()))){

			if (!StringUtil.isEmpty(office.getShape())){
				int rows = officeService.executeSql("UPDATE ffp_office SET shape=POINTFROMTEXT('"+office.getShape()+"') WHERE id=?", body.getObj());
				if (rows> 0){
					pageWrite.outputJSON(body, res);
					return;
				}

				officeService.deleteOffice(officeService.get(FFPOffice.class, body.getObj().toString()));
				body.setStatus(Glossary.Return_State_Exception_Operation);
				body.setMsg("operation failed.");
				pageWrite.outputJSON(body, res);
				return;
			}


		}
		pageWrite.outputJSON(body,res,req);

	}

	private void f4240001(HttpServletRequest req, HttpServletResponse res, DataGrid dataGrid) throws Exception{
		FFPOffice office = new FFPOffice();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), office);
		int fetchType = oConvertUtils.getInt(req.getParameter("fetchType"));
		pageWrite.outputJSON(officeService.getOfficeList(office, dataGrid, fetchType), res, req);
	}

	private void f4223002(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPPBrigadeMedia brigadeMedia = new FFPPBrigadeMedia();
		brigadeMedia.setId(oConvertUtils.getString(req.getParameter("id")));
		pageWrite.outputJSON(pBrigadeService.deletePBrigadeMedia(brigadeMedia), res, req);
	}

	private void f4223001(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPPBrigade brigade = new FFPPBrigade();
		brigade.setId(oConvertUtils.getString(req.getParameter("id")));
		pageWrite.outputJSON(pBrigadeService.deletePBrigade(brigade), res, req);
	}

	private void f4222001(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPPBrigade brigade = new FFPPBrigade();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), brigade);
		pageWrite.outputJSON(pBrigadeService.editPBrigade(brigade), res, req);
	}

	private void f4221002(HttpServletRequest req, HttpServletResponse res) throws Exception{

		FFPPBrigade brigade = new FFPPBrigade();
		brigade.setId(oConvertUtils.getString(req.getParameter("id")));


		FFPPBrigadeMedia brigadeMedia = new FFPPBrigadeMedia();
		brigadeMedia.setBrigade(brigade);
		// 获取用户上传的文件
		try{
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
			pageWrite.outputJSON(pBrigadeService.addPBrigadeMedia(brigadeMedia, multipartRequest.getFile("file")), res);
		} catch (ClassCastException e){
			ReturnBody body = new ReturnBody();
			body.setStatus(Glossary.Return_State_Parameter_Error);
			body.setMsg("Parameter error [file], The file must not be empty.");
			pageWrite.outputJSON(body, res);
		}

	}

	private void f4221001(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPPBrigade brigade = new FFPPBrigade();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), brigade);

		ReturnBody body = null;
		// 获取用户上传的文件
		try{
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
			body = pBrigadeService.addPBrigade(brigade, multipartRequest.getFileMap());
		} catch (ClassCastException e){
			body = pBrigadeService.addPBrigade(brigade, null);
		}

		if (body.getStatus() == Glossary.Return_State_Success && !StringUtil.isEmpty(oConvertUtils.getString(body.getObj()))){

			if (!StringUtil.isEmpty(brigade.getShape())){
				int rows = pBrigadeService.executeSql("UPDATE ffp_pbrigade SET shape=POINTFROMTEXT('"+brigade.getShape()+"') WHERE id=?", body.getObj());
				if (rows> 0){
					pageWrite.outputJSON(body, res);
					return;
				}

				pBrigadeService.deletePBrigade(pBrigadeService.get(FFPPBrigade.class, body.getObj().toString()));
				body.setStatus(Glossary.Return_State_Exception_Operation);
				body.setMsg("operation failed.");
				pageWrite.outputJSON(body, res);
				return;
			}

		}
		pageWrite.outputJSON(body,res,req);
	}

	private void f4233002(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPImportantUnitsMedia unitsMedia = new FFPImportantUnitsMedia();
		unitsMedia.setId(oConvertUtils.getString(req.getParameter("id")));
		pageWrite.outputJSON(importantUnitsService.deleteImportantMedia(unitsMedia), res, req);
	}

	private void f4233001(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPImportantUnits importantUnits = new FFPImportantUnits();
		importantUnits.setId(oConvertUtils.getString(req.getParameter("id")));
		pageWrite.outputJSON(importantUnitsService.deleteImportant(importantUnits), res, req);
	}

	private void f4232001(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPImportantUnits importantUnits = new FFPImportantUnits();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), importantUnits);
		pageWrite.outputJSON(importantUnitsService.editImportant(importantUnits), res, req);
	}

	private void f4231002(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPImportantUnits importantUnits = new FFPImportantUnits();
		importantUnits.setId(oConvertUtils.getString(req.getParameter("id")));


		FFPImportantUnitsMedia fireDocumentMedia = new FFPImportantUnitsMedia();
		fireDocumentMedia.setImportantUnits(importantUnits);
		// 获取用户上传的文件
		try{
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
			pageWrite.outputJSON(importantUnitsService.addImportantMedia(fireDocumentMedia, multipartRequest.getFile("file")), res);
		} catch (ClassCastException e){
			ReturnBody body = new ReturnBody();
			body.setStatus(Glossary.Return_State_Parameter_Error);
			body.setMsg("Parameter error [file], The file must not be empty.");
			pageWrite.outputJSON(body, res);
		}
	}

	private void f4231001(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPImportantUnits importantUnits = new FFPImportantUnits();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), importantUnits);

		ReturnBody body = null;
		// 获取用户上传的文件
		try{
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
			body = importantUnitsService.addImportant(importantUnits, multipartRequest.getFileMap());
		} catch (ClassCastException e){
			body = importantUnitsService.addImportant(importantUnits, null);
		}

		if (body.getStatus() == Glossary.Return_State_Success && !StringUtil.isEmpty(oConvertUtils.getString(body.getObj()))){

			if (!StringUtil.isEmpty(importantUnits.getShape())){
				int rows = fireDocumentService.executeSql("UPDATE ffp_importantUnits SET shape=POINTFROMTEXT('"+importantUnits.getShape()+"') WHERE id=?", body.getObj());
				if (rows> 0){
					pageWrite.outputJSON(body, res);
					return;
				}

				fireDocumentService.deleteFireDocument(fireDocumentService.get(FFPFireDocument.class, body.getObj().toString()));
				body.setStatus(Glossary.Return_State_Exception_Operation);
				body.setMsg("operation failed.");
				pageWrite.outputJSON(body, res);
				return;
			}
		}
		pageWrite.outputJSON(body,res,req);

	}
	private void f4230001(HttpServletRequest req, HttpServletResponse res, DataGrid dataGrid) throws Exception{
		FFPImportantUnits importantUnits = new FFPImportantUnits();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), importantUnits);
		int fetchTtye = oConvertUtils.getInt(req.getParameter("fetchType"));
		pageWrite.outputJSON(importantUnitsService.getImportantList(importantUnits,dataGrid, fetchTtye), res, req);
	}

	private void f4220001(HttpServletRequest req, HttpServletResponse res, DataGrid dataGrid) throws Exception{
		FFPPBrigade brigade = new FFPPBrigade();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), brigade);
		int fetchTtye = oConvertUtils.getInt(req.getParameter("fetchType"));
		pageWrite.outputJSON(pBrigadeService.getPBrigadeList(brigade,dataGrid, fetchTtye), res, req);
	}

	private void f4213002(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPFireDocumentMedia documentMedia = new FFPFireDocumentMedia();
		documentMedia.setId(oConvertUtils.getString(req.getParameter("id")));
		pageWrite.outputJSON(fireDocumentService.deleteFireDocumentMedia(documentMedia), res, req);
	}

	private void f4213001(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPFireDocument document = new FFPFireDocument();
		document.setId(oConvertUtils.getString(req.getParameter("id")));
		pageWrite.outputJSON(fireDocumentService.deleteFireDocument(document), res, req);
	}

	private void f4212001(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPFireDocument document = new FFPFireDocument();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), document);
		pageWrite.outputJSON(fireDocumentService.editFireDocument(document), res, req);
	}

	private void f4211002(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPFireDocument document = new FFPFireDocument();
		document.setId(oConvertUtils.getString(req.getParameter("id")));


		FFPFireDocumentMedia fireDocumentMedia = new FFPFireDocumentMedia();
		fireDocumentMedia.setFireDocument(document);
		// 获取用户上传的文件
		try{
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
			pageWrite.outputJSON(fireDocumentService.addFireDocumentMedia(fireDocumentMedia, multipartRequest.getFile("file")), res);
		} catch (ClassCastException e){
			ReturnBody body = new ReturnBody();
			body.setStatus(Glossary.Return_State_Parameter_Error);
			body.setMsg("Parameter error [file], The file must not be empty.");
			pageWrite.outputJSON(body, res);
		}
	}

	private void f4211001(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPFireDocument document = new FFPFireDocument();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), document);


		ReturnBody body = null;
		// 获取用户上传的文件
		try{
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
			body = fireDocumentService.addFireDocument(document, multipartRequest.getFileMap());
		} catch (ClassCastException e){
			body = fireDocumentService.addFireDocument(document, null);
		}

		if (body.getStatus() == Glossary.Return_State_Success && !StringUtil.isEmpty(oConvertUtils.getString(body.getObj()))){

			if (!StringUtil.isEmpty(document.getShape())){
				int rows = fireDocumentService.executeSql("UPDATE ffp_fire_document SET shape=POINTFROMTEXT('"+document.getShape()+"') WHERE id=?", body.getObj());
				if (rows> 0){
					pageWrite.outputJSON(body, res);
					return;
				}

				fireDocumentService.deleteFireDocument(fireDocumentService.get(FFPFireDocument.class, body.getObj().toString()));
				body.setStatus(Glossary.Return_State_Exception_Operation);
				body.setMsg("operation failed.");
				pageWrite.outputJSON(body, res);
				return;
			}
		}
		pageWrite.outputJSON(body,res,req);

	}

	private void f4210001(HttpServletRequest req, HttpServletResponse res, DataGrid dataGrid) throws Exception{
		FFPFireDocument document = new FFPFireDocument();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), document);
		int fetchTtye = oConvertUtils.getInt(req.getParameter("fetchType"));
		pageWrite.outputJSON(fireDocumentService.getFireDocumentList(document,dataGrid, fetchTtye), res, req);
	}

	private void f4203002(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPHBrigadeMedia brigadeMedia = new FFPHBrigadeMedia();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), brigadeMedia);
		pageWrite.outputJSON(brigadeService.deleteBrigadeMedia(brigadeMedia), res, req);
	}

	private void f4203001(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPHBrigade brigade = new FFPHBrigade();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), brigade);
		pageWrite.outputJSON(brigadeService.deleteBrigade(brigade), res, req);
	}

	private void f4202001(HttpServletRequest req , HttpServletResponse res) throws Exception{
		FFPHBrigade brigade = new FFPHBrigade();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), brigade);
		pageWrite.outputJSON(brigadeService.editBrigade(brigade), res, req);
	}

	private void f4201002(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPHBrigade brigade = new FFPHBrigade();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), brigade);

		FFPHBrigadeMedia brigadeMedia = new FFPHBrigadeMedia();
		brigadeMedia.setBrigade(brigade);
		// 获取用户上传的文件
		try{
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
			pageWrite.outputJSON(brigadeService.addBrigadeMedia(brigadeMedia, multipartRequest.getFile("file")), res);
		} catch (ClassCastException e){
			ReturnBody body = new ReturnBody();
			body.setStatus(Glossary.Return_State_Parameter_Error);
			body.setMsg("Parameter error [file], The file must not be empty.");
			pageWrite.outputJSON(body, res);
		}
	}

	private void f4201001(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPHBrigade brigade = new FFPHBrigade();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), brigade);
		ReturnBody body = null;
		// 获取用户上传的文件
		try{
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
			body = brigadeService.addBrigade(brigade, multipartRequest.getFileMap());
		} catch (ClassCastException e){
			body = brigadeService.addBrigade(brigade, null);
		}

		if (!StringUtil.isEmpty(brigade.getShape()) && body.getStatus() == Glossary.Return_State_Success && !StringUtil.isEmpty(oConvertUtils.getString(body.getObj()))){

			int rows = brigadeService.executeSql("UPDATE ffp_hbrigade SET shape=POINTFROMTEXT('"+brigade.getShape()+"') WHERE id=?", body.getObj());
			if (rows> 0){
				pageWrite.outputJSON(body, res);
				return;
			}

			brigadeService.deleteBrigade(brigadeService.get(FFPHBrigade.class, body.getObj().toString()));
			body.setStatus(Glossary.Return_State_Exception_Operation);
			body.setMsg("operation failed.");
			pageWrite.outputJSON(body, res);
			return;
		}
		pageWrite.outputJSON(body,res,req);

	}

	private void f4200001(HttpServletRequest req, HttpServletResponse res, DataGrid dataGrid) throws Exception{

		FFPHBrigade brigade = new FFPHBrigade();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), brigade);
		int fetchTtye = oConvertUtils.getInt(req.getParameter("fetchType"));
		pageWrite.outputJSON(brigadeService.getBrigadeList(brigade,dataGrid, fetchTtye), res, req);
	}


	private void f4193002(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPFireSituationMedia fireSituationMedia = new FFPFireSituationMedia();
		pageWrite.outputJSON(fireSituationService.deleteFireSituationMedia(fireSituationMedia), res, req);
	}

	private void f4193001(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPFireSituation fireSituation = new FFPFireSituation();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), fireSituation);
		pageWrite.outputJSON(fireSituationService.deleteFireSituation(fireSituation), res, req);
	}

	private void f4192001(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPFireSituation fireSituation = new FFPFireSituation();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), fireSituation);
		pageWrite.outputJSON(fireSituationService.editFireSituation(fireSituation),res,req);
	}

	private void f4191002(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPFireSituation fireSituation = new FFPFireSituation();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), fireSituation);
		FFPFireSituationMedia fireSituationMedia = new FFPFireSituationMedia();
		fireSituationMedia.setFireSituation(fireSituation);
		// 获取用户上传的文件
		try{
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
			pageWrite.outputJSON(fireSituationService.addFireSituationMedia(fireSituationMedia, multipartRequest.getFile("file")), res);
		} catch (ClassCastException e){
			ReturnBody body = new ReturnBody();
			body.setStatus(Glossary.Return_State_Parameter_Error);
			body.setMsg("Parameter error [file], The file must not be empty.");
			pageWrite.outputJSON(body, res);
		}
	}



	private void f4190001(HttpServletRequest req, HttpServletResponse res, DataGrid dataGrid) throws Exception{
		FFPFireSituation fireSituation = new FFPFireSituation();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), fireSituation);
		String time_start_string = oConvertUtils.getString(req.getParameter("time_start"));
		String time_end_string = oConvertUtils.getString(req.getParameter("time_end"));
		Date time_start = null;
		Date time_end = null;
		long h = 0;//小时
		long m = 0;//分钟
		long s = 0;//秒
		long hms = h + m + s;
		if (!StringUtil.isEmpty(time_start_string)){
			switch (time_start_string.trim().length()){
				case 10:
					time_start = DateUtils.parseDate(time_start_string, "yyyy-MM-dd");
					break;
				case 13:
					time_start = DateUtils.parseDate(time_start_string, "yyyy-MM-dd HH");
					break;
				case 16:
					time_start = DateUtils.parseDate(time_start_string, "yyyy-MM-dd HH:mm");
					break;
				default:
					time_start = DateUtils.parseDate(time_start_string, "yyyy-MM-dd HH:mm:ss");
					break;
			}

		}
		if (!StringUtil.isEmpty(time_end_string)){
			switch (time_end_string.trim().length()){
				case 10:
					time_end = DateUtils.parseDate(time_end_string, "yyyy-MM-dd");
					h = 1000*60*60*23;//小时
					m = 1000*60*59;//分钟
					s = 1000*59;//秒
					hms = h + m + s;
					time_end = new Date(time_end.getTime()+hms);
					break;
				case 13:
					time_end = DateUtils.parseDate(time_end_string, "yyyy-MM-dd HH");
					m = 1000*60*59;//分钟
					s = 1000*59;//秒
					hms = m + s;
					time_end = new Date(time_end.getTime()+hms);
					break;
				case 16:
					time_end = DateUtils.parseDate(time_end_string, "yyyy-MM-dd HH:mm");
					s = 1000*59;//秒
					time_end = new Date(time_end.getTime()+s);
					break;
				default:
					time_end = DateUtils.parseDate(time_end_string, "yyyy-MM-dd HH:mm:ss");
					break;
			}
		}
		pageWrite.outputJSON(fireSituationService.getFireSituationList(fireSituation,dataGrid,time_start, time_end), res, req);
	}

	private void f4183002(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPDangerousFacilitiesMedia facilitiesMedia = new FFPDangerousFacilitiesMedia();
		facilitiesMedia.setId(oConvertUtils.getString(req.getParameter("id")));
		pageWrite.outputJSON(dangerousFacilitiesService.deleteDangerousFacilitiesMedia(facilitiesMedia), res);
	}

	private void f4183001(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPDangerousFacilities facilities = new FFPDangerousFacilities();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), facilities);
		pageWrite.outputJSON(dangerousFacilitiesService.deleteDangerousFacilities(facilities), res);
	}

	private void f4182001(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPDangerousFacilities facilities = new FFPDangerousFacilities();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), facilities);
		pageWrite.outputJSON(dangerousFacilitiesService.editDangerousFacilities(facilities), res);
	}

	private void f4181002(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPDangerousFacilitiesMedia facilitiesMedia = new FFPDangerousFacilitiesMedia();

		FFPDangerousFacilities facilities = new FFPDangerousFacilities();

		facilities.setId(oConvertUtils.getString(req.getParameter("id")));

		facilitiesMedia.setFacilities(facilities);

		// 获取用户上传的文件
		try{
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
			pageWrite.outputJSON(dangerousFacilitiesService.addDangerousFacilitiesMedia(facilitiesMedia, multipartRequest.getFile("file")), res);
		} catch (ClassCastException e){
			ReturnBody body = new ReturnBody();
			body.setStatus(Glossary.Return_State_Parameter_Error);
			body.setMsg("parameter error [file], the file must not be empty.");
			pageWrite.outputJSON(body, res);
		}
	}

	private void f4181001(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPDangerousFacilities facilities = new FFPDangerousFacilities();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), facilities);
		ReturnBody body = null;
		// 获取用户上传的文件
		try{
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
			body = dangerousFacilitiesService.addDangerousFacilities(facilities, multipartRequest.getFileMap());
		} catch (ClassCastException e){
			body = dangerousFacilitiesService.addDangerousFacilities(facilities, null);
		}

		if (!StringUtil.isEmpty(facilities.getShape()) && StringUtil.isNotEmpty(body) && body.getStatus() == Glossary.Return_State_Success){

			int rows = dangerousFacilitiesService.executeSql("UPDATE ffp_dangerous_facilities SET shape=POINTFROMTEXT('"+facilities.getShape()+"') WHERE id=?", body.getObj());
			if (rows> 0){
				pageWrite.outputJSON(body, res);
				return;
			}

			dangerousFacilitiesService.deleteDangerousFacilities(dangerousFacilitiesService.get(FFPDangerousFacilities.class, body.getObj().toString()));
			body.setStatus(Glossary.Return_State_Exception_Operation);
			body.setMsg("operation failed.");
			pageWrite.outputJSON(body, res);
			return;
		}

		pageWrite.outputJSON(body, res);

	}

	private void f4180001(HttpServletRequest req, HttpServletResponse res, DataGrid dataGrid) throws Exception{
		FFPDangerousFacilities facilities = new FFPDangerousFacilities();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), facilities);
		int fetchType = oConvertUtils.getInt(req.getParameter("fetchType"));
		pageWrite.outputJSON(dangerousFacilitiesService.getDangerousFacilitiesList(facilities, dataGrid, fetchType), res);
	}

	private void f4173002(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPArtificiallakeMedia artificiallakeMedia = new FFPArtificiallakeMedia();
		artificiallakeMedia.setId(oConvertUtils.getString(req.getParameter("id")));

		pageWrite.outputJSON(artificiallakeService.deleteArtificiallakeMedia(artificiallakeMedia), res);
	}

	private void f4173001(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPArtificiallake artificiallake = new FFPArtificiallake();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), artificiallake);
		pageWrite.outputJSON(artificiallakeService.deleteArtificiallake(artificiallake), res);
	}

	private void f4172001(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPArtificiallake artificiallake = new FFPArtificiallake();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), artificiallake);
		pageWrite.outputJSON(artificiallakeService.editArtificiallake(artificiallake), res);
	}

	private void f4171002(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPArtificiallakeMedia artificiallakeMedia = new FFPArtificiallakeMedia();

		FFPArtificiallake artificiallake = new FFPArtificiallake();

		artificiallake.setId(oConvertUtils.getString(req.getParameter("id")));

		artificiallakeMedia.setArtificiallake(artificiallake);

		// 获取用户上传的文件
		try{
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
			pageWrite.outputJSON(artificiallakeService.addArtificiallakeMedia(artificiallakeMedia, multipartRequest.getFile("file")), res);
		} catch (ClassCastException e){
			ReturnBody body = new ReturnBody();
			body.setStatus(Glossary.Return_State_Parameter_Error);
			body.setMsg("parameter error [file], the file must not be empty.");
			pageWrite.outputJSON(body, res);
		}
	}

	private void f4171001(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPArtificiallake artificiallake = new FFPArtificiallake();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), artificiallake);
		ReturnBody body = null;
		// 获取用户上传的文件
		try{
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
			body = artificiallakeService.addArtificiallake(artificiallake, multipartRequest.getFileMap());
		} catch (ClassCastException e){
			body = artificiallakeService.addArtificiallake(artificiallake, null);
		}

		if (!StringUtil.isEmpty(artificiallake.getShape()) && StringUtil.isNotEmpty(body) && body.getStatus() == Glossary.Return_State_Success){

			int rows = artificiallakeService.executeSql("UPDATE ffp_artificiallake SET shape=POINTFROMTEXT('"+artificiallake.getShape()+"') WHERE id=?", body.getObj());
			if (rows> 0){
				pageWrite.outputJSON(body, res);
				return;
			}

			artificiallakeService.deleteArtificiallake(artificiallakeService.get(FFPArtificiallake.class, body.getObj().toString()));
			body.setStatus(Glossary.Return_State_Exception_Operation);
			body.setMsg("operation failed.");
			pageWrite.outputJSON(body, res);
			return;
		}

		pageWrite.outputJSON(body, res);

	}

	private void f4170001(HttpServletRequest req, HttpServletResponse res, DataGrid dataGrid) throws Exception{
		FFPArtificiallake artificiallake = new FFPArtificiallake();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), artificiallake);
		int fetchType = oConvertUtils.getInt(req.getParameter("fetchType"));
		pageWrite.outputJSON(artificiallakeService.getArtificiallakeList(artificiallake, dataGrid, fetchType), res);
	}

	private void f4163002(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPForestBeltPointMedia beltPointMedia = new FFPForestBeltPointMedia();
		beltPointMedia.setId(oConvertUtils.getString(req.getParameter("id")));

		pageWrite.outputJSON(forestBeltPointService.deleteBeltPointMedia(beltPointMedia), res);
	}

	private void f4163001(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPForestBeltPoint beltPoint = new FFPForestBeltPoint();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), beltPoint);
		pageWrite.outputJSON(forestBeltPointService.deleteBeltPoint(beltPoint), res);
	}

	private void f4162001(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPForestBeltPoint beltPoint = new FFPForestBeltPoint();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), beltPoint);

		pageWrite.outputJSON(forestBeltPointService.editBeltPoint(beltPoint), res);
	}

	private void f4161002(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPForestBeltPoint beltPoint = new FFPForestBeltPoint();
		beltPoint.setId(oConvertUtils.getString(req.getParameter("id")));

		FFPForestBeltPointMedia beltPointMedia = new FFPForestBeltPointMedia();
		beltPointMedia.setForestBeltPoint(beltPoint);


		// 获取用户上传的文件
		try{
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
			pageWrite.outputJSON(forestBeltPointService.addBeltPointMedia(beltPointMedia, multipartRequest.getFile("file")), res);
		} catch (ClassCastException e){
			ReturnBody body = new ReturnBody();
			body.setStatus(Glossary.Return_State_Parameter_Error);
			body.setMsg("parameter error [file], the file must not be empty.");
			pageWrite.outputJSON(body, res);
		}
	}

	private void f4161001(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPForestBeltPoint beltPoint = new FFPForestBeltPoint();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), beltPoint);
		ReturnBody body = null;
		// 获取用户上传的文件
		try{
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
			body = forestBeltPointService.addBeltPoint(beltPoint, multipartRequest.getFileMap());
		} catch (ClassCastException e){
			body = forestBeltPointService.addBeltPoint(beltPoint, null);
		}


		if (!StringUtil.isEmpty(beltPoint.getShape()) && StringUtil.isNotEmpty(body) && body.getStatus() == Glossary.Return_State_Success){

			int rows = commandService.executeSql("UPDATE ffp_forest_beltPoint SET shape=POINTFROMTEXT('"+beltPoint.getShape()+"') WHERE id=?", body.getObj());
			if (rows> 0){
				pageWrite.outputJSON(body, res);
				return;
			}

			forestBeltPointService.deleteBeltPoint(forestBeltPointService.get(FFPForestBeltPoint.class, body.getObj().toString()));
			body.setStatus(Glossary.Return_State_Exception_Operation);
			body.setMsg("operation failed.");
			pageWrite.outputJSON(body, res);
			return;
		}

		pageWrite.outputJSON(body, res);
	}

	private void f4160001(HttpServletRequest req, HttpServletResponse res, DataGrid dataGrid) throws Exception{
		FFPForestBeltPoint beltPoint = new FFPForestBeltPoint();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), beltPoint);
		int fetchType = oConvertUtils.getInt(req.getParameter("fetchType"));
		pageWrite.outputJSON(forestBeltPointService.getBeltPointList(beltPoint, dataGrid, fetchType), res);
	}

	private void f4153002(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPCommandMedia commandMedia = new FFPCommandMedia();
		commandMedia.setId(oConvertUtils.getString(req.getParameter("id")));
		pageWrite.outputJSON(commandService.deleteCommandMedia(commandMedia), res);
	}

	private void f4153001(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPCommand command = new FFPCommand();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), command);
		pageWrite.outputJSON(commandService.deleteCommand(command), res);
	}

	private void f4152001(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPCommand command = new FFPCommand();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), command);
		String id = oConvertUtils.getString(req.getParameter("id"));
		command.setId(id);

		pageWrite.outputJSON(commandService.editCommand(command), res);
	}

	private void f4151002(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPCommand command = new FFPCommand();
		command.setId(oConvertUtils.getString(req.getParameter("id")));

		FFPCommandMedia commandMedia = new FFPCommandMedia();
		commandMedia.setCommand(command);

		// 获取用户上传的文件
		try{
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
			pageWrite.outputJSON(commandService.addCommandMedia(commandMedia, multipartRequest.getFile("file")), res);
		} catch (ClassCastException e){
			ReturnBody body = new ReturnBody();
			body.setStatus(Glossary.Return_State_Parameter_Error);
			body.setMsg("parameter error [id], The document must not be empty.");
		}
	}

	private void f4151001(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPCommand command = new FFPCommand();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), command);

		ReturnBody body = null;
		// 获取用户上传的文件
		try{
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
			body = commandService.addCommand(command, multipartRequest.getFileMap());
		} catch (ClassCastException e){
			body = commandService.addCommand(command, null);
		}

		if (!StringUtil.isEmpty(command.getShape()) && StringUtil.isNotEmpty(body) && body.getStatus() == Glossary.Return_State_Success){

			int rows = commandService.executeSql("UPDATE ffp_command SET shape=POINTFROMTEXT('"+command.getShape()+"') WHERE id=?", body.getObj());
			if (rows> 0){
				pageWrite.outputJSON(body, res);
				return;
			}

			commandService.deleteCommand(commandService.get(FFPCommand.class, body.getObj().toString()));
			body.setStatus(Glossary.Return_State_Exception_Operation);
			body.setMsg("operation failed.");
			pageWrite.outputJSON(body, res);
			return;
		}
		pageWrite.outputJSON(body, res);
	}

	private void f4150001(HttpServletRequest req, HttpServletResponse res, DataGrid dataGrid) throws Exception{
		FFPCommand command = new FFPCommand();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), command);
		int fetchType = oConvertUtils.getInt(req.getParameter("fetchType"));
		pageWrite.outputJSON(commandService.getCommandList(command, dataGrid, fetchType), res);
	}

	private void f4143002(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPRadioStationMedia radioStationMedia = new FFPRadioStationMedia();
		radioStationMedia.setId(oConvertUtils.getString(req.getParameter("id")));
		pageWrite.outputJSON(radioStationService.deleteRadioStationMediaFile(radioStationMedia), res);
	}

	private void f4143001(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPRadioStation radioStation = new FFPRadioStation();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), radioStation);
		pageWrite.outputJSON(radioStationService.deleteRadioStation(radioStation), res);
	}

	private void f4142001(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPRadioStation radioStation = new FFPRadioStation();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), radioStation);

		pageWrite.outputJSON(radioStationService.editRadioStation(radioStation), res);
	}

	private void f4141002(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPRadioStation radioStation = new FFPRadioStation();
		radioStation.setId(oConvertUtils.getString(req.getParameter("id")));

		FFPRadioStationMedia radioStationMedia = new FFPRadioStationMedia();
		radioStationMedia.setRadioStation(radioStation);
		try{
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
			pageWrite.outputJSON(radioStationService.addRadioStationMediaFile(radioStationMedia, multipartRequest.getFile("file")), res);
		} catch (ClassCastException e){
			ReturnBody body = new ReturnBody();
			body.setStatus(Glossary.Return_State_Parameter_Error);
			body.setMsg("parameter error [file], the file must not be empty");
			pageWrite.outputJSON(body, res);
		}
	}

	private void f4141001(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPRadioStation radioStation = new FFPRadioStation();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), radioStation);
		ReturnBody body = null;

		// 获取用户上传的文件
		try{
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
			body = radioStationService.addRadioStation(radioStation, multipartRequest.getFileMap());
		} catch (ClassCastException e){
			body = radioStationService.addRadioStation(radioStation, null);
		}

		if (!StringUtil.isEmpty(radioStation.getShape()) && StringUtil.isNotEmpty(body) && body.getStatus() == Glossary.Return_State_Success){

			int rows = radioStationService.executeSql("UPDATE ffp_radio_station SET shape=POINTFROMTEXT('"+radioStation.getShape()+"') WHERE id=?", body.getObj());
			if (rows> 0){
				pageWrite.outputJSON(body, res);
				return;
			}

			radioStationService.deleteRadioStation(radioStationService.get(FFPRadioStation.class, body.getObj().toString()));
			body.setStatus(Glossary.Return_State_Exception_Operation);
			body.setMsg("operation failed.");
			pageWrite.outputJSON(body, res);
			return;
		}
		pageWrite.outputJSON(body, res);

	}

	private void f4140001(HttpServletRequest req, HttpServletResponse res, DataGrid dataGrid) throws Exception{
		FFPRadioStation radioStation = new FFPRadioStation();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), radioStation);
		int fetchType = oConvertUtils.getInt(req.getParameter("fetchType"));
		pageWrite.outputJSON(radioStationService.getRadioStationList(radioStation, dataGrid, fetchType), res);
	}

	private void f4133002(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPObservatoryMedia observatoryMedia = new FFPObservatoryMedia();
		observatoryMedia.setId(oConvertUtils.getString(req.getParameter("id")));

		pageWrite.outputJSON(observatoryService.deleteObservatoryMedia(observatoryMedia), res);
	}

	private void f4133001(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPObservatory observatory = new FFPObservatory();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), observatory);
		pageWrite.outputJSON(observatoryService.deleteObservatory(observatory), res);
	}

	private void f4132001(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPObservatory observatory = new FFPObservatory();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), observatory);
		pageWrite.outputJSON(observatoryService.editObservatory(observatory), res);
	}

	private void f4131002(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPObservatory observatory = new FFPObservatory();
		observatory.setId(oConvertUtils.getString(req.getParameter("id")));

		FFPObservatoryMedia observatoryMedia = new FFPObservatoryMedia();
		observatoryMedia.setObservatory(observatory);
		try{
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
			pageWrite.outputJSON(observatoryService.addObservatoryMedia(observatoryMedia, multipartRequest.getFile("file")), res);
		} catch (ClassCastException e){
			ReturnBody body = new ReturnBody();
			body.setStatus(Glossary.Return_State_Parameter_Error);
			body.setMsg("parameter error [file], the file must not be empty.");
			pageWrite.outputJSON(body, res);
		}
	}

	private void f4131001(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPObservatory observatory = new FFPObservatory();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), observatory);
		ReturnBody body = null;
		// 获取用户上传的文件
		try{
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
			body = observatoryService.addObservatory(observatory, multipartRequest.getFileMap());
		} catch (ClassCastException e){
			body = observatoryService.addObservatory(observatory, null);
		}

		if (!StringUtil.isEmpty(observatory.getShape()) && StringUtil.isNotEmpty(body) && body.getStatus() == Glossary.Return_State_Success){

			int rows = observatoryService.executeSql("UPDATE ffp_observatory SET shape=POINTFROMTEXT('"+observatory.getShape()+"') WHERE id=?", body.getObj());
			if (rows> 0){
				pageWrite.outputJSON(body, res);
				return;
			}

			observatoryService.deleteObservatory(observatoryService.get(FFPObservatory.class, body.getObj().toString()));
			body.setStatus(Glossary.Return_State_Exception_Operation);
			body.setMsg("operation failed.");
			pageWrite.outputJSON(body, res);
			return;
		}
		pageWrite.outputJSON(body, res);
	}

	private void f4130001(HttpServletRequest req, HttpServletResponse res, DataGrid dataGrid) throws Exception{
		FFPObservatory observatory = new FFPObservatory();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), observatory);
		int fetchType = oConvertUtils.getInt(req.getParameter("fetchType"));
		pageWrite.outputJSON(observatoryService.getObservatoryList(observatory, dataGrid, fetchType), res);
	}

	private void f4123002(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPRemoteMonitoringMedia monitoringMedia = new FFPRemoteMonitoringMedia();
		monitoringMedia.setId(oConvertUtils.getString(req.getParameter("id")));
		pageWrite.outputJSON(remoteMonitoringService.deleteRemoteMonitoringMedia(monitoringMedia), res);
	}

	private ReturnBody f4123001(HttpServletRequest req) throws Exception{
		FFPRemoteMonitoring remotemonitoring = new FFPRemoteMonitoring();
		BeanUtils.populate(remotemonitoring, req.getParameterMap());
		return remoteMonitoringService.deleteRemoteMonitoring(remotemonitoring);
	}

	private ReturnBody f4122001(HttpServletRequest req) throws Exception{
		FFPRemoteMonitoring remotemonitoring = new FFPRemoteMonitoring();

		MyBeanUtils.transMap2Bean(req.getParameterMap(), remotemonitoring);

		return remoteMonitoringService.editRemoteMonitoring(remotemonitoring);
	}

	private void f4121002(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPRemoteMonitoring remoteMonitoring = new FFPRemoteMonitoring();
		remoteMonitoring.setId(oConvertUtils.getString(req.getParameter("id")));

		FFPRemoteMonitoringMedia monitoringMedia = new FFPRemoteMonitoringMedia();
		monitoringMedia.setMonitoring(remoteMonitoring);
		// 获取用户上传的文件
		try{
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
			pageWrite.outputJSON(remoteMonitoringService.addRemoteMonitoringMedia(monitoringMedia, multipartRequest.getFile("file")), res);
		} catch (ClassCastException e){
			ReturnBody body = new ReturnBody();
			body.setStatus(Glossary.Return_State_Parameter_Error);
			body.setMsg("parameter error [file], the file must not be empty.");
			pageWrite.outputJSON(body, res);
		}
	}

	private ReturnBody f4121001(HttpServletRequest req) throws Exception{
		FFPRemoteMonitoring remoteMonitoring = new FFPRemoteMonitoring();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), remoteMonitoring);

		ReturnBody body = null;
		// 获取用户上传的文件
		try{
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
			body = remoteMonitoringService.addRemoteMonitoring(remoteMonitoring, multipartRequest.getFileMap());
		} catch (ClassCastException e){
			body = remoteMonitoringService.addRemoteMonitoring(remoteMonitoring, null);
		}

		if (!StringUtil.isEmpty(remoteMonitoring.getShape()) && StringUtil.isNotEmpty(body) && body.getStatus() == Glossary.Return_State_Success){

			int rows = remoteMonitoringService.executeSql("UPDATE ffp_fire_remotemonitoring SET shape=POINTFROMTEXT('"+remoteMonitoring.getShape()+"') WHERE id=?", body.getObj());
			if (rows> 0){
				return body;
			}

			remoteMonitoringService.deleteRemoteMonitoring(remoteMonitoringService.get(FFPRemoteMonitoring.class, body.getObj().toString()));
			body.setStatus(Glossary.Return_State_Exception_Operation);
			body.setMsg("operation failed.");
			return body;
		}
		return body;
	}

	private void f4120003(HttpServletRequest req, HttpServletResponse res, DataGrid dataGrid) throws Exception{
		FFPHotFeedback feedback = new FFPHotFeedback();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), feedback);
		pageWrite.outputJSON(hotFeedbackService.getHotFeedbackList(feedback, dataGrid), res);
	}

	private void f4120002(HttpServletRequest req, HttpServletResponse res) throws Exception{
		ExcelUtil excelUtil = new ExcelUtil();
		excelUtil.export2007(res, JSONHelper.toList(systemService.loadAll(FFPFireHot.class)),"火灾热点", new String[]{"列表"});
	}
	private ReturnDataGrid f4120001(HttpServletRequest req, DataGrid dataGrid) throws Exception{
		FFPRemoteMonitoring remoteMonitoring = new FFPRemoteMonitoring();
		BeanUtils.populate(remoteMonitoring, req.getParameterMap());
		int fetchType = oConvertUtils.getInt(req.getParameter("fetchType"));
		return remoteMonitoringService.getRemoteMonitoringList(remoteMonitoring, dataGrid, fetchType);
	}

	private ReturnBody f4110002(HttpServletRequest req) throws Exception{
		FFPFireHot hot = new FFPFireHot();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), hot);
		return fireHotService.getHotDetail(hot);
	}
	private ReturnDataGrid f4110001(HttpServletRequest req, DataGrid dataGrid) throws Exception{
		FFPFireHot hot = new FFPFireHot();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), hot);
		int fetchType = oConvertUtils.getInt(req.getParameter("fetchType"));
		String reportTime_start_string = oConvertUtils.getString(req.getParameter("reporttime_start"));
		String reportTime_end_string = oConvertUtils.getString(req.getParameter("reporttime_end"));
		Date reportTime_start = null;
		Date reportTime_end = null;
		long h = 0;//小时
		long m = 0;//分钟
		long s = 0;//秒
		long hms = h + m + s;
		if (!StringUtil.isEmpty(reportTime_start_string)){
			switch (reportTime_start_string.trim().length()){
				case 10:
					reportTime_start = DateUtils.parseDate(reportTime_start_string, "yyyy-MM-dd");
					break;
				case 13:
					reportTime_start = DateUtils.parseDate(reportTime_start_string, "yyyy-MM-dd HH");
					break;
				case 16:
					reportTime_start = DateUtils.parseDate(reportTime_start_string, "yyyy-MM-dd HH:mm");
					break;
				default:
					reportTime_start = DateUtils.parseDate(reportTime_start_string, "yyyy-MM-dd HH:mm:ss");
					break;
			}

		}
		if (!StringUtil.isEmpty(reportTime_end_string)){
			switch (reportTime_end_string.trim().length()){
				case 10:
					reportTime_end = DateUtils.parseDate(reportTime_end_string, "yyyy-MM-dd");
					h = 1000*60*60*23;//小时
					m = 1000*60*59;//分钟
					s = 1000*59;//秒
					hms = h + m + s;
					reportTime_end = new Date(reportTime_end.getTime()+hms);
					break;
				case 13:
					reportTime_end = DateUtils.parseDate(reportTime_end_string, "yyyy-MM-dd HH");
					m = 1000*60*59;//分钟
					s = 1000*59;//秒
					hms = m + s;
					reportTime_end = new Date(reportTime_end.getTime()+hms);
					break;
				case 16:
					reportTime_end = DateUtils.parseDate(reportTime_end_string, "yyyy-MM-dd HH:mm");
					s = 1000*59;//秒
					reportTime_end = new Date(reportTime_end.getTime()+s);
					break;
				default:
					reportTime_end = DateUtils.parseDate(reportTime_end_string, "yyyy-MM-dd HH:mm:ss");
					break;
			}
		}
		return fireHotService.getHotList(hot, dataGrid, fetchType, reportTime_start, reportTime_end);
	}


	private void f4111002(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPFireHot hot = new FFPFireHot();
		hot.setId(oConvertUtils.getString(req.getParameter("id")));

		FFPFireHotMedia hotMedia = new FFPFireHotMedia();
		hotMedia.setFireHot(hot);
		// 获取用户上传的文件
		try{
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
			pageWrite.outputJSON(fireHotService.addHotMedia(hotMedia, multipartRequest.getFile("file")), res);
		} catch (ClassCastException e){
			ReturnBody body = new ReturnBody();
			body.setStatus(Glossary.Return_State_Parameter_Error);
			body.setMsg("Parameter error [file], The file must not be empty.");
			pageWrite.outputJSON(body, res);
		}
	}

	private ReturnBody f4111001(HttpServletRequest req) throws Exception{
		FFPFireHot hot = new FFPFireHot();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), hot);

		ReturnBody body = null;
		// 获取用户上传的文件
		try{
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
			body = fireHotService.addHot(hot, multipartRequest.getFileMap());
		} catch (ClassCastException e){
			body = fireHotService.addHot(hot, null);
		}

		if (!StringUtil.isEmpty(hot.getShape()) && StringUtil.isNotEmpty(body) && body.getStatus() == Glossary.Return_State_Success){

			int rows = fireHotService.executeSql("UPDATE ffp_fire_hot SET shape=POINTFROMTEXT('"+hot.getShape()+"') WHERE id=?", body.getObj());
			if (rows> 0){
				return body;
			}

			fireHotService.deleteHot(fireHotService.get(FFPFireHot.class, body.getObj().toString()));
			body.setStatus(Glossary.Return_State_Exception_Operation);
			body.setMsg("operation failed.");
			return body;
		}
		return body;
	}

	private void f4113003(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPHotFeedbackMedia feedbackMedia = hotFeedbackService.get(FFPHotFeedbackMedia.class, oConvertUtils.getString(req.getParameter("id")));
		pageWrite.outputJSON(hotFeedbackService.deleteHotFeedbackMedia(feedbackMedia), res, req);
	}

	private void f4113002(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPFireHotMedia hotMedia = new FFPFireHotMedia();
		hotMedia.setId(oConvertUtils.getString(req.getParameter("id")));

		pageWrite.outputJSON(fireHotService.deleteHotMedia(hotMedia), res);
	}

	private ReturnBody f4113001(HttpServletRequest req) throws Exception{
		FFPFireHot hot = new FFPFireHot();
		BeanUtils.populate(hot, req.getParameterMap());
		return fireHotService.deleteHot(hot);
	}

	private void f4112003(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPHotFeedback hotFeedback = new FFPHotFeedback();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), hotFeedback);
		pageWrite.outputJSON(hotFeedbackService.editHotFeedback(hotFeedback), res, req);

	}

	private void f4112002(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPHotFeedback hotFeedback = new FFPHotFeedback();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), hotFeedback);
		pageWrite.outputJSON(hotFeedbackService.examineHotFeedback(hotFeedback), res);
	}

	private ReturnBody f4112001(HttpServletRequest req) throws Exception{
		FFPFireHot hot = new FFPFireHot();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), hot);
		return fireHotService.editHot(hot);
	}

	private void f322003(HttpServletRequest req, HttpServletResponse res) throws Exception {
		FFPLicense license = new FFPLicense();
		BeanUtils.populate(license, req.getParameterMap());
		pageWrite.outputJSON(licenseService.registLicense(license), res);
	}

	private ReturnBody f322002(HttpServletRequest req) throws Exception {
		FFPLicense license = new FFPLicense();
		MyBeanUtils.transMap2Bean( req.getParameterMap(), license);
		return licenseService.updateLicense(license);
	}

	private ReturnBody f320001(HttpServletRequest req) throws Exception {
		return licenseService.isExistLicense(oConvertUtils.getString(req.getParameter("imei")));
	}

	private ReturnBody f322001(HttpServletRequest req) throws Exception {
		String key = oConvertUtils.getString(req.getParameter("key"));
		String imei = oConvertUtils.getString(req.getParameter("imei"));
		return licenseService.removeLicense(key, imei);
	}

	private ReturnBody f323001(HttpServletRequest req) throws Exception {
		return licenseService.deleteLicense(oConvertUtils.getInt(req.getParameter("id")));
	}

	private ReturnBody f321001(HttpServletRequest req) throws Exception {
		FFPLicense license = new FFPLicense();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), license);

		TSUser user = new TSUser();
		BeanUtils.populate(user, req.getParameterMap());
		int number = oConvertUtils.getInt(req.getParameter("number"),1);

		return licenseService.insertLicenseExternal(license, user, number);
	}


	private void f222002(HttpServletRequest req, HttpServletResponse res) throws Exception{
		int id = oConvertUtils.getInt(req.getParameter("id"));
		String password = oConvertUtils.getString(req.getParameter("password"));
		String passwordNew = oConvertUtils.getString(req.getParameter("passwordNew"));
		pageWrite.outputJSON(userService.updateUserPassword(id, password, passwordNew), res, req);
	}

	private ReturnBody f222001(HttpServletRequest req) throws Exception {

		TSUser user = new TSUser();
		BeanUtils.populate(user, req.getParameterMap());

		TSDepart depart = new TSDepart();
		depart.setId(oConvertUtils.getString(req.getParameter("depart_id")));

		TSDepartUser departUser = new TSDepartUser();
		departUser.setDepart(depart);

		String password = oConvertUtils.getString(req.getParameter("password"));

		try{
			// 获取用户上传的文件
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
			MultipartFile headPortrait = multipartRequest.getFile("headPortrait");

			return userService.editUser(user, headPortrait, departUser, password);
		} catch (ClassCastException e){
			return userService.editUser(user, null, departUser, password);
		}

	}

	private ReturnBody f220002(HttpServletRequest req) throws Exception{

		TSUser user = new TSUser();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), user);

		return userService.getUser(user, req);
	}



	private ReturnDataGrid f210003(HttpServletRequest req, DataGrid dataGrid) throws Exception{
		FFPLicense license = new FFPLicense();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), license);
		if ( StringUtil.isEmpty( dataGrid.getSort() )) {
			//默认以id排序
			dataGrid.setSort("id");
		}
		int fetchStatus = oConvertUtils.getInt(req.getParameter("fetchStatus"));
		int fetchType = oConvertUtils.getInt(req.getParameter("fetchType"));
		int license_status = oConvertUtils.getInt(req.getParameter("license_status"));
		String createTime_start_str = oConvertUtils.getString(req.getParameter("createTime_start"));
		String createTime_end_str = oConvertUtils.getString(req.getParameter("createTime_end"));
		Date createTime_start = null;
		Date createTime_end = null;
		if (!StringUtil.isEmpty(createTime_start_str)){
			createTime_start = DateUtils.str2Date(createTime_start_str, DateUtils.date_sdf);
		}
		if (!StringUtil.isEmpty(createTime_end_str)){
			createTime_end = DateUtils.str2Date(createTime_end_str, DateUtils.date_sdf);
		}
		return licenseService.getLicenseDataGrid(license,dataGrid, fetchType, createTime_start, createTime_end, fetchStatus, license_status);
	}

	private void f233001(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPAreaDepart areaDepart = new FFPAreaDepart();
		areaDepart.setId(oConvertUtils.getString(req.getParameter("id")));
		int type = oConvertUtils.getInt(req.getParameter("type"));
		pageWrite.outputJSON(areaDepartService.deleteAreaDepart(areaDepart, type), res);
	}

	private void f232001(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPAreaDepart areaDepart = new FFPAreaDepart();


		TSDepart depart = new TSDepart();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), depart);
		areaDepart.setDepart(depart);

		pageWrite.outputJSON(areaDepartService.editAreaDepart(areaDepart), res, req);
	}

	private void f231001(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPAreaDepart areaDepart = new FFPAreaDepart();

		FFPArea area = new FFPArea();
		area.setCode(oConvertUtils.getString(req.getParameter("pac")));

		TSDepart depart = new TSDepart();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), depart);

		areaDepart.setArea(area);
		areaDepart.setDepart(depart);

		pageWrite.outputJSON(areaDepartService.addAreaDepart(areaDepart), res, req);
	}
	private void f230001(HttpServletRequest req, HttpServletResponse res, DataGrid dataGrid) throws Exception{
		FFPAreaDepart areaDepart = new FFPAreaDepart();

		FFPArea area = new FFPArea();
		area.setCode(oConvertUtils.getString(req.getParameter("pac")));

		areaDepart.setArea(area);

		int fetchType = oConvertUtils.getInt(req.getParameter("fetchType"));
		pageWrite.outputJSON(areaDepartService.getAreaDepartList(areaDepart, dataGrid, fetchType), res, req);
	}

	private ReturnBody f223001(HttpServletRequest req) throws Exception{
		TSUser user = new TSUser();
		BeanUtils.populate(user, req.getParameterMap());
		return userService.delUser(user);
	}

	private ReturnBody f221001(HttpServletRequest req) throws Exception{
		TSUser user = new TSUser();
		BeanUtils.populate(user, req.getParameterMap());
		return userService.addUser(user);
	}

	private void f213001(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPAreaBoundary area = new FFPAreaBoundary();
		area.setCode(oConvertUtils.getString(req.getParameter("code")));
		pageWrite.outputJSON(areaService.deleteArea(area), res);
	}

	private void f212001(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPAreaBoundary area = new FFPAreaBoundary();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), area);
		pageWrite.outputJSON(areaService.editArea(area), res);
	}

	private void f211002(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPAreaBoundary area = new FFPAreaBoundary();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), area);

		ReturnBody body = areaService.addAreaSimply(area);

		if (body.getStatus() == Glossary.Return_State_Success){

			if (!StringUtil.isEmpty(area.getBoundary())){

				try{
					WKTReader fromText = new WKTReader();
					fromText.read(area.getBoundary());
					areaService.executeSql("UPDATE ffp_area_code_boundary SET boundary=POINTFROMTEXT('"+area.getBoundary()+"') WHERE code=?", body.getObj());
				} catch (ParseException e) {
					body.setStatus(Glossary.Return_State_Geometry_Format_Error);
					body.setMsg( "Parameter error [boundary].");
					areaService.deleteEntityById(FFPAreaBoundary.class, oConvertUtils.getString(body.getObj()));
					pageWrite.outputJSON(body, res);
					return;
				}

			}
		}
		pageWrite.outputJSON(body, res, req);
	}
	private void f211001(HttpServletRequest req, HttpServletResponse res) throws Exception{
		FFPArea area = new FFPArea();
		MyBeanUtils.transMap2Bean(req.getParameterMap(), area);
		pageWrite.outputJSON(areaService.addArea(area), res, req);
	}

	private void f210006(HttpServletRequest req, HttpServletResponse res) throws Exception{
		String code = oConvertUtils.getString(req.getParameter("code"));
		pageWrite.outputJSON(areaService.peggingPac(code, req), res);
	}

	private void f210005(HttpServletRequest req, HttpServletResponse res) throws Exception{
		String code = oConvertUtils.getString(req.getParameter("code"));
		int format_boundary_type = oConvertUtils.getInt(req.getParameter("format_boundary_type"));
		pageWrite.outputJSON(areaService.getArea(code, format_boundary_type), res);
	}
	private void f210004(HttpServletRequest req,HttpServletResponse res, DataGrid dataGrid) throws Exception{
		String code = oConvertUtils.getString(req.getParameter("id"));
		pageWrite.outputJSON(areaService.areasAll(code).getObj(), res, req);
	}

	private ReturnDataGrid f210002(HttpServletRequest req, DataGrid dataGrid) throws Exception{
		String pac = oConvertUtils.getString(req.getParameter("pac"));
		int fetchType = oConvertUtils.getInt(req.getParameter("fetchType"));
		int fetchBoundary = oConvertUtils.getInt(req.getParameter("fetchBoundary"));
		return areaService.getPac(pac, dataGrid, fetchType, fetchBoundary);
	}

	private ReturnBody f210001(HttpServletRequest req) throws Exception{
		String code = oConvertUtils.getString(req.getParameter("id"));
		return areaService.areas(code);
	}

	private ReturnDataGrid f220001(HttpServletRequest req, DataGrid dataGrid) throws Exception {
		TSUser user = new TSUser();
		MyBeanUtils.transMap2Bean( req.getParameterMap(), user);
		int fetchType = oConvertUtils.getInt(req.getParameter("fetchType"));
		return userService.pacByUser(user, dataGrid, fetchType, req);
	}


}
