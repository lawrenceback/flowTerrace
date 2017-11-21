package com.jxgis.ffireserver.bean.vo;

import com.jxgis.ffireserver.web.bean.TSUser;

import java.util.ArrayList;
import java.util.List;

public class TSUserModel {
	private List<TSUser> users = new ArrayList<>();

	public List<TSUser> getUsers() {
		return users;
	}

	public void setUsers(List<TSUser> users) {
		this.users = users;
	}
}
