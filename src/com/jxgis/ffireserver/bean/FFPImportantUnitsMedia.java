package com.jxgis.ffireserver.bean;

import com.jxgis.ffireserver.web.bean.IdEntity;
import com.jxgis.ffireserver.web.bean.TSMediaFile;

import javax.persistence.*;

/**
 * Created by YanWei on 2017/8/24.
 *
 * 重点防火单位 媒体文件
 *
 */
@Entity
@Table(name = "ffp_importantUnits_media")
public class FFPImportantUnitsMedia extends IdEntity implements java.io.Serializable {
	private FFPImportantUnits importantUnits;
	private TSMediaFile mediaFile;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "importantUnits_id")
	public FFPImportantUnits getImportantUnits() {
		return importantUnits;
	}

	public void setImportantUnits(FFPImportantUnits importantUnits) {
		this.importantUnits = importantUnits;
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
