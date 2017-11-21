package com.jxgis.ffireserver.bean;

import javax.persistence.*;

/**
 * Created by YanWei on 2017/6/14.
 *
 */
@Entity
@Table(name = "ffp_area_code")
@Inheritance(strategy = InheritanceType.JOINED)
public class FFPArea implements java.io.Serializable{
	private String code;
	private String name;
	private int sorting;        //排序

	@Id
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSorting() {
		return sorting;
	}

	public void setSorting(int sorting) {
		this.sorting = sorting;
	}



	@Override
	public String toString() {
		return "FFPArea{" +
				"code='" + code + '\'' +
				", name='" + name + '\'' +
				'}';
	}
}
