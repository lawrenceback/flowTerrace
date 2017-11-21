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
 * Created by YanWei on 2017/7/27.
 *
 * 瞭望台
 *
 */
@Entity
@Table(name = "ffp_observatory")
public class FFPObservatory extends IdEntity implements java.io.Serializable {

	private String pac;
	private String shape;      //形状
	private String name;        //
	private String address;
	private Integer status;     //状态；1_优秀,2_良好,3_一般,4_较差,5_报废
	private String type;       //类型；1_铁质,2_砖瓦,3_木质,4_其他
	private Integer telescope;  //望远镜
	private Integer interphone; //对讲机
	private Integer compass_instrument; //罗盘仪
	private Integer telephone;      //有线电话
	private Integer radio;          //无线电话
	private Double look_area;   //瞭望面积
	private Double look_forest_area ;    //瞭望森林面积
	private Double look_coverage;       //瞭望覆盖率
	private Double c_area ;     //建筑面积
	private Double elevation;   //海拔
	private Date build_year;    //建设时间
	private String manager;     //管理员
	private String phone;       //电话
	private String structure;   //结构
	private String video_surveillance;  //视频监测
	private String build_unit;  //修建单位
	private Double longitude ;  //径度
	private Double latitude ;   //纬度
	private String note;    //说明
	private String city_name;   //市名
	private String county_name; //县名
	private String base_type;   //基地类型
	private String management_unit; //管理单位

	private List<TSMediaFile> mediaFiles = new ArrayList<>();
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

	@Column(nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getTelescope() {
		return telescope;
	}

	public void setTelescope(Integer telescope) {
		this.telescope = telescope;
	}

	public Integer getInterphone() {
		return interphone;
	}

	public void setInterphone(Integer interphone) {
		this.interphone = interphone;
	}

	@Column(name = "compass_instrument")
	public Integer getCompass_instrument() {
		return compass_instrument;
	}

	public void setCompass_instrument(Integer compass_instrument) {
		this.compass_instrument = compass_instrument;
	}

	public Integer getTelephone() {
		return telephone;
	}

	public void setTelephone(Integer telephone) {
		this.telephone = telephone;
	}

	public Integer getRadio() {
		return radio;
	}

	public void setRadio(Integer radio) {
		this.radio = radio;
	}

	@Column(name = "look_area")
	public Double getLook_area() {
		return look_area;
	}

	public void setLook_area(Double look_area) {
		this.look_area = look_area;
	}

	@Column(name = "look_forest_area")
	public Double getLook_forest_area() {
		return look_forest_area;
	}

	public void setLook_forest_area(Double look_forest_area) {
		this.look_forest_area = look_forest_area;
	}

	@Column(name = "look_coverage")
	public Double getLook_coverage() {
		return look_coverage;
	}

	public void setLook_coverage(Double look_coverage) {
		this.look_coverage = look_coverage;
	}

	public Double getC_area() {
		return c_area;
	}

	public void setC_area(Double c_area) {
		this.c_area = c_area;
	}

	public Double getElevation() {
		return elevation;
	}

	public void setElevation(Double elevation) {
		this.elevation = elevation;
	}

	@Column(name = "build_year")
	public Date getBuild_year() {
		return build_year;
	}

	public void setBuild_year(Date build_year) {
		this.build_year = build_year;
	}

	public String getManager() {
		return manager;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}

	public String getCounty_name() {
		return county_name;
	}

	public void setCounty_name(String county_name) {
		this.county_name = county_name;
	}

	public String getBase_type() {
		return base_type;
	}

	public void setBase_type(String base_type) {
		this.base_type = base_type;
	}

	public String getManagement_unit() {
		return management_unit;
	}

	public void setManagement_unit(String management_unit) {
		this.management_unit = management_unit;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStructure() {
		return structure;
	}

	public void setStructure(String structure) {
		this.structure = structure;
	}

	@Column(name = "video_surveillance")
	public String getVideo_surveillance() {
		return video_surveillance;
	}

	public void setVideo_surveillance(String video_surveillance) {
		this.video_surveillance = video_surveillance;
	}

	@Column(name = "build_unit")
	public String getBuild_unit() {
		return build_unit;
	}

	public void setBuild_unit(String build_unit) {
		this.build_unit = build_unit;
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

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
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
