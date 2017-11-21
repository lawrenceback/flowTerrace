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
 * 大型警示牌(大型宣传牌)
 *
 */
@Entity
@Table(name = "ffp_warningBoards")
public class FFPWarningBoards extends IdEntity implements java.io.Serializable {

	private String shape;
	private String name;        //名称
	private String address;     //地址
	private String manager;     //管理员
	private String phone;       //管理员电话
	private Double longitude;   //经度
	private Double latitude;    //纬度
	private String content;     //主要内容
	private String status;      //状态
	private String note;        //说明
	private String build_year;  //建设年度
	private String build_unit;  //修建单位
	private String type;        //类型
	private String number;      //编号
	private String management_unit; //管理单位
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public String getBuild_year() {
		return build_year;
	}

	public void setBuild_year(String build_year) {
		this.build_year = build_year;
	}

	public String getBuild_unit() {
		return build_unit;
	}

	public void setBuild_unit(String build_unit) {
		this.build_unit = build_unit;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getManagement_unit() {
		return management_unit;
	}

	public void setManagement_unit(String management_unit) {
		this.management_unit = management_unit;
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
