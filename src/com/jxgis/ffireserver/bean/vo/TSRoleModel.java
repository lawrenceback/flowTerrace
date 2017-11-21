package com.jxgis.ffireserver.bean.vo;

import com.jxgis.ffireserver.web.bean.TSRole;

import java.util.ArrayList;
import java.util.List;

public class TSRoleModel {
	private List<TSRole> roles = new ArrayList<>();

	public List<TSRole> getRoles() {
		return roles;
	}

	public void setRoles(List<TSRole> roles) {
		this.roles = roles;
	}
}
