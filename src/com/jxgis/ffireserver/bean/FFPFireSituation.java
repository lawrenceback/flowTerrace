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
 * Created by YanWei on 2017/8/17.
 *
 * 火情上报管理表
 */
@Entity
@Table(name = "ffp_fire_situation")
public class FFPFireSituation extends IdEntity implements java.io.Serializable {


	private Integer type = 0;    //-1-测试,1-12119,2-微信上报,3-森林防火终端上报,4-护林通上报,5-其它
	private String reporter;    //火情上报人员
	private Integer age = 0;            //火情上报员年龄
	private String phone;       //火情上报员电话
	private String occupation;  //火情上报员职业
	private String duty;        //值班员
	private Double longitude = 0.0;
	private Double latitude = 0.0;
	private String place;       //火情发生的小地名
	private Date time;
	private Double area = 0.0;
	private Integer fireType;       //1-火灾，2-违规炼山，3-农用火，4-计划烧除，5-其他，6-境外火
	private Integer fireFighting = 0;   //1-没有人,2-少许人,3-很多人
	private String description; //
	private Integer state = 0;      //1-新接收到的火情，2-已经存档并结束的火情，3-扑救调度中的火情，4-调度结束并已经正式形成火灾的火情

	private List<TSMediaFile> mediaFiles = new ArrayList<>();
	private Date createTime = new Date();

	@Column(nullable = false)
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getReporter() {
		return reporter;
	}

	public void setReporter(String reporter) {
		this.reporter = reporter;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
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

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	@Column(nullable = false)
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Double getArea() {
		return area;
	}

	public void setArea(Double area) {
		this.area = area;
	}

	@Column(nullable = false)
	public Integer getFireType() {
		return fireType;
	}

	public void setFireType(Integer fireType) {
		this.fireType = fireType;
	}

	@Column(nullable = false)
	public Integer getFireFighting() {
		return fireFighting;
	}

	public void setFireFighting(Integer fireFighting) {
		this.fireFighting = fireFighting;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(nullable = false)
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
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
