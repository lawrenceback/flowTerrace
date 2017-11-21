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
 * Created by YanWei on 2017/7/31.
 *
 * 无线电台站表
 *
 */
@Entity
@Table(name = "ffp_radio_station")
public class FFPRadioStation extends IdEntity implements java.io.Serializable {

	private String pac;         //行政区代码
	private String name;        //名称
	private String address;
	private String manager;     //管理员
	private String phone;       //管理员电话
	private Double longitude;   //径度
	private Double latitude;    //纬度
	private Double elevation;   //海拔
	private String units;          //使用单位
	private String num_radio;    //电台呼号
	private String radioname;   //电台名称
	private String type;      //电台类型
	private String coding;      //电台编号
	private String r_frequenc;  //接收频率
	private String l_frequenc;  //发射频率
	private String power;       //发射功率
	private Double height;      //天线高度
	private String shape;          //形状
	private Integer status;         //状态；1_优秀,2_良好,3_一般,4_差,5_废弃。
	private String d_type;           //类型
	private String note;        //说明
	private Date build_year;     //建站时间

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

	public Double getElevation() {
		return elevation;
	}

	public void setElevation(Double elevation) {
		this.elevation = elevation;
	}

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	@Column(name = "num_radio", nullable = false)
	public String getNum_radio() {
		return num_radio;
	}

	public void setNum_radio(String num_radio) {
		this.num_radio = num_radio;
	}

	@Column(name = "radioname", nullable = false)
	public String getRadioname() {
		return radioname;
	}

	public void setRadioname(String radioname) {
		this.radioname = radioname;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCoding() {
		return coding;
	}

	public void setCoding(String coding) {
		this.coding = coding;
	}

	public String getR_frequenc() {
		return r_frequenc;
	}

	public void setR_frequenc(String r_frequenc) {
		this.r_frequenc = r_frequenc;
	}

	public String getL_frequenc() {
		return l_frequenc;
	}

	public void setL_frequenc(String l_frequenc) {
		this.l_frequenc = l_frequenc;
	}

	public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
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

	public String getD_type() {
		return d_type;
	}

	public void setD_type(String d_type) {
		this.d_type = d_type;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Column(name = "build_year")
	public Date getBuild_year() {
		return build_year;
	}

	public void setBuild_year(Date build_year) {
		this.build_year = build_year;
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
