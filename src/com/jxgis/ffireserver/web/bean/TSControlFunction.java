package com.jxgis.ffireserver.web.bean;

import javax.persistence.*;

/**
 * Created by YanWeion 2017/7/19.
 *
 * 控件菜单表
 *
 */
@Entity
@Table(name = "t_s_control_function")
public class TSControlFunction extends IdEntity implements java.io.Serializable{

	private TSControl control;
	private TSFunction function;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "control_id", nullable = false)
	public TSControl getControl() {
		return control;
	}

	public void setControl(TSControl control) {
		this.control = control;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "function_id", nullable = false)
	public TSFunction getFunction() {
		return function;
	}

	public void setFunction(TSFunction function) {
		this.function = function;
	}
}
