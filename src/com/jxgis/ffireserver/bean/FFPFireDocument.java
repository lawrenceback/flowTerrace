package com.jxgis.ffireserver.bean;

import com.jxgis.ffireserver.web.bean.IdEntity;
import com.jxgis.ffireserver.web.bean.TSMediaFile;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by YanWei on 2017/8/16.
 *
 * 火灾档案
 */
@Entity
@Table(name = "ffp_fire_document")
public class FFPFireDocument extends IdEntity implements java.io.Serializable {


	private String pac;     //行政区划代码
	private String shape;   //几何
	private String city;    //市
	private String county;  //区/县
	private String no;      //火灾编号
	private String name;    //火灾名称
	private String hotid;   //热点编号
	private String address; //起火地点
	private Double longitude;   //经度
	private Double latitude;    //纬度
	private String s_time;      //起火时间
	private String f_time;      //发现时间
	private String e_time;      //扑灭时间
	private Double duration;    //延续时间
	private String firetype;    //火灾类型
	private String cause;       //起火原因
	private Double firearea;    //火场总面积
	private Double nf_area;     //受害天然林
	private Double af_proport;  //受害人工林
	private String t_categ;     //林种
	private String ta_categ;    //龄组
	private String d_categ;     //优势树种
	private String treeform;    //树种组成
	private Double woodloss;    //损失林木
	private Double iwoodloss;   //损失林木_成林蓄积
	private Double saplloss;    //损失林木_幼林株数
	private Integer inj_minor;      //人员伤亡_轻伤
	private Integer inj_severe;     //人员伤亡_重伤
	private Integer inj_death;      //人员伤亡_死亡
	private String conductor;   //火场指挥员
	private String con_post;    //火场指挥员职务
	private Integer brigade;        //出动专业队
	private Integer for_police;     //出动森警
	private Integer army;           //出动军队
	private Integer arm_police;     //出动武警
	private Integer masses;         //出动群众
	private Integer plane;          //出动飞机架次
	private String planetype;   //机型
	private Double flighttime;  //飞行时间
	private Double flightcost;  //飞行费
	private Integer planefall;      //机降架次
	private Integer fallpeople;     //机降人次
	private Integer p_chemical;     //化灭架次
	private Integer p_bucket;       //吊桶次数
	private Integer commandcar;     //出动车辆_指挥车
	private Integer trans_car;      //出动车辆_运输车
	private Integer armoredcar;     //出动车辆_装甲车
	private Integer othercar;       //出动车辆_其它车辆
	private Integer shortwave;      //携带电台_短波
	private Integer uswave;         //携带电台_超短波
	private Integer windequip;      //出动风力灭火机
	private Integer watergun;       //出动水枪
	private Integer n2n3tool;       //二、三号工具
	private Integer otherequip;     //其他工具
	private Double fightcost;   //扑火经费
	private Double otherloss;   //其他损失折款
	private String wrecker;     //肇事者姓名
	private Integer wreckerage;     //肇事者年龄
	private String wreckerpro;  //肇事者职业
	private String wreckerunit; //肇事者单位
	private String wapunish;    //肇事者行政处分
	private String wcpunish;    //肇事者刑事处罚
	private String hapunish;    //领导行政处分
	private String hcpunish;    //领导刑事处罚
	private Double tempera;     //气温
	private Double humidity;    //湿度
	private String winddir;     //风向
	private String windpower;   //风力
	private Double windspeed;   //风速
	private Double rainfall;    //降雨量
	private String description; //火情简介或说明
	private String province;    //省
	private String town_name;   //乡镇名
	private String village_name;    //村名
	private Double elevation;       //海拔
	private String aspect;          //坡向
	private String slope_position;  //坡位
	private Double slope;           //坡度
	private String land_type;       //地类
	private String front_command_name;  //前线指挥姓名
	private String front_command_post;  //前线指挥职务
	private String rear_command_name;   //后方指挥姓名
	private String rear_command_post;   //后方指挥职务
	private Integer cadre;              //干部人数
	private Integer for_cop;            //森林公安人数
	private Integer brigade_people;     //专业扑火队人数
	private Integer hbrigade_people;    //半专业扑火队人数
	private Integer hbrigade;           //半专业扑火队
	private Integer communication_car;  //出动车辆_通讯车
	private Integer support_car;        //出动车辆_保障车
	private Integer hpw_tanker;         //出动车辆_高压水车
	private Integer bulldozer;          //出动车辆_推土机
	private Integer interphone;         //对讲机数量
	private Integer satellitephone;     //卫星电话数量
	private Integer other_communication;//其它通讯工具数量
	private Integer nav_handheld;       //导航手持终端数量
	private Integer nav_vehicle;        //导航车载终端数量
	private Integer high_pressure_fex;  //高压细水雾灭火机
	private Integer pump;               //灭火水泵数量
	private Double artificial_rain; //人工增雨
	private Integer fire_bombs;         //灭火弹数量
	private Integer b_cutter;       //割灌机数量
	private Integer chainsaw;       //油锯数量
	private Integer camera1;        //相机数量
	private Integer camera2;        //摄像机数量
	private String caseInfo;        //案件处理
	private String wayofaccident;   //肇事方式
	private String weather;         //天气状况
	private Double high_tempera;    //最高气温
	private Double low_tempera;     //最低气温
	private Double low_humidity;    //最低湿度
	private String code;            //信息所属行政区代码
	private Double forestarea;      //受害森林面积
	private Double newforestarea;   //受害新造林地面积
	private Integer artificialfireout;  //出动扑火人工（工日）
	private Integer totavehiclenumber;  //出动车辆总计
	private Integer carnumber;          //其中汽车
	private Integer planeoutnumber;     //出动飞机（架次）
	private Integer WingAirPlaneNumber; //固定翼飞机数量
	private Integer WingAirPlaneTime;   //固定翼飞机飞行时间
	private Integer HelicopterNumber;   //直升机数量
	private Integer HelicopterTime;     //直升机飞行时间
	private Integer WuRenJiNumber;      //无人机数量
	private Integer WuRenjiTime;        //无人机飞行时间
	private Integer IsDealWith;         //是否处理（0.否、1.是）
	private Integer ProcessedNumber;    //已处理人数
	private Integer CriminalNumber;     //刑事处罚人数
	private Integer APenaltyNumber;     //行政处罚人数
	private Integer ASanctionNumber;    //行政处分人数
	private Integer RecordDispositionNumber;        //纪律处分人数
	private String Reporter;        //上报人
	private String ReportTime;      //上报时间
	private List<TSMediaFile> mediaFiles = new ArrayList<>();   //多媒体文件


	private Date createTime = new Date();

	@Column(nullable = false)
	public String getPac() {
		return pac;
	}

	public void setPac(String pac) {
		this.pac = pac;
	}

	@Transient
	public String getShape() {
		return shape;
	}

	public void setShape(String shape) {
		this.shape = shape;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	@Column(nullable = false)
	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	@Column(nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHotid() {
		return hotid;
	}

	public void setHotid(String hotid) {
		this.hotid = hotid;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public String getS_time() {
		return s_time;
	}

	public void setS_time(String s_time) {
		this.s_time = s_time;
	}

	public String getF_time() {
		return f_time;
	}

	public void setF_time(String f_time) {
		this.f_time = f_time;
	}

	public String getE_time() {
		return e_time;
	}

	public void setE_time(String e_time) {
		this.e_time = e_time;
	}

	public Double getDuration() {
		return duration;
	}

	public void setDuration(Double duration) {
		this.duration = duration;
	}

	public String getFiretype() {
		return firetype;
	}

	public void setFiretype(String firetype) {
		this.firetype = firetype;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public Double getFirearea() {
		return firearea;
	}

	public void setFirearea(Double firearea) {
		this.firearea = firearea;
	}

	public Double getNf_area() {
		return nf_area;
	}

	public void setNf_area(Double nf_area) {
		this.nf_area = nf_area;
	}

	public Double getAf_proport() {
		return af_proport;
	}

	public void setAf_proport(Double af_proport) {
		this.af_proport = af_proport;
	}

	public String getT_categ() {
		return t_categ;
	}

	public void setT_categ(String t_categ) {
		this.t_categ = t_categ;
	}

	public String getTa_categ() {
		return ta_categ;
	}

	public void setTa_categ(String ta_categ) {
		this.ta_categ = ta_categ;
	}

	public String getD_categ() {
		return d_categ;
	}

	public void setD_categ(String d_categ) {
		this.d_categ = d_categ;
	}

	public String getTreeform() {
		return treeform;
	}

	public void setTreeform(String treeform) {
		this.treeform = treeform;
	}

	public Double getWoodloss() {
		return woodloss;
	}

	public void setWoodloss(Double woodloss) {
		this.woodloss = woodloss;
	}

	public Double getIwoodloss() {
		return iwoodloss;
	}

	public void setIwoodloss(Double iwoodloss) {
		this.iwoodloss = iwoodloss;
	}

	public Double getSaplloss() {
		return saplloss;
	}

	public void setSaplloss(Double saplloss) {
		this.saplloss = saplloss;
	}

	public Integer getInj_minor() {
		return inj_minor;
	}

	public void setInj_minor(Integer inj_minor) {
		this.inj_minor = inj_minor;
	}

	public Integer getInj_severe() {
		return inj_severe;
	}

	public void setInj_severe(Integer inj_severe) {
		this.inj_severe = inj_severe;
	}

	public Integer getInj_death() {
		return inj_death;
	}

	public void setInj_death(Integer inj_death) {
		this.inj_death = inj_death;
	}

	public String getConductor() {
		return conductor;
	}

	public void setConductor(String conductor) {
		this.conductor = conductor;
	}

	public String getCon_post() {
		return con_post;
	}

	public void setCon_post(String con_post) {
		this.con_post = con_post;
	}

	public Integer getBrigade() {
		return brigade;
	}

	public void setBrigade(Integer brigade) {
		this.brigade = brigade;
	}

	public Integer getFor_police() {
		return for_police;
	}

	public void setFor_police(Integer for_police) {
		this.for_police = for_police;
	}

	public Integer getArmy() {
		return army;
	}

	public void setArmy(Integer army) {
		this.army = army;
	}

	public Integer getArm_police() {
		return arm_police;
	}

	public void setArm_police(Integer arm_police) {
		this.arm_police = arm_police;
	}

	public Integer getMasses() {
		return masses;
	}

	public void setMasses(Integer masses) {
		this.masses = masses;
	}

	public Integer getPlane() {
		return plane;
	}

	public void setPlane(Integer plane) {
		this.plane = plane;
	}

	public String getPlanetype() {
		return planetype;
	}

	public void setPlanetype(String planetype) {
		this.planetype = planetype;
	}

	public Double getFlighttime() {
		return flighttime;
	}

	public void setFlighttime(Double flighttime) {
		this.flighttime = flighttime;
	}

	public Double getFlightcost() {
		return flightcost;
	}

	public void setFlightcost(Double flightcost) {
		this.flightcost = flightcost;
	}

	public Integer getPlanefall() {
		return planefall;
	}

	public void setPlanefall(Integer planefall) {
		this.planefall = planefall;
	}

	public Integer getFallpeople() {
		return fallpeople;
	}

	public void setFallpeople(Integer fallpeople) {
		this.fallpeople = fallpeople;
	}

	public Integer getP_chemical() {
		return p_chemical;
	}

	public void setP_chemical(Integer p_chemical) {
		this.p_chemical = p_chemical;
	}

	public Integer getP_bucket() {
		return p_bucket;
	}

	public void setP_bucket(Integer p_bucket) {
		this.p_bucket = p_bucket;
	}

	public Integer getCommandcar() {
		return commandcar;
	}

	public void setCommandcar(Integer commandcar) {
		this.commandcar = commandcar;
	}

	public Integer getTrans_car() {
		return trans_car;
	}

	public void setTrans_car(Integer trans_car) {
		this.trans_car = trans_car;
	}

	public Integer getArmoredcar() {
		return armoredcar;
	}

	public void setArmoredcar(Integer armoredcar) {
		this.armoredcar = armoredcar;
	}

	public Integer getOthercar() {
		return othercar;
	}

	public void setOthercar(Integer othercar) {
		this.othercar = othercar;
	}

	public Integer getShortwave() {
		return shortwave;
	}

	public void setShortwave(Integer shortwave) {
		this.shortwave = shortwave;
	}

	public Integer getUswave() {
		return uswave;
	}

	public void setUswave(Integer uswave) {
		this.uswave = uswave;
	}

	public Integer getWindequip() {
		return windequip;
	}

	public void setWindequip(Integer windequip) {
		this.windequip = windequip;
	}

	public Integer getWatergun() {
		return watergun;
	}

	public void setWatergun(Integer watergun) {
		this.watergun = watergun;
	}

	public Integer getN2n3tool() {
		return n2n3tool;
	}

	public void setN2n3tool(Integer n2n3tool) {
		this.n2n3tool = n2n3tool;
	}

	public Integer getOtherequip() {
		return otherequip;
	}

	public void setOtherequip(Integer otherequip) {
		this.otherequip = otherequip;
	}

	public Double getFightcost() {
		return fightcost;
	}

	public void setFightcost(Double fightcost) {
		this.fightcost = fightcost;
	}

	public Double getOtherloss() {
		return otherloss;
	}

	public void setOtherloss(Double otherloss) {
		this.otherloss = otherloss;
	}

	public String getWrecker() {
		return wrecker;
	}

	public void setWrecker(String wrecker) {
		this.wrecker = wrecker;
	}

	public Integer getWreckerage() {
		return wreckerage;
	}

	public void setWreckerage(Integer wreckerage) {
		this.wreckerage = wreckerage;
	}

	public String getWreckerpro() {
		return wreckerpro;
	}

	public void setWreckerpro(String wreckerpro) {
		this.wreckerpro = wreckerpro;
	}

	public String getWreckerunit() {
		return wreckerunit;
	}

	public void setWreckerunit(String wreckerunit) {
		this.wreckerunit = wreckerunit;
	}

	public String getWapunish() {
		return wapunish;
	}

	public void setWapunish(String wapunish) {
		this.wapunish = wapunish;
	}

	public String getWcpunish() {
		return wcpunish;
	}

	public void setWcpunish(String wcpunish) {
		this.wcpunish = wcpunish;
	}

	public String getHapunish() {
		return hapunish;
	}

	public void setHapunish(String hapunish) {
		this.hapunish = hapunish;
	}

	public String getHcpunish() {
		return hcpunish;
	}

	public void setHcpunish(String hcpunish) {
		this.hcpunish = hcpunish;
	}

	public Double getTempera() {
		return tempera;
	}

	public void setTempera(Double tempera) {
		this.tempera = tempera;
	}

	public Double getHumidity() {
		return humidity;
	}

	public void setHumidity(Double humidity) {
		this.humidity = humidity;
	}

	public String getWinddir() {
		return winddir;
	}

	public void setWinddir(String winddir) {
		this.winddir = winddir;
	}

	public String getWindpower() {
		return windpower;
	}

	public void setWindpower(String windpower) {
		this.windpower = windpower;
	}

	public Double getWindspeed() {
		return windspeed;
	}

	public void setWindspeed(Double windspeed) {
		this.windspeed = windspeed;
	}

	public Double getRainfall() {
		return rainfall;
	}

	public void setRainfall(Double rainfall) {
		this.rainfall = rainfall;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getTown_name() {
		return town_name;
	}

	public void setTown_name(String town_name) {
		this.town_name = town_name;
	}

	public String getVillage_name() {
		return village_name;
	}

	public void setVillage_name(String village_name) {
		this.village_name = village_name;
	}

	public Double getElevation() {
		return elevation;
	}

	public void setElevation(Double elevation) {
		this.elevation = elevation;
	}

	public String getAspect() {
		return aspect;
	}

	public void setAspect(String aspect) {
		this.aspect = aspect;
	}

	public String getSlope_position() {
		return slope_position;
	}

	public void setSlope_position(String slope_position) {
		this.slope_position = slope_position;
	}

	public Double getSlope() {
		return slope;
	}

	public void setSlope(Double slope) {
		this.slope = slope;
	}

	public String getLand_type() {
		return land_type;
	}

	public void setLand_type(String land_type) {
		this.land_type = land_type;
	}

	public String getFront_command_name() {
		return front_command_name;
	}

	public void setFront_command_name(String front_command_name) {
		this.front_command_name = front_command_name;
	}

	public String getFront_command_post() {
		return front_command_post;
	}

	public void setFront_command_post(String front_command_post) {
		this.front_command_post = front_command_post;
	}

	public String getRear_command_name() {
		return rear_command_name;
	}

	public void setRear_command_name(String rear_command_name) {
		this.rear_command_name = rear_command_name;
	}

	public String getRear_command_post() {
		return rear_command_post;
	}

	public void setRear_command_post(String rear_command_post) {
		this.rear_command_post = rear_command_post;
	}

	public Integer getCadre() {
		return cadre;
	}

	public void setCadre(Integer cadre) {
		this.cadre = cadre;
	}

	public Integer getFor_cop() {
		return for_cop;
	}

	public void setFor_cop(Integer for_cop) {
		this.for_cop = for_cop;
	}

	public Integer getBrigade_people() {
		return brigade_people;
	}

	public void setBrigade_people(Integer brigade_people) {
		this.brigade_people = brigade_people;
	}

	public Integer getHbrigade_people() {
		return hbrigade_people;
	}

	public void setHbrigade_people(Integer hbrigade_people) {
		this.hbrigade_people = hbrigade_people;
	}

	public Integer getHbrigade() {
		return hbrigade;
	}

	public void setHbrigade(Integer hbrigade) {
		this.hbrigade = hbrigade;
	}

	public Integer getCommunication_car() {
		return communication_car;
	}

	public void setCommunication_car(Integer communication_car) {
		this.communication_car = communication_car;
	}

	public Integer getSupport_car() {
		return support_car;
	}

	public void setSupport_car(Integer support_car) {
		this.support_car = support_car;
	}

	public Integer getHpw_tanker() {
		return hpw_tanker;
	}

	public void setHpw_tanker(Integer hpw_tanker) {
		this.hpw_tanker = hpw_tanker;
	}

	public Integer getBulldozer() {
		return bulldozer;
	}

	public void setBulldozer(Integer bulldozer) {
		this.bulldozer = bulldozer;
	}

	public Integer getInterphone() {
		return interphone;
	}

	public void setInterphone(Integer interphone) {
		this.interphone = interphone;
	}

	public Integer getSatellitephone() {
		return satellitephone;
	}

	public void setSatellitephone(Integer satellitephone) {
		this.satellitephone = satellitephone;
	}

	public Integer getOther_communication() {
		return other_communication;
	}

	public void setOther_communication(Integer other_communication) {
		this.other_communication = other_communication;
	}

	public Integer getNav_handheld() {
		return nav_handheld;
	}

	public void setNav_handheld(Integer nav_handheld) {
		this.nav_handheld = nav_handheld;
	}

	public Integer getNav_vehicle() {
		return nav_vehicle;
	}

	public void setNav_vehicle(Integer nav_vehicle) {
		this.nav_vehicle = nav_vehicle;
	}

	public Integer getHigh_pressure_fex() {
		return high_pressure_fex;
	}

	public void setHigh_pressure_fex(Integer high_pressure_fex) {
		this.high_pressure_fex = high_pressure_fex;
	}

	public Integer getPump() {
		return pump;
	}

	public void setPump(Integer pump) {
		this.pump = pump;
	}

	public Double getArtificial_rain() {
		return artificial_rain;
	}

	public void setArtificial_rain(Double artificial_rain) {
		this.artificial_rain = artificial_rain;
	}

	public Integer getFire_bombs() {
		return fire_bombs;
	}

	public void setFire_bombs(Integer fire_bombs) {
		this.fire_bombs = fire_bombs;
	}

	public Integer getB_cutter() {
		return b_cutter;
	}

	public void setB_cutter(Integer b_cutter) {
		this.b_cutter = b_cutter;
	}

	public Integer getChainsaw() {
		return chainsaw;
	}

	public void setChainsaw(Integer chainsaw) {
		this.chainsaw = chainsaw;
	}

	public Integer getCamera1() {
		return camera1;
	}

	public void setCamera1(Integer camera1) {
		this.camera1 = camera1;
	}

	public Integer getCamera2() {
		return camera2;
	}

	public void setCamera2(Integer camera2) {
		this.camera2 = camera2;
	}

	public String getCaseInfo() {
		return caseInfo;
	}

	public void setCaseInfo(String caseInfo) {
		this.caseInfo = caseInfo;
	}

	public String getWayofaccident() {
		return wayofaccident;
	}

	public void setWayofaccident(String wayofaccident) {
		this.wayofaccident = wayofaccident;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public Double getHigh_tempera() {
		return high_tempera;
	}

	public void setHigh_tempera(Double high_tempera) {
		this.high_tempera = high_tempera;
	}

	public Double getLow_tempera() {
		return low_tempera;
	}

	public void setLow_tempera(Double low_tempera) {
		this.low_tempera = low_tempera;
	}

	public Double getLow_humidity() {
		return low_humidity;
	}

	public void setLow_humidity(Double low_humidity) {
		this.low_humidity = low_humidity;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Double getForestarea() {
		return forestarea;
	}

	public void setForestarea(Double forestarea) {
		this.forestarea = forestarea;
	}

	public Double getNewforestarea() {
		return newforestarea;
	}

	public void setNewforestarea(Double newforestarea) {
		this.newforestarea = newforestarea;
	}

	public Integer getArtificialfireout() {
		return artificialfireout;
	}

	public void setArtificialfireout(Integer artificialfireout) {
		this.artificialfireout = artificialfireout;
	}

	public Integer getTotavehiclenumber() {
		return totavehiclenumber;
	}

	public void setTotavehiclenumber(Integer totavehiclenumber) {
		this.totavehiclenumber = totavehiclenumber;
	}

	public Integer getCarnumber() {
		return carnumber;
	}

	public void setCarnumber(Integer carnumber) {
		this.carnumber = carnumber;
	}

	public Integer getPlaneoutnumber() {
		return planeoutnumber;
	}

	public void setPlaneoutnumber(Integer planeoutnumber) {
		this.planeoutnumber = planeoutnumber;
	}

	public Integer getWingAirPlaneNumber() {
		return WingAirPlaneNumber;
	}

	public void setWingAirPlaneNumber(Integer wingAirPlaneNumber) {
		WingAirPlaneNumber = wingAirPlaneNumber;
	}

	public Integer getWingAirPlaneTime() {
		return WingAirPlaneTime;
	}

	public void setWingAirPlaneTime(Integer wingAirPlaneTime) {
		WingAirPlaneTime = wingAirPlaneTime;
	}

	public Integer getHelicopterNumber() {
		return HelicopterNumber;
	}

	public void setHelicopterNumber(Integer helicopterNumber) {
		HelicopterNumber = helicopterNumber;
	}

	public Integer getHelicopterTime() {
		return HelicopterTime;
	}

	public void setHelicopterTime(Integer helicopterTime) {
		HelicopterTime = helicopterTime;
	}

	public Integer getWuRenJiNumber() {
		return WuRenJiNumber;
	}

	public void setWuRenJiNumber(Integer wuRenJiNumber) {
		WuRenJiNumber = wuRenJiNumber;
	}

	public Integer getWuRenjiTime() {
		return WuRenjiTime;
	}

	public void setWuRenjiTime(Integer wuRenjiTime) {
		WuRenjiTime = wuRenjiTime;
	}

	public Integer getIsDealWith() {
		return IsDealWith;
	}

	public void setIsDealWith(Integer isDealWith) {
		IsDealWith = isDealWith;
	}

	public Integer getProcessedNumber() {
		return ProcessedNumber;
	}

	public void setProcessedNumber(Integer processedNumber) {
		ProcessedNumber = processedNumber;
	}

	public Integer getCriminalNumber() {
		return CriminalNumber;
	}

	public void setCriminalNumber(Integer criminalNumber) {
		CriminalNumber = criminalNumber;
	}

	public Integer getAPenaltyNumber() {
		return APenaltyNumber;
	}

	public void setAPenaltyNumber(Integer APenaltyNumber) {
		this.APenaltyNumber = APenaltyNumber;
	}

	public Integer getASanctionNumber() {
		return ASanctionNumber;
	}

	public void setASanctionNumber(Integer ASanctionNumber) {
		this.ASanctionNumber = ASanctionNumber;
	}

	public Integer getRecordDispositionNumber() {
		return RecordDispositionNumber;
	}

	public void setRecordDispositionNumber(Integer recordDispositionNumber) {
		RecordDispositionNumber = recordDispositionNumber;
	}

	public String getReporter() {
		return Reporter;
	}

	public void setReporter(String reporter) {
		Reporter = reporter;
	}

	public String getReportTime() {
		return ReportTime;
	}

	public void setReportTime(String reportTime) {
		ReportTime = reportTime;
	}

	@Transient
	public List<TSMediaFile> getMediaFiles() {
		return mediaFiles;
	}

	public void setMediaFiles(List<TSMediaFile> mediaFiles) {
		this.mediaFiles = mediaFiles;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
