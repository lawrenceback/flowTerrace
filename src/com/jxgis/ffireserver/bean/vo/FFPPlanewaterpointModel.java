package com.jxgis.ffireserver.bean.vo;

import com.jxgis.ffireserver.bean.FFPPlanewaterpoint;

import java.util.ArrayList;
import java.util.List;

/**
 * 飞机吊桶取水点 实体模型
 */
public class FFPPlanewaterpointModel {
	private List<FFPPlanewaterpoint> planewaterpoints = new ArrayList<>();

	public List<FFPPlanewaterpoint> getPlanewaterpoints() {
		return planewaterpoints;
	}

	public void setPlanewaterpoints(List<FFPPlanewaterpoint> planewaterpoints) {
		this.planewaterpoints = planewaterpoints;
	}
}
