package com.jxgis.ffireserver.bean.openfire;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ofuser")
public class Ofuser implements java.io.Serializable{

	private String username;

	@Id
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
