package com.jxgis.ffireserver.bean;

import com.jxgis.ffireserver.web.bean.IdEntity;
import com.jxgis.ffireserver.web.bean.TSMediaFile;

import javax.persistence.*;

/**
 * Created by YanWei on 2017/8/29.
 *
 * 飞机吊桶取水点 媒体文件
 *
 */
@Entity
@Table(name = "ffp_planewaterpoint_media")
public class FFPPlanewaterpointMedia extends IdEntity implements java.io.Serializable {
	private FFPPlanewaterpoint planewaterpoint;
	private TSMediaFile mediaFile;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "planewaterpoint_id")
	public FFPPlanewaterpoint getPlanewaterpoint() {
		return planewaterpoint;
	}

	public void setPlanewaterpoint(FFPPlanewaterpoint planewaterpoint) {
		this.planewaterpoint = planewaterpoint;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "media_file_id")
	public TSMediaFile getMediaFile() {
		return mediaFile;
	}

	public void setMediaFile(TSMediaFile mediaFile) {
		this.mediaFile = mediaFile;
	}
}
