package com.jxgis.ffireserver.bean;

import com.jxgis.ffireserver.web.bean.IdEntity;
import com.jxgis.ffireserver.web.bean.TSMediaFile;

import javax.persistence.*;

/**
 * Created by YanWei on 2017/8/10.
 *
 * 火灾热点媒体文件
 *
 */
@Entity
@Table(name = "ffp_fire_hot_media")
public class FFPFireHotMedia extends IdEntity implements java.io.Serializable {

	private FFPFireHot fireHot;
	private TSMediaFile mediaFile;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fire_hot_id")
	public FFPFireHot getFireHot() {
		return fireHot;
	}

	public void setFireHot(FFPFireHot fireHot) {
		this.fireHot = fireHot;
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
