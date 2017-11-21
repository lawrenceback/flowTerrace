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
 * Created by YanWei on 2017/8/30.
 *
 *
 * 森林防火物资储备库
 *
 */
@Entity
@Table(name = "ffp_warehouse")
public class FFPWarehouse extends IdEntity implements java.io.Serializable {

	private String shape;       //Geometry
	private String name;        //名称
	private String address;     //地址
	private String phone;       //值班电话
	private String manager;     //管理员
	private Double longitude;   //经度
	private Double latitude;    //纬度
	private Integer typeid ;    //类型编号
	private Integer user_id ;   //所属编号
	private Integer mat_id ;    //物资编号
	private String build_year;  //建设年度
	private Double build_area;  //面积
	private String status;      //状态
	private String note;        //说明
	private String type;        //类型
	private String management_unit; //管理单位
	private String director;        //管理单位负责人
	private String reserve_type;    //储备类型
	private Short quantity ;     //物资数量
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

	public Integer getTypeid() {
		return typeid;
	}

	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Integer getMat_id() {
		return mat_id;
	}

	public void setMat_id(Integer mat_id) {
		this.mat_id = mat_id;
	}

	public String getBuild_year() {
		return build_year;
	}

	public void setBuild_year(String build_year) {
		this.build_year = build_year;
	}

	public Double getBuild_area() {
		return build_area;
	}

	public void setBuild_area(Double build_area) {
		this.build_area = build_area;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getManagement_unit() {
		return management_unit;
	}

	public void setManagement_unit(String management_unit) {
		this.management_unit = management_unit;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getReserve_type() {
		return reserve_type;
	}

	public void setReserve_type(String reserve_type) {
		this.reserve_type = reserve_type;
	}

	public Short getQuantity() {
		return quantity;
	}

	public void setQuantity(Short quantity) {
		this.quantity = quantity;
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
