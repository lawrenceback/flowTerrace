package com.jxgis.ffireserver.bean;

import com.jxgis.ffireserver.web.bean.IdEntity;
import com.jxgis.ffireserver.web.bean.TSMediaFile;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

/**
 * Created by YanWei on 2017/8/28.
 *
 * 森林防火办公室
 *
 */
@Entity
@Table(name = "ffp_office")
public class FFPOffice extends IdEntity implements java.io.Serializable {

	private String shape;
	private String name;        //名称
	private String address;     //所在地点
	private String phone;       //值班电话
	private String director;    //办公室主任
	private String dir_phone;   //主任电话
	private Double longitude;   //经度
	private Double latitude;    //纬度
	private Integer num_people;     //机构人数
	private String institutions;    //机构编制
	private String type;            //类型
	private String level;           //级别
	private String status;          //状态
	private String note;            //说明
	private String pac;             //行政区划代码
	private List<TSMediaFile> mediaFiles;
	private Date createTime = new Date();

	@Transient
	public String getShape() {
		return shape;
	}

	public void setShape(String shape) {
		this.shape = shape;
	}

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

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getDir_phone() {
		return dir_phone;
	}

	public void setDir_phone(String dir_phone) {
		this.dir_phone = dir_phone;
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

	public Integer getNum_people() {
		return num_people;
	}

	public void setNum_people(Integer num_people) {
		this.num_people = num_people;
	}

	public String getInstitutions() {
		return institutions;
	}

	public void setInstitutions(String institutions) {
		this.institutions = institutions;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
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
