package com.jxgis.ffireserver.bean.vo;

import com.jxgis.ffireserver.bean.FFPArtificiallake;

import java.util.ArrayList;
import java.util.List;

/**
 * 航空灭火蓄水池实体模型
 */
public class FFPArtificiallakeModel {

	private List<FFPArtificiallake> artificiallake = new ArrayList<>();

	public List<FFPArtificiallake> getArtificiallake() {
		return artificiallake;
	}

	public void setArtificiallake(List<FFPArtificiallake> artificiallake) {
		this.artificiallake = artificiallake;
	}
}
