package com.jxgis.ffireserver.bean;

import com.jxgis.ffireserver.web.bean.IdEntity;
import com.jxgis.ffireserver.web.bean.TSMediaFile;

import javax.persistence.*;

/**
 * Created by YanWei on 2017/8/11.
 *
 * 远程监控表媒体文件表
 *
 */
@Entity
@Table(name = "ffp_fire_remotemonitoring_media")
public class FFPRemoteMonitoringMedia extends IdEntity implements java.io.Serializable {

	private FFPRemoteMonitoring monitoring;
	private TSMediaFile mediaFile;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "remotemonitoring_id")
	public FFPRemoteMonitoring getMonitoring() {
		return monitoring;
	}

	public void setMonitoring(FFPRemoteMonitoring monitoring) {
		this.monitoring = monitoring;
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
