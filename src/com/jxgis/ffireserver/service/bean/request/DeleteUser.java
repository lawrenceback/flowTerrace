package com.jxgis.ffireserver.service.bean.request;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "deleteUser")
public class DeleteUser {

	private String account;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

}
