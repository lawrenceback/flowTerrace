package com.jxgis.ffireserver.bean;

import com.jxgis.ffireserver.web.bean.IdEntity;
import com.jxgis.ffireserver.web.bean.TSMediaFile;

import javax.persistence.*;

/**
 * Created by YanWei on 2017/8/23.
 *
 * 专业森林消防队 媒体文件
 */
@Entity
@Table(name = "ffp_pbrigade_media")
public class FFPPBrigadeMedia extends IdEntity implements java.io.Serializable {
	private FFPPBrigade brigade;
	private TSMediaFile mediaFile;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "brigade_id")
	public FFPPBrigade getBrigade() {
		return brigade;
	}

	public void setBrigade(FFPPBrigade brigade) {
		this.brigade = brigade;
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
