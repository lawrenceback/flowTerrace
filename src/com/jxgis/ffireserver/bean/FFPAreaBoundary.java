package com.jxgis.ffireserver.bean;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Created by YanWei on 2017/9/28.
 *
 */
@Entity
@Table(name = "ffp_area_code_boundary")
@PrimaryKeyJoinColumn(name = "code")
public class FFPAreaBoundary extends FFPArea implements java.io.Serializable{
	private String boundary;    //边界

	@Transient
	public String getBoundary() {
		return boundary;
	}

	public void setBoundary(String boundary) {
		this.boundary = boundary;
	}
}
