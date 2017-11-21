package com.jxgis.ffireserver.bean;

import javax.xml.bind.annotation.XmlRootElement;

import com.jxgis.ffireserver.service.bean.response.FireMediaList;

@XmlRootElement(name = "fireRecord")
public class FireRecord {

	// 火灾档案编号自增长
	private int id;
	// 火灾编号
	private String recordNumber;
	// 热点编号
	private String hotId;
	// 火灾名称
	private String name;
	// 起火地点
	private String address;
	// 经度
	private double longitude;
	// 纬度
	private double latitude;
	// 起火时间
	private String sTime;
	// 扑灭时间
	private String eTime;
	// 火灾类型
	private String fireType;
	// 起火原因
	private String cause;
	// 火场总面积(单位:平方米)
	private double fireArea;
	// 林种
	private String tCateg;
	// 火场指挥员
	private String conductor;
	// 出动人员(单位:人)
	private int brigade;
	// 工具(单位:个)
	private int equip;
	// 扑火经费(单位:万元)
	private double fightCost;
	// 火情简介或说明
	private String description;
	// 建立时间
	private String creTime;
	// 建立人
	private String crePers;
	// 修改时间
	private String modTime;
	// 修改者
	private String modPers;
	// 市
	private String city;
	// 县
	private String county;
	// 媒体信息集合
	private FireMediaList fireMediaList;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRecordNumber() {
		return recordNumber;
	}

	public void setRecordNumber(String recordNumber) {
		this.recordNumber = recordNumber;
	}

	public String getHotId() {
		return hotId;
	}

	public void setHotId(String hotId) {
		this.hotId = hotId;
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

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public String getFireType() {
		return fireType;
	}

	public void setFireType(String fireType) {
		this.fireType = fireType;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public double getFireArea() {
		return fireArea;
	}

	public void setFireArea(double fireArea) {
		this.fireArea = fireArea;
	}

	public String gettCateg() {
		return tCateg;
	}

	public void settCateg(String tCateg) {
		this.tCateg = tCateg;
	}

	public String getConductor() {
		return conductor;
	}

	public void setConductor(String conductor) {
		this.conductor = conductor;
	}

	public int getBrigade() {
		return brigade;
	}

	public void setBrigade(int brigade) {
		this.brigade = brigade;
	}

	public int getEquip() {
		return equip;
	}

	public void setEquip(int equip) {
		this.equip = equip;
	}

	public double getFightCost() {
		return fightCost;
	}

	public void setFightCost(double fightCost) {
		this.fightCost = fightCost;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCrePers() {
		return crePers;
	}

	public void setCrePers(String crePers) {
		this.crePers = crePers;
	}

	public String getsTime() {
		return sTime;
	}

	public void setsTime(String sTime) {
		this.sTime = sTime;
	}

	public String geteTime() {
		return eTime;
	}

	public void seteTime(String eTime) {
		this.eTime = eTime;
	}

	public String getCreTime() {
		return creTime;
	}

	public void setCreTime(String creTime) {
		this.creTime = creTime;
	}

	public String getModTime() {
		return modTime;
	}

	public void setModTime(String modTime) {
		this.modTime = modTime;
	}

	public String getModPers() {
		return modPers;
	}

	public void setModPers(String modPers) {
		this.modPers = modPers;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public FireMediaList getFireMediaList() {
		return fireMediaList;
	}

	public void setFireMediaList(FireMediaList fireMediaList) {
		this.fireMediaList = fireMediaList;
	}

}
