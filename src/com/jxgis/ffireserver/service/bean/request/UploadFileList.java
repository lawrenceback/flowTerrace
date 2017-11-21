package com.jxgis.ffireserver.service.bean.request;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "uploadFileList")
public class UploadFileList {

	private List<UploadFile> uploadFile;

	public List<UploadFile> getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(List<UploadFile> uploadFile) {
		this.uploadFile = uploadFile;
	}

}
