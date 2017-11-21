package com.jxgis.ffireserver.service.bean.response;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.jxgis.ffireserver.web.bean.TSUser;

@XmlRootElement(name = "userList")
public class UserList {

	private List<TSUser> user;
	
	public UserList() {
		
	}
	
	public UserList(List<TSUser> user) {
		this.user = user;
	}

	public List<TSUser> getUser() {
		return user;
	}

	public void setUser(List<TSUser> user) {
		this.user = user;
	}

}
