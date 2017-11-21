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
 * Created by YanWei on 2017/8/1.
 *
 * 森林防火指挥部
 *
 *
 */
@Entity
@Table(name = "ffp_command")
public class FFPCommand extends IdEntity implements java.io.Serializable {

	private String pac;         //行政区号代码
	private String shape;       //形状
	private String name;        //名称
	private String address;     //地址
	private String phone;       //值班电话
	private String director;    //办公室主任
	private String dir_phone;   //主任电话
	private Double longitude;      //经度
	private Double latitude;       //纬度
	private Integer num_people;      //机构人数
	private String institutions;   //机构编制；行政_1,行政事业_2,全额拨款事业_3,差额拨款事业_4,自收自支事业_5,未定编_6,其它_7
	private String type;           //类型；防火指挥部_1,森防指所_2,其它事业机构_3
	private String level;          //级别
	private Integer status;         //状态
	private String note;        //说明
	private String commander;   //指挥长
	private String commander_phone; //指挥长电话

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

	public void setDir_phone(String dirPhone) {
		this.dir_phone = dirPhone;
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

	public void setNum_people(Integer numPeople) {
		this.num_people = numPeople;
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

	public String getCommander() {
		return commander;
	}

	public void setCommander(String commander) {
		this.commander = commander;
	}

	public String getCommander_phone() {
		return commander_phone;
	}

	public void setCommander_phone(String commanderPhone) {
		this.commander_phone = commanderPhone;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
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
