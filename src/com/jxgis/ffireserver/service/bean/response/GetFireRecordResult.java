package com.jxgis.ffireserver.service.bean.response;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.jxgis.ffireserver.bean.FireRecord;
import com.jxgis.ffireserver.bean.Result;

@XmlRootElement(name = "getFireRecordResult")
public class GetFireRecordResult {

	private Result result;

	private FireRecordList fireRecordList;

	public GetFireRecordResult() {

	}

	public GetFireRecordResult(int resCode, List<FireRecord> fireRecords) {
		result = new Result(resCode);
		fireRecordList = new FireRecordList(fireRecords);
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public FireRecordList getFireRecordList() {
		return fireRecordList;
	}

	public void setFireRecordList(FireRecordList fireRecordList) {
		this.fireRecordList = fireRecordList;
	}

}
