package com.jxgis.ffireserver.service.bean.response;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.jxgis.ffireserver.bean.FireRecord;

@XmlRootElement(name = "fireRecordList")
public class FireRecordList {

	private List<FireRecord> fireRecord;

	public FireRecordList() {

	}

	public FireRecordList(List<FireRecord> fireRecord) {
		this.fireRecord = fireRecord;
	}

	public List<FireRecord> getFireRecord() {
		return fireRecord;
	}

	public void setFireRecord(List<FireRecord> fireRecord) {
		this.fireRecord = fireRecord;
	}

}
