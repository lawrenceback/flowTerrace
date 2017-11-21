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
 * Created by YanWei on 2017/6/26.
 *
 * 远程监控表
 *
 */
@Entity
@Table(name = "ffp_fire_remotemonitoring")
public class FFPRemoteMonitoring extends IdEntity implements java.io.Serializable{

	private String pac;         //行政区代码
	private String name;
	private String manager;     //管理员
	private String phone;       //管理员电话
	private String address;
	private Double longitude;   //经度
	private Double latitude;    //纬度
	private String deviceid;    //设备编号
	private String ip;          //视频服务器IP地址
	private String user_;       //登录用户
	private String username;    //登录用户
	private String password;    //登陆密码
	private Double channel;     //通道
	private Integer port;       //登陆端口
	private Double height;      //云台高度,塔高
	private Double elevation;   //云台当前所处地海拔
	private Double h_offset;    //云台水平角纠正值
	private Double v_offset;    //云台俯仰角纠正值
	private String note;        //说明
	private String shape;       //几何


	private List<TSMediaFile> mediaFiles = new ArrayList<>();
	private Date createTime = new Date();


	@Column(length = 50)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(length = 80)
	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	@Column(length = 30)
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

	@Column(length = 30)
	public String getDeviceid() {
		return deviceid;
	}

	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(length = 30)
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	@Column(length = 50)
	public String getUser_() {
		return user_;
	}

	public void setUser_(String user_) {
		this.user_ = user_;
	}

	@Column(length = 50)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Double getElevation() {
		return elevation;
	}

	public void setElevation(Double elevation) {
		this.elevation = elevation;
	}

	public Double getH_offset() {
		return h_offset;
	}

	public void setH_offset(Double h_offset) {
		this.h_offset = h_offset;
	}
	public Double getV_offset() {
		return v_offset;
	}

	public void setV_offset(Double v_offset) {
		this.v_offset = v_offset;
	}

	public String getPac() {
		return pac;
	}

	public void setPac(String pac) {
		this.pac = pac;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Double getChannel() {
		return channel;
	}

	public void setChannel(Double channel) {
		this.channel = channel;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Transient
	public String getShape() {
		return shape;
	}

	public void setShape(String shape) {
		this.shape = shape;
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
