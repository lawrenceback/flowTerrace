package com.jxgis.ffireserver.service.bean.response;

import javax.xml.bind.annotation.XmlRootElement;

import com.jxgis.ffireserver.bean.Result;

@XmlRootElement(name = "pushToTalkResponse")
public class PushToTalkResponse {

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
