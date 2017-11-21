package com.jxgis.ffireserver.service.bean.response;

import javax.xml.bind.annotation.XmlRootElement;

import com.jxgis.ffireserver.bean.Result;

@XmlRootElement(name = "getUserListResult")
public class GetUserListResult {

	private Result result;
	private UserList userList;

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public UserList getUserList() {
		return userList;
	}

	public void setUserList(UserList userList) {
		this.userList = userList;
	}

}
