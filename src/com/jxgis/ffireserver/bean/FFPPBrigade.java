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
 * Created by YanWei on 2017/8/23.
 *
 * 专业森林消防队
 *
 */
@Entity
@Table(name = "ffp_pbrigade")
public class FFPPBrigade extends IdEntity implements java.io.Serializable{

	private String shape;   //geometry 几何
	private String name;    //名称
	private String address; //地址
	private String phone;   //值班电话
	private String manager; //管理员
	private Double latitude;    //经度
	private Double longitude;   //纬度
	private Integer fireengine;     //消防车
	private Integer t_car;          //运兵车辆
	private Integer n2n3tool;       //二号工具
	private Integer w_equipm;       //风力灭火机
	private Integer pump;           //灭火水泵
	private Integer fire_bomb;      //灭火弹
	private Integer wsinjector;     //水雾喷射器
	private Integer chainsaw;       //油锯
	private Integer b_cutter;       //割灌机
	private Integer w_cannons;      //灭火水枪
	private Integer interphone;     //对讲机
	private Integer zj_radio;       //中继台
	private Integer sc_radio;       //手持台
	private Integer jd_radio;       //基地台
	private Integer cz_radio;       //车载台
	private Integer gps;            //GPS定位仪
	private Integer machetes;       //砍刀
	private Integer r_clothing;     //阻燃服、防火服
	private Integer flashlight;     //手电筒
	private Integer helmet;         //头盔
	private Integer gloves;         //手套
	private Integer fire_shoes;     //扑火鞋
	private Integer o_equip;        //其它灭火工具
	private Integer dynamotor;      //发电机
	private Integer anemometer;     //风速仪器等
	private Integer communication_v;//通信车
	private Integer ax;             //大斧
	private Integer sleeping_bag;   //睡袋
	private Integer high_pressure_fex;  //高压细水雾灭火机
	private Integer fire_shovel;    //消防铲
	private Integer lighter;        //点火器
	private Integer motorcycle;     //摩托车
	private Integer fire_detectors; //余火探测仪
	private Integer fsf_extinguishers;  //风水灭火机
	private Integer base_value;     //基地产值
	private Integer fire_extinguisher;  //灭火水车
	private Integer tent;           //帐篷
	private String status;      //状态
	private String type;        //类型
	private Double barracks_area;   //营房面积
	private String base_type;   //基地类型
	private String note;        //说明
	private Short nav_handheld; //导航手持终端数量
	private Short nav_vehicle;  //导航车载终端数量
	private String pac;         //行政区划代码
	private List<TSMediaFile> mediaFiles = new ArrayList<>();
	private Date createTime = new Date();

	@Transient
	public String getShape() {
		return shape;
	}

	public void setShape(String shape) {
		this.shape = shape;
	}

	@Column(nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Integer getFireengine() {
		return fireengine;
	}

	public void setFireengine(Integer fireengine) {
		this.fireengine = fireengine;
	}

	public Integer getT_car() {
		return t_car;
	}

	public void setT_car(Integer t_car) {
		this.t_car = t_car;
	}

	public Integer getN2n3tool() {
		return n2n3tool;
	}

	public void setN2n3tool(Integer n2n3tool) {
		this.n2n3tool = n2n3tool;
	}

	public Integer getW_equipm() {
		return w_equipm;
	}

	public void setW_equipm(Integer w_equipm) {
		this.w_equipm = w_equipm;
	}

	public Integer getPump() {
		return pump;
	}

	public void setPump(Integer pump) {
		this.pump = pump;
	}

	public Integer getFire_bomb() {
		return fire_bomb;
	}

	public void setFire_bomb(Integer fire_bomb) {
		this.fire_bomb = fire_bomb;
	}

	public Integer getWsinjector() {
		return wsinjector;
	}

	public void setWsinjector(Integer wsinjector) {
		this.wsinjector = wsinjector;
	}

	public Integer getChainsaw() {
		return chainsaw;
	}

	public void setChainsaw(Integer chainsaw) {
		this.chainsaw = chainsaw;
	}

	public Integer getB_cutter() {
		return b_cutter;
	}

	public void setB_cutter(Integer b_cutter) {
		this.b_cutter = b_cutter;
	}

	public Integer getW_cannons() {
		return w_cannons;
	}

	public void setW_cannons(Integer w_cannons) {
		this.w_cannons = w_cannons;
	}

	public Integer getInterphone() {
		return interphone;
	}

	public void setInterphone(Integer interphone) {
		this.interphone = interphone;
	}

	public Integer getZj_radio() {
		return zj_radio;
	}

	public void setZj_radio(Integer zj_radio) {
		this.zj_radio = zj_radio;
	}

	public Integer getSc_radio() {
		return sc_radio;
	}

	public void setSc_radio(Integer sc_radio) {
		this.sc_radio = sc_radio;
	}

	public Integer getJd_radio() {
		return jd_radio;
	}

	public void setJd_radio(Integer jd_radio) {
		this.jd_radio = jd_radio;
	}

	public Integer getCz_radio() {
		return cz_radio;
	}

	public void setCz_radio(Integer cz_radio) {
		this.cz_radio = cz_radio;
	}

	public Integer getGps() {
		return gps;
	}

	public void setGps(Integer gps) {
		this.gps = gps;
	}

	public Integer getMachetes() {
		return machetes;
	}

	public void setMachetes(Integer machetes) {
		this.machetes = machetes;
	}

	public Integer getR_clothing() {
		return r_clothing;
	}

	public void setR_clothing(Integer r_clothing) {
		this.r_clothing = r_clothing;
	}

	public Integer getFlashlight() {
		return flashlight;
	}

	public void setFlashlight(Integer flashlight) {
		this.flashlight = flashlight;
	}

	public Integer getHelmet() {
		return helmet;
	}

	public void setHelmet(Integer helmet) {
		this.helmet = helmet;
	}

	public Integer getGloves() {
		return gloves;
	}

	public void setGloves(Integer gloves) {
		this.gloves = gloves;
	}

	public Integer getFire_shoes() {
		return fire_shoes;
	}

	public void setFire_shoes(Integer fire_shoes) {
		this.fire_shoes = fire_shoes;
	}

	public Integer getO_equip() {
		return o_equip;
	}

	public void setO_equip(Integer o_equip) {
		this.o_equip = o_equip;
	}

	public Integer getDynamotor() {
		return dynamotor;
	}

	public void setDynamotor(Integer dynamotor) {
		this.dynamotor = dynamotor;
	}

	public Integer getAnemometer() {
		return anemometer;
	}

	public void setAnemometer(Integer anemometer) {
		this.anemometer = anemometer;
	}

	public Integer getCommunication_v() {
		return communication_v;
	}

	public void setCommunication_v(Integer communication_v) {
		this.communication_v = communication_v;
	}

	public Integer getAx() {
		return ax;
	}

	public void setAx(Integer ax) {
		this.ax = ax;
	}

	public Integer getSleeping_bag() {
		return sleeping_bag;
	}

	public void setSleeping_bag(Integer sleeping_bag) {
		this.sleeping_bag = sleeping_bag;
	}

	public Integer getHigh_pressure_fex() {
		return high_pressure_fex;
	}

	public void setHigh_pressure_fex(Integer high_pressure_fex) {
		this.high_pressure_fex = high_pressure_fex;
	}

	public Integer getFire_shovel() {
		return fire_shovel;
	}

	public void setFire_shovel(Integer fire_shovel) {
		this.fire_shovel = fire_shovel;
	}

	public Integer getLighter() {
		return lighter;
	}

	public void setLighter(Integer lighter) {
		this.lighter = lighter;
	}

	public Integer getMotorcycle() {
		return motorcycle;
	}

	public void setMotorcycle(Integer motorcycle) {
		this.motorcycle = motorcycle;
	}

	public Integer getFire_detectors() {
		return fire_detectors;
	}

	public void setFire_detectors(Integer fire_detectors) {
		this.fire_detectors = fire_detectors;
	}

	public Integer getFsf_extinguishers() {
		return fsf_extinguishers;
	}

	public void setFsf_extinguishers(Integer fsf_extinguishers) {
		this.fsf_extinguishers = fsf_extinguishers;
	}

	public Integer getBase_value() {
		return base_value;
	}

	public void setBase_value(Integer base_value) {
		this.base_value = base_value;
	}

	public Integer getFire_extinguisher() {
		return fire_extinguisher;
	}

	public void setFire_extinguisher(Integer fire_extinguisher) {
		this.fire_extinguisher = fire_extinguisher;
	}

	public Integer getTent() {
		return tent;
	}

	public void setTent(Integer tent) {
		this.tent = tent;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getBarracks_area() {
		return barracks_area;
	}

	public void setBarracks_area(Double barracks_area) {
		this.barracks_area = barracks_area;
	}

	public String getBase_type() {
		return base_type;
	}

	public void setBase_type(String base_type) {
		this.base_type = base_type;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Short getNav_handheld() {
		return nav_handheld;
	}

	public void setNav_handheld(Short nav_handheld) {
		this.nav_handheld = nav_handheld;
	}

	public Short getNav_vehicle() {
		return nav_vehicle;
	}

	public void setNav_vehicle(Short nav_vehicle) {
		this.nav_vehicle = nav_vehicle;
	}

	@Column(nullable = false)
	public String getPac() {
		return pac;
	}

	public void setPac(String pac) {
		this.pac = pac;
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
