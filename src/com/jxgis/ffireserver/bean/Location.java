package com.jxgis.ffireserver.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "location")
public class Location {

	/**
	 * 用户账号
	 */
	private String account;

	/**
	 * 名字
	 */
	private String name;

	/**
	 * 用户号码
	 */
	private String phone;

	/**
	 * 头像url
	 */
	private String headUrl;

	/**
	 * 经度
	 */
	private double longitude;

	/**
	 * 纬度
	 */
	private double latitude;

	/**
	 * 是否是火点信息 0:用户信息 1:火点信息
	 */
	private int isFire = 0;

	// 账户权限
	private int competence = -1;

	private String voipAccount;

	private String authToken;

	public Location() {

	}

	public Location(double longitude, double latitude) {
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public Location(String account, String phone, String name,
			double longitude, double latitude) {
		this.account = account;
		this.phone = phone;
		this.name = name;
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public int getCompetence() {
		return competence;
	}

	public void setCompetence(int competence) {
		this.competence = competence;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
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

	public int getIsFire() {
		return isFire;
	}

	public void setIsFire(int isFire) {
		this.isFire = isFire;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVoipAccount() {
		return voipAccount;
	}

	public void setVoipAccount(String voipAccount) {
		this.voipAccount = voipAccount;
	}

	public String getHeadUrl() {
		return headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

}
