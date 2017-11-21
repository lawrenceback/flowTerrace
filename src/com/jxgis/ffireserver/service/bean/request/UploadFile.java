package com.jxgis.ffireserver.service.bean.request;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "uploadFile")
public class UploadFile {

	private String account;
	private String fileName;
	// 文件的BASE64编码
	private String media;
	private String thumbFileName;
	private String thumbMedia;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getMedia() {
		return media;
	}

	public void setMedia(String media) {
		this.media = media;
	}

	public String getThumbFileName() {
		return thumbFileName;
	}

	public void setThumbFileName(String thumbFileName) {
		this.thumbFileName = thumbFileName;
	}

	public String getThumbMedia() {
		return thumbMedia;
	}

	public void setThumbMedia(String thumbMedia) {
		this.thumbMedia = thumbMedia;
	}

}
