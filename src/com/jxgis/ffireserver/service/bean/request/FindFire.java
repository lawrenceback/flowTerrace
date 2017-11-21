package com.jxgis.ffireserver.service.bean.request;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "findFire")
public class FindFire {

	// 账户信息
	private String account;

	// 媒体信息Base64编码
	private String mediaBase64Str;
	// 火势大小
	private int fireSize;
	// 火势范围
	private int fireRange;

	// 经度
	private double longitude;

	// 纬度
	private double latitude;

	public String getMediaBase64Str() {
		return mediaBase64Str;
	}

	public void setMediaBase64Str(String mediaBase64Str) {
		this.mediaBase64Str = mediaBase64Str;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public int getFireSize() {
		return fireSize;
	}

	public void setFireSize(int fireSize) {
		this.fireSize = fireSize;
	}

	public int getFireRange() {
		return fireRange;
	}

	public void setFireRange(int fireRange) {
		this.fireRange = fireRange;
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

}
