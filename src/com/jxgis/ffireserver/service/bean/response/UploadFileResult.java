package com.jxgis.ffireserver.service.bean.response;

import javax.xml.bind.annotation.XmlRootElement;

import com.jxgis.ffireserver.bean.Result;

@XmlRootElement(name = "uploadFileResult")
public class UploadFileResult {

	private Result result;

	private String url;

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
