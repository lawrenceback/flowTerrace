package com.jxgis.ffireserver.web.bean;

import javax.persistence.*;

/**
 * Created by YanWei on 2017/7/19.
 *
 * 控件表
 *
 */
@Entity
@Table(name = "t_s_control")
public class TSControl extends IdEntity implements java.io.Serializable{

	private String controlCode;     //控件代码
	private String controlName;     //控件名称
	private int type;   //1_隐藏,2_禁用


	private int checked;


	@Column(name = "control_code", nullable = false, unique = true)
	public String getControlCode() {
		return controlCode;
	}

	public void setControlCode(String controlCode) {
		this.controlCode = controlCode;
	}


	@Column(name = "control_name", nullable = false)
	public String getControlName() {
		return controlName;
	}

	public void setControlName(String controlName) {
		this.controlName = controlName;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Transient
	public int getChecked() {
		return checked;
	}

	public void setChecked(int checked) {
		this.checked = checked;
	}
}
