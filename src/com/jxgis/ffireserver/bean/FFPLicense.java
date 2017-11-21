package com.jxgis.ffireserver.bean;

import com.jxgis.ffireserver.web.bean.IdEntityAtuo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by YanWei on 2017/6/12.
 *
 * IMEI 设备序列表
 *
 * @author 言伟
 *
 * 名称取名取imei（最开始讨论为imei），但是我觉得更像是对一个授权码的管理
 */
@Entity
@Table(name = "ffp_license")
public class FFPLicense extends IdEntityAtuo implements java.io.Serializable{

	private String key;     //授权码；六个大写长度（字母+数字）
	private String imei;    //设备序列号
	private String pac;     //行政区号代码
	private Integer status; //许可状态【永久_1,试用_2,作废_3】
	private Date expTime;   //过期时间，当状态为2时才有用
	private String founder; //许可创建人员
	private Integer deviceType; //设备类型；移动设备_1,PC设备_2
	private Integer device;     //设备种类；Android_1,IOS_2,PC_3
	private Date createTime = new Date();   //创建时间

	@Column(name="`key`", length = 6,nullable=false,unique=true)
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Column(length = 15)
	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	@Column(length = 6)
	public String getPac() {
		return pac;
	}

	public void setPac(String pac) {
		this.pac = pac;
	}

	@Column(length = 2)
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "exp_time")
	public Date getExpTime() {
		return expTime;
	}

	public void setExpTime(Date expTime) {
		this.expTime = expTime;
	}

	public String getFounder() {
		return founder;
	}

	public void setFounder(String founder) {
		this.founder = founder;
	}

	@Column(name = "device_type")
	public Integer getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(Integer deviceType) {
		this.deviceType = deviceType;
	}

	@Column(name = "device")
	public Integer getDevice() {
		return device;
	}

	public void setDevice(Integer device) {
		this.device = device;
	}

	@Column(name = "create_time")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
