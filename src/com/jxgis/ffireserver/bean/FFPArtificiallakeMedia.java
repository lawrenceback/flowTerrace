package com.jxgis.ffireserver.bean;

import com.jxgis.ffireserver.web.bean.IdEntity;
import com.jxgis.ffireserver.web.bean.TSMediaFile;

import javax.persistence.*;

/**
 * Created by YanWei on 2017/8/3.
 *
 * 航空灭火蓄水池媒体文件
 *
 */
@Entity
@Table(name = "ffp_artificiallake_media")
public class FFPArtificiallakeMedia extends IdEntity implements java.io.Serializable {

	private FFPArtificiallake artificiallake;
	private TSMediaFile mediaFile;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "artificiallake_id")
	public FFPArtificiallake getArtificiallake() {
		return artificiallake;
	}

	public void setArtificiallake(FFPArtificiallake artificiallake) {
		this.artificiallake = artificiallake;
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
