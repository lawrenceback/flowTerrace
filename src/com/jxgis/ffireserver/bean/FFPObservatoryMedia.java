package com.jxgis.ffireserver.bean;

import com.jxgis.ffireserver.web.bean.IdEntity;
import com.jxgis.ffireserver.web.bean.TSMediaFile;

import javax.persistence.*;

/**
 * Created by YanWei on 2017/7/27.
 *
 * 瞭望台媒体文件
 *
 */
@Entity
@Table(name = "ffp_observatory_media")
public class FFPObservatoryMedia extends IdEntity implements java.io.Serializable {

	private TSMediaFile mediaFile;
	private FFPObservatory observatory;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "media_file_id")
	public TSMediaFile getMediaFile() {
		return mediaFile;
	}

	public void setMediaFile(TSMediaFile mediaFile) {
		this.mediaFile = mediaFile;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "observatory_id")
	public FFPObservatory getObservatory() {
		return observatory;
	}

	public void setObservatory(FFPObservatory observatory) {
		this.observatory = observatory;
	}
}
