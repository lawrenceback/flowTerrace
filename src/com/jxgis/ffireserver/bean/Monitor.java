package com.jxgis.ffireserver.bean;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 监控头
 * 
 * @author 何桂明
 * 
 */
@XmlRootElement(name = "monitor")
public class Monitor {

	private int id;
	// 市
	private String city;
	// 县
	private String country;

	/**
	 * 行政区代码
	 */
	private String pac;
	// 地址
	private String address;
	// IP
	private String ip;

	/**
	 * 端口
	 */
	private int port;

	// 通道
	private int channel;
	// 经度
	private double longitude;
	// 纬度
	private double latitude;
	// 海拔
	private double elevation;
	// 账号
	private String account;
	// 密码
	private String password;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getChannel() {
		return channel;
	}

	public void setChannel(int channel) {
		this.channel = channel;
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

	public double getElevation() {
		return elevation;
	}

	public void setElevation(double elevation) {
		this.elevation = elevation;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPac() {
		return pac;
	}

	public void setPac(String pac) {
		this.pac = pac;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

}
