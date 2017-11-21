package com.jxgis.ffireserver.service.bean.response;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.jxgis.ffireserver.bean.FireMedia;

@XmlRootElement(name = "fireMediaList")
public class FireMediaList {

	private List<FireMedia> fireMedia;
	
	public FireMediaList() {
		
	}
	
	public FireMediaList(List<FireMedia> fireMedia) {
		this.fireMedia = fireMedia;
	}

	public List<FireMedia> getFireMedia() {
		return fireMedia;
	}

	public void setFireMedia(List<FireMedia> fireMedia) {
		this.fireMedia = fireMedia;
	}

}
