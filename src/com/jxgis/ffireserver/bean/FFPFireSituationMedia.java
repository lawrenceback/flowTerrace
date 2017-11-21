package com.jxgis.ffireserver.bean;

import com.jxgis.ffireserver.web.bean.IdEntity;
import com.jxgis.ffireserver.web.bean.TSMediaFile;

import javax.persistence.*;

/**
 * Created by YanWei on 2017/8/17.
 *
 * 火情调度表媒体文件
 *
 */
@Entity
@Table(name = "ffp_fire_situation_media")
public class FFPFireSituationMedia extends IdEntity implements java.io.Serializable {

	private FFPFireSituation fireSituation;
	private TSMediaFile mediaFile;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fire_situation_id")
	public FFPFireSituation getFireSituation() {
		return fireSituation;
	}

	public void setFireSituation(FFPFireSituation fireSituation) {
		this.fireSituation = fireSituation;
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
