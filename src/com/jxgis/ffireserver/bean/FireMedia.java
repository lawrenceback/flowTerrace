package com.jxgis.ffireserver.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "media")
public class FireMedia {

	/**
	 * 自增长ID
	 */
	private int id;

	/**
	 * 所属火灾档案号
	 */
	private String recordNumber;

	/**
	 * 媒体名
	 */
	private String filename;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRecordNumber() {
		return recordNumber;
	}

	public void setRecordNumber(String recordNumber) {
		this.recordNumber = recordNumber;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

}
