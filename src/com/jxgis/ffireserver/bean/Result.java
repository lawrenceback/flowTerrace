package com.jxgis.ffireserver.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "result")
public class Result {

	// 结果码
	private int resCode;
	// 结果描述信息
	private String resDesc;

	public Result() {

	}

	public Result(int resCode) {
		this.resCode = resCode;
	}
	
	public Result(int resCode, String resDesc) {
		this.resCode = resCode;
		this.resDesc = resDesc;
	}

	public int getResCode() {
		return resCode;
	}

	public void setResCode(int resCode) {
		this.resCode = resCode;
	}

	public String getResDesc() {
		return resDesc;
	}

	public void setResDesc(String resDesc) {
		this.resDesc = resDesc;
	}

}
