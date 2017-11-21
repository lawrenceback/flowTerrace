package com.jxgis.ffireserver.web.bean;

import javax.persistence.*;

/**
 * Created by YanWei on 2017/7/19.
 *
 * 控件角色权限
 *
 */
@Entity
@Table(name = "t_s_control_role_permissions")
public class TSControlRolePermissions extends IdEntity implements java.io.Serializable{

	private TSRole role;
	private TSControl control;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id", nullable = false)
	public TSRole getRole() {
		return role;
	}

	public void setRole(TSRole role) {
		this.role = role;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "control_id", nullable = false)
	public TSControl getControl() {
		return control;
	}

	public void setControl(TSControl control) {
		this.control = control;
	}
}
