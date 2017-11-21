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
 * Created by YanWei on 2017/8/3.
 *
 * 航空灭火蓄水池
 *
 */
@Entity
@Table(name = "ffp_artificiallake")
public class FFPArtificiallake extends IdEntity implements java.io.Serializable {


	private String pac;         //行政区代码
	private String name;        //名称
	private String manager;     //管理员
	private String address;     //地址
	private String phone;       //管理员电话
	private Double longitude;   //经度
	private Double latitude;    //纬度
	private Double volume;      //容积量
	private String shape;          //形状
	private Integer status;         //状态；优秀_1,良好_2,一般_3,差_4,废弃_5
	private Double storage_area; //蓄水面积；蓄水面积单位为平米
	private Double storage_capacity; //蓄水容量；单位为立方米
	private Double maximum_depth;    //最大深度；单位为米
	private String management_unit;     //管理单位
	private String traffic_condition;    //交通情况
	private String is_cableway;     //是否有电线索道；1_是,2_否
	private String is_take_water;    //能否吊桶取水；1_是,2_否
	private String note;        //说明

	private String city_name;   //市名
	private String county_name; //区/名称

	private List<TSMediaFile> mediaFiles = new ArrayList<>();


	private Date createTime = new Date();

	@Column(nullable = false)
	public String getPac() {
		return pac;
	}

	public void setPac(String pac) {
		this.pac = pac;
	}

	@Column(nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
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

	public Double getVolume() {
		return volume;
	}

	public void setVolume(Double volume) {
		this.volume = volume;
	}

	@Transient
	public String getShape() {
		return shape;
	}

	public void setShape(String shape) {
		this.shape = shape;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Double getStorage_area() {
		return storage_area;
	}

	public void setStorage_area(Double storage_area) {
		this.storage_area = storage_area;
	}

	public void setStorage_capacity(Double storage_capacity) {
		this.storage_capacity = storage_capacity;
	}
	public Double getStorage_capacity() {
		return storage_capacity;
	}

	public Double getMaximum_depth() {
		return maximum_depth;
	}

	public void setMaximum_depth(Double maximum_depth) {
		this.maximum_depth = maximum_depth;
	}

	public String getManagement_unit() {
		return management_unit;
	}

	public void setManagement_unit(String management_unit) {
		this.management_unit = management_unit;
	}

	@Column(name = "traffic_condition")
	public String getTraffic_condition() {
		return traffic_condition;
	}

	public void setTraffic_condition(String traffic_condition) {
		this.traffic_condition = traffic_condition;
	}

	public String getIs_cableway() {
		return is_cableway;
	}

	public void setIs_cableway(String is_cableway) {
		this.is_cableway = is_cableway;
	}

	public String getIs_take_water() {
		return is_take_water;
	}

	public void setIs_take_water(String is_take_water) {
		this.is_take_water = is_take_water;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
