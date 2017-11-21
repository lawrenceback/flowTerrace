package com.jxgis.ffireserver.service.bean.request;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "modifyUserAvatar")
public class ModifyUserAvatar {

	/**
	 * 用户账号
	 */
	private String account;

	/**
	 * 头像url
	 */
	private String avatarUrl;
	
	public ModifyUserAvatar() {
		
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

}
