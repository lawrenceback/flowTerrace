package com.jxgis.ffireserver.service.bean.response;

import javax.xml.bind.annotation.XmlRootElement;

import com.jxgis.ffireserver.bean.Result;

@XmlRootElement(name = "findFireResult")
public class FindFireResult {

	// 统一结果
	private Result result;
	
	public FindFireResult() {
		
	}

	public FindFireResult(int resCode) {
		result = new Result(resCode);
	}
	
	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

}
