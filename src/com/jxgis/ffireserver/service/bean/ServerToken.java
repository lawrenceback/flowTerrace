package com.jxgis.ffireserver.service.bean;

public class ServerToken {

	private String account;
	private long time;

	public ServerToken() {

	}

	public ServerToken(String account, long time) {
		this.account = account;
		this.time = time;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

}
