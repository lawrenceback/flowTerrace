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
 * Created by YanWei on 2017/8/2.
 *
 * 防火林带
 *
 */
@Entity
@Table(name = "ffp_forest_beltPoint")
public class FFPForestBeltPoint extends IdEntity implements java.io.Serializable {
	private String pac;     //行政代码
	private String shape;      //形状
	private String start_addr;  //起始地点
	private String stop_addr;   //终止地点
	private String name;        //名称
	private String build_addr;  //营造位置
	private Date build_year;     //营造年度
	private String tree_type;       //树种
	private String type;           //类型；主防火林带_1,副防火林带_2,防火隔离带_3,自然阻隔带_4,防火道路_5,其它阻隔带_6
	private String build_unit;      //营造单位；林业局_1,林场_2,村庄_3,个体_4
	private Double belt_len;     //长度
	private Double belt_width;   //宽度
	private Integer status;         //状态；优秀_1,良好_2,一般_3,差_4,废弃_5
	private String note;        //说明
	private Double longitude;   //经度
	private Double latitude;    //纬度
	private String row_spacing;  //株行距
	private String city_name;   //地区
	private String county_name; //县名
	private String manager; //管理员
	private String phone;   //管理员电话
	private String management_unit; //管理单位

	List<TSMediaFile> mediaFiles = new ArrayList<>();
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

	public String getStart_addr() {
		return start_addr;
	}

	public void setStart_addr(String start_addr) {
		this.start_addr = start_addr;
	}

	public String getStop_addr() {
		return stop_addr;
	}

	public void setStop_addr(String stop_addr) {
		this.stop_addr = stop_addr;
	}

	@Column(nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBuild_addr() {
		return build_addr;
	}

	public void setBuild_addr(String build_addr) {
		this.build_addr = build_addr;
	}

	public Date getBuild_year() {
		return build_year;
	}

	public void setBuild_year(Date build_year) {
		this.build_year = build_year;
	}

	public String getTree_type() {
		return tree_type;
	}

	public void setTree_type(String tree_type) {
		this.tree_type = tree_type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBuild_unit() {
		return build_unit;
	}

	public void setBuild_unit(String buildUnit) {
		this.build_unit = build_unit;
	}

	public Double getBelt_len() {
		return belt_len;
	}

	public void setBelt_len(Double belt_len) {
		this.belt_len = belt_len;
	}

	public Double getBelt_width() {
		return belt_width;
	}

	public void setBelt_width(Double belt_width) {
		this.belt_width = belt_width;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(columnDefinition = "LONGTEXT")
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
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

	public String getRow_spacing() {
		return row_spacing;
	}

	public void setRow_spacing(String row_spacing) {
		this.row_spacing = row_spacing;
	}

	@Transient
	public List<TSMediaFile> getMediaFiles() {
		return mediaFiles;
	}

	public void setMediaFiles(List<TSMediaFile> mediaFiles) {
		this.mediaFiles = mediaFiles;
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

	public String getManager() {
		return manager;
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

	public String getManagement_unit() {
		return management_unit;
	}

	public void setManagement_unit(String management_unit) {
		this.management_unit = management_unit;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
