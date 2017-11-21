package com.jxgis.ffireserver.util;

/**
 * Created by Administrator on 2017/8/17.
 */
public class RadioUser {

	private int id;
	private String account;
	private String pac;
	private String name;
	private String avatar;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPac() {
		return pac;
	}

	public void setPac(String pac) {
		this.pac = pac;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public RadioUser() {
	}

	public RadioUser(int id, String account, String pac, String name, String avatar) {
		this.id = id;
		this.account = account;
		this.pac = pac;
		this.name = name;
		this.avatar = avatar;
	}
}
